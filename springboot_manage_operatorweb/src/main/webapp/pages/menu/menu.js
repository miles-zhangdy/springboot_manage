//讲数据放入页面
var treeData = null;
var addFlag = 0;

var dataInfo = JSON.parse('{"jsontree":[{"id":"1","text":"菜单11","parent":"#"},{"id":"2","text":"菜单22","parent":"#"},{"id":"3","text":"菜单33","parent":"#"},{"id":"4","text":"菜单44","parent":"1"},{"id":"5","text":"菜单55","parent":"2"},{"id":"6","text":"菜单66","parent":"3"},{"id":"7","text":"菜单77","parent":"3"}]}');

$(function(){
	treeData = dataInfo.jsontree;
	initPage();
	queryData();
});

function queryData(){
	$.ajax({
		url:'menu/findmenulist?' + Math.random(),
		type:'get',
		dataType:'json',
		success:function(result){
			
		}
	});
}

function initPage(){
	var content = '';
	if(treeData.length >  0){
		content +=  eachJson();
	}
	content += '<li><a href="javascript:void(0);" name="addFirstClass">添加菜单</li>'
	$("#all_tree").append(content);
	
	if(treeData.length >  0){
		setChildTree();
	}
	bindFun();
}

function eachJson(){
	var content = '';
	var dataArray = new Array();
	var i = 0;
	$.each(treeData, function(index, items){
		addFlag ++;
		if(items.parent == '#'){
			content += '<li><span><i class="icon-folder-open"></i><input name="className" maxlength="15" value="'+items.text+'" val="'+items.id+'"></span>';
			content += '&nbsp;&nbsp;<a href="javacript:void(0)" name="add_class" val="'+items.id+'">添加下级菜单</a>';
			content += '&nbsp;&nbsp;<a href="javacript:void(0)" name="delClass" val="'+items.id+'"><em>删除菜单</em></a>';
			content += '<ul id="addFlag'+items.id+'"></ul>';
		}else{
			dataArray.push(items);
		}
	});
	
	treeData = dataArray;
	return content;
}

function setChildTree(){
	$.each(treeData, function(index, items){
		addFlag ++;
		var parentNode = $('ul[id="addFlag'+items.parent+'"]');
		var content = '';
		content += '<li><span><i class="icon-folder-open"></i><input name="className" maxlength="15" value="'+items.text+'" val="'+items.id+'"></span>';
		content += '&nbsp;&nbsp;<a href="javacript:void(0)" name="delClass" val="'+items.id+'"><em>删除菜单</em></a>';
		content += '<ul id="addFlag'+items.id+'"></ul>';
		parentNode.append(content);
	});
}




$(function () {
    bindFun();
});

function delClass(classId, obj){
	obj.parent().remove();
//	$.messager.confirm("删除部门菜单", "确认删除部门菜单吗", function() {
//		$.ajax({
//			url:'/action/noteClassMgr/delNoteClass',
//			type:'post',
//			data:{classId:classId},
//			dataType:'json',
//			success:function(result){
//				if(result.code == SUC){
//					alert('删除成功');
//					obj.parent().remove();
//				}else{
//					alert('删除失败');
//				}
//			}
//		});
//	});
}

//保存
function saveClass(className, parentId, obj){
	addFlag = addFlag +1;
	if(className == '' || className == undefined || className == null){
		alert('请输入菜单名');
		return false;
	}
	var content = '';
	content += '<span><i class="icon-leaf"></i><input name="className" value="'+className+'" val="ff"></span>';
	content += '&nbsp;&nbsp;<a href="javacript:void(0)" name="delClass" val="test"><em>删除菜单</em></a><ul id="addFlag'+addFlag+'"></ul>';
	//obj.parent().remove();
	obj.parent().html(content);
	bindFun();
//	$.messager.confirm("添加部门菜单", "确认添加部门菜单吗", function() {
//	
//	$.ajax({
//		url:'/action/noteClassMgr/insertNoteClass',
//		data:{className:className,parentId:parentId},
//		type:'post',
//		async:false,
//		dataType:'json',
//		success:function(result){
//			if(result.code == SUC){
//				var content = '';
//				var classId = result.data;
//				content += '<span><i class="icon-leaf"></i><input name="className" value="'+className+'" val="'+classId+'"></span>';
//				content += '&nbsp;&nbsp;<a href="javacript:void(0)" name="delClass" val="'+classId+'"><em>删除菜单</em></a>';
//				//obj.parent().remove();
//				obj.parent().html(content);
//				bindFun();
//			}else{
//				alert(result.msg);
//			}
//		}
//	});
	
//	});
}

function bindFun(){
	
	$("input[name='className']").unbind().on('change', function(){
		updateClassName($(this));
	});
	$("a[name='delClass']").unbind().click(function(){
		var $this = $(this);
		delClass($this.attr('val'), $this);
	});
	
	$("a[name='addFirstClass']").unbind().click(function(){
		addFirstClass($(this));
	});
	
	$("a[name='saveFirstClass']").unbind().click(function(){
		saveFirstClass($(this));
	});
	
	$("a[name='cancelFirstClass']").unbind().click(function(){
		cancelFirstClass($(this));
	});
	
	$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').unbind().on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
	
	$("a[name='add_class']").unbind().click(function(){
    	var $this = $(this);
    	var children = $this.parent('li.parent_li').find(' > ul > li');
    	children.show('fast');
    	var parentId = $this.attr('val') ;
    	var content = '<li><span><i class="icon-leaf"></i> <input class="form-control" maxlength="15" name="className"></span>';
    	content += '&nbsp;&nbsp;<a href="javascript:void(0);" name="saveClass" val="'+parentId+'">保存</a>&nbsp;&nbsp;';
    	content += '&nbsp;&nbsp;<a href="javascript:void(0);" name="cancel">取消</a>&nbsp;&nbsp;</li>';
    	$this.parent().find('ul[id="addFlag'+parentId+'"]').append(content);
    	$("a[name='saveClass']").unbind().click(function(){
    		var $thiss = $(this);
    		var parentIds = $thiss.attr('val');
    		var className = $thiss.parent().find('input[name="className"]').val();
    		saveClass(className, parentIds, $thiss);
    	});
    	$("a[name='cancel']").unbind().click(function(){
    		var $thisc = $(this);
    		$thisc.parent('li').remove();
    		//$this.parent('li').empty();
    	});
    });
	
}

function addFirstClass(obj){
	var content = '';
	content += '<span><i class="icon-folder-open"></i><input type="text" maxlength="15" class="form-control"></span>';
	content += '&nbsp;&nbsp;<a href="javascript:void(0);" name="saveFirstClass">保存</a>';
	content += '&nbsp;&nbsp;<a href="javascript:void(0);" name="cancelFirstClass">取消</a>';
	obj.parent().html(content);
	bindFun();
}

function cancelFirstClass(obj){
	var content = '';
	content += '<li><a href="javascript:void(0);" name="addFirstClass">添加菜单</li>';
	obj.parent().remove();
	$("#all_tree").append(content);
	bindFun();
}

function updateClassName(obj){
	var className = obj.parent().find('input').val();
	var classId = obj.parent().find('input').attr('val');
	if(className == '' || className == undefined || className == null){
		alert('请输入菜单名称');
		obj.focus();
		return false;
	}
	$.ajax({
		url:'/action/noteClassMgr/updNoteClass',
		type:'post',
		data:{className:className, classId:classId},
		dataType:'json',
		success:function(result){
			if(result.code == SUC){
				alert('修改成功');
				bindFun();
			}else{
				alert(result.msg);
			}
		}
	});
}



function saveFirstClass(obj){
	addFlag = addFlag +1;
	var className = obj.parent().find('input').val();
	if(className == '' || className == undefined || className == null){
		alert('请输入菜单名称');
		return false;
	}
	var content = '';
	content += '<span><i class="icon-folder-open"></i><input name="className" value="'+className+'" val="e"></span>';
	content += '&nbsp;&nbsp;<a href="javacript:void(0)" name="add_class" val="'+addFlag+'">添加下级菜单</a>';
	content += '&nbsp;&nbsp;<a href="javacript:void(0)" name="delClass" val="vvv"><em>删除菜单</em></a><ul id="addFlag'+addFlag+'"></ul>';
	obj.parent().html(content);
	$("#all_tree").append('<li><a href="javascript:void(0);" name="addFirstClass">添加菜单</li>');
	bindFun();
	return false;
//	$.messager.confirm("添加部门菜单", "确认添加部门菜单吗", function() {
//		$.ajax({
//			url:'/action/noteClassMgr/insertNoteClass',
//			type:'post',
//			data:{className:className, parentId:''},
//			dataType:'json',
//			success:function(result){
//				if(result.code == SUC){
//					var content = '';
//					content += '<span><i class="icon-folder-open"></i><input name="className" maxlength="15" value="'+className+'" val="'+result.data+'"></span>';
//					content += '&nbsp;&nbsp;<a href="javacript:void(0)" name="add_class" val="'+result.data+'">添加下级菜单</a>';
//					content += '&nbsp;&nbsp;<a href="javacript:void(0)" name="delClass" val="'+result.data+'"><em>删除菜单</em></a><ul></ul>';
//					obj.parent().html(content);
//					$("#all_tree").append('<li><a href="javascript:void(0);" name="addFirstClass">添加菜单</li>');
//					bindFun();
//				}else{
//					alert(result.msg);
//				}
//			}
//		});
//	});
}






