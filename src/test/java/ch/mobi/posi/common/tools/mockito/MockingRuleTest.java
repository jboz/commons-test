package ch.mobi.posi.common.tools.mockito;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import ch.mobi.posi.common.tools.test.MyService;

/**
 * Test de la classe {@link MockingRule}.
 * 
 * @author jboz
 */
public class MockingRuleTest {

  @Rule
  public MockingRule rule = MockingRule.init();

  @Mock
  private MyService mock;

  @Spy
  private MyService spy;

  @Test
  public void testRule() {
    assertThat(MockingRule.init()).isNotNull();

    assertThat(mock).isNotNull();
    assertThat(spy).isNotNull();
  }
}
