package ch.mobi.posi.common.tools.dbunit;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

import org.junit.Test;
import org.junit.runner.RunWith;

import ch.mobi.posi.common.tools.test.MyEntity;

@DataSet("entities")
@RunWith(DBUnitRunner.class)
public class DBUnitRunnerTest {

  private EntityManager em;

  @Test
  public void testLoad() {
    assertThat(em).isNotNull();
    final MyEntity entity = em.find(MyEntity.class, 1l);
    assertEquals(Long.valueOf(1), entity.getId());
    assertEquals("Hervé", entity.getName());
  }

  @Test
  @DataSet("datas/others")
  public void testLoadOthers() {
    assertThat(em).isNotNull();
    final MyEntity entity = em.find(MyEntity.class, 20l);
    assertThat(entity.getId()).isEqualTo(20);
    assertEquals("Pierre", entity.getName());
  }

  @Test
  @DataSet({ "datas/others", "entities" })
  public void testMultipleDataSet() {
    assertThat(em).isNotNull();

    assertThat(em.find(MyEntity.class, 1l)).isNotNull();
    assertThat(em.find(MyEntity.class, 20l)).isNotNull();
  }

  @Test
  @DataSet("datas/others")
  public void testReplacementDataSet() {
    final MyEntity entity = em.find(MyEntity.class, 20l);

    assertThat(entity.getId()).isEqualTo(20);
    assertThat(entity.getName()).isEqualTo("Pierre");
    assertThat(entity.getAmount()).isNull();

    assertThat(entity.getBirthDay()).isNotNull();
    assertThat(get(Calendar.YEAR, entity.getBirthDay())).isNotEqualTo(0);
    assertThat(get(Calendar.MONTH, entity.getBirthDay())).isNotEqualTo(0);
    assertThat(get(Calendar.DAY_OF_MONTH, entity.getBirthDay())).isNotEqualTo(0);
    assertThat(get(Calendar.HOUR_OF_DAY, entity.getBirthDay())).isEqualTo(0);
    assertThat(get(Calendar.MINUTE, entity.getBirthDay())).isEqualTo(0);
    assertThat(get(Calendar.SECOND, entity.getBirthDay())).isEqualTo(0);
    assertThat(get(Calendar.MILLISECOND, entity.getBirthDay())).isEqualTo(0);

    assertThat(entity.getTimeToMarket()).isNotNull();
    assertThat(get(Calendar.YEAR, entity.getTimeToMarket())).isEqualTo(1970);
    assertThat(get(Calendar.MONTH, entity.getTimeToMarket())).isEqualTo(Calendar.JANUARY);
    assertThat(get(Calendar.DAY_OF_MONTH, entity.getTimeToMarket())).isEqualTo(1);
    assertThat(get(Calendar.HOUR_OF_DAY, entity.getTimeToMarket())).isNotEqualTo(0);
    assertThat(get(Calendar.MINUTE, entity.getTimeToMarket())).isNotEqualTo(0);
    // assertThat(get(Calendar.SECOND, entity.getTimeToMarket())).isNotEqualTo(0);
    // assertThat(get(Calendar.MILLISECOND, entity.getTimeToMarket())).isNotEqualTo(0);

    assertThat(entity.getCreatedOn()).isNotNull();
    assertThat(get(Calendar.YEAR, entity.getCreatedOn())).isNotEqualTo(0);
    assertThat(get(Calendar.MONTH, entity.getCreatedOn())).isNotEqualTo(0);
    assertThat(get(Calendar.DAY_OF_MONTH, entity.getCreatedOn())).isNotEqualTo(0);
    assertThat(get(Calendar.HOUR_OF_DAY, entity.getCreatedOn())).isNotEqualTo(0);
    assertThat(get(Calendar.MINUTE, entity.getCreatedOn())).isNotEqualTo(0);
    // assertThat(get(Calendar.SECOND, entity.getCreatedOn())).isNotEqualTo(0);
    // assertThat(get(Calendar.MILLISECOND, entity.getCreatedOn())).isNotEqualTo(0);

  }

  private static int get(int field, Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    return calendar.get(field);
  }

  @Test(expected = TransactionRequiredException.class)
  public void test_noTransactionnal() {
    assertThat(em).isNotNull();
    MyEntity entity = new MyEntity();
    em.persist(entity);
    em.flush();
  }

  @Test
  @Transactionnal
  public void testTransactionnal_OK() {
    assertThat(em).isNotNull();
    MyEntity entity = new MyEntity();
    entity.setName("a new One");

    em.persist(entity);
    em.flush();

    @SuppressWarnings("unchecked")
    final List<MyEntity> entities = em.createNativeQuery("select * from MyEntity order by id asc", MyEntity.class)
        .getResultList();
    // maintenant 3 entités en base
    assertThat(entities).hasSize(4);
    assertThat(entities.get(0).getName()).isEqualTo("Hervé");
    assertThat(entities.get(1).getName()).isEqualTo("Jimi");
    assertThat(entities.get(2).getName()).isEqualTo("Pierre");
    assertThat(entities.get(3).getName()).isEqualTo("a new One");
  }
}
