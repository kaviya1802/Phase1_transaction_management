package com.ecommerce.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public Connection connection;
	
	public DBConnection(String url, String username, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url, username, password);
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
}
