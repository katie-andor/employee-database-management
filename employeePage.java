package employeeSignIn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class employeePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeePage frame = new employeePage();
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
	public employeePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//view order button
		JButton btnNewButton = new JButton("View Orders");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//opens the view orders JFrame
				employeeViewOrders EVO = new employeeViewOrders();
				EVO.show();
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton.setBounds(113, 236, 393, 69);
		contentPane.add(btnNewButton);
		
		//view customer accounts button
		JButton btnNewButton_1 = new JButton("View Customer Accounts");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//opens the view customer accounts JFrame and closes the current window
				employeeViewCustomerAccounts EVCA = new employeeViewCustomerAccounts();
				EVCA.show();
				dispose();		
				}
			});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton_1.setBackground(new Color(128, 128, 255));
		btnNewButton_1.setBounds(113, 150, 393, 69);
		contentPane.add(btnNewButton_1);
		
		//create coupon button
		JButton btnCreateCoupon = new JButton("Create Coupon");
		btnCreateCoupon.setForeground(new Color(255, 255, 255));
		btnCreateCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//opens the create coupon JFrame and closes the current window
				employeeCreateCoupons ECC = new employeeCreateCoupons();
				ECC.show();
				dispose();	
			}
		});
		btnCreateCoupon.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnCreateCoupon.setBackground(new Color(128, 128, 255));
		btnCreateCoupon.setBounds(113, 324, 393, 69);
		contentPane.add(btnCreateCoupon);
		
		//add item button
		JButton btnNewButton_1_1 = new JButton("Add Item");
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//opens the add item JFrame and closes the current window
				employeeAddItem EAI = new employeeAddItem();
				EAI.show();
				dispose();		
				}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton_1_1.setBackground(new Color(128, 128, 255));
		btnNewButton_1_1.setBounds(113, 71, 393, 69);
		contentPane.add(btnNewButton_1_1);
		
		
		
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
