package com.auditor.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.auditor.model.CentroCusto;
import com.auditor.model.Departamento;
import com.auditor.model.Unidade;
import com.auditor.view.DepartamentoView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Label;

public class DepartamentoController  {

	private Departamento departamento = null;
	private DepartamentoView departamentoView = null;
	public OrganizacaoController organizacaoController;
	
	public DepartamentoController(OrganizacaoController organizacaoController){
		this.organizacaoController = organizacaoController;
	    departamentoView = new DepartamentoView(this);
	    departamentoView.modoTabela();
	    refreshTable();
	}
	
	public boolean salvar(Departamento departamento){
		
		if(departamento.salvar()){
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(Departamento departamento){
		
		if(departamento.alterar()){
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(Departamento departamento){
			if(remover(departamento.getId())){
				return true;
			}
			else {
				return false;
			}
	}
	
	public boolean remover(Long id){
		boolean result;		
		Departamento departamento = Departamento.pesquisaDepartamentoID(id);
		
		result = departamento.remover();
		organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
		refreshTable();
		return result;
	}
	
	public Departamento getDepartamento() {
		if(departamento == null){
			departamento = new Departamento();
		}	
		return departamento;
	}
	
	public Departamento getNovoDepartamento(){
		departamento = new Departamento();
		return departamento;
	}

	public DepartamentoView getDepartamentoView() {
		return departamentoView;
	}

	public void setDepartamentoView(DepartamentoView departamentoView) {
		this.departamentoView = departamentoView;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public DepartamentoView getView(){
		refreshTable();
		return this.departamentoView;
	}
	
	public void visualizar(Object event){
		
		departamento = (Departamento) event;
		
		Object[] dados = new Object[15];
		dados[0] = departamento.getDescricaoDepartamento();
		dados[1] = departamento.getEmail();
		dados[2] = departamento.getEmailTemplate();
		dados[3] = departamento.getNome();
		dados[4] = departamento.getStatus();
		dados[5] = departamento.getUnidade();
		/*
		dados[6] = departamento.getTelefone().getDddNumero();
		dados[7] = departamento.getTelefone().getDepartamento();
		dados[8] = departamento.getTelefone().getFinalidade();
		dados[9] = departamento.getTelefone().getObs();
		dados[10] = departamento.getTelefone().getPrestadora();
		dados[11] = departamento.getTelefone().getTelefone();
		dados[12] = departamento.getTelefone().getTelefoneReferencia();
		dados[13] = departamento.getTelefone().getTipo();
		dados[14] = departamento.getTelefone().getAtivacao();*/
				
		departamentoView.visualizar(dados);
	}
	
	@SuppressWarnings("rawtypes")
	public BeanItemContainer listUnidade(){
		List<CentroCusto> listaCentro = (List<CentroCusto>) this.organizacaoController.getOrganizacao().getCentroCusto();
		BeanItemContainer<Unidade>beans = new BeanItemContainer<Unidade>(Unidade.class);
		
		
		for (Iterator iterator = listaCentro.iterator(); iterator.hasNext();) {
			CentroCusto wo = (CentroCusto) iterator.next();
			List<Unidade> listaUnidade = (List<Unidade>) wo.getUnidade();
			
			for (Iterator iterator2 = listaUnidade.iterator(); iterator2.hasNext();) {
				Unidade unidade = (Unidade) iterator2.next();
				beans.addBean(unidade);
			}
		}
		refreshTable();
		return beans;
	}
	
	public void refreshTable(){
		defaultTable();		
	}
	
	@SuppressWarnings("rawtypes")
	public void defaultTable(){		
		departamentoView.tabelaFiltro.tableMain.removeAllItems();
		departamentoView.tabelaFiltro.tableMain.addContainerProperty("Id", Label.class, "");
		departamentoView.tabelaFiltro.tableMain.addContainerProperty("Nome", Label.class, "");
		departamentoView.tabelaFiltro.tableMain.addContainerProperty("Email", Label.class, "");	
		departamentoView.tabelaFiltro.tableMain.addContainerProperty("Endereco", Label.class, "");			

		List<CentroCusto> listaCentro = (List<CentroCusto>) this.organizacaoController.getOrganizacao().getCentroCusto();
		List<Unidade> listaUnidade = new ArrayList<Unidade>();
		
		for (Iterator iterator = listaCentro.iterator(); iterator.hasNext();) {
			CentroCusto wo = (CentroCusto) iterator.next();
			listaUnidade.addAll(wo.getUnidade());
		}
		
		for (Iterator iterator = listaUnidade.iterator(); iterator.hasNext();) {
			Unidade wo = (Unidade) iterator.next();
		
			List<Departamento> listaDepartamento = (List<Departamento>) wo.getDepartamento();
						
			for (int i = 0; i < wo.getDepartamento().size(); i++) {
				Departamento d = listaDepartamento.get(i);
				Label lDepId = new Label(String.valueOf(d.getId()));
				lDepId.setWidth("1px");
				
				Label lDepNome = new Label(d.getNome());
				lDepNome.setWidth("1px");
				
				Label lDepEmail = new Label(d.getEmail());
				lDepEmail.setWidth("1px");					
				
				departamentoView.tabelaFiltro.tableMain.addItem(new Object[]{lDepId, lDepNome, lDepEmail, ""}, d);
			}
		}
	}
}