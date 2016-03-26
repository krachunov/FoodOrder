package com.levins.food.menu.ui.table.order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import com.levins.food.menu.jpa.Employee;
import com.levins.food.menu.jpa.Food;
import com.levins.food.menu.jpa.FoodMenuUnit;

public class SearchModelOrder {
	private List<Food> listOfUnit;

	public List<Food> getListOfFood() {
		return listOfUnit;
	}

	public void setListOfFood(List<Food> singleLine) {
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
			Integer currentCuantity = Integer
					.valueOf(foodRecord[Food.INDEX_QUANTITY]);

			FoodMenuUnit food = new Food(current_id, parsedDate,
					currentFoodName, currentPrice, currentCuantity);
			lineList.add(food);
		}
		return lineList;
	}

}
