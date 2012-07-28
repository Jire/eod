package eod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Used to mark an element as not implemented.
 * 
 * <p>Elements which are tagged as <i>not implemented</i>
 * typically serve as a specification for a feature which
 * will be implemented in a later release.</p>
 * 
 * @author Thomas G. P. Nappo
 */
@Retention(RetentionPolicy.SOURCE)
public @interface NotImplemented {}