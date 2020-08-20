package br.appjsf3.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory factory = null;
	private static EntityManager manager = null;

	public static EntityManager getEntityManager() {

		try {
			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("appjsf3PU");
			}

			if (manager == null) {
				manager = factory.createEntityManager();
			}
			return manager;
		} catch (Throwable e) {
			System.out.println("Erro na criação inicial do objeto EntityManager: " + e);
			throw new ExceptionInInitializerError(e);
		}

	}

}
