<%@page import="edu.nju.cineplex.model.Plan"%>
<%@page import="java.util.List"%>
<%@page import="edu.nju.cineplex.model.Movie"%>
<%@page import="edu.nju.cineplex.action.business.MoviePlanBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE>
<html>
<head>
<%
MoviePlanBean moviePlanBean=(MoviePlanBean)request.getAttribute("moviePlanBean");
Movie movie=moviePlanBean.getMovie();
List<Plan> plans=moviePlanBean.getPlans();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电影详细信息</title>
<%@include file="js-css-import.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gd.js"></script>
<script type="text/javascript">
	function checkImage(){
		var image=document.getElementById("image");
		var image_url="${pageContext.request.contextPath}/image/<%=movie.getId()%>.gif";
		if(CheckImgExists(image_url)){
			image.src=image_url;
		}
	}
	function performAjaxSubmit() {
		var sampleText = document.getElementById("sampleText").value;
		var sampleFile = document.getElementById("sampleFile").files[0];
		
		var formdata = new FormData();
		formdata.append("sampleText", sampleText);
		formdata.append("sampleFile", sampleFile);	        		
		
		var xhr = new XMLHttpRequest();
		
		xhr.open("POST","${pageContext.request.contextPath}/UploadImage", true);
		xhr.send(formdata);
		
		xhr.onload = function(e) {
   			if (this.status == 200) {
   			  // window.location.reload();
   				//alert(this.responseText);
   				var response=this.responseXML.documentElement;
				var result=response.getElementsByTagName("result")[0].childNodes[0].nodeValue;
				if(result=="1"){
					alert("上传成功！");
					window.location.reload();
				}else{
					var error=response.getElementsByTagName("error")[0].childNodes[0].nodeValue;
					alert(error);
				}
   			}
    	};	        		
	}
</script>
</head>
<body onload="checkImage()">
<%@include file="waiter-nav.jsp" %>
<div class="main jumbotron">
   	 <div class="wrap">
   	 	<div class="section group">
				<div class="cont-desc span_1_of_2" style="width:100% !important;">
				  <div class="product-details">				
					<div class="grid images_3_of_2">
						<img src="${pageContext.request.contextPath}/image/preview.jpg" alt="" id="image"/>
				  </div>
				<div class="desc span_3_of_2">
					<h2><%=movie.getName() %></h2>
					<p>电影描述：<%=movie.getDescription() %></p>					
					<div class="price">
						<p><span></span></p>
					</div>
					<div class="available">
						<ul>
						  <li><span>下线时间</span> &nbsp; <%=movie.getEndTime() %></li>
						  <li><span>是否已下线</span>&nbsp; 
						  <%
						  if(moviePlanBean.isHasEnd()){
						%>
						已经下线！
						<%
						  }else{
					      %>
					      尚未下线！
					      <%
						  }
						  %>
						  </li>
						  <li><span></span></li>
					    </ul>
					</div>
				<div class="share-desc">
					<div class="share">
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#imageUpload">上传图片</button>
							
								
					</div>
					<%
					if(!moviePlanBean.isHasEnd()){
					%>
					<div class="button"><span><a href="${pageContext.request.contextPath}/WaiterAddPlan?movieId=<%=movie.getId()%>">添加放映计划</a></span></div>	
					<%	
					}
					%>
									
					<div class="clear"></div>
				</div>
				 <div class="wish-list">
				 	<ul>
				 		<li class="wish"></li>
				 	    <li class="compare"></li>
				 	</ul>
				 </div>
			</div>
			<div class="clear"></div>
		  </div>
		<div class="product_desc">	
			 <h2>放映计划</h2>
			 <%
			 if(plans.size()==0){
		     %>
		     没有匹配的放映计划！
		     <%
			 }else{
		     %>
		     <table class="table table-hover">
				<tr>
					<td>放映厅</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>票价</td>
					<td>审核状态</td>
					<td></td>
				</tr>
			
			<%
			
			for(Plan plan:(List<Plan>)plans){
			%>
				<tr>
					<td><%=plan.getHallId() %>号厅</td>
					<td><%=plan.getStartTime() %></td>
					<td><%=plan.getEndTime() %></td>
					<td><%=plan.getPrice() %>元</td>
					
						<%
						if(plan.getApproved()=='1'){
						%>
						<td>
						已批准
						</td>
						<td>无法修改</td>
						<%	
						}else if(plan.getApproved()=='0'){
						%>
						<td>尚未审核</td>
						<td><a href="${pageContext.request.contextPath}/WaiterChangePlan?planId=<%=plan.getId() %>">修改</a></td>
						<%
						}else{
						%>
						<td>不批准</td>
						<td><a href="${pageContext.request.contextPath}/WaiterChangePlan?planId=<%=plan.getId() %>">修改</a></td>
						<%
						}
						%>
				</tr>
		    <%
			}
			%>
			</table>
		     <%
			 }
			 %>
	   </div>
   </div>
				
 </div>
 </div>
 </div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="imageUpload" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							   <div class="modal-dialog">
							      <div class="modal-content">
							         <div class="modal-header">
							            <button type="button" class="close"
							               data-dismiss="modal" aria-hidden="true">
							                  &times;
							            </button>
							            <h4 class="modal-title" id="myModalLabel">
							               上传图片
							            </h4>
							         </div>
							         <div class="modal-body">
							         	<input id="sampleText" name="sampleText" type="hidden" value="<%=movie.getId() %>" >
							            <input id="sampleFile" name="sampleFile" type="file" /> 
							         </div>
							         <div class="modal-footer">
							            <button type="button" class="btn btn-default"
							               data-dismiss="modal">关闭
							            </button>
							            <button type="button" class="btn btn-primary" onclick="performAjaxSubmit()">
							               上传
							            </button>
							         </div>
							      </div><!-- /.modal-content -->
							</div>
</div><!-- /.modal -->
	

<%@include file="toTop-import.jsp" %>
</body>
</html>