package com.midiasocial.controller;

import com.abstracts.controller.Controller;
import com.midiasocial.model.Criterio;
import com.midiasocial.model.PalavraChaveMidia;
import com.midiasocial.view.CriterioView;
import de.steinwedel.vaadin.MessageBox;

public class CriterioController extends Controller {
	
	private Criterio criterio = null;
	private CriterioView criterioView = null;
	private PalavraChaveMidia palavra = null;
	
	public CriterioController(){
	   
	}
	
	public boolean salvar(Criterio criterio){
		
		if(criterio.salvar()){
			String palavras = criterioView.getpChaveForm().palavraChave.getValue().toString();
	        String listPalavras[] = palavras.split(";");
			String palavrasRepetidas = "";
			
			for (int i = 0; i < listPalavras.length; i++) {
				palavra = new PalavraChaveMidia();
				palavra.setPalavraChave(listPalavras[i].trim());
				palavra.setCriterio(criterio);
				
				if(!palavra.salvar()){
					palavrasRepetidas += listPalavras[i] +"\n";
				}
			}
			
			if(palavrasRepetidas.length() > 0){
				criterioView.msg("Palavra-chave repetida: "+palavrasRepetidas, MessageBox.Icon.WARN);
			}
			criterioView.refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(Criterio criterio){
		
		if(criterio.alterar()){
			criterioView.refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(Criterio criterio){
			if(remover(criterio.getId())){
				return true;
			}
			else {
				return false;
			}
	}
	
	public boolean remover(Long id){
		boolean result;
		System.out.println("ID: "+id);
		Criterio criterio = Criterio.pesquisaCriterioID(id);
		
		result = criterio.remover();
		criterioView.refreshTable();
		return result;
	}
	
	public Criterio getCriterio() {
		if(criterio == null){
			criterio = new Criterio();
		}	
		return criterio;
	}
	
	public Criterio getNovoCriterio(){
		criterio = new Criterio();
		return criterio;
	}

	public CriterioView getcriterioView() {
		return criterioView;
	}

	public void setcriterioView(CriterioView criterioView) {
		this.criterioView = criterioView;
	}

	public void setcriterio(Criterio criterio) {
		this.criterio = criterio;
	}

	public CriterioView getView(){
		criterioView.refreshTable();
		return this.criterioView;
	}
	
	public void visualizar(Object event){
		
		criterio = (Criterio) event;
		
		Object[] dados = new Object[15];
		criterioView.visualizar(dados);
	}
	
	@Override
	public void carregaControle() {
		 criterioView = new CriterioView(this);
		 criterioView.modoTabela();
		 criterioView.refreshTable();
		
	}
}
