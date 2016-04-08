package com.levins.food.menu.ui.table.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.levins.food.menu.jpa.CurrentOrder;
import com.levins.food.menu.jpa.Food;
import com.levins.food.menu.jpa.FoodMenuUnit;

public class SearchModelOrder {
	private List<CurrentOrder> listOfUnit;

	public List<CurrentOrder> getListOfFood() {
		return listOfUnit;
	}

	public void setListOfFood(List<CurrentOrder> singleLine) {
		this.listOfUnit = singleLine;
	}

	public static List<FoodMenuUnit> readString(List<String> list)
			throws ParseException {
		List<FoodMenuUnit> lineList = new ArrayList<FoodMenuUnit>();
		for (String record : list) {
			String[] foodRecord = record.split(";");

			SimpleDateFormat formatter = new SimpleDateFormat(
					"dd-MM-yyyy hh:mm:ss");
			Date parsedDate = formatter.parse(foodRecord[1]);

			// TODO
			Long current_id = Long.valueOf(foodRecord[Food.INDEX_ID]);
			String currentFoodName = foodRecord[Food.INDEX_FOOD_NAME];
			Double currentPrice = Double.valueOf(foodRecord[Food.INDEX_PIRCE]);

			FoodMenuUnit food = new Food(current_id, parsedDate,
					currentFoodName, currentPrice);
			lineList.add(food);
		}
		return lineList;
	}

}
