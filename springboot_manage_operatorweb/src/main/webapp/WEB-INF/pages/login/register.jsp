<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<%@ include file="../static/top.jsp"%>
</head>
<body>
	<div class="account-pages"></div>
	<div class="clearfix"></div>
	<div class="wrapper-page">
		<div class=" card-box">
			<div class="panel-heading">
				<h3 class="text-center">
					<strong class="text-custom">注册会员</strong>
				</h3>
			</div>

			<div class="panel-body">
				<form class="form-horizontal m-t-20" id="edit-frm">

					<div class="form-group ">
						<label class="col-md-3 control-label">用户名</label>
						<div class="col-xs-9">
							<input class="form-control" type="text" id="userName"
								name="userName" placeholder="用户名">
							<input type="hidden" id="userFreeze" name="userFreeze" value="1"/>
							<input type="hidden" id="userValidate" name="userValidate" value="0"/>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">真实姓名</label>
						<div class="col-xs-9">
							<input class="form-control" type="text" id="userCompellation"
								name="userCompellation" placeholder="真实姓名">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">联系方式</label> 
						<div class="col-xs-9">
							<input class="form-control" type="text" id="userPhone"
								name="userPhone" placeholder="联系方式">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">年龄</label>
						<div class="col-xs-9">
							<input class="form-control" type="text" id="userAge" name="userAge" placeholder="年龄">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">性别</label>
						<div class="col-md-9">
							<label class="radio-inline"> <input type="radio"
								name="userSex" value="1" checked> 男
							</label> <label class="radio-inline"> <input type="radio"
								name="userSex" value="0"> 女
							</label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12">
							<div class="checkbox checkbox-primary">
								<input id="checkbox-signup" type="checkbox" checked="checked">
								<label for="checkbox-signup">I accept <a href="javascript:alert('敬请期待')">Terms
										and Conditions</a></label>
							</div>
						</div>
					</div>

					<div class="form-group text-center m-t-40">
						<div class="col-xs-12">
							<button id="sign-up" type="submit"
								class="btn btn-pink btn-block text-uppercase waves-effect waves-light"
								>注册</button>
						</div>
					</div>

				</form>

			</div>
		</div>

		<div class="row">
			<div class="col-sm-12 text-center">
				<p>
					已有账户?<a href="user/tologin" class="text-primary m-l-5"><b>登录</b></a>
				</p>
			</div>
		</div>

	</div>
	<script src="pages/login/register.js"></script>
</body>
</html>