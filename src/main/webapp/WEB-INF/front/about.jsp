<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>FS 我们的团队</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/jumbotron-narrow.css" rel="stylesheet">
<style type="text/css">
.body {
	padding-top: 70px
}
</style>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
</head>
<body class="">
    <jsp:include page="../templet/head.jsp"></jsp:include>
	<div id="nav_main" class="navbar navbar-default navbar-static-top" role="navigation" style="margin-top: 0px;z-index:999">
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
					<li><a href="main">推荐</a></li>
					<li><a href="person">我的订阅</a></li>
					<li><a href="feedmanage">订阅中心</a></li>
					<li><a href="reader">自定义</a></li>
					<li><a href="download">下载</a></li>
					<li class="active"><a href="about">关于</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container" id="rowinfo">
		<div class="row" style="margin-top: 8px">
			<div class="col-md-12">
				<h1>FeSa 阅读</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<blockquote>
					FS,FeSa,取自feed search的含义,我们的宗旨是为了更好的阅读而努力,我们不是资讯的生产者,但是我们会把资讯整理起来,让阅读变得更舒适。
					<p></p>
				</blockquote>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h1>关于我们</h1>
				<blockquote>
					<p>我们是一个团队,我们的目标只有一个就是让阅读变的更舒适。我们的手段只有一个就是让机器更加的了解人们的想法。</p>
				</blockquote>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h1>我们能提供的服务</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<blockquote>
					<p>各种类的新闻资讯，有你想要的频道，有你想要的站点，要是没能满足你，你可以告诉我，我们的蜘蛛会每天寻找着新的资源，送到你的面前。</p>
				</blockquote>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h1>联系我们</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<blockquote>
					邮件：244328187@qq.com&nbsp; <br> <br>Web版：http://www.feedsear.com&nbsp;
					<br> <br>移动版：http://app.feedsear.com
					<p></p>
				</blockquote>
			</div>
		</div>
	</div>
	<div class="navbar navbar-default navbar-static-bottom"
		style="background-color: gray">
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
	$(function() {
		$("#rowinfo").css("min-height", "568px");
	});
</script>
</html>