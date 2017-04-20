<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="/resources/css/verify.css">

<br><br>
<div class="background">
	<div class="text-container">
		<br>
		<br>
		<p class="verify"><b>Please change your password.</b></p>
		
		<form:form name="changePasswordForm" method="POST">
			<label>Password
				<input type="password" name="password1" id="password1" placeholder="" aria-describedby="Enter Your Password" required >
				<span class="form-error">Please Enter a Password</span>
			</label>
			<label>Confirm Password
				<input type="password" name="password2" id="password2" placeholder="" aria-describedby="Confirm Your Password" required >
				<span class="form-error">Please Confirm Your Password</span>
			</label>
			
			<input type="text" name="hash" id="hash" value="${param.hash}">
			
			<br>
			
			<button class="button" type="submit" value="Submit">Change Password</button>
		</form:form>
		
		<br>
		
		<p class="invalid"><b>${error}</b></p>
		
		<br>
	</div>
</div>