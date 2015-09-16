package edu.nju.cineplex.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

public class ActivateSelectHandler extends SimpleTagSupport{
	private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
	public void doTag() throws JspException, IOException {
		JspWriter out=getJspContext().getOut();
		PageContext pageContext=(PageContext)getJspContext();
		HttpSession session=pageContext.getSession();
		out.println("<div class='jumbotron' align='center'>");
		String email=(String)session.getAttribute("name");
		if(memberManageService.isActivated(email)){
			out.println("<h1>已经激活的账户！</h1>");
		}else {
			out.println("<h1>一次充值200元以上即可激活账户</h1>");
			out.println("<input class='form-control' type='text' placeholder='输入银行卡号' id='cardId' style='width:20%;margin-top:20px;'><br>");
			out.println("<input class='form-control' type='password' placeholder='输入银行卡密码' id='passwd' style='width:20%;'><br>");
			out.println("<input class='form-control' type='text' id='money' placeholder='输入充值金额' style='width:20%;'><br>");
			out.println("<input type='button' onclick='submit()' value='充值' class='btn btn-default'>");
		}
		out.println("</div>");
	}

}
