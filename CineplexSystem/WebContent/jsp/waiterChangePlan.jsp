<%@page import="edu.nju.cineplex.action.business.PlanBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="timeSelector" uri="/WEB-INF/tlds/timeSelector.tld" %>
<%@page import="edu.nju.cineplex.model.Hall"%>
<%@page import="edu.nju.cineplex.model.Movie"%>
<%@page import="edu.nju.cineplex.model.Plan"%>
<%@page import="edu.nju.cineplex.action.business.PlanDetailBean"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<title>修改放映计划</title>
<%@include file="js-css-import.jsp" %>
<%
PlanBean planBean=(PlanBean)request.getAttribute("planBean");
Plan plan=planBean.getPlan();
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function changePlan(){
		var movieId=document.getElementById("movieId").value;
		var planId=document.getElementById("planId").value;
		var planDate=document.getElementById("planDate");
		var planDate_err=document.getElementById("planDate_err");
		if(planDate.value==""){
			if(planDate_err.style.visibility=="hidden"){
				planDate_err.style.visibility="visible";
			}
			return false;
		}else{
			if(planDate_err.style.visibility=="visible"){
				planDate_err.style.visibility="hidden";
			}
		}
		var from_hour=document.getElementsByTagName("select")[0].value;
		var from_min=document.getElementsByTagName("select")[1].value;
		var to_hour=document.getElementsByTagName("select")[2].value;
		var to_min=document.getElementsByTagName("select")[3].value;
		var time_err=document.getElementById("time_err");
		if(parseInt(from_hour)>parseInt(to_hour)){
			if(time_err.style.visibility=="hidden"){
				time_err.style.visibility="visible";
			}
			return false;
		}else if(parseInt(from_hour)==parseInt(to_hour)){
			if(parseInt(from_min)>=parseInt(to_min)){
				if(time_err.style.visibility=="hidden"){
					time_err.style.visibility="visible";
				}
				return false;
			}
			
		}
		if(time_err.style.visibility=="visible"){
			time_err.style.visibility="hidden";
		}
		var hallId=document.getElementById("hallId");
		var hallId_err=document.getElementById("hallId_err");
		if(hallId.value==""){
			if(hallId_err.style.visibility=="hidden"){
				hallId_err.style.visibility="visible";
			}
			return false;
		}else{
			if(hallId_err.style.visibility=="visible"){
				hallId_err.style.visibility="hidden";
			}
		}
		var price=document.getElementById("price");
		var price_err=document.getElementById("price_err");
		var price_pattern=/^\d+\.?\d{0,1}$/;
		if(!price_pattern.test(price.value)){
			if(price_err.style.visibility=="hidden"){
				price_err.style.visibility="visible";
			}
			return false;
		}else{
			if(price_err.style.visibility=="visible"){
				price_err.style.visibility="hidden";
			}
		}
		var no_change=document.getElementById("no_change");
		if(planDate.value=="<%=planBean.getDate() %>"&&from_hour=="<%=planBean.getStartHour() %>"
		&&from_min=="<%=planBean.getStartMin()%>"&&to_hour=="<%=planBean.getEndHour() %>"
		&&to_min=="<%=planBean.getEndMin()%>"&&hallId.value=="<%=plan.getHallId()%>"&&price.value=="<%=plan.getPrice()%>"){
			no_change.style.visibility="visible";
			return false;
		}else{
			no_change.style.visibility="hidden";
		}
		var change_suc=document.getElementById("change_suc");
		XMLHttp.send("post","${pageContext.request.contextPath}/WaiterChangePlan","movieId="+movieId+"&planId="+planId
				+"&planDate="+planDate.value+"&from_hour="+from_hour+"&from_min="+from_min
				+"&to_hour="+to_hour+"&to_min="+to_min+"&hallId="+hallId.value+"&price="
				+price.value,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			if(result=="1"){
				if(change_suc.style.visibility=="hidden"){
					change_suc.style.visibility="visible";
				}
				window.location.replace("${pageContext.request.contextPath}/WaiterAllMovies");
			}else{
				if(change_suc.style.visibility=="visible"){
					change_suc.style.visibility="hidden";
				}
				window.alert(result);
				return false;
			}
		}
		
		return false;
	}
	function setSelect(){
		document.getElementsByTagName("select")[0].value=<%=planBean.getStartHour()%>;
		document.getElementsByTagName("select")[1].value=<%=planBean.getStartMin()%>;
		document.getElementsByTagName("select")[2].value=<%=planBean.getEndHour()%>;
		document.getElementsByTagName("select")[3].value=<%=planBean.getEndMin()%>;
	}
</script>
</head>
<body onload="setSelect()">
<%@include file="waiter-nav.jsp" %>
<div class="jumbotron" align="center">

<table class="table table-hover">
	<tr>
		<td align="center">放映日期</td>
		<td align="center">
			<input type="hidden" id="movieId" value="<%=plan.getMovieId() %>" />
			<input type="hidden" id="planId" value="<%=plan.getId() %>" />
			<input style="width:20%;" class="Wdate form-control height30" type="text" onClick="WdatePicker()"  value="<%=planBean.getDate() %>" name="planDate" id="planDate"/>
			<div style="visibility:hidden;" class="log_err" id="planDate_err">日期不能为空</div>
		</td>
	</tr>
	<tr>
		<td align="center">开始时间</td>
		<td align="center">
			<timeSelector:hourSelector/>时<timeSelector:minuteSelector/>分
		</td>
	</tr>
	<tr>
		<td align="center">结束时间</td>
		<td align="center">
			<timeSelector:hourSelector/>时<timeSelector:minuteSelector/>分
			<div class="log_err" style="visibility:hidden;" id="time_err">请确保开始时间在结束时间之前！</div>
		</td>
	</tr>
	<tr>
		<td align="center">放映厅</td>
		<td align="center">
			<input style="width:20%;" class="form-control" type="text" name="hallId" id="hallId" value="<%=plan.getHallId() %>"/>
			<div class="log_err" style="visibility:hidden;" id="hallId_err">放映厅号不能为空！</div>
		</td>
	</tr>
	<tr>
		<td align="center">票价</td>
		<td align="center">
			<input style="width:20%;" class="form-control" type="text" name="price" id="price" value="<%=plan.getPrice() %>"/>
			<div class="log_err" style="visibility:hidden;" id="price_err">票价必须是小数位数小于等于两位的数字！</div>
		</td>
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
	}
	%>
	<tr>
		<td colspan="2" align="center"><input type="button" value="提交修改" onclick="changePlan()" class="btn btn-default"></td>
	</tr>
	
</table>
<div class="log_err" style="visibility:hidden;" id="no_change">未进行修改，无法提交！</div>
<div class="green" style="visibility:hidden;" id="change_suc">修改成功，请等待经理审核！</div>
	</div>
<%@include file="toTop-import.jsp" %>
</body>
</html>