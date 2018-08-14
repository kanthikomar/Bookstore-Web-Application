package net.codejava.javaee.bookstore;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/addBook":
				insertBook(request, response);
				break;
			case "/purchaseBook":
				purchaseBook(request, response);
				break;
			case "/generateReports":
				generateReports(request, response);
				break;
			case "/bill":
				generateBill(request,response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateBook(request, response);
				break;
			case "/generateSaleReport":
				generateSaleReport(request, response);
				break;
			case "/generateCustomerReport":
				generateCustomerReport(request, response);
				break;
			default:
				purchaseBook(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void generateSaleReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String bookstorename = request.getParameter("bookstorename");
		List<Book> listSale = bookDAO.generateSaleReport(bookstorename);
		 request.setAttribute("listSale", listSale);
			RequestDispatcher dispatcher = request.getRequestDispatcher("SaleReport.jsp");
			dispatcher.forward(request, response);
	}
	private void generateCustomerReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String bookstorename = request.getParameter("bookstorename");
		List<Book> listCustomer = bookDAO.generateCustomerReport(bookstorename);
		 request.setAttribute("listCustomer", listCustomer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerReport.jsp");
			dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String isbn = request.getParameter("id");
		Book existingBook = bookDAO.getBook(isbn);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);

	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String isbn = request.getParameter("isbn");
		String bookName = request.getParameter("bookname");
		String publisher = request.getParameter("publisher");
		String author[] = request.getParameter("author").split(",");
		float price = Float.parseFloat(request.getParameter("price"));
		int edition = Integer.parseInt(request.getParameter("edition"));

		Book newBook = new Book(isbn,bookName,publisher, price, edition,author);
		bookDAO.insertBook(newBook);
		response.sendRedirect("purchaseBook");
	}
	
	private void purchaseBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		List<Book> listBook = bookDAO.listAllBooks();
        request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookPurchase.jsp");
		dispatcher.forward(request, response);
		
	}
	private void generateReports(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("GenerateReports.jsp");
		dispatcher.forward(request, response);
		
//		Book newBook = new Book(bookName,publisher, price, edition);
//		bookDAO.insertBook(newBook);
//		response.sendRedirect("list");
	}
	private void generateBill(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String bookName = request.getParameter("bookName");
		String isbn = request.getParameter("isbn");
		int quantity = Integer.parseInt(request.getParameter("qty"));
		float price = bookDAO.fetchPrice(bookName,isbn,quantity);
		float totalprice = price * quantity;
		request.setAttribute("bookName", bookName);
		request.setAttribute("isbn", isbn);
		request.setAttribute("quantity", quantity);
		request.setAttribute("price", totalprice);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Bill.jsp");
		dispatcher.forward(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String isbn = request.getParameter("isbn");
		String bookname = request.getParameter("bookName");
		String publisher = request.getParameter("publisher");
		float price = Float.parseFloat(request.getParameter("price"));
		int edition = Integer.parseInt(request.getParameter("edition"));
		Book book = new Book(isbn, bookname, publisher, price,edition);
		bookDAO.updateBook(book);
		response.sendRedirect("purchaseBook");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String id = request.getParameter("id");

		Book book = new Book(id);
		bookDAO.deleteBook(book);
		response.sendRedirect("purchaseBook");
	}

}
