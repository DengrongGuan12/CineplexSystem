<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>经理操作页面</title>
<%@include file="js-css-import.jsp" %>
<script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider();
    });
    </script>
</head>
<body>
<%@include file="manager-nav.jsp" %>
<div class="slider">
<div class="slider-wrapper theme-default">
<div id="slider" class="nivoSlider">
<img src="${pageContext.request.contextPath}/image/1.jpg" data-thumb="${pageContext.request.contextPath}/image/1.jpg" alt="" />
<img src="${pageContext.request.contextPath}/image/2.jpg" data-thumb="${pageContext.request.contextPath}/image/2.jpg" alt="" />
<img src="${pageContext.request.contextPath}/image/3.jpg" data-thumb="${pageContext.request.contextPath}/image/3.jpg" alt="" />
<img src="${pageContext.request.contextPath}/image/4.jpg" data-thumb="${pageContext.request.contextPath}/image/4.jpg" alt="" />
<img src="${pageContext.request.contextPath}/image/5.jpg" data-thumb="${pageContext.request.contextPath}/image/5.jpg" alt="" />
</div>
</div>
</div>
<%@include file="toTop-import.jsp" %>
</body>
</html>