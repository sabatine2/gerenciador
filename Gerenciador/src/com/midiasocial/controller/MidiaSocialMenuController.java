package com.midiasocial.controller;

import com.abstracts.controller.Controller;
import com.midiasocial.view.menu.MidiaSocialMenu;
import com.principal.controller.GerenciadorController;

public class MidiaSocialMenuController{

	private GerenciadorController gerenciadorController;
	private MidiaSocialMenu midiaSocialMenu;
	
	public MidiaSocialMenuController(final GerenciadorController gerenciadorController){
		this.gerenciadorController = gerenciadorController;
		this.midiaSocialMenu = new MidiaSocialMenu(this);
	}
	
	public MidiaSocialMenu getMidiaSocialMenu() {
		return midiaSocialMenu;
	}

	public void setMidiaSocialMenu(MidiaSocialMenu midiaSocialMenu) {
		this.midiaSocialMenu = midiaSocialMenu;
	}

	public GerenciadorController getGerenciadorController() {
		return gerenciadorController;
	}

	public void setGerenciadorController(GerenciadorController gerenciadorController) {
		this.gerenciadorController = gerenciadorController;
	}

	
}
