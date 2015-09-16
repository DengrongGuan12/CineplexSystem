package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.cineplex.service.AdminManageService;
import edu.nju.cineplex.service.AdminManageServiceBean;

/**
 * Servlet implementation class AdminLog
 */
@WebServlet("/AdminLog")
public class AdminLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdminManageService adminManageService=AdminManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLog() {
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
		String name=request.getParameter("name");
		String passwd=request.getParameter("passwd");
		String remember=request.getParameter("remember");
		Cookie cookie=null;
		if(adminManageService.validate(name, passwd)){
			HttpSession session=request.getSession(true);
			output="<response><result>1</result></response>";
			session.setAttribute("name", name);
			if(name.startsWith("w")){
				session.setAttribute("login", "waiter");
			}else{
				session.setAttribute("login", "manager");
			}
			if(remember.equals("1")){
				cookie = new Cookie("name", name);
				cookie.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(cookie);
				cookie=new Cookie("passwd", passwd);
				cookie.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(cookie);
			}
		}else{
			output="<response><result>0</result></response>";
		}
		out.println(output);
		out.close();
	}

}
