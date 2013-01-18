package com.regiao.view;

import java.util.ArrayList;
import java.util.Iterator;

import com.abstracts.view.ViewComponente;
import com.regiao.controller.RegiaoController;
import com.regiao.model.AreaCobertura;
import com.regiao.model.Regiao;
import com.regiao.view.form.RegiaoForm;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access", "rawtypes"})
public class RegiaoView extends ViewComponente{

	public RegiaoController regiaoController;
	public RegiaoForm regiaoForm;
	public com.view.componente.TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	public Panel painelAdd;
	public AreaView areaView;
	private VerticalLayout vLayout;
	
	public RegiaoView(RegiaoController regiaoController){
		this.regiaoController = regiaoController;
	}

	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Regi�o");
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
					if (event.getItemId() instanceof Regiao) {
						regiaoController.visualizar(event.getItemId());
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
 							"Remover Regi�o?",
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
 										"Regi�o Removida com Sucesso",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 									System.out.println("RESULTADO TARGET: "+target);
 								} catch (Exception e) {
 									msgErro(e.getMessage());
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
	    regiaoForm = new RegiaoForm(this.regiaoController.getRegiao());
		visualizarView.addComponent(regiaoForm);
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    regiaoForm = new RegiaoForm(this.regiaoController.getNovaRegiao());	    
	    
		addView.addComponent(regiaoForm);
		addView.addComponent(createPanel());
		return addView;
	}

	@Override
	public void editar() {

		try{
			if(this.regiaoController.alterar(regiaoForm.getRegiao())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Regiao Alterada com Sucesso",  
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
			Regiao regiao = ( Regiao )target;
			System.out.println("remover target");
	      	this.regiaoController.remover(regiao.getId());
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Regi�o?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							regiaoController.removerButton(regiaoForm.getRegiao());
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
								"Regiao Removida com Sucesso",  
								new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
							mb.show();
						}
						else {}
					}
				});
			}catch(Exception e){	
				msgErro(e.getMessage());
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
		//try{
			if(this.regiaoController.salvar(regiaoForm.getRegiao())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
                        MessageBox.Icon.INFO, 
                        "Regiao Cadastrada com Sucesso",  
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
		/*}catch(Exception e){	
			System.out.println("ERRO SALVAR");
			msgErro(e.getMessage());
		}*/
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
	
	public void msgErro(String e){
		MessageBox mb = new MessageBox(getWindow(), 
				"Erro", 
                MessageBox.Icon.ERROR, 
                e,  
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
	
	public AbsoluteLayout createPanel(){
		
		AbsoluteLayout aLayout = new AbsoluteLayout();
		painelAdd = new Panel("Area");
		vLayout = new VerticalLayout();
		Button buttonAdd = new Button("+", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
			  novaArea(new AreaCobertura());	
			}
		});
		
		aLayout.setWidth("60em");
		aLayout.setHeight("35em");
		
		vLayout.setMargin(true, false, false, true);
        vLayout.setSpacing(true);
		
		painelAdd.setWidth("40em");
		painelAdd.setHeight("35em");
		
		vLayout.addComponent(buttonAdd);
		painelAdd.setContent(vLayout);
		
		aLayout.addComponent(painelAdd, "top:0px;left:25px;");
		
		return aLayout;
	}
	
	public void removeAreaView(Object object){
		painelAdd.removeComponent((AreaView) object);
	}
	
	public void novaArea(AreaCobertura area){
		areaView = new AreaView(area);
		areaView.buttonRemove.addListener(new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				removeAreaView(event.getButton().getData());
			}
		});
		
		vLayout.addComponent(areaView);

	}
	
	public void carregaArea(ArrayList<AreaCobertura> area){
		
		for(int i=0; i< area.size();i++){
			novaArea(area.get(i));
      }
		 
	}
	
	public ArrayList<AreaCobertura> getArea(){
			
		ArrayList<AreaCobertura> area = new ArrayList<AreaCobertura>();
		
		for (Iterator iterator = painelAdd.getComponentIterator(); iterator.hasNext();) {
			Object o = iterator.next();
			if (o instanceof AreaView) {
				AreaView areaV = (AreaView) o;
				area.add(areaV.getAreaCobertura());
			}
		}
		 return area;
	}
}