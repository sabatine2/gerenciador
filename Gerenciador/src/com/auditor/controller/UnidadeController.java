package com.auditor.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.auditor.model.CentroCusto;
import com.auditor.model.Departamento;
import com.auditor.model.Unidade;
import com.auditor.model.contato.EnderecoAuditor;
import com.auditor.view.UnidadeView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Label;

public class UnidadeController  {

	private Unidade unidade = null;
	private UnidadeView unidadeView = null;
	public OrganizacaoController organizacaoController;
	
	public UnidadeController(OrganizacaoController organizacaoController){
		this.organizacaoController = organizacaoController;
	     unidadeView = new UnidadeView(this);
	     unidadeView.modoTabela();
	     refreshTable();
	}
	
	public boolean salvar(Unidade unidade){
		
		EnderecoAuditor endereco = new EnderecoAuditor();
		boolean ok;
		
		try{
			endereco = unidadeView.getEnderecoForm().getEndereco();
			endereco.salvar();
			
			unidadeView.getUnidadeForm().getUnidade().setEndereco(endereco);
			
			ok = true;
		}
		catch (Exception e) {
			unidadeView.msgErro(e);
			ok = false;
		}
		
		if(ok == true){
			unidade.salvar();
			
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(Unidade unidade){
		
		EnderecoAuditor endereco = new EnderecoAuditor();
		boolean ok;
		
		try{
			endereco = unidadeView.getEnderecoForm().getEndereco();
			endereco.alterar();
			
			unidadeView.getUnidadeForm().getUnidade().setEndereco(endereco);
			
			ok = true;
		}
		catch (Exception e) {
			unidadeView.msgErro(e);
			ok = false;
		}
			
		if(ok == true){
			unidade.alterar();
			
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(Unidade unidade){
		
		if (remover(unidade.getId())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;		
		Unidade unidade = Unidade.pesquisaUnidadeID(id);
		
		result = unidade.remover();
		organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
		refreshTable();
		return result;
	}
	
	public Unidade getUnidade() {
		if(unidade == null){
			unidade = new Unidade();
		}
		
		return unidade;
	}
	
	public Unidade getNovaUnidade(){
		unidade = new Unidade();
		return unidade;
	}

	public UnidadeView getUnidadeView() {
		return unidadeView;
	}

	public void setUnidadeView(UnidadeView unidadeView) {
		this.unidadeView = unidadeView;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public UnidadeView getView(){
		 refreshTable();
		  return this.unidadeView;
	}
	
	public void visualizar(Object event){
		
		unidade = (Unidade) event;
		
		Object[] dados = new Object[14];
		dados[0] = unidade.getCentro();
		dados[1] = unidade.getEmail();
		dados[2] = unidade.getNome();
		//dados[3] = unidade.getUnidade();
		
		dados[4] = unidade.getEndereco().getBairro();
		dados[5] = unidade.getEndereco().getCep();
		dados[6] = unidade.getEndereco().getCidade();
		dados[7] = unidade.getEndereco().getComplemento();
		dados[8] = unidade.getEndereco().getEstado();
		dados[9] = unidade.getEndereco().getLogradouro();
		dados[10] = unidade.getEndereco().getNumero();
		dados[11] = unidade.getEndereco().getObs();
		dados[12] = unidade.getEndereco().getStatus();
		dados[13] = unidade.getEndereco().getTipo();
		
		unidadeView.visualizar(dados);
	}
	
	@SuppressWarnings("rawtypes")
	public BeanItemContainer listCentro(){
		List<CentroCusto> listaCentro = (List<CentroCusto>) this.organizacaoController.getOrganizacao().getCentroCusto();
		BeanItemContainer<CentroCusto>beans = new BeanItemContainer<CentroCusto>(CentroCusto.class);
		
		
		for (Iterator iterator = listaCentro.iterator(); iterator.hasNext();) {
			CentroCusto wo = (CentroCusto) iterator.next();
			beans.addBean(wo);
		}
		return beans;
	}
	
	public void refreshTable(){
		unidadeView.tabelaFiltro.tableMain.removeAllItems();
		defaultTable();		
	}
	
	@SuppressWarnings("rawtypes")
	public void defaultTable(){
		unidadeView.tabelaFiltro.tableMain.addContainerProperty("Id", Label.class, "");
		unidadeView.tabelaFiltro.tableMain.addContainerProperty("Nome", Label.class, "");
		unidadeView.tabelaFiltro.tableMain.addContainerProperty("Email", Label.class, "");	
		unidadeView.tabelaFiltro.tableMain.addContainerProperty("Endereco", Label.class, "");			

		List<CentroCusto> listaCentro = (List<CentroCusto>) this.organizacaoController.getOrganizacao().getCentroCusto();
		List<Unidade> listaUnidade = new ArrayList<Unidade>();
		
		for (Iterator iterator = listaCentro.iterator(); iterator.hasNext();) {
			CentroCusto wo = (CentroCusto) iterator.next();
			listaUnidade.addAll(wo.getUnidade());
		}
		
		for (Iterator iterator = listaUnidade.iterator(); iterator.hasNext();) {
			Unidade wo = (Unidade) iterator.next();
			Label lId = new Label(String.valueOf(wo.getId()));
			lId.setWidth("1px");
			
			Label lNome = new Label(wo.getNome());
			lNome.setWidth("1px");
			
			Label lEmail = new Label(wo.getEmail());
			lEmail.setWidth("1px");

			String estado = wo.getEndereco().getEstado();
			String sigla = estado.substring(0, 2);
			Label lEndereco = new Label(wo.getEndereco().getLogradouro()+" "+ wo.getEndereco().getNumero()+", "+ wo.getEndereco().getCidade()+" / "+sigla);
			lEndereco.setWidth("1px");
			
			Object unidade = unidadeView.tabelaFiltro.tableMain.addItem(new Object[]{lId, lNome, lEmail, lEndereco}, wo);
			
			List<Departamento> listaDepartamento = (List<Departamento>) wo.getDepartamento();
						
			for (int i = 0; i < wo.getDepartamento().size(); i++) {
				Departamento d = listaDepartamento.get(i);
				Label lDepId = new Label(String.valueOf(d.getId()));
				lDepId.setWidth("1px");
				
				Label lDepNome = new Label(d.getNome());
				lDepNome.setWidth("1px");
				
				Label lDepEmail = new Label(d.getEmail());
				lDepEmail.setWidth("1px");					
				
				Object departamento = unidadeView.tabelaFiltro.tableMain.addItem(new Object[]{lDepId+"- Departamento", lDepNome, lDepEmail, ""}, d);
				unidadeView.tabelaFiltro.tableMain.setParent(departamento, unidade);
				unidadeView.tabelaFiltro.tableMain.setChildrenAllowed(d, false);
			}
		}
	}
}