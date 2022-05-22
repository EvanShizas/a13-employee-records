package new_interface_beta;

/**
 * Stores and lists employee names, IDs, salary, and start date in an ArrayList.
 * 
 * modified     20220514
 * date         20220512
 * @filename    Employee.java
 * @author      Alvin Chan, Evan Shizas
 * @version     1.0.0
 * @see         A13 - Employee Records
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Employee extends JFrame {

	private JPanel contentPane;
	private JTextField firstNameIn;
	private JTextField lastNameIn;
	private JTextField dialogBox;
	private JScrollPane scrollListEmployeeDataTable;
	private JSpinner employeeIDNumber;
	private JSpinner startDateIn;
	private JTextField annualSalaryIn;
	private JButton add;
	private JButton remove;
	private JButton reset;
	private JButton update;
	private JButton sort;
	private JSpinner.DateEditor dateEdit;
	private JTable listEmployeeRecordsDataTable;

	ArrayList<String> nameData = new ArrayList<String>();
	ArrayList<String> idData = new ArrayList<String>();
	ArrayList<Integer> salaryData = new ArrayList<Integer>();
	ArrayList<String> dateData = new ArrayList<String>();
	
	String firstLastName, employeeID, annualSalary, startDate;

	int errorCode = 0;

	boolean allowDebug = false;

	Date today = new Date();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Employee() {
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("A13 - Employee Records");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 440);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEmployeeRecords = new JLabel("Employee Records");
		lblEmployeeRecords.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeRecords.setForeground(Color.BLUE);
		lblEmployeeRecords.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblEmployeeRecords.setBackground(Color.WHITE);
		lblEmployeeRecords.setBounds(0, 0, 833, 41);
		contentPane.add(lblEmployeeRecords);

		JLabel firstName = new JLabel("First Name:");
		firstName.setBounds(10, 85, 85, 22);
		contentPane.add(firstName);

		JLabel lastName = new JLabel("Last Name:");
		lastName.setBounds(10, 117, 80, 22);
		contentPane.add(lastName);

		JLabel employeeIdNumberLbl = new JLabel("Employee #:");
		employeeIdNumberLbl.setBackground(Color.WHITE);
		employeeIdNumberLbl.setBounds(10, 149, 80, 22);
		contentPane.add(employeeIdNumberLbl);

		JLabel annualSalaryLbl = new JLabel("Salary ($):");
		annualSalaryLbl.setBackground(Color.WHITE);
		annualSalaryLbl.setBounds(10, 180, 78, 22);
		contentPane.add(annualSalaryLbl);

		JLabel startDateLbl = new JLabel("Start Date:");
		startDateLbl.setBounds(10, 212, 78, 22);
		contentPane.add(startDateLbl);

		JLabel enterBelowLabel = new JLabel("Enter Information Below:");
		enterBelowLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		enterBelowLabel.setBounds(10, 55, 229, 20);
		contentPane.add(enterBelowLabel);

		JLabel dateStructureLbl = new JLabel("mm/dd/yyyy");
		dateStructureLbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		dateStructureLbl.setHorizontalAlignment(SwingConstants.LEFT);
		dateStructureLbl.setBackground(Color.WHITE);
		dateStructureLbl.setBounds(95, 232, 144, 22);
		contentPane.add(dateStructureLbl);

		annualSalaryIn = new JTextField();
		annualSalaryIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		annualSalaryIn.setHorizontalAlignment(SwingConstants.LEFT);
		annualSalaryIn.setBounds(95, 181, 144, 22);
		contentPane.add(annualSalaryIn);
		annualSalaryIn.setColumns(10);

		startDateIn = new JSpinner();
		startDateIn.setModel(new SpinnerDateModel(today, null, today, Calendar.MONTH));
		startDateIn.setForeground(Color.WHITE);
		startDateIn.setBackground(Color.WHITE);
		startDateIn.setBounds(95, 212, 144, 22);
		contentPane.add(startDateIn);

		dateEdit = new JSpinner.DateEditor(startDateIn, "MM/dd/yyyy");
		startDateIn.setEditor(dateEdit);

		firstNameIn = new JTextField();
		firstNameIn.setBounds(95, 85, 144, 22);
		contentPane.add(firstNameIn);
		firstNameIn.setColumns(10);

		lastNameIn = new JTextField();
		lastNameIn.setBounds(95, 117, 144, 22);
		contentPane.add(lastNameIn);
		lastNameIn.setColumns(10);

		employeeIDNumber = new JSpinner();
		employeeIDNumber.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		employeeIDNumber.setForeground(Color.WHITE);
		employeeIDNumber.setBackground(Color.WHITE);
		employeeIDNumber.setBounds(95, 149, 144, 22);
		contentPane.add(employeeIDNumber);

		add = new JButton("Add");
		add.setBounds(10, 265, 110, 23);
		contentPane.add(add);
		add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addActionPerformed(evt);
			}
		});

		remove = new JButton("Remove");
		remove.setBounds(129, 265, 110, 23);
		contentPane.add(remove);
		remove.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeActionPerformed(evt);
			}
		});

		update = new JButton("Update");
		update.setBounds(11, 299, 228, 23);
		contentPane.add(update);
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}
		});

		sort = new JButton("Sort");
		sort.setBounds(11, 333, 228, 23);
		contentPane.add(sort);
		sort.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sortActionPerformed(evt);
			}
		});

		reset = new JButton("Reset");
		reset.setBounds(11, 367, 228, 23);
		contentPane.add(reset);
		reset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				resetActionPerformed(evt);
			}
		});

		dialogBox = new JTextField();
		dialogBox.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 12));
		dialogBox.setEditable(false);
		dialogBox.setBackground(Color.WHITE);
		dialogBox.setBounds(250, 55, 573, 22);
		contentPane.add(dialogBox);
		dialogBox.setColumns(10);

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Employee #"); // tweak size to be smaller
		tableModel.addColumn("Name");
		tableModel.addColumn("Salary");
		tableModel.addColumn("Start Date");
		listEmployeeRecordsDataTable = new JTable(tableModel);
		listEmployeeRecordsDataTable.setEnabled(false);
		listEmployeeRecordsDataTable.setRowSelectionAllowed(false);
		listEmployeeRecordsDataTable.getTableHeader().setReorderingAllowed(false);
		listEmployeeRecordsDataTable.setBounds(72, 190, 56, 23);
		contentPane.add(listEmployeeRecordsDataTable);

		/*
		 * new Object[][] { { "334040532", "Evan Shizas", "$100,000", "02/10/2004" }, },
		 * new String[] { "Employee #", "Name", "Salary", "Start Date" }) { Class[]
		 * columnTypes = new Class[] { String.class, String.class, String.class,
		 * String.class };
		 * 
		 * public Class getColumnClass(int columnIndex) { return
		 * columnTypes[columnIndex]; } }
		 */

		scrollListEmployeeDataTable = new JScrollPane(listEmployeeRecordsDataTable);
		scrollListEmployeeDataTable.setBounds(250, 85, 573, 306);
		scrollListEmployeeDataTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollListEmployeeDataTable);
	}

	private void resetActionPerformed(java.awt.event.ActionEvent evt) {
		int confirmOption = JOptionPane.showConfirmDialog(null, "All data will be lost!\n\nWish to continue?",
				"RESET WARNING", JOptionPane.YES_NO_OPTION);

		if (confirmOption == JOptionPane.YES_OPTION) {
			nameData.clear();
			idData.clear();
			salaryData.clear();
			dateData.clear();
		}
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		if (!validInput()) {
			errorResponse();
		} else {
			nameData.add(firstNameIn.getText() + " " + lastNameIn.getText());
			idData.add(employeeIDNumber.getValue().toString()); // auto increment/choose?
			salaryData.add(Integer.valueOf(annualSalaryIn.getText()));
			dateData.add(startDateIn.getValue().toString());
			
			DefaultTableModel tableModel = (DefaultTableModel)listEmployeeRecordsDataTable.getModel(); 
			tableModel.addRow(new Object[]{ idData.get(idData.size() - 1), nameData.get(nameData.size() - 1), salaryData.get(salaryData.size() - 1), dateData.get(dateData.size() - 1) });
		}
	}

	private void removeActionPerformed(java.awt.event.ActionEvent evt) {
		DefaultTableModel tableModel = (DefaultTableModel)listEmployeeRecordsDataTable.getModel();

		for (int i = 0; i < idData.size(); i++) {
			if (idData.get(i).equals(employeeIDNumber.getValue().toString())) {
				if (listEmployeeRecordsDataTable.getRowCount() > 0) {
					tableModel.removeRow(idData.indexOf(idData.get(i)));
				} 
			}
		}
		
		if (!validInput()) {
			errorResponse();
		} else {
			for (int i = 0; i < idData.size() - 1; i++) { // needs error check against invalid targets
				if (i < idData.size() - 1) { 
					if (idData.get(i).equals(employeeIDNumber.getValue().toString())) { // needs to check for unique id
						if (listEmployeeRecordsDataTable.getRowCount() > 0) {
							nameData.remove(i);
							idData.remove(i); 
							salaryData.remove(i);
							dateData.remove(i);
							
							System.out.println(idData.size());
							
							tableModel.removeRow(idData.indexOf(idData.get(i)));
						}
					}
				} else {
					// write code to prevent out of bounds explosion
				}
			}
		}
	}

	private void updateActionPerformed(java.awt.event.ActionEvent evt) {
		if (!validInput()) {
			errorResponse();
		} else {
			for (int i = 0; i < idData.size(); i++) {
				if (idData.get(i).equals(employeeIDNumber.getValue().toString())) {
					if (nameData.get(i).equals(firstNameIn + " " + lastNameIn)) {
						salaryData.set(i, Integer.parseInt(annualSalaryIn.getText()));
						dateData.set(i, startDateIn.getValue().toString());
					}
				}
			}
		}
	}

	private void sortActionPerformed(java.awt.event.ActionEvent evt) {
		if (!validInput()) {
			errorResponse();
		} else {
			ArrayList<String> sortedName = new ArrayList<String>();
			ArrayList<String> sortedID = new ArrayList<String>();
			ArrayList<Integer> sortedSalary = new ArrayList<Integer>();
			ArrayList<String> sortedDate = new ArrayList<String>();
			
			sortedName = (ArrayList<String>)nameData.clone();
			sortedID = (ArrayList<String>)idData.clone();
			sortedSalary = (ArrayList<Integer>)salaryData.clone();
			sortedDate = (ArrayList<String>)dateData.clone();
			
			for (int i = 0; i < idData.size(); i++) {
				if (idData.get(i).equals(employeeIDNumber.getValue().toString())) {
					String tempName;
					String tempID;
					int tempSalary;
					String tempDate;
					
					int counter = 1;
					while (counter > 0) {
						counter = 0;
						for (int n = 0; n < idData.size(); n++) {
							if (n + 1 < idData.size()) {
								if (Integer.valueOf(sortedID.get(n)) > Integer.valueOf(sortedID.get(n + 1))) {
									tempName = sortedName.get(n);
									tempID = sortedID.get(n);
									tempSalary = sortedSalary.get(n);
									tempDate = sortedDate.get(n);
									
									sortedName.set(n, sortedName.get(n + 1));
									sortedID.set(n, sortedID.get(n + 1));
									sortedSalary.set(n, sortedSalary.get(n + 1));
									sortedDate.set(n, sortedDate.get(n + 1));
									
									sortedName.set(n + 1, tempName);
									sortedID.set(n + 1, tempID);
									sortedSalary.set(n + 1, tempSalary);
									sortedDate.set(n + 1, tempDate);
									
									counter++;
								}
							}
						}
					}
					
					nameData = sortedName;
					idData = sortedID;
					salaryData = sortedSalary;
					dateData = sortedDate;
				}
			}

			DefaultTableModel tableModel = (DefaultTableModel)listEmployeeRecordsDataTable.getModel();
			for (int i = 0; i < sortedID.size(); i++) {
				if (sortedID.get(i).equals(employeeIDNumber.getValue().toString())) {
					if (listEmployeeRecordsDataTable.getRowCount() > 0) {
						tableModel.removeRow(i); // fix remove code; removes doubles on 0 and 1 ID
						tableModel.insertRow(i, new Object[]{ sortedID.get(i), nameData.get(nameData.size() - 1), salaryData.get(salaryData.size() - 1), dateData.get(dateData.size() - 1) });
					}
				}
			}
		}
	}

	public boolean validInput() {
		boolean valid;

		// check for blank fields
		if (firstNameIn.getText().equals("")) {
			valid = false;
		} else if (lastNameIn.getText().equals("")) {
			valid = false;
		} else if (annualSalaryIn.getText().equals("")) {
			valid = false;
		} else {
			valid = true;
		}

		// Checks each character to verify that there are only letters.
		for (int i = 0; i < firstNameIn.getText().length(); i++) {
			if (firstNameIn.getText().charAt(i) < 97 || firstNameIn.getText().charAt(i) > 122) {
				errorCode = 4;
				return false;
			}
		}

		// Checks each character to verify that there are only letters.
		for (int i = 0; i < lastNameIn.getText().length(); i++) {
			if (lastNameIn.getText().charAt(i) < 97 || lastNameIn.getText().charAt(i) > 122) {
				errorCode = 3;
				valid = false;
			}
		}

		// check only numbers are used in salary
		try {
			Integer.parseInt(annualSalaryIn.getText());
		} catch (Exception e) {
			valid = false; // idk if this works out
		}

		return valid;
	}

	public void arrayInitialize() { // Only needed if a 2D array is being done

	}

	public void arrayWriteOut() { // Produces text in the GUI

	}

	public void errorResponse() {

	}
}

/**
 * Developer Notes:
 * 
 * Code structure is similar to that of A11 (Evan's version).
 * 
 * [Error Code Info]
 * 
 * [ArrayList Design] ArrayList data order is as follows... 1 --> firstNameIn +
 * lastNameIn 2 --> employeeIDNumber 3 --> annualSalaryIn 4 --> startDateIn
 * 
 */