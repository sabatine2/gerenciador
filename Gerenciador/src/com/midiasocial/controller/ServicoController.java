package com.midiasocial.controller;

import com.abstracts.controller.Controller;
import com.midiasocial.model.Servico;
import com.midiasocial.view.service.MidiaSocialServiceView;
import com.usuario.model.Usuario;

public class ServicoController extends Controller {
	
	private Servico servico = null;
	private MidiaSocialServiceView midiaSocialServiceView = null;
	
	public ServicoController(Usuario usuario){
		
	}
	
	public void salvar(Servico servico){
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
	
	public boolean alterarServico(){
		if (getServico().isAtivo()){
			getServico().setAtivo(false);
			getServico().alterar();
			return false;
		}
		else{
			getServico().setAtivo(true);
			getServico().alterar();
			return true;
		}
	}
	
	public boolean ativarServico(){
		getServico().inicializarServico();
		return true;
	}

	@Override
	public void carregaControle() {
		midiaSocialServiceView = new MidiaSocialServiceView(this);
	    midiaSocialServiceView.modoTabela();
	    getMidiaSocialServiceView().refreshTable();
		
	}
	
}
