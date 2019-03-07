<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据字典</title>
<%@ include file="../static/top.jsp"%>
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
							<h4 class="page-title">dictionary</h4>
							<ol class="breadcrumb">
								<li>zhangdy</li>
								<li>Tables</li>
								<li class="active">dictionary</li>
							</ol>
						</div>

					</div>

					<div class="row">
						<div class="col-sm-12">
							<div class="card-box">
								<form class="form-inline" id="search-frm">
									<div class="form-group col-sm-2">
										<label for="dictionaryKey">标识</label> <input type="text"
											class="form-control" id="dictionaryKey" placeholder="标识">
									</div>
									<button type="button" class="btn btn-default" onclick="queryList(1, 10, true)">查询</button>
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
								<table id="datatable" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th><input type="checkbox" id="checkall"></th>
											<th>标识</th>
											<th>描述</th>
											<th>创建时间</th>
											<th>创建人</th>
											<th>修改人</th>
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

		<div class="modal fade" id="insertModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog" style="width: 60%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>数据字典</h3>
					</div>
					<form class="form-horizontal validate" id="insertDictionaryform">
						<input type="hidden" name="dicId" id="dicId">
						<div class="modal-body">
							<div class="form-group">
								<label class="col-sm-3 control-label">字典标识</label>
								<div class="col-sm-5">
									<input name="dictionaryKey" id="dictionaryKey" type="text"
										class="form-control"> <input type="hidden"
										name="editFlag" id="editFlag"> <input type="hidden"
										id="id" name="id">
								</div>
							</div>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label class="col-sm-3 control-label">字典描述</label>
								<div class="col-sm-5">
									<input name="dictionartDesc" id="dictionartDesc" type="text"
										class="form-control">
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
	<script src="pages/dictionary/dictionary.js"></script>
</body>
</html>