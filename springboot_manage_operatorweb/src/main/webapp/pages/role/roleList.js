/**
 * 用户管理 js
 */

var totalPage = 1;
var pageSize = 10;

$(function() {
	
	$("#addBtn").click(function() {
		$("#edit-frm")[0].reset();
		$("#edit-frm").find("#editFlag").val("add");
		$('#editModal').modal('show');
	});
	
	$("#checkall").click(function(){
		$("#datatable").find(":checkbox").prop("checked", $(this).prop("checked"));
	});
	
	editDataValidate();
	
	queryList(totalPage, pageSize, true);
	
	$("#deleteBtn").click(function(){
		deleteBatch();
	});
	
	$("#empowerment-frm").find("#save-btn").click(function(){
		savePermission();
	});
});

function checkboxBind(){
	$("#datatable").find(":checkbox[name='datacheckbox']").unbind().click(function(){
		if($("#datatable").find(":checkbox[name='datacheckbox']:checked").length
				== $("#datatable").find(":checkbox[name='datacheckbox']").length){
			$("#checkall").prop("checked", true);
		}else{
			$("#checkall").prop("checked", false);
		}
	});
}


//initFlag false/true不/重新初始化分页信息
function queryList(curPage, pageSize, initFlag) {
	$.get("role/findrolelist", {
		"page" : curPage,
		"pageSize" : pageSize
	},
	function(data) {
		if (data.success == true) {
			var totalPage = data.data.totalPage;
			if (data.data.maxPage == 0) {
				totalPage = 1;
			}
			if (initFlag) {
				$.jqPaginator('#pagetable',{
					totalPages : totalPage,
					visiblePages : pageSize,
					currentPage : 1,
					prev : '<li class="prev"><a href="javascript:;">上页</a></li>',
					next : '<li class="next"><a href="javascript:;">下页</a></li>',
					first : '<li class="first"><a href="javascript:;">首页</a></li>',
					last : '<li class="last"><a href="javascript:;">尾页</a></li>',
					page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
					onPageChange : function(
							num, type) {
						if ((this.currentPage != 1) || (1 != num)) {
							queryList(num, pageSize, false);
						}
					}
				});

			}
			insertTable(data.data.list);

		} else {
			alert(data.msg);
		}

	}, "json");

}

function insertTable(data){//#datatable
	$("#datatable").find("tbody").empty();
	if(data.length > 0){
		var content = "";
		$.each(data, function(index, item){
			content += "<tr><td><input type='checkbox' value='" + item.id + "' name='datacheckbox' ></td>";
			content += "<td>" + item.name + "</td>";
			content += "<td>" + getAvailable(item.available) + "</td>";
			content += "<td class=\"actions\" ><a href=\"javascript:showModify('" + item.id + "')\" class=\"on-default edit-row\" title=\"编辑\"><i class=\"fa fa-pencil\">&nbsp;编辑</i></a>";
			content += "<a href=\"javascript:empowerment('" + item.id + "')\" class=\"on-default remove-row\"><i class=\"fa  fa-sitemap\" title=\"赋权\">&nbsp;赋权</i></a>";
			content += "<a href=\"javascript:deleteData('" + item.id + "')\" class=\"on-default remove-row\"><i class=\"fa fa-trash-o\" title=\"删除\">&nbsp;删除</i></a>";
			content += "</td></tr>";
		});
		$("#datatable").find("tbody").append(content);
	}
	checkboxBind();
}

function getAvailable(available){
	if(available == "0"){
		return "是";
	}
	return "否";
}

function editrole(){
	var url = "role/addrole";
	if($("#edit-frm").find("#editFlag").val() == "modify"){
		url = "role/modifyrole";
	}
	$.ajax({
		url : url,
		type : 'post',
		async : false,
		data : $("#edit-frm").serialize(),
		dataType : 'json',
		success : function(result) {
			if (result.success) {
				alert(result.msg);
				$('#editModal').modal('hide');
			} else {
				alert(result.msg);
			}
			queryList(1, pageSize, true);
		}
	});
}

var setting = {
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};


function empowerment(id){
	
	$.ajax({
		url:'role/findpermissiontree',
		type:'get',
		async:false,
		data:{id:id},
		dataType:"json",
		success:function(result){
			if(result.success){
				var zNodes = result.data;
				$('#empowermentModal').modal('show');
				$.fn.zTree.init($("#role-ztree"), setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("role-ztree");
				type = { "Y" : "ps", "N" : "s" };
				zTree.setting.check.chkboxType = type;
				$('#empowermentModal').find("#role_id").val(id);
			}else{
				alert(result.msg);	
			}
		}
	});
}

function showModify(id){
	var data;
	$.ajax({
		url:'role/getrole',
		type:'post',
		data:{id:id},
		async:false,
		dataType:"json",
		success:function(result){
			if(result.success){
				data = result.data;
			}else{
				alert(result.msg);
			}
		}
	});
	$("#edit-frm")[0].reset();
	$("#edit-frm").find("#editFlag").val("modify");
	$("#edit-frm").find("#name").val(data.name);
	$("#edit-frm").find("input[name='available'][value='"+data.available+"']").prop("checked", true);
	$("#edit-frm").find("#id").val(data.id);
	$('#editModal').modal('show');
	
}
function deleteBatch(){
	var obj = $("#datatable").find(":checkbox[name='datacheckbox']:checked");
	if(obj.length > 0){
		var ids = "";
		$.each(obj, function(index, item){
			ids += $(this).val() + ",";
		})
		ids = ids.substring(0, ids.length-1);
		deleteData(ids);
	}else{
		alert("请选择要删除的信息！！！");
	}
}

function deleteData(id){
	if(confirm("确认删除该条信息吗？")){
		$.ajax({
			url:'role/deleterole',
			type:'post',
			data:{id:id},
			dataType:"json",
			success:function(result){
				if(result.success){
					alert(result.msg);
				}else{
					alert(result.msg);
				}
				queryList(1, pageSize, true);
			}
		});
	}
}

function editDataValidate(){
	$("#edit-frm").validate(
			{
				submitHandler : function(form) {
					editrole();
				},
				rules : {
					name : {
						required : true
					}
				},
				messages : {
					name : {
						required :  "必须输入角色名"
					}
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					error.addClass("help-block");
					if (element.prop("type") === "checkbox") {
						error.insertAfter(element.parent("label"));
					} else {
						error.insertAfter(element);
					}
				},
				highlight : function(element, errorClass, validClass) {
					$(element).parents(".col-sm-5").addClass("has-error")
							.removeClass("has-success");
				},
				unhighlight : function(element, errorClass, validClass) {
					$(element).parents(".col-sm-5").addClass("has-success")
							.removeClass("has-error");
				}

			});
}

function savePermission(){
	var zTree = $.fn.zTree.getZTreeObj("role-ztree");  
	var nodes = zTree.getCheckedNodes(true); 
	var permissionIds = new Array();
	$.each(nodes, function(index, item){
		permissionIds.push(item.id);
	});
	$.ajax({
		url:'rolepermission/addrolepermission',
		type:'post',
		data:{sysRoleId:$("#empowerment-frm").find("#role_id").val(), sysPermissionIds:permissionIds},
		dataType:"json",
		async:false,
		success:function(result){
			if(result.success){
				alert(result.msg);
				$('#empowermentModal').modal('hide');
			}else{
				alert(result.msg);
			}
		}
	});
}

