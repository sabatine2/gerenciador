package com.midiasocial.view;

import com.abstracts.view.ViewComponente;
import com.midiasocial.controller.UsuarioPubMidiaSocialController;
import com.midiasocial.model.UsuarioPubMidiaSocial;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

import com.vaadin.ui.VerticalLayout;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

public class UsuarioPubMidiaSocialView extends ViewComponente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UsuarioPubMidiaSocialController userController;
	public com.view.componente.TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	
	public UsuarioPubMidiaSocialView(UsuarioPubMidiaSocialController userController){
		this.userController = userController;
		buttonAdicionar.setVisible(false);
		
	}

	@Override
	public void modoTabela() {
		
		
		tabelaFiltro = new TabelaFiltro("Usuário");
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
					if (event.getItemId() instanceof UsuarioPubMidiaSocial) {
						userController.visualizar(event.getItemId());
					}
				}
			}
		});
		
		addComponent(modotabelaFiltro());
		setComponent(modoLayoutTable);
		isRemover = true;
		defaultTable();
	}
	

	@Override
	public VerticalLayout modotabelaFiltro() {
		modoLayoutTable = new VerticalLayout();
		modoLayoutTable.setHeight("500px");
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

	}

	@Override
	public void adicionar() {
		
	}

	@Override
	public void remover(Object target) {
		
	}

	@Override
	public void voltar() {
		
	}

	@Override
	public void salvar() {
		
	}

	@Override
	public void visualizar(Object[] dados) {
		getWindow().addWindow(new PerfilInfoView(userController.getUsuario().getIdMidia()));
	}
	
	public void msgErro(String e){
		MessageBox mb = new MessageBox(getWindow(), 
				"Erro", 
                MessageBox.Icon.ERROR, 
                e,  
                new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
      	mb.show(); 
	}
	
	public void refreshTable(){
		defaultTable();		
	}
	
	@SuppressWarnings("unchecked")
	public void defaultTable(){
		tabelaFiltro.tableMain.setContainerDataSource(new BeanItemContainer<UsuarioPubMidiaSocial>(UsuarioPubMidiaSocial.class,UsuarioPubMidiaSocial.listaUsuario()));
		tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"idInterno", "nome", "screenName"});
		
	}
	
}