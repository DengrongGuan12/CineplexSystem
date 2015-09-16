<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>输入提示示例</title>
<style>
TD{
font-size:12px;
}
</style>
<script type="text/javascript" src='../js/gd.js'></script>
<script type="text/javascript">
	function ok(){
		XMLHttp.send("post","${pageContext.request.contextPath}/AjaxTest","name=gdr12&passwd=12345",gf);
		function gf(xmlhttp){
			var response=xmlhttp.responseXML.documentElement;
			var name=document.getElementById("name");
			name.innerHTML=response.getElementsByTagName("name")[0].childNodes[0].nodeValue;
			var passwd=document.getElementById("passwd");
			passwd.innerHTML=response.getElementsByTagName("passwd")[0].childNodes[0].nodeValue;
		}
	}
	function suggest(){
		var key = document.getElementById("key").value;
		XMLHttp.send("post","${pageContext.request.contextPath}/Suggest","key="+key,xy);
		function xy(xmlhttp){
			clearTable();
			var out = "";
			var res = xmlhttp.responseXML.documentElement;
			var items = res.getElementsByTagName("item");
	        for ( var i = 0; i < items.length; i++){
	             addRow(items[i].firstChild.nodeValue);

	        }
	        setDivStyle();
			
		};
		function clearTable(){
			var content = document.getElementById("content");
			while (content.childNodes.length > 0) {
			    content.removeChild(content.childNodes[0]);
			}
		};
		function addRow(item){
			var content = document.getElementById("content");
			var row = document.createElement("tr");
			var cell = document.createElement("td");
			cell.appendChild(document.createTextNode(item));
			cell.onmouseover = function() {
			   this.style.background = "blue";
			};
			cell.onmouseout = function() {
			   this.style.background = "#f5f5f1";
			};
			cell.onclick = function() {
			   document.getElementById("key").value= this.innerHTML;
			   document.getElementById("suggest").style.visibility= "hidden";
			};
			row.appendChild(cell);
			content.appendChild(row);
		};
		function setDivStyle(){
			var suggest = document.getElementById("suggest");
			suggest.style.border = "black 0px solid";
			suggest.style.backgroundColor = "#f5f5f1";
			document.getElementById("suggest").style.visibility= "visible";
		};
	}
	//开始调用Ajax的功能
	function refresh() {
		//实时刷新页面的倒计时时间
		var refreshTime=function(){
			setTimeout(refreshTime,1000);
		    var time = document.getElementById("time").innerHTML;
		    time = time - 1;
		    if (time > 0) {
		        document.getElementById("time").innerHTML= time;
		    } else {
		        document.getElementById("time").innerHTML= 6;
		    }
		};
		//必须先定义再调用
		refreshTime();
		
		refreshRequest();
	}
	function refreshRequest(){
		XMLHttp.send("post","${pageContext.request.contextPath}/Refresh",null,mz);
		function mz(xmlhttp){
		      var response = xmlhttp.responseXML.documentElement;
		      var res=response.childNodes[0].nodeValue;
		      document.getElementById("time-content").innerHTML = res;
		      //设置在6000毫秒以后重新从服务器取值
		      setTimeout("refreshRequest()",6000);
			  
		};
	};
</script>
</head>
<body onload="refresh()">
用户名为：
<span id='name'>

</span>
<br />
密码为：
<span id='passwd'>

</span>
<br />
<input type="button" onclick="ok()" value="click here"/>
<br />
输入提示示例(可以输入字母a开头的字符串进行测试)
<br />
请输入：
<div>
<input type="text" id="key" name="key" onkeyup="suggest()" style="width:40%;"/>
<br />
<table id="suggest" style="position:absolute;width:40%">
	<tbody id="content"></tbody>
</table>
</div>

<br />
本页面将在
<span id="time"></span>秒中之内刷新局部区域
<br />
<div id="time-content"></div>




</body>
</html>
