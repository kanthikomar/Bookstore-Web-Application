<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
			<form action="generateSaleReport" method="post">
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		Sale Report
            	</h2>
            </caption>            
            <tr>
                <th>BookStore Name: </th>
                <td>
                	<input type="text" name="bookstorename" size="5"/>
                </td>
            </tr>
             <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="generate" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
     
        
	</center>
	
	<br/>
	<br/>
	
	<div align="center">
			<form action="generateCustomerReport" method="post">
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		Visitor Count
            	</h2>
            </caption>            
            <tr>
                <th>BookStore Name: </th>
                <td>
                	<input type="text" name="bookstorename" size="45"/>
                
                </td>
            </tr>
             <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="generate" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
     
        
	</center>

</body>
</html>