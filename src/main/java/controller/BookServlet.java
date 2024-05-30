package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import service.BookService;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet(description = "This is for Book related operations", urlPatterns = { "/books" })
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method != null) {
			doDelete(request, response);
		}
		try {

			

						List<Book> books = bookService.getAllBooks();

						request.setAttribute("books", books);

						RequestDispatcher rd = request.getRequestDispatcher("/bims_home.jsp");
						rd.forward(request, response);
						
					
				
			

			

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("_method");

		if (method != null && method.equalsIgnoreCase("DELETE")) {
			doDelete(request, response);
		} else if (method != null && method.equalsIgnoreCase("PUT")) {
			doPut(request, response);
		} else {
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			Double price = Double.parseDouble(request.getParameter("price"));
			Book book = new Book(title, author, price);
			PrintWriter out = response.getWriter();
			try {
				if (bookService.addNewBook(book)) {
					response.sendRedirect("/SampleServletProject/books");
				} else {

					out.print("<H1> Book Not addded </H1>");
					return;
				}
			} catch (ClassNotFoundException | SQLException e) {
				out.print("<H1> Book Not addded </H1>");
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oldTitle = request.getParameter("oldTitle");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		Double price = Double.parseDouble(request.getParameter("price"));
		Book book = new Book(title, author, price);
		PrintWriter out = response.getWriter();
		try {
			if (bookService.updateBook(book, oldTitle)) {
				response.sendRedirect("/SampleServletProject/books");
			} else {

				out.print("<H1> Book Not Update </H1>");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			out.print("<H1> Book Not addded </H1>");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("do delete called");
		String title = request.getParameter("title");
		System.out.println("Title = " + title);
		Book book = new Book();
		book.setTitle(title);

		try {
			if (bookService.deleteBook(book)) {

				System.out.println("data deleted successfully");
				response.sendRedirect("/SampleServletProject/books");
			} else {
				System.out.println("Book not deleted");
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

}
