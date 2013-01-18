package com.auditor.view;

import java.util.List;

import com.abstracts.view.ViewComponente;
import com.auditor.controller.TelefoneController;
import com.auditor.model.Conta;
import com.auditor.model.contato.Telefone;
import com.auditor.view.form.TelefoneForm;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access", "unchecked"})
public class TelefoneView extends ViewComponente {

	public TelefoneForm telefoneForm;
	public TelefoneController telefoneController;
	public TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	public ContaView contaView;
	public VerticalLayout modoLayoutContaView;
	public VerticalLayout modoLayoutContaAdd;
	public Table table;
	
	public TelefoneView(TelefoneController telefoneController){
		this.telefoneController = telefoneController;
	}
	
	@Override
	public void modoTabela() {

		tabelaFiltro = new TabelaFiltro("Telefone");
		tabelaFiltro.filterField.addListener(new TextChangeListener() {
			
			/**
			 * 
			 */
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
					telefoneController.visualizar(event.getItemId());
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
 							"Remover Telefone?",
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
 										"Telefone Removido com Sucesso",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 								} catch (Exception e) {
 									MessageBox mb = new MessageBox(getWindow(), 
 	 										"ERRO", 
 	 										MessageBox.Icon.WARN, 
 	 										e.getMessage(),  
 	 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 	 									mb.show();
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
		telefoneForm = new TelefoneForm(this.telefoneController.getTelefone(), this.telefoneController.listDepartamento(), this.telefoneController.beansReferencia, this.telefoneController.beansPrestadora);
		visualizarView.addComponent(telefoneForm);
		visualizarView.addComponent(buildContaLayout());
		System.out.println("RESULTADO: "+this.telefoneController.getTelefone().getDepartamento());
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    telefoneForm = new TelefoneForm(this.telefoneController.getNovoTelefone(), this.telefoneController.listDepartamento(), this.telefoneController.beansReferencia, this.telefoneController.beansPrestadora);
		addView.addComponent(telefoneForm);
		return addView;
	}
	
	public VerticalLayout modoContaAdicionar(){
		VerticalLayout contaAddView = new VerticalLayout();
		contaView = new ContaView(telefoneController.getTelefone(), telefoneController);
		contaView.modoAddConta(new Conta());
	    contaAddView.addComponent(contaView);
		return contaAddView;
	}
	
	public VerticalLayout modoContaView(Conta conta){
		VerticalLayout contaVisualizar = new VerticalLayout();
		contaView = new ContaView(telefoneController.getTelefone(), telefoneController);
		contaView.modoViewConta(conta);
	    contaVisualizar.addComponent(contaView);
		return contaVisualizar;
	}

	@Override
	public void editar() {
		
		if (getComponent() == modoLayoutView) {
			//Telefone telefone = telefoneForm.getTelefone();
			//telefone.getConta().add(conta);
			try{
				if(this.telefoneController.alterar(telefoneForm.getTelefone())){
					isRemover = false;
					MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Telefone Alterado com Sucesso",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					mb.show();
				}
				else{
				}
			}catch(Exception e){	
			msgErro(e);
			}
		}
		else if (getComponent() == modoLayoutContaAdd || getComponent() == modoLayoutContaView) {
			if (contaView.getContaDetalhe().valida()) {
				
				if (contaView.conta.getId() == null) {
					Conta conta = contaView.getContaDetalhe().commit();
					conta.setTelefone(telefoneForm.getTelefone());
					conta.salvar();
					MessageBox mb = new MessageBox(getWindow(), 
						"Salvar", 
                        MessageBox.Icon.INFO, 
                        "Conta adicionada",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					mb.show(); 
					contaView.modoViewConta(conta);
				}
				else {
					Conta conta = contaView.getContaDetalhe().alterar(contaView.conta);
					conta.setTelefone(telefoneForm.getTelefone());
					conta.alterar();
					MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
	                    MessageBox.Icon.INFO, 
	                    "Conta alterada",  
	                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					mb.show(); 
				}
			}
			else {
				MessageBox mb = new MessageBox(getWindow(), 
						"Erro", 
                        MessageBox.Icon.ERROR, 
                        "Preencha os campos obrigat�rios",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
				mb.show(); 
			}
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
		
		telefoneForm.readOnlyFalse();
		
		buttonAdicionar.setVisible(false);
		buttonClonar.setVisible(false);
		buttonEditar.setVisible(false);
		buttonSalvar.setVisible(true);
		buttonDeletar.setVisible(false);
		buttonVoltar.setVisible(true);
	}
	
	public void contaAdd(){
		
		modoLayoutContaAdd = modoContaAdicionar();
		if(getComponent() != null){
			removeComponent(getComponent());
		}
		addComponent(modoLayoutContaAdd);
		setComponent(modoLayoutContaAdd);
		
		buttonAdicionar.setVisible(false);
		buttonClonar.setVisible(false);
		buttonEditar.setVisible(true);
		buttonSalvar.setVisible(false);
		buttonDeletar.setVisible(false);
		buttonVoltar.setVisible(true);
	}
	
	public void contaView(Conta conta){
		
		modoLayoutContaView = modoContaView(conta);
		if(getComponent() != null){
			removeComponent(getComponent());
		}
		addComponent(modoLayoutContaView);
		setComponent(modoLayoutContaView);
		
		buttonAdicionar.setVisible(false);
		buttonClonar.setVisible(false);
		buttonEditar.setVisible(true);
		buttonSalvar.setVisible(false);
		buttonDeletar.setVisible(true);
		buttonVoltar.setVisible(true);
	}
		
	public void removerConta(Object target) {
		Conta conta = (Conta)target;
	   	this.telefoneController.removerConta(conta.getId());
	}
	
	public void removerContaButton(){
		
		try{
			MessageBox mb = new MessageBox(getWindow(), 
				"Remover", 
				MessageBox.Icon.QUESTION, 
				"Remover Conta?",
				new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
				new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
			mb.show(new MessageBox.EventListener() {	 
		
				public void buttonClicked(ButtonType buttonType) {	
					if (buttonType.equals(buttonType.YES)) {
					
						telefoneController.removerContaButton(contaView.conta);
						removeComponent(getComponent());
						addComponent(modoLayoutView);
						setComponent(modoLayoutView);
						isRemover = false;
						refreshTable();
			
						buttonAdicionar.setVisible(false);
						buttonDeletar.setVisible(true);
						buttonClonar.setVisible(false);
						buttonEditar.setVisible(true);
						buttonSalvar.setVisible(false);
						buttonVoltar.setVisible(true);
					
						MessageBox mb = new MessageBox(getWindow(), 
							"Remover", 
							MessageBox.Icon.INFO, 
							"Conta Removido com Sucesso",  
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
	
	@Override
	public void remover(Object target) {
		
		
		if (getComponent() == modoLayoutTable || getComponent() == modoLayoutView) {
			if (isRemover) {
				Telefone telefone = (Telefone)target;
				System.out.println("Remover");
				this.telefoneController.remover(telefone.getDddNumero());
			}
			else {
				try{
					MessageBox mb = new MessageBox(getWindow(), 
						"Remover", 
						MessageBox.Icon.QUESTION, 
						"Remover Telefone?",
						new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
						new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
					mb.show(new MessageBox.EventListener() {	 
				
						public void buttonClicked(ButtonType buttonType) {	
							if (buttonType.equals(buttonType.YES)) {
							
								telefoneController.removerButton(telefoneForm.getTelefone());
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
									"Telefone Removido com Sucesso",  
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
		else if (getComponent() == modoLayoutContaView) {
			removerContaButton();
		}
	}

	@Override
	public void voltar() {
		removeComponent(getComponent());
		if (getComponent() == modoLayoutView || getComponent() == modoLayoutAdd) {
	    	addComponent(modoLayoutTable);
	    	setComponent(modoLayoutTable);
	    	isRemover= true;
	    	telefoneForm.readOnlyTrue();
	    	
			buttonAdicionar.setVisible(true);
			buttonSalvar.setVisible(false);
			buttonClonar.setVisible(false);
			buttonEditar.setVisible(false);
			buttonDeletar.setVisible(false);
			buttonVoltar.setVisible(false);
		}
		else{
			addComponent(modoLayoutView);
			setComponent(modoLayoutView);

			contaView.getContaDetalhe().limpa();
			refreshTable();
			
			buttonAdicionar.setVisible(false);
			buttonDeletar.setVisible(true);
			buttonClonar.setVisible(false);
			buttonEditar.setVisible(true);
			buttonSalvar.setVisible(false);
			buttonVoltar.setVisible(true);
		}
	}

	public void salvar() {
		
	       try{
				if(this.telefoneController.salvar(telefoneForm.getTelefone())){
					removeComponent(getComponent());
					addComponent(modoLayoutTable);
					setComponent(modoLayoutTable);
					MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
						MessageBox.Icon.INFO, 
                        "Telefone Cadastrado com Sucesso",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					mb.show(); 
              	
					telefoneForm.readOnlyTrue();
					
					buttonAdicionar.setVisible(true);
					buttonDeletar.setVisible(false);
					buttonClonar.setVisible(false);
					buttonEditar.setVisible(false);
					buttonSalvar.setVisible(false);
					buttonVoltar.setVisible(false);
				}
				else{
					System.out.println("salvar view erro");
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
		telefoneForm.readOnlyTrue();
		
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
	
	public AbsoluteLayout buildContaLayout(){
		
		AbsoluteLayout mainLayout = new AbsoluteLayout();
		
		mainLayout.setMargin(false, false, true, true);
		mainLayout.setHeight("200px");
		mainLayout.setWidth("515px");
		
		Label caption = new Label("<b>Contas</b>", Label.CONTENT_XHTML);
		mainLayout.addComponent(caption, "left:26px;top:4px;");
		
        Button buttonAdd = new Button("+", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	contaAdd();
            }
        });
        mainLayout.addComponent(buttonAdd, "left:460px;");	
        
        table = new Table();
		table.setImmediate(true);
		table.setWidth("472px");
		table.setHeight("150px");
		table.setContainerDataSource(this.telefoneController.getTelefone().ListaBensConta());
		table.setVisibleColumns(new Object[]{"id", "usuario", "data", "valor"});
		table.setSelectable(true);
		table.addListener(new ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
				
				if (event.isDoubleClick()) {
					System.out.println("EVENTO");
					telefoneController.visualizarConta(event.getItemId());
				}
			}
		});
		table.addActionHandler(new Action.Handler() {

 			/**
			 * ITEM CLICK BOTAO DIREITO EVENTO
			 */
			public void handleAction(Action action, Object sender, final Object target) {
				if (target != null) {
					if (action == REMOVE_ITEM_ACTION) {
 						MessageBox mb = new MessageBox(getWindow(), 
 							"Remover", 
 							MessageBox.Icon.QUESTION, 
 							"Remover Conta?",
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
 				  	mb.show(new MessageBox.EventListener() {
 				  		 
 						public void buttonClicked(ButtonType buttonType) {
 							
 							if (buttonType.equals(buttonType.YES)) {
 								try {
 									table.removeItem(target);
 									removerConta(target);
 									MessageBox mb = new MessageBox(getWindow(), 
 										"Remover", 
 										MessageBox.Icon.INFO, 
 										"Conta Removida com Sucesso",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 								} catch (Exception e) {
 									MessageBox mb = new MessageBox(getWindow(), 
 	 										"ERRO", 
 	 										MessageBox.Icon.WARN, 
 	 										e.getMessage(),  
 	 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 	 									mb.show();
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
		mainLayout.addComponent(table,"top:30px;left:26px");
		
		return mainLayout;
	}
	
	public void refreshTable(){
		
		table.removeAllItems();
		List<Conta> itensTable = (List<Conta>) telefoneController.getTelefone().listBensConta();
		for (int i = 0; i < itensTable.size(); i++) {
			table.addItem(itensTable.get(i));
		}
	}
}