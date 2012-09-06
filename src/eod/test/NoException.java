package eod.test;

/**
 * 
 * @author Thomas G. P. Nappo
 */
public final class NoException extends Throwable {

	private static final long serialVersionUID = -3834810670660624824L;

	private NoException() {
		throw new UnsupportedOperationException();
	}

}