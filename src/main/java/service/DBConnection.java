package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	public DBConnection() {
		super();
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		return DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/practice","root","root");  
	}
}
