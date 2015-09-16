<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="member" uri="/WEB-INF/tlds/member.tld" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>充值</title>
<%@include file="js-css-import.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
	function submit(){
		var cardId=document.getElementById("cardId");
		if(cardId.value==""){
			window.alert("银行卡号不能为空！");
			cardId.focus();
			return;
		}
		var passwd=document.getElementById("passwd");
		if(passwd.value==""){
			window.alert("密码不能为空！");
			passwd.focus();
			return;
		}
		var money=document.getElementById("money");
		var money_pattern=/^\d+\.?\d{0,1}$/;
		if(!money_pattern.test(money.value)){
			window.alert("金额格式不正确！必须是小数位数小于等于1的数字！");
			money.focus();
			return;
		}
		XMLHttp.send("post","${pageContext.request.contextPath}/MemberAddMoney","cardId="+cardId.value
				+"&passwd="+passwd.value+"&money="+money.value,gf);
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
<div>当前会员卡余额：<%=request.getAttribute("money") %><br></div>
<input class='form-control' type='text' placeholder='输入银行卡号' id='cardId' style='width:20%;margin-top:20px;'><br>
<input class='form-control' type='password' placeholder='输入银行卡密码' id='passwd' style='width:20%;'><br>
<input class='form-control' type='text' id='money' placeholder='输入充值金额' style='width:20%;'><br>
<input type="button" value="充值" onclick="submit()" class='btn btn-default'>
</div>
<%@include file="toTop-import.jsp" %>
</body>
</html>