<%@page import="edu.nju.cineplex.enums.MemberState"%>
<%@page import="edu.nju.cineplex.action.business.MemberInfoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="member" uri="/WEB-INF/tlds/member.tld" %>
<!DOCTYPE>
<html>
<head>
<%MemberInfoBean memberInfoBean=(MemberInfoBean)request.getAttribute("memberInfoBean"); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>

<title>我的账户</title>
<%@include file="js-css-import.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
	function stop(){
		
		if(confirm("你确定要取消会员资格吗？取消之后将无法恢复！")){
			XMLHttp.send("post","${pageContext.request.contextPath}/MemberStop","",gf);
			
	    }else{
	    	
	    }
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			window.alert(result);
			window.location.replace("${pageContext.request.contextPath}/Home");
		}
	}
	function convert(){
		var point=document.getElementById("point").innerHTML;
		if(point==0){
			alert("没有可兑换的积分！");
			return;
		}
		XMLHttp.send("post","${pageContext.request.contextPath}/MemberConvertPoint","",gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			window.alert(result);
			window.location.reload();
		}
		
	}
	function edit(obj){
		obj.style.display="none";
		var submit=document.getElementById("submit");
		submit.style.display="inline";
		var ageValue=document.getElementById("ageValue");
		var ageInput=document.getElementById("ageInput");
		var age=document.getElementById("age");
		ageValue.style.display="none";
		ageInput.style.display="inline";
		age.focus();
		var sexValue=document.getElementById("sexValue");
		var sexSelect=document.getElementById("sexSelect");
		sexValue.style.display="none";
		sexSelect.style.display="inline";
		var homeValue=document.getElementById("homeValue");
		var homeInput=document.getElementById("homeInput");
		homeValue.style.display="none";
		homeInput.style.display="inline";
		//var home=document.getElementById("home");
		//home.focus();
		
		
	}
	function submit(){
		var age=document.getElementById("age");
		var age_pattern=/^\d{1,3}$/;
		if(!age_pattern.test(age.value)){
			alert("年龄格式不正确！");
			age.focus();
			return;
		}
		var sexs=document.getElementsByName("sex");
		var sex_value="";
		for(var i=0;i<sexs.length;i++){
			if(sexs.item(i).checked){
				sex_value=sexs.item(i).getAttribute("value");
				break;
			}
		}
		var home=document.getElementById("city");
		var index = home.selectedIndex; // 选中索引
		var city_value = home.options[index].value; // 选中值
		XMLHttp.send("post","${pageContext.request.contextPath}/MemberInfo","age="+age.value+"&sex="+sex_value+"&home="+city_value,gf);
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
<div class="jumbotron" align="center">
	<table class="table table-hover">
		<tr>
			<td align="center">
			会员卡号：
			</td>
			<td align="center"><%=memberInfoBean.getCardNum() %></td>
		</tr>
		<tr>
			<td align="center">账户余额：
			</td>
			<td align="center"><%=memberInfoBean.getMoney() %>元</td>
		</tr>
		<tr>
			<td align="center">激活状态：
			</td>
			<td align="center">
			<%
			if(memberInfoBean.isActivated()){
			%>
			已激活
			<%	
			}else{
			%>
			尚未激活
			<%	
			}
			%>
			</td>
		</tr>
		<tr>
			<td align="center">
			积分：
			</td>
			<td align="center"><span id="point"><%=memberInfoBean.getPoint() %></span><input type="button" value="兑换" onclick="convert()" style="margin-left:20px;"></td>
		</tr>
		<tr>
			<td align="center">
			等级：
			</td>
			<td align="center"><%=memberInfoBean.getLevel() %></td>
		</tr>
		<tr>
			<td align="center">
			注册邮箱：
			</td>
			<td align="center"><%=memberInfoBean.getEmail() %></td>
		</tr>
		<tr>
			<td align="center">
			年龄：
			</td>
			<td align="center">
			<span id="ageValue">
			<%
			if(memberInfoBean.getAge().equals("")){
			%>
			尚未编辑
			<%
			}else{
			%>
			<%=memberInfoBean.getAge() %>
			<%
			}
			%>
			</span>
			<span id="ageInput" style="display:none;">
				<input type="text" value="<%=memberInfoBean.getAge()%>" id="age">
			</span>
			</td>
		</tr>
		<tr>
			<td align="center">
			性别：
			</td>
			<td align="center"><span id="sexValue">

			<%
			if(memberInfoBean.getSex()=='2'){
			%>
			尚未编辑
			<%
			}else if(memberInfoBean.getSex()=='1'){
			%>
			男
			<%
			}else{
			%>
			女
			<%
			}
			%>
			</span>
			<span id="sexSelect" style="display:none;">
			<input type="radio" name="sex" value="male" checked="checked">男
			<input type="radio" name="sex" value="female">女
			</span>
			</td>
		</tr>
		<tr>
			<td align="center">
				家庭住址：

			</td>
			<td align="center">
			<span id="homeValue">
				<%
				if(memberInfoBean.getHome().equals("")){
				%>
				尚未编辑！
				<%	
				}else{
				%>
				<%=memberInfoBean.getHome() %>
				<%
				}
				%>
				</span>
				<span id="homeInput" style="display:none;">
				<select name=city id="city" style="width:100px;">
				<option value="上海" selected>上海</option>
				<option value="南京">南京</option>
				<option value="北京">北京</option>
				<option value="苏州">苏州</option>
				<option value="杭州">杭州</option>
				<option value="其他">其他</option>
				</select>市
				</span>
			</td>
		</tr>
		<tr>
			<td align="center">
			状态：

			</td>
			<td align="center">
				<%
				if(memberInfoBean.getState()==MemberState.Normal){
				%>
				正常
				<%	
				}else if(memberInfoBean.getState()==MemberState.NoMoney){
				%>
				余额不足，请尽快<a href='${pageContext.request.contextPath}/MemberAddMoney'>充值</a>！
				<%	
				}
				%>
				<input type="button" value="取消资格" onclick="stop()" style="margin-left:20px;">
			</td>
		</tr>
	</table>

<input type="button" value="编辑" style="display:inline;" onclick="edit(this)" id="edit">
<input type="button" value="提交修改" style="display:none;" onclick="submit()" id="submit">
</div>
<%@include file="toTop-import.jsp" %>
</body>
</html>