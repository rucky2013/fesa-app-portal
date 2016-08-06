<%@page import="com.fs.commons.app.pojo.UserInfoPojo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	UserInfoPojo user_temp = (UserInfoPojo) session.getValue("user");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/myJSTLFunction.tld" prefix="myfn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>FS 推荐</title>
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
</head>
<body>
	<jsp:include page="../templet/head.jsp"></jsp:include>
	<div id="nav_main" class="navbar navbar-default navbar-static-top"
		role="navigation" style="margin-top: 0px; z-index: 999">
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
			<jsp:include page="../templet/navbar.jsp"></jsp:include>
		</div>
	</div>
	<div class="container">
		<div class="row" id="rec_children" style="padding: 15px"></div>
		<div id="id_tj" class="row" style="margin-top: 10px">
			<c:forEach var="article" items="${mostfile}" varStatus="status">
				<div class="col-md-4" style="padding-left: 0px; padding-right: 0px">
					<div class="well">
						<h3>
							<a
								href="feeddescription?feedid=${article.feedId.id}&&detailid=${article.id}"
								target="_blank" style="color: black;"> <c:out
									value="${article.title}"></c:out>
							</a>
						</h3>
						<p style="text-align: center;">
							<img src="${myfn:getImageurl(article.description)}"
								style="max-width: 293px" />
						</p>
						<span> <fmt:formatDate value="${article.publishedDate}"
								pattern="yyyy.MM.dd HH:mm" />
						</span> <span style="margin-left: 20px; font-weight: bolder;"> <a
							href="javascript:" style="float: right;"
							onclick="feeddetail('${article.feedId.id}')"> <c:out
									value="${article.feedId.feedName}"></c:out>
						</a>
						</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row">
			<div class="col-md-12">
				<a class="btn btn-default btn-lg" style="width: 100%"
					href="javascript:scroll(0,0)">没有内容了(回到顶端)</a>
			</div>
		</div>
	</div>
	<input type="hidden" id="ptime" value="0"  />
	<input type="hidden" id="crawler" value="${crawler}" />
	<jsp:include page="../templet/bottom.jsp"></jsp:include>
	<script type="text/javascript">
	$(function() {
		getmore();
		$(window).scroll(function() {
			if ($(this).scrollTop() > document.body.scrollHeight-920) {
				getmore();
			}
		});
	});
	function getmore() {
		$.ajax({
			type : 'get',
			url : $("#crawler").val()+'news/getNewsDataForPage',
			datatype : 'json',
			data : {
				ptimestamp:$("#ptime").val(),
				ptype:'loadmore'
			},
			success : function(datas) {
				if (datas.datas.length>0) {
					var items = datas.datas;
					var strhtml = "";
					var _time="";
					$(items).each(function(i, item) {
						_time=item.timestamp;
						strhtml += '<div class="col-md-4" style="padding-left:0px;padding-right:0px">';
						strhtml += '<div class="well">';
						strhtml += '<h3>';
						strhtml += '<a href="newdetail?newid='+ item.id+ '" target="_blank" style="color: black;">';
						strhtml += item.title;
						strhtml += '</a>';
						strhtml += '</h3>';
						strhtml += '<p style="text-align:center">';
						strhtml += '<img src="'+ item.image+ '" style="max-width:293px" />';
						strhtml += '</p>';
						strhtml += '<span>';
						strhtml += item.time;
						strhtml += '</span>';
						strhtml += '<span style="margin-left: 20px; font-weight: bolder;">';
						strhtml += '<a href="feeddetail?feedtitle='+ item.source+'" style="float: right;">';
						strhtml += item.source;
						strhtml += '</a>';
						strhtml += '</span>';
						strhtml += '</div>';
						strhtml += '</div>';
					});
					$("#id_tj").append(strhtml);
					$("#ptime").val(_time);
				}
			}
		});
	}
	</script>
</body>
</html>