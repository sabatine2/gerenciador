package com.midiasocial.controller;

import com.abstracts.controller.Controller;
import com.midiasocial.model.UsuarioPubMidiaSocial;
import com.midiasocial.service.MidiaSocialService;
import com.midiasocial.view.UsuarioPubMidiaSocialView;

public class UsuarioPubMidiaSocialController extends Controller {
	
	private UsuarioPubMidiaSocial usuario = null;
	private UsuarioPubMidiaSocialView usuarioView = null;
	
	public UsuarioPubMidiaSocialController(){
	   
	}
	
	public boolean salvar(UsuarioPubMidiaSocial usuario){
		
		if(usuario.salvar()){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(UsuarioPubMidiaSocial usuario){
		
		if(usuario.alterar()){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(UsuarioPubMidiaSocial usuario){
			if(remover(usuario.getIdInterno())){
				return true;
			}
			else {
				return false;
			}
	}
	
	public boolean remover(Long id){
		boolean result;
		System.out.println("ID: "+id);
		UsuarioPubMidiaSocial usuario = UsuarioPubMidiaSocial.pesquisaUsuarioID(id);
		result = usuario.remover();
		return result;
	}
	
	public void visualizar(Object event){
		
		usuario = (UsuarioPubMidiaSocial) event;
		
		Object[] dados = new Object[15];
		usuarioView.visualizar(dados);
	}
	
	public UsuarioPubMidiaSocial getUsuario() {
		if(usuario == null){
			usuario = new UsuarioPubMidiaSocial();
		}	
		return usuario;
	}
	
	public UsuarioPubMidiaSocial getNovoUsuario(){
		usuario = new UsuarioPubMidiaSocial();
		return usuario;
	}

	public UsuarioPubMidiaSocialView getUsuarioView() {
		return usuarioView;
	}

	public void setUsuarioView(UsuarioPubMidiaSocialView usuarioView) {
		this.usuarioView = usuarioView;
	}

	public void setUsuario(UsuarioPubMidiaSocial usuario) {
		this.usuario = usuario;
	}

	public UsuarioPubMidiaSocialView getView(){
		return this.usuarioView;
	}
	
	public void importarPagina(){
		
	}

	@Override
	public void carregaControle() {
		 usuarioView = new UsuarioPubMidiaSocialView(this);
		 usuarioView.modoTabela();
	}
	
	
}
