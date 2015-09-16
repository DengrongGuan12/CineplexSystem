<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="edu.nju.cineplex.action.business.TicketBean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务员操作页面</title>
<%@include file="js-css-import.jsp" %>
</head>
<body>
<%@include file="waiter-nav.jsp" %>
<%
ArrayList<TicketBean> ticketBeans=(ArrayList<TicketBean>)request.getAttribute("ticketBeans");
%>
<body>
<div class="jumbotron">
<table class="table table-hover">
	<tr>
		<td align="center">电影名称</td>
		<td align="center">放映日期</td>
		<td align="center">开始时间</td>
		<td align="center">结束时间</td>
		<td align="center">票价</td>
		<td align="center">厅号</td>
		<td align="center">剩余票数</td>
		<td align="center">当前状态</td>
		<td align="center">购票</td>
	</tr>
	<%
	for(TicketBean ticketBean:ticketBeans){
	%>
		<tr>
			<td align="center"><%=ticketBean.getMovieName() %></td>
			<td align="center"><%=ticketBean.getDate() %></td>
			<td align="center"><%=ticketBean.getStartTime() %></td>
			<td align="center"><%=ticketBean.getEndTime() %></td>
			<td align="center"><%=ticketBean.getPrice() %></td>
			<td align="center"><%=ticketBean.getHallId() %>号厅</td>
			<td align="center"><%=ticketBean.getRemainderSeatNum() %></td>
			<td align="center"><%=ticketBean.getState() %></td>
			<td align="center">
				<%
				if(ticketBean.getState().equals("尚未放映")&&ticketBean.getRemainderSeatNum()>0){
				%>
				<a href="${pageContext.request.contextPath}/WaiterSellTicket?planId=<%=ticketBean.getPlanId() %>">售票</a>
				<%	
				}else{
				%>
				无法售票！
				<%
				}
				%>
			</td>
		</tr>
	<%
	}
	%>
</table>
</div>

<%@include file="toTop-import.jsp" %>
</body>
</html>