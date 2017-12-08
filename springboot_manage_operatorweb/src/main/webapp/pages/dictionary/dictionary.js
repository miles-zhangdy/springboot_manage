/**
 * 数据字典js
 */

var totalPage = 1;
var pageSize = 10;

$(function() {
	queryList(totalPage, pageSize, true);
	$("#addBtn").click(function() {
		$("#insertDictionaryform")[0].reset();
		$("#insertDictionaryform").find("#editFlag").val("add");
		$('#insertModal').modal('show');
	});
	insertDictionaryValidate();
	$("#checkall").click(function(){
		$("#datatable").find(":checkbox").prop("checked", $(this).prop("checked"));
	});
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


// initFlag false/true不/重新初始化分页信息
function queryList(curPage, pageSize, initFlag) {
	$.get("dictionary/finddictionarylist", {
		"page" : curPage,
		"pageSize" : pageSize,
		"dictionaryKey":$.trim($("#search-frm").find("#dictionaryKey").val())
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
			content += "<td>" + item.dictionaryKey + "</td>";
			content += "<td>" + item.dictionartDesc + "</td>";
			content += "<td>" + ObjectUtil.fmtTime(item.createTime, "yyyy-mm-dd") + "</td>";
			content += "<td>" + item.createUser + "</td>";
			content += "<td>" + ObjectUtil.fmtTime(item.operTime, "yyyy-mm-dd") + "</td>";
			content += "<td class=\"actions\" ><a href=\"javascript:showModify('" + item.id + "')\" class=\"on-default edit-row\" title=\"编辑\"><i class=\"fa fa-pencil\">&nbsp;编辑</i></a>";
			content += "<a href=\"javascript:deleteDictionary('" + item.id + "')\" class=\"on-default remove-row\"><i class=\"fa fa-trash-o\" title=\"删除\">&nbsp;删除</i></a>";
			content += "<a href=\"dictionaryparam/todictionaryparampage?dictionaryId="+item.id+"\" class=\"on-default edit-row\"><i class=\"fa  fa-th-list\" title=\"查看字典值\">&nbsp;查看字典值</i></a>";
			content += "</td></tr>";
		});
		$("#datatable").find("tbody").append(content);
	}
	checkboxBind();
}

function showModify(id){
	var data;
	$.ajax({
		url:'dictionary/getdictionary',
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
	$("#insertDictionaryform")[0].reset();
	$("#insertDictionaryform").find("#editFlag").val("modify");
	$("#insertDictionaryform").find("#dictionaryKey").val(data.dictionaryKey);
	$("#insertDictionaryform").find("#dictionartDesc").val(data.dictionartDesc);
	$("#insertDictionaryform").find("#id").val(data.id);
	$('#insertModal').modal('show');
}

function deleteBatch(){
	var obj = $("#datatable").find(":checkbox[name='datacheckbox']:checked");
	if(obj.length > 0){
		var ids = "";
		$.each(obj, function(index, item){
			ids += $(this).val() + ",";
		})
		ids = ids.substring(0, ids.length-1);
		deleteDictionary(ids);
	}else{
		alert("请选择要删除的信息！！！");
	}
}

function deleteDictionary(id){
	if(confirm("确认删除该条信息吗？")){
		$.ajax({
			url:'dictionary/deletedictionary',
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

function insertDictionaryValidate() {
	$("#insertDictionaryform").validate(
			{
				submitHandler : function(form) {
					insertDictionary();
				},
				rules : {
					dictionaryKey : {
						required : true
					},
					dictionartDesc : {
						required : true
					}
				},
				messages : {
					dictionaryKey : {
						required : "必须输入"
					},
					dictionartDesc : {
						required : "必须输入"
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

function insertDictionary() {
	var url = "dictionary/adddictionary";
	if($("#insertDictionaryform").find("#editFlag").val() == "modify"){
		url = "dictionary/modifydictionary";
	}
	$.ajax({
		url : url,
		type : 'post',
		async : false,
		data : $("#insertDictionaryform").serialize(),
		dataType : 'json',
		success : function(result) {
			if (result.success) {
				alert(result.msg);
				$('#insertModal').modal('hide');
			} else {
				alert(result.msg);
			}
			queryList(1, 10, true);
		}
	});
}
