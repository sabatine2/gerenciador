package com.despesas.controller;

import com.despesas.dao.DespesasDAO;
import com.despesas.model.Despesas;
import com.despesas.model.ItensDespesas;
import com.despesas.view.DespesaView;
import com.despesas.view.menu.MenuDespesas;
import com.principal.helper.HibernateUtil;
import com.relatorio.IreportConexao;
import com.vaadin.Application;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.StreamResource;

@SuppressWarnings({})
public class DespesasController {

	private DespesaView      despesaView;
	private Despesas         despesa = null;
	private String statusView = null;
	public MenuDespesas menuDespesas = null;
	
	public DespesasController(){
		despesaView = new DespesaView(this);
		menuDespesas = new MenuDespesas(this);
	    despesaView.defaultTable();    
	    statusView = "tabela";
	}

	/*public void filtro(String nome){
		beans.removeAllContainerFilters();
		beans.addContainerFilter("nome", nome, true, false);
	}*/
	
	public void visualizar(Object event){
		
		statusView = "visualizar";
    	Despesas despesaInfo = (Despesas) event;
    	org.hibernate.Session s = HibernateUtil.openSession();
	    DespesasDAO despesasDAO = new DespesasDAO(s, Despesas.class);
	    despesa = despesasDAO.buscaDespesas(despesaInfo.getId());
		despesaView.visualizar();
	}
	
	public void adicionar(){
		
		statusView = "adicionar";
		despesaView.adicionar();
	}
	
	public boolean salvar(Despesas despesas){	    
		System.out.println("despesas: "+despesas.getItensDespesa().toString());
		if (statusView.equals("adicionar")) {
			if (despesas.salvar()) {
				/*for (int i = 0; i < itens.size(); i++) {
					
					ItensDespesas item = itens.getIdByIndex(i);
					item.salvar();
				}*/
				despesaView.defaultTable();
				return true;
			}
			else {
				return false;
			}
		}
		else {
			despesas.alterar();
			/*for (int i = 0; i < itens.size(); i++) {
				
				ItensDespesas item = itens.getIdByIndex(i);
				item.alterar();
			}*/
			despesaView.defaultTable();
			return true;
		}
    }
	
	public void removerItem(ItensDespesas item){
		System.out.println("itemNomeControl: "+item.getNome().toString());
		item.remover();
	}
	
	public boolean removerButton(Despesas despesas){
		if(remover(despesas.getId())){
			return true;
		}
		else {
			return false;
		}
	}

	public boolean remover(Long id){
		boolean result;		
		Despesas despesas = Despesas.pesquisaDespesaID(id);
	
		result = despesas.remover();
		despesaView.defaultTable();
		return result;
	}
	
	public DespesaView getView(){
		 return this.despesaView;
	}
	
	public StreamResource geraRelatorio(long idAtivo, Application app){
		
		IreportConexao iReportConexao = new IreportConexao();
		
		StreamResource.StreamSource source = iReportConexao.gerarel(idAtivo, "relatorios/relatorio_usuario.jasper");
		StreamResource resource = new StreamResource(source, "relatorio2.pdf", app);
		
		resource.setMIMEType("application/pdf");
        return resource;
	}

	public MenuDespesas getMenuDespesas() {
		return menuDespesas;
	}

	public void setMenuDespesas(MenuDespesas menuDespesas) {
		this.menuDespesas = menuDespesas;
	}

	public Despesas getDespesa() {
		if(despesa == null){
			despesa = new Despesas();
		}
		return despesa;
	}

	public void setDespesa(Despesas despesa) {
		this.despesa = despesa;
	}
}