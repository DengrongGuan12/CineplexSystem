package edu.nju.cineplex.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.cineplex.action.business.MemberStatisticsDataBean;
import edu.nju.cineplex.service.AdminManageService;
import edu.nju.cineplex.service.AdminManageServiceBean;

/**
 * Servlet implementation class ManagerShowMemberInfo
 */
@WebServlet("/ManagerShowMemberInfo")
public class ManagerShowMemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdminManageService adminManageService=AdminManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerShowMemberInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberStatisticsDataBean memberStatisticsDataBean=adminManageService.getMemberStatisticsDataBean();
		request.setAttribute("memberStatisticsData", memberStatisticsDataBean);
		ServletContext context=getServletContext();
		context.getRequestDispatcher("/jsp/managerShowMemberInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
