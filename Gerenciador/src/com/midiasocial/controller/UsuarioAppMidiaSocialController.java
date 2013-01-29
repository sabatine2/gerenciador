package com.midiasocial.controller;

import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.view.UsuarioAppMidiaSocialView;
import com.vaadin.data.util.BeanItemContainer;

public class UsuarioAppMidiaSocialController {
	
	private UsuarioAppMidiaSocial usuario = null;
	private UsuarioAppMidiaSocialView usuarioView = null;
	
	public UsuarioAppMidiaSocialController(){
	    usuarioView = new UsuarioAppMidiaSocialView(this);
	    usuarioView.modoTabela();
	    refreshTable();
	}
	
	public boolean salvar(UsuarioAppMidiaSocial usuario){
		
		if(usuario.salvar()){
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(UsuarioAppMidiaSocial usuario){
		
		if(usuario.alterar()){
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(UsuarioAppMidiaSocial usuario){
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
		UsuarioAppMidiaSocial usuario = UsuarioAppMidiaSocial.pesquisaUsuarioID(id);
		
		result = usuario.remover();
		refreshTable();
		return result;
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
		refreshTable();
		return this.usuarioView;
	}
	
	public void visualizar(Object event){
		
		usuario = (UsuarioAppMidiaSocial) event;
		
		Object[] dados = new Object[15];
		usuarioView.visualizar(dados);
	}
	
	public void refreshTable(){
		defaultTable();		
	}
	
	public void defaultTable(){
		usuarioView.tabelaFiltro.tableMain.setContainerDataSource(new BeanItemContainer<UsuarioAppMidiaSocial>(UsuarioAppMidiaSocial.class, UsuarioAppMidiaSocial.listaUsuario()));
		usuarioView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"idInterno", "nome", "screenName", "status"});
	}
}
