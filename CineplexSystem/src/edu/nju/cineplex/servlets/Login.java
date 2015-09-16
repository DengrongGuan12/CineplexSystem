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

import edu.nju.cineplex.service.MemberManageServiceBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberManageServiceBean memberManageServiceBean=MemberManageServiceBean.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String passwd=request.getParameter("passwd");
		String remember=request.getParameter("remember");
		Cookie cookie=null;
		if(memberManageServiceBean.validate(email, passwd)){
			output="<response><result>1</result></response>";
			HttpSession session=request.getSession(true);
			session.setAttribute("login", "member");
			session.setAttribute("name", email);
			if(remember.equals("1")){
				cookie = new Cookie("email", email);
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
