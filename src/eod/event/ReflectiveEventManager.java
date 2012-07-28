package eod.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.inject.Singleton;

import eod.OrdinalMap;

/**
 * An {@link EventManager} which uses reflection to both
 * register {@link EventListener}s and dispatch {@link Event}s.
 * 
 * @author Thomas G. P. Nappo
 * @see {@link AbstractEventManager}
 * @version 1.0 - Added support for cancellation
 * @version 1.1 - Added support for priorities
 */
@Singleton
public class ReflectiveEventManager extends AbstractEventManager {

	@Override
	public void dispatchEvent(Event event) {
		OrdinalMap<Map<Method, EventListener>> priorityMap = getRegistry().get(event.getClass());

		if (priorityMap != null) {
			CancellableEvent cancellableEvent = null;
			boolean cancellable;
			if (cancellable = event instanceof CancellableEvent) {
				cancellableEvent = (CancellableEvent) event;
				if (cancellableEvent.isCancelled()) return;
			}

			try {
				for (Entry<Integer, Map<Method, EventListener>> entry : priorityMap.entrySet()) {
					for (Entry<Method, EventListener> mapping : entry.getValue().entrySet()) {
						if (mapping.getKey().getAnnotation(EventHandler.class).priority().ordinal() == entry.getKey()) {
							mapping.getKey().invoke(mapping.getValue(), event);
							if (cancellable && cancellableEvent.isCancelled()) return;
						}
					}
				}
			} catch (InvocationTargetException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
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
					EventPriority priority = handler.priority();

					OrdinalMap<Map<Method, EventListener>> priorityMap = getRegistry().get(event);
					if (priorityMap == null) {
						priorityMap = new OrdinalMap<Map<Method, EventListener>>(EventPriority.class, (Map<Method, EventListener>) new HashMap<Method, EventListener>());
					}

					Map<Method, EventListener> methodMap = priorityMap.get(priority.ordinal());

					methodMap.put(method, listener);
					priorityMap.put(priority.ordinal(), methodMap);

					getRegistry().put(event, priorityMap);
				}
			}
		}
	}

}