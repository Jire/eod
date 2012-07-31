package eod;

/**
 * Simple static utility methods to verify
 * proper state of method arguments.
 * 
 * @author Thomas G. P. Nappo
 */
public final class Preconditions {

	/**
	 * Ensures that an object reference passed as a
	 * parameter to the calling method is not <tt>null</tt>.
	 * 
	 * @param reference The object reference to perform validation.
	 * @return The non-null reference that was validated
	 * @throws NullPointerException If the reference is <tt>null</tt>.
	 */
	public static <T> T checkNotNull(T reference) {
		if (reference == null) {
			throw new NullPointerException();
		}
		return reference;
	}

	/**
	 * Ensures the truth of an expression involving
	 * one or more parameters to the calling method.
	 * 
	 * @param expression The representative expression, as a <tt>boolean</tt>.
	 * @throws IllegalArgumentException If the expression is <tt>false</tt>.
	 */
	public static void checkArgument(boolean expression) {
		if (!expression) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Preconditions is a static-method-only class
	 * and should therefore never be constructed.
	 * 
	 * @throws UnsupportedOperationException If construction occurs.
	 */
	private Preconditions() {
		throw new UnsupportedOperationException("Preconditions should not be constructed!");
	}

}