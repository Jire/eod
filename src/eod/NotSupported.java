package eod;

/**
 * Used to mark something to not support a type.
 * 
 * @author Thomas G. P. Nappo
 */
public @interface NotSupported {

	/**
	 * Retrieves the type's class that is
	 * designated as not being supported.
	 * 
	 * @return The unsupported type's class.
	 */
	Class<?> value();

}