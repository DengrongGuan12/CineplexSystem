package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.action.business.MoviePlanBean;
import edu.nju.cineplex.service.MovieManageService;
import edu.nju.cineplex.service.MovieManageServiceBean;

/**
 * Servlet implementation class ManagerAllPlans
 */
@WebServlet("/ManagerAllPlans")
public class ManagerAllPlans extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieManageService movieManageService=MovieManageServiceBean.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerAllPlans() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<MoviePlanBean> moviePlanBeans=movieManageService.getMoviePlanBeans();
		request.setAttribute("allPlans", moviePlanBeans);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/managerAllPlans.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
