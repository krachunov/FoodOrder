package com.levins.food.menu.ui.table.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.levins.food.menu.jpa.Food;
import com.levins.food.menu.jpa.FoodMenuUnit;

@SuppressWarnings("serial")
public class TableModelOrder extends AbstractTableModel {
	private static final int COLUMNS_COUNT = 4;
	private List<FoodMenuUnit> listToTable;

	public List<FoodMenuUnit> getListToTable() {
		return listToTable;
	}

	public void setListToTable(List<FoodMenuUnit> listToTable) {
		this.listToTable = listToTable;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS_COUNT;
	}

	@Override
	public int getRowCount() {

		return (listToTable != null ? listToTable.size() : 0);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		@SuppressWarnings({ "rawtypes", "unused" })
		List list = getListToTable();
		Food singleResult = (Food) listToTable.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return createdDate(singleResult.getDate());
		case 1:
			return singleResult.getValue();
		case 2:
			return singleResult.getPrice()+" лв.";
		case 3:
			return singleResult.getQuantity();
		}
		return null;
	}

	public static String createdDate(Date date) {
		String format = "dd/MMMM/yyyy' - 'HH:mm:";
		DateFormat df = new SimpleDateFormat(format);
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		// case 0:
		// return "ID";
		case 0:
			return "Date";
		case 1:
			return "Name";
		case 2:
			return "Price";
		case 3:
			return "Quantity";

		default:
			break;
		}
		return null;
	}

	public void deleteRecord(int index) {
		Food remove = (Food) listToTable.remove(index);
		// FoodAction action = new FoodAction();
		// action.removeFood(remove.getValue());
		fireTableDataChanged();
	}
}
