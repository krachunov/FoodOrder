package com.levins.food.menu.jpa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.levins.food.menu.ui.OrderMenu;

public class Demo {

	public static List<Employee> getAllEmployees()
			throws ClassNotFoundException {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("FoodMenu");
		EntityManager entityManager = factory.createEntityManager();

		String stringQuery = String.format("from * s where (s.Employee");
		System.out.println(stringQuery);
		Query query = entityManager.createQuery(stringQuery);
		@SuppressWarnings("unchecked")
		List<Employee> list = query.getResultList();

		entityManager.close();
		return list;
	}

	// "dd_MM_yyyy" "dd_MM_yyyy':'HH:mm:"
	public static String createdDate(Date date) {
		String format = "dd/MMMM/yyyy' - 'HH:mm:";
		DateFormat df = new SimpleDateFormat(format);
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	public static void main(String[] args) {

		OrderMenu menu = new OrderMenu();
		menu.setVisible(true);

//		Date d = new Date();
//		System.out.println(createdDate(d));
	}
}
