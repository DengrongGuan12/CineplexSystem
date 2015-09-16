<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<title>服务员经理登录页面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
	function checkLog(){
		var name=document.getElementById("name");
		var name_err=document.getElementById("name_err");
		if(name.value==""){
			if(name_err.style.visibility=="hidden"){
				name_err.style.visibility="visible";
				return false;
			}
		}else{
			if(name_err.style.visibility=="visible"){
				name_err.style.visibility="hidden";
			}
		}
		var passwd=document.getElementById("passwd");
		var passwd_err=document.getElementById("passwd_err");
		if(passwd.value==""){
			if(passwd_err.style.visibility=="hidden"){
				passwd_err.style.visibility="visible";
			}
			return false;
		}else{
			if(passwd_err.style.visibility=="visible"){
				passwd_err.style.visibility="hidden";
			}
		}
		var remember=document.getElementById("remember");
		var rem=0;
		if(remember.checked){
			rem=1;
		}
		var log_err=document.getElementById("log_err");
		XMLHttp.send("post","${pageContext.request.contextPath}/AdminLog","name="+name.value+"&passwd="+passwd.value+"&remember="+rem,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			if(result=="0"){
				if(log_err.style.visibility=="hidden"){
					log_err.style.visibility="visible";
				}
			}else{
				if(log_err.style.visibility=="visible"){
					log_err.style.visibility="hidden";
				}
				window.location.replace("${pageContext.request.contextPath}/Admin");
			}
		}
		return false;
	}
</script>
</head>
<body class="center_align_body">
	<div class="center_align_div" style="width:60%">
		<form onsubmit="return checkLog();" action="">
			<div class="admin_log_title">经理/服务员在此登录</div>
			<input class="admin_log_input mt10" type="text" placeholder="输入用户名" value="<%=request.getAttribute("name") %>"  id="name"/>
			<div id="name_err" style="color:red;visibility:hidden;" >用户名不能为空！</div>
			<input class="admin_log_input mt10" type="password" placeholder="输入密码" value="<%=request.getAttribute("passwd") %>" id="passwd"/>
			<div id="passwd_err" style="color:red;visibility:hidden;">密码不能为空！</div>
			<input class="mt10" type="checkbox" id="remember">记住我
			<div id="log_err" style="color:red;visibility:hidden;">用户名或密码错误，请重新输入！</div>
			<input type="submit" value="登录" class="admin_log_btn" id="log_btn" />
			<div id="log_suc" style="visibility:hidden;" class="mt10 green">登录成功！</div>
		</form>
	</div>


</body>
</html>