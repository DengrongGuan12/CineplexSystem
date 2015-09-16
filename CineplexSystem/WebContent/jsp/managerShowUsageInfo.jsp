<%@page import="java.util.HashMap"%>
<%@page import="edu.nju.cineplex.action.business.MonthPurchaseNumBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
MonthPurchaseNumBean monthPurchaseNumBean=(MonthPurchaseNumBean)request.getAttribute("monthPurchaseNumBean");
HashMap<Integer,HashMap<Integer,Integer>> yearMonthNum=monthPurchaseNumBean.getYearMonthHashMap();
%>
<title>影院月总人数折线图</title>
<%@include file="js-css-import.jsp" %>
		<script type="text/javascript">
$(function () {
		<%
		for(int year:yearMonthNum.keySet()){
		%>
		$("#<%=year%>_container").highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: '影院<%=year%>年月总人数曲线'
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
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
                name: '月总人数',
                data: [<%
                       HashMap<Integer,Integer> month_num=yearMonthNum.get(year);
                	   for(int month:month_num.keySet()){
                	%>
                	<%=month_num.get(month)%>,
                	<%
                	   }
                %>]
            },]
        });
		<%
		}
		%>
        
    });
    

		</script>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/modules/exporting.js"></script>
<%@include file="manager-nav.jsp" %>

<div class="jumbotron">

<%
for(int year:yearMonthNum.keySet()){
%>
<div id="<%=year %>_container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<div style="margin-top:10px;margin-bottom:30px;">进入<%=year%>年具体月份：

<%
HashMap<Integer,Integer> month_num=yearMonthNum.get(year);
for(int month:month_num.keySet()){
	if(month_num.get(month)>0){
%>
<a href="${pageContext.request.contextPath}/ManagerShowDailyUsageInfo?year=<%=year%>&month=<%=month%>"><%=month %>月</a>
<%
	}
}
%>
</div>
<%
}
%>
</div>
<%@include file="toTop-import.jsp" %>
</body>
</html>