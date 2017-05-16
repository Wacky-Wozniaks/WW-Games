<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="/resources/css/gofish.css">
<script src="/resources/js/gofish.js"></script>

<div class="container">
	<div class="top">
		<p><b><i>Computer</i></b></p>
		<br>
		<span id="player2"></span>
	</div>
	<div class="center">
		<img class="playing-card" src="/resources/images/cards/Back.svg"></img>
		<p id="count"></p>
		<br>
		<div class="message"></div>
	</div>
	<div class="bottom">
		<p><b><i>$(name)</i></b></p>
		<br>
		<span id="player1"></span>
		<br>
		
		<button id="gofish">Go Fish!</button>
		
	</div>
</div>