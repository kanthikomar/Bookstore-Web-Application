<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Purchase Book</title>
</head>
<body>
<center>
		<h1>Purchase Book</h1>
        <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>ISBN</th>
                <th>BookName</th>
                <th>publisher</th>
                <th>Price</th>
                <th>edition</th>
                <th>Author</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.isbn}" /></td>
                    <td><c:out value="${book.bookName}" /></td>
                    <td><c:out value="${book.publisher}" /></td>
                    <td><c:out value="${book.price}" /></td>
                    <td><c:out value = "${book.edition}"/></td>
                    <td><c:out value = "${book.authors}"/></td>
                    <td>
                    	<a href="edit?id=<c:out value='${book.isbn}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${book.isbn}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </div>
    <br/>	
    <br/>
    <div align="center">
			<form action="addBook" method="post">
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		Add Book
            	</h2>
            </caption>            
            <tr>
                <th>Book Name: </th>
                <td>
                	<input type="text" name="bookname" size="45"/>
                
                </td>
            </tr>
            <tr>
                <th>ISBN: </th>
                <td>
                	<input type="text" name="isbn" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Publisher: </th>
                <td>
                	<input type="text" name="publisher" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Author: </th>
                <td>
                	<input type="text" name="author" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Edition: </th>
                <td>
                	<input type="text" name="edition" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                	<input type="text" name="price" size="45"/>
                </td>
            </tr>
             <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
    <br/>
    <br/>
    <div align="center">
			<form action="bill" method="post">
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		Buy Book
            	</h2>
            </caption>            
            <tr>
                <th>Book Name: </th>
                <td>
                	<input type="text" name="bookName" size="45"/>
                
                </td>
            </tr>
            <tr>
                <th>ISBN: </th>
                <td>
                	<input type="text" name="isbn" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Quantity: </th>
                <td>
                	<input type="text" name="qty" size="5"/>
                </td>
            </tr>
             <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
     
        
	</center>
</body>
</html>