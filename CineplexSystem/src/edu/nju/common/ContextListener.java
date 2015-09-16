package edu.nju.common;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.nju.cineplex.service.MemberManageService;
import edu.nju.cineplex.service.MemberManageServiceBean;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	private MemberManageService memberManageService=MemberManageServiceBean.getInstance();
    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	ServletContext servletContext= arg0.getServletContext();
    	Timer timer=new Timer();
    	timer.schedule(new MemberStateTask(),1000,1000*60*60);//每小时执行一次
    	servletContext.setAttribute(Constants.onlineCount, 0);
    	servletContext.setAttribute(Constants.loginCount, 0);
    	servletContext.setAttribute(Constants.visitorCount, 0);
    	System.out.println("Application initialized");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
    
    private class MemberStateTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			memberManageService.checkState();
			
		}
    	
    }
	
}
