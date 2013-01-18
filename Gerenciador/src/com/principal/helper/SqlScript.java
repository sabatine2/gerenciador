package com.principal.helper;

import com.usuario.model.Usuario;

public class SqlScript {

	public static void createUsuaro(){
		
		if(Usuario.pesquisaNome("admin")== null){
		Usuario u = new Usuario();
		u.setNivel("admin");
		u.setNome("admin");
		u.setSenha("123456");
		u.salvar();
		u = null;
		}
		if(Usuario.pesquisaNome("admin")== null){
		Usuario u = new Usuario();
		u.setNivel("admin");
		u.setNome("sabatine");
		u.setSenha("123456");
		u.salvar();
		u=null;
		}
	}
	
}
