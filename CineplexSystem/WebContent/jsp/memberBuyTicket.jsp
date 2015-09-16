<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="edu.nju.cineplex.action.business.SimplePlanBeans"%>
<%@page import="edu.nju.cineplex.action.business.SimplePlanBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.cineplex.action.business.TicketBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="member" uri="/WEB-INF/tlds/member.tld" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<title>购票</title>
<%@include file="js-css-import.jsp" %>
<%
TicketBean ticketBean=(TicketBean)request.getAttribute("ticketBean");
SimplePlanBeans simplePlanBeans2=(SimplePlanBeans)request.getAttribute("likedPlanBeans");
ArrayList<SimplePlanBean> simplePlanBeans=simplePlanBeans2.getSimplePlanBeans();
HashSet<String> movieIdSet=simplePlanBeans2.getMovieIdSet();
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
	seat="";
	function choose(obj){
		obj.style.display="none";
		document.getElementById("giveup"+obj.value).style.display="inline";
		if(seat!=""){
			document.getElementById("choose"+seat).style.display="inline";
			document.getElementById("giveup"+seat).style.display="none";
		}
		seat=obj.value;
	}
	function giveup(obj){
		obj.style.display="none";
		document.getElementById("choose"+obj.value).style.display="inline";
		seat="";
	}
	function submit(){
		if(seat==""){
			window.alert("请选择座位！");
			return;
		}
		var money=document.getElementById("money").innerHTML;
		if(money<<%=ticketBean.getPrice()%>){
			window.alert("你的余额不足！请尽快充值！");
			return;
		}
		
		XMLHttp.send("post","${pageContext.request.contextPath}/MemberBuyTicket","planId=<%=ticketBean.getPlanId()%>&seatId="+seat,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			window.alert(result);
			window.location.replace("${pageContext.request.contextPath}/MemberBuyTicket?planId=<%=ticketBean.getPlanId()%>");
			
			
		}
	}
	function checkImage(){
		var image=document.getElementById("image");
		var image_url="${pageContext.request.contextPath}/image/<%=ticketBean.getMovieId()%>.gif";
		if(CheckImgExists(image_url)){
			image.src=image_url;
		}
		checkImages();
	}
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
</head>
<body onload="checkImage()">
<member:NavSelect/>
<div class="main">
   	 <div class="wrap">
   	 	<div class="section group">
				<div class="cont-desc span_1_of_2">
				  <div class="product-details">				
					<div class="grid images_3_of_2">
						<img src="${pageContext.request.contextPath}/image/preview.jpg" id="image" alt="" />
				  </div>
				<div class="desc span_3_of_2">
					<h2><%=ticketBean.getMovieName() %></h2>
					<p>描述:<%=ticketBean.getDescription() %></p>					
					<div class="price">
						<p>价格（普通）: <span><%=ticketBean.getPrice() %>元</span></p>
					</div>
					<div class="available">
						<ul>
						  <li><span>放映日期:</span>&nbsp; <%=ticketBean.getDate() %></li>
						  <li><span>开始时间:</span>&nbsp; <%=ticketBean.getStartTime()%></li>
						  <li><span>结束时间:</span>&nbsp; <%=ticketBean.getEndTime()%></li>
						  <li><span>厅号:</span>&nbsp; <%=ticketBean.getHallId()%>号厅</li>
					    </ul>
					</div>
				<div class="share-desc">
					<div class="share">
						<p>账户余额：&nbsp; <span id="money"><%=ticketBean.getMoney() %></span>元</p>		
					</div>
					<div class="button"><span><input type="button" class="btn btn-warning" onclick="submit()" value="购票"></span></div>					
					<div class="clear"></div>
				</div>
				<div class="wish-list">
				 	<ul>
				 		<li class="wish"><span>总票数:</span>&nbsp; <%=ticketBean.getSeatNum()%></li>
				 	    <li class="compare"><span>剩余票数:</span>&nbsp; <%=ticketBean.getRemainderSeatNum()%></li>
				 	</ul>
				 </div>
				 
			</div>
			<div class="clear"></div>
		  </div>
		<div class="product_desc" align="center">	
			 <h2>选择座位 :</h2>
			 前
<%
			boolean[][] seat=ticketBean.getSeat();
			int i=1;
			for(boolean[] seatRow:seat){
			%>
			<div class="center_align_div">
			<%
				for(boolean seatState:seatRow){
					if(seatState){
					%>
						<input type="button" value="<%=i%>" style="width:30px;background:#08F608;display:inline;" onclick="choose(this)" id="choose<%=i%>">
						<input type="button" value="<%=i%>" style="width:30px;background:blue;display:none;" onclick="giveup(this)" id="giveup<%=i %>" >
					<%
					}else{
					%>
						<input type="button" value="<%=i%>" disabled="disabled" style="background:red;width:30px;" >
					<%
					}
					i++;
				}
				%>
				</div>
				<%
			}
			%>

后
	   </div>
   </div>
				<div class="rightsidebar span_3_of_1 sidebar">
					<h2>猜你喜欢</h2>
						<%
						for(SimplePlanBean simplePlanBean:simplePlanBeans){
						%>
						<div class="special_movies">
					 	   <div class="movie_poster">
					 		  <a href="${pageContext.request.contextPath}/MemberBuyTicket?planId=<%=simplePlanBean.getPlanId()%>"><img src="${pageContext.request.contextPath}/image/black-swan.jpg" alt="" name="<%=simplePlanBean.getMovieId()%>"/></a>
					 		 </div>
					 		   <div class="movie_desc">
							    <h3><a href="${pageContext.request.contextPath}/MemberBuyTicket?planId=<%=simplePlanBean.getPlanId()%>"><%=simplePlanBean.getMovieName() %></a></h3>
								   <p>&nbsp; <%=simplePlanBean.getPrice() %></p>
								     <span><a href="${pageContext.request.contextPath}/MemberBuyTicket?planId=<%=simplePlanBean.getPlanId()%>">购票</a></span>
								 </div>
								<div class="clear"></div>
					 		</div>	
						<%
						}
						%>
 					   </div>
 		 		 </div>
   	 		</div>
        </div>

<%@include file="toTop-import.jsp" %>
</body>
</html>