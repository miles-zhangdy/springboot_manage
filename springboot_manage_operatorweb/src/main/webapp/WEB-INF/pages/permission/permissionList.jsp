<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>权限管理</title>
<%@ include file="../static/top.jsp"%>
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
							<h4 class="page-title">permission</h4>
							<ol class="breadcrumb">
								<li>Ubold</li>
								<li>Tables</li>
								<li class="active">permission</li>
							</ol>
						</div>

					</div>

					<div class="row">
						<div class="col-sm-12">
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
											<th>资源名称</th>
											<th>资源类型</th>
											<th>访问url地址</th>
											<th>排序</th>
											<th>是否显示</th>
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

			<div class="modal-dialog" style="width: 60%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>权限信息</h3>
					</div>
					<form class="form-horizontal validate" id="edit-frm">
						<input type="hidden" name="id" id="id"> <input
							type="hidden" name="editFlag" id="editFlag">
							<input type="hidden" name="parentid" id="parentid">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-2 control-label">资源名称</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="name" maxlength="18"
												id="name" placeholder="资源名称">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">资源类型</label>
										<div class="col-md-10">
											<select name="type" class="form-control" id="type">
												<option value="menu">菜单</option>
												<option value="button">按钮</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">排序</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="sortstring" maxlength="18"
												id="sortstring"  >
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">样式</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="menuClassName" 
												id="menuClassName"  >
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-2 control-label">访问url</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="url" 
												id="url" placeholder="访问url地址">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">权限代码</label>
										<div class="col-md-10">
											<select name="percode" class="form-control" id="percode">
												<option value="query">查询</option>
												<!-- <option value="create">添加</option>
												<option value="modify">修改</option>
												<option value="delete">删除</option> -->
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">是否可用</label>
										<div class="col-md-10">
											<label class="radio-inline"> <input type="radio"
												name="available" value="0" checked> 是
											</label> <label class="radio-inline"> <input type="radio"
												name="available" value="1"> 否
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">是否显示</label>
										<div class="col-md-10">
											<label class="radio-inline"> <input type="radio"
												name="isShow" value="0" checked> 是
											</label> <label class="radio-inline"> <input type="radio"
												name="isShow" value="1"> 否
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

	</div>
	<!-- END wrapper -->
	<!-- Examples -->
	<script type="text/javascript">
		var pageParentId = ${parentId};
	</script>
	<script src="pages/permission/permissionList.js"></script>
</body>
</html>