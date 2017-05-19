<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="/resources/css/profile.css">
<script src="/resources/js/profile.js"></script>

<div class="container">
	<br>
	<h3 class="center">${name} Profile</h3>
	<br>
	<div class="callout secondary center">
		<h5>Name: ${name}</h5>
		<br>
		<h5>Email: ${email}</h5>
		<br>
		<h5><a onclick="changePassword()">Change Password</a></h5>
	</div>
</div>