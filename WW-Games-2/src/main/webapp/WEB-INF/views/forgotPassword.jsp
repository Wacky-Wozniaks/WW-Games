<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="/resources/css/login.css">

<br><br>
<div class="background">
	<div class="text-container">
		<br>
		<br>
		<p class="message"><b>Enter your email to recover your password.</b></p>
		<form:form name="forgotPasswordForm" method="POST">
			<label>Email
				<input type="text" name="email" id="email" placeholder="" aria-describedby="Enter Your Email" required >
				<span class="form-error">Please Enter an Email</span>
			</label>
			
			<br>
			
			<button class="button" type="submit" value="Submit">Recover Password</button>
		</form:form>
		
		<p class="invalid"><b>${msg}</b></p>
		
		<br>
	</div>
</div>