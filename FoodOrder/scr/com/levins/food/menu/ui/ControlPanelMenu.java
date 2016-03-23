package com.levins.food.menu.ui;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import com.levins.food.menu.jpa.FoodManage;

import java.awt.Panel;
import java.util.List;

public class ControlPanelMenu extends JFrame {
	private static final long serialVersionUID = -7500945945511983948L;
	FoodManage manage;
	JComboBox comboBoxDepartment;
	JComboBox comboBoxEmployee;
	String selectedDepartment;

	public ControlPanelMenu() {
		manage = new FoodManage();
		setTitle("Food Order");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 4;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 0;
		getContentPane().add(separator_2, gbc_separator_2);

		JLabel lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 1;
		getContentPane().add(lblDepartment, gbc_lblDepartment);

		JLabel lblEmployees = new JLabel("Employees");
		GridBagConstraints gbc_lblEmployees = new GridBagConstraints();
		gbc_lblEmployees.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployees.gridx = 1;
		gbc_lblEmployees.gridy = 1;
		getContentPane().add(lblEmployees, gbc_lblEmployees);

		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 3;
		gbc_lblDate.gridy = 1;
		getContentPane().add(lblDate, gbc_lblDate);

		JLabel lblFood = new JLabel("Food");
		GridBagConstraints gbc_lblFood = new GridBagConstraints();
		gbc_lblFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFood.gridx = 4;
		gbc_lblFood.gridy = 1;
		getContentPane().add(lblFood, gbc_lblFood);

		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_lblQuantity.gridx = 6;
		gbc_lblQuantity.gridy = 1;
		getContentPane().add(lblQuantity, gbc_lblQuantity);

		Panel panelDepartment = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;

		// TODO
		List<String> departmentList = manage.getAllDepartment();
		comboBoxDepartment = createDropDown(departmentList);

		ItemChangeListener aListener = new ItemChangeListener();
		aListener.addBox(comboBoxEmployee);
		selectedDepartment = (String) comboBoxDepartment.getSelectedItem();

		List<String> employeeList = manage.getAllEmployees(selectedDepartment);
		comboBoxEmployee = createDropDown(employeeList);

		aListener.addPrimaryBox(comboBoxDepartment);
		aListener.addListCoice(selectedDepartment);
		aListener.addBox(comboBoxEmployee);
		comboBoxDepartment.addItemListener(aListener);
		panelDepartment.add(comboBoxDepartment);
		getContentPane().add(panelDepartment, gbc_panel);

		Panel panelEmployee = new Panel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;

		panelEmployee.add(comboBoxEmployee);
		getContentPane().add(panelEmployee, gbc_panel_1);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 3;
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 2;
		getContentPane().add(separator, gbc_separator);
		this.pack();
	}

	/**
	 * 
	 * @param panel
	 * @param listWithRecord
	 * @return selected choice
	 */
	@SuppressWarnings("unchecked")
	private JComboBox createDropDown(List<String> listWithRecord) {
		Object[] bookTitles = listWithRecord.toArray();
		JComboBox<String> bookList = new JComboBox(bookTitles);
		// String selectedBook = (String) bookList.getSelectedItem();
		return bookList;
	}

}
