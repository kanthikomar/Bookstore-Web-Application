<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Books Store Application</title>
</head>
<body>
	<center>
		<h1>Books Management</h1>
        <h2>
        	<a href="new">Add New Book</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Books</a>
        	
        </h2>
	</center>
    <div align="center">
		<form action="update" method="post">
       
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            			Edit Book
            		
            	</h2>
            </caption>
        			<input type="hidden" name="isbn" value="<c:out value='${book.isbn}' />" />         
            <tr>
                <th>Title: </th>
                <td>
                	<input type="text" name="bookName" size="45"
                			value="<c:out value='${book.bookName}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Publisher: </th>
                <td>
                	<input type="text" name="publisher" size="45"
                			value="<c:out value='${book.publisher}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                	<input type="text" name="price" size="5"
                			value="<c:out value='${book.price}' />"
                	/>
                </td>
            </tr>
             <tr>
                <th>edition: </th>
                <td>
                	<input type="text" name="edition" size="5"
                			value="<c:out value='${book.edition}' />"
                	/>
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
</body>
</html>
