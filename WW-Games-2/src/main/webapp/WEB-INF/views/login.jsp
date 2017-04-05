<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="resources/css/login.css">

<br><br>
<div class="background">
	<div class="text-container">
		<br>
		<form:form name="loginForm" method="POST">
			<label>Email
				<input type="text" name="email" id="email" placeholder="" aria-describedby="Enter Your Email" required >
				<span class="form-error">Please Enter an Email</span>
			</label>
			<label>Password
				<input type="password" name="password" id="password" placeholder="" aria-describedby="Enter Your Password" required >
				<span class="form-error">Please Enter a Password</span>
			</label>
			
			<br>
			
			<button class="button" type="submit" value="Submit">Log In</button>
		</form:form>
		
		<a href="signup">New user? Sign up!</a>
		
		<br>
		
		<br>
		<p class="invalid"><b>${error}</b></p>
		<p class="message"><b>${msg}</b></p>
		
		<br>
	</div>
</div>