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

import edu.nju.cineplex.action.business.PlanActivityBean;
import edu.nju.cineplex.service.ActivityManageService;
import edu.nju.cineplex.service.ActivityManageServiceBean;
import edu.nju.cineplex.service.PlanManageService;
import edu.nju.cineplex.service.PlanManageServiceBean;

/**
 * Servlet implementation class WaiterAddActivity
 */
@WebServlet("/WaiterAddActivity")
public class WaiterAddActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlanManageService planManageService=PlanManageServiceBean.getInstance();
	private ActivityManageService activityManageService=ActivityManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaiterAddActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<PlanActivityBean> planActivityBeans=planManageService.getPlanActivityBeans();
		request.setAttribute("planActivityBeans", planActivityBeans);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/waiterAddActivity.jsp").forward(request, response);
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
		String question=request.getParameter("question");
		String optionA=request.getParameter("optionA");
		String optionB=request.getParameter("optionB");
		String optionC=request.getParameter("optionC");
		String optionD=request.getParameter("optionD");
		String planIds=request.getParameter("planIds");
		output=activityManageService.addActivity(question, optionA, optionB, optionC, optionD, planIds);
		out.println(output);
		out.close();
	}

}
