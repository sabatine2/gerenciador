package com.midiasocial.controller;

import com.midiasocial.model.AplicacaoMidiaSocial;
import com.midiasocial.view.AplicacaoMidiaSocialView;

public class AplicacaoMidiaSocialController {
	
	private AplicacaoMidiaSocial app = null;
	private AplicacaoMidiaSocialView appView = null;
	
	public AplicacaoMidiaSocialController(){
	    appView = new AplicacaoMidiaSocialView(this);
	    appView.modoTabela();
	    refreshTable();
	}
	
	public boolean salvar(AplicacaoMidiaSocial app){
		
		if(app.salvar()){
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(AplicacaoMidiaSocial app){
		
		if(app.alterar()){
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(AplicacaoMidiaSocial app){
			if(remover(app.getId())){
				return true;
			}
			else {
				return false;
			}
	}
	
	public boolean remover(Long id){
		boolean result;
		System.out.println("ID: "+id);
		AplicacaoMidiaSocial app = AplicacaoMidiaSocial.pesquisaAppID(id);
		
		result = app.remover();
		refreshTable();
		return result;
	}
	
	public AplicacaoMidiaSocial getApp() {
		if(app == null){
			app = new AplicacaoMidiaSocial();
		}	
		return app;
	}

	public AplicacaoMidiaSocialView getAppView() {
		return appView;
	}

	public void setAppView(AplicacaoMidiaSocialView appView) {
		this.appView = appView;
	}

	public void setApp(AplicacaoMidiaSocial app) {
		this.app = app;
	}

	public AplicacaoMidiaSocialView getView(){
		refreshTable();
		return this.appView;
	}
	
	public void visualizar(Object event){
		
		app = (AplicacaoMidiaSocial) event;
		
		Object[] dados = new Object[4];
		dados[0] = app.getApiKey();
		dados[1] = app.getApiSecret();
		dados[2] = app.getNome();
		dados[3] = app.getRedeSocial();
		appView.visualizar(dados);
	}
	
	public void refreshTable(){
		defaultTable();		
	}
	
	public void defaultTable(){
		appView.tabelaFiltro.tableMain.setContainerDataSource(AplicacaoMidiaSocial.listaBeans());
		appView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id", "nome", "redeSocial"});
	}
}
