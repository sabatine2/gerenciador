package com.midiasocial.controller;

import com.abstracts.controller.Controller;
import com.midiasocial.view.PaginaView;

public class PaginaController extends Controller {

	private PaginaView pageView = null;
	
	public PaginaController(){}
	
	public PaginaView getView(){
		
		return this.pageView;
	}

	@Override
	public void carregaControle() {
		pageView = new PaginaView(this);
	}
}
