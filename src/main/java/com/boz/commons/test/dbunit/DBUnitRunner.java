package com.boz.commons.test.dbunit;

import static com.boz.commons.test.dbunit.DBUnitTools.closePersistenceContext;
import static com.boz.commons.test.dbunit.DBUnitTools.injectEntityManager;
import static com.boz.commons.test.dbunit.DBUnitTools.insertDataSet;

import javax.persistence.EntityManager;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.javaboz.commons.test.ReflectionUtils;

/**
 * Runner JUnit pour utiliser DBUnit.
 *
 * @author jboz
 */
public class DBUnitRunner extends BlockJUnit4ClassRunner {

	private static EntityManager entityManager;

	public DBUnitRunner(final Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected Statement methodInvoker(final FrameworkMethod method, final Object testObject) {
		return new DBUnitStatement(method, testObject, super.methodInvoker(method, testObject));
	}

	/**
	 * Junit statement that inject EntityManager and start transaction (if required) before test and close EntityManager and
	 * commit/rollback transaction (if required) after test.
	 *
	 * @author jboz
	 */
	private static class DBUnitStatement extends Statement {

		private final FrameworkMethod method;
		private final Object testObject;
		private final Statement base;

		public DBUnitStatement(final FrameworkMethod method, final Object testObject, final Statement base) {
			super();
			this.method = method;
			this.testObject = testObject;
			this.base = base;
		}

		@Override
		public void evaluate() throws Throwable {
			aroundTest(base, method, testObject, true);
		}

	}

	/**
	 * Execute test with DBUnit environment.
	 *
	 * @param statement : test to execute
	 * @param method : test method
	 * @param testObject : tested object
	 * @throws Throwable
	 */
	public static void aroundTest(final Statement statement, final FrameworkMethod method, final Object testObject)
			throws Throwable {
		aroundTest(statement, method, testObject, false);
	}

	/**
	 * Execute test with DBUnit environment.
	 *
	 * @param statement : test to execute
	 * @param method : test method
	 * @param testObject : tested object
	 * @param forceDBUnit : force initialize DBUnit context
	 * @throws Throwable
	 */
	public static void aroundTest(final Statement statement, final FrameworkMethod method, final Object testObject,
			final boolean forceDBUnit) throws Throwable {
		// ne pas utiliser DBUnit si le test ne le demande pas
		final boolean dbUnitTest = forceDBUnit || isDBUnitTest(method, testObject);

		final Transactionnal transactionnal = ReflectionUtils.getMethodOrClassLevelAnnotation(Transactionnal.class,
				method.getMethod(), testObject.getClass());
		try {
			if (dbUnitTest) {
				// test start
				entityManager = injectEntityManager(testObject);
				insertDataSet(entityManager, method.getMethod(), testObject);

				if (transactionnal != null) {
					// lancement de la transaction
					entityManager.getTransaction().begin();
				}
			}
			statement.evaluate();

		} catch (final Exception e) {
			// rollback on exception
			if (dbUnitTest && entityManager != null && transactionnal != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		} finally {
			if (dbUnitTest) {
				if (entityManager != null && transactionnal != null && entityManager.getTransaction().isActive()) {
					if (transactionnal.rollback()) {
						entityManager.getTransaction().rollback();
					} else if (!entityManager.getTransaction().getRollbackOnly()) {
						entityManager.getTransaction().commit();
					}
				}
				// test end if not ignored
				closePersistenceContext(entityManager);
			}
		}
	}

	/**
	 * @return true si le test requiet DBUnit.
	 */
	private static boolean isDBUnitTest(final FrameworkMethod method, final Object testObject) {
		return testObject.getClass().getAnnotation(DataSet.class) != null || method.getMethod().getAnnotation(DataSet.class) != null
				|| method.getMethod().getAnnotation(Transactionnal.class) != null;
	}
}
