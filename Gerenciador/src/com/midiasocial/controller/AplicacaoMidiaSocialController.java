package com.midiasocial.controller;

import com.abstracts.controller.Controller;
import com.midiasocial.model.AplicacaoMidiaSocial;
import com.midiasocial.view.AplicacaoMidiaSocialView;
import com.vaadin.data.util.BeanItemContainer;

public class AplicacaoMidiaSocialController extends Controller{
	
	private AplicacaoMidiaSocial app = null;
	private AplicacaoMidiaSocialView appView = null;
	
	public AplicacaoMidiaSocialController(){
	   
	}
	
	public boolean salvar(AplicacaoMidiaSocial app){
		
		if(app.salvar()){
			appView.refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(AplicacaoMidiaSocial app){
		
		if(app.alterar()){
			appView.refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(AplicacaoMidiaSocial app){
			if(app.remover()){
				return true;
			}
			else {
				return false;
			}
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
		appView.refreshTable();
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

	@Override
	public void carregaControle() {
		 appView = new AplicacaoMidiaSocialView(this);
		 appView.modoTabela();
		 appView.refreshTable();
		
	}
	

}
