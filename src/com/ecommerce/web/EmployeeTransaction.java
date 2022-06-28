package com.ecommerce.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class EmployeeTransaction
 */
@WebServlet("/EmployeeTransaction")
public class EmployeeTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String INSERT_EMPLOYEE_QUERY = "INSERT INTO EMPLOYEE (empId, name) VALUES(?,?)";
	public static String INSERT_EMPLOYEE_ADDRESS_QUERY = "INSERT INTO ADDRESS(empId,address,city,country) VALUES(?,?,?,?)";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		
		PrintWriter out = response.getWriter();
		
		//load data from config
		Properties properties = new Properties();
		properties.load(getServletContext().getResourceAsStream("/config.properties"));
		
		//get connection
		DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
		
		Connection connection = conn.getConnection();
		//make autocommit false
		
		connection.setAutoCommit(false); 
		
		insertEmployeeData(connection, 3, "Selva");
		insertEmployeeAddressData(connection, 1, "US,California", "Texas", "United States");
		
		connection.commit();
		
		out.println("<html><body>");
		out.println(" Data inserted successfully !!!");
		out.println("</body></html>");
		
		//close connection
		conn.closeConnection();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static void insertEmployeeData(Connection conn, int id, String name) throws SQLException {
		
		PreparedStatement pstm = conn.prepareStatement(INSERT_EMPLOYEE_QUERY);
		pstm.setInt(1, id);
		pstm.setString(2, name);
		int rowNo = pstm.executeUpdate();
		System.out.println(rowNo+ "Rows Affected !!! ");
		pstm.close();
	}
	
	public static void insertEmployeeAddressData(Connection conn, int id, String address, String city, String country) throws SQLException {
		
		PreparedStatement pstm = conn.prepareStatement(INSERT_EMPLOYEE_ADDRESS_QUERY);
		pstm.setInt(1, id);
		pstm.setString(2, address);
		pstm.setString(3, city);
		pstm.setString(4, country);
		int rowNo = pstm.executeUpdate();
		System.out.println(rowNo+ "Rows Affected !!! ");
		pstm.close();
	}
}
