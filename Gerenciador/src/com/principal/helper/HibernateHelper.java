package com.principal.helper;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.exception.DAOException;
import com.principal.model.Aplicacao;

import java.sql.*;

//classe que conecta ao Hibernate

public class HibernateHelper{

	private final static SessionFactory sessionFactory;
	private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();
	private static ServiceRegistry serviceRegistry;
	public static Connection con        = null;  
	
	static {
		try {
			com.principal.model.Aplicacao dados = AplicacaoHelper.APLICACAO;
			
			Configuration configuration = new Configuration();
			System.setProperty("hibernate.connection.password",dados.getSenhaBanco());
			System.setProperty("hibernate.connection.username",dados.getUsuarioBanco());
			System.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
			/*
			System.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			*/
			System.setProperty("hibernate.connection.url",dados.getUrl());
			System.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2008Dialect");
			/*
			System.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			*/
			System.setProperty("hibernate.show_sql","false");
			System.setProperty("hibernate.use_sql_comments","false");
			System.setProperty("hibernate.format_sql","false");
			System.setProperty("hibernate.hbm2ddl.auto","update");
			System.setProperty("hibernate.jdbc.batch_size","20");
			System.setProperty("hibernate.cache.use_second_level_cache","true");
			System.setProperty("hibernate.current_session_context_class","thread");
			System.setProperty("hibernate.transaction.factory_class","org.hibernate.transaction.JDBCTransactionFactory");
			System.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
			System.setProperty("hibernate.current_session_context_class","org.hibernate.context.ThreadLocalSessionContext");  
			System.setProperty("c3p0.acquire_increment","1");   
			System.setProperty("c3p0.timeout","1800"); 
			System.setProperty("c3p0.min_size","3");  
			System.setProperty("c3p0.max_size","30"); 
			System.setProperty("c3p0.max_statements","0");
			System.setProperty("c3p0.idle_test_period","900");
			configuration.setProperties(System.getProperties());
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
			configuration.getProperties()).buildServiceRegistry();
			configuration.buildSettings(serviceRegistry);
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
			}
		
		}

	public static Session currentSession() throws HibernateException {  

		 Session session = null;
		 try{
			 session = (Session) sessions.get(); 
		 }catch(HibernateException hex){
		     hex.printStackTrace();
		 }
		 if(session == null){
		     session = sessionFactory.openSession();
		     sessions.set(session);  
		 }else{
			 session.clear();
		 }
		return session;  
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}

	public static void closeSession() {  
       Session session = (Session) sessions.get();  
       sessions.set(null);  
         
       if (session != null && session.isOpen()) {  
           session.flush();  
           session.close();  
       }  
   }  
	
	public static boolean testeConexao(Aplicacao app) throws DAOException{
		 //jdbc:sqlserver://localhost:1433;databaseName=CRM_MIDIA
		 String url             = app.getUrl();  
		 String user            = app.getUsuarioBanco();  
		 String pwd             = app.getSenhaBanco();  
		 
		 boolean sucesso = false;
		 
	     try {  
	    	 try {
			    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			 } catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 con = DriverManager.getConnection(url, user, pwd);  
	         con.setAutoCommit(false);  
	         sucesso = true;
	         
	     } catch (ClassNotFoundException e) {  
	    	 throw new DAOException("Ocorreu um erro no sistema ao se conectar em" + url  
	                 + "\n, se o problema persistir contate o administrador:\n Driver n‹o encontrado!");
	     } catch (SQLException e) {  
	    	 throw new DAOException("Ocorreu um erro no sistema, se o problema persistir contate o administrador:\n Erro na Conex‹o com Banco\n"+ e);  
	     }  
	    
	     return sucesso;
	  }
}