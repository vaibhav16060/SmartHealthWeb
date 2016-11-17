package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class DataConnector {
	
	static Connection con = null;
	
	protected void ConnectToDataBase(){
		
		try {
			if(con == null){
				DataSource dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + "jdbc/db");
				con = dataSource.getConnection();
				/*Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarthealthdb", "root", "dehradun123");*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getContext(){
		
		return con;
	}
}
