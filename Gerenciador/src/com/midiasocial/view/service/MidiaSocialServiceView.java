package com.midiasocial.view.service;

import com.abstracts.view.ViewComponente;
import com.midiasocial.controller.ServicoController;
import com.midiasocial.model.ServicoAtualizacao;
import com.midiasocial.model.Servico;
import com.midiasocial.view.form.ServicoForm;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

public class MidiaSocialServiceView extends ViewComponente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServicoController serviceController;
	public TabelaFiltro tabelaFiltro;
	public boolean iser;
	private ServicoForm servicoForm;
	
	public MidiaSocialServiceView(ServicoController serviceController){
		this.serviceController = serviceController;
	}

	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Servico");
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
					if (event.getItemId() instanceof Servico) {
						serviceController.visualizar(event.getItemId());
					}
				}
			}
		});
		
		tabelaFiltro.tableMain.addActionHandler(new Action.Handler() {

 			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * ITEM CLICK BOTAO DIREITO EVENTO
			 */
			public void handleAction(Action action, Object sender, final Object target) {
				if (target != null) {
					if (action == REMOVE_ITEM_ACTION) {
 						MessageBox mb = new MessageBox(getWindow(), 
 							"er", 
 							MessageBox.Icon.QUESTION, 
 							"er Servico?",
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
 				  	mb.show(new MessageBox.EventListener() {
 				  		 
 						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public void buttonClicked(ButtonType buttonType) {
 							
 							if (buttonType.equals(buttonType.YES)) {
 								try {
 									tabelaFiltro.tableMain.removeItem(target);
 									remover(target);
 									MessageBox mb = new MessageBox(getWindow(), 
 										"Remoção", 
 										MessageBox.Icon.INFO, 
 										"Servico removido",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 									System.out.println("RESULTADO TARGET: "+target);
 								} catch (Exception e) {
 									msgErro("remover.view: "+e.getMessage() + iser);
								}
 							}
 							else {
 							}
 						}
 					});
 	                  	 
					}
				}
 				else {
				}
            }

            public Action[] getActions(Object target, Object sender) {
                         // Context menu for an item
                    return new Action[] { REMOVE_ITEM_ACTION };
             }
        });
					
		
			addComponent(modotabelaFiltro());
			setComponent(modoLayoutTable);
			iser = true;
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
		VerticalLayout visualizarView = new VerticalLayout();
		servicoForm = new ServicoForm(serviceController.getServico());
		visualizarView.addComponent(servicoForm);
		visualizarView.setSpacing(true);
		@SuppressWarnings("unchecked")
		Table table = new Table("",new BeanItemContainer<ServicoAtualizacao>(ServicoAtualizacao.class,ServicoAtualizacao.listaServico()));
		table.setSizeFull();
		visualizarView.addComponent(table);
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
		servicoForm = new ServicoForm(new Servico());
	    addView.addComponent(servicoForm);
		return addView;
	}

	@Override
	public void editar() {
		try{
			Servico servico = servicoForm.getServico();
			removeComponent(getComponent());
			addComponent(modoLayoutTable);
			setComponent(modoLayoutTable);
			
			if(serviceController.alterar(servico)){
			
			MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Serivco Alterado",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
              	mb.show();
			}
			
			buttonAdicionar.setVisible(true);
			buttonDeletar.setVisible(false);
			buttonClonar.setVisible(false);
			buttonEditar.setVisible(false);
			buttonSalvar.setVisible(false);
			buttonVoltar.setVisible(false);
			refreshTable();
		}
		catch(Exception e){	
			msgErro(e.getMessage());
		}
	}

	@Override
	public void adicionar() {
	
		modoLayoutAdd = modoadicionarView();
		if(getComponent() != null){
			removeComponent(getComponent());
		}
		addComponent(modoLayoutAdd);
		setComponent(modoLayoutAdd);
		
		buttonAdicionar.setVisible(false);
		buttonClonar.setVisible(false);
		buttonEditar.setVisible(false);
		buttonSalvar.setVisible(true);
		buttonDeletar.setVisible(false);
		buttonVoltar.setVisible(true);
		
	}

	@Override
	public void remover(Object target) {
		Servico servico = (Servico) target;
		serviceController.remover(servico);
	}

	@Override
	public void voltar() {
		
		removeComponent(getComponent());
    	addComponent(modoLayoutTable);
    	setComponent(modoLayoutTable);
    	iser= true;
		
    	buttonAdicionar.setVisible(true);
		buttonSalvar.setVisible(false);
		buttonClonar.setVisible(false);
		buttonEditar.setVisible(false);
		buttonDeletar.setVisible(false);
		buttonVoltar.setVisible(false);
		
	}

	@Override
	public void salvar() {
		try{
			Servico servico = servicoForm.getServico();
			serviceController.salvar(servico);
			removeComponent(getComponent());
			addComponent(modoLayoutTable);
			setComponent(modoLayoutTable);
				
			MessageBox mb = new MessageBox(getWindow(), 
						"Servico Cadastrado", 
                        MessageBox.Icon.INFO, 
                        "Novo servico cadastrado",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
            mb.show(); 
			
			buttonAdicionar.setVisible(true);
			buttonDeletar.setVisible(false);
			buttonClonar.setVisible(false);
			buttonEditar.setVisible(false);
			buttonSalvar.setVisible(false);
			buttonVoltar.setVisible(false);
			refreshTable();
			
		}catch(Exception e){	
			msgErro(e.getMessage());
		}
	}

	@Override
	public void visualizar(Object[] dados) {
		modoLayoutView = modovisualizarView();
		if(getComponent() != null){
			removeComponent(getComponent());
		}
		addComponent(modoLayoutView);
		setComponent(modoLayoutView);
		iser = false;
		
		buttonClonar.setCaption("Força Atualização");
		buttonClonar.setVisible(true);
		buttonClonar.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				serviceController.ativarServico();
			}
		});	
		
		buttonAdicionar.setVisible(false);
		buttonDeletar.setVisible(false);
		buttonSalvar.setVisible(false);
		buttonEditar.setVisible(true);
		buttonVoltar.setVisible(true);
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
	
	public void defaultTable(){
		tabelaFiltro.tableMain.setContainerDataSource(new BeanItemContainer<Servico>(Servico.class, Servico.listaServico()));
	}
}