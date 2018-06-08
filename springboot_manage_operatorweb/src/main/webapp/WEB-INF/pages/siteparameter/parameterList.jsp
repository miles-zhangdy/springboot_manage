<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>参数管理</title>
<%@ include file="../static/top.jsp"%>
<link href="static/plugins/summernote/dist/summernote.css" rel="stylesheet" />
  <script src="static/plugins/tinymce/tinymce.min.js"></script>
    <script src="static/plugins/summernote/dist/summernote.js"></script>
  
  
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
							<h4 class="page-title">siteparameter</h4>
							<ol class="breadcrumb">
								<li>Ubold</li>
								<li>Tables</li>
								<li class="active">siteparameter</li>
							</ol>
						</div>

					</div>

					<div class="row">
						<div class="col-sm-12">
							<div class="card-box">
								<form class="form-inline" id="search-frm">
									<div class="form-group col-sm-3">
										<label for="userName">参数名</label> <input type="text"
											class="form-control" id="paramName" placeholder="参数名">
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
								<table id="datatable" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th><input type="checkbox" id="checkall"></th>
											<th>参数名</th>
											<th>参数值</th>
											<th>用途</th>
											<th>备注</th>
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
						<h3>参数信息</h3>
					</div>
					<form class="form-horizontal validate" id="edit-frm">
						<input type="hidden" name="id" id="id"> <input
							type="hidden" name="editFlag" id="editFlag">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-2 control-label">参数名</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="paramName"
												id="paramName" placeholder="参数名">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">用途</label>
										<div class="col-md-10">
											<input type="text" id="paramComment" name="paramComment"
												class="form-control" placeholder="参数用途">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-2 control-label">参数值</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="paramValue"
												id="paramValue" maxlength="18" placeholder="参数值">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label" for="repetitionPassword">备注</label>
										<div class="col-md-10">
											<input type="text" id="remark" name="remark" maxlength="18"
												class="form-control" placeholder="备注">
										</div>
									</div>
								</div>
								<div class="col-md-12">
										<label class="col-md-1 control-label">参数值2</label>
										<div class="col-md-11">
											<textarea id="editData" name="editData"></textarea>
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
   		$(document).ready(function () {
   		/*  $('#editData').summernote({  
   	        height: 400,                  
   	        minHeight: 300,             
   	        maxHeight: 500,        
   	        focus: true,   
   	        lang:'zh-CN',   
   	        // 重写图片上传  
   	    onImageUpload: function(files, editor, $editable) {  
   	        sendFile(files[0],editor,$editable);  
   	    }  
   	  });   */
   			
		    if($("#editData").length > 0){
		        tinymce.init({
		            selector: "textarea#editData",
		            theme: "modern",
		            height:300,
		            plugins: [
		                "advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker",
		                "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
		                "save table contextmenu directionality emoticons template paste textcolor"
		            ],
		            toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | l      ink image | print preview media fullpage | forecolor backcolor emoticons", 
		            style_formats: [
		                {title: 'Bold text', inline: 'b'},
		                {title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
		                {title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
		                {title: 'Example 1', inline: 'span', classes: 'example1'},
		                {title: 'Example 2', inline: 'span', classes: 'example2'},
		                {title: 'Table styles'},
		                {title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
		            ]
		        });    
		    }   
		});
  </script>
	
	<script src="pages/siteparameter/parameterList.js"></script>
</body>
</html>