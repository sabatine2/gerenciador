package com.auditor.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.auditor.model.CentroCusto;
import com.auditor.model.Conta;
import com.auditor.model.Departamento;
import com.auditor.model.ItemConta;
import com.auditor.model.ReferenciaServico;
import com.auditor.model.Unidade;
import com.auditor.model.contato.PrestadoraTelefonia;
import com.auditor.model.contato.Telefone;
import com.auditor.view.TelefoneView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Label;

@SuppressWarnings({"unchecked", "rawtypes"})
public class TelefoneController {

	public Telefone telefone = null;
	private TelefoneView telefoneView = null;
	public Conta conta = null;
	public OrganizacaoController organizacaoController;
	public BeanItemContainer<PrestadoraTelefonia>beansPrestadora = PrestadoraTelefonia.ListaBens();
	public BeanItemContainer<ReferenciaServico>beansReferencia = ReferenciaServico.ListaBens();
	public BeanItemContainer<Departamento>beansDepartamento = Departamento.listaBens();
	
	public TelefoneController(OrganizacaoController organizacaoController){
		this.organizacaoController = organizacaoController;
		telefoneView = new TelefoneView(this);
		telefoneView.modoTabela();
		refreshTable();
	}
	
	public boolean salvar(Telefone telefone){
		
		if(telefone.salvar()){
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			refreshTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean alterar(Telefone telefone){
		
		if(telefone.alterar()){
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			refreshTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean removerButton(Telefone telefone){
		
		if(remover(telefone.getDddNumero())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		Telefone telefone = Telefone.pesquisaTelefoneID(id);
		
		result = telefone.remover();
		organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
		refreshTable();
		return result;
	}
	
	public boolean removerContaButton(Conta conta){
		
		if(removerConta(conta.getId())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerConta(Long id){
		boolean result;
		Conta conta = Conta.pesquisaContaID(id);
		
		result = conta.remover();
		return result;
	}
	
	public Telefone getTelefone() {
		if(telefone == null){
			telefone = new Telefone();
		}
		return telefone;
	}
	
	public void deletarAll(){
		
		List itemList = conta.listItemConta();
		
		for (int i = 0; i < itemList.size(); i++) {
			
			ItemConta item = (ItemConta) itemList.get(i);
			item.remover();
		}
	}
	
	public Telefone getNovoTelefone(){
		telefone = new Telefone();
		return telefone;
	}

	public TelefoneView getTelefoneView() {
		return telefoneView;
	}

	public void setTelefoneView(TelefoneView telefoneView) {
		this.telefoneView = telefoneView;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public TelefoneView getView(){
		refreshTable();
		return this.telefoneView;
	}
	
	public BeanItemContainer listDepartamento(){
		List<CentroCusto> listaCentro = (List<CentroCusto>) this.organizacaoController.getOrganizacao().getCentroCusto();
		BeanItemContainer<Departamento>beans = new BeanItemContainer<Departamento>(Departamento.class);
		
		for (Iterator iterator = listaCentro.iterator(); iterator.hasNext();) {
			CentroCusto wo = (CentroCusto) iterator.next();
			List<Unidade> listaUnidade = (List<Unidade>) wo.getUnidade();
			
			for (Iterator iterator2 = listaUnidade.iterator(); iterator2.hasNext();) {
				Unidade unidade = (Unidade) iterator2.next();
				List<Departamento> listaDepartamento = (List<Departamento>) unidade.getDepartamento();
				
				for (Iterator iterator3 = listaDepartamento.iterator(); iterator3.hasNext();) {
					Departamento departamento = (Departamento) iterator3.next();
					beans.addBean(departamento);
				}
			}
		}
		return beans;
	}
	
	public void visualizar(Object event){
		
		telefone = (Telefone) event;
		//String a = telefone.getPrestadora().getNome();
		System.out.println("RESULTADO: "+telefone.getPrestadora());
		
		Object[] dados = new Object[15];
		dados[0] = telefone.getDddNumero();
		dados[1] = telefone.getRamal();
		dados[2] = telefone.getFinalidade();
		dados[3] = telefone.getObs();
		dados[4] = telefone.getPrestadora();
		dados[5] = telefone.getTelefoneReferencia();
		dados[6] = telefone.getTipo();
		dados[7] = telefone.getAtivacao();
				
		telefoneView.visualizar(dados);
	}
	
	public void visualizarConta(Object event){
		
		conta = (Conta) event;
		
		telefoneView.contaView(conta);
	}
	
	public void refreshTable(){
		telefoneView.tabelaFiltro.tableMain.removeAllItems();
		defaultTable();
	}
	
	public void defaultTable(){
		telefoneView.tabelaFiltro.tableMain.addContainerProperty("dddNumero", Label.class, "");
		telefoneView.tabelaFiltro.tableMain.addContainerProperty("tipoDispositivo", Label.class, "");
		telefoneView.tabelaFiltro.tableMain.addContainerProperty("tipo", Label.class, "");
		telefoneView.tabelaFiltro.tableMain.addContainerProperty("finalidade", Label.class, "");	
		telefoneView.tabelaFiltro.tableMain.addContainerProperty("obs", Label.class, "");			

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
				List<Telefone> listaTel = (List<Telefone>) d.getTelefone();
				//beansDepartamento.addBean(d);
				
				for (int j = 0; j < d.getTelefone().size(); j++) {
					Telefone tel = listaTel.get(j);
					
					Label telId = new Label(String.valueOf(tel.getDddNumero()));
					telId.setWidth("1px");

					Label telDisp = new Label(tel.getTipoDispositivo());
					telDisp.setWidth("1px");
					
					Label telTipo = new Label(tel.getTipo());
					telTipo.setWidth("1px");
				
					Label telFinalidade = new Label(tel.getFinalidade());
					telFinalidade.setWidth("1px");	
					
					Label telObs = new Label(tel.getObs());
					telObs.setWidth("1px");
					
					telefoneView.tabelaFiltro.tableMain.addItem(new Object[]{telId, telDisp, telTipo, telFinalidade, telObs}, tel);
				}
			}
		}
	}
}