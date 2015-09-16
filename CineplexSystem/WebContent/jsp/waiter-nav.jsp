<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="header">
		 <div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="${pageContext.request.contextPath}/Admin">售票</a></li>
						<li><a href="${pageContext.request.contextPath}/WaiterAddMovie">添加电影</a></li>
						<li><a href="${pageContext.request.contextPath}/WaiterAllMovies">所有电影</a></li>
						<li><a href="${pageContext.request.contextPath}/WaiterAddActivity">添加活动</a></li>
						<li><a href="${pageContext.request.contextPath}/WaiterShowMemberInfo">查询</a></li>
					</ul>
				</div>
					<div class="account_desc">
						<ul>
							<li style="color:white;">欢迎你,服务员<%=session.getAttribute("name") %></li>
							<li><a href="${pageContext.request.contextPath}/AdminLogout">退出</a></li>
						</ul>
					</div>
				<div class="clear"></div>
			</div>
	  	</div>
  	</div>
    
