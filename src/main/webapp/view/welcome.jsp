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
	${message} ${loggedInUserName}. <br/><br/>You are logged in as ${pageContext.request.userPrincipal.name}. | <a href="javascript:void(0);" onclick="document.forms['logoutForm'].submit()">Logout</a> | <a href="${path}/users/update-password" >Update Password</a>
	<br><br><br>
	User List : <a href="${path}/users">user list</a><br/>
	Add User : <a href="${path}/users/insert">Add User</a>
</c:if>

<c:if test="${pageContext.request.userPrincipal.name == null}">
Welcome to springboot
<br/>
<br/>
<a href="${path}/login">Login</a><br/>
<a href="${path}/forgot-password">Forgot Password</a><br/>
<a href="${path}/signup">Signup</a>
</c:if>
</body>
</html>