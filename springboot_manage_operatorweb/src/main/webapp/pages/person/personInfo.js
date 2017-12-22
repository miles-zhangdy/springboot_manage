/**
*	personInfo.jss
*	个人信息
*/
	
$(function(){
	
	getUser();
	
	editDataValidate();
	
	$("#modify-person-btn").click(function(){
		showModify();
	})
});

function getUser(){
	$.ajax({
		url:'user/getperson?' + Math.random(),
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success){
				fillUser(result.data);
			}else{
				alert(result.msg);
			}
		}
	});
}

function fillUser(userInfo){
	debugger
	$("#person-info").find("#userName").html(userInfo.userName);
	$("#person-info").find("#userCompellation").html(userInfo.userCompellation);
	$("#person-info").find("#userAge").html(userInfo.userAge);
	$("#person-info").find("#userSex").html(getSex(userInfo.userSex));
	$("#person-info").find("#userPhone").html(userInfo.userPhone);
	$("#edit-frm").find("#id").val(userInfo.id);
}

function getSex(sex){
	if(sex == "0"){
		return "女";
	}
	return "男"
}

function showModify(){
	var data;
	$.ajax({
		url:'user/getperson',
		type:'post',
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
	$("#edit-frm").find("#userName").val(data.userName);
	$("#edit-frm").find("#userAge").val(data.userAge);
	$("#edit-frm").find("#userCompellation").val(data.userCompellation);
	$("#edit-frm").find("#userPhone").val(data.userPhone);
	$("#edit-frm").find("#id").val(data.id);
	$('#editModal').modal('show');
	
}

function modifyPersonInfo(){
	$.ajax({
		url : "user/modifyuser",
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
			getUser();
		}
	});
}

function editDataValidate(){
	$("#edit-frm").validate(
			{
				submitHandler : function(form) {
					modifyPersonInfo();
				},
				rules : {
					userAge : {
						required : true,
						isNum:true
					},
					userCompellation : {
						required : true
						
					},
					userPhone : {
						mobile : true
					}
					
				},
				messages : {
					userAge : {
						required : "必须输入年龄",
						isNum : "只能输入数字"
					},
					userCompellation : {
						required : "必须输入真实姓名"
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