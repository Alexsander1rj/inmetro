package br.gov.inmetro.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory("inmetroPU");

	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
