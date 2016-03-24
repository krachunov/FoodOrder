package com.levins.food.menu.ui.table.order;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.levins.food.menu.jpa.Employee;
import com.levins.food.menu.jpa.Food;
import com.levins.food.menu.jpa.FoodMenuUnit;
import com.levins.food.menu.ui.ConfirmWindow;

@SuppressWarnings("serial")
@Deprecated
public class OrderTableWindow extends JFrame {
	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JTable table;
	private SearchModelOrder model;
	private TableModelOrder tableModel;
	private JButton btnDelete;
	private List<FoodMenuUnit> listToTable;
	private JLabel lblFood;
	private JLabel lblPrice;
	protected JTextField foodTextField;
	protected JTextField priceTextField;

	public TableModelOrder getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModelOrder tableModel) {
		this.tableModel = tableModel;
	}

	public List<FoodMenuUnit> getListToTable() {
		return listToTable;
	}

	/**
	 * Create the frame.
	 * 
	 * @param insis
	 */
	public OrderTableWindow() {

		model = new SearchModelOrder();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 94, 208, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		tableModel = new TableModelOrder();

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				List<String> resultFromDataBase = new ArrayList<String>();

				String searchName = foodTextField.getText().trim().equals("") ? "%"
						: foodTextField.getText().trim();
				String searchPrice = priceTextField.getText().trim().equals("") ? "%"
						: priceTextField.getText().trim();

//				FoodAction action = new FoodAction();
//				List<Food> findFood = action.findFood(searchName);
//				for (Food food : findFood) {
//					resultFromDataBase.add(food.toString());
//				}

				try {
					tableModel.setListToTable(SearchModelOrder.readString(resultFromDataBase));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});

		lblFood = new JLabel("Name");
		GridBagConstraints gbc_lblFood = new GridBagConstraints();
		gbc_lblFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFood.anchor = GridBagConstraints.EAST;
		gbc_lblFood.gridx = 0;
		gbc_lblFood.gridy = 0;
		contentPane.add(lblFood, gbc_lblFood);

		foodTextField = new JTextField();

		GridBagConstraints gbc_foodTextField = new GridBagConstraints();
		gbc_foodTextField.insets = new Insets(0, 0, 5, 5);
		gbc_foodTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_foodTextField.gridx = 1;
		gbc_foodTextField.gridy = 0;
		contentPane.add(foodTextField, gbc_foodTextField);
		foodTextField.setColumns(10);
		GridBagConstraints gbc_btnFind = new GridBagConstraints();
		gbc_btnFind.anchor = GridBagConstraints.WEST;
		gbc_btnFind.insets = new Insets(0, 0, 5, 0);
		gbc_btnFind.gridx = 2;
		gbc_btnFind.gridy = 0;
		contentPane.add(btnFind, gbc_btnFind);

		lblPrice = new JLabel("Department");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 1;
		contentPane.add(lblPrice, gbc_lblPrice);

		priceTextField = new JTextField();
		GridBagConstraints gbc_priceTextField = new GridBagConstraints();
		gbc_priceTextField.insets = new Insets(0, 0, 5, 5);
		gbc_priceTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceTextField.gridx = 1;
		gbc_priceTextField.gridy = 1;
		contentPane.add(priceTextField, gbc_priceTextField);
		priceTextField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteRow();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridwidth = 2;
		gbc_btnDelete.gridx = 1;
		gbc_btnDelete.gridy = 3;
		contentPane.add(btnDelete, gbc_btnDelete);
	}

	public File openFile(String textToButton) {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showDialog(this, textToButton);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return file;
		}
		fileChooser.setVisible(true);
		return null;
	}

	public void deleteRow(String... otherMail) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			ConfirmWindow confirm = new ConfirmWindow();
			if (confirm.isState()) {
				tableModel.deleteRecord(selectedRow);
			}

		}

	}

}
