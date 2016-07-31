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
<%@ taglib uri="/WEB-INF/tld/myJSTLFunction.tld" prefix="myfn" %>
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
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css"> 
<link href="<%=basePath%>css/jumbotron-narrow.css" rel="stylesheet" type="text/css">
<style type="text/css">
.body {
	padding-top: 70px
}
</style>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript">
    var sitelist=new Array();
	$(function() {
		$("#hidvalue").val(20);
		$(window).scroll(function() {
			if ($(this).scrollTop() > document.body.scrollHeight-700) {
				getmore();
			}
		});
	});
	function getChild(t,self){
		if(!$(self).hasClass("fcheck")){
			$(self).css("background-color","rgb(220,220,220)");
			$(self).addClass("fcheck");
			$.ajax({
				type : 'get',
				url : 'getchildren',
				datatype : 'json',
				data : 'speid='+ t,
				success : function(datas) {
					var res=eval("("+datas+")").getResult;
					var htmlstr='';
					if(res!=null&&res.length>0){
						 $(res).each(function(i,item){
							 sitelist.push(item);
							 htmlstr+='<div class="col-md-2 site_'+item.speid+'" id="site_id_'+item.id+'">';
							 htmlstr+='<a class="thumbnail" style="margin:0px;cursor:pointer">';
							 htmlstr+='<img src="'+'<%=basePath%>'+item.feedphoto+'" alt="图片加载失败" title="双击取消选择" ondblclick="site_remove('+item.id+')" />';    
							 //htmlstr+='<span style="vertical-align:top;float:right" onclick="site_remove('+item.id+')"><span class="glyphicon glyphicon-remove"></span></span>';
							 htmlstr+='</a>';
							 htmlstr+='</div>';
						 });
						 $("#rec_children").append(htmlstr);
						 $("#row_begin").css("display","normal");
					}else if(sitelist.length==0)
						$("#row_begin").css("display","none");
				}
			});
		}else{
			$(self).css("background-color","white");
			$(self).removeClass("fcheck");
			var tem_arr=new Array();
			var tag_id=t;
			$(sitelist).each(function(j,jtem){
				if(jtem.speid!=tag_id){
					tem_arr.push(jtem);
				}else{
					var del_id=".site_"+tag_id;
					$("#rec_children")[0].removeChild($(del_id)[0]);
				}
			});
			sitelist=tem_arr;
			recclean();
		}	
	}
	function site_remove(t){
		var remove_id=t;
		var temparr=new Array();
		var del_id="#site_id_"+t;
		$(sitelist).each(function(i,item){
			if(item.id!=remove_id)
				temparr.push(item);
		});
		$("#rec_children")[0].removeChild($(del_id)[0]);
		sitelist=temparr;
		recclean();
	}
	function recclean(){
		if(sitelist.length==0){
			$("#rec_parent Button").each(function(j,jtem){
				if($(jtem).hasClass("fcheck")){
					$(jtem).removeClass("fcheck");
					$(jtem).css("background-color","white");
				}
			});
			$("#row_begin").css("display","none");
		}
	}
	function letgo(){
		
	}
	function feeddetail(fedid) {
		window.location.href = "feeddetail?feedid=" + fedid;
	}
	function getsrc(s){
		var urls=[];
		s.replace(/\<img.*?src\=\"(.*?)\"[^>]*>/ig, function(a,b) {
		    urls.push(b);
		});
		if(urls.length>0)
			return urls[0];
		else
			return "";
	}
	function getmore(){
		var t=$("#hidvalue").val();
		//alert("获取："+t);
		$.ajax({
			type : 'post',
			url : 'getmoretujian',
			datatype : 'json',
			data : 'pageindex='+ t,
			success : function(datas) {
				if (eval("(" + datas + ")").getmoreResult != null&&eval("(" + datas + ")").getmoreResult.length>0) {
					var items = eval("(" + datas + ")").getmoreResult;
					var strhtml = "";
					$(items).each(function(i,item){
						var d=item.publishedDate!=null?item.publishedDate:item.createDate;
						var time = (parseInt(d.year) + 1900)
						+ "年"
						+ (parseInt(d.month) + 1)
						+ "月"
						+ parseInt(d.date)
						+ "日  "
						+ d.hours
						+ "时"
						+ d.minutes
						+ "分";
						strhtml+='<div class="col-md-4" style="padding-left:0px;padding-right:0px">';
						strhtml+='<div class="well">';
						strhtml+='<h3>';
						strhtml+='<a href="feeddescription?feedid='+item.feedId.id+'&&detailid='+item.id+'" target="_blank" style="color: black;">';
						strhtml+=item.title;
						strhtml+='</a>';
						strhtml+='</h3>';
						strhtml+='<p style="text-align:center">';
						strhtml+='<img src="'+getsrc(item.description)+'" style="max-width:293px" />';
						strhtml+='</p>';
						strhtml+='<span>';
						strhtml+=time;
						strhtml+='</span>';
						strhtml+='<span style="margin-left: 20px; font-weight: bolder;">';
						strhtml+='<a href="javascript:void(0)" style="float: right;" onclick="feeddetail('+item.feedId.id+')">';
						strhtml+=item.feedId.feedName;
						strhtml+='</a>';
						strhtml+='</span>';
						strhtml+='</div>';
						strhtml+='</div>';
					});
					$("#id_tj").append(strhtml);
					$("#hidvalue").val(parseInt(t) + 20);
				}
			}
		});
	}
</script>
</head>
<body>
	<jsp:include page="../templet/head.jsp"></jsp:include>
	<div id="nav_main" class="navbar navbar-default navbar-static-top" role="navigation" style="margin-top: 0px;z-index:999">
		<div class="container" id="doc">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="main">FeSa</a>
			</div>
			<jsp:include page="../templet/navbar.jsp"></jsp:include>
		</div>
	</div>
	<div class="container">
		<div class="row" style="margin-top: 8px">
			<div class="col-md-12" id="rec_parent">
			<c:forEach var="commend" items="${recommends}">
				<button type="button" class="btn btn-default btn-lg" onclick="getChild('${commend.id}',this)">
					<span class="${commend.icon}"></span>
					${commend.name}
				</button>
			</c:forEach>	
			</div>
		</div>	
		<div class="row" id="rec_children" style="padding:15px">
		</div>
		<div class="row" id="row_begin" style="display:none;" >
		  <div class="col-md-12">
		     <a class="btn btn-default btn-lg" style="width: 100%;" onclick="letgo()">开始推荐</a>
		  </div>   
		</div>
		<input type="hidden" value="20" id="hidvalue" />
		<div id="id_tj" class="row" style="margin-top:10px">
		<c:forEach var="article" items="${mostfile}" varStatus="status">
				<div class="col-md-4" style="padding-left:0px;padding-right:0px">
					<div class="well">
						<h3>
							<a href="feeddescription?feedid=${article.feedId.id}&&detailid=${article.id}" target="_blank" style="color: black;"> 
								<c:out value="${article.title}"></c:out>
							</a>
						</h3>
						<p style="text-align:center;">
						<img src="${myfn:getImageurl(article.description)}" style="max-width:293px" />
					    </p>
					    <span> 
							<fmt:formatDate value="${article.publishedDate}" pattern="yyyy.MM.dd HH:mm" />
						</span> 
						<span style="margin-left: 20px; font-weight: bolder;"> 
					    <a href="javascript:" style="float: right;" onclick="feeddetail('${article.feedId.id}')">
							<c:out value="${article.feedId.feedName}"></c:out> 
						</a>
						</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row">
		  <div class="col-md-12">
		  <a class="btn btn-default btn-lg" style="width: 100%" href="javascript:scroll(0,0)">没有内容了(回到顶端)</a>
		</div>
		</div>
	</div>
	<jsp:include page="../templet/bottom.jsp"></jsp:include>
</body>
</html>