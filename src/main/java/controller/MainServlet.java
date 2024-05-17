package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DBConnection;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/users")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("psw");
		String name = request.getParameter("name");
		int rows = -1;

		DBConnection db = new DBConnection();

		try {
			Connection conn = db.getConnection();
//			String query = "insert into users(email,password,name) values("+email+","+password+","+name+")";
			PreparedStatement ps = conn.prepareStatement("select * from users where email = ? and password = ?");

			ps.setString(1, email);
			ps.setString(2, password);
		

			ResultSet rs = ps.executeQuery();  
			
			boolean data = rs.next();
			
			response.setContentType("text/html;charset=UTF-8");

			PrintWriter out = response.getWriter();
			
			
			if(data) {
				System.out.println("Login Successful");  
				System.out.println("Email = "+ rs.getString(1)+" Password = "+rs.getString(2)+" Name = "+rs.getString("name"));  
				out.println("<h1> Login Successful </h1>");
			}else {
				out.println("<h1> Login Failed </h1>");
			}
			
			

		} catch (ClassNotFoundException e) {

			System.out.println("JDBC Class Not found in your project");

		} catch (SQLException e) {

			System.out.println("Db Connection Problem occurs in project");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("psw");
		String name = request.getParameter("name");
		int rows = -1;

		DBConnection db = new DBConnection();

		try {
			Connection conn = db.getConnection();
//			String query = "insert into users(email,password,name) values("+email+","+password+","+name+")";
			PreparedStatement ps = conn.prepareStatement("insert into users(email,password,name) values(?,?,?)");

			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, name);

			rows = ps.executeUpdate();

		} catch (ClassNotFoundException e) {

			System.out.println("JDBC Class Not found in your project");

		} catch (SQLException e) {

			System.out.println("Db Connection Problem occurs in project");
		}

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		if (rows > 0) {
			System.out.println("User Registered Successfully");
			out.println("<h1> User Registered Successfully </h1>");
		} else {
			System.out.println("User registration failed");
			out.println("<h1> User Registered Failed </h1>");
		}

	}

}
