package eod;

/**
 * Used to supply an object of a single type.
 * 
 * @author Thomas G. P. Nappo
 *
 * @param <T> The type of the supplied object.
 */
public interface Supplier<T> {

	/**
	 * Retrieves an instance of the appropriate type.
	 * The returned object may or may not be a new
	 * instance, depending on the implementation.
	 * @return An instance of the appropriate type.
	 */
	public T get();

}