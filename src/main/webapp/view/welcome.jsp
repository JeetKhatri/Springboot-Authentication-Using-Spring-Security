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
${message}
<br><br><br>
User List : <a href="${path}/users">user list</a>
Add User : <a href="${path}/users/insert">Add User</a>
</body>
</html>