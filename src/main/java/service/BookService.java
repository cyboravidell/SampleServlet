package service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;

public class BookService {

	public List<Book> getAllBooks() throws ClassNotFoundException, SQLException {

		List<Book> books = new ArrayList<Book>();
		Connection conn = DBConnection.getConnection();
//		String query = "insert into users(email,password,name) values("+email+","+password+","+name+")";

		PreparedStatement ps = conn.prepareStatement("select * from book");

		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			Book book = new Book();
		
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setPrice(rs.getDouble("price"));
			
			books.add(book);
			
			System.out.println(book);
		}

		return books;

	
}

	public boolean addNewBook(Book book) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();

		PreparedStatement ps = conn.prepareStatement("insert into book (title,author,price) values(?,?,?)");
		
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setDouble(3, book.getPrice());
		
		return ps.executeUpdate()>0;
	}
	
	public boolean deleteBook(Book book) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();

		PreparedStatement ps = conn.prepareStatement("delete from book where title = ?");
		
		ps.setString(1, book.getTitle());
		
		
		return ps.executeUpdate()>0;
	}

	public boolean updateBook(Book book, String oldTitle) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();

		PreparedStatement ps = conn.prepareStatement("update book set title=?, author=?, price=? where title = ?");
		
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setDouble(3, book.getPrice());
		ps.setString(4, oldTitle);
		
		
		return ps.executeUpdate()>0;
		
	}
	
	public List<Book> searchBook( String title) throws ClassNotFoundException, SQLException {
	    Connection conn = DBConnection.getConnection();

	    // Corrected SQL syntax with proper comma separation and spacing
	  
	    List<Book> books = new ArrayList<Book>();

//		String query = "insert into users(email,password,name) values("+email+","+password+","+name+")";

		PreparedStatement ps = conn.prepareStatement("select * from book where title like ? ");
       ps.setString(1,title+'%');
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			Book book = new Book();
		
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setPrice(rs.getDouble("price"));
			
			books.add(book);
			
			//System.out.println(book);
		}

		return books;
	

}
}
