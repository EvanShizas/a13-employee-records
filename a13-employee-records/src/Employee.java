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

public class Employee extends JFrame {

	private JPanel contentPane;
	private JTextField firstNameIn;
	private JTextField lastNameIn;
	private JTextField dialogBox;
	private JTextArea listEmployeeRecordsBox;
	private JScrollPane scrollListEmployeeRecordsBox;
	private JSpinner employeeIDNumber;
	private JSpinner startDateIn;
	private JTextField annualSalaryIn;
	private JButton add;
	private JButton remove;
	private JButton reset;
	private JButton update;
	private JButton sort;
	private JSpinner.DateEditor dateEdit;
	
	ArrayList <String> employeeRecordsData = new ArrayList <String>();
	
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
		setBounds(100, 100, 500, 687);
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
		lblEmployeeRecords.setBounds(0, 0, 488, 41);
		contentPane.add(lblEmployeeRecords);
		
		JLabel firstName = new JLabel("First Name:");
		firstName.setBounds(10, 55, 85, 22);
		contentPane.add(firstName);

		JLabel lastName = new JLabel("Last Name:");
		lastName.setBounds(250, 55, 80, 22);
		contentPane.add(lastName);
		
		JLabel employeeIdNumberLbl = new JLabel("Employee #:");
		employeeIdNumberLbl.setBackground(Color.WHITE);
		employeeIdNumberLbl.setBounds(250, 88, 80, 20);
		contentPane.add(employeeIdNumberLbl);
		
		JLabel annualSalaryLbl = new JLabel("Salary:      $");
		annualSalaryLbl.setBounds(10, 119, 78, 22);
		contentPane.add(annualSalaryLbl);

		JLabel startDateLbl = new JLabel("Start Date:");
		startDateLbl.setBounds(10, 152, 78, 22);
		contentPane.add(startDateLbl);
		
		JLabel enterBelowLabel = new JLabel("Enter Information Below:");
		enterBelowLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		enterBelowLabel.setBounds(10, 92, 178, 20);
		contentPane.add(enterBelowLabel);
		
		JLabel dateStructureLbl = new JLabel("mm/dd/yyyy");
		dateStructureLbl.setFont(new Font("Tahoma", Font.ITALIC, 11));
		dateStructureLbl.setHorizontalAlignment(SwingConstants.LEFT);
		dateStructureLbl.setBackground(Color.WHITE);
		dateStructureLbl.setBounds(88, 170, 144, 22);
		contentPane.add(dateStructureLbl);
		
		annualSalaryIn = new JTextField();
		annualSalaryIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		annualSalaryIn.setHorizontalAlignment(SwingConstants.LEFT);
		annualSalaryIn.setBounds(88, 119, 144, 22);
		contentPane.add(annualSalaryIn);
		annualSalaryIn.setColumns(10);

		startDateIn = new JSpinner();
		startDateIn.setModel(new SpinnerDateModel(today, null, today, Calendar.MONTH));
		startDateIn.setForeground(Color.WHITE);
		startDateIn.setBackground(Color.WHITE);
		startDateIn.setBounds(88, 152, 144, 22);
		contentPane.add(startDateIn);
		
		dateEdit = new JSpinner.DateEditor(startDateIn, "MM/dd/yyyy");
		startDateIn.setEditor(dateEdit);
		
		firstNameIn = new JTextField();
		firstNameIn.setBounds(88, 55, 144, 22);
		contentPane.add(firstNameIn);
		firstNameIn.setColumns(10);

		lastNameIn = new JTextField();
		lastNameIn.setBounds(334, 55, 144, 22);
		contentPane.add(lastNameIn);
		lastNameIn.setColumns(10);

		employeeIDNumber = new JSpinner();
		employeeIDNumber.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		employeeIDNumber.setForeground(Color.WHITE);
		employeeIDNumber.setBackground(Color.WHITE);
		employeeIDNumber.setBounds(334, 88, 144, 20);
		contentPane.add(employeeIDNumber);
		
		add = new JButton("Add");
		add.setBounds(250, 119, 110, 23);
		contentPane.add(add);
		add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addActionPerformed(evt);
			}
		});

		remove = new JButton("Remove");
		remove.setEnabled(false);
		remove.setBounds(368, 119, 110, 23);
		contentPane.add(remove);
		remove.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeActionPerformed(evt);
			}
		});

		update = new JButton("Update");
		update.setEnabled(false);
		update.setBounds(250, 152, 228, 23);
		contentPane.add(update);
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}
		});

		sort = new JButton("Sort");
		sort.setEnabled(false);
		sort.setBounds(250, 185, 228, 23);
		contentPane.add(sort);
		sort.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sortActionPerformed(evt);
			}
		});

		reset = new JButton("Reset");
		reset.setBounds(250, 218, 228, 23);
		contentPane.add(reset);
		reset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				resetActionPerformed(evt);
			}
		});

		dialogBox = new JTextField();
		dialogBox.setText("#########");
		dialogBox.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 12));
		dialogBox.setEditable(false);
		dialogBox.setBackground(Color.WHITE);
		dialogBox.setBounds(10, 253, 468, 22);
		contentPane.add(dialogBox);
		dialogBox.setColumns(10);

		listEmployeeRecordsBox = new JTextArea();
		listEmployeeRecordsBox.setText("Employee Name: #########\r\nEmployee #: #########\r\n\r\nAnnual Salary: #########\r\nStart Date: #########\r\n----------------------------------------------------------------------------------------\r\nEmployee Name: #########\r\nEmployee #: #########\r\n\r\nAnnual Salary: #########\r\nStart Date: #########\r\n----------------------------------------------------------------------------------------");
		listEmployeeRecordsBox.setLineWrap(true);
		listEmployeeRecordsBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		listEmployeeRecordsBox.setEditable(false);
		contentPane.add(listEmployeeRecordsBox);

		scrollListEmployeeRecordsBox = new JScrollPane(listEmployeeRecordsBox);
		scrollListEmployeeRecordsBox.setBounds(10, 286, 468, 354);
		scrollListEmployeeRecordsBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollListEmployeeRecordsBox);
		
		debugConsole();
	}
	
	private void resetActionPerformed(java.awt.event.ActionEvent evt) {
		int confirmOption = JOptionPane.showConfirmDialog (null, "All data will be lost!\n\nWish to continue?","RESET WARNING", JOptionPane.YES_NO_OPTION);

		if (confirmOption == JOptionPane.YES_OPTION) {
			employeeRecordsData.clear();
		}
		
		debugConsole();
	}
	
	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		
		debugConsole();
	}
	
	private void removeActionPerformed(java.awt.event.ActionEvent evt) {
		
		debugConsole();
	}
	
	private void updateActionPerformed(java.awt.event.ActionEvent evt) {
		
		debugConsole();
	}
	
	private void sortActionPerformed(java.awt.event.ActionEvent evt) {
		
		debugConsole();
	}
	
	public boolean validInput() {
		return false;
	}
	
	public void arrayInitialize() { // Only needed if a 2D array is being done
		
	}
	
	public void arrayWriteOut() { // Produces text in the GUI
		
	}
	
	public void errorResponse() {
		
	}
	
	public void debugConsole() {
		if (allowDebug) {
			System.out.println("errorCode -> " + errorCode);
			
			int nextLine = 0;
			
			for (int i = 0; i < employeeRecordsData.size(); i++) {
				System.out.print(employeeRecordsData.get(i) + " // ");
				
				if (i - 4 == nextLine) {
					nextLine = i;
					System.out.println();
				}
			}

			System.out.println("------------------------------------------------------------------------------------------");
		}
	}
}

/**	Developer Notes:
 * 
 * Code structure is similar to that of A11 (Evan's version).
 * 
 * [Error Code Info]
 * 
 * [ArrayList Design]
 *  ArrayList data order is as follows...
 *  1 --> firstNameIn + lastNameIn
 *  2 --> employeeIDNumber
 *  3 --> annualSalaryIn
 *  4 --> startDateIn
 * 
 */
