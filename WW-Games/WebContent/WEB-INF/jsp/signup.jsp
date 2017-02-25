<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="resources/css/login.css">

<br><br>
<div class="background">
	<div class="text-container">
		<br>
		<form action="SignupServlet" method="post">
			<label>Username
				<input type="text" name="un" id="username" placeholder="" aria-describedby="Enter Your Username" required >
				<span class="form-error">Please Enter a Username</span>
			</label>
			<label>Password
				<input type="password" name="pw" id="password" placeholder="" aria-describedby="Enter Your Password" required >
				<span class="form-error">Please Enter a Password</span>
			</label>
			<label>Confirm Password
				<input type="password" name="pw2" id="password2" placeholder="" aria-describedby="Enter Your Password" required >
				<span class="form-error">Please Enter a Password</span>
			</label>
			<label>First Name
				<input type="text" name="fn" id="firstname" placeholder="" aria-describedby="Enter Your First Name" required >
				<span class="form-error">Please Enter your First Name</span>
			</label>
			<label>Last Name
				<input type="text" name="ln" id="lastname" placeholder="" aria-describedby="Enter Your Last Name" required >
				<span class="form-error">Please Enter your Last Name</span>
			</label>
			
			<br>
			
			<button class="button" type="submit" value="Submit">Sign Up</button>
		</form>
		
		<br>
	</div>
</div>