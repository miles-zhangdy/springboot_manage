/**
 * register.js
 * add by zhangdaye 2017-12-11  
 * 
 */
$(function(){
	
	$.validator.addMethod("isExistsUser", function(value, element) {
    	var isExists = isExistsUser(value);
    	return this.optional(element) || isExists;
    }, "用户名已存在");
	
	editDataValidate();
	
	$("#checkbox-signup").click(function(){
		$("#sign-up").prop("disabled", !$(this).prop("checked"));
	});
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

function registerUser(){
	if(!$("#checkbox-signup").prop("checked")){
		return false;
	}
	$.ajax({
		url:'user/registeruser',
		type:'post',
		async:false,
		data:$("#edit-frm").serialize(),
		dataType:'json',
		success:function(result){
			if(result.success){
				alert("注册成功！！！");
				window.location.href="user/tologin";
			}else{
				alert(result.msg);
			}
		}
	})
}

function editDataValidate(){
	$("#edit-frm").validate(
			{
				submitHandler : function(form) {
					registerUser();
				},
				rules : {
					userName : {
						required : true,
						minlength:4,
						isExistsUser:true
					},
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
					userName : {
						required : "请输入用户名",
						minlength: "最小4位",
						isExistsUser:"用户名已存在"
					},
					userAge : {
						required : "请输入年龄",
						isNum : "只能输入数字"
					},
					userCompellation : {
						required : "请输入真实姓名"
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
