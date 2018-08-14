package net.codejava.javaee.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author www.codejava.net
 *
 */
public class BookDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertBook(Book book) throws SQLException {
		String sql = "INSERT INTO books (isbn, bookName, publisher, price, edition) VALUES (?, ?, ?, ?,?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getisbn());
		statement.setString(2, book.getbookName());
		statement.setString(3, book.getpublisher());
		statement.setFloat(4, book.getPrice());
		statement.setInt(5, book.getEdition());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		for(int i=0;i<book.authors.length;i++){
			sql = "Insert into author values(?,?)";
			statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, book.getisbn());
			statement.setString(2, book.authors[i]);
			rowInserted = statement.executeUpdate() > 0;
		}
		disconnect();
		return rowInserted;
	}
	
	
	public List<Book> listAllBooks() throws SQLException {
		List<Book> listBook = new ArrayList<>();
		
		String sql = "SELECT B.isbn,B.publisher,B.bookname,B.edition,B.price,A.author FROM books B,author A where B.isbn = A.isbn";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String isbn = resultSet.getString("isbn");
			String publisher = resultSet.getString("publisher");
			String bookname = resultSet.getString("bookname");
			int edition = resultSet.getInt("edition");
			float price = resultSet.getFloat("price");
			String authors = resultSet.getString("author");
			
			Book book = new Book(isbn, bookname, publisher,price, edition, authors);
			listBook.add(book);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listBook;
	}
	public List<Book> generateSaleReport(String bookstorename) throws SQLException {
		List<Book> listBook = new ArrayList<>();
		
		String sql = "select b.bookname, s.saledate, s.selling_price, sum(s.selling_price) as TOTAL_SALES from books b, sale s, bookstores bs where bs.bookstore_name = '"+bookstorename+"' and bs.storeid = s.storeid and s.isbn = b.isbn group by s.saledate;";		
		
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
//		statement.setString(1, bookstorename);
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String bookname = resultSet.getString("bookname");
			String saledate = resultSet.getString("saledate");
			float price = resultSet.getFloat("selling_price");
			float totalSP = resultSet.getFloat("TOTAL_SALES");
			
			Book book = new Book(bookname, saledate,price, totalSP);
			listBook.add(book);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listBook;
	}
	public List<Book> generateCustomerReport(String bookstorename) throws SQLException {
		List<Book> listBook = new ArrayList<>();
		
		String sql = "select s.saledate, count(s.customersid) as NO_OF_CUSTOMERS from sale s, bookstores bs where bs.bookstore_name = '"+bookstorename+"' and bs.storeid = s.storeid group by month(s.saledate);";		
		
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
//		statement.setString(1, bookstorename);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			String saledate = resultSet.getString("saledate");
			int count = resultSet.getInt("NO_OF_CUSTOMERS");			
			Book book = new Book(saledate,count);
			listBook.add(book);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listBook;
	}
	public float fetchPrice(String bookName, String isbn, int quantity) throws SQLException{
		String sql = "SELECT price FROM books where isbn="+isbn;
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		float price=0;
		while (resultSet.next()) {
			price = resultSet.getFloat("price");
		}
		return price;
		
	}
	
	public boolean deleteBook(Book book) throws SQLException {
		String sql = "DELETE FROM author where isbn = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getisbn());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		
		sql = "DELETE FROM books where isbn = ?";
		
		connect();
		
		statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getisbn());
		
		rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateBook(Book book) throws SQLException {
		String sql = "UPDATE books SET BookName = ?, publisher=?, price = ?, edition = ?";
		sql += " WHERE isbn = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getbookName());
		statement.setString(2, book.getpublisher());
		statement.setFloat(3, book.getPrice());
		statement.setInt(4, book.getEdition());
		statement.setString(5, book.getisbn());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
//	
	public Book getBook(String isbn) throws SQLException {
		Book book = null;
		String sql = "SELECT * FROM books WHERE isbn = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, isbn);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String bookname = resultSet.getString("bookname");
			String publisher = resultSet.getString("publisher");
			float price = resultSet.getFloat("price");
			int edition = resultSet.getInt("edition");
			
			book = new Book(isbn, bookname, publisher, price, edition);
		}
		
		resultSet.close();
		statement.close();
		
		return book;
	}
//	public boolean purchase_books(Book book) throws SQLException {
//		String sql = "insert into sale (isbn, storeID, customerID, saleDate, discount, sellingPrice, quantity) values(?,?,?,?,?,?,?) ";
//		
//		connect();
//		
//		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
//		statement.setString(1, book.getbookName());
//		statement.setString(2, book.getpublisher());
//		statement.setFloat(3, book.getPrice());
//		statement.setInt(4, book.getId());
//		
//		boolean rowUpdated = statement.executeUpdate() > 0;
//		statement.close();
//		disconnect();
//		return rowUpdated;		
//	}
	
}
