package com.auditor.controller;

import com.auditor.model.CentroCusto;
import com.auditor.model.contato.EnderecoAuditor;
import com.auditor.view.CentroCustoView;

public class CentroCustoController  {

	private CentroCusto centroCusto = null;
	private CentroCustoView centroView = null;
	public OrganizacaoController organizacaoController;
	
	public CentroCustoController(OrganizacaoController organizacaoController){
		this.organizacaoController = organizacaoController;
	     centroView = new CentroCustoView(this);
	     centroView.modoTabela();
	}
	
	public boolean salvar(CentroCusto centroCusto){
		
		EnderecoAuditor endereco = new EnderecoAuditor();
		boolean ok;
		
		try{
			endereco = centroView.getEnderecoForm().getEndereco();
			endereco.salvar();
						
			centroView.getCentroForm().getCentro().setEndereco(endereco);
			ok = true;
		}
		catch (Exception e) {
			centroView.msgErro(e);
			ok = false;
		}
			
		if(ok == true){			
			centroCusto.salvar();
			organizacaoController.getOrganizacao().getCentroCusto().add(centroCusto);
			
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(CentroCusto centroCusto){
		
		EnderecoAuditor endereco = new EnderecoAuditor();
		boolean ok;
		
		try{
			endereco = centroView.getEnderecoForm().getEndereco();
			endereco.alterar();
			
			centroView.getCentroForm().getCentro().setEndereco(endereco);
			ok = true;
		}
		catch (Exception e) {
			centroView.msgErro(e);
			ok = false;
		}
			
		if(ok == true){
			centroCusto.alterar();
			
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(CentroCusto centroCusto){
		
		if (remover(centroCusto.getId())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		CentroCusto centro = CentroCusto.pesquisaCentroCustoID(id);
		
		result = centro.remover();
		organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
		return result;
	}
	
	public CentroCusto getCentro() {
		if(centroCusto == null){
			centroCusto = new CentroCusto();
			centroCusto.setOrganizacao(organizacaoController.getOrganizacao());
		}
		return centroCusto;
	}
	
	public CentroCusto getNovoCentro() {
		centroCusto = new CentroCusto();
		centroCusto.setOrganizacao(organizacaoController.getOrganizacao());
		return centroCusto;
	}
	
	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	public CentroCustoView getCentroView() {
		return centroView;
	}

	public void setCentroView(CentroCustoView centroView) {
		this.centroView = centroView;
	}

	public CentroCustoView getView(){
		 return this.centroView;	
	}
	
	public void visualizar(Object event){
		
		centroCusto = (CentroCusto) event;
		centroView.visualizar();
	}
	

}