package com.ticket.controller;

import com.ticket.model.Prioridade;
import com.ticket.view.PrioridadeView;

public class PrioridadeController {

	private Prioridade prioridade = null;
	private PrioridadeView prioridadeView = null;
	
	public PrioridadeController(){
	}
	
	public boolean salvar(Prioridade prioridade){
		
		if(prioridade.salvar()){
		
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean alterar(Prioridade prioridade){
		
		if(prioridade.alterar()){
	
			return true;
		}
		else {
			return false;
		}	
	}
	

	public void visualizar(Object event){
		
		prioridade = ( Prioridade ) event;
		prioridadeView.visualizar();
	}
	
	
	public boolean removerButton(Prioridade prioridade){
		
		if(remover(prioridade.getId())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		Prioridade prioridade = Prioridade.pesquisaOrdemID(id);
		result = prioridade.remover();
		return result;
	}
	
	public Prioridade getPrioridade() {
		if(prioridade == null){
			prioridade = new Prioridade();
		}
		return prioridade;
	}
	
	public Prioridade getNovoPrioridade() {
		prioridade = new Prioridade();
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public PrioridadeView getView(){
		if(prioridadeView == null){
	        prioridadeView = new PrioridadeView(this);
		}
	    prioridadeView.modoTabela();
		return prioridadeView;
	}
	

}