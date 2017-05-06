<%@ include file="taglibs.jsp" %>

<link rel="stylesheet" href="resources/css/userLogged.css">

<div class=" row">
	<br>
	<h1 class="center">Welcome ${name}!</h1>
	<br>
	<div class="center large-9 columns">
		<p>You have ${points} WW-Points and are at Level ${level}.</p>
		<br>
		<div class="success progress" role="progressbar" tabindex="0" aria-valuenow="${levelPercent}" aria-valuemin="0" aria-valuetext="${levelPercent} percent" aria-valuemax="100">
			<div class="progress-meter" style="width: ${levelPercent}%">
				<p class="progress-meter-text">${levelPercent}%</p>
			</div>
		</div>
		<p>You need ${levelPoints} WW-Points to get to the next level.</p>
		<%--<br> <a class="large button" href="/games">Play Games</a>--%>
	</div>
	<div class="large-3 columns">
		<div class="callout secondary">
			<h5><a href="home">Home</a></h5>
			<hr>
			<h5><i>Games</i></h5>
			<p class="side-link"><a href="games/tictactoe">Tic Tac Toe</a></p>
			<p class="side-link"><a href="games/getfour">GetFour</a></p>
			<p class="side-link"><a href="games/war">War</a></p>
		</div>
	</div>
</div>
