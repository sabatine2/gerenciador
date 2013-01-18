package com.regiao.controller;


import java.util.Iterator;

import com.regiao.model.AreaCobertura;
import com.regiao.model.Regiao;
import com.regiao.view.RegiaoView;

@SuppressWarnings("rawtypes")
public class RegiaoController {

	private Regiao regiao = null;
	private RegiaoView regiaoView = null;

	
	public RegiaoController(){
		
		regiaoView = new RegiaoView(this);
		regiaoView.modoTabela();
		defaultTable();
	}
	
	public boolean salvar(Regiao regiao){
		
		if(regiao.salvar()){
			
			for (Iterator iterator = regiaoView.getArea().iterator(); iterator.hasNext();) {
				AreaCobertura areaV = (AreaCobertura) iterator.next();
				
		        if(areaV.getId() == null){
		        	areaV.setRegiao(regiao);
		        	areaV.salvar();
		        }
		        else {
		        	areaV.alterar();
		        }
			}
			
			defaultTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean alterar(Regiao regiao){
		
		if(regiao.alterar()){
			defaultTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean removerButton(Regiao regiao){
		
		if(remover(regiao.getId())){
			defaultTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		Regiao regiao = Regiao.pesquisaRegiaoID(id);
		
		result = regiao.remover();
		defaultTable();
		return result;
	}
	
	public Regiao getRegiao() {
		if(regiao == null){
			regiao = new Regiao();
		}
		return regiao;
	}
	
	public Regiao getNovaRegiao(){
		regiao = new Regiao();
		return regiao;
	}
	
	public RegiaoView getView(){
		defaultTable();
		return this.regiaoView;
	}
	
	public void visualizar(Object event){
		
		regiao = ( Regiao ) event;
		Object[] dados = new Object[25];
		regiaoView.visualizar(dados);
	}
	
	public void defaultTable(){
		regiaoView.tabelaFiltro.tableMain.setContainerDataSource(Regiao.ListaBens());
		regiaoView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id", "nome", "status"});
	}
	
}
