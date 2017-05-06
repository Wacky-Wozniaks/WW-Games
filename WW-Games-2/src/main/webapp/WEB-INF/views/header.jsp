<%-- This should only show up on pages where the user is logged in! --%>
<%@ include file="taglibs.jsp" %>

<link rel="stylesheet" href="resources/css/header.css">

<nav class="top-bar" data-topbar role="navigation">
	<div class="top-bar-left">
		<ul class="dropdown menu" data-dropdown-menu="parxkb-dropdown-menu" role="menubar">
			<li><a href="/home">Home</a></li>
			<li role="menuitem" class="is-dropdown-submenu-parent opens-right">
				<a href="#!">Games</a>
				<ul class="menu submenu is-dropdown-submenu first-sub vertical" role="menu">
					<li role="menuitem" class="is-submenu-item is-dropdown-submenu-item"><a class="icon" href="/games/tictactoe">Tic Tac Toe</a></li>
					<li role="menuitem" class="is-submenu-item is-dropdown-submenu-item"><a class="icon" href="/games/getfour">GetFour</a></li>
					<li role="menuitem" class="is-submenu-item is-dropdown-submenu-item"><a class="icon" href="/games/war">War</a></li>
				</ul>
			</li>
			<c:if test="${fn:contains(pageContext.request.requestURI, '/games/')}">
				<li><a href="${pageContext.request.requestURI}">Replay</a></li>
			</c:if>
		</ul>
	</div>
	<div class="top-bar-right">
		<ul class="dropdown menu" data-dropdown-menu="parxkb-dropdown-menu" role="menubar">
			<li role="menuitem" class="is-dropdown-submenu-parent opens-left">
				<a href="#!">${name}</a>
				<ul class="menu submenu is-dropdown-submenu first-sub vertical" role="menu">
					<li role="menuitem" class="is-submenu-item is-dropdown-submenu-item"><a class="icon" href="/profile">Profile <i class="material-icons">person</i></a></li>
					<li role="menuitem" class="is-submenu-item is-dropdown-submenu-item"><a class="icon" href="/logout">Logout <i class="material-icons">keyboard_tab</i></a></li>
				</ul>
			</li>
		</ul>
	</div>
</nav>