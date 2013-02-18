package com.midiasocial.controller;

import com.abstracts.controller.Controller;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.view.UsuarioAppMidiaSocialView;
import com.vaadin.data.util.BeanItemContainer;

public class UsuarioAppMidiaSocialController extends Controller {
	
	private UsuarioAppMidiaSocial usuario = null;
	private UsuarioAppMidiaSocialView usuarioView = null;
	
	public UsuarioAppMidiaSocialController(){
	    
	}
	
	public boolean salvar(UsuarioAppMidiaSocial usuario){
		
		if(usuario.salvar()){
			usuarioView.refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public void importaDado(){
		usuario.importarDados();
	}
	
	public boolean alterar(UsuarioAppMidiaSocial usuario){
		
		if(usuario.alterar()){
			usuarioView.refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(UsuarioAppMidiaSocial usuario){
			if(usuario.remover()){
				return true;
			}
			else {
				return false;
			}
	}
	
	public UsuarioAppMidiaSocial getUsuario() {
		if(usuario == null){
			usuario = new UsuarioAppMidiaSocial();
		}	
		return usuario;
	}
	
	public UsuarioAppMidiaSocial getNovoUsuario(){
		usuario = new UsuarioAppMidiaSocial();
		return usuario;
	}

	public UsuarioAppMidiaSocialView getUsuarioView() {
		return usuarioView;
	}

	public void setUsuarioView(UsuarioAppMidiaSocialView usuarioView) {
		this.usuarioView = usuarioView;
	}

	public void setUsuario(UsuarioAppMidiaSocial usuario) {
		this.usuario = usuario;
	}

	public UsuarioAppMidiaSocialView getView(){
		usuarioView.refreshTable();
		return this.usuarioView;
	}
	
	public void visualizar(Object event){
		
		usuario = (UsuarioAppMidiaSocial) event;
		
		Object[] dados = new Object[15];
		usuarioView.visualizar(dados);
	}

	@Override
	public void carregaControle() {
		usuarioView = new UsuarioAppMidiaSocialView(this);
	    usuarioView.modoTabela();
	    usuarioView.refreshTable();
		
	}
	
	
	
}
