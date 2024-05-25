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
}
