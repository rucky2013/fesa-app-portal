<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>FS 注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/jumbotron-narrow.css" rel="stylesheet"
	type="text/css">
<style>
.body {
	padding-top: 70px
}
</style>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="../templet/head.jsp"></jsp:include>
	<div class="navbar navbar-default navbar-static-top" role="navigation" style="margin-top: 0px;z-index:999">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="main">FeSa</a>

			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav navbar-center">
					<li><a href="main">推荐</a></li>
					<li><a href="person">我的订阅</a></li>
					<li><a href="feedmanage">订阅中心</a></li>
					<li><a href="reader">自定义</a></li>
					<li><a href="download">下载</a></li>
					<li><a href="about">关于</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row" style="margin-top:18px">
		    <div class="col-md-3"></div>
			<div class="col-md-6" style="margin-top: 10px">
				<div class="well">
					<form>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon"><span style="color:red">*</span>用户名:</div>
								<input class="form-control" id="InputName" placeholder="Enter name" type="email" onfocus="controlfocus()" />
							</div>
						</div>
						<div class="form-group">
							<div class="input-group"> 
								<div class="input-group-addon"><span style="color:red">*</span>邮&nbsp;&nbsp;&nbsp;箱:</div>
								<input class="form-control" id="InputEmail"
									placeholder="Enter email" type="email" onfocus="controlfocus()" />
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">&nbsp;手机号:</div> 
								<input class="form-control" id="InputPhone"
									placeholder="Enter phone number" type="tel" onfocus="controlfocus()" />
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon"><span style="color:red">*</span>密&nbsp;&nbsp;&nbsp;码:</div> 
								<input class="form-control" id="InputPassword" placeholder="Password"
									type="password" onfocus="controlfocus()" />
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">*确认密码:</div> 
								<input class="form-control" id="InputForco" placeholder="Password"
									type="password" onfocus="controlfocus()" />
							</div>
						</div>
						<div class="form-group">
							<span id="msg" style="color: red;display:none">提示信息</span>
						</div>
						<div class="form-group">
							<a class="btn btn-primary" onclick="btnRegister()"
								style="width: 100%">注册</a>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	<div class="navbar navbar-default navbar-fixed-bottom"
		style="background-color: gray;">
		<style>
.body {
	padding-top: 70px
}
</style>
		<div class="container" style="text-align: center; color: white">
			<span>FeSa 版权所有 © 2006-2013 京公海网安备110108001536号 京ICP证110059号</span>
		</div>
	</div>
</body>
<script type="text/javascript">
	function controlfocus() {
		$("#msg").hide();
	}
	function exitmail(){
		var msg = $("#msg");
		var str=$('#InputEmail').val();
		var userName = $('#InputName').val();
		var userPass = $('#InputPassword').val();
		var useremail = $('#InputEmail').val();
		var userphone=$('#InputPhone').val();
		if(str==""){
			msg.text("请输入邮箱地址!");
			msg.show();	
			return;
		}
		$.ajax({
			type : 'post',
			url : 'existemail',
			datatype : 'json',
			data : 'email=' + $('#InputEmail').val(),
			success : function(datas) {
				if (!eval("(" + datas + ")").existResult) {
					result=true;
					$.ajax({
						type : 'post',
						url : 'registerinfo',
						datatype : 'json',
						data : 'username=' + userName + '&password=' + userPass
								+ '&email=' + useremail+'&phone='+userphone,
						success : function(datas) {
							if (eval("(" + datas + ")").registerResult) {
								window.location.href = "./main";
							} else
								alert("注册失败");
						}
					});
				}else{
					msg.text("邮箱已被注册，请更改注册邮箱!");
					msg.show();
				}
			}
		});
	}
	function btnRegister() {
		var msg = $("#msg");
		var userName = $('#InputName').val();
		var userPass = $('#InputPassword').val();
		var userFor = $('#InputForco').val();
		var useremail = $('#InputEmail').val();
		if (userName == "") {
			msg.text("请输入用户名!");
			msg.show();
			return;
		} else if (userPass == "" || userFor == "") {
			msg.text("请输入密码和确认密码!");
			msg.show();
			return;
		} else if (userPass != userFor) {
			msg.text("两次密码不一致!");
			msg.show();
			return;
		}else{
			exitmail();
		}
	}
</script>
</html>