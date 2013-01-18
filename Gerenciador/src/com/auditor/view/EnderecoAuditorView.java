package com.auditor.view;

import com.abstracts.view.ViewComponente;
import com.auditor.controller.EnderecoAuditorController;
import com.auditor.model.contato.EnderecoAuditor;
import com.auditor.view.form.EnderecoAuditorForm;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings("serial")
public class EnderecoAuditorView extends ViewComponente {

	private EnderecoAuditorController enderecoController;
	private EnderecoAuditorForm enderecoForm;
	public TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	
	public EnderecoAuditorView(EnderecoAuditorController enderecoController){
		this.enderecoController = enderecoController;
	}
	
	public EnderecoAuditorView(VerticalLayout modoadicionarView) {
		
	}

	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Endere�o Auditor");
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
					enderecoController.visualizar(event.getItemId());
				}
			}
		});
		
		/*tabelaFiltro.tableMain.addActionHandler(new Action.Handler() {

			public void handleAction(Action action, Object sender, final Object target) {
				if (target != null) {
					if (action == REMOVE_ITEM_ACTION) {
 						MessageBox mb = new MessageBox(getWindow(), 
 							"Remover", 
 							MessageBox.Icon.QUESTION, 
 							"Remover Endere�o?",
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
 				  	mb.show(new MessageBox.EventListener() {
 				  		 
 						public void buttonClicked(ButtonType buttonType) {
 							
 							if (buttonType.equals(buttonType.YES)) {
 								try {
 									tabelaFiltro.tableMain.removeItem(target);
 									remover(target);
 								} catch (Exception e) {
 									msgErro("Impossivel excluir endere�o");
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
        });*/
					
		
			addComponent(modotabelaFiltro());
			setComponent(modoLayoutTable);
			isRemover = true;
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
	    enderecoForm = new EnderecoAuditorForm(this.enderecoController.getEndereco());
	    visualizarView.addComponent(enderecoForm);
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		
		VerticalLayout addView = new VerticalLayout();
	    enderecoForm = new EnderecoAuditorForm(this.enderecoController.getNovoEndereco());
		addView.addComponent(enderecoForm);
		return addView;
	}

	@Override
	public void editar() {

		try{
			if(this.enderecoController.alterar(enderecoForm.getEndereco())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Endereco Alterado com Sucesso",  
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
		
		enderecoController.defaultTable();
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
				EnderecoAuditor endereco = (EnderecoAuditor)target;
				this.enderecoController.remover(endereco.getId());
				MessageBox mb = new MessageBox(getWindow(), 
						"Remover", 
						MessageBox.Icon.INFO, 
						"Endere�o TESTE Removido com Sucesso",  
						new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
				mb.show();
		}
		else {
			try{
				if(this.enderecoController.removerButton(enderecoForm.getEndereco())){
					removeComponent(getComponent());
					addComponent(modoLayoutTable);
					setComponent(modoLayoutTable);
					isRemover = true;
					MessageBox mb = new MessageBox(getWindow(), 
							"Remover", 
	                        MessageBox.Icon.INFO, 
	                        "Endere�o Removido com Sucesso",  
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
				msgErro("Impossivel excluir endere�o");
			}
		}
	}

	@Override
	public void voltar() {
		removeComponent(getComponent());
    	addComponent(modoLayoutTable);
    	setComponent(modoLayoutTable);
    	isRemover = true;
    	enderecoController.defaultTable();
    	
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
			if(this.enderecoController.salvar(enderecoForm.getEndereco())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
                        MessageBox.Icon.INFO, 
                        "Endere�o Cadastrado com Sucesso",  
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
		isRemover = false;
		//enderecoForm.buttonOff();
		
		buttonAdicionar.setVisible(false);
		buttonDeletar.setVisible(true);
		buttonClonar.setVisible(false);
		buttonEditar.setVisible(true);
		buttonSalvar.setVisible(false);
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
	
	public void limpa(){
		
		enderecoForm.enderecoAuditor.setBairro("");
		enderecoForm.enderecoAuditor.setCep("");
		enderecoForm.enderecoAuditor.setCidade("");
		enderecoForm.enderecoAuditor.setComplemento("");
		enderecoForm.enderecoAuditor.setEstado("");
		enderecoForm.enderecoAuditor.setLogradouro("");
		enderecoForm.enderecoAuditor.setNumero("");
		enderecoForm.enderecoAuditor.setObs("");
		enderecoForm.enderecoAuditor.setStatus("");
		enderecoForm.enderecoAuditor.setTipo("");
	}
}