package com.principal.helper;

import java.util.HashMap;

import com.usuario.model.Usuario;

public class Session {

	 private static HashMap<Long, Session> instance = new HashMap<Long, Session>();  
	 private Usuario usuario;  
	  
	   public Session(){  
	   }  
	  
	   public void setUsuario(Usuario usuario){  
	      this.usuario = usuario;  
	   }  
	  
	   public Usuario getUsuario(){  
	       return usuario;  
	   }
	   
	   public static void finalizeInstance(Long id){
		   instance.remove(id); 
	   }
	   
	   public static Session getInstance(Long id){
		      return instance.get(id);
	   }
	   public static void addInstance(Session session){
		    instance.put(session.getUsuario().getId(), session);
	   }
	   
}
