/**
 * Stores and lists employee names, IDs, salary, and start date in an ArrayList.
 * 
 * modified     20220520
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
import java.util.Collections;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
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

	final int MAX_INT = 2147483646;
	final int MIN_INT = 0;
	final int MAX_ID_RANGE = 400000000;
	ArrayList <String> employeeRecordsData = new ArrayList <String>();
	String firstLastName, employeeID, annualSalary, startDate, calDate;
	int employeeCount = 0, errorCode = 0;
	boolean removePress = false, updatePress = false, sortPress = false;
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

	public Employee() { // GUI Constructor
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
		annualSalaryIn.setColumns(10);
		contentPane.add(annualSalaryIn);

		startDateIn = new JSpinner();
		startDateIn.setModel(new SpinnerDateModel(today, null, today, Calendar.MONTH));
		startDateIn.setForeground(Color.WHITE);
		startDateIn.setBackground(Color.WHITE);
		startDateIn.setBounds(95, 212, 144, 22);
		dateEdit = new JSpinner.DateEditor(startDateIn, "MM/dd/yyyy");
		startDateIn.setEditor(dateEdit);
		contentPane.add(startDateIn);

		firstNameIn = new JTextField();
		firstNameIn.setBounds(95, 85, 144, 22);
		firstNameIn.setColumns(10);
		contentPane.add(firstNameIn);

		lastNameIn = new JTextField();
		lastNameIn.setBounds(95, 117, 144, 22);
		lastNameIn.setColumns(10);
		contentPane.add(lastNameIn);

		employeeIDNumber = new JSpinner();
		employeeIDNumber.setModel(new SpinnerNumberModel(0, 0, MAX_ID_RANGE, 1));
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
		remove.setEnabled(false);
		remove.setBounds(129, 265, 110, 23);
		contentPane.add(remove);
		remove.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeActionPerformed(evt);
			}
		});

		update = new JButton("Update");
		update.setEnabled(false);
		update.setBounds(11, 299, 228, 23);
		contentPane.add(update);
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}
		});

		sort = new JButton("Sort");
		sort.setEnabled(false);
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
		dialogBox.setColumns(10);
		contentPane.add(dialogBox);

		listEmployeeRecordsDataTable = new JTable();
		listEmployeeRecordsDataTable.setEnabled(false);
		listEmployeeRecordsDataTable.setRowSelectionAllowed(false);
		listEmployeeRecordsDataTable.getTableHeader().setReorderingAllowed(false);
		tableInitialize();
		listEmployeeRecordsDataTable.setBounds(72, 190, 56, 23);
		contentPane.add(listEmployeeRecordsDataTable);

		scrollListEmployeeDataTable = new JScrollPane(listEmployeeRecordsDataTable);
		scrollListEmployeeDataTable.setBounds(250, 85, 573, 306);
		scrollListEmployeeDataTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollListEmployeeDataTable);
	}

	private void resetActionPerformed(java.awt.event.ActionEvent evt) {
		int confirmOption = JOptionPane.showConfirmDialog (null, "All data will be lost!\n\nWish to continue?","RESET WARNING", JOptionPane.YES_NO_OPTION);

		if (confirmOption == JOptionPane.YES_OPTION) {
			employeeRecordsData.clear();
			employeeCount = 0; 
			errorCode = 0; 

			dialogBox.setText("");
			firstNameIn.setText("");
			lastNameIn.setText("");
			annualSalaryIn.setText("");
			startDateIn.setModel(new SpinnerDateModel(today, null, today, Calendar.MONTH));
			dateEdit = new JSpinner.DateEditor(startDateIn, "MM/dd/yyyy");
			startDateIn.setEditor(dateEdit);
			employeeIDNumber.setValue(0);

			add.setEnabled(true);
			remove.setEnabled(false);
			update.setEnabled(false);
			sort.setEnabled(false);
			firstNameIn.setEnabled(true);
			lastNameIn.setEnabled(true);
			annualSalaryIn.setEnabled(true);
			startDateIn.setEnabled(true);
			employeeIDNumber.setEnabled(true);

			sortPress = false;
			
			tableInitialize();
		}
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		if (!validInput()) {
			errorResponse();
		}

		else {
			calDate = startDateIn.getValue().toString();
			calDate = calDate.substring(4, 10) + "," + calDate.substring(23, 28);

			dialogBox.setText("SUCCESSFULLY ADDED EMPLOYEE AND INFORMATION!");

			if (employeeCount == 1) {
				sort.setEnabled(true);
			}
			if (employeeCount == 0) {
				remove.setEnabled(true);
				update.setEnabled(true);
			}

			employeeRecordsData.add(employeeIDNumber.getValue().toString());
			employeeRecordsData.add(firstNameIn.getText() + " " + lastNameIn.getText());
			employeeRecordsData.add("$" + annualSalaryIn.getText());
			employeeRecordsData.add(calDate);

			employeeCount++;

			employeeIDNumber.setValue(0);
		}

		tableWriteOut();
	}

	private void removeActionPerformed(java.awt.event.ActionEvent evt) {
		removePress = true;

		if (!validInput()) {
			errorResponse();
		}

		else {
			boolean matchID = false;

			String employeeRemove = employeeIDNumber.getValue().toString();

			if (employeeRemove.equals("0")) { // Removes the most recent entry if no specific employee ID is selected.
				dialogBox.setText("REMOVING MOST RECENT ENTRY...");

				if (sortPress) {
					dialogBox.setText("REMOVING MOST RECENT EMPLOYEE...");
				}
					
				employeeRecordsData.remove(employeeRecordsData.size() - 1);
				employeeRecordsData.remove(employeeRecordsData.size() - 1);
				employeeRecordsData.remove(employeeRecordsData.size() - 1);
				employeeRecordsData.remove(employeeRecordsData.size() - 1);
			}

			else { // Performed if a specific employee number is selected in "Employee #:".
				dialogBox.setText("REMOVING MATCHING EMPLOYEE ID ENTRY...");

				// Finds entry with the same employee ID. Removes values associated with entry.
				for (int i = 0; i < employeeRecordsData.size(); i++) {
					if (employeeRecordsData.get(i).equals(employeeRemove)) {
						employeeRecordsData.remove(i + 3);
						employeeRecordsData.remove(i + 2);
						employeeRecordsData.remove(i + 1);
						employeeRecordsData.remove(i);

						matchID = true;
						break;
					}

					i += 3;
				}

				if (!matchID) {
					errorCode = 8;
					errorResponse();
				}
			}

			if (matchID || employeeRemove.equals("0")) {
				employeeCount--;

				if (employeeCount == 1)
					sort.setEnabled(false);
				if (employeeCount == 0) {
					remove.setEnabled(false);
					update.setEnabled(false);
					sortPress = false;
				}
			}
		}

		startDateIn.setModel(new SpinnerDateModel(today, null, today, Calendar.MONTH));
		dateEdit = new JSpinner.DateEditor(startDateIn, "MM/dd/yyyy");
		startDateIn.setEditor(dateEdit);
		employeeIDNumber.setValue(0);
		
		removePress = false;

		tableWriteOut();
	}

	private void updateActionPerformed(java.awt.event.ActionEvent evt) {
		updatePress = true;

		if (!validInput()) {
			errorResponse();
		}

		else {	
			boolean matchID = false;

			String employeeUpdate = employeeIDNumber.getValue().toString();;

			dialogBox.setText("UPDATING EMPLOYEE ENTRY...");

			for (int i = 0; i < employeeRecordsData.size(); i++) {
				if (employeeRecordsData.get(i).equals(employeeUpdate)) {
					employeeRecordsData.set(i + 2, "$" + annualSalaryIn.getText());
					employeeRecordsData.set(i + 3, calDate);

					matchID = true;
					break;
				}

				i += 3;
			}

			if (!matchID) {
				errorCode = 8;
				errorResponse();
			}
		}

		updatePress = false;

		employeeIDNumber.setValue(0);

		tableWriteOut();
	}

	private void sortActionPerformed(java.awt.event.ActionEvent evt) {
		sortPress = true;

		ArrayList<String> employeeIDList = new ArrayList<String>();
		ArrayList<String> newList = new ArrayList<String>();
		
		dialogBox.setText("SORTING ENTRIES BY EMPLOYEE ID...");

		for (int i = 0; i < employeeRecordsData.size(); i++) {
			employeeIDList.add(employeeRecordsData.get(i));
			i += 3;
		}

		Collections.sort(employeeIDList);

		// Re-organizes employeeRecordData relative to employee ID order in employeeIDList.
		for (int i = 0; i < employeeIDList.size(); i++) {
			for (int j = 0; j < employeeRecordsData.size(); j++) {
				if (employeeRecordsData.get(j).equals(employeeIDList.get(i))) {
					newList.add(employeeRecordsData.get(j));
					newList.add(employeeRecordsData.get(j + 1));
					newList.add(employeeRecordsData.get(j + 2));
					newList.add(employeeRecordsData.get(j + 3));

					continue;
				}

				j += 3;
			}
		}

		employeeRecordsData = newList;

		startDateIn.setModel(new SpinnerDateModel(today, null, today, Calendar.MONTH));
		dateEdit = new JSpinner.DateEditor(startDateIn, "MM/dd/yyyy");
		startDateIn.setEditor(dateEdit);
		employeeIDNumber.setValue(0);

		tableWriteOut();
	}

	public boolean validInput() {
		try {
			boolean uniqueRandomID = false;

			errorCode = 7; // If try{} fails at parsing String values.

			String firstNameTest = firstNameIn.getText().toLowerCase().replace(" ", ""), lastNameTest = lastNameIn.getText().toLowerCase().replace(" ", "");

			errorCode = 6; // If try{} fails at parsing double values.

			double testIn1 = 0;

			int testID = 0;

			/* Next if-else statements allow for non-filled text-fields to exist.
			   They are counted as N/A and have no value. */

			if (annualSalaryIn.getText().equals("")) {
				errorCode = 10;
				return false;
			}

			else {
				testIn1 = Double.parseDouble(annualSalaryIn.getText());
			}

			// Prevents there being no first name in entry.
			if (firstNameTest.equals("") && !removePress && !updatePress || firstNameTest.equals(null) && !removePress && !updatePress) {
				errorCode = 5;
				return false;
			}

			// Checks each character to verify that there are only letters.
			for (int i = 0; i < firstNameTest.length(); i++) {
				if (firstNameTest.charAt(i) < 97 || firstNameTest.charAt(i) > 122) {
					errorCode = 4;
					return false;
				}
			}

			// Checks each character to verify that there are only letters.
			for (int i = 0; i < lastNameTest.length(); i++) {
				if (lastNameTest.charAt(i) < 97 || lastNameTest.charAt(i) > 122 && !lastNameTest.equals("")) {
					errorCode = 3;
					return false;
				}
			}

			// Creates a unique ID if "0" is entered.
			if (employeeIDNumber.getValue().toString().equals("0") && !removePress && !updatePress) {
				while (!uniqueRandomID) {
					uniqueRandomID = true;

					testID = (int)((Math.random() * MAX_ID_RANGE) + 1);

					for (int i = 0; i < employeeRecordsData.size(); i++) {
						if (testID == Integer.parseInt(employeeRecordsData.get(i))) {
							uniqueRandomID = false;
							break;
						}

						i += 3;
					}
				}

				employeeIDNumber.setValue(testID);
			}

			// Checks unique ID with other IDs.
			else if (!removePress && !updatePress) {
				testID = Integer.parseInt(employeeIDNumber.getValue().toString());

				for (int i = 0; i < employeeRecordsData.size(); i++) {
					if (testID == Integer.parseInt(employeeRecordsData.get(i))) {
						errorCode = 2;
						employeeIDNumber.setValue(0);
						return false;
					}

					i += 3;
				}
			}

			/* Next if-else statement check if values are real numbers.
			   If not between 0 & 2147483646, produces an error statement in dialogBox. */

			if (testIn1 > MAX_INT || testIn1 < MIN_INT) {
				errorCode = 1;
				return false;
			}

			else if ((int) (testIn1 * 100) != (testIn1 * 100)) {
				errorCode = 9;
				return false;
			}

			else {
				errorCode = 0;
				return true;
			}
		}

		catch (Exception e) {
			return false;
		}
	}

	public void tableInitialize() { // Initializes table format in GUI
		listEmployeeRecordsDataTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Employee #", "Name", "Salary", "Start Date"
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}

	public void tableWriteOut() { // Produces text in GUI
		tableInitialize();
		
		DefaultTableModel tableCreationModel = (DefaultTableModel)listEmployeeRecordsDataTable.getModel();
		
		for (int i = 0; i < employeeRecordsData.size(); i++) {
			tableCreationModel.addRow(new Object[]{employeeRecordsData.get(i), employeeRecordsData.get(i + 1), employeeRecordsData.get(i + 2), employeeRecordsData.get(i + 3),});
			i += 3;
		}
	}

	public void errorResponse() {
		if (errorCode == 1) {
			dialogBox.setText("ERROR: SALARY CANNOT BE GREATER THAN $2,147,483,646 OR LESS THAN $0!");
		}

		else if (errorCode == 2) {
			dialogBox.setText("ERROR: REPEAT EMPLOYEE IDs! TRY AGAIN!");
		}

		else if (errorCode == 3) {
			dialogBox.setText("ERROR: CANNOT USE DIGITS OR SPECIAL CHARACTERS IN LAST NAME!");
		}

		else if (errorCode == 4) {
			dialogBox.setText("ERROR: CANNOT USE DIGITS OR SPECIAL CHARACTERS IN FIRST NAME!");
		}

		else if (errorCode == 5) {
			dialogBox.setText("ERROR: MUST HAVE A FIRST NAME! NO DIGITS OR SPECIAL CHARACTERS!");
		}

		else if (errorCode == 6) {
			dialogBox.setText("ERROR: NO LETTERS OR SPECIAL CHARACTERS IN SALARY FIELD!");
		}

		else if (errorCode == 7) {
			dialogBox.setText("ERROR: INVALID INPUT(S)! TRY AGAIN...");
		}

		else if (errorCode == 8) {
			dialogBox.setText("ERROR: NO MATCHING EMPLOYEE ID! TRY AGAIN!");
		}

		else if (errorCode == 9) {
			dialogBox.setText("ERROR: NO MORE THAN 2 DECIMAL NUMBERS IN SALARY ENTRY! TRY AGAIN!");
		}
		
		else if (errorCode == 10) {
			dialogBox.setText("ERROR: MUST HAVE A SALARY! NO LETTERS OR SPECIAL CHARACTERS!");
		}

		else {
			dialogBox.setText("AN UNEXPECTED ERROR HAS OCCURED! PRESS RESET TO CONTINUE...");
			add.setEnabled(false);
			remove.setEnabled(false);
			update.setEnabled(false);
			sort.setEnabled(false);
			firstNameIn.setEnabled(false);
			lastNameIn.setEnabled(false);
			annualSalaryIn.setEnabled(false);
			startDateIn.setEnabled(false);
			employeeIDNumber.setEnabled(false);
		}
	}
}

/**	Developer Notes:
 * 
 * Code structure is similar to that of A11 - Student Grades (Evan's version).
 * 
 * [Error Code Info]
 * 	0 -> no error
 * 	1 -> non-real salary value(s)
 * 	2 -> repeat employee ID
 * 	3 -> digits in lastNameIn
 * 	4 -> digits in firstNameIn
 * 	5 -> no first name in field
 * 	6 -> parsing of non-double characters
 * 	7 -> invalid String inputs
 * 	8 -> no matching employee ID
 * 	9 -> excess decimal values present in salary entry
 * 
 * [ArrayList Design]
 *  employeeRecordsData data order is as follows...
 *  1 --> employeeIDNumber
 *  2 --> firstNameIn + lastNameIn
 *  3 --> annualSalaryIn
 *  4 --> startDateIn
 * 
 */
