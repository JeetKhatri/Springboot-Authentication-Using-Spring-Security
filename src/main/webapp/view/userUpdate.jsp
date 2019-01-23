<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome </title>
</head>
<body>
<form:form mathod="post" action="${path}/users" modelAttribute="userForm">
<form:hidden path="id" />
<form:hidden path="password" />
	<table>
		<tr>
			<td>Name : </td>
			<td><form:input path="name"  /></td>
		</tr>
		<tr>
			<td>Number : </td>
			<td><form:input path="number" /></td>
		</tr>
		<tr>
			<td>Roles : </td>
			<td><form:input path="roles" value="${userForm.roles.name}" disabled="true"/></td>
		</tr>
		<tr>
			<td><form:button type="submit" value="save">Save</form:button></td>
		</tr>
	</table>
</form:form>
<br/>
<a href="${path}/..">Home page</a>
</body>
</html>