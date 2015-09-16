package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.cineplex.action.business.RechargeRecordBean;
import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

/**
 * Servlet implementation class MemberRechargeRecords
 */
@WebServlet("/MemberRechargeRecords")
public class MemberRechargeRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberManageService memberManageService=MemberManageServiceBean.getInstance();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberRechargeRecords() {
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
		ArrayList<RechargeRecordBean> rechargeRecordBeans=memberManageService.getRechargeRecordBeans(email);
		request.setAttribute("rechargeRecordBeans", rechargeRecordBeans);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/memberRechargeRecords.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
