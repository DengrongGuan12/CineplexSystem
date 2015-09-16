package edu.nju.cineplex.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

public class NavSelectHandler extends SimpleTagSupport{
	private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
	public void doTag() throws JspException, IOException {
		JspWriter out=getJspContext().getOut();
		PageContext pageContext=(PageContext)getJspContext();
		HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
		HttpSession session=pageContext.getSession();
		out.println("<div class='header'>");
		out.println("<div class='headertop_desc'>");
		out.println("<div class='wrap'>");
		out.println("<div class='nav_list'>");
		out.println("<ul>");
		out.println("<li><a href='"+request.getContextPath()+"/Home'>Home</a></li>");
		out.println("</ul>");
		out.println("</div>");
		out.println("<div class='account_desc'>");
		out.println("<ul>");
		if(session.getAttribute("login")!=null&&session.getAttribute("login").equals("member")){
			out.println("<li><a href='"+request.getContextPath()+"/MemberLogout'>退出</a></li>");
			String email=(String)session.getAttribute("name");
			if(memberManageService.isActivated(email)){
				switch (memberManageService.getStateOfMember(email)) {
				case NoMoney:
					out.println("<li><font color='red'>余额不足，请</font><a href='"+request.getContextPath()+"/MemberAddMoney'>充值</a></li>");
					out.println("<li><a href='"+request.getContextPath()+"/MemberInfo'>我的账户</a></li>");
					out.println("<li><a href='"+request.getContextPath()+"/MemberJoinActivity'>活动</a></li>");
					out.println("<li><a href='"+request.getContextPath()+"/MemberPurchaseRecords'>消费记录</a></li>");
					out.println("<li><a href='"+request.getContextPath()+"/MemberRechargeRecords'>缴费记录</a></li>");
					break;

				case Normal:
					out.println("<li><a href='"+request.getContextPath()+"/MemberAddMoney'>充值</a></li>");
					out.println("<li><a href='"+request.getContextPath()+"/MemberInfo'>我的账户</a></li>");
					out.println("<li><a href='"+request.getContextPath()+"/MemberJoinActivity'>活动</a></li>");
					out.println("<li><a href='"+request.getContextPath()+"/MemberPurchaseRecords'>消费记录</a></li>");
					out.println("<li><a href='"+request.getContextPath()+"/MemberRechargeRecords'>缴费记录</a></li>");
					break;
				case Pause:
					out.println("<li><font color='red'>记录暂停，请</font><a href='"+request.getContextPath()+"/MemberAddMoney'>充值</a></li>");
					break;
				case Stop:
					out.println("<li><font color='red'>会员记录停止！</font></li>");
					break;
				default:
					break;
				}
				
				
			}else {
				out.println("<li><a href='"+request.getContextPath()+"/MemberActivate'>激活</a></li>");
			}
			
			
		}else{
			out.println("<li><a href='"+request.getContextPath()+"/Signin'>登录/注册</a></li>");
		}
		out.println("</ul></div>");
		out.println("<div class='clear'></div></div></div></div>");
	}

}
