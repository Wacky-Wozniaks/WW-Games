<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="resources/css/login.css">

<br><br>
<div class="background">
	<div class="text-container">
		<br>
		<form:form name="signupForm" method="POST">
			<label>Email
				<input type="text" name="email" id="email" placeholder="" aria-describedby="Enter Your Email" required >
				<span class="form-error">Please Enter your Email</span>
			</label>
			<label>Password
				<input type="password" name="password" id="password" placeholder="" aria-describedby="Enter Your Password" required >
				<span class="form-error">Please Enter a Password</span>
			</label>
			<label>Confirm Password
				<input type="password" name="password2" id="password2" placeholder="" aria-describedby="Enter Your Password" required >
				<span class="form-error">Please Enter a Password</span>
			</label>
			<label>First Name
				<input type="text" name="firstName" id="firstName" placeholder="" aria-describedby="Enter Your First Name" required >
				<span class="form-error">Please Enter your First Name</span>
			</label>
			<label>Last Name
				<input type="lastName" name="ln" id="lastName" placeholder="" aria-describedby="Enter Your Last Name" required >
				<span class="form-error">Please Enter your Last Name</span>
			</label>
			
			<br>
			
			<button class="button" type="submit" value="Submit">Sign Up</button>
		</form:form>
		
		<c:if test='${header.referer.substring(header.referer.lastIndexOf("/") + 1, header.referer.length()) eq "signup"}'>
			<br>
			<p class="invalid"><b>Please ensure the following:</b></p>
			<ul class="invalid">
				<li>Email must be a Middlesex email</li>
				<li>Passwords must match and be at least 8 characters</li>
				<li>All fields must be filled out</li>
			</ul>
		</c:if>
		
		<br>
		<p class="invalid"><b>${error}</b></p>
		
		<br>
	</div>
</div>