package com.levins.food.menu.ui.table.employee;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import com.levins.food.menu.jpa.FoodManage;
import com.levins.food.menu.jpa.FoodMenuUnit;
import com.levins.food.menu.ui.ConfirmWindow;

@SuppressWarnings("serial")
public class RemoveEmployeeWindow extends JFrame {
	@SuppressWarnings("unused")
	private SearchModelEmployee model;
	private JPanel contentPane;
	private JTable table;
	private TableModelEmployee tableModel;
	private JButton btnDelete;
	private List<FoodMenuUnit> listToTable;
	private JLabel lblName;
	private JLabel lblDepartment;
	protected JTextField nameTextField;
	protected JTextField departmentTextField;

	public TableModelEmployee getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModelEmployee tableModel) {
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
	public RemoveEmployeeWindow() {

		model = new SearchModelEmployee();
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
		tableModel = new TableModelEmployee();
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				List<String> resultFromDataBase = new ArrayList<String>();

				String searchName = nameTextField.getText().trim().equals("") ? "%"
						: nameTextField.getText().trim();
				String searchDepartment = departmentTextField.getText().trim()
						.equals("") ? "%" : departmentTextField.getText()
						.trim();

				// TODO need to check
				FoodManage action = new FoodManage();
				List<Employee> findEmployees = action.findEmployees(searchName,
						searchDepartment);
				for (Employee employee : findEmployees) {
					resultFromDataBase.add(employee.toString());
				}

				tableModel.setListToTable(SearchModelEmployee
						.readString(resultFromDataBase));
			}
		});

		lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);

		nameTextField = new JTextField();

		GridBagConstraints gbc_searchUserTextField = new GridBagConstraints();
		gbc_searchUserTextField.insets = new Insets(0, 0, 5, 5);
		gbc_searchUserTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchUserTextField.gridx = 1;
		gbc_searchUserTextField.gridy = 0;
		contentPane.add(nameTextField, gbc_searchUserTextField);
		nameTextField.setColumns(10);
		GridBagConstraints gbc_btnFind = new GridBagConstraints();
		gbc_btnFind.anchor = GridBagConstraints.WEST;
		gbc_btnFind.insets = new Insets(0, 0, 5, 0);
		gbc_btnFind.gridx = 2;
		gbc_btnFind.gridy = 0;
		contentPane.add(btnFind, gbc_btnFind);

		lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.anchor = GridBagConstraints.EAST;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 1;
		contentPane.add(lblDepartment, gbc_lblDepartment);

		departmentTextField = new JTextField();
		GridBagConstraints gbc_egnTextField = new GridBagConstraints();
		gbc_egnTextField.insets = new Insets(0, 0, 5, 5);
		gbc_egnTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_egnTextField.gridx = 1;
		gbc_egnTextField.gridy = 1;
		contentPane.add(departmentTextField, gbc_egnTextField);
		departmentTextField.setColumns(10);

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
