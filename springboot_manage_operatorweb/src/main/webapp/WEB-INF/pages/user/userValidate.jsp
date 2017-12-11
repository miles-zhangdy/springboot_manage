<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户审核</title>
<%@ include file="../static/top.jsp"%>
<style type="text/css">
th {
	text-align: center;
}

td {
	text-align: center;
}
</style>
</head>
<body class="fixed-left">
	<!-- Begin page -->
	<div id="wrapper">

		<!-- Top Bar Start -->
		<%@ include file="../static/header.jsp"%>
		<!-- Top Bar End -->

		<!-- ========== Left Sidebar Start ========== -->
		<%@ include file="../static/left.jsp"%>
		<!-- Left Sidebar End -->

		<!-- ============================================================== -->
		<!-- Start right Content here -->
		<!-- ============================================================== -->
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="container">

					<!-- Page-Title -->
					<div class="row">
						<div class="col-sm-12">
							<h4 class="page-title">users</h4>
							<ol class="breadcrumb">
								<li>Ubold</li>
								<li>Tables</li>
								<li class="active">users</li>
							</ol>
						</div>

					</div>

					<div class="row">
						<div class="col-sm-12">
							<div class="card-box">
								<div class="box-icon">
								   <a href="javascript:void(0)"
										class="btn btn-danger waves-effect waves-light" id="deleteBtn">
										<i class="glyphicon  glyphicon-remove">&nbsp;删除</i>
									</a>
								</div>
								<table id="datatable" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th><input type="checkbox" id="checkall"></th>
											<th>用户名</th>
											<th>年龄</th>
											<th>性别</th>
											<th>联系方式</th>
											<th>注册时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
								<div class="row">
									<div class="col-md-12">
										<div id="pagetable" class="pagination"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- container -->
			</div>
			<!-- content -->
			<%@ include file="../static/footer.jsp"%>
		</div>
		<!-- ============================================================== -->
		<!-- End Right content here -->
		<!-- ============================================================== -->

		<!-- Right Sidebar -->

		<!-- /Right-bar -->

	</div>
	<!-- END wrapper -->
	<!-- Examples -->
	<script src="pages/user/userValidate.js"></script>
</body>
</html>