package com.boz.commons.test.dbunit;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import com.boz.commons.test.helpers.MyService;

@DataSet("entities")
public class DBUnitRuleNextTest {
	@Rule
	public DBUnitRule dbUnitRule = DBUnitRule.init();

	@InjectEntiyManager
	private MyService service;

	@Test
	public void testLoad() {
		assertThat(service.getEm()).isNotNull();
	}
}
