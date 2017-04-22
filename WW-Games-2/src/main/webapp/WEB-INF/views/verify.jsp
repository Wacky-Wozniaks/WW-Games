<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="/resources/css/verify.css">

<br><br>
<div class="background">
	<div class="text-container">
		<br>
		<p class="verify"><b>Please Log In to Finish Verification</b></p>
		<form:form name="verifyForm" method="POST">
			<label>Email
				<input type="text" name="email" id="email" placeholder="" aria-describedby="Enter Your Email" required >
				<span class="form-error">Please Enter your Email</span>
			</label>
			<label>Password
				<input type="password" name="password" id="password" placeholder="" aria-describedby="Enter Your Password" required >
				<span class="form-error">Please Enter a Password</span>
			</label>
						
			<br>
			
			<button class="button" type="submit" value="Submit">Verify Email</button>
		</form:form>
		
		<p class="error"><b>${error}</b></p>
		
		<br>
	</div>
</div>