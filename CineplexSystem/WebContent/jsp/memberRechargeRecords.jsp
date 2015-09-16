<%@page import="edu.nju.cineplex.action.business.RechargeRecordBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="member" uri="/WEB-INF/tlds/member.tld" %>
<!DOCTYPE>
<html>
<head>
<%ArrayList<RechargeRecordBean> rechargeRecordBeans=(ArrayList<RechargeRecordBean>)request.getAttribute("rechargeRecordBeans"); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>充值记录</title>
<%@include file="js-css-import.jsp" %>
</head>
<body>
<member:NavSelect/>
<div class="jumbotron">
<%
if(rechargeRecordBeans.size()==0){
%>

<h1>尚未缴费！</h1>

<%
}else{
%>
<table class="table table-hover">
	<tr>
		<td align="center">充值时间</td>
		<td align="center">充值金额</td>
	</tr>
	<%
	for(RechargeRecordBean rechargeRecordBean:rechargeRecordBeans){
	%>
	<tr>
	<td align="center"><%=rechargeRecordBean.getTime() %></td>
	<td align="center"><%=rechargeRecordBean.getMoney() %>元</td>
	</tr>
	<%
	}
	%>
</table>
<%
}
%>
</div>
<%@include file="toTop-import.jsp" %>
</body>
</html>