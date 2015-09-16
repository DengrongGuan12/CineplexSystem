<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<script type="text/javascript">
	function jump1(){
		window.location=document.form1.url_textbox.value;
	}
	function jump2(){
		window.location.replace(document.form1.url_textbox.value);
	}
</script>
</head>
<body>
<div align="center">
<h1>输入网页地址</h1>
<form action="" name="form1">
	<br />
	<input type="text" name="url_textbox" size="60">
	<br><br>
	<input type="button" value="跳转" onClick="jump1()">
	<input type="button" value="替换" onCLick="jump2()">
</form>
</div>
</body>
</html>
