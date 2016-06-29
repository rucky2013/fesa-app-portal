<%@page import="com.fs.app.portal.pojo.UserInfoPojo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!-- background-color: rgb(83, 83, 83); -->
<div id="nav_top" class="navbar navbar-static-top" role="navigation"
	style="width: 100%; height: 90px;background: url(<%=basePath%>images/top_bg.jpg) repeat-x 0 100%">
	<div class="container-fluid">
		<div class="navbar-header">
		     <a class="navbar-brand" href="main" > 
		        <img alt="feisa" style="margin-left: 190px" src="<%=basePath%>images/Logo.png">
		     </a>
		</div>
		<div style="float: right;">
			<div style="text-align: center; line-height: 90px; width: 400px">
				<a href="javascript:void(0)" style="font-size: large; padding: 15px; color: rgb(155, 233, 125); border-right: #746161 1px solid;">博客</a>
				<a href="././FSrss.xml" style="font-size: large; padding: 15px; color: rgb(155, 233, 125); border-right: #746161 1px solid;">RSS</a>
				<a href="javascript:void(0)" style="font-size: large; padding: 15px; color: rgb(155, 233, 125); border-right: #746161 1px solid;">论坛</a>
				<a href="javascript:void(0)" style="font-size: large; padding: 15px; color: rgb(155, 233, 125);" onclick="fsreader()">自定义阅读</a>
			</div>
		</div>
	</div>
</div>
<style type="text/css">
body{
	background-image:url("<%=basePath%>images/bg.jpg");
}
</style>
<script type="text/javascript">
	function fsreader() {
		this.window.location.href = "reader";
	}
</script>
<script type="text/javascript">
$(document).ready(function(){
	var _defautlTop = $("#navi-l").offset().top;
	$(window).scroll(function(){
	    if($(this).scrollTop() > _defautlTop){
	       $('#doc').addClass('headerFix');
	       $("#nav_main").addClass("navbar-fixed-top");
	       $("#nav_main").removeClass("navbar-static-top");
	    }else{
	       $('#doc').removeClass('headerFix');
	       $("#nav_main").addClass("navbar-static-top");
	       $("#nav_main").removeClass("navbar-fixed-top");
	    }
	});
});
</script>