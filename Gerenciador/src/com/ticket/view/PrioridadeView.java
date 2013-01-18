package com.ticket.view;

import java.util.Iterator;
import java.util.List;

import com.abstracts.view.ViewComponente;
import com.ticket.controller.PrioridadeController;
import com.ticket.model.Prioridade;
import com.ticket.view.form.PrioridadeForm;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class PrioridadeView extends ViewComponente {

	private PrioridadeController prioridadeController;
	public PrioridadeForm prioridadeForm;
	public TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	public Table table;
	
	public PrioridadeView(PrioridadeController prioridadeController){
		this.prioridadeController = prioridadeController;
	}
	
	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Prioridade");
		tabelaFiltro.filterField.setVisible(false);
		
		tabelaFiltro.tableMain.addListener(new ItemClickListener() {
			
			/**
			 * ITEM CLICK EVENTO
			 */
			public void itemClick(ItemClickEvent event) {
				
				if (event.isDoubleClick()) {	
					if (event.getItemId() instanceof Prioridade) {
						prioridadeController.visualizar(event.getItemId());
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
 							"Remover Prioridade?",
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
 										"Prioridade Removida com Sucesso",  
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
			defaultTable();
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
		prioridadeForm = new PrioridadeForm(this.prioridadeController.getPrioridade());
		visualizarView.addComponent(prioridadeForm);
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    prioridadeForm = new PrioridadeForm(this.prioridadeController.getNovoPrioridade());
	  	addView.addComponent(prioridadeForm);
		return addView;
	}

	@Override
	public void editar() {
		
		try{
			if(this.prioridadeController.alterar(prioridadeForm.getPrioridade())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Prioridade Alterado com Sucesso",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
              	mb.show();
              	refreshTable();
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
			Prioridade ordem = ( Prioridade )target;
	      	this.prioridadeController.remover( ordem.getId() );
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Prioridade?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							prioridadeController.removerButton(prioridadeForm.getPrioridade());
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
							voltar();
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
			if(this.prioridadeController.salvar(prioridadeForm.getPrioridade())){
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
	                    MessageBox.Icon.INFO, 
	                    "Prioridade Cadastrado com Sucesso",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
				mb.show(); 
				refreshTable();
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
		
	}
	
	
	public void visualizar() {
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
	
	public void refreshTable(){
		tabelaFiltro.tableMain.removeAllItems();
		defaultTable();
	}
	
	public CssLayout getCss(String cor){
		
		final String[] corInfo = cor.split("-");
		Label label = null; 
		if(corInfo.length > 0){
			label = new Label(corInfo[0]);
		}else label = new Label("Sem Cor");
	
		CssLayout cssLayout = new CssLayout() {
			
            @Override
            protected String getCss(Component c) {
            	if(corInfo.length > 1){
                return "background:"+corInfo[1]+";";
            	}
            	else return null;
            }
        };
      
        label.setSizeFull();
        cssLayout.addComponent(label);
        cssLayout.setHeight("100%");
        cssLayout.setWidth("100%");
        return cssLayout;
	}
	
	public void defaultTable(){
		tabelaFiltro.tableMain.addContainerProperty("Prioridade", String.class, null);
		tabelaFiltro.tableMain.addContainerProperty("Publico",String.class, null);
		tabelaFiltro.tableMain.addContainerProperty("Prioridade Urgência",String.class, null);
		tabelaFiltro.tableMain.addContainerProperty("Cor", CssLayout.class, null);
		
		List<Prioridade> prioridade = Prioridade.listaPrioridade();
		
	    for(Iterator<Prioridade> i = prioridade.iterator(); i.hasNext();){
	    	Prioridade pri = i.next();
	    	tabelaFiltro.tableMain.addItem(new Object[] {pri.getPrioridade(),pri.getPublico(),pri.getPrioridadeUrgencia(),getCss(pri.getCor())},pri);
	    }

	}
	
}