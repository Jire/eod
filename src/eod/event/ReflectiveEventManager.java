package eod.event;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import com.google.inject.Singleton;

/**
 * An {@link EventManager} which uses reflection to both
 * register {@link EventListener}s and dispatch {@link Event}s.
 * 
 * @author Thomas G. P. Nappo
 * @see {@link AbstractEventManager}
 * @version 1.0 - Added support for cancellation
 * @version 1.1 - Added support for priorities
 * @version 1.1.5 - Added <i> Google Guice</i> default {@link Singleton} binding
 * @version 1.2 - Significant speed improvements
 */
@Singleton
public class ReflectiveEventManager extends AbstractEventManager {

	@Override
	public void dispatchEvent(Event event) {
		CancellableEvent cancellableEvent = null;
		boolean cancellable;
		if (cancellable = event instanceof CancellableEvent) {
			cancellableEvent = (CancellableEvent) event;
			if (cancellableEvent.isCancelled()) {
				return;
			}
		}

		try {
			for (EventPriority priority : EventPriority.values()) {
				Map<Method, EventListener> internalMapping = getRegistry().getMethodMap(event.getClass(), priority);
				if (internalMapping == null) continue;
				for (Entry<Method, EventListener> entry : internalMapping.entrySet()) {
					entry.getKey().invoke(entry.getValue(), event);
					if (cancellable && cancellableEvent.isCancelled()) {
						return;
					}
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

	@Override
	public void registerListener(EventListener listener) {
		for (Method method : listener.getClass().getMethods()) {
			EventHandler handler = method.getAnnotation(EventHandler.class);
			if (handler != null) {
				Class<?>[] parameters = method.getParameterTypes();
				if (parameters.length == 1) {
					@SuppressWarnings("unchecked")
					Class<? extends Event> event = (Class<? extends Event>) parameters[0];
					getRegistry().register(event, handler.priority(), method, listener);
				}
			}
		}
	}

}