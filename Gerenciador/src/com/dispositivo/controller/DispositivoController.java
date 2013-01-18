package com.dispositivo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.auditor.controller.OrganizacaoController;
import com.auditor.model.CentroCusto;
import com.auditor.model.Departamento;
import com.auditor.model.Unidade;
import com.auditor.model.contato.Telefone;
import com.dispositivo.model.Dispositivo;
import com.dispositivo.view.DispositivoView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Label;

@SuppressWarnings("rawtypes")
public class DispositivoController {
	
	private Dispositivo dispositivo = null;
	private DispositivoView dispositivoView = null;
	public OrganizacaoController organizacaoController;
	
	public DispositivoController(OrganizacaoController organizacaoController){
		this.organizacaoController = organizacaoController;
	    dispositivoView = new DispositivoView(this);
	     dispositivoView.modoTabela();
	     refreshTable();
	}
	
	public boolean salvar(Dispositivo dispositivo){
		
		
			
		if(dispositivo.salvar()){
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(Dispositivo dispositivo){
		
		if(dispositivo.alterar()){
			organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
			refreshTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(Dispositivo dispositivo){
			if(remover(dispositivo.getId())){
				return true;
			}
			else {
				return false;
			}
	}
	
	public boolean remover(Long id){
		
		for (Iterator iterator = dispositivo.getNumeroTelefone().iterator(); iterator.hasNext();) {
			Telefone tel = ( Telefone ) iterator.next();
			tel.setDispositivo(null);
			tel.alterar();
		}
		
		boolean result;		
		Dispositivo dispositivo = Dispositivo.pesquisaDispositivoID(id);
		
		result = dispositivo.remover();
		organizacaoController.setOrganizacao(organizacaoController.getOrganizacao().pesquisaOrganizacaoID());
		refreshTable();
		return result;
	}
	
	public Dispositivo getDispositivo() {
		if(dispositivo == null){
			dispositivo = new Dispositivo();
		}
		return dispositivo;
	}
	
	public Dispositivo getNovoDispositivo() {
		dispositivo = new Dispositivo();
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public DispositivoView getDispositivoView() {
		return dispositivoView;
	}

	public void setDispositivoView(DispositivoView dispositivoView) {
		this.dispositivoView = dispositivoView;
	}

	public DispositivoView getView(){
		 refreshTable();
		 return this.dispositivoView;	
	}
	
	public void visualizar(Object event){
		
		dispositivo = ( Dispositivo ) event;
		
		Object[] dados = new Object[25];
		dados[0] = dispositivo.getMarca();
		dados[1] = dispositivo.getStatus();
		dados[2] = dispositivo.getSistemaOperacional();
		
		dispositivoView.visualizar(dados);
	}
	
	public Telefone insereTelefone(Telefone telefone){
		
		telefone.setDispositivo(dispositivo);
		dispositivo.getNumeroTelefone().add(telefone);
		
		return telefone;
	}
	
	public void desvincularTelefone(Telefone telefone){
		
		telefone.setDispositivo(null);
		telefone.alterar();
		dispositivo.getNumeroTelefone().remove(telefone);
	}
	
	public void refreshTable(){
		dispositivoView.tabelaFiltro.tableMain.removeAllItems();
		defaultTable();		
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
	
	public void defaultTable(){
		dispositivoView.tabelaFiltro.tableMain.addContainerProperty("id", Label.class, "");
		dispositivoView.tabelaFiltro.tableMain.addContainerProperty("dataAquisicao", Label.class, "");
		dispositivoView.tabelaFiltro.tableMain.addContainerProperty("marca", Label.class, "");	
		dispositivoView.tabelaFiltro.tableMain.addContainerProperty("status", Label.class, "");			

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
				List<Dispositivo> listaDisp = ( List<Dispositivo> ) d.getDispositivo();
				//beansDepartamento.addBean(d);
				
				for (int j = 0; j < d.getDispositivo().size(); j++) {
					Dispositivo disp = listaDisp.get(j);
					
					Label dispId = new Label(String.valueOf(disp.getId()));
					dispId.setWidth("1px");
				
					Label dispData = new Label(String.valueOf(disp.getDataAquisicao()));
					dispData.setWidth("1px");
				
					Label dispMarca = new Label(disp.getMarca());
					dispMarca.setWidth("1px");	
					
					Label dispStatus = new Label(disp.getStatus());
					dispStatus.setWidth("1px");
					
					dispositivoView.tabelaFiltro.tableMain.addItem(new Object[]{dispId, dispData, dispMarca, dispStatus}, disp);
				}
			}
		}
	}
}
