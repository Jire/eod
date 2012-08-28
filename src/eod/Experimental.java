package eod;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Target;

/**
 * Signifies the annotated target to be subject to change,
 * and possibly removal, in a future release.
 * 
 * <p>Targets of this annotation are exempt from any
 * compatibility guarantees made by its containing library.</p>
 * 
 * @author Thomas G. P. Nappo
 */
@Target({ ANNOTATION_TYPE, CONSTRUCTOR, FIELD, METHOD, TYPE, PACKAGE })
public @interface Experimental {}