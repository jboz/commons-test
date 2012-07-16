package com.boz.commons.test;

import static org.fest.assertions.Assertions.assertThat;

import javax.management.DescriptorKey;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.experimental.theories.DataPoint;
import org.junit.rules.ExpectedException;
import org.mockito.Captor;

import com.boz.commons.test.helpers.MyEntity;
import com.boz.commons.test.helpers.MyService;

/**
 * Test de la classe {@link ReflectionUtils}.
 *
 * @author jboz
 */
public class ReflectionUtilsTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Deprecated
	private static class AbstractAnnotedClass {

		@Rule
		public final String provider = "PROVIDED";

		@Ignore
		public void testAbstractAnnoted() {
		}
	}

	@DataPoint
	private static class AnnotedClass extends AbstractAnnotedClass {

		@Captor
		public MyService service;

		@DescriptorKey("test")
		public void testAnnoted() {
		}
	}

	@Test
	public void testGetMethodOrClassLevelAnnotation() {
		assertThat(
				ReflectionUtils.getMethodOrClassLevelAnnotation(DescriptorKey.class, AnnotedClass.class.getMethods()[0],
						AnnotedClass.class)).isInstanceOf(DescriptorKey.class);

		assertThat(
				ReflectionUtils.getMethodOrClassLevelAnnotation(Deprecated.class, AnnotedClass.class.getMethods()[0], AnnotedClass.class))
				.isInstanceOf(Deprecated.class);

		assertThat(
				ReflectionUtils.getMethodOrClassLevelAnnotation(DataPoint.class, AnnotedClass.class.getMethods()[0], AnnotedClass.class))
				.isInstanceOf(DataPoint.class);

		assertThat(
				ReflectionUtils.getMethodOrClassLevelAnnotation(Ignore.class, AnnotedClass.class.getMethods()[0], AnnotedClass.class))
				.isNull();

		assertThat(
				ReflectionUtils.getMethodOrClassLevelAnnotation(Captor.class, AnnotedClass.class.getMethods()[0], AnnotedClass.class))
				.isNull();

		assertThat(
				ReflectionUtils.getMethodOrClassLevelAnnotation(IncludeCategory.class, AnnotedClass.class.getMethods()[0],
						AnnotedClass.class)).isNull();
	}

	@Test
	public void testGetClassLevelAnnotation() {
		assertThat(ReflectionUtils.getClassLevelAnnotation(DataPoint.class, AnnotedClass.class)).isInstanceOf(DataPoint.class);
		assertThat(ReflectionUtils.getClassLevelAnnotation(Deprecated.class, AnnotedClass.class)).isInstanceOf(Deprecated.class);
		assertThat(ReflectionUtils.getClassLevelAnnotation(IncludeCategory.class, AnnotedClass.class)).isNull();
	}

	@Test
	public void testGetFieldAnnotatedWith() {
		assertThat(ReflectionUtils.getFieldAnnotatedWith(Object.class, Captor.class)).isNull();
		assertThat(ReflectionUtils.getFieldAnnotatedWith(AnnotedClass.class, Captor.class)).isNotNull();
		assertThat(ReflectionUtils.getFieldAnnotatedWith(AnnotedClass.class, Captor.class).getName()).isEqualTo("service");
		assertThat(ReflectionUtils.getFieldAnnotatedWith(AnnotedClass.class, Rule.class).getName()).isEqualTo("provider");
	}

	@Test
	public void testGetFieldByType() {
		assertThat(ReflectionUtils.getFieldByType(Object.class, MyService.class)).isNull();
		assertThat(ReflectionUtils.getFieldByType(AnnotedClass.class, MyService.class)).isNotNull();
		assertThat(ReflectionUtils.getFieldByType(AnnotedClass.class, MyService.class).getName()).isEqualTo("service");
		assertThat(ReflectionUtils.getFieldByType(AnnotedClass.class, String.class).getName()).isEqualTo("provider");
	}

	@Test
	public void testGetFieldByName() {
		assertThat(ReflectionUtils.getFieldByName(Object.class, "service")).isNull();
		assertThat(ReflectionUtils.getFieldByName(AnnotedClass.class, "service").getName()).isEqualTo("service");
		assertThat(ReflectionUtils.getFieldByName(AnnotedClass.class, "provider").getName()).isEqualTo("provider");
	}

	@Test
	public void testSetFieldValueObjectClassOfTObject() {
		final AnnotedClass annotedClass = new AnnotedClass();
		assertThat(annotedClass.service).isNull();
		ReflectionUtils.setFieldValue(annotedClass, MyService.class, new MyService());
		assertThat(annotedClass.service).isNotNull();
	}

	@Test
	public void testSetFieldValueObjectStringObject() {
		final AnnotedClass annotedClass = new AnnotedClass();
		assertThat(annotedClass.service).isNull();
		ReflectionUtils.setFieldValue(annotedClass, AnnotedClass.class.getFields()[0], new MyService());
		assertThat(annotedClass.service).isNotNull();
	}

	@Test
	public void testSetFieldValueObjectFieldObject() {
		final AnnotedClass annotedClass = new AnnotedClass();
		assertThat(annotedClass.service).isNull();
		ReflectionUtils.setFieldValue(annotedClass, "service", new MyService());
		assertThat(annotedClass.service).isNotNull();
		// pas d'erreur même si le champs est final
		ReflectionUtils.setFieldValue(new AnnotedClass(), "provider", "otherValue");
	}

	@Test
	public void testGetFieldValue() {
		final AnnotedClass annotedClass = new AnnotedClass();
		assertThat(ReflectionUtils.getFieldValue(annotedClass, AnnotedClass.class.getFields()[0])).isNull();
		annotedClass.service = new MyService();
		assertThat(ReflectionUtils.getFieldValue(annotedClass, AnnotedClass.class.getFields()[0])).isNotNull();
	}

	@Test
	public void testGetFieldValue_NullPointerException() {
		thrown.expect(NullPointerException.class);

		ReflectionUtils.getFieldValue(null, AbstractAnnotedClass.class.getFields()[0]);
	}

	@Test
	public void testGetFieldValue_IllegalArgumentException() throws SecurityException, NoSuchFieldException {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Error while trying to access field " + MyEntity.class.getDeclaredField("name"));

		ReflectionUtils.getFieldValue(new AnnotedClass(), MyEntity.class.getDeclaredField("name"));
	}

	@Test
	public void testSetFieldValue_NullPointerException() {
		thrown.expect(NullPointerException.class);

		ReflectionUtils.setFieldValue(null, AbstractAnnotedClass.class.getFields()[0], "otherValue");
	}

	@Test
	public void testSetFieldValue_IllegalArgumentException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Unable to assign the value to field: provider. "
				+ "Ensure that this field is of the correct type. Value: 1");

		ReflectionUtils.setFieldValue(new AnnotedClass(), AbstractAnnotedClass.class.getFields()[0], Long.valueOf(1));
	}
}
