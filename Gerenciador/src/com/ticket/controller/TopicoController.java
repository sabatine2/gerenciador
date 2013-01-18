package com.ticket.controller;

import com.ticket.model.Topico;
import com.ticket.view.TopicoView;

public class TopicoController {

	private Topico topico = null;
	private TopicoView topicoView = null;
	
	public TopicoController(){
	    topicoView = new TopicoView(this);
	    topicoView.modoTabela();
	    refreshTable();
	}
	
	public boolean salvar(Topico topico){
		
		topico.setPalavraChave(topicoView.pChaveForm.palavraChave.getValue().toString());
		
		if(topico.salvar()){
			refreshTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean alterar(Topico topico){
		
		if(topico.alterar()){
			refreshTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean removerButton(Topico topico){
		
		if(remover(topico.getId())){
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		Topico topico = Topico.pesquisaOrdemID(id);
		
		result = topico.remover();
		refreshTable();
		return result;
	}
	
	public Topico getTopico() {
		if(topico == null){
			topico = new Topico();
		}
		return topico;
	}
	
	public Topico getNovoTopico() {
		topico = new Topico();
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public TopicoView getTopicoView() {
		return topicoView;
	}

	public void setTopicoView(TopicoView topicoView) {
		this.topicoView = topicoView;
	}

	public TopicoView getView(){
		 refreshTable();
		 return this.topicoView;	
	}
	
	public void visualizar(Object event){
		
		topico = ( Topico ) event;
		
		Object[] dados = new Object[25];
		dados[0] = topico.getNome();
		dados[1] = topico.getStatus();
		dados[2] = topico.getDescricao();
		
		topicoView.visualizar(dados);
	}
	
	public void refreshTable(){
		topicoView.tabelaFiltro.tableMain.removeAllItems();
		defaultTable();		
	}
	
	public void defaultTable(){
		topicoView.tabelaFiltro.tableMain.setContainerDataSource(Topico.listaBeans());
		topicoView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id", "nome", "prioridade", "observacao"});
	}
}