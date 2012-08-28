package eod;

/**
 * An immutable object that may contain a non-null
 * reference to another object. Each instance of this
 * type either contains a non-null reference, or contains
 * nothing (in which case we say that the reference is "absent");
 * it is never said to <i>"contain <tt>null</tt>"</i>.
 * 
 * @author Thomas G. P. Nappo
 *
 * @param <T> The type of the reference.
 */
public final class Optional<T> implements Supplier<T> {

	private final T reference;

	private Optional(T reference) {
		this.reference = reference;
	}

	@Override
	public T get() {
		if (!isPresent()) {
			throw new IllegalStateException("Cannot retrieve reference when absent!");
		}
		return reference;
	}

	public boolean isPresent() {
		return reference != null;
	}

	public static <T> Optional<T> of(T reference) {
		return new Optional<T>(reference);
	}

	public static <T> Optional<T> absent() {
		return of(null);
	}

	public static <T> Optional<T> fromNullable(@Nullable T nullableReference) {
		return of(nullableReference);
	}

}