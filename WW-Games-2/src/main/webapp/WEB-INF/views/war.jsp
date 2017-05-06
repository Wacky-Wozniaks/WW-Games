<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="/resources/css/war.css">
<script src="/resources/js/war.js"></script>

<div class="container">
	<br>
	<div>
		<span id="player2Count"><b>26</b></span>
		<img class="playing-card" src="/resources/images/cards/Back.svg"></img>
		<span id="player2Cards"></span>
		<p><b><i>Computer</i></b></p>
	</div>
	<div class="right">
		<p class="right-text"><b><i>${name}</i></b></p>
		<span id="player1Cards"></span>
		<img class="playing-card" src="/resources/images/cards/Back.svg"></img>
		<span id="player1Count"><b>26</b></span>
		<br>
		<button id="draw" class="button right">Draw</button>
	</div>
</div>