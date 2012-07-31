package eod.predicate;

/**
 * A functional interface which is used to define a
 * filtering condition for objects by which they evaluate.
 * 
 * <p>Implementations which modify state are discouraged.</p>
 * 
 * @author Thomas G. P. Nappo
 * @param <T> The type of input that the predicate evaluates.
 */
public interface Predicate<T> {

	/**
	 * Checks whether the input value lies
	 * between the specified filtering criterion.
	 * 
	 * @param input The input that the predicate should act on.
	 * @return Whether or not the input value meets the filtering criterion.
	 */
	public boolean evaluate(T input);

}