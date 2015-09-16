package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.cineplex.action.business.ActivityJoinedBean;
import edu.nju.cineplex.action.business.ActivityToJoinBean;
import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

/**
 * Servlet implementation class MemberJoinActivity
 */
@WebServlet("/MemberJoinActivity")
public class MemberJoinActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		String email=(String)session.getAttribute("name");
		ArrayList<ActivityToJoinBean> activityToJoinBeans=memberManageService.getActivityToJoinBeans(email);
		ArrayList<ActivityJoinedBean> activityJoinedBeans=memberManageService.getActivityJoinedBeans(email);
		request.setAttribute("activityToJoinBeans", activityToJoinBeans);
		request.setAttribute("activityJoinedBeans", activityJoinedBeans);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/memberJoinActivity.jsp").forward(request, response);
		
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
		String options=request.getParameter("options");
		HttpSession session=request.getSession(true);
		String email=(String)session.getAttribute("name");
		output=memberManageService.chooseOption(email, options);
		out.println(output);
		out.close();
	}

}
