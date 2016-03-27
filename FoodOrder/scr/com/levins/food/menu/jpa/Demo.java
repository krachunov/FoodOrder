package com.levins.food.menu.jpa;

import java.io.IOException;
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

	public static void main(String[] args) throws IOException {

		OrderMenu menu = new OrderMenu();
		menu.setVisible(true);
	}
}
