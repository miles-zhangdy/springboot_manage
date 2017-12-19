/**
 * 登录页面js
 */

var usernameFlag = false;
var passwordFlag = false;
$(function() {

	fillFrm();

	/*$("#userName").blur(function() {
		if ((/^[a-z0-9_-]{4,20}$/).test($("#userName").val())) {
			$('.user-hint').html("✔").css("color", "green");
			usernameFlag = true;
		} else {
			$('.user-hint').html("×").css("color", "red");
			usernameFlag = false;
		}
	});

	$('#password').blur(function() {
		if ((/^[a-z0-9_-]{6,16}$/).test($("#password").val())) {
			$('.password-hint').html("✔").css("color", "green");
			passwordFlag = true;
		} else {
			$('.password-hint').html("×").css("color", "red");
			passwordFlag = false;
		}
	});*/

	$("#login-btn").click(function() {
		checkLogin();
	});
	document.onkeydown = function (){
		var keyevt = arguments[0] || window.event;
		if(keyevt.keyCode == 13){
			checkLogin();
		}
	} 
});

function checkLogin(){
	if(ObjectUtil.isEmpty($("#userName").val())){
		swal("请输入用户名");
		$("#userName").focus();
		return false;
	}
	if(ObjectUtil.isEmpty($("#password").val())){
		swal("请输入密码");
		$("#password").focus();
		return false;
	}
	if(ObjectUtil.isEmpty($("#validateCode").val())){
		swal("请输入验证码");
		$("#validateCode").focus();
		return false;
	}
	login();
}


function login() {
	$.ajax({
		url : "user/login",
		type : "post",
		data : {
			userName : $("#userName").val(),
			password : $("#password").val(),
			validateCode : $("#validateCode").val(),
			remember : $("#checkbox-signup").prop("checked")
		},
		dataType : 'json',
		success : function(result) {
			if(result.success){
				window.location.href="user/toindex";
			}else{
				swal(result.msg);
				checkImg();
			}
		}
	});
}

function fillFrm() {
	$.ajax({
		url : "user/getremember",
		type : "post",
		dataType : 'json',
		success : function(result) {
			if (result.success) {
				var data = result.data;
				if (data.remember == "true") {
					$("#userName").val(data.username);
					$("#password").val(data.password);
					$("#checkbox-signup").prop("checked", true);
				}
			}
		}
	});

}