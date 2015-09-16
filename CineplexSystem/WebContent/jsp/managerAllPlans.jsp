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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有放映计划</title>
<%@include file="js-css-import.jsp" %>
</head>
<body class="center_align_body">
<%@include file="manager-nav.jsp" %>
	<div class="center_align_div jumbotron">
	
		<table class="table table-hover">
			<tr>
				<td align="center">电影名</td>
				<td align="center">开始时间</td>
				<td align="center">结束时间</td>
				<td align="center">厅号</td>
				<td align="center">票价</td>
				<td align="center">审核状态</td>
				<td align="center">详情</td>
			</tr>
			<%
			ArrayList<MoviePlanBean> moviePlanBeans=(ArrayList<MoviePlanBean>)request.getAttribute("allPlans");
			for(MoviePlanBean moviePlanBean:moviePlanBeans){
				Movie movie=moviePlanBean.getMovie();
				List plans=moviePlanBean.getPlans();
				for(Plan plan:(List<Plan>)plans){
				%>
				<%
				if(plan.getApproved()=='0'){
					
				%>
				<tr class="info">
				<%	
				}else if(plan.getApproved()=='1'){
				%>
				<tr>
				<%	
				}else{
				%>
				<tr class="danger">
				<%	
				}
				%>
				
				<td align="center"><%=movie.getName() %></td>
				<td align="center"><%=plan.getStartTime() %></td>
				<td align="center"><%=plan.getEndTime() %></td>
				<td align="center"><%=plan.getHallId() %></td>
				<td align="center"><%=plan.getPrice() %>元</td>
				<td align="center">
				
					<%
					if(plan.getApproved()=='0'){
					%>
					尚未审核	
					<%
					}else if(plan.getApproved()=='1'){
				    %>
					审核通过
					<%
					}else{
					%>
					审核不过
					<%
					}
					%>
				</td>
				<td align="center">
				<a href="${pageContext.request.contextPath}/ManagerPlanDetail?planId=<%=plan.getId()%>">查看详情</a>
				</td>
				</tr>
				<%}
			%>
			
			<%
			}
			%>
		</table>
	</div>
<%@include file="toTop-import.jsp" %>
</body>
</html>