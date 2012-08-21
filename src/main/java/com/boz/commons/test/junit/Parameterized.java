package com.boz.commons.test.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.Runner;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaboz.commons.test.DateUtils;


/**
 * <p>
 * The custom runner <code>Parameterized</code> implements parameterized tests. When running a parameterized test class, instances
 * are created for the cross-product of the test methods and the test data elements.<br>
 * This mix JUnit {@link org.junit.runners.Parameterized} and {@link Theories}.
 * </p>
 * For example, to test a Fibonacci function, write:
 *
 * <pre>
 * &#064;RunWith(Parameterized.class)
 * public class FibonacciTest {
 *   &#064;Parameters
 *   public static List&lt;Object[]&gt; data() {
 *     return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 } });
 *   }
 *
 *   &#064;Theory
 *   public void test(int fInput, int fExpected) {
 *     assertEquals(fExpected, Fibonacci.compute(fInput));
 *   }
 * }
 * </pre>
 */
public class Parameterized extends Suite {
  private static final Logger LOG = LoggerFactory.getLogger(Parameterized.class);

  private final ArrayList<Runner> runners = new ArrayList<Runner>();

  /**
   * Only called reflectively. Do not use programmatically.
   */
  public Parameterized(final Class<?> klass) throws Throwable {
    super(klass, Collections.<Runner> emptyList());
    final List<Object[]> parametersList = getParametersList(getTestClass());

    for (int i = 0; i < parametersList.size(); i++) {
      // on crée un runner pour chaque paramètre
      // ces runner n'exécuteront que les méthodes paramétrées
      runners.add(new TestClassRunnerWithParameters(klass, parametersList.get(i), i));
    }
    if (!CollectionUtils.isEmpty(new TestClass(klass).getAnnotatedMethods(Test.class))) {
      // on ajoute un runner classique pour les autres méthodes de test
      runners.add(new BlockJUnit4ClassRunner(klass) {

        @Override
        protected String getName() {
          return getTestClass().getJavaClass().getSimpleName();
        }

        @Override
        protected String testName(final FrameworkMethod method) {
          return method.getName();
        }
      });
    }
  }

  @Override
  protected List<Runner> getChildren() {
    return runners;
  }

  @SuppressWarnings("unchecked")
  private List<Object[]> getParametersList(final TestClass klass) throws Throwable {
    return (List<Object[]>) getParametersMethod(klass).invokeExplosively(null);
  }

  private FrameworkMethod getParametersMethod(final TestClass testClass) throws Exception {
    final List<FrameworkMethod> methods = testClass.getAnnotatedMethods(Parameters.class);
    for (final FrameworkMethod each : methods) {
      final int modifiers = each.getMethod().getModifiers();
      if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
				return each;
			}
    }

    throw new Exception("No public static parameters method on class " + testClass.getName());
  }

  /**
   * Annotation for a method which provides parameters to be injected into the test class constructor by
   * <code>Parameterized</code>
   */
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public static @interface Parameters {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public static @interface TestParameterized {
  }

  private class TestClassRunnerWithParameters extends BlockJUnit4ClassRunner {

    private final int fParameterSetNumber;

    private final Object[] fParameterList;

    private TestClassRunnerWithParameters(final Class<?> type, final Object[] parameterList, final int i) throws InitializationError {
      super(type);
      fParameterList = parameterList;
      fParameterSetNumber = i;
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
      // exécute uniquement les méthodes paramétrées
      return getTestClass().getAnnotatedMethods(TestParameterized.class);
    }

    @Override
    protected void validateTestMethods(final List<Throwable> errors) {
      for (final FrameworkMethod each : computeTestMethods()) {
				if (each.getAnnotation(TestParameterized.class) != null) {
					each.validatePublicVoid(false, errors);
				} else {
					each.validatePublicVoidNoArg(false, errors);
				}
			}
    }

    @Override
    protected String getName() {
      return String.format("[%s] - %.300s", fParameterSetNumber, prettyPrint(fParameterList));
    }

    @Override
    protected String testName(final FrameworkMethod method) {
      return String.format("%s[%s]", method.getName(), fParameterSetNumber);
    }

    @Override
    protected Statement methodInvoker(final FrameworkMethod method, final Object test) {
      return methodCompletesWithParameters(method, test, fParameterList);
    }

    private Statement methodCompletesWithParameters(final FrameworkMethod method, final Object freshInstance,
        final Object... values) {
      return new Statement() {
        @Override
        public void evaluate() throws Throwable {
          // exécution de la méthode avec paramètres
          if (LOG.isTraceEnabled()) {
            LOG.debug(" test no=" + fParameterSetNumber + ", values : " + ArrayUtils.toString(values));
          } else {
            LOG.debug(" test no=" + fParameterSetNumber + ", values : " + prettyPrint(values));
          }
          method.invokeExplosively(freshInstance, values);
        }
      };
    }
  }

  /**
   * Prepare <code>params</code> to be print.
   */
  private static String prettyPrint(final Object[] params) {
    final StringBuilder builder = new StringBuilder();
    builder.append("{");
    if (params != null) {
      for (int i = 0; i < params.length; i++) {
        final Object param = params[i];
        if (i > 0) {
          builder.append(", ");
        }

        if (param == null) {
          builder.append("null");
        } else if (param instanceof String) {
          builder.append("\"").append(param.toString()).append("\"");
        } else if (param instanceof Date) {
          builder.append(DateUtils.format((Date) param));
        } else if (param instanceof Number) {
          builder.append(param.toString());
        } else if (param instanceof Object[]) {
          builder.append(prettyPrint((Object[]) param));
        } else {
          builder.append(param.getClass().getSimpleName()).append("@").append(Integer.toHexString(param.hashCode()));
        }
      }
    }

    builder.append("}");
    return builder.toString();
  }
}
