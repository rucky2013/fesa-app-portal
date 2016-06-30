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
<title>FS 重置密码</title>
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
					<li class="active"><a href="main">推荐</a></li>
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
		<div class="row" style="margin-top: 8px">
			<div class="col-md-5" style="margin-left: 300px">
				<div class="well">
					<form>
						<div class="form-group">
							<span>新密码:</span> <input class="form-control" id="InputPassword"
								placeholder="Password" type="password">
						</div>
						<div class="form-group">
							<span>确认密码:</span> <input class="form-control"
								id="InputConfiPassword" placeholder="Password" type="password">
						</div>
						<a class="btn btn-primary" onclick="btnChange('${m}','${u}')"
							style="width: 100%;">提交</a>
					</form>
				</div>
			</div>
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
	function btnChange(mail, uid) {
		if ($("#InputPassword").val() == $("#InputConfiPassword").val()) {
			$.ajax({
				type : 'post',
				url : 'user/changepwd',
				datatype : 'json',
				data : 'm=' + mail + "&u=" + uid + "&newPwd="+ $("#InputPassword").val(),
				success : function(res) {
					if (res.datas) {
						alert("修改成功,请登录！");
						window.location.href = "person";
					} else {
						alert("修改失败!");
					}
				}
			});
		} else {
			if ($("#InputPassword").val() == "")
				alert("请输入密码");
			else if ($("#InputConfiPassword").val() == "")
				alert("请输入确认密码");
			else if ($("#InputPassword").val() != $("#InputConfiPassword").val())
				alert("两次输入的密码不一致,请重新输入！");
		}
	}
</script>
</html>