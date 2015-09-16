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

import edu.nju.cineplex.action.business.SimplePlanBeans;
import edu.nju.cineplex.action.business.TicketBean;
import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;
import edu.nju.cineplex.service.PlanManageService;
import edu.nju.cineplex.service.PlanManageServiceBean;
import edu.nju.cineplex.service.PurchaseManageService;
import edu.nju.cineplex.service.PurchaseManageServiceBean;

/**
 * Servlet implementation class MemberBuyTicket
 */
@WebServlet("/MemberBuyTicket")
public class MemberBuyTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlanManageService planManageServiceBean=PlanManageServiceBean.getInstance();
	private MemberManageService memberManageServiceBean=MemberManageServiceBean.getInstance();
	private PurchaseManageService purchaseManageService=PurchaseManageServiceBean.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberBuyTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		String planId=request.getParameter("planId");
		TicketBean ticketBean=planManageServiceBean.getTicketBean(planId);
		String email=(String)session.getAttribute("name");
		double money=memberManageServiceBean.getMoneyByEmail(email);
		ticketBean.setMoney(money);
		SimplePlanBeans simplePlanBeans=planManageServiceBean.getLikedSimplePlanBeans();
		request.setAttribute("likedPlanBeans", simplePlanBeans);
		request.setAttribute("ticketBean", ticketBean);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/memberBuyTicket.jsp").forward(request, response);
		
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
		HttpSession session=request.getSession(true);
		String email=(String)session.getAttribute("name");
		String cardId=memberManageServiceBean.getCardIdByEmail(email);
		output=purchaseManageService.insertRecord(planId, seatId, cardId);
		out.println(output);
		out.close();
	}

}
