package com.ticket.view;

import com.abstracts.view.ViewComponente;
import com.auditor.view.form.PalavraChaveForm;
import com.ticket.controller.TopicoController;
import com.ticket.model.Topico;
import com.ticket.view.form.TopicoForm;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class TopicoView extends ViewComponente {

	private TopicoController topicoController;
	public TopicoForm topicoForm;
	public TabelaFiltro tabelaFiltro;
	public PalavraChaveForm pChaveForm;
	public boolean isRemover;
	public Table table;
	
	public TopicoView(TopicoController topicoController){
		this.topicoController = topicoController;
	}
	
	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Tópico");
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
					if (event.getItemId() instanceof Topico) {
						topicoController.visualizar(event.getItemId());
					}
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
 							"Remover T�pico?",
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
 										"Tópico Removido com Sucesso",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 									System.out.println("RESULTADO TARGET: "+target);
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
		
		topicoForm = new TopicoForm(this.topicoController.getTopico());
		visualizarView.addComponent(topicoForm);

		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    topicoForm = new TopicoForm(this.topicoController.getNovoTopico());
	    pChaveForm = new PalavraChaveForm(new String());

		addView.addComponent(topicoForm);
		addView.addComponent(pChaveForm);
		return addView;
	}

	@Override
	public void editar() {
		
		try{
			if(this.topicoController.alterar(topicoForm.getTopico())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Topico Alterado com Sucesso",  
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
			Topico ordem = ( Topico )target;
	      	this.topicoController.remover( ordem.getId() );
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Topico?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							topicoController.removerButton(topicoForm.getTopico());
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
								"Ordem Removida com Sucesso",  
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
			if(this.topicoController.salvar(topicoForm.getTopico())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
	                    MessageBox.Icon.INFO, 
	                    "Topico Cadastrado com Sucesso",  
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
		isRemover = false;
		
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
	
	public void msgAviso(String e){
		MessageBox mb = new MessageBox(getWindow(), 
				"Aviso", 
                MessageBox.Icon.WARN, 
                e,  
                new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
      	mb.show(); 
	}
	
	public PalavraChaveForm getpChaveForm() {
		return pChaveForm;
	}
	public void setpChaveForm(PalavraChaveForm pChaveForm) {
		this.pChaveForm = pChaveForm;
	}
}