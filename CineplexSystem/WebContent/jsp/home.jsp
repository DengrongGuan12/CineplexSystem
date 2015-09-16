<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Hashtable"%>
<%@page import="edu.nju.cineplex.enums.MoviePlayState"%>
<%@page import="edu.nju.cineplex.action.business.SimplePlanBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="edu.nju.cineplex.action.business.PlansOfDaysBean"%>
<%@page import="edu.nju.cineplex.action.business.TicketBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="member" uri="/WEB-INF/tlds/member.tld" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页面</title>
<link href="${pageContext.request.contextPath}/css/style-new.css" rel="stylesheet" type="text/css" media="all"/>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/move-top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nivo.slider.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
</head>
<%
PlansOfDaysBean plansOfDaysBean=(PlansOfDaysBean)request.getAttribute("plansOfDaysBean");
Hashtable<String,ArrayList<SimplePlanBean>> date_simplePlanBeans=plansOfDaysBean.getDay_simplePlanBeans();
Map.Entry[] set = PlansOfDaysBean.getSortedHashtableByKey(date_simplePlanBeans);
HashSet<String> movieIdSet=plansOfDaysBean.getMovieIdSet();
%>
<script type="text/javascript">
	function checkImages(){
		<%
		Iterator it=movieIdSet.iterator();
		while(it.hasNext()){
			String imageName=(String)it.next();
		%>
		var image_url="${pageContext.request.contextPath}/image/<%=imageName%>.gif";
		if(CheckImgExists(image_url)){
			var array = document.getElementsByName("<%=imageName%>");
			for (var i = 0; i < array.length; i++) {
				array[i].src=image_url;
			}
		}
		
		<%
	    }
		%>
	}
</script>
<body onload="checkImages()">

<member:NavSelect/>
<div class="main">
  	<div class="wrap">
      <div class="content">
      		<%
      			for(int n=0;n<set.length;n++){
      		%>
      		<div class="content_top">
	    		<div class="heading">
	    			<h3><%=set[n].getKey().toString() %></h3>
	    		</div>
	    	</div>
	    	<%
	    	ArrayList<SimplePlanBean> simplePlanBeans=date_simplePlanBeans.get(set[n].getKey().toString());
	    	int size=simplePlanBeans.size();
	    	int section_grp_num=0;
	    	if(size%5==0){
	    		section_grp_num=size/5;
	    	}else{
	    		section_grp_num=size/5+1;
	    	}
	    	int index=0;
	    	for(int i=1;i<=section_grp_num;i++){
	    		int j=0;
	    	%>
	    	<div class="section group">
    		<%
    		while(j<5 && index<size){
    			SimplePlanBean simplePlanBean=simplePlanBeans.get(index);
    		%>
    		<div class="grid_1_of_5 images_1_of_5">
					 <img src="${pageContext.request.contextPath}/image/black-swan.jpg" name="<%=simplePlanBean.getMovieId() %>"/>
					 <h2><%=simplePlanBean.getMovieName() %></h2>
					<div class="price-details">
				       <div class="price-number">
							<p><span class="rupees"><%=simplePlanBean.getPrice() %>元</span></p>
					   </div>
					   <div class="add-cart">								
							<h4>
							<%
							if(simplePlanBean.getMoviePlayState()==MoviePlayState.NotPlay){
							%>
							<a href="${pageContext.request.contextPath}/MemberBuyTicket?planId=<%=simplePlanBean.getPlanId()%>">购票</a>
							<%	
							}else if(simplePlanBean.getMoviePlayState()==MoviePlayState.Played){
							%>
							<a>放映结束</a>
							<%
							}else{
							%>
							<a>正在放映</a>
							<%	
							}
							%>
							</h4>
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
	    	
      		<%
      			}
      		%>
       </div>
  </div>
</div>

<%@include file="toTop-import.jsp" %>
<body>
</html>