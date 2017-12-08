<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- Top Bar Start -->
<div class="topbar">
	<!-- LOGO -->
	<div class="topbar-left">
		<div class="text-center">
			<a href="user/toindex" class="logo"><i
				class="icon-magnet icon-c-logo"></i><span>Ub<i
					class="md md-album"></i>ld
			</span></a>
		</div>
	</div>
	<!-- Button mobile view to collapse sidebar menu -->
	<div class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="">
				<div class="pull-left">
					<button class="button-menu-mobile open-left">
						<i class="ion-navicon"></i>
					</button>
					<span class="clearfix"></span>
				</div>
				<ul class="nav navbar-nav navbar-right pull-right">
					<li class="dropdown"><a href=""
						class="dropdown-toggle profile" data-toggle="dropdown"
						aria-expanded="true"><img
							src="static/images/users/avatar-1.jpg" alt="user-img"
							class="img-circle"> </a>
						<ul class="dropdown-menu">
							<li><a href="user/logout" title="${sessionUser.userName }"><i
									class="ti-power-off m-r-5"></i> Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
<!-- Top Bar End -->
<script type="text/javascript">
	function logout(){
	}
</script>