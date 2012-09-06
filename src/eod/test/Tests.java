package eod.test;

import static eod.predicate.Predicates.filter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import eod.predicate.Predicate;

/**
 * 
 * @author Thomas G. P. Nappo
 */
public final class Tests {

	private static final Tester tester = new Tester() {
		@Override
		public boolean test(TestCase testCase) {
			Method method = testCase.getMethod();
			Class<?> declaringClass = method.getDeclaringClass();
			String methodSignature = declaringClass.getName() + ":" + method.getName();
			try {
				return method.invoke(declaringClass.newInstance()) == null;
			} catch (InvocationTargetException e) {
				if (e.getTargetException().getClass().getName().equals(testCase.getException().getName())) {
					return true;
				}
			} catch (IllegalAccessException e) {
				System.out.println("The testable method is inaccessible: " + methodSignature);
			} catch (IllegalArgumentException e) {
				System.out.println("The testable method has arguments and is therefore untestable: " 
						+ methodSignature);
			} catch (InstantiationException e) {
				System.out.println("The testable method's declaring class could not be instantiated: "
						+ methodSignature);
			}
			return false;
		}
	};

	private static final Predicate<Method> isTestMethod = new Predicate<Method>() {
		@Override
		public boolean evaluate(Method input) {
			return input.isAnnotationPresent(Test.class);
		}
	};

	public static void testClasses(Class<?>... classes) {
		int failed = 0;
		int passed = 0;
		for (Class<?> testClass : classes) {
			for (Method method : filter(testClass.getMethods(), isTestMethod)) {
				if (!tester.test(TestCase.create(method))) {
					System.out.println("Test failed: " + testClass.getName() + ":" + method.getName());
					failed++;
				} else {
					System.out.println("Test passed: " + testClass.getName() + ":" + method.getName());
					passed++;
				}
			}
		}
		int total = failed + passed;
		System.out.println(passed + "/" + total + " tests passed, " + failed + " failed.");
	}

	public static void testSuite(TestSuite suite) {
		testClasses(suite.getClasses());
	}

	/**
	 * Tests is a static-utility class
	 * and should therefore never be constructed.
	 * 
	 * @throws UnsupportedOperationException If construction occurs.
	 */
	private Tests() {
		throw new UnsupportedOperationException("Tests should not be constructed!");
	}

}