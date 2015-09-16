<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <div class="header">
		 <div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="${pageContext.request.contextPath}/Admin">首页</a></li>
						<li><a href="${pageContext.request.contextPath}/ManagerAllPlans">所有放映计划</a></li>
						<li><a href="${pageContext.request.contextPath}/ManagerShowMemberInfo">会员情况</a></li>
						<li><a href="${pageContext.request.contextPath}/ManagerShowUsageInfo">影院使用情况</a></li>
					</ul>
				</div>
					<div class="account_desc">
						<ul>
							<li style="color:white;">欢迎你,经理<%=session.getAttribute("name") %></li>
							<li><a href="${pageContext.request.contextPath}/AdminLogout">退出</a></li>
						</ul>
					</div>
				<div class="clear"></div>
			</div>
	  	</div>
  	</div>
