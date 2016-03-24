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
	private List<FoodMenuUnit> listOfUnit;

	public List<FoodMenuUnit> getListOfAnimal() {
		return listOfUnit;
	}

	public void setListOfAnimal(List<FoodMenuUnit> singleLine) {
		this.listOfUnit = singleLine;
	}

	public static void writeNewFile(Queue<String> sorceToWrite,
			String outputFile) throws IOException {
		try (BufferedWriter bufferWrite = new BufferedWriter(new FileWriter(
				outputFile))) {
			while (sorceToWrite.size() > 0) {
				bufferWrite.write(sorceToWrite.poll());
			}
		}
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

			FoodMenuUnit emp = new Food(parsedDate, foodRecord[2],
					Double.valueOf(foodRecord[3]));
			lineList.add(emp);
		}
		return lineList;
	}

}
