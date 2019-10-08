package br.com.sysagrega.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Conexao {

	private static final String PERSISTENCE_UNIT = "sysagregaPU";

	private static final ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();

	private static EntityManagerFactory entityManagerFactory;

	// private Conexao() {
	// }
	//
	public static EntityManager geEntityManager() {

		if (entityManagerFactory == null) {

			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		}

		EntityManager entityManager = threadEntityManager.get();

		if (entityManager == null || !entityManager.isOpen()) {

			entityManager = entityManagerFactory.createEntityManager();
			Conexao.threadEntityManager.set(entityManager);
		}

		return entityManager;

	}

	public static void closeEntityManager() {

		EntityManager em = threadEntityManager.get();

		if (em != null) {

			EntityTransaction transaction = em.getTransaction();

			if (transaction.isActive()) {

				transaction.commit();

			}

			em.close();
			threadEntityManager.set(null);
		}
	}
	
	public static void closeEntityManagerFactory() {
		
		closeEntityManager(); 
		entityManagerFactory.close(); 
		
	}

}
