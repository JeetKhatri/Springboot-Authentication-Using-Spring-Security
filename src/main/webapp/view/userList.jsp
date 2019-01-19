<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of user</title>
</head>
<body>
<table border=1>
	<tr>
		<th>Name</th>
		<th>Number</th>
		<th>Role</th>
	</tr>
	<c:forEach items="${userDetails}" var="user">
		<tr>
			<td>${user.name}</td>
			<td>${user.number}</td>
			<td>${user.roles.name}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>