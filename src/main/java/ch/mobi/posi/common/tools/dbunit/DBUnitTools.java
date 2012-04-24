package ch.mobi.posi.common.tools.dbunit;

import static ch.mobi.posi.common.tools.ReflectionUtils.getFieldAnnotatedWith;
import static ch.mobi.posi.common.tools.ReflectionUtils.getFieldByType;
import static ch.mobi.posi.common.tools.ReflectionUtils.getFieldValue;
import static ch.mobi.posi.common.tools.ReflectionUtils.getMethodOrClassLevelAnnotation;
import static ch.mobi.posi.common.tools.ReflectionUtils.setFieldValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Séparation des méthodes utilitaires.
 * 
 * @author jboz
 */
public final class DBUnitTools {
  private static final Logger LOG = LoggerFactory.getLogger(DBUnitTools.class);

  /**
   * Injection de {@link EntityManager}.
   * 
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public static EntityManager injectEntityManager(final Object testObject) {
    Field field = getFieldByType(testObject.getClass(), EntityManager.class);
    if (field == null) {
      field = getFieldAnnotatedWith(testObject.getClass(), ToInject.class);
    }

    if (field != null) {
      final EntityManager entityManager = createFactory(testObject).createEntityManager();

      if (EntityManager.class.equals(field.getType())) {
        // EntityManager directement injecté
        setFieldValue(testObject, field, entityManager);
      } else {
        // on est dans le cas d'une dépendance
        final Field subField = getFieldByType(field.getType(), EntityManager.class);
        if (subField != null) {
          Object dependance = getFieldValue(testObject, field);
          if (dependance == null) {
            try {
              // on instancie la dépendance si null
              dependance = field.getType().newInstance();
              setFieldValue(testObject, field, dependance);

            } catch (final Exception e) {
              new IllegalArgumentException(e);
            }
          }
          // on attribue un EntityManager Ã  cette dépendance
          setFieldValue(dependance, subField, entityManager);
        }
      }
      return entityManager;
    }
    return null;
  }

  /**
   * Création du context JPA.
   */
  private static EntityManagerFactory createFactory(final Object testObject) {
    final JpaConfig jpaConfig = testObject.getClass().getAnnotation(JpaConfig.class);

    return Persistence.createEntityManagerFactory(jpaConfig == null ? "pu-test" : jpaConfig.persistenceUnit());
  }

  /**
   * Création du data set.
   */
  public static IDataSet getDataSet(final Method testMethod, final Object testObject) throws MalformedURLException,
      DataSetException {
    final Class<?> testClass = testObject.getClass();
    final DataSet dataSetAnnotation = getMethodOrClassLevelAnnotation(DataSet.class, testMethod, testClass);
    if (dataSetAnnotation == null) {
      // No @DataSet annotation found
      return null;
    }
    final List<IDataSet> dataSets = new ArrayList<IDataSet>();

    final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder().setDtdMetadata(false).setColumnSensing(true);
    for (final String dataSetName : dataSetAnnotation.value()) {
      dataSets.add(builder.build(DataSetResolver.resolve(testClass, dataSetName)));
    }

    final ReplacementDataSet replacementDataSet = new ReplacementDataSet(new CompositeDataSet(
        (IDataSet[]) dataSets.toArray(new IDataSet[dataSets.size()])));

    replacementDataSet.addReplacementObject("[NULL]", null);
    replacementDataSet.addReplacementObject("[NOW]", Calendar.getInstance().getTime());

    replacementDataSet.setStrictReplacement(true);

    return replacementDataSet;
  }

  /**
   * Insertion du jeu de test.
   */
  private static void insertDataSet(final IDatabaseConnection connection, final IDataSet dataSet) throws SQLException,
      DatabaseUnitException {
    DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
  }

  /**
   * Création d'une datasource basé sur les propriétés JPA.
   */
  private static DataSource createDataSource(final EntityManagerFactory factory) {
    final String driverClassName = (String) factory.getProperties().get("hibernate.connection.driver_class");
    final String databaseUrl = (String) factory.getProperties().get("hibernate.connection.url");
    final String userName = (String) factory.getProperties().get("hibernate.connection.username");
    final String password = (String) factory.getProperties().get("hibernate.connection.password");

    LOG.info("Creating data source. Driver: " + driverClassName + ", url: " + databaseUrl + ", user: " + userName
        + ", password: <not shown>");
    final BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUsername(userName);
    dataSource.setPassword(password);
    dataSource.setUrl(databaseUrl);

    return dataSource;
  }

  /**
   * Insertion du jeu de test.
   */
  public static void insertDataSet(final EntityManager entityManager, final Method testMethod, final Object testObject) {
    IDatabaseConnection connection = null;
    try {
      final IDataSet dataSet = getDataSet(testMethod, testObject);
      if (dataSet == null) {
        // no dataset specified
        return;
      }
      connection = new DatabaseConnection(createDataSource(entityManager.getEntityManagerFactory()).getConnection());
      connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new DefaultDataTypeFactory() {
        @Override
        public Collection<String> getValidDbProducts() {
          return Arrays.asList(new String[] { "hsql", "oracle", "db2", "h2" });
        }
      });
      insertDataSet(connection, dataSet);

    } catch (final Exception e) {
      throw new IllegalArgumentException("Error inserting test data from DbUnit dataset for method " + testMethod, e);
    } finally {
      // ferme la connection Ã  la base.
      if (connection != null) {
        try {
          connection.close();
        } catch (final SQLException e) {
        }
      }
    }
  }

  /**
   * Ferme l'accÃ¨s aux données.
   */
  public static void closePersistenceContext(EntityManager entityManager) {
    if (entityManager != null) {
      // ferme via la factory
      entityManager.getEntityManagerFactory().close();
    }
  }
}
