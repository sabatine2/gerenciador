package com.abstracts.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAO<T> {

    private Class<?> persistentClass;
    protected Session session;
    protected Transaction tx = null;
    
    public DAO(Session session, Class<?> persistentClass) {
        this.session = session;
        this.persistentClass = persistentClass;
    }

    protected Session getSession(){
		return session; 	
	}
    
    @SuppressWarnings("unchecked")
    public T load(Long id) {
        System.out.println("lendo " + persistentClass + " com id " + id);
        return (T) session.load(persistentClass, id);
    }
   
    public boolean save(T t) {
    	System.out.println("salvando  " + t);
    	try{
    	tx = session.beginTransaction();
    	session.save(t);
    	tx.commit();
    	return true;
    	}
    	catch (Exception e) {
    		System.out.println("salvando ou merge  " + e.getMessage());
        	tx.rollback();
    		return false;
		}
    	
    }
    
    public boolean saveOrMerge(T t) {
    	System.out.println("salvando ou alterando  " + t);
    	try{
    	tx = session.beginTransaction();
    	session.saveOrUpdate(t);
    	tx.commit();
    	return true;
    	}
    	catch (Exception e) {
    		tx.rollback();
    		System.out.println("salvando ou merge  " + e.getMessage());
    		return false;
		}
    }
    
	public boolean delete(T t) {
		System.out.println("delete  " + t);
    	try{
    	tx = session.beginTransaction();
    	session.delete(t);
    	tx.commit();
    	return true;
    	}
    	catch (Exception e) {
    		tx.rollback();
    		System.out.println(e.getMessage());
    		return false;
    	}
	}
	
    @SuppressWarnings("unchecked")
	public List<T> list() {
    	System.out.println("Listando todos");
    	return session.createCriteria(persistentClass).list();
    }

    public boolean merge(T t) {
    	System.out.println("merge  " + t);
    	try{
    	tx = session.beginTransaction();
    	session.merge(t);
    	tx.commit();
    	System.out.println("sucesso merge" + t);
        return true;
    	}
    	catch (Exception e) {
    		System.out.println("erro  merge " + e);
    	    tx.rollback();
    		return false;
		}
    }
    
    public boolean update(T t) {
    	System.out.println("update  " + t);
    	try{
    	tx = session.beginTransaction();
    	session.update(t);
    	tx.commit();
    	return true;
    	}
    	catch (Exception e) {
    		tx.rollback();
    		return false;
		}
    }
    
}