package com.midiasocial.controller;

import com.midiasocial.view.PaginaView;

public class PaginaController {

	private PaginaView pageView = null;
	
	public PaginaController(){
			pageView = new PaginaView(this);
		}
	
	public PaginaView getView(){
		
		return this.pageView;
	}
}
