package com.principal.helper;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

//classe que conecta ao Hibernate

public class HibernateUtil{

	private final static SessionFactory sessionFactory;
	private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();
	private static ServiceRegistry serviceRegistry;
	
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
			configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
			}
		
		}

	public static Session currentSession() throws HibernateException {  

        Session s = (Session)sessions.get();  
        if (s == null) {  
            s = sessionFactory.openSession();  
            sessions.set(s);  
        }  
        return s;  
	}

	public static Session openSession() {
		sessions.set(sessionFactory.openSession());
		return sessions.get();
	}

	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static void closeCurrentSession() {
		sessions.get().close();
		sessions.set(null);
	}

	public static SessionFactory getSessionFactory() {
    	return sessionFactory;
	}
	
}