package com.levins.food.menu.ui;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.levins.food.menu.jpa.FoodManage;

import java.awt.Panel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("rawtypes")
public class OrderMenu extends JFrame {
	private static final long serialVersionUID = -7500945945511983948L;
	FoodManage manage;
	JComboBox comboBoxDepartment;
	JComboBox comboBoxEmployee;
	JComboBox comboBoxFood;
	JDatePickerImpl datePicker;
	String selectedDepartment;
	private JTextField textFieldQuantity;
	private JTextField textFieldPrice;
	private List<String> foodList;

	@SuppressWarnings("unchecked")
	public OrderMenu() {
		manage = new FoodManage();
		setTitle("Food Order");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 85, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, 1.0, Double.MIN_VALUE };
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

		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 5;
		gbc_lblPrice.gridy = 1;
		getContentPane().add(lblPrice, gbc_lblPrice);

		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_lblQuantity.gridx = 7;
		gbc_lblQuantity.gridy = 1;
		getContentPane().add(lblQuantity, gbc_lblQuantity);

		// box1
		List<String> departmentList = manage.getAllDepartment();
		comboBoxDepartment = new JComboBox(departmentList.toArray());
		selectedDepartment = (String) comboBoxDepartment.getSelectedItem();
		// box2
		List<String> employeeList = manage.getAllEmployees(selectedDepartment);
		comboBoxEmployee = new JComboBox(employeeList.toArray());

		// LIstener
		ItemChangeListener aListener = new ItemChangeListener();
		aListener.addBox(comboBoxEmployee);
		aListener.addPrimaryBox(comboBoxDepartment);
		aListener.addBox(comboBoxEmployee);
		comboBoxDepartment.addItemListener(aListener);

		Panel panelDepartment = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;

		panelDepartment.add(comboBoxDepartment);
		getContentPane().add(panelDepartment, gbc_panel);

		Panel panelEmployee = new Panel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;

		panelEmployee.add(comboBoxEmployee);
		getContentPane().add(panelEmployee, gbc_panel_1);

		JDatePanelImpl datePanel = createDateChoice();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 2;

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 3;
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 2;
		getContentPane().add(separator, gbc_separator);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setVisible(true);
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = (Date) datePicker.getModel().getValue();
				foodList = manage.getAllFodd(date);
//				comboBoxFood = new JComboBox(foodList.toArray());
				comboBoxFood.setModel(new JComboBox<>(foodList.toArray()).getModel());
			}
		});

		Panel panelDate = new Panel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 2;

		panelDate.add(datePicker);
		getContentPane().add(panelDate, gbc_panel_2);

		Panel panelFood = new Panel();
		GridBagConstraints gbc_panelFood = new GridBagConstraints();
		gbc_panelFood.insets = new Insets(0, 0, 5, 5);
		gbc_panelFood.gridx = 6;
		gbc_panelFood.gridy = 0;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 2;
		getContentPane().add(panelFood, gbc_panelFood);

		comboBoxFood = new JComboBox(new Object[] {});

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 2;
		getContentPane().add(comboBoxFood, gbc_comboBox);

		textFieldPrice = new JTextField();
		GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
		gbc_textFieldPrice.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrice.gridx = 5;
		gbc_textFieldPrice.gridy = 2;
		getContentPane().add(textFieldPrice, gbc_textFieldPrice);
		textFieldPrice.setColumns(10);

		textFieldQuantity = new JTextField();
		GridBagConstraints gbc_textFieldQuantity = new GridBagConstraints();
		gbc_textFieldQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldQuantity.gridx = 7;
		gbc_textFieldQuantity.gridy = 2;
		getContentPane().add(textFieldQuantity, gbc_textFieldQuantity);
		textFieldQuantity.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date value = (Date) datePicker.getModel().getValue();
				System.out.println(value);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		this.pack();
	}

	private JDatePanelImpl createDateChoice() {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		return datePanel;
	}

}
