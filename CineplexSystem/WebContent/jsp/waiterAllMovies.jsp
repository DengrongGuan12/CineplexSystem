<%@page import="edu.nju.cineplex.action.business.SimpleMovieBean"%>
<%@page import="edu.nju.cineplex.model.Plan"%>
<%@page import="edu.nju.cineplex.action.business.MoviePlanBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.cineplex.model.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<%
ArrayList<SimpleMovieBean> simpleMovieBeans=(ArrayList<SimpleMovieBean>)request.getAttribute("simpleMovieBeans");
int size=simpleMovieBeans.size();
int section_grp_num=0;
if(size%5==0){
	section_grp_num=size/5;
}else{
	section_grp_num=size/5+1;
}
int index=0;
%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有电影</title>
<link href="${pageContext.request.contextPath}/css/style-new.css" rel="stylesheet" type="text/css" media="all"/>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/move-top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nivo.slider.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
function checkImages(){
	<%
	for(int i=0;i<size;i++){
		SimpleMovieBean simpleMovieBean=simpleMovieBeans.get(i);
		String imageName=simpleMovieBean.getId();
	%>
	var image_url="${pageContext.request.contextPath}/image/<%=imageName%>.gif";
	if(CheckImgExists(image_url)){
		var image=document.getElementById("<%=imageName %>");
		image.src=image_url;
	}
	<%
	}
	%>
}

</script>
</head>
<body onload="checkImages()">
<%@include file="waiter-nav.jsp" %>
<div class="main">
  	<div class="wrap">
      <div class="content">
    	<div class="content_top">
    		<div class="heading">
    		<h3>所有电影</h3>
    		</div>
    	</div>
	      
			<%
    	for(int i=1;i<=section_grp_num;i++){
    		int j=0;
    	%>
    	<div class="section group">
    		<%
    		while(j<5 && index<size){
    			SimpleMovieBean simpleMovieBean=simpleMovieBeans.get(index);
    		%>
    		<div class="grid_1_of_5 images_1_of_5">
					 <a href="${pageContext.request.contextPath}/WaiterSingleMovie?movieId=<%=simpleMovieBean.getId() %>"><img src="${pageContext.request.contextPath}/image/black-swan.jpg" alt="查看详情" id="<%=simpleMovieBean.getId() %>"/></a>
					 <h2><a href="${pageContext.request.contextPath}/WaiterSingleMovie?movieId=<%=simpleMovieBean.getId() %>"><%=simpleMovieBean.getName() %></a></h2>
					<div class="price-details">
				       <div class="price-number">
							<p><span class="rupees"><%=simpleMovieBean.getOffLine() %></span></p>
					   </div>
					   <div class="add-cart">								
							<h4><a href="${pageContext.request.contextPath}/WaiterSingleMovie?movieId=<%=simpleMovieBean.getId() %>">查看详情</a></h4>
					   </div>
					   <div class="clear"></div>
					</div>					 
			</div>
    		<%
    			index++;
    			j++;
    		}
    		%>
    	</div>
    	<%
    	}
    	%>
       </div>
  </div>
</div>

<%@include file="toTop-import.jsp" %>
</body>
</html>