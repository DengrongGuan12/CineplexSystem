<%@page import="edu.nju.cineplex.action.business.TicketBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
<title>服务员售票</title>
<%@include file="js-css-import.jsp" %>
<%
TicketBean ticketBean=(TicketBean)request.getAttribute("ticketBean");
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
	seat="";
	function t(obj){
		if(obj.value=="is"){
			document.getElementById("memberId").style.display="block";
			document.getElementById("getMoney").style.display="none";
		}else{
			document.getElementById("memberId").style.display="none";
			document.getElementById("getMoney").style.display="block";
		}
	}
	function choose(obj){
		obj.style.display="none";
		document.getElementById("giveup"+obj.value).style.display="inline";
		if(seat!=""){
			document.getElementById("choose"+seat).style.display="inline";
			document.getElementById("giveup"+seat).style.display="none";
		}
		seat=obj.value;
	}
	function giveup(obj){
		obj.style.display="none";
		document.getElementById("choose"+obj.value).style.display="inline";
		seat="";
	}
	function submit(){
		if(seat==""){
			window.alert("请选择座位！")
			return;
		}
		var ifMember=document.getElementById("ifMember");
		var memberId=document.getElementById("memberId");
		if(ifMember.value=="is"){
			
			var memberId_pattern=/[0-9]{7}/;
			if(!memberId_pattern.test(memberId.value)){
				memberId.focus();
				window.alert("会员帐号应该为7位数字！");
				return;
			}
		}else{
			var money=document.getElementById("money");
			var change=document.getElementById("change");
			var price_pattern=/^\d+\.?\d{0,1}$/;
			if(!price_pattern.test(money.value)){
				money.focus();
				window.alert("现金必须是小数位数小于等于两位的数字！");
				return;
			}
			var change_value=money.value-<%=ticketBean.getPrice()%>;
			if(change_value<0){
				window.alert("您输入的金额不够缴纳费用！");
				return;
			}
			change.innerHTML=Fractional(change_value);
		}
		XMLHttp.send("post","${pageContext.request.contextPath}/WaiterSellTicket","planId=<%=ticketBean.getPlanId()%>&seatId="+seat+"&memberId="+memberId.value,gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
			window.alert(result);
			window.location.replace("${pageContext.request.contextPath}/WaiterSellTicket?planId=<%=ticketBean.getPlanId()%>");
			
			
		}
	}
	//小数位数控制，可以四舍五入
    function Fractional(n) {
        //小数保留位数
        var bit = 2;
        //加上小数点后要扩充1位
        bit++;
        //数字转为字符串
        n = n.toString();
        //获取小数点位置
        var point = n.indexOf('.');
        //n的长度大于保留位数长度
        if (n.length > point + bit) {
            //保留小数后一位是否大于4，大于4进位
            if (parseInt(n.substring(point + bit, point + bit + 1)) > 4) {
                return n.substring(0, point) + "." + (parseInt(n.substring(point + 1, point + bit)) + 1);
            }
            else {
                return n.substring(0, point) + n.substring(point, point + bit);
            }
        }
        return n;
    }
	function setSelect(){
		document.getElementById("ifMember").value="isnot";
		
	}
</script>
</head>
<body class="center_align_body" onload="setSelect()">
<%@include file="waiter-nav.jsp" %>
<div class="jumbotron">

<table class="table table-hover">
	<tr>
		<td align="center">是否是会员</td>
		<td align="center">
			<select id="ifMember" onchange="t(this)">
				<option value="is">是</option>
				<option value="isnot">不是</option>
				
			</select><br />
			<input type="text" id="memberId" style="display:none;" placeholder="输入会员帐号"/>
		</td>
	</tr>
	<tr>
		<td align="center">电影名称</td>
		<td align="center"><%=ticketBean.getMovieName() %></td>
	</tr>
	<tr>
		<td align="center">放映日期</td>
		<td align="center"><%=ticketBean.getDate() %></td>
	</tr>
	<tr>
		<td align="center">开始时间</td>
		<td align="center"><%=ticketBean.getStartTime()%></td>
	</tr>
	<tr>
		<td align="center">结束时间</td>
		<td align="center"><%=ticketBean.getEndTime() %></td>
	</tr>
	<tr>
		<td align="center">价格（普通）</td>
		<td align="center"><%=ticketBean.getPrice() %></td>
	</tr>
	<tr>
		<td align="center">厅号</td>
		<td align="center"><%=ticketBean.getHallId() %>号厅</td>
	</tr>
	<tr>
		<td align="center">总票（座位）数</td>
		<td align="center"><%=ticketBean.getSeatNum() %></td>
	</tr>
	<tr>
		<td align="center">剩余票（座位）数</td>
		<td align="center"><%=ticketBean.getRemainderSeatNum() %></td>
	</tr>
</table>
选择座位：<br />
前
<%
			boolean[][] seat=ticketBean.getSeat();
			int i=1;
			for(boolean[] seatRow:seat){
			%>
			<div class="center_align_div">
			<%
				for(boolean seatState:seatRow){
					if(seatState){
					%>
						<input type="button" value="<%=i%>" style="width:30px;background:#08F608;display:inline;" onclick="choose(this)" id="choose<%=i%>">
						<input type="button" value="<%=i%>" style="width:30px;background:blue;display:none;" onclick="giveup(this)" id="giveup<%=i %>" >
					<%
					}else{
					%>
						<input type="button" value="<%=i%>" disabled="disabled" style="background:red;width:30px;" >
					<%
					}
					i++;
				}
				%>
				</div>
				<%
			}
			%>

后
</div>
<div style="display:block;" id="getMoney">
	输入金额：<input type="text" placeholder="现金" id="money" >元<br>
	找零：<span id="change"></span>元


</div>
<input type="button" value="确认提交" onclick="submit()" class="btn btn-primary"> 
<%@include file="toTop-import.jsp" %>
</body>
</html>