package com.endereco.controller;

import com.endereco.model.Endereco;
import com.endereco.view.EnderecoView;
import com.googlemaps.GeocodingException;

public class EnderecoAuditorController  {

	private Endereco enderecoAuditor = null;
	private EnderecoView enderecoView = null;
	
	public EnderecoAuditorController(){
	     enderecoView = new EnderecoView(this);
	     enderecoView.modoTabela();
	     defaultTable();
	}
	
	public boolean salvar(Endereco enderecoAuditor){
		
		if(enderecoAuditor.salvar()){
			defaultTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(Endereco enderecoAuditor){
		
		if(enderecoAuditor.alterar()){
			defaultTable();
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(Endereco endereco){
		
		if(remover(endereco.getId())){
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		
		Endereco endereco = Endereco.pesquisaId(id);
		result = endereco.remover();
		defaultTable();
		return result;
	}
	
	public Endereco getEndereco() {
		if(enderecoAuditor == null){
			enderecoAuditor = new Endereco();
		}
		return enderecoAuditor;
	}

	public Endereco getNovoEndereco(){
		enderecoAuditor = new Endereco();
		return enderecoAuditor;
	}

	public void setEnderecoAuditor(Endereco enderecoAuditor) {
		this.enderecoAuditor = enderecoAuditor;
	}

	
	public EnderecoView getEnderecoView() {
		return enderecoView;
	}

	
	public void setEnderecoView(EnderecoView enderecoView) {
		this.enderecoView = enderecoView;
	}

	public EnderecoView getView(){
		  return this.enderecoView;
	}
	
	public void visualizar(Object event){
		
		enderecoAuditor = (Endereco) event;
		
		Object[] dados = new Object[10];
		dados[0] = enderecoAuditor.getBairro();
		dados[1] = enderecoAuditor.getCep();
		dados[2] = enderecoAuditor.getCidade();
		dados[3] = enderecoAuditor.getComplemento();
		dados[4] = enderecoAuditor.getEstado();
		dados[5] = enderecoAuditor.getLogradouro();
		dados[6] = enderecoAuditor.getNumero();
		dados[7] = enderecoAuditor.getObs();
		dados[8] = enderecoAuditor.getStatus();
		dados[9] = enderecoAuditor.getTipo();		
		
		enderecoView.visualizar(dados);
	}
	
	public void defaultTable(){
		enderecoView.tabelaFiltro.tableMain.setContainerDataSource(Endereco.ListaBens());
		enderecoView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"tipo", "logradouro", "numero" ,"bairro",
				"cep", "cidade", "estado", "status", "latitude", "longitude", "altura"});
	}
}