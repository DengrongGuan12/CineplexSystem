package edu.nju.cineplex.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.cineplex.action.business.TicketBean;
import edu.nju.cineplex.service.PlanManageService;
import edu.nju.cineplex.service.PlanManageServiceBean;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlanManageService planManageServiceBean=PlanManageServiceBean.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ServletContext context=getServletContext();
		String name1=(String)session.getAttribute("name");
		if(name1!=null&&(name1.startsWith("w")||name1.startsWith("m"))){
			String admin=(String) session.getAttribute("login");
			if(admin.equals("waiter")){
				//跳转到服务员界面
				ArrayList<TicketBean> ticketBeans=planManageServiceBean.getTicketBeans();
				request.setAttribute("ticketBeans", ticketBeans);
				context.getRequestDispatcher("/jsp/waiter-home.jsp").forward(request, response);
				
			}else if(admin.equals("manager")){
				//经理界面
				context.getRequestDispatcher("/jsp/manager-home.jsp").forward(request, response);
			}else{
				String name=null;
				String passwd=null;
				Cookie cookie = null;
				Cookie[] cookies = request.getCookies();
				if (null != cookies) {
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if (cookie.getName().equals("name")) {
							name=cookie.getValue();
							if(passwd!=null){
								break;
							}
						}
						if(cookie.getName().equals("passwd")){
							passwd=cookie.getValue();
							if(name!=null){
								break;
							}
							
						}
					}
				}
				if(name==null){
					name="";
				}
				if(passwd==null){
					passwd="";
				}
				request.setAttribute("name", name);
				request.setAttribute("passwd", passwd);
				context.getRequestDispatcher("/jsp/adminLog.jsp").forward(request, response);
			}
		}else{
			String name=null;
			String passwd=null;
			Cookie cookie = null;
			Cookie[] cookies = request.getCookies();
			if (null != cookies) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("name")) {
						name=cookie.getValue();
						if(passwd!=null){
							break;
						}
					}
					if(cookie.getName().equals("passwd")){
						passwd=cookie.getValue();
						if(name!=null){
							break;
						}
						
					}
				}
			}
			if(name==null){
				name="";
			}
			if(passwd==null){
				passwd="";
			}
			request.setAttribute("name", name);
			request.setAttribute("passwd", passwd);
			context.getRequestDispatcher("/jsp/adminLog.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
