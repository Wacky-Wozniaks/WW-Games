<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="resources/css/verify.css">

<br><br>
<div class="background">
	<div class="text-container">
		<br>
		<p class="verify"><b>Please Log In to Finish Verification</b></p>
		<form action="VerifyServlet" method="post">
			<label>Email
				<input type="text" name="em" id="email" placeholder="" aria-describedby="Enter Your Email" required >
				<span class="form-error">Please Enter your Email</span>
			</label>
			<label>Password
				<input type="password" name="pw" id="password" placeholder="" aria-describedby="Enter Your Password" required >
				<span class="form-error">Please Enter a Password</span>
			</label>
			<input type="text" name="hash" id="hash" value="${param.hash}">
			
			<br>
			
			<button class="button" type="submit" value="Submit">Sign Up</button>
		</form>
		
		<c:if test='${header.referer.substring(header.referer.lastIndexOf("/") + 1, header.referer.length()).contains("verify")}'>
			<br>
			<p class="invalid"><b>There has been an error in verification.</b></p>
		</c:if>
		
		<br>
	</div>
</div>