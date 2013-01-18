package com.auditor.view;

import com.abstracts.view.ViewComponente;
import com.auditor.controller.DepartamentoController;
import com.auditor.model.Departamento;
import com.auditor.view.form.DepartamentoForm;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.PainelInfo;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class DepartamentoView extends ViewComponente {

	public DepartamentoForm departamentoForm;
	public DepartamentoController departamentoController;
	public TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	private PainelInfo painelInfo;
	
	public DepartamentoView(DepartamentoController departamentoController){
		this.departamentoController = departamentoController;
	}
	
	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Departamento");
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
					departamentoController.visualizar(event.getItemId());
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
 							"Remover Departamento?",
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
 				  	mb.show(new MessageBox.EventListener() {
 				  		 
 						public void buttonClicked(ButtonType buttonType) {
 							
 							if (buttonType.equals(buttonType.YES)) {
 								//try {
 									tabelaFiltro.tableMain.removeItem(target);
 									remover(target);
 									MessageBox mb = new MessageBox(getWindow(), 
 										"Remover", 
 										MessageBox.Icon.INFO, 
 										"Departamento Removido com Sucesso",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 								/*} catch (Exception e) {
 									
								}*/
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
		painelInfo = new PainelInfo("Organizacao", departamentoController.organizacaoController.getOrganizacao().getId(),
				departamentoController.organizacaoController.getOrganizacao().getNomeFantasia(),
				departamentoController.organizacaoController.getOrganizacao().getDataCriacao(),
				departamentoController.organizacaoController.getOrganizacao().getDataUpdate(),
				"teste2");
		
	    departamentoForm = new DepartamentoForm(this.departamentoController.getDepartamento(), this.departamentoController.listUnidade());
	    
	    visualizarView.addComponent(painelInfo);
	    visualizarView.addComponent(departamentoForm);
	    visualizarView.addComponent(tabelaTelefone());
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		
		VerticalLayout addView = new VerticalLayout();
	    departamentoForm = new DepartamentoForm(this.departamentoController.getNovoDepartamento(), this.departamentoController.listUnidade());
	    
	    addView.addComponent(departamentoForm);
	
		return addView;
	}

	@Override
	public void editar() {
		
		try{
			if(this.departamentoController.alterar(departamentoForm.getDepartamento())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Departamento Alterado com Sucesso",  
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
			Departamento departamento = (Departamento)target;
	      	this.departamentoController.remover(departamento.getId());
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Departamento?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							departamentoController.removerButton(departamentoForm.getDepartamento());
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
								"Departamento Removido com Sucesso",  
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
    	departamentoController.refreshTable();
    	
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
			if(this.departamentoController.salvar(departamentoForm.getDepartamento())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
                        MessageBox.Icon.INFO, 
                        "Departamento Cadastrado com Sucesso",  
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
	
	public VerticalLayout tabelaTelefone(){
		
		VerticalLayout mainLayout = new VerticalLayout();
		
		mainLayout.setMargin(false, false, true, true);
		mainLayout.setSpacing(true);
		mainLayout.setHeight("100.0%");
		mainLayout.setWidth("494px");
		
		Label caption = new Label("<b>Telefones</b>", Label.CONTENT_XHTML);
		mainLayout.addComponent(caption);
		mainLayout.setComponentAlignment(caption, Alignment.TOP_LEFT);	
        
        Table table = new Table();
		table.setImmediate(true);
		table.setWidth("475px");
		table.setHeight("150px");
		table.setContainerDataSource(this.departamentoController.getDepartamento().listaBensTel());
		table.setVisibleColumns(new Object[]{"dddNumero", "tipo", "finalidade" , "ativacao"});
		mainLayout.addComponent(table);
		
		return mainLayout;
	}
}
