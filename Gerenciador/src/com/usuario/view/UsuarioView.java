package com.usuario.view;

import net.sf.jasperreports.engine.JRException;

import com.abstracts.view.ViewComponente;
import com.usuario.controller.UsuarioController;
import com.usuario.model.Usuario;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.view.componente.TabelaFiltro;

public class UsuarioView extends ViewComponente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UsuarioController usuarioController;
	private TabelaFiltro tabelaFiltro;
	private BeanItemContainer<Usuario> beanUsuario;
	private Button buttonImprimir;
	
	public UsuarioView(final UsuarioController usuarioController){
		this.usuarioController = usuarioController;
		
	buttonImprimir = new Button("Imprimir",new Button.ClickListener() {
			// inline click-listener
				public void buttonClick(ClickEvent event) {
		           try {
					getWindow().open(usuarioController.geraRelatorio(getApplication()), "_new");
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});
			buttonImprimir.setWidth("-1px");
			buttonImprimir.setHeight("-1px");
			buttonImprimir.setVisible(true);
			right.addComponent(buttonImprimir);
		
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
					usuarioController.visualizar(event.getItemId());
				}
			}
		});
		
		
			addComponent(modotabelaFiltro());
			setComponent(modoLayoutTable);
			defaultTable();
			buttonAdicionar.setVisible(false);
	}

	 public void filtro(TextChangeEvent event){
			beanUsuario.removeAllContainerFilters();
			beanUsuario.addContainerFilter("nome", event.getText(), true, false);
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
	   
		usuarioController.setUsuario((Usuario)usuario);
    
	}
	
	@Override
	public void visualizar(Object[] dados) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(){
		   beanUsuario.removeAllItems();
		   beanUsuario = null;
		   tabelaFiltro.tableMain.setContainerDataSource(beanUsuario);
		   defaultTable();
	   }	
	  
	public void removeClienteLista(Usuario usuario){
		   beanUsuario.removeItem(usuario);
		}
	   
	public void insereClienteLista(Usuario usuario){
		   beanUsuario.addBean(usuario);
	}
	   
	public void defaultTable(){
		    beanUsuario = Usuario.listaBens();
			tabelaFiltro.tableMain.setContainerDataSource(beanUsuario);
			tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id","nome","nivel"});
	}
}
