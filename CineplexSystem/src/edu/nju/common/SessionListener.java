package edu.nju.common;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;






/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("Session attribute removed!!");
    	String attribute=arg0.getName();
    	HttpSession session=arg0.getSession();
		ServletContext application=session.getServletContext();
		int login=(int)application.getAttribute(Constants.loginCount);
    	int visitor=(int)application.getAttribute(Constants.visitorCount);
    	if(attribute.equals("myCourseList")){
    		login--;
    		visitor++;
    		application.setAttribute(Constants.loginCount, login);
    		application.setAttribute(Constants.visitorCount, visitor);
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("Session attribute added!!!");
		HttpSession session=arg0.getSession();
		ServletContext application=session.getServletContext();
		int login=(int)application.getAttribute(Constants.loginCount);
    	int visitor=(int)application.getAttribute(Constants.visitorCount);
    	if (arg0.getName().equals("myCourseList")) {
    		login++;
    		visitor--;
    		application.setAttribute(Constants.loginCount, login);
    		application.setAttribute(Constants.visitorCount, visitor);
		}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("Session attribute replaced!!!");
		HttpSession session=arg0.getSession();
		ServletContext application=session.getServletContext();
		int login=(int)application.getAttribute(Constants.loginCount);
    	int visitor=(int)application.getAttribute(Constants.visitorCount);
    	if(arg0.getName().equals("myCourseList")){
    		if(session.getAttribute("myCourseList")==null){
    			login--;
        		visitor++;
        		application.setAttribute(Constants.loginCount, login);
        		application.setAttribute(Constants.visitorCount, visitor);
    		}
    		
    	}
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("session created!!!");
    	HttpSession session=arg0.getSession();
    	session.setAttribute("login", false);
    	ServletContext application=session.getServletContext();
    	int online=(int) application.getAttribute(Constants.onlineCount);
    	int visitor=(int)application.getAttribute(Constants.visitorCount);
    	online++;
    	visitor++;
		application.setAttribute(Constants.onlineCount, online);
		application.setAttribute(Constants.visitorCount, visitor);
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("Session destroyed!!!");
    	HttpSession session=arg0.getSession();
    	
//    	MyCourseListBean myCourseListBean=(MyCourseListBean)session.getAttribute("myCourseList");
//    	if(myCourseListBean!=null){
//    		session.setAttribute("myCourseList", null);
//    	}
    	ServletContext application=session.getServletContext();
    	int online=(int) application.getAttribute(Constants.onlineCount);
    	int visitor=(int)application.getAttribute(Constants.visitorCount);
    	visitor--;
		online--;
    	application.setAttribute(Constants.onlineCount, online);
    	application.setAttribute(Constants.visitorCount, visitor);
    }
	
}
