package com.boz.commons.test.helpers;

import javax.persistence.EntityManager;

/**
 * Classe service pour tests.
 *
 * @author jboz
 */
public class MyService {

	private EntityManager em;

	public String greet(final String userName) {
		return "Hello, " + userName;
	}

	public EntityManager getEm() {
		return em;
	}
}