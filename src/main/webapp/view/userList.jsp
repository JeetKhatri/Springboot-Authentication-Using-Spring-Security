<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="path" value="${pageContext.request.contextPath}"></c:set> 
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
		<th>Action</th>
	</tr>
	<c:forEach items="${userDetails}" var="user">
		<tr>
			<td>${user.name}</td>
			<td>${user.number}</td>
			<td>${user.roles.name}</td>
			<td><a href="${path}/users/edit/${user.id}">Edit</a>&nbsp;&nbsp;&nbsp;<a href="${path}/users/delete/${user.id}">Delete</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>