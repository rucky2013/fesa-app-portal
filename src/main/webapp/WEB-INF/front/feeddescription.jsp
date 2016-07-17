<%@page import="com.fs.commons.app.pojo.BaseFeedPojo"%>
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
<title>FS 阅读</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>css/jumbotron-narrow.css" rel="stylesheet">
<style>
.body {
	padding-top: 70px
}
code{
    white-space:normal;
    color:black;
    font-family: Microsoft Yahei;
}
.art-summary{
 font-family: Microsoft Yahei;
}
.art-summary img{
    max-width: 600px;
    margin: 10px auto;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.4);
    display: block;
}
.art-detail{
font-family: Microsoft Yahei;
}
.art-detail img{
    max-width: 600px;
    margin: 10px auto;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.4);
    display: block;
}
</style>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
</head>
<body>
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
					<li><a href="about">关于</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row" style="margin-top: 8px" id="rowinfo">
			<div class="col-md-9">
				<div id="contentinfos">
					<div class="well">
						<h3 style="text-align: center;">
							<c:out value="${currentdetail.title}"></c:out>
						</h3>
						<hr style="height: 1px; border: none; border-top: 1px solid #555555;">
						<span class="art-detail"> 
					    <c:if test="${currentdetail.description!=null}">
						   <p>${currentdetail.description}</p> 
	                    </c:if>
						<c:if test="${currentdetail.description==null&&currentdetail.content!=null}">
				    	   <p>${currentdetail.content}</p> 
						</c:if>
						</span>
						<hr>
						<span
							style="margin-left: 20px"> <!-- <c:out value="${fed.publishedDate}"></c:out>  -->
							<c:if test="${currentdetail.publishedDate==null}">
							<fmt:formatDate value="${currentdetail.createDate}"
								pattern="yyyy年MM月dd日 HH时mm分" />
							</c:if>
							<c:if test="${currentdetail.publishedDate!=null}">
							<fmt:formatDate value="${currentdetail.publishedDate}"
								pattern="yyyy年MM月dd日 HH时mm分" />
							</c:if>
						</span> <span style="margin-left: 20px; font-weight: bolder;"><a
							href="${currentdetail.uri}" target="_blank">查看原文</a> </span>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div style="padding-left:50px">
					<img 
						src="<%=basePath%>${currentfeed.feedPhoto}"
						class="img-responsive img-rounded">
				</div>
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
		$("#rowinfo").css("min-height", "460px");
	});
</script>
</html>