<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Customers</title>
	</head>

	<body>
	
	<table>
	    <c:forEach items="${bookings}" var="booking" varStatus="status">
	        <tr>
	        	<td>${booking.id}</td>
	        	<td>${booking.dateStart}</td>
	        	<td>${booking.dateEnd}</td>
	        	<td>${booking.carID}</td>
	        	<td>${booking.customerID}</td>
	        	<td>
		        	<a href="BookingsEdit?id=${booking.id}">
	        		Edit
	        		</a>
	        		<a href="BookingsDelete?id=${booking.id}">
	        		Delete
	        		</a>
	        	</td>
	        </tr>
	    </c:forEach>
	</table>
	
	</body>
</html>