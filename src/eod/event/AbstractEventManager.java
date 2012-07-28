package eod.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import eod.OrdinalMap;

/**
 * An abstract implementation of {@link EventManager}
 * which simply provides a registry for the event system.
 * 
 * @author Thomas G. P. Nappo
 */
public abstract class AbstractEventManager implements EventManager {

	/**
	 * The registry of the event manager, used to map event
	 * listeners to the event handlers that they contain.
	 */
	private final Map<Class<? extends Event>, OrdinalMap<Map<Method, EventListener>>> registry;

	/**
	 * Constructs an event manager, requiring an already-existing registry.
	 * 
	 * @param registry The registry of the event manager, used to
	 * map event listeners to the event handlers that they contain.
	 */
	public AbstractEventManager(Map<Class<? extends Event>, OrdinalMap<Map<Method, EventListener>>> registry) {
		this.registry = registry;
	}

	/**
	 * Constructs an event manager, suppling a new {@link HashMap} as the registry.
	 */
	public AbstractEventManager() {
		this(new HashMap<Class<? extends Event>, OrdinalMap<Map<Method, EventListener>>>());
	}

	/**
	 * Retrieves the <tt>registry</tt> of the event manager, used
	 * to map event listeners to the event handlers that they contain.
	 * 
	 * @return The event manager's {@link #registry}.
	 */
	protected Map<Class<? extends Event>, OrdinalMap<Map<Method, EventListener>>> getRegistry() {
		return registry;
	}

}