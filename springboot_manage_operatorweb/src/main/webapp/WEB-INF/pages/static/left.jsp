<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="left side-menu">
	<div class="sidebar-inner slimscrollleft">
		<!--- Divider -->
		<div id="sidebar-menu">
			<!-- <ul>
				<li class="text-muted menu-title">Navigation</li>
				<li class="has_sub" name="index"><a href="#"><i
						class="ti-home"></i> <span> 首页 </span> </a>
				<ul class="list-unstyled">
						<li><a href="user/toindex">首页</a></li>
					</ul></li>
				<li class="has_sub"><a href="#" class="waves-effect"><span>user manage </span></a>
					<ul class="list-unstyled">
						<li><a href="user/touserpage"> 用户管理</a></li>
					</ul></li>
				<li class="has_sub"><a href="#" class="waves-effect"><span>menu
							manage </span></a>
					<ul class="list-unstyled">
						<li><a href="permission/topermissionpage"> 菜单管理</a></li>

					</ul></li>
					<li class="has_sub"><a href="#" class="waves-effect"><span>role
							manage </span></a>
					<ul class="list-unstyled">
						<li><a href="role/torolepage"> 角色管理</a></li>

					</ul></li>
			</ul> -->
			<!-- <div class="clearfix"></div> -->
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<script type="text/javascript">
	
	var urls = ${ENVIRONMENT_USER.urls};
	$("#sidebar-menu").append(getUrls(urls))
	
	
	function getUrls(object){
		var content = "<ul class=\"list-unstyled\">";
		$.each(object, function(index, item){
			if(item.parentid == 0 || item.childList.length > 0){
				content += "<li class=\"has_sub\">";
				content += "<a href=\"#\"  class=\"waves-effect\">";
				if(item.parentid == 0){
					content += "<i class=\""+item.className+"\"></i>"
				}
				content += "<span>" + item.name + "</span></a>";
			}else{
				content += "<li >";
				content += "<a href=\""+item.url+"\" ><span>" + item.name + "</span></a>";
			}
			if(ObjectUtil.isNotEmpty(item.childList)){
				content +=getUrls(item.childList);
			}
			content += "</li>";
		});
		content += "</ul>";
		return content;
	}
	
	var projectname = '<%=pathBak%>';

	var url = window.location.href.replace(projectname, "");
	var para = window.location.search;
	url = url.replace(para, "");
	var obj = $("#sidebar-menu").find("a[href='" + $.trim(url) + "']");
		obj.parent().addClass("active");
		obj.closest(".has_sub").find("a").eq(0).addClass("waves-effect active");
</script>
