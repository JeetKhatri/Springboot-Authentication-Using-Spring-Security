<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome </title>
</head>
<body>
<form method="post" action="${path}/users/forgot-password" >

	<table>
		<tr>
			<td>Name : </td>
			<td><input type="text" name="name" autofocus="true" /></td>
		</tr>
		<tr>
			<td><button type="submit" value="save">Save</button></td>
		</tr>
	</table>
</form>
<br/>
<a href="${path}/..">Home page</a>
</body>
</html>