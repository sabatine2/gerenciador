package com.auditor.controller;

import java.util.List;

import java.util.Iterator;

import com.auditor.dao.OrganizacaoDAO;
import com.auditor.model.CentroCusto;
import com.auditor.model.Organizacao;
import com.auditor.model.contato.EnderecoAuditor;
import com.auditor.view.OrganizacaoView;
import com.auditor.view.menu.MenuAuditor;
import com.vaadin.ui.Label;

public class OrganizacaoController  {

	private Organizacao organizacao = null;
	private OrganizacaoView organizacaoView = null;
	public MenuAuditor menuAuditor = null;
	
	public OrganizacaoController(){
	     organizacaoView = new OrganizacaoView(this);
	     menuAuditor = new MenuAuditor(this);
	     organizacaoView.modoTabela();
	     refreshTable();
	}
	
	public boolean salvar(Organizacao organizacao){
		
		EnderecoAuditor endereco = new EnderecoAuditor();
		boolean ok;
		
		try{
			endereco = organizacaoView.getEnderecoForm().getEndereco();
			endereco.salvar();
			
			organizacaoView.getOrganizacaoForm().getOrganizacao().setEndereco(endereco);
			
			ok = true;
		}
		catch (Exception e) {
			organizacaoView.msgErro(e);
			ok = false;
		}
			
		if(ok == true){
			organizacao.salvar();
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(Organizacao organizacao){
		
		EnderecoAuditor endereco = new EnderecoAuditor();
		boolean ok;
		
		try{
			endereco = organizacaoView.getEnderecoForm().getEndereco();
			endereco.alterar();
			
			organizacaoView.getOrganizacaoForm().getOrganizacao().setEndereco(endereco);
			
			ok = true;
		}
		catch (Exception e) {
			organizacaoView.msgErro(e);
			ok = false;
		}
			
		if(ok == true){
			organizacao.alterar();
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(Organizacao organizacao){
		
		if (remover(organizacao.getId())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		
		org.hibernate.Session s = com.principal.helper.HibernateUtil.openSession();
		OrganizacaoDAO organizacaoDAO = new OrganizacaoDAO(s, Organizacao.class);
		Organizacao organizacao = organizacaoDAO.buscaOrganizacao(id);
		s.close();
		
		result = organizacao.remover();
		refreshTable();
		return result;
	}
	
	public Organizacao getOrganizacao() {
		if(organizacao == null){
			organizacao = new Organizacao();
		}
		return organizacao;
	}
	
	public Organizacao getNovaOrganizacao() {
		organizacao = new Organizacao();
		return organizacao;
	}

	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}

	public OrganizacaoView getOrganizacaoView() {
		return organizacaoView;
	}

	public void setOrganizacaoView(OrganizacaoView organizacaoView) {
		this.organizacaoView = organizacaoView;
	}
	
	public OrganizacaoView getView(){
		return this.organizacaoView;
	}	
	
	public MenuAuditor getMenuAuditor() {
		return menuAuditor;
	}

	public void setMenuAuditor(MenuAuditor menuAuditor) {
		this.menuAuditor = menuAuditor;
	}

	public void visualizar(Object event){
		
		organizacao = (Organizacao) event;
		
		Object[] dados = new Object[29];
		dados[0] = organizacao.getCnaeFiscal();
		dados[1] = organizacao.getCnpj();
		dados[2] = organizacao.getEmail();
		dados[3] = organizacao.getInscricaoEstadual();
		dados[4] = organizacao.getInscricaoEstadualSubstTributario();
		dados[5] = organizacao.getInscricaoMunicipal();
		dados[6] = organizacao.getLogo();
		dados[7] = organizacao.getNomeFantasia();
		dados[8] = organizacao.getRazaoSocial();
		dados[9] = organizacao.getRegimeTributario();
		
		dados[10] = organizacao.getEndereco().getBairro();
		dados[11] = organizacao.getEndereco().getCep();
		dados[12] = organizacao.getEndereco().getCidade();
		dados[13] = organizacao.getEndereco().getComplemento();
		dados[14] = organizacao.getEndereco().getEstado();
		dados[15] = organizacao.getEndereco().getLogradouro();
		dados[16] = organizacao.getEndereco().getNumero();
		dados[17] = organizacao.getEndereco().getObs();
		dados[18] = organizacao.getEndereco().getStatus();
		dados[19] = organizacao.getEndereco().getTipo();
		
		organizacaoView.visualizar(dados);
	}
	
	public void refreshTable(){
		organizacaoView.tabelaFiltro.tableMain.removeAllItems();
		defaultTable();		
	}
	
	@SuppressWarnings("rawtypes")
	public void defaultTable(){
		organizacaoView.tabelaFiltro.tableMain.addContainerProperty("Id", Label.class, "");
		organizacaoView.tabelaFiltro.tableMain.addContainerProperty("Nome", Label.class, "");
		organizacaoView.tabelaFiltro.tableMain.addContainerProperty("Email", Label.class, "");		
		organizacaoView.tabelaFiltro.tableMain.addContainerProperty("Endereco", Label.class, "");	
		
		List<Organizacao> lista = Organizacao.ListaOrganizacao();
		
		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			Organizacao wo = (Organizacao) iterator.next();
			Label lId = new Label(String.valueOf(wo.getId()));
			lId.setWidth("1px");
			
			String estado = wo.getEndereco().getEstado();
			String sigla = estado.substring(0, 2);
			Label lEndereco = new Label(wo.getEndereco().getLogradouro()+" "+ wo.getEndereco().getNumero()+", "+ wo.getEndereco().getCidade()+" / "+sigla);
			lEndereco.setWidth("1px");
			
			Label lEmail = new Label(wo.getEmail());
			lEmail.setWidth("1px");
			
			Label lNome = new Label(wo.getNomeFantasia());
			lNome.setWidth("1px");
			
			Object org = organizacaoView.tabelaFiltro.tableMain.addItem(new Object[]{lId, lNome, lEmail, lEndereco}, wo);
			
			List<CentroCusto> listaCentro = (List<CentroCusto>) wo.getCentroCusto();
						
			for (int i = 0; i < wo.getCentroCusto().size(); i++) {
				CentroCusto c = listaCentro.get(i);
				Label lCentroId = new Label(String.valueOf(c.getId()));
				//lCentroId.setWidth("30px");
				
				Label lCentroNome = new Label(c.getNome());
				lCentroNome.setWidth("1px");
				
				Label lCentroEmail = new Label(c.getEmail());
				lCentroEmail.setWidth("1px");
				
				Label lCentroEndereco = new Label(wo.getEndereco().getLogradouro()+" "+ wo.getEndereco().getNumero()+", "+ wo.getEndereco().getCidade()+" / "+sigla);
				lCentroEndereco.setWidth("1px");
				
				Object centro = organizacaoView.tabelaFiltro.tableMain.addItem(new Object[]{lCentroId+"- Centro Custo", lCentroNome, lCentroEmail, lCentroEndereco}, c);
				organizacaoView.tabelaFiltro.tableMain.setParent(centro, org);
				organizacaoView.tabelaFiltro.tableMain.setChildrenAllowed(c, false);
			}
		}
	}
}