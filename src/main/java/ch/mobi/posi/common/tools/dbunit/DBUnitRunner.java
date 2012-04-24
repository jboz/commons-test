package ch.mobi.posi.common.tools.dbunit;

import static ch.mobi.posi.common.tools.dbunit.DBUnitTools.closePersistenceContext;
import static ch.mobi.posi.common.tools.dbunit.DBUnitTools.injectEntityManager;
import static ch.mobi.posi.common.tools.dbunit.DBUnitTools.insertDataSet;

import javax.persistence.EntityManager;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import ch.mobi.posi.common.tools.ReflectionUtils;

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

    final Statement base = super.methodInvoker(method, testObject);

    return new Statement() {

      @Override
      public void evaluate() throws Throwable {
        final Transactionnal transactionnal = ReflectionUtils.getMethodOrClassLevelAnnotation(Transactionnal.class,
            method.getMethod(), testObject.getClass());
        try {
          // test start
          entityManager = injectEntityManager(testObject);
          insertDataSet(entityManager, method.getMethod(), testObject);

          if (transactionnal != null) {
            // lancement de la transaction
            entityManager.getTransaction().begin();
          }

          base.evaluate();

        } finally {
          if (entityManager != null && transactionnal != null && entityManager.getTransaction().isActive()) {
            if (transactionnal.rollback()) {
              entityManager.getTransaction().rollback();
            } else {
              entityManager.getTransaction().commit();
            }
          }
          // test end if not ignored
          closePersistenceContext(entityManager);
        }
      }
    };
  }
}
