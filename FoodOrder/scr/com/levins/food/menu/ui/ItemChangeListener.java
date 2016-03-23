package com.levins.food.menu.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;

import com.levins.food.menu.jpa.FoodManage;

@SuppressWarnings("rawtypes")
class ItemChangeListener implements ItemListener {
	private JComboBox parentBox;
	private JComboBox box;

	public ItemChangeListener() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			String choice = (String) parentBox.getSelectedItem();
			FoodManage manage = new FoodManage();
			List<String> employeeList = manage.getAllEmployees(choice);
			Object[] bookTitles = employeeList.toArray();
			box.setModel(new JComboBox<>(bookTitles).getModel());

		}
	}

	public void addBox(JComboBox box) {
		this.box = box;
	}

	public void addPrimaryBox(JComboBox comboBoxDepartment) {
		this.parentBox = comboBoxDepartment;

	}
}
