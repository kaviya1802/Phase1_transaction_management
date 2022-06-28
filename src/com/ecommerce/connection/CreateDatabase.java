package com.ecommerce.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateDatabase
 */
@WebServlet("/CreateDatabase")
public class CreateDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CreateDatabase
		
		try {
			PrintWriter out = response.getWriter();
			
			//load data from config.properties
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/config.properties"));
			
			//Get connection
			
			DBConnection connection = new DBConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			
			//create statement
			Statement stm = connection.getConnection().createStatement();
			
			//execute query
			String query = "CREATE DATABASE BankService";
			int result = stm.executeUpdate(query);
			
			//Print response
			out.println("<html></body>");
			if(result>0) {
				out.println("Query OK, " + result+ " rows affected");
			}else {
				out.println("</body></html>");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
