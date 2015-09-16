package edu.nju.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration config = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static final ThreadLocal threadLocal = new ThreadLocal();
	public static Session currentSession() {
		Session currentSession = (Session) threadLocal.get();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			threadLocal.set(currentSession);
		}
		return currentSession;
	}
	public static void closeSession() {
		Session currentSession = (Session) threadLocal.get();
		if (currentSession != null) {
			if(currentSession.isOpen()){
				currentSession.close();
			}
			
		}
		threadLocal.set(null);
	}
	public static void flushClearClose(){
		Session currentSession = (Session) threadLocal.get();
		currentSession.flush();
		currentSession.clear();
		closeSession();
	}

}
