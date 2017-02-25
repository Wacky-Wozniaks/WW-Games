<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Login Page</title>
	</head>
	<body>
		<form action="LoginServlet" method="post">
			Please enter your username
			<input type="text" name="un"/><br>
			
			Please enter your password
			<input type="text" name="pw"/>
			
			<input type="submit" value="submit">
		</form>
		<c:if test='${header.referer.substring(header.referer.lastIndexOf("/") + 1, header.referer.length()) eq "login"}'>
			<p>Invalid Username/Password.</p>
		</c:if>
	</body>
</html>