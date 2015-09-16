<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<title>注册登录页</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/classEditor.js"></script>

<script type="text/javascript">
	function checkReg(){
		var email=document.getElementById("reg_email");
		var email_pattern= /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		var email_err=document.getElementById("email_err");
		/*if(!pattern_test(email_pattern,email,email_err)){
			return false;
		}*/
		if(email_pattern.test(email.value)){
			removeClass.call(email,"err_border");
			if(email_err.style.visibility=="visible"){
				email_err.style.visibility="hidden";
			}
			
			
		}else{
			addClass.call(email,"err_border");
			email.value="";
			email.focus();
			if(email_err.style.visibility=="hidden"){
				email_err.style.visibility="visible";
			}
			return false;
		}
		
		var passwd1=document.getElementById("reg_passwd1");
		var passwd_err1=document.getElementById("passwd_err1");
		var passwd_pattern=/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{4,16}/;
		/*if(!pattern_test(passwd_pattern,passwd1,passwd_err1)){
			return false;
		}*/
		if(passwd_pattern.test(passwd1.value)){
			removeClass.call(passwd1,"err_border");
			if(passwd_err1.style.visibility=="visible"){
				passwd_err1.style.visibility="hidden";
			}
			
			
		}else{
			addClass.call(passwd1,"err_border");
			passwd1.value="";
			passwd1.focus();
			if(passwd_err1.style.visibility=="hidden"){
				passwd_err1.style.visibility="visible";
			}
			return false;
		}
		var passwd2=document.getElementById("reg_passwd2");
		var passwd_err2=document.getElementById("passwd_err2");
		if(passwd1.value !=passwd2.value){
			addClass.call(passwd2,"err_border");
			passwd2.value="";
			passwd2.focus();
			if(passwd_err2.style.visibility=="hidden"){
				passwd_err2.style.visibility="visible";
			}
			return false;
		}else{
			removeClass.call(passwd2,"err_border");
			if(passwd_err2.style.visibility=="visible"){
				passwd_err2.style.visibility="hidden";
			}
		}
		var reg_btn=document.getElementById("reg_btn");
		//将btn设置为不可点击，并修改btn中的内容
		reg_btn.disabled=true;
		addClass.call(reg_btn,"reging");
		reg_btn.value="注册中。。。";
		XMLHttp.send("post","${pageContext.request.contextPath}/Register","email="+email.value+"&passwd="+passwd1.value,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			var has_reg=document.getElementById("has_reg");
			var reg_suc=document.getElementById("reg_suc");
			if(result=="1"){
				if(has_reg.style.visibility=="visible"){
					has_reg.style.visibility="hidden";
				}
				if(reg_suc.style.visibility=="hidden"){
					reg_suc.style.visibility="visible";
				}
			}else{
				if(has_reg.style.visibility=="hidden"){
					has_reg.style.visibility="visible";
				}
				if(reg_suc.style.visibility=="visible"){
					reg_suc.style.visibility="hidden";
				}
				
			}
		}
		//重新设置为可点击
		reg_btn.disabled=false;
		removeClass.call(reg_btn,"reging");
		reg_btn.value="注册";
		return false;
	}
	function checkLog(){
		var email=document.getElementById("log_email");
		var email_pattern= /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		var email_err=document.getElementById("log_email_err");
		/*if(!pattern_test(email_pattern,email,email_err)){
			return false;
		}*/
		if(email_pattern.test(email.value)){
			removeClass.call(email,"err_border");
			if(email_err.style.visibility=="visible"){
				email_err.style.visibility="hidden";
			}
			
			
		}else{
			addClass.call(email,"err_border");
			email.value="";
			email.focus();
			if(email_err.style.visibility=="hidden"){
				email_err.style.visibility="visible";
			}
			return false;
		}
		var passwd1=document.getElementById("log_passwd");
		var passwd_err1=document.getElementById("log_passwd_err");
		var passwd_pattern=/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{4,16}/;
		/*if(!pattern_test(passwd_pattern,passwd1,passwd_err1)){
			return false;
		}*/
		if(passwd_pattern.test(passwd1.value)){
			removeClass.call(passwd1,"err_border");
			if(passwd_err1.style.visibility=="visible"){
				passwd_err1.style.visibility="hidden";
			}
			
			
		}else{
			addClass.call(passwd1,"err_border");
			passwd1.value="";
			passwd1.focus();
			if(passwd_err1.style.visibility=="hidden"){
				passwd_err1.style.visibility="visible";
			}
			return false;
		}
		var remember=document.getElementById("remember");
		var rem=0;
		if(remember.checked){
			rem=1;
		}
		var log_btn=document.getElementById("log_btn");
		//将btn设置为不可点击，并修改btn中的内容
		log_btn.disabled=true;
		addClass.call(log_btn,"reging");
		log_btn.value="登录中。。。";
		XMLHttp.send("post","${pageContext.request.contextPath}/Login","email="+email.value+"&passwd="+passwd1.value+"&remember="+rem,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			var log_err=document.getElementById("log_err");
			var log_suc=document.getElementById("log_suc");
			if(result=="1"){
				if(log_err.style.visibility=="visible"){
					log_err.style.visibility="hidden";
				}
				if(log_suc.style.visibility=="hidden"){
					log_suc.style.visibility="visible";
				}
				window.location.replace("${pageContext.request.contextPath}/Home");
			}else{
				if(log_err.style.visibility=="hidden"){
					log_err.style.visibility="visible";
				}
				if(log_suc.style.visibility=="visible"){
					log_suc.style.visibility="hidden";
				}
				
			}
		}
		//重新设置为可点击
		log_btn.disabled=false;
		removeClass.call(log_btn,"reging");
		log_btn.value="登录";
		return false;
	}
</script>
</head>
<body class="content_center">

<div class="signin_out radius">
	<div class="signin_line"></div>
	<div class="signin_inner">
		<div class="register">
			<h5>新会员注册</h5>
			<form action="" onsubmit="return checkReg();">
				<input name="reg_email" id="reg_email" class="border radius" type="text" placeholder="输入邮箱地址" value=""/>
				<div class="reg_err" style="visibility:hidden;" id="email_err">请输入有效的邮箱地址</div>
				<br />
				<input name="reg_passwd1" id="reg_passwd1" class="border radius" type="password" placeholder="输入4-16位的密码(必须包含字母，数字，以及特殊字符)" value=""/>
				<div class="reg_err" id="passwd_err1" style="visibility:hidden;">密码格式不正确</div>
				<br />
				<input name="reg_passwd2" id="reg_passwd2" class="border radius" type="password" placeholder="再一次输入密码确认" value=""/>
				<div class="reg_err" id="passwd_err2" style="visibility:hidden;">两次输入的密码不匹配</div>
				<br />
				<div class="log_err" id="has_reg" style="visibility:hidden;">该邮箱已经注册，请尝试登录或使用其他邮箱！</div>
				<input type="submit" class="regin_btn" id="reg_btn" value="注册" /><br />
				<div class="reg_suc" id="reg_suc" style="visibility:hidden;">注册成功，请先登录</div>
			</form>
		</div>
		<div class="login">
			<h5>会员登录</h5>
			<p class="px16">已有帐号在此登录</p>
			<form action="" onsubmit="return checkLog();">
				<input id="log_email" class="border radius" type="text" placeholder="输入邮箱地址" value='<%=request.getAttribute("email")%>'/>
				<div class="reg_err" style="visibility:hidden;" id="log_email_err">请输入有效的邮箱地址</div><br />
				<input id="log_passwd" class="border radius" type="password" placeholder="输入4-16位的密码" value='<%=request.getAttribute("passwd")%>'/>
				<div class="reg_err" style="visibility:hidden;" id="log_passwd_err">请输入有效的密码</div><br />
				<div class="checkbox">
					<input type="checkbox" name="remember" id="remember"/>记住我
				</div>
				<div class="log_err" style="visibility:hidden;" id="log_err">
					邮箱或密码输入错误，请重新输入！
				</div>
				
				<br />
				<input type="submit" class="regin_btn" id="log_btn" value="登录" /><br />
				<div class="reg_suc" id="log_suc" style="visibility:hidden;">登录成功！</div>
				
			</form>
		</div>
	</div>
	
</div>

</body>
</html>