package ch.mobi.posi.common.tools.dbunit;

import org.junit.Rule;

/**
 * Classe de base d'un test DBUnit.
 * 
 * @author jboz
 */
public abstract class AbstractDBUnitTest {

  @Rule
  public DBUnitRule dbUnitRule = DBUnitRule.init();

}
