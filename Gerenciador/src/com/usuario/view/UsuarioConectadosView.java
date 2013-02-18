package com.usuario.view;

import java.util.Date;
import java.util.Iterator;

import com.abstracts.view.ViewComponente;
import com.login.model.Login;
import com.usuario.controller.UsuarioController;
import com.usuario.model.Usuario;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.PainelInfo;
import com.view.componente.TabelaFiltro;

public class UsuarioConectadosView extends ViewComponente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UsuarioController usuarioController;
	private TabelaFiltro tabelaFiltro;
	private BeanItemContainer<Login> beanLogin;
	
	public UsuarioConectadosView(UsuarioController usuarioController){
		this.usuarioController = usuarioController;
		
	}

	@Override
	public void modoTabela() {
		// TODO Auto-generated method stub
		tabelaFiltro = new TabelaFiltro("Usuarios");
		tabelaFiltro.filterField.addListener(new TextChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void textChange(TextChangeEvent event) {
				//filtro(event);  
			}
		});
		
		tabelaFiltro.tableMain.addListener(new ItemClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * ITEM CLICK EVENTO
			 */
			public void itemClick(ItemClickEvent event) {
				
				if (event.isDoubleClick()) {
					Login login = (Login)event.getItemId();
					usuarioController.visualizar(login.getUsuario());
					visualizar(login.getUsuario());
				}
			}
		});
		
		
			addComponent(modotabelaFiltro());
			setComponent(modoLayoutTable);
			defaultTable();
			buttonAdicionar.setVisible(false);
	}
	

	@Override
	public VerticalLayout modotabelaFiltro() {
			modoLayoutTable = new VerticalLayout();
			modoLayoutTable.setMargin(true);
		
			modoLayoutTable.addComponent(tabelaFiltro);
			return modoLayoutTable;
	}

	@Override
	public VerticalLayout modovisualizarView() {
	   return null;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		return null;
	}

	@Override
	public void editar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionar() {
	
	}

	@Override
	public void remover(Object target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void voltar() {
	}

	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
	}

	public void visualizar(Object usuario) {
		Usuario u = (Usuario) usuario;
	}
	
	@Override
	public void visualizar(Object[] dados) {
		// TODO Auto-generated method stub
		
	}
	   
	public void defaultTable(){
		tabelaFiltro.tableMain.addContainerProperty("Usuario" ,       String.class,  null);
		tabelaFiltro.tableMain.addContainerProperty("Logado",         Date.class,  null);
		tabelaFiltro.tableMain.addContainerProperty("Sistemas Operacional", String.class, null);
		tabelaFiltro.tableMain.addContainerProperty("Navegador",      String.class, null);
		tabelaFiltro.tableMain.addContainerProperty("Ativo",          CheckBox.class, null);
		
		for(Iterator<Login> i = Login.buscaAtivo().iterator();i.hasNext();){
			Login login = i.next();
			
			tabelaFiltro.tableMain.addItem(new Object[] {login.getUsuario().getNome(),
                    login.getDataCreate(),
                    login.getSistemasOperacional(),
                    login.getNavegador(),
                    login.getAtivo()},
					login);
		}  
	}
	
}
