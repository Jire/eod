package eod.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to encapsulate events, being mapped to their
 * appropriate listeners and methods.
 * 
 * @author Thomas G. P. Nappo
 * @version 1.1 - Removed diamond operator dependency
 */
public class EventRegistry {

	private final Map<String, Map<Method, EventListener>> map = new HashMap<String, Map<Method, EventListener>>();

	public synchronized void register(Class<? extends Event> event, EventPriority priority, Method method, EventListener listener) {
		String keyName = getKeyName(event, priority);
		Map<Method, EventListener> methodMap = map.get(keyName);
		if (methodMap == null) {
			map.put(keyName, methodMap = new HashMap<Method, EventListener>());
		}
		methodMap.put(method, listener);
		map.put(keyName, methodMap);
	}

	public synchronized Map<Method, EventListener> getMethodMap(Class<? extends Event> event, EventPriority priority) {
		return map.get(getKeyName(event, priority));
	}

	protected String getKeyName(Class<? extends Event> event, EventPriority priority) {
		return event.getName() + "-" + priority.name();
	}

}