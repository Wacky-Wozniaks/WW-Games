<%@ page import="com.wackywozniaks.beans.UserBean" %>

<link rel="stylesheet" href="resources/css/userLogged.css">

<center>
	<% UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));%>
	<h1>Welcome <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>!</h1>
	<%--You have <%= currentUser.getPoints() %> points.--%>
	<br>
	<a class="large button" href="games">Play Games</a>
</center>
