package com.login.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.login.model.Login;
import com.login.view.LoginView;
import com.principal.controller.GerenciadorController;
import com.principal.helper.Session;
import com.usuario.model.Usuario;

public class LoginController {

	private LoginView loginView;
	private GerenciadorController gerenciadorController;
	
	public LoginController(){
		
		this.loginView = new LoginView(this);
	}
	
    public boolean valida(Object dados[]){
		
		String nome  = (String) dados[0];
		String senha =(String) dados[1];
		try{
		Usuario usuario = Usuario.autenticaUsu(nome, senha);
	    	if(usuario != null){
	    		
	    		List<Login> l2 = Login.buscaLoginUsuarioAtivo(usuario);
	    		for(Iterator<Login> i = l2.iterator(); i.hasNext();){
	    			Login login = i.next();
	    			login.setDataEncerramento(new Date());
	    			login.setAtivo(false);
	    			login.alterar();
	    		}
	    		
	    		Login login = new Login((String) dados[4], (String) dados[3] , (String) dados[2], usuario,true,(Integer) dados[5] ,(Integer) dados[6]);
	    		usuario.setLogin(login);
	    		login.salvar();
	    		
	    		Session s = new Session();
	    		s.setUsuario(usuario);
	    		gerenciadorController.setSession(s);
	    		
	    		gerenciadorController.getMainWindow().removeWindow(getView());
	    		gerenciadorController.init();
	    		return true;
	    	}
	    	else{
	    		return false;
	    	}
		}
		catch (Exception e){
			System.out.print(e.getMessage());
    		return false;
		}
	}
		
	public LoginView getView(){
		return this.loginView;
	}	
	
	public void setGerenciadorController(GerenciadorController gerenciadorDespesas){
		this.gerenciadorController = gerenciadorDespesas;
	}

	
}
