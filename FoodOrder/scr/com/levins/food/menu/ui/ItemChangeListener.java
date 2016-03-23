package com.levins.food.menu.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.levins.food.menu.jpa.FoodManage;

class ItemChangeListener implements ItemListener {
	private JComboBox parentBox;
	private JComboBox box;
	private String choice;

	public ItemChangeListener() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Object item = event.getItem();
			choice = (String) parentBox.getSelectedItem();
			FoodManage manage = new FoodManage();
			List<String> employeeList = manage.getAllEmployees(choice);
			Object[] bookTitles = employeeList.toArray();
			box.setModel(new JComboBox<>(bookTitles).getModel());

			System.out.println("ddddd");
		}
	}

	public void addBox(JComboBox box) {
		this.box = box;
	}

	public void addListCoice(String choice) {
		this.choice = choice;
	}

	public void addPrimaryBox(JComboBox comboBoxDepartment) {
		this.parentBox = comboBoxDepartment;

	}
}
