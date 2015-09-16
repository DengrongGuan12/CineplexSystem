<%@page import="java.util.HashMap"%>
<%@page import="edu.nju.cineplex.action.business.MemberStatisticsDataBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员情况</title>
<%@include file="js-css-import.jsp" %>
<%MemberStatisticsDataBean memberStatisticsDataBean=(MemberStatisticsDataBean)request.getAttribute("memberStatisticsData");
HashMap<String,String> age_percent=memberStatisticsDataBean.getAgeData();
HashMap<String,String> sex_percent=memberStatisticsDataBean.getSexDataHashMap();
HashMap<String,String> home_percent=memberStatisticsDataBean.getHome_data();
HashMap<String,String> state_percent=memberStatisticsDataBean.getCardState_data();
%>
<script type="text/javascript">
$(function () {
    $('#age_container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员年龄比例'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            data: [
                <%
                for(String key:age_percent.keySet()){
                %>
                ['<%=key%>',<%=age_percent.get(key)%>],
                <%
                }
                %>
            ]
        }]
    });
    
    $('#sex_container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员男女比例'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            data: [
                <%
                for(String key:sex_percent.keySet()){
                %>
                ['<%=key%>',<%=sex_percent.get(key)%>],
                <%
                }
                %>
            ]
        }]
    });
    $('#home_container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员居住地情况'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            data: [
                <%
                for(String key:home_percent.keySet()){
                %>
                ['<%=key%>',<%=home_percent.get(key)%>],
                <%
                }
                %>
            ]
        }]
    });
    
    $('#state_container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员卡有效情况'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            data: [
                <%
                for(String key:state_percent.keySet()){
                %>
                ['<%=key%>',<%=state_percent.get(key)%>],
                <%
                }
                %>
            ]
        }]
    });
});
    

		</script>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/modules/exporting.js"></script>
<%@include file="manager-nav.jsp" %>
<!-- <table>
	<tr>
		<td><div id="age_container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div></td>
		<td><div id="sex_container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div></td>
	</tr>
	<tr>
		<td><div id="home_container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div></td>
		<td><div id="state_container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div></td>
	</tr>
</table>
 -->
<div class="row jumbotron">
<div class="col-md-6" id="age_container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto 0 100"></div>
<div class="col-md-6" id="sex_container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
<div class="col-md-6" id="home_container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto 0 100"></div>
<div class="col-md-6" id="state_container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
</div>



<%@include file="toTop-import.jsp" %>
</body>
</html>