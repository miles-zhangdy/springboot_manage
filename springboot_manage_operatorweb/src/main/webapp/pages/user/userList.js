/**
 * 用户管理 js
 */

var totalPage = 1;
var pageSize = 10;
var oldName = "";
$(function() {
	
	$("#addBtn").click(function() {
		$("#edit-frm")[0].reset();
		$("#edit-frm").find("#editFlag").val("add");
		oldName = "";
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
	
	$("#chooserole-frm").find("#add-choose").click(function(){
		addChoose();
	});
	$("#chooserole-frm").find("#remove-choose").click(function(){
		removeChoose();
	});
	
	$("#save-user-role").click(function(){
		saveUserRole();
	});
    $.validator.addMethod("isExistsUser", function(value, element) {
    	var isExists = isExistsUser(value);
    	if($("#edit-frm").find("#editFlag").val() != "add" && oldName == value){
    		isExists = true;
    	}
    	return this.optional(element) || isExists;
    }, "用户名已存在");
});

function isExistsUser(userName){
	var res = false;
	$.ajax({
		url:'user/getuserbyname',
		type:'post',
		async:false,
		data:{userName:userName},
		dataType:'json',
		success:function(result){
			if(result.success){
				res = result.data;
			}else{
				alert(result.msg);
			}
		}
	});
	return res;
}

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
	$.get("user/finduserlist", {
		"page" : curPage,
		"pageSize" : pageSize,
		"userName":$.trim($("#search-frm").find("#userName").val()),
		"userSex":$("#search-frm").find("#userSex").val(),
		"userValidate":1
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
			content += "<td>" + item.userName + "</td>";
			content += "<td>" + item.userAge + "</td>";
			content += "<td>" + getSex(item.userSex) + "</td>";
			content += "<td>" + item.userPhone + "</td>";
			content += "<td>" + getFreeze(item.userFreeze) + "</td>";
			content += "<td>" + ObjectUtil.fmtTime(item.createTime, "yyyy-mm-dd") + "</td>";
			content += "<td class=\"actions\" ><a href=\"javascript:showModify('" + item.id + "')\" class=\"on-default edit-row\" title=\"编辑\"><i class=\"fa fa-pencil\">&nbsp;编辑</i></a>";
			content += "<a href=\"javascript:chooseRole('" + item.id + "')\" class=\"on-default edit-row\"><i class=\"fa   fa-user\" title=\"赋予角色\">&nbsp;赋予角色</i></a>";
			if(item.userFreeze == "0"){
				content += "<a href=\"javascript:userFreeze('" + item.id + "', '1')\" class=\"on-default edit-row\"><i class=\"fa   fa-check\" title=\"解除冻结\">&nbsp;解除冻结</i></a>";
			}else{
				content += "<a href=\"javascript:userFreeze('" + item.id + "', '0')\" class=\"on-default remove-row\"><i class=\"fa fa-times\" title=\"冻结\">&nbsp;冻结用户</i></a>";
			}
			content += "<a href=\"javascript:deleteData('" + item.id + "')\" class=\"on-default remove-row\"><i class=\"fa fa-trash-o\" title=\"删除\">&nbsp;删除</i></a>";
			content += "</td></tr>";
		});
		$("#datatable").find("tbody").append(content);
	}
	checkboxBind();
}



function chooseRole(userId){
	$.ajax({
		url:'userrole/finduserrolelist',
		type:'post',
		data:{sysUserId:userId},
		dataType:'json',
		success:function(result){
			if(result.success){
				var content1 = "";
				var content2 = "";
				$.each(result.data.list, function(index, item){
					if(item.checked == "checked"){
						content2 += "<option value='"+item.id+"' >"+item.name+"</option>";
					}else{
						content1 += "<option value='"+item.id+"' >"+item.name+"</option>";
					}
				});
				$("#chooserole-frm").find("#nochoose").empty();
				$("#chooserole-frm").find("#nochoose").append(content1);
				$("#chooserole-frm").find("#choosed").empty();
				$("#chooserole-frm").find("#choosed").append(content2);
				$("#chooserole-frm").find("#sysUserId").val(userId);
				$("#chooseRoleModal").modal("show");
			}else{
				alert(result.msg);
			}
		}
	});
}

function addChoose(){
	$("#chooserole-frm").find("#nochoose option:selected").appendTo('#choosed');
}

function removeChoose(){
	$("#chooserole-frm").find("#choosed option:selected").appendTo('#nochoose');
}

function saveUserRole(){
	var userId = $("#chooserole-frm").find("#sysUserId").val();
	var roleIds = $("#chooserole-frm").find("#choosed option");
	if(ObjectUtil.isEmpty(roleIds)){
		alert("请选择角色");
		return false;
	}
	var rIds = new Array();
	$.each(roleIds, function(index, item){
		rIds.push(item.value);
	});
	$.ajax({
		url:'userrole/adduserrole',
		type:'post',
		data:{sysUserId:userId, roleIds:rIds},
		async:false,
		dataType:'json',
		success:function(result){
			if(result.success){
				alert(result.msg);
				$("#chooseRoleModal").modal("hide");
			}else{
				alert(result.msg);
			}
		}
	});
}

function getSex(sex){
	if(sex == "0"){
		return "女";
	}
	return "男"
}

function getFreeze(freeze){
	if(freeze == "0"){
		return "冻结";
	}
	return "未冻结"
}

function editUser(){
	var url = "user/adduser";
	if($("#edit-frm").find("#editFlag").val() == "modify"){
		url = "user/modifyuser";
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

function userFreeze(id, freeze){
	$.ajax({
		url : "user/modifyuser",
		type : 'post',
		async : false,
		data : {id:id, userFreeze:freeze},
		dataType : 'json',
		success : function(result) {
			if (result.success) {
				alert(result.msg);
			} else {
				alert(result.msg);
			}
			queryList(1, pageSize, true);
		}
	});
}

function showModify(id){
	var data;
	$.ajax({
		url:'user/getuser',
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
	$("#edit-frm").find("#userName").val(data.userName);
	$("#edit-frm").find("#password").val(data.password);
	$("#edit-frm").find("#userAge").val(data.userAge);
	$("#edit-frm").find("#userCompellation").val(data.userCompellation);
	$("#edit-frm").find("#repetitionPassword").val(data.password);
	$("#edit-frm").find("#userPhone").val(data.userPhone);
	$("#edit-frm").find("input[name='userFreeze'][value='"+data.userFreeze+"']").prop("checked", true);
	$("#edit-frm").find("input[name='userSex'][value='"+data.userFreeze+"']").prop("checked", true);
	$("#edit-frm").find("#id").val(data.id);
	oldName = data.userName;
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
			url:'user/deleteuser',
			type:'post',
			data:{id:id},
			dataType:"json",
			success:function(result){
				if(result.success){
					alert(result.msg);
				}else{
					alert(result.msg);
				}
				queryList(1, 10, true);
			}
		});
	}
}

function editDataValidate(){
	$("#edit-frm").validate(
			{
				submitHandler : function(form) {
					editUser();
				},
				rules : {
					userName : {
						required : true,
						minlength:4,
						isExistsUser:true
					},
					password : {
						required : true,
						minlength: 5 
					},
					userAge : {
						required : true,
						isNum:true
					},
					userCompellation : {
						required : true
						
					},
					repetitionPassword : {
						required : true,
						equalTo : "#password"
					},
					userPhone : {
						mobile : true
					}
					
				},
				messages : {
					userName : {
						required : "必须输入用户名",
						minlength: "最小4位",
						isExistsUser:"用户名已存在"
					},
					password : {
						required : "必须输入密码",
						minlength: "密码最少5位"
					},
					userAge : {
						required : "必须输入年龄",
						isNum : "只能输入数字"
					},
					userCompellation : {
						required : "必须输入真实姓名"
					},
					repetitionPassword : {
						required : "必须输入确认密码",
						equalTo : "确认密码必须和密码保持一致"
					},
					userPhone : {
						mobile : "请输入正确的手机号"
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



