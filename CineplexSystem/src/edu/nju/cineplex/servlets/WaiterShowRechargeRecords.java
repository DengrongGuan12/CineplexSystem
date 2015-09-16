package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

/**
 * Servlet implementation class WaiterShowRechargeRecords
 */
@WebServlet("/WaiterShowRechargeRecords")
public class WaiterShowRechargeRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaiterShowRechargeRecords() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String email=request.getParameter("email");
		output=memberManageService.getRechargeRecords(email);
		out.println(output);
		out.close();
		
	}

}
