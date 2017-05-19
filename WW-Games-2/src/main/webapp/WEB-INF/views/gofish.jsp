<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="/resources/css/gofish.css">
<script src="/resources/js/gofish.js"></script>

<%@ include file="cardLoading.jsp" %>

<div id="game" class="hidden container">
	<div class="top">
		<p><b><i>Computer</i></b></p>
		<p id="books2">Books: 0</p>
		<br>
		<span id="player2"></span>
	</div>
	
	<hr>
	
	<div class="center">
		<img id="deck" class="playing-card" src="/resources/images/cards/Back.svg"></img>
		<button id="gofish" class="button right">Go Fish!</button>
		<p id="count"></p>
		<br>
		<p id="msg"></p>
	</div>
	
	<hr>
	
	<div class="bottom right">
		<p><b><i>${name}</i></b></p>
		<p id= "books1">Books: 0</p>
		<br>
		<span id="player1"></span>
		
	</div>
</div>