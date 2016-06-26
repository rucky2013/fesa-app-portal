<%@page import="cn.lyf.bean.userInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	userInfo user_temp = (userInfo) session.getValue("user");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>FeedSearch,FS,fesa</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/jumbotron-narrow.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
.body {
	padding-top: 70px
}
</style>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript">
	function controlfocus() {
		$("#msg").hide();
	}
	function btnLogin() {
		var message = $('#msg');
		var userName = $('#InputName').val();
		var userPass = $('#InputPassword').val();
		if (userName == "") {
			message.text("请输入用户名!");
			message.show();
			return;
		} else if (userPass == "") {
			message.text("请输入密码!");
			message.show();
			return;
		}
		$.ajax({
			type : 'post',
			url : 'loginNew?username=' + userName + '&password=' + userPass,
			success : function(data) {
				if (eval("(" + data + ")").logeResult)
					window.location.href = "main";
				else
					alert("登录失败");
			}
		});
	}
	function btnRegister() {
		window.location.href = "register";
	}
	function btnReset() {
		window.location.href = "missingPwd";
	}
	function loginout() {
		$.ajax({
			type : 'post',
			url : 'loginOut',
			datatype : 'json',
			success : function(datas) {
				if (eval("(" + datas + ")").loginoutResult) {
					window.location.href = "main";
				} else {
					alert("对不起,退出失败.出现异常!我们带给你的不便请谅解.");
				}
			}
		});
	}
	function pmanage() {
		alert("个人信息管理");
	}
	function showdetail(e, fid, deid) {
		var artDetail = $(e.srcElement).parent().parent().children(
				'.art-detail');
		var artSummary = $(e.srcElement).parent().parent().children(
				'.art-summary');
		if ($(artDetail).css("display") == "none") {
			$(artDetail).css("display", "normal");
			$(artSummary).css("display", "none");
			personRead(fid, deid);
		} else {
			$(artDetail).css("display", "none");
			$(artSummary).css("display", "normal");
		}
	}
	function feeddetail(fedid) {
		window.location.href = "feeddetail?feedid=" + fedid;
	}
	function personRead(fid, deid) {
		$.ajax({
			type : 'post',
			url : 'personread',
			datatype : 'json',
			data : 'parafid=' + fid + '&detailid=' + deid,
			success : function(datas) {
				if (eval("(" + datas + ")").readResult) {
					//暂不处理
				} else {
					//暂不处理
				}
			}
		});
	}
</script>
</head>
<body>
	<jsp:include page="../templet/head.jsp"></jsp:include>
	<div id="nav_main" class="navbar navbar-default navbar-static-top" style="margin-top: 0px">
		<div class="container" id="doc">
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
				<ul class="nav navbar-nav navbar-center" id="navi-l">
					<li class="active"><a href="main">首页</a></li>
					<li><a href="person">个人中心</a></li>
					<li><a href="feedmanage">订阅中心</a></li>
					<li><a href="download">下载</a></li>
					<li><a href="about">关于</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row" style="margin-top: 8px">
			<div class="col-md-8">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img src="<%=basePath%>images/main.jpg">
							<div class="carousel-caption">
								<h2>Title</h2>
								<p>Description</p>
							</div>
						</div>
						<div class="item">
							<img src="<%=basePath%>images/main1.jpg">
						</div>
						<div class="item">
							<img src="<%=basePath%>images/main2.jpg">
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-example-generic"
						data-slide="prev"><span
						class="glyphicon glyphicon-chevron-left"></span></a> <a
						class="right carousel-control" href="#carousel-example-generic"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</div>
			</div>
			<div class="col-md-4">
				<div class="well">
					<c:if test="<%=user_temp == null%>">
						<form style="height: 160px">
							<div class="form-group">
								<input class="form-control" id="InputName"
									placeholder="登录名:Enter name" onfocus="controlfocus()"
									type="text">
							</div>
							<div class="form-group">
								<input class="form-control" id="InputPassword"
									placeholder="密码:Password" onfocus="controlfocus()"
									type="password">
							</div>
							<div class="form-group" style="margin-bottom: 0px">
								<a class="btn btn-link" onclick="btnReset()"
									style="font-size: 10px">忘记密码?</a> <span id="msg"
									style="color: red; display: none; width: 100px"></span> <a
									class="btn btn-link" onclick="btnRegister()"
									style="font-size: 10px; float: right;">注册</a>
							</div>
							<p>
								<a class="btn btn-primary" onclick="btnLogin()"
									style="width: 100%;">登陆</a>
							</p>
						</form>
					</c:if>
					<c:if test="<%=user_temp != null%>">
						<form style="height: 160px">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group" style="margin-bottom: 0px">
										<img alt="" style="border: outset;"
											src="<%=basePath%>images/user.jpg" />
									</div>
								</div>
								<div class="col-md-6" style="margin-top: 0px">
									<a class="btn btn-link">我的收藏 1 </a> <a class="btn btn-link">我的日志
										3 </a> <a class="btn btn-link">我的频道 2 </a> <a class="btn btn-link"
										style="margin-bottom: 0px">我的订阅 5 </a>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12" style="text-align: center;">
									<div class="form-group"
										style="margin-bottom: 0px; margin-top: 10px">
										<span style="font-weight: bold;"><c:out
												value="<%=user_temp.getUserName()%>"></c:out></span> <span><input
											type="button" value="个人管理" onclick="pmanage()" /> </span> <span><input
											type="button" value="退出登陆" onclick="loginout()" /> </span>
									</div>
								</div>
							</div>
						</form>
					</c:if>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<hr>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9">
				<div class="alert alert-success">
					<h4>热文</h4>
					<c:forEach var="article" items="${mostfile}">
						<div class="well">
							<h3>
								<a href="javascript:"
									onclick="showdetail(event,'${article.feedId.id}','${article.id}')"
									style="color: black;"> <c:out value="${article.title}"></c:out></a>
							</h3>
							<hr
								style="height: 1px; border: none; border-top: 1px solid #555555;">
							<span class="art-summary"> ${article.summary} </span> <span
								class="art-detail" style="display: none;"> <c:if
									test="${article.content!=null && fn:length(article.content)>10}">
							   ${article.content} 
							</c:if> <c:if
									test="${article.content==null||fn:length(article.content)==0}">
					    	   ${article.description} 
							</c:if>
							</span>
							<hr>
							<span><c:out value="${article.author}"></c:out> </span> <span
								style="margin-left: 20px"> <!-- <c:out value="${fed.publishedDate}"></c:out>  -->
								<fmt:formatDate value="${article.publishedDate}"
									pattern="yyyy年MM月dd日 HH时mm分" />
							</span> <span style="margin-left: 20px; font-weight: bolder;"> <a
								href="${article.uri}" target="_blank">查看原文</a> <a
								href="javascript:" style="float: right;"
								onclick="feeddetail('${article.feedId.id}')"><c:out
										value="${article.feedId.feedName}"></c:out> </a>
							</span>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-md-3">
				<div class="alert alert-success">
					<h4>热门频道</h4>
					<c:forEach var="chanel" items="${mostchannel}">
						<p>
							<button type="button" class="btn btn-default btn-lg"
								style="width: 100%">
								<span class="glyphicon glyphicon-map-marker" style="float: left"></span>
								<a href="./feedmanage?chanel=${chanel.key}" style="color: black"><c:out
										value="${chanel.value}"></c:out></a>
							</button>
						</p>
					</c:forEach>
				</div>
				<hr />
				<div class="alert alert-success">
					<h4>订阅最多</h4>
					<c:forEach var="reg" items="${mostreg}">
						<p>
							<button type="button" class="btn btn-default btn-lg"
								style="width: 100%">
								<span class="glyphicon glyphicon-send" style="float: left"></span>
								<a href="./feeddetail?feedid=${reg.key}" style="color: black"><c:out
										value="${reg.value}"></c:out></a>
							</button>
						</p>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="navbar navbar-default navbar-static-bottom"
		style="background-color: gray">
		<div class="container" style="text-align: center; color: white">
			<span>FeSa 版权所有 © 2006-2013 京公海网安备110108001536号 京ICP证110059号</span>
		</div>
	</div>
</body>
</html>