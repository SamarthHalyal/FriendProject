package Database;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Demo extends JApplet {
	private JTextField txtEmployeeName;
	private JTextField txtDesignation;
	private Connection conn;
	//private JTable table;

	/**
	 * Create the applet.
	 */
	public void init() {
		setSize(500,300);
	}
	
	public Demo() {
		getContentPane().setLayout(null);
		
		JLabel lblEmployeeName = new JLabel("Employee Name :");
		lblEmployeeName.setBounds(24, 49, 145, 16);
		getContentPane().add(lblEmployeeName);
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.setBounds(187, 44, 223, 26);
		getContentPane().add(txtEmployeeName);
		txtEmployeeName.setColumns(10);
		
		JLabel lblEmployeeDesignation = new JLabel("Employee Designation :");
		lblEmployeeDesignation.setBounds(24, 100, 155, 16);
		getContentPane().add(lblEmployeeDesignation);
		
		txtDesignation = new JTextField();
		txtDesignation.setBounds(187, 95, 223, 26);
		getContentPane().add(txtDesignation);
		txtDesignation.setColumns(10);
		
		JLabel lblManager = new JLabel("Manager :");
		lblManager.setBounds(24, 152, 101, 16);
		getContentPane().add(lblManager);
		
		JComboBox managerlist = new JComboBox();
		managerlist.setBounds(187, 148, 223, 27);
		try
		{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","toor");
		Statement statement = conn.createStatement();
		ResultSet set = statement.executeQuery("select ReportMang from Managers;");
		while(set.next()){
			managerlist.addItem(set.getString("ReportMang"));
		}
		conn.close();
		}catch(Exception e2){
			System.out.println("Error in database operation 1");
		}
		getContentPane().add(managerlist);
		
		JButton submit = new JButton("SUBMIT");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtEmployeeName.getText();
				String desg = txtDesignation.getText();
				String sel = managerlist.getSelectedItem().toString();
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","toor");
					Statement statement = conn.createStatement();
					String sql = "insert into Employees (EmpName,EmpDesg,ReportMang) values(\""+name.toString()+"\",\""+desg.toString()+"\",\""+sel.toString()+"\");";
					if(name.equals("") || desg.equals("")){}
					else
						statement.executeUpdate(sql);
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error in database operation 2");
				}
			}
		});
		submit.setBounds(24, 238, 117, 29);
		getContentPane().add(submit);
		
		JButton reset = new JButton("RESET");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmployeeName.setText("");
				txtDesignation.setText("");
			}
		});
		reset.setBounds(164, 238, 117, 29);
		getContentPane().add(reset);
		
		JButton btnShow = new JButton("SHOW");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopUP p = new PopUP();
			}
		});
		btnShow.setBounds(293, 238, 117, 29);
		getContentPane().add(btnShow);

	}
}
