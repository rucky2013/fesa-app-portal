<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="utf-8">
    <title>FS 错误</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>css/jumbotron-narrow.css" rel="stylesheet">
	<style>
        .body{padding-top:70px}
      </style>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
  </head>
  <body>
    <jsp:include page="../templet/head.jsp"></jsp:include>
    <div class="navbar navbar-default navbar-static-top" role="navigation" style="margin-top: 0px;z-index:999">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button><a class="navbar-brand" href="main">FeSa</a>

        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav navbar-center">
            <li> <a href="main">推荐</a>
            </li>
            <li> <a href="person">我的订阅</a>
            </li>
            <li> <a href="feedmanage">订阅中心</a>
            </li>
            <li><a href="reader">自定义</a></li>
            <li> <a href="download">下载</a>
            </li>
            <li class="active"> <a href="about">关于</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row" style="margin-top:8px" id="rowinfo">
        <div class="col-md-12">
          <h1>出错了啊</h1>
          <span style="text-align: center;">${message}</span>
        </div>
      </div>
    </div>
    <div class="navbar navbar-default navbar-static-bottom" style="background-color:gray">
      <style>
        .body{padding-top:70px}
      </style>
      <div class="container" style="text-align:center;color:white">
        <span>FeSa 版权所有 © 2006-2013　京公海网安备110108001536号　京ICP证110059号</span>
      </div>
    </div>
  </body>
  <script type="text/javascript">
      $(function(){
    	  $("#rowinfo").css("min-height","568px");
      });
  </script>
</html>