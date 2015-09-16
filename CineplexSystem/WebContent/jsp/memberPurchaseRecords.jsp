<%@page import="edu.nju.cineplex.action.business.PurchaseRecordBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="member" uri="/WEB-INF/tlds/member.tld" %>
<!DOCTYPE>
<html>
<head>
<%ArrayList<PurchaseRecordBean> purchaseRecordBeans=(ArrayList<PurchaseRecordBean>)request.getAttribute("purchaseRecordBeans"); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的消费记录</title>
<%@include file="js-css-import.jsp" %>
</head>
<body>
<member:NavSelect/>
<div class="jumbotron">
<table class="table table-hover">
<tr>
<td align="center">消费时间</td>
<td align="center">所购电影</td>
<td align="center">放映日期</td>
<td align="center">开始时间</td>
<td align="center">结束时间</td>
<td align="center">放映厅</td>
<td align="center">所选座位</td>
<td align="center">票价</td>
<td align="center">实际支付</td>
</tr>
<%
for(PurchaseRecordBean purchaseRecordBean:purchaseRecordBeans){
%>
<tr>
<td align="center"><%=purchaseRecordBean.getPurchaseTime() %></td>
<td align="center"><%=purchaseRecordBean.getMovieName() %></td>
<td align="center"><%=purchaseRecordBean.getPlayDate() %></td>
<td align="center"><%=purchaseRecordBean.getStartTime() %></td>
<td align="center"><%=purchaseRecordBean.getEndTime() %></td>
<td align="center"><%=purchaseRecordBean.getHallId() %>号厅</td>
<td align="center"><%=purchaseRecordBean.getSeatId() %>号座</td>
<td align="center"><%=purchaseRecordBean.getPrice() %></td>
<td align="center"><%=purchaseRecordBean.getMoney() %></td>

</tr>


<%
}
%>
</table>
</div>

<%@include file="toTop-import.jsp" %>
</body>
</html>