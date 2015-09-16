<%@page import="edu.nju.cineplex.action.business.ActivityJoinedBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="edu.nju.cineplex.action.business.ActivityToJoinBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="member" uri="/WEB-INF/tlds/member.tld" %>
<!DOCTYPE>
<html>
<head>
<%
ArrayList<ActivityToJoinBean> activityToJoinBeans=(ArrayList<ActivityToJoinBean>)request.getAttribute("activityToJoinBeans");
ArrayList<ActivityJoinedBean> activityJoinedBeans=(ArrayList<ActivityJoinedBean>)request.getAttribute("activityJoinedBeans");

%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参加活动</title>
<%@include file="js-css-import.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
	function submit(){
		options="";
		<%
		for(ActivityToJoinBean activityToJoinBean:activityToJoinBeans){
		%>
		var allOptions=document.getElementsByName("<%=activityToJoinBean.getActivityId()%>");
		var option_value="";
		for(var i=0;i<allOptions.length;i++){
			if(allOptions.item(i).checked){
				option_value=allOptions.item(i).getAttribute("value");
				break;
			}
		}
		if(option_value==""){
			alert("有尚未回答的问题，无法提交！");
			return;
		}
		options=options+option_value+";";
		<%
		}
		%>
		XMLHttp.send("post","${pageContext.request.contextPath}/MemberJoinActivity","options="+options,gf);
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
<member:NavSelect/>
<div class="main jumbotron">
  	<div class="wrap">
      <div class="content">
      		<div class="content_top">
	    		<div class="heading">
	    			<h3>已经参加过的活动</h3>
	    		</div>
	    	</div>
	    	<div class="section group" style="margin-top:20px;">
	    		<%
				if(activityJoinedBeans.size()==0){
				%>
				<h3>尚未参加任何活动！</h3>
				<%	
				}else{
				%>
				<table class="table table-hover">
					<tr>
						<td align="center">
						题目
						</td>
						<td align="center">选项</td>
						<td align="center">总的参与人数</td>
						<td align="center">我的选择</td>
						<td align="center">是否结算</td>
					</tr>
				<%
					for(ActivityJoinedBean activityJoinedBean:activityJoinedBeans){
						%>
						<tr>
							<td align="center"><%=activityJoinedBean.getQuestion() %></td>
							<td align="center">
								<%
								for(String option:activityJoinedBean.getOptionNum().keySet()){
								%>
								<%=option %>(<%=activityJoinedBean.getOptionNum().get(option) %>人选择)
								<%
								}
								%>
							</td>
							<td align="center">
								<%=activityJoinedBean.getTotalJoined() %>
							</td>
							<td align="center">
								<%=activityJoinedBean.getOption() %>
							</td>
							<td align="center">
								<%
								if(activityJoinedBean.isEnd()){
								%>
								已经结算,获得了<%=activityJoinedBean.getGetPoint() %>个积分<br>
								<%
								}else{
								%>
								尚未结算！
								<%
								}
								%>
							</td>
						</tr>
						
						<%
					}%>
					</table>
					<%
				}
				%>
	    	</div>
	    	<div class="content_top">
	    		<div class="heading">
	    			<h3>可以参加的活动</h3>
	    		</div>
	    	</div>
	    	<div class="section group" style="margin-top:20px;">
	    		<%
				if(activityToJoinBeans.size()==0){
				%>
				<h3>当前没有活动可以参加！</h3>
				<%
				}else{
				%>
				<table class="table table-hover">
					<tr>
						<td align="center">
						题目
						</td>
						<td align="center">选项</td>
					</tr>
				<%
				
					for(ActivityToJoinBean activityToJoinBean:activityToJoinBeans){
				%>
					<tr>
						<td align="center"><%=activityToJoinBean.getQuestion() %></td>
						<td align="center">
						<%
						for(String optionId:activityToJoinBean.getHashMap().keySet()){
						%>
						<input type="radio" name="<%=activityToJoinBean.getActivityId()%>" value="<%=optionId%>"><%=activityToJoinBean.getHashMap().get(optionId) %>
						<%
						}
						%>
						</td>
					</tr>
						<%
					}
					%>
					<tr>
					<td align="center" colspan="2">
						<input type="button" value="提交" onclick="submit()">
					</td>
					
					</tr>
				</table>
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