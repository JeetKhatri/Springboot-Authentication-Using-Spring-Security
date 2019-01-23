<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome </title>
</head>
<body>
${message }<br/>
<form method="post" action="${path}/users/update-password" >
	<table>
		<tr>
			<td>Existing Password : </td>
			<td><input type="password" name="existingPassword" autofocus="true" /></td>
		</tr>
		<tr>
			<td>New Password : </td>
			<td><input type="password" name="newPassword" /></td>
		</tr>
		<tr>
			<td>Confirm Password : </td>
			<td><input type="password" name="confirmPassword"/></td>
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