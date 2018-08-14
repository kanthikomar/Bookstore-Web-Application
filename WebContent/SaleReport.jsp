<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sale Report</title>
</head>
<body>
<center>
		<h1>Sale Report</h1>
        <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2></h2></caption>
            <tr>
                <th>BookName</th>
                <th>Sale Date</th>
                <th>Price</th>
                <th>Total Selling Price of the day</th>
            </tr>
            <c:forEach var="book" items="${listSale}">
                <tr>
                    <td><c:out value="${book.bookName}" /></td>
                    <td><c:out value="${book.saledate}" /></td>
                    <td><c:out value="${book.price}" /></td>
                    <td><c:out value = "${book.totalprice}"/></td>
                    
                </tr>
            </c:forEach>
        </table>
        
    </div>
</center>
</body>
</html>