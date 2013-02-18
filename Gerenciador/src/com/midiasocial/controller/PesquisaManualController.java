package com.midiasocial.controller;

import com.abstracts.controller.Controller;
import com.midiasocial.view.PesquisaManualView;

public class PesquisaManualController extends Controller{

	private PesquisaManualView pesquisaView = null;
	
	public PesquisaManualController(){
		
		pesquisaView = new PesquisaManualView(this);
	}
	
	public PesquisaManualView getView(){
		
		return this.pesquisaView;
	}

	@Override
	public void carregaControle() {
		// TODO Auto-generated method stub
		
	}
}
