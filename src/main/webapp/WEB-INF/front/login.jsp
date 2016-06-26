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
<title>FS 登录</title>
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
			<div class="col-md-6">
				<div class="well">
					<form>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">登录名:</div>
								<input class="form-control" id="InputEmail" placeholder="Enter email" type="email">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">密&nbsp;&nbsp;&nbsp;码:</div>
								<input class="form-control" id="InputPassword" placeholder="Password" type="password">
							</div>
						</div>
						<div class="form-group" style="margin-bottom:5px">
							<a class="btn btn-link" onclick="btnReset()"
								style="font-size: 10px">忘记密码?</a> <a class="btn btn-link"
								onclick="btnRegister()" style="float: right;">注册</a>
						</div>
						<!-- 
						<div class="form-group">
						<button type="button" class="btn btn-default btn-lg" style="width:170px">
                             <span class="glyphicon glyphicon-user"></span> 微博登录
                        </button>
                        <button type="button" class="btn btn-default btn-lg" style="width:170px">
                             <span class="glyphicon glyphicon-tree-conifer"></span> QQ登录
                        </button>
                        </div>
                         -->
						<a class="btn btn-primary" onclick="btnLogin()"
							style="width: 100%;">登陆</a>
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
	function btnLogin() {
		var message = "";
		var userName = $('#InputEmail').val();
		var userPass = $('#InputPassword').val();
		if (userName == "") {
			alert("请输入用户名!");
			return;
		} else if (userPass == "") {
			alert("请输入密码!");
			return;
		}
		$.ajax({
			type : 'post',
			url : 'loginNew?username=' + userName + '&password=' + userPass,
			success : function(data) {
				if (eval("(" + data + ")").logeResult)
					window.location.href="main";
				else
					alert("登录失败");
			}
		});
	}
	function btnRegister() {
		window.location.href="register";
	}
	function btnReset() {
		window.location.href="missingPwd";
	}
</script>
</html>