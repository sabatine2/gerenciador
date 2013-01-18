package com.auditor.view;

import com.abstracts.view.ViewComponente;
import com.auditor.model.contato.PrestadoraTelefonia;
import com.auditor.view.form.PrestadoraTelefoniaForm;
import com.auditor.controller.PrestadoraTelefoniaController;
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
public class PrestadoraTelefoniaView extends ViewComponente {

	private PrestadoraTelefoniaForm prestadoraForm;
	private PrestadoraTelefoniaController prestadoraController;
	public TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	
	public PrestadoraTelefoniaView(PrestadoraTelefoniaController prestadoraController){
		this.prestadoraController = prestadoraController;
	}
	
	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Prestadora Telefonia");
		tabelaFiltro.filterField.addListener(new TextChangeListener() {
			
			public void textChange(TextChangeEvent event) {
				filtro(event);  
			}
		});
		
		tabelaFiltro.tableMain.addListener(new ItemClickListener() {
			
			/**
			 * ITEM CLICK EVENTO
			 */
			public void itemClick(ItemClickEvent event) {
				
				if (event.isDoubleClick()) {	
					prestadoraController.visualizar(event.getItemId());
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
 							"Remover Prestadora?",
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
 										"Prestadora Removida com Sucesso",  
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
		prestadoraForm = new PrestadoraTelefoniaForm(this.prestadoraController.getPrestadora());
		visualizarView.addComponent(prestadoraForm);
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    prestadoraForm = new PrestadoraTelefoniaForm(this.prestadoraController.getNovaPrestadora());
		addView.addComponent(prestadoraForm);
		return addView;
	}

	@Override
	public void editar() {
		
		try{
			if (this.prestadoraController.validarCNPJ(prestadoraForm.cnpj.getValue().toString())) {
			
				if(this.prestadoraController.alterar(prestadoraForm.getPrestadora())){
					removeComponent(getComponent());
					addComponent(modoLayoutTable);
	 				setComponent(modoLayoutTable);
	 				isRemover = true;
	 				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Prestadora Alterada com Sucesso",  
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
			else {
				MessageBox mb = new MessageBox(getWindow(), 
					"Erro", 
		            MessageBox.Icon.ERROR, 
		            "CNPJ inv�lido",  
		            new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
		      	mb.show(); 
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
		
		prestadoraForm.codigo.setReadOnly(false);
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
			PrestadoraTelefonia prestadora = (PrestadoraTelefonia)target;
	      	this.prestadoraController.remover(prestadora.getCodigo());
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Prestadora?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							prestadoraController.removerButton(prestadoraForm.getPrestadora());
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
								"Prestadora Removida com Sucesso",  
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
    	isRemover = true;
    	
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
			if (this.prestadoraController.validarCNPJ(prestadoraForm.cnpj.getValue().toString())) {
				
				if(this.prestadoraController.salvar(prestadoraForm.getPrestadora())){
					removeComponent(getComponent());
					addComponent(modoLayoutTable);
					setComponent(modoLayoutTable);
					MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
                        MessageBox.Icon.INFO, 
                        "Prestadora Cadastrada com Sucesso",  
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
			else {
				MessageBox mb = new MessageBox(getWindow(), 
						"Erro", 
		                MessageBox.Icon.ERROR, 
		                "CNPJ inv�lido",  
		                new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
		      	mb.show(); 
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

		prestadoraForm.codigo.setReadOnly(false);
     	prestadoraForm.prestadoraTelefonia.setCodigo((Long) dados[0]);
     	prestadoraForm.codigo.setReadOnly(true);
     	
     	prestadoraForm.prestadoraTelefonia.setNome((String) dados[1]);
     	prestadoraForm.prestadoraTelefonia.setCnpj((String) dados[2]);
     	prestadoraForm.prestadoraTelefonia.setEstado((String) dados[3]);
		
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
		
		prestadoraForm.prestadoraTelefonia.setCodigo(null);
     	prestadoraForm.prestadoraTelefonia.setNome("");
     	prestadoraForm.prestadoraTelefonia.setCnpj("");
     	prestadoraForm.prestadoraTelefonia.setEstado("");
	}
	
	public void filtro(TextChangeEvent event){
		
		this.prestadoraController.filtro(event.getText());  
	}	
}