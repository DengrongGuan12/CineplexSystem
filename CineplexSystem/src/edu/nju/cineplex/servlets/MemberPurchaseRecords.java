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

import edu.nju.cineplex.action.business.PurchaseRecordBean;
import edu.nju.cineplex.service.PurchaseManageService;
import edu.nju.cineplex.service.PurchaseManageServiceBean;

/**
 * Servlet implementation class MemberPurchaseRecords
 */
@WebServlet("/MemberPurchaseRecords")
public class MemberPurchaseRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PurchaseManageService purchaseManageService=PurchaseManageServiceBean.getInstance();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPurchaseRecords() {
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
		ArrayList<PurchaseRecordBean> purchaseRecordBeans=purchaseManageService.getPurchaseRecordBeans(email);
		request.setAttribute("purchaseRecordBeans", purchaseRecordBeans);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/memberPurchaseRecords.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
