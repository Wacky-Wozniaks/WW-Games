<%-- This should only show up on pages where the user is logged in! --%>
<%@ page import="com.wackywozniaks.beans.UserBean" %>

<link rel="stylesheet" href="resources/css/header.css">

<% UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));%>
<nav class="top-bar" data-topbar role="navigation">
	<div class="top-bar-left">
		<ul class="dropdown menu">
			<li><a href="#">One</a></li>
			<li><a href="#experience">Two</a></li>
			<li><a href="#whatilike">Three</a></li>
			<li><a href="#connect">Four</a></li>
		</ul>
	</div>
	<div class="top-bar-right">
		<ul class="dropdown menu" data-dropdown-menu="parxkb-dropdown-menu" role="menubar">
			<li role="menuitem" class="is-dropdown-submenu-parent opens-right">
				<a href="#!"><%= currentUser.getFirstName() + " " + currentUser.getLastName() %></a>
				<ul class="menu submenu is-dropdown-submenu first-sub vertical" role="menu">
					<li role="menuitem" class="is-submenu-item is-dropdown-submenu-item"><a class="icon" href="#!">Profile <i class="material-icons">person</i></a></li>
					<li role="menuitem" class="is-submenu-item is-dropdown-submenu-item"><a class="icon" href="#!">Logout <i class="material-icons">keyboard_tab</i></a></li>
				</ul>
			</li>
		</ul>
	</div>
</nav>