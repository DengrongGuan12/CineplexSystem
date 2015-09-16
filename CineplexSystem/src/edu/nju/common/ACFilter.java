package edu.nju.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.nju.cineplex.enums.MemberState;
import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

/**
 * Servlet Filter implementation class ACFilter
 */
public class ACFilter implements Filter {
	private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
    /**
     * Default constructor. 
     */
    public ACFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest request2=(HttpServletRequest)request;
		String url=request2.getServletPath();
		if(url.indexOf("Waiter") !=-1){
			HttpSession session=request2.getSession();
			if(session.getAttribute("login")==null||!session.getAttribute("login").equals("waiter")){
				ServletContext context=request2.getServletContext();
				context.getRequestDispatcher("/Admin").forward(request, response);
				return;
				
			}
		}else if(url.indexOf("Manager") !=-1){
			HttpSession session=request2.getSession();
			if(session.getAttribute("login")==null||!session.getAttribute("login").equals("manager")){
				ServletContext context=request2.getServletContext();
				context.getRequestDispatcher("/Admin").forward(request, response);
				return;
				
			}
		}else if(url.indexOf("Member")!=-1){
			
			HttpSession session=request2.getSession();
			if(session.getAttribute("login")==null||!session.getAttribute("login").equals("member")){
				ServletContext context=request2.getServletContext();
				context.getRequestDispatcher("/Signin").forward(request, response);
				return;
				
			}
			String email=(String)session.getAttribute("name");
			if(!memberManageService.isActivated(email)&&!url.equals("/MemberLogout")){
				ServletContext context=request2.getServletContext();
				context.getRequestDispatcher("/MemberActivate").forward(request, response);
				return;
			}
			if(memberManageService.getStateOfMember(email)==MemberState.Pause&&!url.equals("/MemberLogout")){
				ServletContext context=request2.getServletContext();
				context.getRequestDispatcher("/MemberAddMoney").forward(request, response);
				return;
			}
			if(memberManageService.getStateOfMember(email)==MemberState.Stop&&!url.equals("/MemberLogout")){
				ServletContext context=request2.getServletContext();
				context.getRequestDispatcher("/Home").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
