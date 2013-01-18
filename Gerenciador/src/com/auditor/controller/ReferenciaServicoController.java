package com.auditor.controller;

import com.auditor.dao.ReferenciaServicoDAO;
import com.auditor.model.ReferenciaServico;
import com.auditor.view.ReferenciaServicoView;
import com.principal.helper.HibernateUtil;

public class ReferenciaServicoController  {

	private ReferenciaServico referenciaServico = null;
	private ReferenciaServicoView referenciaServicoView = null;
	
	public ReferenciaServicoController(){
	     referenciaServicoView = new ReferenciaServicoView(this);
	     referenciaServicoView.modoTabela();
	     defaultTable();
	}
	
	public boolean salvar(ReferenciaServico referenciaServico){
		
		if(referenciaServico.salvar()){
			defaultTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(ReferenciaServico referenciaServico){
		
		if(referenciaServico.alterar()){
			defaultTable();
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(ReferenciaServico referenciaServico){
		
		if(remover(referenciaServico.getId())){
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ReferenciaServicoDAO referenciaDAO = new ReferenciaServicoDAO(s, ReferenciaServico.class);
		ReferenciaServico referencia = referenciaDAO.buscaReferenciaServico(id);
		s.close();
		
		result = referencia.remover();
		defaultTable();
		return result;
	}
	
	public ReferenciaServico getReferencia() {
		if(referenciaServico == null){
			referenciaServico = new ReferenciaServico();
		}
		return referenciaServico;
	}
	
	public ReferenciaServico getNovaReferencia(){
		referenciaServico = new ReferenciaServico();
		return referenciaServico;
	}
	
	public void setNullReferencia(){
		referenciaServico = null;
	}

	public void setReferenciaServico(ReferenciaServico referenciaServico) {
		this.referenciaServico = referenciaServico;
	}

	public ReferenciaServicoView getReferenciaServicoView() {
		return referenciaServicoView;
	}

	public void setReferenciaServicoView(ReferenciaServicoView referenciaServicoView) {
		this.referenciaServicoView = referenciaServicoView;
	}

	public ReferenciaServicoView getView(){
		  return this.referenciaServicoView;
	}
	
	public void visualizar(Object event){
		
		referenciaServico = (ReferenciaServico) event;
		
		Object[] dados = new Object[2];
		dados[0] = referenciaServico.getTipo();
		dados[1] = referenciaServico.getObs();
		
		referenciaServicoView.visualizar(dados);
	}
	
	public void defaultTable(){
		referenciaServicoView.tabelaFiltro.tableMain.setContainerDataSource(ReferenciaServico.ListaBens());
		referenciaServicoView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id", "tipo", "obs"});
	}
}