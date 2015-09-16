package edu.nju.cineplex.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.action.business.PlansOfDaysBean;
import edu.nju.cineplex.service.PlanManageService;
import edu.nju.cineplex.service.PlanManageServiceBean;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlanManageService planManageServiceBean=PlanManageServiceBean.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		ArrayList<TicketBean> ticketBeans=planManageServiceBean.getTicketBeans();
//		request.setAttribute("ticketBeans", ticketBeans);
		PlansOfDaysBean plansOfDaysBean=planManageServiceBean.getPlansOfDaysBean();
		request.setAttribute("plansOfDaysBean", plansOfDaysBean);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
