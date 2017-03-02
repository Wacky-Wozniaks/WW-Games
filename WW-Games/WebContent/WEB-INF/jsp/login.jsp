<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="resources/css/login.css">

<br><br>
<div class="background">
	<div class="text-container">
		<br>
		<form action="LoginServlet" method="post">
			<label>Email
				<input type="text" name="un" id="username" placeholder="" aria-describedby="Enter Your Email" required >
				<span class="form-error">Please Enter an Email</span>
			</label>
			<label>Password
				<input type="password" name="pw" id="password" placeholder="" aria-describedby="Enter Your Password" required >
				<span class="form-error">Please Enter a Password</span>
			</label>
			
			<br>
			
			<button class="button" type="submit" value="Submit">Log In</button>
		</form>
		
		<a href="signup">New user? Sign up!</a>
		
		<br>
		
		<c:if test='${header.referer.substring(header.referer.lastIndexOf("/") + 1, header.referer.length()) eq "login"}'>
			<br>
			<p class="invalid"><b>Invalid Email/Password.</b></p>
		</c:if>
		<c:if test='${header.referer.substring(header.referer.lastIndexOf("/") + 1, header.referer.length()) eq "signup"}'>
			<br>
			<p class="verify"><b>Please verify your email. A message has been sent to you.</b></p>
		</c:if>
		
		<br>
	</div>
</div>