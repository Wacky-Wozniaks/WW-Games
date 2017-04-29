<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="/resources/css/tictactoe.css">
<script src="/resources/js/tictactoe.js"></script>

<p id="msg">It's your turn! Click on a square. (You are X)</p>

<br>

<table class="board" cellspacing="0">
	<tr class="board-row">
		<td class="board-cell"><h1 id="cell-00" class="text"></h1></td>
		<td class="board-cell vertical"><h1 id="cell-01" class="text"></h1></td>
		<td class="board-cell"><h1 id="cell-02" class="text"></h1></td>
	</tr>
	<tr class="board-row">
		<td class="board-cell horizontal"><h1 id="cell-10" class="text"></h1></td>
		<td class="board-cell horizontal vertical"><h1 id="cell-11" class="text"></h1></td>
		<td class="board-cell horizontal"><h1 id="cell-12" class="text"></h1></td>
	</tr>
	<tr class="board-row">
		<td class="board-cell"><h1 id="cell-20" class="text"></h1></td>
		<td class="board-cell vertical"><h1 id="cell-21" class="text"></h1></td>
		<td class="board-cell"><h1 id="cell-22" class="text"></h1></td>
	</tr>
</table>