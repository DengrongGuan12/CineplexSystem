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

import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

/**
 * Servlet implementation class MemberAddMoney
 */
@WebServlet("/MemberAddMoney")
public class MemberAddMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAddMoney() {
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
		double money=memberManageService.getMoneyByEmail(email);
		request.setAttribute("money", money);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/memberAddMoney.jsp").forward(request, response);
		
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
		String cardId=request.getParameter("cardId");
		String passwd=request.getParameter("passwd");
		String money=request.getParameter("money");
		HttpSession session=request.getSession(true);
		String email=(String)session.getAttribute("name");
		output=memberManageService.addMoney(email, cardId, passwd, money);
		out.println(output);
		out.close();
	}

}
