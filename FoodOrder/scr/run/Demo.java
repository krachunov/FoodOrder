package run;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.levins.food.menu.ui.OrderMenu;

public class Demo {

	public static void main(String[] args) throws IOException {

		 OrderMenu menu = new OrderMenu();
		 menu.setVisible(true);

		// Employee employee = new Employee("Ico", "IT");
		//
		// Date date = new Date();
		// Food food1 = new Food(1000l, date, "Musaka", 1d);
		// Food food2 = new Food(1002l, date, "voda", 2d);
		// Food food3 = new Food(1003l, date, "shokolad", 3.5d);
		//
		// HashMap<Long, Integer> orderLIst = new HashMap<>();
		//
		// orderLIst.put(food1.getId(), 1);
		// orderLIst.put(food2.getId(), 1);
		// orderLIst.put(food3.getId(), 0);
		//
		// Double totalAmount = 0d;
		// for (Entry<Long, Integer> food : orderLIst.entrySet()) {
		// System.out.println("price " + food.getKey());
		// totalAmount += (food.getValue() * food.getKey().getPrice());
		// }
		// System.out.println(totalAmount);
		//
		// MyOrder order = new MyOrder(employee, date, orderLIst, totalAmount);

	}
}
