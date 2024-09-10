package employeeSignIn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class employeeViewOrders extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeViewOrders frame = new employeeViewOrders();
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
	public employeeViewOrders() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 513);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnShow = new JButton("Show Data");
		btnShow.setForeground(new Color(255, 255, 255));
		btnShow.setBackground(new Color(128, 128, 255));
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost/tblogin?serverTimezone=EST", "root", "database28");
					Statement stmt = con.createStatement();
					String sql = "SELECT o.orderID, o.customerID, o.orderDate, o.couponCode, o.status\r\n"
							+ "FROM tblogin.orders o\r\n"
							+ "JOIN tblogin.CustomerTable c ON o.customerID = c.customerID\r\n"
							+ "ORDER BY c.fullName";
					ResultSet rs = stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					
					for(int i=0; i<cols; i++)
						colName[i] = rsmd.getColumnName(i + 1);
					model.setColumnIdentifiers(colName);
					String orderID, customerID, orderDate, couponCode, status;
					while(rs.next()) {
						orderID=rs.getString(1);
						customerID=rs.getString(2);
						orderDate=rs.getString(3);
						couponCode=rs.getString(4);
						status=rs.getString(5);
						String[] row = {orderID, customerID, orderDate, couponCode, status};
						model.addRow(row);
					}
					stmt.close();
					con.close();
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShow.setBounds(159, 10, 128, 52);
		contentPane.add(btnShow);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(128, 128, 255));
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				employeePage EP = new employeePage();
				EP.show();
				dispose();
			}
		});
		btnClear.setBounds(320, 10, 128, 52);
		contentPane.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 603, 394);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(128, 128, 255));
		scrollPane.setViewportView(table);
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
