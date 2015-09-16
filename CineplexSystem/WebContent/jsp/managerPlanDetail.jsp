<%@page import="edu.nju.cineplex.model.Hall"%>
<%@page import="edu.nju.cineplex.model.Movie"%>
<%@page import="edu.nju.cineplex.model.Plan"%>
<%@page import="edu.nju.cineplex.action.business.PlanDetailBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>放映计划详情</title>
<%@include file="js-css-import.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<%
PlanDetailBean planDetailBean=(PlanDetailBean)request.getAttribute("planDetail");
Movie movie=planDetailBean.getMovie();
Plan plan=planDetailBean.getPlan();
Hall hall=planDetailBean.getHall();
%>
<script type="text/javascript">
	function examine(){
		var reason=document.getElementById("reason");
		var reason_err=document.getElementById("reason_err");
		var result=document.getElementById("result");
		if(reason.value==""&&result.value=="refuse"){
			reason_err.style.display="block";
			return false;
		}else{
			reason_err.style.display="none";
		}
		var planId=document.getElementById("planId");
		
		XMLHttp.send("post","${pageContext.request.contextPath}/ManagerPlanDetail","planId="+planId.value
				+"&result="+result.value+"&reason="+reason.value,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			if(result=="1"){
				location.replace("${pageContext.request.contextPath}/ManagerPlanDetail?planId=<%=plan.getId()%>");
			}else{
				
				window.alert(result);
				return false;
			}
		}
		return false;
		
	}
	function t(obj){
		
		if(obj.value=="refuse"){
			document.getElementById("reason").style.display="block";
		}else{
			document.getElementById("reason_err").style.display="none";
			document.getElementById("reason").style.display="none";
		}
	}
</script>
</head>
<body class="center_align_body">
<%@include file="manager-nav.jsp" %>
<div class="center_align_div">


<table class="table table-hover">
	<tr>
		<td align="center">电影名称</td>
		<td align="center"><%=movie.getName() %></td>
	</tr>
	<tr>
		<td align="center">电影描述</td>
		<td align="center"><%=movie.getDescription() %></td>
	</tr>
	<tr>
		<td align="center">电影下线时间</td>
		<td align="center"><%=movie.getEndTime() %></td>
	</tr>
	<tr>
		<td align="center">放映开始时间</td>
		<td align="center"><%=plan.getStartTime() %></td>
	</tr>
	<tr>
		<td align="center">放映结束时间</td>
		<td align="center"><%=plan.getEndTime() %></td>
	</tr>
	<tr>
		<td align="center">放映厅</td>
		<td align="center"><%=plan.getHallId() %>号厅</td>
	</tr>
	<tr>
		<td align="center">座位数</td>
		<td align="center"><%=hall.getSeatNum() %></td>
	</tr>
	<tr>
		<td align="center">票价</td>
		<td align="center"><%=plan.getPrice() %>元</td>
	</tr>
	
	<%
	if(plan.getApproved()=='0'){
	%>
	<tr>
		<td align="center">
			审核状态
		</td>
		<td align="center">
			尚未审核
		</td>
	</tr>
	<tr>
		<td align="center">
			审核
		</td>
		<td align="center">
				<input type="hidden" value="<%=plan.getId()%>" name="planId" id="planId">
				<select id="result" onchange="t(this)">
				<option value="approve" selected="selected">批准</option>
				<option value="refuse">不批准</option>
				</select>
				<textarea rows="" cols="40px" placeholder="输入不批准的理由" style="display:none;" id="reason"></textarea>
				<br />
				<div class="log_err" id="reason_err" style="display:none;">理由不能为空！</div>
				
			
		</td>
		
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input class="btn btn-default" type="button" value="提交审核" onclick="examine()">
			
		</td>
		
	</tr>
	
	<%
	}else if(plan.getApproved()=='2'){
	%>
	<tr>
		<td align="center">审核状态</td>
		<td align="center">不批准</td>
	</tr>
	<tr>
		<td align="center">原因</td>
		<td align="center"><%=plan.getReason() %></td>
	</tr>
	<%	
	}else{
	%>
	<tr>
		<td align="center">审核状态</td>
		<td align="center">批准</td>
	</tr>
	<%
	}
	%>
	
</table>

</div>

</body>
</html>