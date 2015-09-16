<%@page import="java.util.HashMap"%>
<%@page import="edu.nju.cineplex.action.business.DailyUsageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
DailyUsageBean dailyUsageBean=(DailyUsageBean)request.getAttribute("dailyUsageBean");
HashMap<Integer,Integer> dailyUsageData=dailyUsageBean.getDailyUsageMap();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=dailyUsageBean.getYear() %>年<%=dailyUsageBean.getMonth() %>月的日总人数</title>
<%@include file="js-css-import.jsp" %>
		<script type="text/javascript">
		$(function () {
			$("#container").highcharts({
	            chart: {
	                type: 'line'
	            },
	            title: {
	                text: '影院日总人数曲线'
	            },
	            xAxis: {
	                categories: [
	                <%
	                for(int day:dailyUsageData.keySet()){
	                %>
	                <%=day%>,
	                <%
	                }
	                %>]
	            },
	            yAxis: {
	                title: {
	                    text: '人数 (/人)'
	                }
	            },
	            plotOptions: {
	                line: {
	                    dataLabels: {
	                        enabled: true
	                    },
	                    enableMouseTracking: false
	                }
	            },
	            series: [{
	                name: '日总人数',
	                data: [<%
	                	   for(int day:dailyUsageData.keySet()){
	                	%>
	                	<%=dailyUsageData.get(day)%>,
	                	<%
	                	   }
	                %>]
	            },]
	        });
	        
	    });
		</script>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/modules/exporting.js"></script>
<%@include file="manager-nav.jsp" %>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<%@include file="toTop-import.jsp" %>
</body>
</html>