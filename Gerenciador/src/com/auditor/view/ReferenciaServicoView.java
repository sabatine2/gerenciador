package com.auditor.view;

import com.abstracts.view.ViewComponente;
import com.auditor.controller.ReferenciaServicoController;
import com.auditor.model.ReferenciaServico;
import com.auditor.view.form.ReferenciaServicoForm;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class ReferenciaServicoView extends ViewComponente {

	public ReferenciaServicoForm referenciaForm;
	public ReferenciaServicoController referenciaController;
	public TabelaFiltro tabelaFiltro;
	boolean isRemover;
	
	public ReferenciaServicoView(ReferenciaServicoController referenciaController) {
		this.referenciaController = referenciaController;
	}
	
	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Refer�ncia Servi�o");
		tabelaFiltro.filterField.addListener(new TextChangeListener() {
			
			public void textChange(TextChangeEvent event) {
				//filtro(event);  
			}
		});
		
		tabelaFiltro.tableMain.addListener(new ItemClickListener() {
			
			/**
			 * ITEM CLICK EVENTO
			 */
			public void itemClick(ItemClickEvent event) {
				
				if (event.isDoubleClick()) {	
					referenciaController.visualizar(event.getItemId());
				}
			}
		});
		
		tabelaFiltro.tableMain.addActionHandler(new Action.Handler() {

 			/**
			 * ITEM CLICK BOTAO DIREITO EVENTO
			 */
			public void handleAction(Action action, Object sender, final Object target) {
				if (target != null) {
					if (action == REMOVE_ITEM_ACTION) {
 						MessageBox mb = new MessageBox(getWindow(), 
 							"Remover", 
 							MessageBox.Icon.QUESTION, 
 							"Remover Refer�ncia?",
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
 				  	mb.show(new MessageBox.EventListener() {
 				  		 
 						public void buttonClicked(ButtonType buttonType) {
 							
 							if (buttonType.equals(buttonType.YES)) {
 								try {
 									tabelaFiltro.tableMain.removeItem(target);
 									remover(target);
 									MessageBox mb = new MessageBox(getWindow(), 
 										"Remover", 
 										MessageBox.Icon.INFO, 
 										"Refer�ncia Removida com Sucesso",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 								} catch (Exception e) {
 									
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
			isRemover= true;
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
		referenciaForm = new ReferenciaServicoForm(this.referenciaController.getReferencia());
		visualizarView.addComponent(referenciaForm);
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
		referenciaForm = new ReferenciaServicoForm(this.referenciaController.getNovaReferencia());
		addView.addComponent(referenciaForm);
		return addView;
	}

	@Override
	public void editar() {
		
		try{
			if(this.referenciaController.alterar(referenciaForm.getReferencia())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Refer�ncia Alterada com Sucesso",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
              	mb.show(); 
              	
				buttonAdicionar.setVisible(true);
				buttonDeletar.setVisible(false);
				buttonClonar.setVisible(false);
				buttonEditar.setVisible(false);
				buttonSalvar.setVisible(false);
				buttonVoltar.setVisible(false);
			}
			else{
			}
		}
		catch(Exception e){	
			msgErro(e);
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
		
		//limpa();
		
		buttonAdicionar.setVisible(false);
		buttonClonar.setVisible(false);
		buttonEditar.setVisible(false);
		buttonSalvar.setVisible(true);
		buttonDeletar.setVisible(false);
		buttonVoltar.setVisible(true);
	}

	@Override
	public void remover(Object target) {
		
		if (isRemover) {
			ReferenciaServico referenciaServico = (ReferenciaServico)target;
	      	this.referenciaController.remover(referenciaServico.getId());
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Refer�ncia?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							referenciaController.removerButton(referenciaForm.getReferencia());
							removeComponent(getComponent());
							addComponent(modoLayoutTable);
							setComponent(modoLayoutTable);
							isRemover = true;
					
							buttonAdicionar.setVisible(true);
							buttonDeletar.setVisible(false);
							buttonClonar.setVisible(false);
							buttonEditar.setVisible(false);
							buttonSalvar.setVisible(false);
							buttonVoltar.setVisible(false);
							
							MessageBox mb = new MessageBox(getWindow(), 
								"Remover", 
								MessageBox.Icon.INFO, 
								"Refer�ncia Removida com Sucesso",  
								new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
							mb.show();
						}
						else {}
					}
				});
			}catch(Exception e){	
				msgErro(e);
			}
		}
	}

	@Override
	public void voltar() {
		removeComponent(getComponent());
    	addComponent(modoLayoutTable);
    	setComponent(modoLayoutTable);
    	isRemover= true;
		
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
			if(this.referenciaController.salvar(referenciaForm.getReferencia())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
                        MessageBox.Icon.INFO, 
                        "Refer�ncia Cadastrada com Sucesso",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
              	mb.show(); 
              	
				buttonAdicionar.setVisible(true);
				buttonDeletar.setVisible(false);
				buttonClonar.setVisible(false);
				buttonEditar.setVisible(false);
				buttonSalvar.setVisible(false);
				buttonVoltar.setVisible(false);
			}
			else{
			}
		}
		catch(Exception e){	
			msgErro(e);
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
		
		isRemover= false;
		
     	buttonAdicionar.setVisible(false);
		buttonDeletar.setVisible(true);
		buttonClonar.setVisible(false);
		buttonEditar.setVisible(true);
		buttonSalvar.setVisible(false);
		buttonVoltar.setVisible(true);
	}
	
	public void msgErro(Exception e){
		MessageBox mb = new MessageBox(getWindow(), 
				"Erro", 
                MessageBox.Icon.ERROR, 
                e.getMessage(),  
                new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
      	mb.show(); 
	}
	
	public void limpa(){
		
		referenciaForm.referencia.setTipo("");
     	referenciaForm.referencia.setObs("");
	}
}