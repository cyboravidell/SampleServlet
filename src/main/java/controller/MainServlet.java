package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.UserAlreadyExistException;
import model.User;
import service.UserService;

public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private String owner;
    private String chairman;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Calls the init method of HttpServlet to perform default initialization
        this.owner = config.getInitParameter("owner"); // Retrieves the "owner" parameter from web.xml
    }

    public MainServlet() {
        super();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("psw"));

        PrintWriter out = response.getWriter();
        ServletContext servletContext = getServletConfig().getServletContext();
        // Use the owner parameter

        chairman = servletContext.getInitParameter("chairman");
        System.out.println("Owner: " + this.owner);
        System.out.println("Chairman: " + this.chairman);
        
        //Now We are setting custom ServletContext attribute
        
        servletContext.setAttribute("user", new User("ravi@gmail.com", "Ravi1234", "Ravi Kumar"));
        
        try {
            if (userService.userLogin(user)) {
            	
            	response.sendRedirect("/SampleServletProject/books");
//                RequestDispatcher rd = request.getRequestDispatcher("/bims_home.html");
//                rd.forward(request, response);
            } else {
                out.println("<h1> Login Failed </h1>");
                out.println("<h1> "+ (User)servletContext.getAttribute("user")+" </h1>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h1> Login Failed </h1>");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();

        String email = request.getParameter("email");
        user.setEmail(email);

        String pass = request.getParameter("psw");
        user.setPassword(pass);

        String name = request.getParameter("name");
        user.setName(name);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        int rows;
        try {
            rows = userService.userRegister(user);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not loaded");
            e.printStackTrace();
            return;
        } catch (SQLException e) {
            System.out.println("either connection problem occurs or Query execution Failed");
            e.printStackTrace();
            return;
        } catch (UserAlreadyExistException e) {
            System.out.println(e.getMessage());
            out.println("<h1> " + e.getMessage() + " </h1>");
            return;
        }

        if (rows > 0) {
            System.out.println("User Registered Successfully");
            out.println("<h1> User Registered Successfully </h1>");
        } else {
            System.out.println("User registration failed");
            out.println("<h1> User Registration Failed </h1>");
        }
    }
}
