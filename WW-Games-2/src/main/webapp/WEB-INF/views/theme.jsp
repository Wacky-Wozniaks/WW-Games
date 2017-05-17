<%@ page language="java" contentType="text/html; charset=windows-1256" pageEncoding="windows-1256" %>
<%@ include file="taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>

		<link rel="stylesheet" href="/resources/css/foundation.min.css"> <%-- Foundation CSS --%>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> <%-- Google Icons --%>
		<link href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.3/sweetalert2.min.css" rel="stylesheet"> <%-- Sweetalert --%>
		
		<script src="/resources/js/jquery.js"></script> <%-- JQuery --%>
		<script src="/resources/js/what-input.js"></script> <%-- What-Input (for Foundation) --%>
		<script src="/resources/js/foundation.min.js"></script> <%-- Foundation JS --%>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.3/sweetalert2.min.js"></script> <%-- Sweetalert --%>
		
		<script src="resources/js/theme.js"></script>
		
		<title>Wacky Wozniaks</title>
	</head>
	
	<body>
		<%
        	if (!request.getServletPath().contains("/login") && !request.getServletPath().contains("/signup") && !request.getServletPath().contains("/verify") && !request.getServletPath().contains("/forgotPassword") && !request.getServletPath().contains("/changePassword")) {
   		%>
			<%@ include file="header.jsp" %>
		<%
        	}
    	%>
		<decorator:body/>
	</body>

</html>