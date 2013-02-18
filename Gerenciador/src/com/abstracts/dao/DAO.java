package com.abstracts.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.exception.DAOException;
import com.principal.helper.HibernateHelper;

/**
 * Classe generica de DAO para tratar os metodos CRUD
 * 
 * @author 
 * 
 */
public class DAO<T> {

    private Class<?> persistentClass;
    protected Transaction tx = null;
    private Logger logger = Logger.getLogger(DAO.class);  

    public DAO(Class<?> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Retorna apenas um objeto referente a classe e id informados
     * @param id
     * @return Object
     */
    @SuppressWarnings("unchecked")
    public T load(Long id) {
    	Session session = HibernateHelper.currentSession();
    	System.out.println("Carregando " + persistentClass + " com id " + id);
    	logger.info("Carregando " + persistentClass + " com id " + id);
        return (T) session.load(persistentClass, id);
    }
   
    /**
     * Persiste o objeto passado por par‰metro
     * @param obj 
     */
    public boolean save(T t) throws DAOException {
    	Session session = null;
        try{
        	session = HibernateHelper.openSession();
	    	tx = session.beginTransaction();
	    	session.save(t);
	    	tx.commit();
	    	session.close();
	    	logger.info("Salvando  " + t);
	    	return true;
        }
    	catch (HibernateException e) {
    		logger.info("Erro Salvando " + e.getMessage());
    		tx.rollback();
    		session.close();
    		throw new DAOException(e.getMessage());
		}
      
    }
    
    /**
     * Persiste ou altera o objeto passado por par‰metro
     * @param obj 
     */
   public boolean saveOrMerge(T t) throws DAOException{
	   Session session = null;
       try{
       	session = HibernateHelper.openSession();
	    	tx = session.beginTransaction();
	    	session.saveOrUpdate(t);
	    	tx.commit();
	    	session.close();
			logger.info("Salvando  " + t);
	    	return true;
    	}
    	catch (HibernateException e) {
    		tx.rollback();
    		logger.info("Erro Salvando ou merge  " + e.getMessage());
    		session.close();
    		throw new DAOException(e.getMessage());
    	}
    }
   
   /**
    * Exclui o objeto passado por par‰metro
    * @param obj 
    */
	public boolean delete(T t) throws DAOException{
		Session session = null;
        try{
        	session = HibernateHelper.currentSession();
			tx = session.beginTransaction();
	    	session.delete(t);
	    	tx.commit();
	    	logger.info("Delete  " + t);
	    	return false;
		}
    	catch (Exception e) {
    		tx.rollback();
    		logger.info("Erro Deletando " + e.getMessage());
    		throw new DAOException(e.getMessage());
    	}
  }
	
	 /**
     * Retorna todos os registros da tabela (classe) informada
     * @param objClass
     * @return List<Object>
     */
     public List<T> list() {
    	logger.info("Listando todos");
    	Session session = HibernateHelper.currentSession();
		return session.createCriteria(persistentClass).list();
    }

     /**
      * Altera o objeto passado por par‰metro
      * @param obj 
      */
    public boolean merge(T t) throws DAOException {
    	Session session = null;
        try{
        	session = HibernateHelper.openSession();
	    	tx = session.beginTransaction();
	    	session.merge(t);
	    	tx.commit();
	    	session.close();
	    	logger.info("Sucesso Merge" + t);
	    	return true;
        }
    	catch (HibernateException e) {
    		logger.info("Erro  Merge " + e.getMessage());
    	    tx.rollback();
    	    session.close();
    	    throw new DAOException(e.getMessage());
		}
	}
    
    public boolean update(T t) throws DAOException {
    	Session session = null;
        try{
        	session = HibernateHelper.openSession();
	    	tx = session.beginTransaction();
	    	session.update(t);
	    	tx.commit();
	    	session.close();
	    	logger.info("Sucesso Update" + t);
	    	return true;
    	}
    	catch (HibernateException e) {
    		logger.info("Erro update " + e.getMessage());
    		tx.rollback();
    		session.close();
     	    throw new DAOException(e.getMessage());
		}
   }
    
}