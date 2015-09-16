<%@page import="edu.nju.cineplex.action.business.PlanActivityBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<title>服务员添加活动</title>
<%@include file="js-css-import.jsp" %>
<%
ArrayList<PlanActivityBean> planActivityBeans=(ArrayList<PlanActivityBean>)request.getAttribute("planActivityBeans");
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
	var count=2;
	function addOption(){
		if(count==4){
			window.alert("最多只能4个选项！");
			return;
		}
		count++;
		var letter=getLetter(count);
		var newOption="<input type='text' value='"+letter+":' id='option"+letter+"'>";
		document.getElementById("options").insertAdjacentHTML("beforeEnd", newOption);
	}
	function getLetter(num){
		if(num==3){
			return "C";
		}else{
			return "D";
		}
	}
	function removeOption(){
		if(count==2){
			window.alert("最少两个选项！");
			return;
		}
		
		var letter=getLetter(count);
		document.getElementById("options").removeChild(document.getElementById("option"+letter));
		count--;
	}
	function submit(){
		var question=document.getElementById("question");
		if(question.value==""){
			window.alert("请输入题目！");
			question.focus();
			return;
		}
		var optionA=document.getElementById("optionA");
		var optionA_pattern=/^A:.*/;
		if(!optionA_pattern.test(optionA.value)){
			window.alert("该选项内容必须以A:开头,且内容不超过4个字！");
			optionA.focus();
			return;
		}
		
		var optionB=document.getElementById("optionB");
		var optionB_pattern=/^B:.*/;
		if(!optionB_pattern.test(optionB.value)){
			window.alert("该选项内容必须以B:开头,且内容不超过4个字！");
			optionB.focus();
			return;
		}
		var optionC_value="";
		var optionC=document.getElementById("optionC");
		var optionC_pattern=/^C:.*/;
		if(optionC!=null){
			if(!optionC_pattern.test(optionC.value)){
				window.alert("该选项内容必须以C:开头,且内容不超过4个字！");
				optionC.focus();
				return;
			}
			optionC_value=optionC.value;
		}
		var optionD_value="";
		var optionD=document.getElementById("optionD");
		var optionD_pattern=/^D:.*/;
		if(optionD!=null){
			if(!optionD_pattern.test(optionD.value)){
				window.alert("该选项内容必须以D:开头,且内容不超过4个字！");
				optionD.focus();
				return;
			}
			optionD_value=optionD.value;
		}
		
		var planIds="";
		<%
		for(PlanActivityBean planActivityBean:planActivityBeans){
			if(!planActivityBean.isIfOffLine()&&planActivityBean.getQuestion()==null){
			%>
			if(document.getElementById("<%=planActivityBean.getPlanId()%>").checked){
				planIds=planIds+"<%=planActivityBean.getPlanId()%>"+";";
			}
			<%
			}
		}
		%>
		if(planIds==""){
			window.alert("至少选择一个匹配的放映计划！");
			return;
		}
		XMLHttp.send("post","${pageContext.request.contextPath}/WaiterAddActivity","question="+question.value+"&optionA="+optionA.value+"&optionB="+optionB.value+"&optionC="+optionC_value+"&optionD="+optionD_value+"&planIds="+planIds,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			window.alert(result);
			window.location.reload();
		}
		
	}
	
</script>
</head>
<body>
<%@include file="waiter-nav.jsp" %>
<div align="center" class="jumbotron">

	<div align="center"><input type="text" placeholder="题目内容" id="question" class="form-control" style="width:20%;margin-top:-20px;margin-bottom:10px;"></div>
	
	<div align="center">
	添加选项:<input type="button" value="+" onclick="addOption()">
	删除选项:<input type="button" value="-" onclick="removeOption()">
	</div>
	<br>
	
	<div align="center" id="options">
		<input type="text" value="A:" id="optionA">
		<input type="text" value="B:" id="optionB">
	</div>
	<br>
	选择匹配的放映计划：<br>
	<table class="table table-hover">
		<tr>
			<td align="center">电影名称</td>
			<td align="center">下线时间</td>
			<td align="center">问题描述</td>
			<td align="center">是否下线</td>
			<td align="center">选择</td>
		</tr>
		<%
		for(PlanActivityBean planActivityBean:planActivityBeans){
		%>
		<tr>
			<td align="center"><%=planActivityBean.getMovieName() %></td>
			<td align="center"><%=planActivityBean.getMovieOfflineTime() %></td>
			<td align="center">
			<%
			if(planActivityBean.getQuestion()==null){
			%>
			未绑定题目
			<%	
			}else{
			%>
			<%=planActivityBean.getQuestion() %>
			<%
			}
			%></td>
			<td align="center">
				<%
				if(planActivityBean.isIfOffLine()){
				%>
				已经下线
				<%
				}else{
				%>
				尚未下线
				<%
				}
				%>
			</td>
			<td align="center">
				<%
				if(planActivityBean.getQuestion()!=null){
				%>
				该计划已经有对应活动了！
				<%	
				}else if(planActivityBean.isIfOffLine()){
				%>
				该电影已经下线！
				<%
				}else{
				%>
				<input type="checkbox" id="<%=planActivityBean.getPlanId()%>">
				<%
				}
				%>
			</td>
		</tr>
		<%
		
		}
		%>
</table>
<div align="center"><input type="button" value="提交" onclick="submit()" class="btn btn-primary"></div>
</div>

	
<%@include file="toTop-import.jsp" %>
</body>
</html>