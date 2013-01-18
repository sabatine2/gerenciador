package com.midiasocial.controller;

import com.midiasocial.view.PesquisaManualView;

public class PesquisaManualController {

	private PesquisaManualView pesquisaView = null;
	
	public PesquisaManualController(){
		
		pesquisaView = new PesquisaManualView(this);
	}
	
	public PesquisaManualView getView(){
		
		return this.pesquisaView;
	}
}
