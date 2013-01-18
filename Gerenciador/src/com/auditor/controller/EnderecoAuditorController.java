package com.auditor.controller;

import com.auditor.dao.EnderecoAuditorDAO;
import com.auditor.model.contato.EnderecoAuditor;
import com.auditor.view.EnderecoAuditorView;
import com.googlemaps.GeocodingException;

public class EnderecoAuditorController  {

	private EnderecoAuditor enderecoAuditor = null;
	private EnderecoAuditorView enderecoView = null;
	
	public EnderecoAuditorController(){
	     enderecoView = new EnderecoAuditorView(this);
	     enderecoView.modoTabela();
	     defaultTable();
	}
	
	public boolean salvar(EnderecoAuditor enderecoAuditor) throws GeocodingException{
		
		if(enderecoAuditor.salvar()){
			defaultTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(EnderecoAuditor enderecoAuditor) throws GeocodingException{
		
		if(enderecoAuditor.alterar()){
			defaultTable();
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(EnderecoAuditor endereco){
		
		if(remover(endereco.getId())){
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		
		org.hibernate.Session s = com.principal.helper.HibernateUtil.openSession();
		EnderecoAuditorDAO enderecoDAO = new EnderecoAuditorDAO(s, EnderecoAuditor.class);
		EnderecoAuditor endereco = enderecoDAO.buscaEnderecoAuditor(id);
		s.close();
	
		result = endereco.remover();
		defaultTable();
		return result;
	}
	
	public EnderecoAuditor getEndereco() {
		if(enderecoAuditor == null){
			enderecoAuditor = new EnderecoAuditor();
		}
		return enderecoAuditor;
	}

	public EnderecoAuditor getNovoEndereco(){
		enderecoAuditor = new EnderecoAuditor();
		return enderecoAuditor;
	}

	public void setEnderecoAuditor(EnderecoAuditor enderecoAuditor) {
		this.enderecoAuditor = enderecoAuditor;
	}

	
	public EnderecoAuditorView getEnderecoView() {
		return enderecoView;
	}

	
	public void setEnderecoView(EnderecoAuditorView enderecoView) {
		this.enderecoView = enderecoView;
	}

	public EnderecoAuditorView getView(){
		  return this.enderecoView;
	}
	
	public void visualizar(Object event){
		
		enderecoAuditor = (EnderecoAuditor) event;
		
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
		enderecoView.tabelaFiltro.tableMain.setContainerDataSource(EnderecoAuditor.ListaBens());
		enderecoView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"tipo", "logradouro", "numero" ,"bairro",
				"cep", "cidade", "estado", "status", "latitude", "longitude", "altura"});
	}
}