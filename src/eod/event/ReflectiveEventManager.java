package eod.event;

import static eod.Preconditions.checkArgument;
import static eod.Preconditions.checkNotNull;
import static eod.predicate.Predicates.filter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import com.google.inject.Singleton;

import eod.predicate.Predicate;
import eod.predicate.Predicates;

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
 * @version 1.3 - Modified to use {@link Predicates}
 */
@Singleton
public final class ReflectiveEventManager extends AbstractEventManager {

	@Override
	public void dispatchEvent(Event event) {
		checkNotNull(event);
		CancellableEvent cancellableEvent = null;
		boolean cancellable;
		if (cancellable = event instanceof CancellableEvent) {
			cancellableEvent = (CancellableEvent) event;
			checkArgument(!cancellableEvent.isCancelled());
		}

		try {
			/*
			 * Iterate through the priorities in ascending order (based on ordinal)
			 */
			for (EventPriority priority : EventPriority.values()) {
				Map<Method, EventListener> internalMapping = getRegistry().getMethodMap(event.getClass(), priority);
				if (internalMapping != null) {
					for (Entry<Method, EventListener> entry : internalMapping.entrySet()) {
						entry.getKey().invoke(entry.getValue(), event);
						/*
						 * Immediately return in the case of the event being
						 * cancelled. Implementing the "ignoreCancelled" feature
						 * will allow greater flexibility.
						 * 
						 * Plan: Have prior iteration of "ignoreCancelled" handlers,
						 * with a separate registry.
						 */
						if (cancellable && cancellableEvent.isCancelled()) {
							return;
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void registerListener(EventListener listener) {
		checkNotNull(listener);

		for (Method method : filter(listener.getClass().getMethods(), isEventHandler)) {
			/*
			 * Unchecked cast suppressed because it is better to throw an
			 * exception, as it clearly signals something is wrong with the
			 * specified event handler.
			 */
			getRegistry().register((Class<? extends Event>) method.getParameterTypes()[0],
					method.getAnnotation(EventHandler.class).priority(), method, listener);
		}
	}

	/**
	 * A predicates which evaluates a method to verify it is a valid {@link EventHandler}.
	 */
	static final Predicate<Method> isEventHandler = new Predicate<Method>() {
		@Override
		public boolean evaluate(Method input) {
			return input.isAnnotationPresent(EventHandler.class) && input.getParameterTypes().length == 1;
		}
	};

}