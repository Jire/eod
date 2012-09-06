package eod.test;

import eod.Experimental;

/**
 * 
 * @author Thomas G. P. Nappo
 */
public final class TestSuite {

	private final Class<?>[] classes;

	@Experimental
	protected TestSuite(Class<?>... classes) {
		this.classes = classes;
	}

	public Class<?>[] getClasses() {
		return classes.clone();
	}

	public static TestSuite create(Class<?>... classes) {
		return new TestSuite(classes);
	}

}