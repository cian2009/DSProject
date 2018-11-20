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
	    <c:forEach items="${cars}" var="car">
	        <tr>
	        	<td>${car.id}</td>
	        	<td>${car.reg}</td>
	        	<td>${car.year}</td>
	        	<td>${car.make}</td>
	        	<td>${car.cost}</td>
	        	<td>
		        	<a href="CarsEdit?id=${car.id}">
	        		Edit
	        		</a>
	        		<a href="CarsDelete?id=${car.id}">
	        		Delete
	        		</a>
	        	</td>
	        </tr>
	    </c:forEach>
	</table>
	
	</body>
</html>