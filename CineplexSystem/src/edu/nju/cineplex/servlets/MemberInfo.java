package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.cineplex.action.business.MemberInfoBean;
import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

/**
 * Servlet implementation class MemberInfo
 */
@WebServlet("/MemberInfo")
public class MemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("name");
		MemberInfoBean memberInfoBean=memberManageService.getMemberInfoBean(email);
		request.setAttribute("memberInfoBean", memberInfoBean);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/memberInfo.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = response.getWriter();
		String output = "";
		String age=request.getParameter("age");
		String sex=request.getParameter("sex");
		String home=request.getParameter("home");
		HttpSession session=request.getSession(true);
		String email=(String)session.getAttribute("name");
		memberManageService.updateInfo(email, age, sex, home);
		output="<response><result>修改成功！</result></response>";
		out.println(output);
		out.close();
		
	}

}
