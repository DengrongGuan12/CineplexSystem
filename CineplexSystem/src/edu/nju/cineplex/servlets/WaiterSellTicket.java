package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.action.business.TicketBean;
import edu.nju.cineplex.service.PlanManageService;
import edu.nju.cineplex.service.PlanManageServiceBean;
import edu.nju.cineplex.service.PurchaseManageService;
import edu.nju.cineplex.service.PurchaseManageServiceBean;

/**
 * Servlet implementation class WaiterSellTicket
 */
@WebServlet("/WaiterSellTicket")
public class WaiterSellTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlanManageService planManageService=PlanManageServiceBean.getInstance();
	private PurchaseManageService purchaseManageService=PurchaseManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaiterSellTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String planId=request.getParameter("planId");
		TicketBean ticketBean=planManageService.getTicketBean(planId);
		request.setAttribute("ticketBean", ticketBean);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/waiterSellTicket.jsp").forward(request, response);
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
		String seatId=request.getParameter("seatId");
		String memberId=request.getParameter("memberId");
		
		output=purchaseManageService.insertRecord(planId, seatId, memberId);
		out.println(output);
		out.close();
		
	}

}
