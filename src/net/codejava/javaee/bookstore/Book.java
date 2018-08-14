package net.codejava.javaee.bookstore;

/**
 * Book.java
 * This is a model class represents a book entity
 * @author www.codejava.net
 *
 */
public class Book {
	protected String isbn;
	protected String bookName;
	protected String publisher;
	protected int edition;
	protected float price;
	protected String author;
	protected String authors[];
	protected String saledate;
	protected float totalprice;
	protected int customercount;
	public Book() {
	}

	public Book(String isbn) {
		this.isbn = isbn;
	}

//	public Book(int isbn, String bookName, String publisher, float price, long edition) {
//		this(bookName, publisher, price, edition);
//		this.isbn = isbn;
//	}
	
	public Book(String isbn, String bookName, String publisher, float price, int edition) {
		this.isbn = isbn;
		this.bookName = bookName;
		this.publisher = publisher;
		this.price = price;
		this.edition = edition;
	}
	public Book(String bookName, String publisher, float price, int edition) {
		this.bookName = bookName;
		this.publisher = publisher;
		this.price = price;
		this.edition = edition;
	}
	public Book(String isbn, String bookName, String publisher, float price, int edition, String author) {
		this.isbn = isbn;
		this.bookName = bookName;
		this.publisher = publisher;
		this.price = price;
		this.edition = edition;
		this.author = author;
	}
	public Book(String isbn, String bookName, String publisher, float price, int edition, String authors[]) {
		this.isbn = isbn;
		this.bookName = bookName;
		this.publisher = publisher;
		this.price = price;
		this.edition = edition;
		this.authors = authors;
	}
	public Book(String bookname, String saledate, float price, float totalprice){
		this.bookName = bookname;
		this.saledate = saledate;
		this.price = price;
		this.totalprice = totalprice;		
	}
	public Book(String saledate, int customercount){
		this.saledate = saledate;
		this.customercount = customercount;
	}
	
	public String getsaledate() {
		return saledate;
	}
	public void setsaledate(String saledate) {
		this.saledate = saledate;
	}
	public String getisbn() {
		return isbn;
	}
	public String getAuthors() {
		return author;
	}
	public void setAuthors(String author) {
		this.author = author;
	}
	public void setisbn(String isbn) {
		this.isbn = isbn;
	}
	
	public int getEdition() {
		return edition;
	}

	public void setEdition(int isbn) {
		this.edition = isbn;
	}

	public String getbookName() {
		return bookName;
	}

	public void setbookName(String bookName) {
		this.bookName = bookName;
	}

	public String getpublisher() {
		return publisher;
	}

	public void setpublisher(String author) {
		this.publisher = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	public float gettotalprice() {
		return totalprice;
	}

	public void settotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	public void setcustomercount(int customercount){
		this.customercount = customercount;
	}
	public int getcustomercount(){
		return this.customercount;
	}
}
