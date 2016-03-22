package com.levins.food.menu.ui.table.employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.levins.food.menu.jpa.Employee;
import com.levins.food.menu.jpa.FoodMenuUnit;

public class SearchModelEmployee {
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

	public static List<FoodMenuUnit> readString(List<String> list) {
		List<FoodMenuUnit> lineList = new ArrayList<FoodMenuUnit>();
		for (String record : list) {
			String[] empRecord = record.split(";");
			FoodMenuUnit emp = new Employee(empRecord[0], empRecord[1]);
			lineList.add(emp);
		}
		return lineList;
	}

}
