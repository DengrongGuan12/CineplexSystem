package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.service.MovieManageService;
import edu.nju.cineplex.service.MovieManageServiceBean;
import edu.nju.cineplex.service.PlanManageService;
import edu.nju.cineplex.service.PlanManageServiceBean;

/**
 * Servlet implementation class WaiterAddPlan
 */
@WebServlet("/WaiterAddPlan")
public class WaiterAddPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlanManageService planManageService=PlanManageServiceBean.getInstance();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaiterAddPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/AddPlan.jsp").forward(request, response);
		
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
		String movieId=request.getParameter("movieId");
		String planDate=request.getParameter("planDate");
		String startTime=planDate+" "+request.getParameter("from_hour")+":"+request.getParameter("from_min")+":00";
		String endTime=planDate+" "+request.getParameter("to_hour")+":"+request.getParameter("to_min")+":00";
		String hallId=request.getParameter("hallId");
		String price=request.getParameter("price");
		output=planManageService.addPlan(movieId, startTime, endTime, hallId, price);
		out.println(output);
		out.close();
	}

}
