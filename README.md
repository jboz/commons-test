  commons-test
============

Utilitaires for testing

### Wiki page of this test tools.
This project give some lite tools for test unit writing.
It's base on framework [JUnit](http://www.junit.org/), [DBUnit](http://www.dbunit.org/) and [Mockito](http://code.google.com/p/mockito/).


If you're using the GitHub for Mac, simply sync your repository and you'll see the new branch.


### JUnit features :
```
@RunWith(Parameterized.class)
public class ParameterizedTest {

  private final MyService service = new MyService();

  @Parameters
  public static Collection<Object[]> datas1() {
    final Collection<Object[]> datas = new ArrayList<Object[]>();

    datas.add(new Object[] { "Paul", "Hello, Paul", 1, new Object[] { "test", Double.valueOf(2.54689) } });
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
```


### Mockito features :
**Based on JUnit rule**
```
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
```


### DBUnit features :
Load datas with **@DataSet**, automaticly inject EntityManager if defined int class :
```
@DataSet("entities")
public class DBUnitRuleTest {

  private EntityManager em;

  @Test
  @DataSet("datas/others")
  public void testLoadOthers() {
    assertThat(em).isNotNull();
    final MyEntity entity = em.find(MyEntity.class, 20l);
    assertEquals(Long.valueOf(20), entity.getId());
    assertEquals("Pierre", entity.getName());
  }
}
```

**@DataSet** can be defined for the entire class and/or for a specific method.

To activate DBUnit, 3 ways
**a JUnit rule :**
```
  @Rule
  public DBUnitRule dbUnitRule = DBUnitRule.init();
```

**an abstract class :**
```
public class AbstractDBUnitTestTest extends AbstractDBUnitTest {
```

**a runner :**
```
@RunWith(DBUnitRunner.class)
public class DBUnitRunnerTest {
```

You can define the rollback strategy with @Transactionnal.
You can redefined JPA config with @JpaConfig (by default the unit persistence name is 'pu-test').
_note that you can not even change the persistence.xml file name, but soon I hope :)_

### Support or Contact
Create an issue on Guthub, or a pull request ;)
