package eod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Signals a field or parameter as being able to be <tt>null</tt>.
 * 
 * @author Thomas G. P. Nappo
 */
@Target({ ElementType.PARAMETER, ElementType.FIELD })
public @interface Nullable {}