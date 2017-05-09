<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="/resources/css/checkers.css">
<script src="/resources/js/checkers.js"></script>

<p id="msg">It's your turn! Click on a piece then click where you want to move it. (You are red)</p>

<table class="board" cellspacing="0">
	<tbody>
		<tr class="board-row">
			<td id="cell-00" class="board-cell board-cell-1"></td>
			<td id="cell-01" class="board-cell board-cell-2"></td>
			<td id="cell-02" class="board-cell board-cell-1"></td>
			<td id="cell-03" class="board-cell board-cell-2"></td>
			<td id="cell-04" class="board-cell board-cell-1"></td>
			<td id="cell-05" class="board-cell board-cell-2"></td>
			<td id="cell-06" class="board-cell board-cell-1"></td>
			<td id="cell-07" class="board-cell board-cell-2"></td>
		</tr>
		<tr class="board-row">
			<td id="cell-10" class="board-cell board-cell-2"></td>
			<td id="cell-11" class="board-cell board-cell-1"></td>
			<td id="cell-12" class="board-cell board-cell-2"></td>
			<td id="cell-13" class="board-cell board-cell-1"></td>
			<td id="cell-14" class="board-cell board-cell-2"></td>
			<td id="cell-15" class="board-cell board-cell-1"></td>
			<td id="cell-16" class="board-cell board-cell-2"></td>
			<td id="cell-17" class="board-cell board-cell-1"></td>
		</tr>
		<tr class="board-row">
			<td id="cell-20" class="board-cell board-cell-1"></td>
			<td id="cell-21" class="board-cell board-cell-2"></td>
			<td id="cell-22" class="board-cell board-cell-1"></td>
			<td id="cell-23" class="board-cell board-cell-2"></td>
			<td id="cell-24" class="board-cell board-cell-1"></td>
			<td id="cell-25" class="board-cell board-cell-2"></td>
			<td id="cell-26" class="board-cell board-cell-1"></td>
			<td id="cell-27" class="board-cell board-cell-2"></td>
		</tr>
		<tr class="board-row">
			<td id="cell-30" class="board-cell board-cell-2"></td>
			<td id="cell-31" class="board-cell board-cell-1"></td>
			<td id="cell-32" class="board-cell board-cell-2"></td>
			<td id="cell-33" class="board-cell board-cell-1"></td>
			<td id="cell-34" class="board-cell board-cell-2"></td>
			<td id="cell-35" class="board-cell board-cell-1"></td>
			<td id="cell-36" class="board-cell board-cell-2"></td>
			<td id="cell-37" class="board-cell board-cell-1"></td>
		</tr>
		<tr class="board-row">
			<td id="cell-40" class="board-cell board-cell-1"></td>
			<td id="cell-41" class="board-cell board-cell-2"></td>
			<td id="cell-42" class="board-cell board-cell-1"></td>
			<td id="cell-43" class="board-cell board-cell-2"></td>
			<td id="cell-44" class="board-cell board-cell-1"></td>
			<td id="cell-45" class="board-cell board-cell-2"></td>
			<td id="cell-46" class="board-cell board-cell-1"></td>
			<td id="cell-47" class="board-cell board-cell-2"></td>
		</tr>
		<tr class="board-row">
			<td id="cell-50" class="board-cell board-cell-2"></td>
			<td id="cell-51" class="board-cell board-cell-1"></td>
			<td id="cell-52" class="board-cell board-cell-2"></td>
			<td id="cell-53" class="board-cell board-cell-1"></td>
			<td id="cell-54" class="board-cell board-cell-2"></td>
			<td id="cell-55" class="board-cell board-cell-1"></td>
			<td id="cell-56" class="board-cell board-cell-2"></td>
			<td id="cell-57" class="board-cell board-cell-1"></td>
		</tr>
		<tr class="board-row">
			<td id="cell-60" class="board-cell board-cell-1"></td>
			<td id="cell-61" class="board-cell board-cell-2"></td>
			<td id="cell-62" class="board-cell board-cell-1"></td>
			<td id="cell-63" class="board-cell board-cell-2"></td>
			<td id="cell-64" class="board-cell board-cell-1"></td>
			<td id="cell-65" class="board-cell board-cell-2"></td>
			<td id="cell-66" class="board-cell board-cell-1"></td>
			<td id="cell-67" class="board-cell board-cell-2"></td>
		</tr>
		<tr class="board-row">
			<td id="cell-70" class="board-cell board-cell-2"></td>
			<td id="cell-71" class="board-cell board-cell-1"></td>
			<td id="cell-72" class="board-cell board-cell-2"></td>
			<td id="cell-73" class="board-cell board-cell-1"></td>
			<td id="cell-74" class="board-cell board-cell-2"></td>
			<td id="cell-75" class="board-cell board-cell-1"></td>
			<td id="cell-76" class="board-cell board-cell-2"></td>
			<td id="cell-77" class="board-cell board-cell-1"></td>
		</tr>
	</tbody>
</table>