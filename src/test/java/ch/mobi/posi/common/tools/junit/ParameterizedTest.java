package ch.mobi.posi.common.tools.junit;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.runner.RunWith;

import ch.mobi.posi.common.tools.junit.Parameterized.Parameters;
import ch.mobi.posi.common.tools.junit.Parameterized.TestParameterized;
import ch.mobi.posi.common.tools.test.MyService;

/**
 * Test de la classe {@link Parameterized}.
 * 
 * @author jboz
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {

  private MyService service = new MyService();

  @Parameters
  public static Collection<Object[]> datas1() {
    final Collection<Object[]> datas = new ArrayList<Object[]>();

    final int one = 1;

    datas.add(new Object[] { "Paul", "Hello, Paul", one, new Object[] { "test", Double.valueOf(2.54689) } });
    datas.add(new Object[] { "Jack", "Hello, Jack", 2, new String[] {} });
    datas.add(new Object[] { "my name is Patty  !", "Hello, my name is Patty  !", 5, null });

    return datas;
  }

  @TestParameterized
  public void testGreet(final String name, final String expected, final int cpt, final Object[] vals) {
    System.out.println(cpt + ", " + ToStringBuilder.reflectionToString(vals, ToStringStyle.SHORT_PREFIX_STYLE));
    assertThat(service.greet(name)).isEqualTo(expected);
  }
}
