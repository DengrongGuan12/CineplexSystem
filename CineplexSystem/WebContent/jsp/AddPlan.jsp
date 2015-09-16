<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="timeSelector" uri="/WEB-INF/tlds/timeSelector.tld" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<title>制定放映计划</title>
<%@include file="js-css-import.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function addPlan(){
		var movieId=document.getElementById("movieId").value;
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
		var add_suc=document.getElementById("add_suc");
		XMLHttp.send("post","${pageContext.request.contextPath}/WaiterAddPlan","movieId="+movieId
				+"&planDate="+planDate.value+"&from_hour="+from_hour+"&from_min="+from_min
				+"&to_hour="+to_hour+"&to_min="+to_min+"&hallId="+hallId.value+"&price="
				+price.value,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			if(result=="1"){
				if(add_suc.style.visibility=="hidden"){
					add_suc.style.visibility="visible";
				}
				window.alert("添加成功！");
			}else{
				if(add_suc.style.visibility=="visible"){
					add_suc.style.visibility="hidden";
				}
				window.alert(result);
				return false;
			}
		}
		
		return false;
	}
</script>
</head>
<body>
<%@include file="waiter-nav.jsp" %>
	<div class="jumbotron" align="center">
		<form action="" onsubmit="return addPlan();" style="margin-top:70px;">
			<input type="hidden" value="<%=request.getParameter("movieId") %>" id="movieId" name="movieId"/>
			<input style="width:20%;" class="form-control Wdate mt10 height30" type="text" onClick="WdatePicker()" placeholder="输入放映日期" name="planDate" id="planDate"/>
			<div style="visibility:hidden;" class="log_err" id="planDate_err">日期不能为空</div>
			<br />
			从<timeSelector:hourSelector/>时<timeSelector:minuteSelector/>分
			<br />
			到<timeSelector:hourSelector/>时<timeSelector:minuteSelector/>分
			<br />
			<div class="log_err" style="visibility:hidden;" id="time_err">请确保开始时间在结束时间之前！</div>
			<input style="width:20%;" type="text" name="hallId" id="hallId" placeholder="输入放映厅号" class="form-control"/><br />
			<div class="log_err" style="visibility:hidden;" id="hallId_err">放映厅号不能为空！</div>
			<input placeholder="输入票价" type="text" name="price" id="price" style="width:20%;" class="form-control"/><br />
			<div class="log_err" style="visibility:hidden;" id="price_err">票价格式不正确！</div>
			<input type="submit" value="添加放映计划" class="btn btn-primary"/>
			<div class="green" style="visibility:hidden;" id="add_suc">添加成功，请等待经理审核！</div>
			
		</form>
	</div>
<%@include file="toTop-import.jsp" %>
</body>
</html>