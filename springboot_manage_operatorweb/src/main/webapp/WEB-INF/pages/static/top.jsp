<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String contextPath = request.getContextPath();
	String httpPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ contextPath + "/";
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	String pathBak = httpPath;
	if(request.getServerPort() == 80){
		pathBak = request.getScheme() + "://" + request.getServerName() + contextPath + "/";
	}
%>
<base href="<%=httpPath%>">
<link rel="shortcut icon" href="static/images/favicon_1.ico">
<link rel="stylesheet" href="static/plugins/morris/morris.css">
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="static/css/core.css" rel="stylesheet" type="text/css" />
<link href="static/css/components.css" rel="stylesheet" type="text/css" />
<link href="static/plugins/multiselect/css/multi-select.css"
	rel="stylesheet" type="text/css" />
<link href="static/css/icons.css" rel="stylesheet" type="text/css" />
<link href="static/css/pages.css" rel="stylesheet" type="text/css" />
<link href="static/css/responsive.css" rel="stylesheet" type="text/css" />
<link href="static/plugins/sweetalert/dist/sweetalert.css"
	rel="stylesheet" type="text/css">

<script>
	var resizefunc = [];
</script>
<script src="static/js/modernizr.min.js"></script>
<script src="static/js/jquery-2.1.1.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/js/detect.js"></script> 
<script src="static/js/fastclick.js"></script> 
<script src="static/js/jquery.slimscroll.js"></script> 
<script src="static/js/jquery.blockUI.js"></script> 
<script src="static/js/waves.js"></script> 
<script src="static/js/wow.min.js"></script> 
<script src="static/js/jqPaginator.min.js"></script>
<script src="static/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="static/js/jqueryValidationExtend.js"></script>
<script src="static/js/additional-methods.js"></script>
<script src="static/js/common.js"></script>
<script src="static/js/md5.js"></script>
<script src="static/js/jquery.scrollTo.min.js"></script>
<script src="static/plugins/peity/jquery.peity.min.js"></script> 
<script src="static/plugins/sweetalert/dist/sweetalert.min.js"></script>

<!-- jQuery  -->
