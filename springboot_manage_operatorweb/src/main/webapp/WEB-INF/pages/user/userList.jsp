<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
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
								<li>zhangdy</li>
								<li>Tables</li>
								<li class="active">users</li>
							</ol>
						</div>

					</div>

					<div class="row">
						<div class="col-sm-12">
							<div class="card-box">
								<form class="form-inline" id="search-frm">
									<div class="form-group col-md-3">
										<label for="userName">用户名</label> <input type="text"
											class="form-control" id="userName" placeholder="用户名">
									</div>
									<div class="form-group col-md-2">
										<label for="userSex">性别</label> <select class="form-control"
											id="userSex" name="userSex">
											<option value="">全部</option>
											<option value="1">男</option>
											<option value="0">女</option>
										</select>
									</div>
									<button type="button" class="btn btn-default"
										onclick="queryList(1, 10, true)">查询</button>
								</form>
							</div>
							<div class="card-box">
								<div class="box-icon">
									<a href="javascript:void(0)"
										class="btn btn-setting btn-round btn-default" id="addBtn">
										<i class="glyphicon glyphicon-plus">&nbsp;增加</i>
									</a> <a href="javascript:void(0)"
										class="btn btn-danger waves-effect waves-light" id="deleteBtn">
										<i class="glyphicon  glyphicon-remove">&nbsp;删除</i>
									</a>
								</div>
								<div class="table-responsive">
									<table id="datatable" class="table table-hover mails m-0 table table-actions-bar">
										<thead>
											<tr>
												<th>
													<div class="checkbox checkbox-primary checkbox-single m-r-15">
	                                                    <input id="checkall" type="checkbox">
	                                                    <label for=""checkall""></label>
	                                                </div>
												</th>
												<th>用户名</th>
												<th>年龄</th>
												<th>性别</th>
												<th>联系方式</th>
												<th>冻结</th>
												<th>创建时间</th>
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
						<h3>用户信息</h3>
					</div>
					<form class="form-horizontal validate" id="edit-frm">
						<input type="hidden" name="id" id="id"> <input
							type="hidden" name="editFlag" id="editFlag">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-2 control-label">用户名</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="userName"
												maxlength="18" id="userName" placeholder="用户名">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label" for="password">密码</label>
										<div class="col-md-10">
											<input type="password" id="password" name="password"
												maxlength="18" class="form-control" placeholder="密码">
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
										<label class="col-md-2 control-label" for="repetitionPassword">确认密码</label>
										<div class="col-md-10">
											<input type="password" id="repetitionPassword"
												name="repetitionPassword" maxlength="18"
												class="form-control" placeholder="确认密码">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">联系方式</label>
										<div class="col-md-10">
											<input type="text" class="form-control" id="userPhone"
												name="userPhone" placeholder="联系方式">

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">冻结</label>
										<div class="col-md-10">
											<label class="radio-inline"> <input type="radio"
												name="userFreeze" value="1" checked>否
											</label> <label class="radio-inline"> <input type="radio"
												name="userFreeze" value="0">是
											</label>
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
		<div class="modal fade" id="chooseRoleModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog" style="width: 40%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>选择角色</h3>
					</div>
					<form class="form-horizontal validate" id="chooserole-frm">
						<input type="hidden" name="sysUserId" id="sysUserId" />
						<div class="modal-body">
							<div class="row">
								<div class="col-md-5">
									<select class="form-control" multiple="multiple" id="nochoose" >
										
									</select>
								</div>
								<div class="col-md-1">
									<div class="btn-group-vertical ">
										<button class="btn btn-primary" type="button" id="add-choose">&gt;&gt;</button>
										<button class="btn btn-default" type="button" id="remove-choose">&lt;&lt;</button>
									</div>
								</div>
								<div class="col-md-5">
									<select class="form-control" multiple="multiple" id="choosed" >
										
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="save-user-role">保存</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- END wrapper -->
	<!-- Examples -->
	<script src="pages/user/userList.js"></script>
</body>
</html>