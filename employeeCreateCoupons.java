package employeeSignIn;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class employeeCreateCoupons extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField couponCode;
	private JTextField discount;
	private JTextField expiryDate;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeCreateCoupons frame = new employeeCreateCoupons();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Database.connect();
		setupClosingDBConnection();
	}

	/**
	 * Create the frame.
	 */
	public employeeCreateCoupons() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 502);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter coupon code:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(154, 91, 289, 28);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		contentPane.add(lblNewLabel);
		
		couponCode = new JTextField();
		couponCode.setBounds(189, 125, 227, 28);
		contentPane.add(couponCode);
		couponCode.setColumns(10);
		
		JLabel lblEnterItemName = new JLabel("Enter discount percentage:");
		lblEnterItemName.setForeground(new Color(255, 255, 255));
		lblEnterItemName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterItemName.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblEnterItemName.setBounds(165, 171, 289, 28);
		contentPane.add(lblEnterItemName);
		
		discount = new JTextField();
		discount.setColumns(10);
		discount.setBounds(189, 209, 227, 28);
		contentPane.add(discount);
		
		JLabel lblEnterItemName_1 = new JLabel("Enter expiry date:");
		lblEnterItemName_1.setForeground(new Color(255, 255, 255));
		lblEnterItemName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterItemName_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblEnterItemName_1.setBounds(199, 247, 204, 28);
		contentPane.add(lblEnterItemName_1);
		
		expiryDate = new JTextField();
		expiryDate.setColumns(10);
		expiryDate.setBounds(189, 285, 227, 28);
		contentPane.add(expiryDate);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get values from input fields
		        String couponCodeValue = couponCode.getText(); // Retrieve text from itemID text field
		        String discountValue = discount.getText(); // Retrieve text from itemName text field
		        String expiryDateValue = expiryDate.getText(); // Retrieve text from price text field
		        
		        // Call addItem() with the values from the input fields
		        createCoupon(couponCodeValue, discountValue, expiryDateValue);
		        employeePage EP = new employeePage();
				EP.show();
				dispose();
			}
		});
		btnNewButton.setBounds(249, 352, 113, 41);
		contentPane.add(btnNewButton);
	}
	
	public static void createCoupon(String couponCode, String discount, String expiryDate) {
	    try {
	        // Load the MySQL JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // Connect to the MySQL database
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost/tblogin?serverTimezone=EST", 
	            "root", 
	            "database28"
	        );
	        
	        // Create a statement
	        Statement stmt = con.createStatement();
	        
	        // Prepare the SQL statement
	        String sql = "INSERT INTO tblogin.coupons (couponCode, discount, expiryDate) " +
	                     "VALUES ('" + couponCode + "', '" + discount + "', '" + expiryDate + "')";
	        
	        // Execute the statement
	        int rowsAffected = stmt.executeUpdate(sql);
	        
	        // Check the result
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Item Added Successfully");
	        } else {
	            JOptionPane.showMessageDialog(null, "Error: Item Could Not Be Added");
	        }
	        
	        // Close the connection
	        con.close();
	        
	    } catch (Exception e) {
	        // Handle exceptions
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
