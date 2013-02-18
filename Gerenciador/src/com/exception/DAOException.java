package com.exception;
/**
 * Classe de exception do DAO 
 * Ser‡ criado no DAO e tratada no modelo.
 * @author 
 * 
 */
public class DAOException extends Exception{  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;  
	
	public DAOException(String msg){  
		super(msg);  
		this.msg = msg;  
	}  
	public String getMessage(){  
		return msg;  
	}  
}  

