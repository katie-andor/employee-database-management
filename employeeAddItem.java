package employeeSignIn;
import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class employeeAddItem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField itemID;
	private JTextField itemName;
	private JTextField price;
	private JLabel lblEnterItemName_2;
	private JTextField leftInStock;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeAddItem frame = new employeeAddItem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//Closes the Database Securely If The Window Is Closed
		Database.connect();
		setupClosingDBConnection();
	}

	/**
	 * Create the frame.
	 */
	public employeeAddItem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 502);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//enter item number label
		JLabel lblNewLabel = new JLabel("Enter Item Number:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(206, 69, 204, 28);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		contentPane.add(lblNewLabel);
		
		//text field takes data in
		itemID = new JTextField();
		itemID.setBounds(191, 107, 227, 28);
		contentPane.add(itemID);
		itemID.setColumns(10);
		
		//enter item name label
		JLabel lblEnterItemName = new JLabel("Enter Item Name:");
		lblEnterItemName.setForeground(new Color(255, 255, 255));
		lblEnterItemName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterItemName.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblEnterItemName.setBounds(206, 162, 204, 28);
		contentPane.add(lblEnterItemName);
		
		//text field takes data in
		itemName = new JTextField();
		itemName.setColumns(10);
		itemName.setBounds(191, 200, 227, 28);
		contentPane.add(itemName);
		
		//enter item price label
		JLabel lblEnterItemName_1 = new JLabel("Enter Item Price:");
		lblEnterItemName_1.setForeground(new Color(255, 255, 255));
		lblEnterItemName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterItemName_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblEnterItemName_1.setBounds(206, 248, 204, 28);
		contentPane.add(lblEnterItemName_1);
		
		//text field takes data in
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(191, 286, 227, 28);
		contentPane.add(price);
		
		//enter leftInStock label
		lblEnterItemName_2 = new JLabel("Enter How Many Left in Stock:");
		lblEnterItemName_2.setBackground(new Color(255, 255, 255));
		lblEnterItemName_2.setForeground(new Color(255, 255, 255));
		lblEnterItemName_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterItemName_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblEnterItemName_2.setBounds(147, 324, 327, 28);
		contentPane.add(lblEnterItemName_2);
		
		//text field takes in data
		leftInStock = new JTextField();
		leftInStock.setColumns(10);
		leftInStock.setBounds(191, 362, 227, 28);
		contentPane.add(leftInStock);
		
		//submit button
		btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get values from input fields
		        String itemIDValue = itemID.getText(); // gets text from itemID text field
		        String itemNameValue = itemName.getText(); // gets text from itemName text field
		        String priceValue = price.getText(); // gets text from price text field
		        String leftInStockValue = leftInStock.getText(); // gets text from leftInStock text field
		        
		        // calls addItem() with the values from the input fields
		        addItem(itemIDValue, itemNameValue, priceValue, leftInStockValue);
		        employeePage EP = new employeePage();
				EP.show();
				dispose();
			}
		});
		btnNewButton.setBounds(244, 406, 121, 49);
		contentPane.add(btnNewButton);
	}
	
	public static void addItem(String itemID, String itemName, String price, String leftInStock) {
	    try {
	    	//connecting to the database
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost/tblogin?serverTimezone=EST", "root", "database28");
	        Statement stmt = con.createStatement();  
	        //SQL query
	        String sql = "INSERT INTO tblogin.store_items (itemID, itemName, price, leftInStock) " +
	                     "VALUES ('" + itemID + "', '" + itemName + "', '" + price + "', '" + leftInStock + "')";
	        // execute statement
	        int rowsAffected = stmt.executeUpdate(sql);
	        // Check the result
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Item Added Successfully");
	        } else {
	            JOptionPane.showMessageDialog(null, "Error: Item Could Not Be Added");
	        }
	        con.close();
	        
	    } catch (Exception e) {
	        System.out.println("An error occurred: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	public static void setupClosingDBConnection() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            try { Database.connection.close(); System.out.println("Application Closed - DB Connection Closed");
				} catch (SQLException e) { e.printStackTrace(); }
	        }
	    }, "Shutdown-thread"));
	}
}
