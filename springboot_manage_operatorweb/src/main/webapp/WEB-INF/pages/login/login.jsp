<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<%@ include file="../static/top.jsp"%>
</head>
<body>
	<div class="account-pages"></div>
	<div class="clearfix"></div>
	<div class="wrapper-page">
		<div class=" card-box">
			<div class="panel-heading">
				<h3 class="text-center">
					 <strong class="text-custom">登录</strong>
				</h3>
			</div>


			<div class="panel-body">
				<form class="form-horizontal m-t-20" action="">

					<div class="form-group ">
						<div class="col-xs-12">
							<input class="form-control" type="text" id="userName"
								name="userName" placeholder="Username" >  
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12">
							<input class="form-control" type="password" id="password"
								name="password" placeholder="Password">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-xs-6">
							<input class="form-control" type="text" id="validateCode" 	maxlength="4"  
								name="validateCode" placeholder="验证码"> 
						</div>
						<div class="col-xs-6">
							<img src="validatecode/getValidateCode" id="validatiCodeImg"  style="padding-top: 10px; height:40px">
								<a href="javascript:void(0)" onclick="checkImg()" style="margin-left:15px">换一张</a> 
						</div>
					</div>
					
					<%--<div class="form-group ">
						<div class="col-xs-12">
							<div class="checkbox checkbox-primary">
								<input id="checkbox-signup" type="checkbox"> <label
									for="checkbox-signup"> 记住密码 </label>
							</div>
							
						</div>
					</div>--%>

					<div class="form-group text-center m-t-40">
						<div class="col-xs-12">
							<button
								class="btn btn-pink btn-block text-uppercase waves-effect waves-light"
								type="button" id="login-btn">登录</button>
						</div>
					</div>

					<div class="form-group m-t-30 m-b-0">
						<div class="col-sm-12">
							<!-- <a href="#" class="text-dark"><i class="fa fa-lock m-r-5"></i>
								Forgot your password?</a> -->
						</div>
					</div>
				</form>

			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 text-center">
				<p>
					没有账户? <a href="user/toregisterpage" class="text-primary m-l-5">
					<b>注册</b></a>
				</p>

			</div>
		</div>
	</div>
	<script>
		$(function(){
			checkImg();
		});
	
		function checkImg(){
			var data = Math.random();
			$("#validatiCodeImg").attr('src',"${pageContext.request.contextPath}/validatecode/getvalidatecode?data="+data);
		}
	</script>
	  <script src="pages/login/login.js"></script>
</body>
</html>