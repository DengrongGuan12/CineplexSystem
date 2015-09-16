<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<title>查询会员信息</title>
<%@include file="js-css-import.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
	function submit(){
		var purchaseRecords=document.getElementById("purchaseRecords");
		purchaseRecords.style.display="none";
		
		var rechargeRecords=document.getElementById("rechargeRecords");
		rechargeRecords.style.display="none";
		
		var hidePButton=document.getElementById("hidePButton");
		hidePButton.style.display="none";
		
		var hideRButton=document.getElementById("hideRButton");
		hideRButton.style.display="none";
		
		var memberInfo=document.getElementById("memberInfo");
		memberInfo.style.display="none";
		
		var idInput=document.getElementById("idInput");
		var id_pattern=/\d{7}/;
		if(!id_pattern.test(idInput.value)){
			window.alert("卡号必须是7位数字");
			return;
		}
		XMLHttp.send("post","${pageContext.request.contextPath}/WaiterShowMemberInfo","cardId="+idInput.value,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			if(result=="notexist"){
				window.alert("该用户不存在！");
				if(memberInfo.style.display=="inline"){
					memberInfo.style.display=="none";
				}
				return;
			}else{
				
				if(memberInfo.style.display=="none"){
					
					var cardId=document.getElementById("cardId");
					cardId.innerHTML=response.getElementsByTagName("cardId")[0].childNodes[0].nodeValue;
					
					var remainMoney=document.getElementById("remainMoney");
					remainMoney.innerHTML=response.getElementsByTagName("remainMoney")[0].childNodes[0].nodeValue;
					
					var activated=document.getElementById("activated");
					activated.innerHTML=response.getElementsByTagName("activated")[0].childNodes[0].nodeValue;
					
					var point=document.getElementById("point");
					point.innerHTML=response.getElementsByTagName("point")[0].childNodes[0].nodeValue;
					
					var level=document.getElementById("level");
					level.innerHTML=response.getElementsByTagName("level")[0].childNodes[0].nodeValue;
					
					var email=document.getElementById("email");
					email.innerHTML=response.getElementsByTagName("email")[0].childNodes[0].nodeValue;
					
					var age=document.getElementById("age");
					age.innerHTML=response.getElementsByTagName("age")[0].childNodes[0].nodeValue;
					
					var sex=document.getElementById("sex");
					sex.innerHTML=response.getElementsByTagName("sex")[0].childNodes[0].nodeValue;
					
					var home=document.getElementById("home");
					home.innerHTML=response.getElementsByTagName("home")[0].childNodes[0].nodeValue;
					
					var state=document.getElementById("state");
					state.innerHTML=response.getElementsByTagName("state")[0].childNodes[0].nodeValue;
					
					
					memberInfo.style.display="inline";
				}
			}
		}
		
	}
	function showPurchaseRecords(){
		var email=document.getElementById("email");
		XMLHttp.send("post","${pageContext.request.contextPath}/WaiterShowPurchaseRecords","email="+email.innerHTML,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			var purchaseRecords=document.getElementById("purchaseRecords");
			if(result=="noRecord"){
				window.alert("该用户暂时没有消费记录!");
				if(purchaseRecords.style.display=="inline"){
					purchaseRecords.style.display="none";
				}
				return;
				
			}else{
				var hidePButton=document.getElementById("hidePButton");
				hidePButton.style.display="inline";
				var rowNum=purchaseRecords.rows.length;
			     for (i=1;i<rowNum;i++){
			    	 purchaseRecords.deleteRow(1);
			    }
				var beans=response.getElementsByTagName("bean");
				for(var i=0;i<beans.length;i++){
					var bean=beans[i];
					var newRow=purchaseRecords.insertRow(i+1);
					
					for(var j=0;j<9;j++){
						var newCell=newRow.insertCell(j);
						var nodeValue=bean.childNodes[j].childNodes[0].nodeValue;
						newCell.innerHTML=nodeValue;
					}
				}
				if(purchaseRecords.style.display=="none"){
					purchaseRecords.style.display="inline";
				}
			}
		}
	}
	function showRechargeRecords(){
		var email=document.getElementById("email");
		XMLHttp.send("post","${pageContext.request.contextPath}/WaiterShowRechargeRecords","email="+email.innerHTML,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			var rechargeRecords=document.getElementById("rechargeRecords");
			if(result=="noRecord"){
				window.alert("该用户暂时没有缴费记录!");
				if(rechargeRecords.style.display=="inline"){
					rechargeRecords.style.display="none";
				}
				return;
				
			}else{
				var hideRButton=document.getElementById("hideRButton");
				hideRButton.style.display="inline";
				var rowNum=rechargeRecords.rows.length;
			     for (i=1;i<rowNum;i++){
			    	 rechargeRecords.deleteRow(1);
			    }
				var beans=response.getElementsByTagName("bean");
				for(var i=0;i<beans.length;i++){
					var bean=beans[i];
					var newRow=rechargeRecords.insertRow(i+1);
					for(var j=0;j<2;j++){
						var newCell=newRow.insertCell(j);
						newCell.innerHTML=bean.childNodes[j].childNodes[0].nodeValue;
					}
				}
				if(rechargeRecords.style.display=="none"){
					rechargeRecords.style.display="inline";
				}
			}
		}
	}
	function hidePurchaseRecords(obj){
		var purchaseRecords=document.getElementById("purchaseRecords");
		purchaseRecords.style.display="none";
		obj.style.display="none";
	}
	function hideRechargeRecords(obj){
		var rechargeRecords=document.getElementById("rechargeRecords");
		rechargeRecords.style.display="none";
		obj.style.display="none";
	}
	
</script>
</head>
<body class="center_align_body">
<%@include file="waiter-nav.jsp" %>
<div align="center" class="jumbotron">
      <input type="text" class="form-control" placeholder="输入会员卡号..." id="idInput" style="width:15%;margin-bottom:20px;">
      
      <button class="btn btn-default" type="button" onclick="submit()">提交</button>
      
      <div align="center" style="display:none;" id="memberInfo">
<table class="table table-hover">
	<tr>
		<td align="center">会员卡号:</td>
		<td align="center"><span id="cardId"></span></td>
	</tr>
	<tr>
		<td align="center">账户余额:</td>
		<td align="center"><span id="remainMoney"></span></td>
	</tr>
	<tr>
		<td align="center">激活状态:</td>
		<td align="center"><span id="activated"></span></td>
	</tr>
	
	<tr>
		<td align="center">积分:</td>
		<td align="center"><span id="point"></span></td>
	</tr>
	<tr>
		<td align="center">等级:</td>
		<td align="center"><span id="level"></span></td>
	</tr>
	
	<tr>
		<td align="center">邮箱:
		</td>
		<td align="center"><span id="email"></span></td>
	</tr>
	
	<tr>
		<td align="center">年龄:
		</td>
		<td align="center"><span id="age"></span></td>
	</tr>
	<tr>
		<td align="center">性别:
		</td>
		<td align="center"><span id="sex"></span></td>
	</tr>
	<tr>
		<td align="center">家庭住址:
		</td>
		<td align="center"><span id="home"></span></td>
	</tr>
	<tr>
		<td align="center">账户状态:
		</td>
		<td align="center"><span id="state"></span></td>
	</tr>
	
</table>
<input type="button" value="消费记录" onclick="showPurchaseRecords()" class="btn btn-primary">
<input type="button" value="收起" onclick="hidePurchaseRecords(this)" id="hidePButton" style="display:none;" class="btn btn-warning">
<br>
<table style="display:none;" id="purchaseRecords" class="table table-hover">
	<tr>
		<td align="center">消费时间</td>
		<td align="center">实际支付</td>
		<td align="center">所选座位</td>
		<td align="center">放映厅</td>
		<td align="center">票价</td>
		<td align="center">放映日期</td>
		<td align="center">开始时间</td>
		<td align="center">结束时间</td>
		<td align="center">所购电影</td>
	</tr>
</table>
<br>
<input type="button" value="缴费记录" onclick="showRechargeRecords()" class="btn btn-primary">
<input type="button" value="收起" onclick="hideRechargeRecords(this)" id="hideRButton" style="display:none;" class="btn btn-warning">
<br>
<table style="display:none;" id="rechargeRecords" class="table table-hover">
	<tr>
		<td align="center">充值金额</td>
		<td align="center">充值时间</td>
		
	</tr>
</table>
</div>
      
</div><!-- /input-group -->



<%@include file="toTop-import.jsp" %>
</body>
</html>