package eod.test;

import java.lang.reflect.Method;

public final class TestCase {

	private final Method method;
	private final Class<? extends Throwable> exception;
	private final int timeout;

	private TestCase(Method method, Class<? extends Throwable> exception, int timeout) {
		this.method = method;
		this.exception = exception;
		this.timeout = timeout;
	}

	public Method getMethod() {
		return method;
	}

	public Class<? extends Throwable> getException() {
		return exception;
	}

	public int getTimeout() {
		return timeout;
	}

	public static TestCase create(Method method, Class<? extends Throwable> exception, int timeout) {
		return new TestCase(method, exception, timeout);
	}

	public static TestCase create(Method method, Test test) {
		return create(method, test.expected(), test.timeout());
	}

	public static TestCase create(Method method) {
		return create(method, method.getAnnotation(Test.class));
	}

}