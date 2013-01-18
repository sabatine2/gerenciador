package com.telefone.controller;

import java.util.ArrayList;
import java.util.Collection;
import com.contato.model.Contato;
import com.telefone.model.TelefoneContato;
import com.telefone.view.TelefonePanel;

public class TelefoneController {

	private Collection<TelefoneContato> listaTelefone;
	private TelefonePanel telefoneView;
	private Contato contato;
	
	public TelefoneController(Collection<TelefoneContato> listaTelefone, Contato contato){
		this.listaTelefone = listaTelefone;
		this.contato = contato;
	}
	
	public TelefonePanel getView(){
	   telefoneView = new TelefonePanel(listaTelefone,this);
	   return telefoneView;
	}
	
	public void addTelefone(TelefoneContato telefone){
		telefone.setContato(contato);
		if(telefone.getId() == null){
			listaTelefone.add(telefone);
		}
	}
	
	public void visualizar(TelefoneContato telefone){
		
	  
	}
	
	public void ligar(String numero){
		//com.telecom.controller.TelefoneController telefone = TelecomUtil.getTelefoneController();
		//telefone.setTelefone(numero);
	}
	
	public boolean removerTelefone(TelefoneContato telefone){
		if(telefone.getId() != null){
		    telefone.remover();
		}
		
		listaTelefone.remove(telefone);	
		return true;
		
	}

	/**
	 * @return the listTelefone
	 */
	public Collection<TelefoneContato> getListaTelefone() {
		return listaTelefone;
	}

	/**
	 * @param listaTelefone the listaTelefone to set
	 */
	public void setListaTelefone(Collection<TelefoneContato> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}
	
	
}
