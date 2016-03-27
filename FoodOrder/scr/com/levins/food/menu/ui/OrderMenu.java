package com.levins.food.menu.ui;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.levins.food.menu.jpa.DataBaseConnection;
import com.levins.food.menu.jpa.Employee;
import com.levins.food.menu.jpa.Food;
import com.levins.food.menu.jpa.FoodManage;
import com.levins.food.menu.jpa.MyOrder;
import com.levins.food.menu.ui.table.order.SearchModelOrder;
import com.levins.food.menu.ui.table.order.TableModelOrder;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

@SuppressWarnings("rawtypes")
public class OrderMenu extends JFrame {
	private static final long serialVersionUID = -7500945945511983948L;
	private FoodManage manage;
	private JComboBox comboBoxDepartment;
	private JComboBox comboBoxEmployee;
	private JComboBox comboBoxFood;
	private JDatePickerImpl datePicker;
	private String selectedDepartment;
	private JTextField textFieldQuantity;
	private JTextArea textFieldPrice;
	private JTextArea textAreaTotalCost;
	private double totalCost;
	private List<String> foodNameList;
	private List<Food> orderList;
	List<String> orderListToString;

	private JTable table;
	private SearchModelOrder model;
	private TableModelOrder tableModel;

	public TableModelOrder getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModelOrder tableModel) {
		this.tableModel = tableModel;
	}

	@SuppressWarnings("unchecked")
	public OrderMenu() throws IOException {
		File file = new File("resources/bg.jpg");
		BufferedImage myImage = ImageIO.read(new File("resources/bg.jpg"));
		setContentPane(new ImagePanel(myImage));
		manage = new FoodManage();
		model = new SearchModelOrder();
		orderList = new ArrayList<Food>();
		orderListToString = new ArrayList<>();
		tableModel = new TableModelOrder();
		setResizable(false);

		setTitle("Food Order");
		setSize(956, 573);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 41, 0, 0, 0, 130, 130, 81 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				48, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 5;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 2;
		gbc_separator_2.gridy = 0;
		getContentPane().add(separator_2, gbc_separator_2);

		JLabel lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.anchor = GridBagConstraints.WEST;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 1;
		gbc_lblDepartment.gridy = 1;
		getContentPane().add(lblDepartment, gbc_lblDepartment);

		// box1
		List<String> departmentList = manage.getAllDepartment();
		comboBoxDepartment = new JComboBox(departmentList.toArray());
		comboBoxDepartment.insertItemAt("", 0);
		comboBoxDepartment.setSelectedIndex(0);
		comboBoxDepartment.setToolTipText("Select Department");
		comboBoxDepartment.setMaximumRowCount(4);
		selectedDepartment = (String) comboBoxDepartment.getSelectedItem();
		// box2
		List<String> employeeList = manage.getAllEmployees(selectedDepartment);

		// LIstener
		ItemChangeListener aListener = new ItemChangeListener();
		aListener.addPrimaryBox(comboBoxDepartment);
		comboBoxDepartment.addItemListener(aListener);

		JLabel label = new JLabel("1");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		getContentPane().add(label, gbc_label);

		Panel panelDepartment = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;

		panelDepartment.add(comboBoxDepartment);
		getContentPane().add(panelDepartment, gbc_panel);

		JDatePanelImpl datePanel = createDateChoice();

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridheight = 19;
		gbc_separator_1.insets = new Insets(0, 0, 0, 5);
		gbc_separator_1.gridx = 2;
		gbc_separator_1.gridy = 1;
		getContentPane().add(separator_1, gbc_separator_1);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 5;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 3;
		gbc_separator.gridy = 2;
		getContentPane().add(separator, gbc_separator);
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 2;
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		gbc_panel.gridheight = 7;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 2;

		// TODO
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane1 = new GridBagConstraints();
		gbc_scrollPane1.gridwidth = 3;
		gbc_scrollPane1.gridheight = 16;
		gbc_scrollPane1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane1.gridx = 4;
		gbc_scrollPane1.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane1);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JLabel lblEmployees = new JLabel("Employees");
		GridBagConstraints gbc_lblEmployees = new GridBagConstraints();
		gbc_lblEmployees.anchor = GridBagConstraints.WEST;
		gbc_lblEmployees.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployees.gridx = 1;
		gbc_lblEmployees.gridy = 3;
		getContentPane().add(lblEmployees, gbc_lblEmployees);

		JLabel label_1 = new JLabel("2");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 4;
		getContentPane().add(label_1, gbc_label_1);
		comboBoxEmployee = new JComboBox(employeeList.toArray());
		comboBoxEmployee.setMaximumRowCount(10);
		comboBoxDepartment.setMaximumRowCount(10);
		comboBoxEmployee.setToolTipText("Select Employee");
		aListener.addBox(comboBoxEmployee);
		aListener.addBox(comboBoxEmployee);

		Panel panelEmployee = new Panel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.WEST;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;

		panelEmployee.add(comboBoxEmployee);
		getContentPane().add(panelEmployee, gbc_panel_1);
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 2;

		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 5;
		getContentPane().add(lblDate, gbc_lblDate);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setVisible(true);
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = (Date) datePicker.getModel().getValue();
				foodNameList = manage.getAllFoodByDate(date);
				comboBoxFood.setModel(new JComboBox<>(foodNameList.toArray())
						.getModel());
			}
		});

		JLabel label_2 = new JLabel("3");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 6;
		getContentPane().add(label_2, gbc_label_2);

		Panel panelDate = new Panel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.WEST;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 6;

		panelDate.add(datePicker);
		getContentPane().add(panelDate, gbc_panel_2);

		JLabel lblFood = new JLabel("Food");
		GridBagConstraints gbc_lblFood = new GridBagConstraints();
		gbc_lblFood.anchor = GridBagConstraints.WEST;
		gbc_lblFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFood.gridx = 1;
		gbc_lblFood.gridy = 7;
		getContentPane().add(lblFood, gbc_lblFood);

		comboBoxFood = new JComboBox(new Object[] {});
		comboBoxFood.insertItemAt("", 0);
		comboBoxFood.setSelectedIndex(0);
		comboBoxFood.setToolTipText("Choice food");
		comboBoxFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				textFieldPrice.setText(null);
				String selectedItem = (String) comboBoxFood.getModel()
						.getSelectedItem();

				Date date = (Date) datePicker.getModel().getValue();
				FoodManage manage = new FoodManage();
				Double foodPrice = manage.getPrice(selectedItem, date);
				textFieldPrice.append(String.valueOf(foodPrice));
			}
		});

		JLabel label_3 = new JLabel("4");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 8;
		getContentPane().add(label_3, gbc_label_3);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 8;
		getContentPane().add(comboBoxFood, gbc_comboBox);

		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 1;
		gbc_lblPrice.gridy = 9;
		getContentPane().add(lblPrice, gbc_lblPrice);

		JLabel label_4 = new JLabel("5");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 10;
		getContentPane().add(label_4, gbc_label_4);

		textFieldPrice = new JTextArea();
		textFieldPrice.setSize(10, 10);
		textFieldPrice.setEditable(false);
		GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
		gbc_textFieldPrice.anchor = GridBagConstraints.WEST;
		gbc_textFieldPrice.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrice.gridx = 1;
		gbc_textFieldPrice.gridy = 10;
		getContentPane().add(textFieldPrice, gbc_textFieldPrice);
		textFieldPrice.setColumns(5);

		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 1;
		gbc_lblQuantity.gridy = 11;
		getContentPane().add(lblQuantity, gbc_lblQuantity);

		JLabel label_5 = new JLabel("6");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 12;
		getContentPane().add(label_5, gbc_label_5);

		textFieldQuantity = new JTextField();
		textFieldQuantity.setToolTipText("Choose quantity");
		textFieldQuantity.setText("1");
		GridBagConstraints gbc_textFieldQuantity = new GridBagConstraints();
		gbc_textFieldQuantity.anchor = GridBagConstraints.WEST;
		gbc_textFieldQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldQuantity.gridx = 1;
		gbc_textFieldQuantity.gridy = 12;
		getContentPane().add(textFieldQuantity, gbc_textFieldQuantity);
		textFieldQuantity.setColumns(3);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FoodManage manage = new FoodManage();

				Food orderedFood = createOrderedFood(manage);
				orderedFood.setQuantity(Integer.valueOf(textFieldQuantity
						.getText()));

				System.out.println(orderedFood.toString());
				model.setListOfFood(orderList);
				totalCost += (orderedFood.getPrice() * orderedFood
						.getQuantity());
				textAreaTotalCost.setText("");
				textAreaTotalCost.append(String.valueOf(totalCost + " лв."));
				textFieldQuantity.setText("1");

				orderList.add(orderedFood);
				String stringToTableView = orderedFood.toString();
				orderListToString.add(stringToTableView);
				try {
					tableModel.setListToTable(SearchModelOrder
							.readString(orderListToString));
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}

			private Food createOrderedFood(FoodManage manage) {
				Date date = (Date) datePicker.getModel().getValue();
				String selectedFoodName = (String) comboBoxFood.getModel()
						.getSelectedItem();
				Long foodID = manage.getFoodID(date, selectedFoodName);

				Object connection = DataBaseConnection.getInstance();
				EntityManager entityManager = (((DataBaseConnection) connection)
						.getEntityManager(FoodManage.UNIT_NAME));
				Food selectedFood = (Food) entityManager.getReference(
						Food.class, foodID);
				return selectedFood;
			}

		});
		GridBagConstraints gbc_btnB = new GridBagConstraints();
		gbc_btnB.insets = new Insets(0, 0, 5, 5);
		gbc_btnB.gridx = 1;
		gbc_btnB.gridy = 13;
		getContentPane().add(btnAdd, gbc_btnB);

		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FoodManage manage = new FoodManage();
				Employee employee = createdOrderedEmployee(manage);

				Date dateValue = (Date) datePicker.getModel().getValue();

				MyOrder purch = new MyOrder(employee, dateValue, orderList,
						totalCost);
				employee.getPurchase().add(purch);

				manage.addUnit(employee);
				manage.addUnit(purch);

				System.out.println(purch.toString());
				purch = null;
				employee = null;
				clearAllField();

			}

			private Employee createdOrderedEmployee(FoodManage manage) {
				String selectedDepartment = (String) comboBoxDepartment
						.getModel().getSelectedItem();
				String selectedEmployeeName = (String) comboBoxEmployee
						.getModel().getSelectedItem();
				Long employeesID = manage.getEmployeesID(selectedEmployeeName,
						selectedDepartment);
				Employee employee = getEmployeeFromBase(employeesID);
				return employee;
			}

			private Employee getEmployeeFromBase(Long id) {
				Object connection = DataBaseConnection.getInstance();
				EntityManager entityManager = ((DataBaseConnection) connection)
						.getEntityManager(FoodManage.UNIT_NAME);
				Employee employee = entityManager.getReference(Employee.class,
						id);
				return employee;
			}

			private void clearAllField() {
				textAreaTotalCost.setText("0.0");
				textFieldQuantity.setText("1");
				textFieldPrice.setText("0");
				comboBoxDepartment.setSelectedIndex(0);
				datePicker.getJFormattedTextField().setText("");
				comboBoxFood.setModel(new JComboBox<>(new Object[] {})
						.getModel());
				orderListToString = new ArrayList<>();
				orderList = new ArrayList<Food>();
				totalCost = 0;
				try {
					tableModel.setListToTable(SearchModelOrder
							.readString(orderListToString));
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		});
		GridBagConstraints gbc_btnBuy = new GridBagConstraints();
		gbc_btnBuy.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuy.gridx = 4;
		gbc_btnBuy.gridy = 18;
		getContentPane().add(btnBuy, gbc_btnBuy);

		JLabel lblTotalCost = new JLabel("Total cost");
		lblTotalCost.setBackground(Color.GRAY);
		GridBagConstraints gbc_lblTotalCost = new GridBagConstraints();
		gbc_lblTotalCost.anchor = GridBagConstraints.EAST;
		gbc_lblTotalCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalCost.gridx = 5;
		gbc_lblTotalCost.gridy = 18;
		getContentPane().add(lblTotalCost, gbc_lblTotalCost);

		textAreaTotalCost = new JTextArea();
		textAreaTotalCost.setTabSize(3);
		textAreaTotalCost.setText("0.0 лв.");
		textAreaTotalCost.setEditable(false);
		GridBagConstraints gbc_textAreaTotalCost = new GridBagConstraints();
		gbc_textAreaTotalCost.anchor = GridBagConstraints.WEST;
		gbc_textAreaTotalCost.insets = new Insets(0, 0, 5, 0);
		gbc_textAreaTotalCost.gridx = 6;
		gbc_textAreaTotalCost.gridy = 18;
		getContentPane().add(textAreaTotalCost, gbc_textAreaTotalCost);
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
