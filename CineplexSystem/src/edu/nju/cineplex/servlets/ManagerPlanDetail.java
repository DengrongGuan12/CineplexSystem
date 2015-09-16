package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.action.business.PlanDetailBean;
import edu.nju.cineplex.service.PlanManageService;
import edu.nju.cineplex.service.PlanManageServiceBean;

/**
 * Servlet implementation class ManagerPlanDetail
 */
@WebServlet("/ManagerPlanDetail")
public class ManagerPlanDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlanManageService planManageService=PlanManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerPlanDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context=getServletContext();
		String planId=request.getParameter("planId");
		PlanDetailBean planDetailBean=planManageService.getPlanDetailBean(planId);
		request.setAttribute("planDetail", planDetailBean);
		context.getRequestDispatcher("/jsp/managerPlanDetail.jsp").forward(request, response);
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
		String planId=request.getParameter("planId");
		String result=request.getParameter("result");
		String reason=request.getParameter("reason");
		output=planManageService.examine(planId, result, reason);
		out.println(output);
		out.close();
		
	}

}
