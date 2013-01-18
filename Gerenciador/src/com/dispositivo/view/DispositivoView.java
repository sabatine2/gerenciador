package com.dispositivo.view;

import com.dispositivo.controller.DispositivoController;
import com.dispositivo.model.Dispositivo;
import com.dispositivo.view.form.DispositivoForm;
import com.abstracts.view.ViewComponente;
import com.auditor.model.contato.Telefone;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;
import com.view.componente.PainelInfo;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class DispositivoView extends ViewComponente {

	private DispositivoForm dispositivoForm;
	private DispositivoController dispositivoController;
	public  TabelaFiltro tabelaFiltro;
	private PainelInfo painelInfo;
	public boolean isRemover;
	public Table table;
	
	public DispositivoView(DispositivoController dispositivoController){
		this.dispositivoController = dispositivoController;
	}
	
	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Dispositivo");
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
					if (event.getItemId() instanceof Dispositivo) {
						dispositivoController.visualizar(event.getItemId());
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
 							"Remover Dispositivo?",
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
 										"Dispositivo Removido com Sucesso",  
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
		painelInfo = new PainelInfo("Organizacao", dispositivoController.organizacaoController.getOrganizacao().getId(), "teste", dispositivoController.organizacaoController.getOrganizacao().getDataCriacao(), dispositivoController.organizacaoController.getOrganizacao().getDataUpdate(), "");
	    dispositivoForm = new DispositivoForm(this.dispositivoController.getDispositivo(), this.dispositivoController.listDepartamento());
	
		visualizarView.addComponent(painelInfo);
		visualizarView.addComponent(dispositivoForm);
		visualizarView.addComponent(buildTabela());
		
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    dispositivoForm = new DispositivoForm(this.dispositivoController.getNovoDispositivo(), this.dispositivoController.listDepartamento());

		addView.addComponent(dispositivoForm);
		return addView;
	}

	@Override
	public void editar() {
		
		try{
			if(this.dispositivoController.alterar(dispositivoForm.getDispostivo())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Dispositivo Alterado com Sucesso",  
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
			Dispositivo dispositivo = ( Dispositivo )target;
	      	this.dispositivoController.remover( dispositivo.getId() );
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Dispositivo?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							dispositivoController.removerButton(dispositivoForm.getDispostivo());
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
								"Dispositivo Removido com Sucesso",  
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
				if(this.dispositivoController.salvar(dispositivoForm.getDispostivo())){
					removeComponent(getComponent());
					addComponent(modoLayoutTable);
					setComponent(modoLayoutTable);
					MessageBox mb = new MessageBox(getWindow(), 
							"Cadastrar", 
	                        MessageBox.Icon.INFO, 
	                        "Dispositivo Cadastrado com Sucesso",  
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
	
	public AbsoluteLayout buildTabela(){
		
		AbsoluteLayout mainLayout = new AbsoluteLayout();
		
		mainLayout.setMargin(false, false, true, true);
		mainLayout.setHeight("200px");
		mainLayout.setWidth("515px");
		
		Label caption = new Label("<b>N�meros</b>", Label.CONTENT_XHTML);
		mainLayout.addComponent(caption, "left:26px;top:4px;");
		
        Button buttonAdd = new Button("+", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	
            	AbsoluteLayout btnLayout = new AbsoluteLayout();
                btnLayout.setMargin(true, false, false, true);
                btnLayout.setWidth("200px");
                btnLayout.setHeight("52px");
                
            	final ComboBox cbTel = new ComboBox("Selecione um N�mero");
            	cbTel.setWidth("200px");
            	cbTel.setNullSelectionAllowed(false);
            	cbTel.setContainerDataSource(Telefone.ListaBens());
            	cbTel.setItemCaptionPropertyId("dddNumero");
            	
            	final Window win = new Window("Telefones");
            	win.center();
            	win.setHeight("170px");
            	win.setWidth("246px");
            	win.setModal(true);
            	win.addComponent(cbTel);
            	win.addComponent(btnLayout);
            	
            	Button buttonOk = new Button("OK", new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						
						try {
						
							Telefone tel = dispositivoController.insereTelefone((Telefone) cbTel.getValue());
							table.addItem(new Object[]{tel.getDddNumero(), tel.getTipo(), tel.getFinalidade()}, tel);
							win.setVisible(false);
							
						} catch (Exception e) {
							win.setVisible(false);
						}
					}
				});
            	
            	btnLayout.addComponent(buttonOk, "top:25px;left:75px;");
            	
            	getWindow().addWindow(win);
            }
        });
        
        mainLayout.addComponent(buttonAdd, "left:460px;");	
        
        table = new Table();
		table.setImmediate(true);
		table.setWidth("472px");
		table.setHeight("150px");
		table.setContainerDataSource(this.dispositivoController.getDispositivo().ListaBensTel());
		table.setVisibleColumns(new Object[]{"dddNumero", "tipo", "finalidade"});
		table.setSelectable(true);
		table.addListener(new ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
				
				if (event.isDoubleClick()) {
					//DUPLO CLICK
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
 							"Remover ?",
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
 				  	mb.show(new MessageBox.EventListener() {
 				  		 
 						public void buttonClicked(ButtonType buttonType) {
 							
 							if (buttonType.equals(buttonType.YES)) {
 								try {
 									table.removeItem(target);
 									removeTelefone(target);
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
	
	public void removeTelefone(Object target){
		Telefone telefone = ( Telefone ) target;
		dispositivoController.desvincularTelefone(telefone);
	}
}
