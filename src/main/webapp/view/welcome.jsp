<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome</title>
</head>
<body>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<form action="${path}/logout" id="logoutForm" method="post" ></form>
	${message} ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
	<br><br><br>
	User List : <a href="${path}/users">user list</a>
	Add User : <a href="${path}/users/insert">Add User</a>
</c:if>
Hello
</body>
</html>