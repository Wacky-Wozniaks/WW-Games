<%-- This should only show up on pages where the user is logged in! --%>

<link rel="stylesheet" href="resources/css/header.css">

<nav class="top-bar" data-topbar role="navigation">
	<div class="top-bar-left">
		<ul class="dropdown menu">
			<li><a href="">One</a></li>
			<li><a href="">Two</a></li>
			<li><a href="">Three</a></li>
			<li><a href="">Four</a></li>
		</ul>
	</div>
	<div class="top-bar-right">
		<ul class="dropdown menu" data-dropdown-menu="parxkb-dropdown-menu" role="menubar">
			<li role="menuitem" class="is-dropdown-submenu-parent opens-right">
				<a href="">${name}</a>
				<ul class="menu submenu is-dropdown-submenu first-sub vertical" role="menu">
					<li role="menuitem" class="is-submenu-item is-dropdown-submenu-item"><a class="icon" href="">Profile <i class="material-icons">person</i></a></li>
					<li role="menuitem" class="is-submenu-item is-dropdown-submenu-item"><a class="icon" href="">Logout <i class="material-icons">keyboard_tab</i></a></li>
				</ul>
			</li>
		</ul>
	</div>
</nav>