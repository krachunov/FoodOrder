package com.levins.food.menu.jpa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.levins.food.menu.ui.ControlPanelMenu;

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
	public static Date createdDate(String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		// return reportDate;
		return today;
	}

	public static void main(String[] args) {
		Date date = createdDate("dd_MM_yyyy':'HH:mm:");

		// Double totalAmount = 0d;
		// for (Food food : listFood) {
		// totalAmount += food.getPrice();
		// }
		// // MyOrder purch = new MyOrder(employee, date, listFood,
		// totalAmount);
		// employee.getPurchase().add(purch);
		// action.removeEmployee("%", "IT");

		// FoodManage manager = new FoodManage();
		// System.out.println(manager.getAllDepartment().size());

		ControlPanelMenu menu = new ControlPanelMenu();
		menu.setVisible(true);

	}
}
