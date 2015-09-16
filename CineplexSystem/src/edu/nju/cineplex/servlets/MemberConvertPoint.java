package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

/**
 * Servlet implementation class MemberConvertPoint
 */
@WebServlet("/MemberConvertPoint")
public class MemberConvertPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberConvertPoint() {
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
		String output = "<response><result>兑换成功！</result></response>";
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("name");
		memberManageService.convertPoint(email);
		out.println(output);
		out.close();
	}

}
