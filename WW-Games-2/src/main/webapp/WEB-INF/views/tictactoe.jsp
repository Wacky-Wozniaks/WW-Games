<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="resources/css/tictactoe.css">
<script src="resources/js/tictactoe.js"></script>

<p id="msg">It's your turn! Click on a square. (You are X)</p>

<br>

<table class="board">
	<tr class="board-row">
		<td class="board-cell"><h1 id="cell-1" class="text"><i class="material-icons">clear</i></h1></td>
		<td class="board-cell vertical"><h1 id="cell-2" class="text">&nbsp;</h1></td>
		<td class="board-cell"><h1 id="cell-3" class="text">&nbsp;</h1></td>
	</tr>
	<tr class="board-row">
		<td class="board-cell horizontal"><h1 id="cell-4" class="text"><i class="material-icons">radio_button_unchecked</i></h1></td>
		<td class="board-cell horizontal vertical"><h1 id="cell-5" class="text">&nbsp;</h1></td>
		<td class="board-cell horizontal"><h1 id="cell-6" class="text">&nbsp;</h1></td>
	</tr>
	<tr class="board-row">
		<td class="board-cell"><h1 id="cell-7" class="text">&nbsp;</h1></td>
		<td class="board-cell vertical"><h1 id="cell-8" class="text">&nbsp;</h1></td>
		<td class="board-cell"><h1 id="cell-9" class="text">&nbsp;</h1></td>
	</tr>
</table>