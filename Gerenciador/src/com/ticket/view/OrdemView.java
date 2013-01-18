package com.ticket.view;

import java.util.List;

import com.abstracts.view.ViewComponente;
import com.ticket.controller.OrdemController;
import com.ticket.model.Mensagem;
import com.ticket.model.Nota;
import com.ticket.model.Ordem;
import com.ticket.view.form.OrdemForm;
import com.ticket.view.panel.PanelOperacao;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.view.componente.JanelaConfirmacao;
import com.view.componente.TabelaFiltro;

public class OrdemView extends ViewComponente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TabelaFiltro     tabelaFiltro;
	private OrdemForm        ordemForm = null;
	private OrdemController  ordemController;
	private BeanItemContainer<Ordem> beanOrdem;
	

	public OrdemView(OrdemController ordemController){
		super();
	    this.ordemController = ordemController;
	    modoTabela();
	}
	
	@Override
    public void modoTabela(){
		tabelaFiltro = new TabelaFiltro("Ordem");
		tabelaFiltro.filterField.addListener(new TextChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub
				filtro(event);  
			}
		});
		
		tabelaFiltro.tableMain.addListener(new ItemClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void itemClick(ItemClickEvent event) {
				
				if (event.isDoubleClick()) {	
					ordemController.visualizar(event.getItemId());
				}
			}
		});
		
		tabelaFiltro.tableMain.addActionHandler(new Action.Handler() {

 			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void handleAction(Action action, Object sender, final Object target) {
 				if (action == REMOVE_ITEM_ACTION) {
 					
 					final JanelaConfirmacao janelaConfirmacao = new JanelaConfirmacao("Remover", "Deseja remover?");
 					getWindow().addWindow(janelaConfirmacao);
 				
 					janelaConfirmacao.getButtonSim().addListener(new Button.ClickListener() {
 	                /**
						 * 
						 */
						private static final long serialVersionUID = 1L;

					// inline click-listener
 	                public void buttonClick(ClickEvent event) {
 	                  	 tabelaFiltro.tableMain.removeItem(target);
 	                  	 remover(target);
 	                  	 janelaConfirmacao.getParent().removeWindow(janelaConfirmacao);
 	                	
 	                }});
 				 
 					janelaConfirmacao.getButtonNao().addListener(new Button.ClickListener() {
 					/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

					public void buttonClick(ClickEvent event) {
 		                // close the window by removing it from the parent window
 		                janelaConfirmacao.getParent().removeWindow(janelaConfirmacao);
 		            }});
 				 
 				} 
            }

            public Action[] getActions(Object target, Object sender) {
                         // Context menu for an item
                    return new Action[] { REMOVE_ITEM_ACTION };
             }
        });
					
		addComponent(tabelaFiltro());
		setComponent(modoLayoutTable);
		defaultTable();
    	
    }
	
	 public VerticalLayout tabelaFiltro(){
		    
	    	modoLayoutTable = new VerticalLayout();
			modoLayoutTable.setHeight("500px");
			modoLayoutTable.setMargin(true);
		
			modoLayoutTable.addComponent(tabelaFiltro);
			return modoLayoutTable;
	 }
	public void filtro(TextChangeEvent event){
		//this.pedidoController.filtro(event.getText());  
	}	

	@Override
	public VerticalLayout modotabelaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerticalLayout modovisualizarView() {
		if(modoLayoutView == null){ modoLayoutView = new VerticalLayout();}
		modoLayoutView.setMargin(true);
		modoLayoutView.setSpacing(true);
		modoLayoutView.removeAllComponents();
		ordemForm = new OrdemForm(ordemController.getOrdem(),"visualizar");
		modoLayoutView.addComponent(ordemForm);
		modoLayoutView.addComponent(getTicketEventosView());
		modoLayoutView.addComponent(new PanelOperacao(ordemController));
		return modoLayoutView;
	}
	
	public VerticalLayout modoEditarView() {
		if(modoLayoutView == null){ modoLayoutView = new VerticalLayout();}
		modoLayoutView.setMargin(true);
		modoLayoutView.setSpacing(true);
		modoLayoutView.removeAllComponents();
		ordemForm = new OrdemForm(ordemController.getOrdem(),"editar");
		modoLayoutView.addComponent(ordemForm);
		return modoLayoutView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		if(modoLayoutAdd == null){ modoLayoutAdd = new VerticalLayout();}
		modoLayoutAdd.setMargin(true);
		modoLayoutAdd.setSpacing(true);
		modoLayoutAdd.removeAllComponents();
		ordemForm = new OrdemForm(ordemController.getOrdem(),"inserir");
		modoLayoutAdd.addComponent(ordemForm);
		return modoLayoutAdd;
	}
	
	
	@Override
	public void editar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void adicionar() {
		if(getComponent() != null){
	    removeComponent(getComponent());
	    }
      
		//  pedidoController.modoAdicionar();
		addComponent(modoadicionarView());
	    setComponent(modoLayoutAdd);
	    
	    buttonVoltar.setVisible(true);
	    buttonEditar.setVisible(false);
	    buttonSalvar.setVisible(true);
	    buttonClonar.setVisible(false);
	    buttonAdicionar.setVisible(false);
	
	}

	@Override
	public void remover(Object target) {
		//pedidoController.remover((Cliente)target);
	}

	@Override
	public void voltar() {
		removeComponent(getComponent());
    	addComponent(modoLayoutTable);
    	setComponent(modoLayoutTable);
    	
    	ordemController.setOrdem(null);
    	//BOTOES//
    	buttonVoltar.setVisible(false);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(false);
    	buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(true);
    	buttonDeletar.setVisible(false);
   }

	@Override
	public void salvar() {
		try{
		   ordemController.setOrdem(ordemForm.getOrdem());
		   if(ordemController.salvar()){
			   //Mensagem.msgConf(this,"Nova ordem Gerada", "Sucesso");
		   }
		   else{
			 //  Mensagem.msgConf(this,"Erro na ordem de servi�o", "Erro");
		   }   
		}catch (Exception e) {
			//Mensagem.msgErro(this, e.getMessage(), e.getMessage());
		}
	}

	public void visualizar() {
		if(getComponent() != null){
	    	removeComponent(getComponent());
	    	getComponent().detach();
	    	}
	    
		addComponent(modovisualizarView());
	    	
	    	
	    setComponent(modoLayoutView);
	    
	    buttonVoltar.setVisible(true);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(true);
    	buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(false);
    	
	}
	
	@Override
	public void visualizar(Object[] cliente) {}
	
	public void update(){
		   beanOrdem.removeAllItems();
		   beanOrdem = null;
		   tabelaFiltro.tableMain.setContainerDataSource(beanOrdem);
		   defaultTable();
	}	
		
	public void removeOrdemLista(Ordem funcionario){
		   beanOrdem.removeItem(funcionario);
	}
	   
	public void insereOrdemLista(Ordem funcionario){
		   beanOrdem.addBean(funcionario);
	}
	   
	public void defaultTable(){
			beanOrdem = ordemController.listOrdem();
			tabelaFiltro.tableMain.setContainerDataSource(beanOrdem);
	}
	
	public void novoEvento(){
		visualizar();
	}
	
	public TicketEventosView getTicketEventosView(){
		TicketEventosView ticket = new TicketEventosView();
	 	ticket.setListNotas((List<Nota>) ordemController.getOrdem().getNota());
      	ticket.panelNota();
      	ticket.setListMensagem((List<Mensagem>) ordemController.getOrdem().getMensagem());
      	ticket.panelMensagem();
      	return ticket;
	}
}
