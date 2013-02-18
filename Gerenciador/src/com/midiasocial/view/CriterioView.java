package com.midiasocial.view;

import com.midiasocial.controller.CriterioController;
import com.midiasocial.model.Criterio;
import com.midiasocial.view.form.CriterioForm;
import com.midiasocial.view.form.PalavraChaveMidiaForm;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.TabelaFiltro;
import com.abstracts.view.ViewComponente;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;
import de.steinwedel.vaadin.MessageBox.Icon;

@SuppressWarnings({"serial", "static-access"})
public class CriterioView extends ViewComponente {

	public CriterioController criterioController;
	public CriterioForm criterioForm;
	public TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	public PalavraChaveMidiaForm pChaveForm;
	
	public CriterioView(CriterioController criterioController){
		this.criterioController = criterioController;
	}

	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Criterio");
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
					if (event.getItemId() instanceof Criterio) {
						criterioController.visualizar(event.getItemId());
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
 							"Remover Criterio?",
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "N‹o"));
 				  	mb.show(new MessageBox.EventListener() {
 				  		 
 						public void buttonClicked(ButtonType buttonType) {
 							
 							if (buttonType.equals(buttonType.YES)) {
 								try {
 									tabelaFiltro.tableMain.removeItem(target);
 									remover(target);
 									MessageBox mb = new MessageBox(getWindow(), 
 										"Remover", 
 										MessageBox.Icon.INFO, 
 										"Criterio Removido",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 									System.out.println("RESULTADO TARGET: "+target);
 								} catch (Exception e) {
 									msg("remover.view: "+e.getMessage() + isRemover, MessageBox.Icon.ERROR);
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
	    criterioForm = new CriterioForm(this.criterioController.getCriterio());
	    pChaveForm = new PalavraChaveMidiaForm(this.criterioController.getCriterio().getPalavraChaveString());
		visualizarView.addComponent(criterioForm);
		visualizarView.addComponent(pChaveForm);
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    criterioForm = new CriterioForm(this.criterioController.getNovoCriterio());
	    pChaveForm = new PalavraChaveMidiaForm(this.criterioController.getCriterio().getPalavraChaveString());
		addView.addComponent(criterioForm);
		addView.addComponent(pChaveForm);
		return addView;
	}

	@Override
	public void editar() {

		try{
			if(this.criterioController.alterar(criterioForm.getCriterio())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Criterio Alterado",  
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
			msg(e.getMessage(), MessageBox.Icon.ERROR);
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
			Criterio topico = ( Criterio )target;
			System.out.println("remover target" + " " +topico.getId());
	      	this.criterioController.remover(topico.getId());
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Criterio?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							criterioController.removerButton(criterioForm.getCriterio());
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
								"Criterio Removido",  
								new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
							mb.show();
						}
						else {}
					}
				});
			}catch(Exception e){	
				msg("remover.view.button: "+e.getMessage(), MessageBox.Icon.ERROR);
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
			if(this.criterioController.salvar(criterioForm.getCriterio())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
                        MessageBox.Icon.INFO, 
                        "Criterio Cadastrado",  
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
		}catch(Exception e){	
			System.out.println("ERRO SALVAR");
			msg("Erro ao salvar " + e.getMessage(), MessageBox.Icon.ERROR);
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
	
	public void msg(String e, Icon icon){
		MessageBox mb = new MessageBox(getWindow(), 
				"Erro", 
                icon, 
                e,  
                new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
      	mb.show(); 
	}

	public PalavraChaveMidiaForm getpChaveForm() {
		return pChaveForm;
	}

	public void setpChaveForm(PalavraChaveMidiaForm pChaveForm) {
		this.pChaveForm = pChaveForm;
	}
	
	public void refreshTable(){
		defaultTable();		
	}
	
	public void defaultTable(){
		tabelaFiltro.tableMain.setContainerDataSource(new BeanItemContainer<Criterio>(Criterio.class, Criterio.listaUsuario()));
		tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id", "nome", "status", "prioridade"});
	}
}