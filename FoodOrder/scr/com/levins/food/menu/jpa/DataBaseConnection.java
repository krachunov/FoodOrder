package com.levins.food.menu.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataBaseConnection {
	static final String UNIT = "FoodMenu";
	private static DataBaseConnection dataBaseConnetction;
	private static EntityManager entityManager;

	private DataBaseConnection() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(UNIT);
		entityManager = factory.createEntityManager();
	}

	public static DataBaseConnection getInstance() {
		if (dataBaseConnetction == null) {
			return dataBaseConnetction = new DataBaseConnection();
		} else {
			return dataBaseConnetction;
		}
	}

	public EntityManager getEntityManager(String unitName) {
		return entityManager;
	}

}
