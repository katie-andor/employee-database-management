package employeeSignIn;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class employeeLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JLabel lblNewLabel_2;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeLogin frame = new employeeLogin();
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
	public employeeLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//title label
		JLabel lblNewLabel = new JLabel("Employee Login Page");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 266, 30);
		contentPane.add(lblNewLabel);
		
		//username label
		JLabel lblNewLabel_1 = new JLabel("username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(34, 50, 87, 30);
		contentPane.add(lblNewLabel_1);
		
		//password label
				lblNewLabel_2 = new JLabel("password");
				lblNewLabel_2.setForeground(Color.WHITE);
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel_2.setBounds(34, 119, 87, 30);
				contentPane.add(lblNewLabel_2);
		
		//username text field
		user = new JTextField();
		user.setBounds(34, 81, 181, 30);
		contentPane.add(user);
		user.setColumns(10);
		
		//password text field
		pass = new JPasswordField();
		pass.setBounds(34, 151, 181, 30);
		contentPane.add(pass);
		
		//login button
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//connecting to the database
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost/tblogin?serverTimezone=EST", "root", "database28");
					Statement stmt = con.createStatement();
					//SQL query
					String sql = "SELECT * FROM tblogin.EmployeeTable WHERE username='"+user.getText()+"' and pass='"+pass.getText().toString()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					//checking whether login is correct
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login Successful");
						employeePage EP = new employeePage();
						EP.show();
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "Incorrect Info");
						con.close();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
		}});
		btnNewButton.setBackground(new Color(255, 128, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(34, 210, 85, 21);
		contentPane.add(btnNewButton);
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
