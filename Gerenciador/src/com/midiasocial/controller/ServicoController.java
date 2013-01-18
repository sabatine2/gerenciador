package com.midiasocial.controller;

import com.midiasocial.model.Servico;
import com.midiasocial.view.service.MidiaSocialServiceView;
import com.usuario.model.Usuario;

public class ServicoController {
	
	private Servico servico = null;
	private MidiaSocialServiceView midiaSocialServiceView = null;
	private Usuario usuario;
	
	public ServicoController(Usuario usuario){
		this.usuario = usuario;
		midiaSocialServiceView = new MidiaSocialServiceView(this);
	    midiaSocialServiceView.modoTabela();
	    getMidiaSocialServiceView().refreshTable();
	}
	
	public void salvar(Servico servico){
		servico.setUsuario(usuario);
		servico.salvar();
	}
	
	public boolean alterar(Servico servico){
		
		if(servico.alterar()){
			getMidiaSocialServiceView().refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public void editar(Servico servico){
		servico.alterar();
		setServico(servico);
	}
	
	public void remover(Servico servico){
		servico.remover();
		getMidiaSocialServiceView().refreshTable();
	}
	
	public Servico getServico() {
		if(servico == null){
			servico = new Servico();
		}	
		return servico;
	}

	public MidiaSocialServiceView getMidiaSocialServiceView() {
		return midiaSocialServiceView;
	}

	public void setMidiaSocialServiceView(MidiaSocialServiceView midiaSocialServiceView) {
		this.midiaSocialServiceView = midiaSocialServiceView;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public MidiaSocialServiceView getView(){
		getMidiaSocialServiceView().refreshTable();
		return this.midiaSocialServiceView;
	}
	
	public void visualizar(Object event){
		
		servico = (Servico) event;
		
		Object[] dados = new Object[15];
		midiaSocialServiceView.visualizar(dados);
	}
	
	public void alteraStatusServico(){
		if(getServico().servicoAtivo()){
			getServico().pararServico();
		}
		else{
			getServico().iniciarServico();
		}
	}
	
}
