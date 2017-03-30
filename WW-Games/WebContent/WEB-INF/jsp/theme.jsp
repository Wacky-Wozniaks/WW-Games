<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>
<%@ include file="taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<link rel="stylesheet" href="resources/css/foundation.min.css"> <%-- Foundation CSS --%>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> <%-- Google Icons --%>
		
		<script src="resources/js/jquery.js"></script> <%-- JQuery --%>
		<script src="resources/js/what-input.js"></script> <%-- What-Input (for Foundation) --%>
		<script src="resources/js/foundation.min.js"></script> <%-- Foundation JS --%>
		
		<script src="resources/js/theme.js"></script>
		
		<title>Wacky Wozniaks</title>
	</head>
	
	<body>
		<%
        	if (!request.getServletPath().contains("/login") && !request.getServletPath().contains("/signup") && !request.getServletPath().contains("/verify")) {
   		%>
			<%@ include file="header.jsp" %>
		<%
        	}
    	%>
		<decorator:body/>
	</body>

</html>