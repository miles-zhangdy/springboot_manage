/**
 * 用户管理 js
 */

var totalPage = 1;
var pageSize = 10;
var oldName = "";
$(function() {
	
	$("#checkall").click(function(){
		$("#datatable").find(":checkbox").prop("checked", $(this).prop("checked"));
	});
	
	
	queryList(totalPage, pageSize, true);
	
	$("#deleteBtn").click(function(){
		deleteBatch();
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
	$.get("user/finduserlist", {
		"page" : curPage,
		"pageSize" : pageSize,
		"userName":$.trim($("#search-frm").find("#userName").val()),
		"userSex":$("#search-frm").find("#userSex").val(),
		"userValidate":0
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
			content += "<td>" + ObjectUtil.fmtTime(item.createTime, "yyyy-mm-dd") + "</td>";
			content += "<td class=\"actions\" >";
			if(item.userValidate == "0"){
				content += "<a href=\"javascript:userValidate('" + item.id + "', '1')\" class=\"on-default edit-row\"><i class=\"fa   fa-check\" title=\"审核\">审核通过</i></a>";
				content += "<a href=\"javascript:userValidate('" + item.id + "', '2')\" class=\"on-default edit-row\"><i class=\"fa   fa-times\" title=\"审核\">审核不通过</i></a>";
			}
			content += "<a href=\"javascript:deleteData('" + item.id + "')\" class=\"on-default remove-row\"><i class=\"fa fa-trash-o\" title=\"删除\">&nbsp;删除</i></a>";
			content += "</td></tr>";
		});
		$("#datatable").find("tbody").append(content);
	}
	checkboxBind();
}

function getSex(sex){
	if(sex == "0"){
		return "女";
	}
	return "男"
}

function userValidate(id, userValidate){
	$.ajax({
		url : "user/modifyuser",
		type : 'post',
		async : false,
		data : {id:id, userValidate:userValidate},
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


