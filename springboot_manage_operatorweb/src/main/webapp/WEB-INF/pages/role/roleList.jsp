<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<%@ include file="../static/top.jsp"%>
	<!-- <link rel="stylesheet" href="static/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css"> -->
	    <link rel="stylesheet" href="static/plugins/bootstrap-ztree3/css/bootstrap-style/bootstrapStyle.css" type="text/css">
	<script type="text/javascript" src="static/plugins/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="static/plugins/ztree/jquery.ztree.excheck-3.5.js"></script>
<style type="text/css" >
	th{text-align: center;}
	td{text-align: center;}
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
							<h4 class="page-title">role</h4>
							<ol class="breadcrumb">
								<li>zhangdy</li>
								<li>Tables</li>
								<li class="active">role</li>
							</ol>
						</div>

					</div>

					<div class="row">
						<div class="col-sm-12">
							<!-- <div class="card-box">
								<form class="form-inline" id="search-frm">
									<div class="form-group col-md-2">
										<label for="userName">用户名</label> <input type="text"
											class="form-control" id="userName" placeholder="用户名">
									</div>
									<div class="form-group col-md-2">
										<label for="userSex">性别</label> 
										<select class="form-control" id="userSex" name="userSex">
											<option value="">全部</option>
											<option value="1">男</option>
											<option value="0">女</option>
										</select>
									</div>
									<button type="button" class="btn btn-default"
										onclick="queryList(1, 10, true)">查询</button>
								</form>
							</div> -->
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
								<table id="datatable" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th><input type="checkbox" id="checkall"></th>
											<th>角色名称</th>
											<th>是否有效</th>
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

		<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog" style="width: 50%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>角色信息</h3>
					</div>
					<form class="form-horizontal validate" id="edit-frm">
						<input type="hidden" name="id" id="id"> <input
							type="hidden" name="editFlag" id="editFlag">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6 col-md-offset-3">
									<div class="form-group">
										<label class="col-md-2 control-label">角色名</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="name" maxlength="18"
												id="name" placeholder="角色名">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">是否有效</label>
										<div class="col-md-10">
											<label class="radio-inline"> <input type="radio"
												name="available" value="0" checked> 是
											</label> <label class="radio-inline"> <input type="radio"
												name="available" value="1"> 否
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
		
		<div class="modal fade" id="empowermentModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog" style="width: 50%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>角色赋权</h3>
					</div>
					<form class="form-horizontal validate" id="empowerment-frm">
						<input type="hidden" name="role_id" id="role_id"> <input
							type="hidden" name="editFlag" id="editFlag">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-8">
									<div class="form-group">
										<label class="col-md-2 control-label">权限</label>
										<div class="col-md-10"  >
											<ul id="role-ztree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" id="save-btn" class="btn btn-primary">保存</button>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>
	<!-- END wrapper -->
	<!-- Examples -->
	<script src="pages/role/roleList.js"></script>
</body>
</html>