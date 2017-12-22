<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
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
							<h4 class="page-title">personInfo</h4>
							<ol class="breadcrumb">
								<li>ZDY</li>
								<li>Tables</li>
								<li class="active">personInfo</li>
							</ol>
						</div>

					</div>
 					<div class="row" id="person-info">
						<div class="col-lg-5">
							<div class="card-box col-lg-6">
								<div class="bar-widget">
									<div class="table-box">
										<div class="table-detail">
										   <h4 class="m-t-0 m-b-5" style="text-align: right"><b>用户名：</h4>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
						   		<h4 class="m-t-20 m-b-5"  id="userName"><b>zhangsan</b></h4>
						    </div>
						</div>
						<div class="col-lg-5">
							<div class="card-box col-lg-6">
								<div class="bar-widget">
									<div class="table-box">
										<div class="table-detail">
										   <h4 class="m-t-0 m-b-5" style="text-align: right"><b>真实姓名：</h4>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
						   		<h4 class="m-t-20 m-b-5"  id="userCompellation"><b>张三</b></h4>
						    </div>
						</div>
						<div class="col-lg-5">
							<div class="card-box col-lg-6">
								<div class="bar-widget">
									<div class="table-box">
										<div class="table-detail">
										   <h4 class="m-t-0 m-b-5" style="text-align: right"><b>年龄：</h4>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
						   		<h4 class="m-t-20 m-b-5"  id="userAge"><b>23</b></h4>
						    </div>
						</div>
						<div class="col-lg-5">
							<div class="card-box col-lg-6">
								<div class="bar-widget">
									<div class="table-box">
										<div class="table-detail">
										   <h4 class="m-t-0 m-b-5" style="text-align: right"><b>性别：</h4>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
						   		<h4 class="m-t-20 m-b-5"  id="userSex"><b>男</b></h4>
						    </div>
						</div>
						<div class="col-lg-5">
							<div class="card-box col-lg-6">
								<div class="bar-widget">
									<div class="table-box">
										<div class="table-detail">
										   <h4 class="m-t-0 m-b-5" style="text-align: right"><b>联系方式：</h4>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
						   		<h4 class="m-t-20 m-b-5"  id=userPhone><b>18269775865</b></h4>
						    </div>
						</div>
					</div>
					<button type="button" class="btn btn-default"  style="text-align: center;" id="modify-person-btn">修改个人信息</button>
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

		<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog" style="width: 60%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>个人信息</h3>
					</div>
					<form class="form-horizontal validate" id="edit-frm">
						<input type="hidden" name="id" id="id">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-2 control-label">用户名</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="userName" disabled
												maxlength="18" id="userName" placeholder="用户名">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">年龄</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="userAge"
												maxlength="2" id="userAge" placeholder="年龄">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">性别</label>
										<div class="col-md-10">
											<label class="radio-inline"> <input type="radio"
												name="userSex" value="1" checked> 男
											</label> <label class="radio-inline"> <input type="radio"
												name="userSex" value="0"> 女
											</label>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-2 control-label">真实姓名</label>
										<div class="col-md-10">
											<input type="text" class="form-control"
												name="userCompellation" id="userCompellation" maxlength="18"
												placeholder="真实姓名">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">联系方式</label>
										<div class="col-md-10">
											<input type="text" class="form-control" id="userPhone"
												name="userPhone" placeholder="联系方式">

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary">保存</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- END wrapper -->
	<!-- Examples -->
	<script src="pages/person/personInfo.js"></script>
</body>
</html>