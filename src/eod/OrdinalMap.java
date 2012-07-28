package eod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A {@link Map} which uses the ordinals of
 * {@link Enum} entry constants to populate
 * an internal map, in ascending order.
 * 
 * @author Thomas G. P. Nappo
 *
 * @param <V> The type of mapped values.
 */
public class OrdinalMap<V> implements Map<Integer, V> {

	private final Map<Integer, V> map;

	public OrdinalMap(Class<? extends Enum<?>> valueType, V virginValue) {
		map = new HashMap<Integer, V>();
		Enum<?>[] enums = valueType.getEnumConstants();
		for (int i = 0; i < enums.length; i++) {
			put(enums[i].ordinal(), virginValue);
		}
	}

	public OrdinalMap(Class<? extends Enum<?>> valueType) {
		this(valueType, null);
	}

	@Override
	public V put(Integer key, V value) {
		return map.put(key, value);
	}

	@Override
	public V get(Object o) {
		return map.get(o);
	}

	@Override
	public Set<Entry<Integer, V>> entrySet() {
		return map.entrySet();
	}

	@Override
	public Set<Integer> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<V> values() {
		return map.values();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public V remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends V> m) {
		map.putAll(m);
	}

	@Override
	public void clear() {
		map.clear();
	}

}