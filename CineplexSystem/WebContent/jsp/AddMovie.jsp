<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<title>添加电影</title>
<%@include file="js-css-import.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function checkAdd(){
		var title=document.getElementById("title");
		var title_err=document.getElementById("title_err");
		if(title.value==""||title.value.length>64){
			if(title_err.style.visibility=="hidden"){
				title_err.style.visibility="visible";
				return false;
			}
		}else{
			if(title_err.style.visibility=="visible"){
				title_err.style.visibility="hidden";
			}
		}
		var description=document.getElementById("description");
		var description_err=document.getElementById("description_err");
		if(description.value==""||description.value.length>128){
			if(description_err.style.visibility=="hidden"){
				description_err.style.visibility="visible";
			}
			return false;
		}else{
			if(description_err.style.visibility=="visible"){
				description_err.style.visibility="hidden";
			}
		}
		var endTime=document.getElementById("endTime");
		var endTime_err=document.getElementById("endTime_err");
		if(endTime.value==""){
			if(endTime_err.style.visibility=="hidden"){
				endTime_err.style.visibility="visible";
			}
			return false;
		}else{
			if(endTime_err.style.visibility=="visible"){
				endTime_err.style.visibility="hidden";
			}
		}
		
		var add_suc=document.getElementById("add_suc");
		XMLHttp.send("post","${pageContext.request.contextPath}/WaiterAddMovie","title="+title.value+"&description="+description.value+"&endTime="+endTime.value,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			if(result=="1"){
				if(add_suc.style.visibility=="hidden"){
					add_suc.style.visibility="visible";
				}
			}else{
				if(add_suc.style.visibility=="visible"){
					add_suc.style.visibility="hidden";
				}
			}
		}
		return false;
	}
</script>
</head>
<body>
<%@include file="waiter-nav.jsp" %>
<div class="jumbotron" align="center">
	<input style='width:20%;margin-top:20px;' class="form-control" type="text" name="title" placeholder="输入影片标题(少于64个字)" id="title"/><br />
	<div class="log_err" style="visibility:hidden;" id="title_err">标题不能为空且必须小于64个字！</div>
	<textarea style='width:20%;margin-top:20px;' class="form-control height80" name="description" placeholder="输入影片描述(少于128个字)" id="description"></textarea><br />
	<div class="log_err" style="visibility:hidden;" id="description_err">描述不能为空且必须小于128个字！</div>
	<input style='width:20%;margin-top:20px;' class="form-control Wdate height30" type="text" onClick="WdatePicker()" placeholder="输入下线时间" name="endTime" id="endTime"/> <br />
	<div class="log_err" style="visibility:hidden;" id="endTime_err">必须输入下线时间！</div>
	<input style='width:20%;margin-top:20px;' class="admin_log_btn" type="submit" value="添加电影" onclick="checkAdd()"/>
	<div class="green mt10" id="add_suc" style="visibility:hidden;">添加成功！</div>
</div>
<%@include file="toTop-import.jsp" %>

</body>
</html>