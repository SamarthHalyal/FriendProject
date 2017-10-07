package Database;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PopUP extends JFrame{
	public PopUP() {
		String[][] data = null;
		data = new String[10][4];
		

		String[] column_headers = {"Name","Designation","Manager"};
		
		try{
			// 1. Create a connection to database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","toor");
			// 2. Create  a statement
			Statement statement = conn.createStatement();
			// 3. Execute SQL query
			ResultSet set = statement.executeQuery("select * from Employees");
			// 4. Process the result set
			int i=0;
			while(set.next()) {
				data[i][0] = set.getString("EmpName").toString();
				data[i][1] = set.getString("EmpDesg").toString();
				data[i][2] = set.getString("ReportMang").toString();
				i++;
			}
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error in POPUP");
		}

		JTable table = new JTable(data,column_headers);
		table.setBounds(24, 346, 442, 231);
		JScrollPane sp = new JScrollPane(table);
		getContentPane().add(sp);
		
		this.setSize(500, 300);
		this.setVisible(true);
	}
	
	public static void main(String arg[]){
		
	}
}
