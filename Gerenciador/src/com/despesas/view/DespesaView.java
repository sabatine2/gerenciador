package com.despesas.view;

import java.util.ArrayList;

import com.despesas.controller.DespesasController;
import com.despesas.model.Despesas;
import com.despesas.model.ItensDespesas;
import com.despesas.view.form.DespesaForm;
import com.vaadin.event.Action;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Button.ClickEvent;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class DespesaView extends VerticalSplitPanel {
	
	public Table tableItensDespesas;
	private DespesaForm despesaForm;
	public Button buttonAdicionar;
	public Button buttonClonar;
	public Button buttonSalvar;
	public Button buttonEditar;
	public Button buttonVoltar;
	public Button buttonAlterarSenha;
	public Button buttonDeletar;
	public Button buttonImprimir;
	public TabelaFiltro tabelaFiltro;
    public AbsoluteLayout modoLayoutAdd = null;
    public VerticalLayout modoLayoutTable;
    public VerticalLayout modoLayoutView;
    public Component      modoLayoutAtivo;
    public ArrayList<ItensDespesas> listItens = new ArrayList<ItensDespesas>();
	private DespesasController despesasController;
	private static final Action REMOVE_ITEM_ACTION = new Action("Remove");
	private Long idAtivo;
	public boolean isRemover;
	
	public DespesaView(DespesasController despesasController){
		this.despesasController = despesasController;
    	
		setWidth("100%");
		setHeight("100%");
        addStyleName("small white white");
    	setMargin(true);
		setSplitPosition(9);
		setLocked(true);

		CssLayout css = new CssLayout();
		css.setWidth("100%");
		css.setHeight("98%");
		css.addStyleName("toolbar-invert");
		css.addComponent(buildHorizontalLayoutLeft());
		css.addComponent(buildHorizontalLayoutRight());
			
		addComponent(css);
		
		tabelaFiltro = new TabelaFiltro("Despesas");
		tabelaFiltro.filterField.addListener(new TextChangeListener() {
			
			public void textChange(TextChangeEvent event) {
				//filtro(event); 
			}
		});
		
		tabelaFiltro.tableMain.addListener(new ItemClickListener() {
		
			public void itemClick(ItemClickEvent event) {
			 	
				if (event.isDoubleClick()) {
					DespesaView.this.despesasController.visualizar(event.getItemId());
					despesaForm.setBeans();
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
 							"Remover Despesa?",
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
 										"Despesa removida",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 								} catch (Exception e) {}
 							}
 							else {}
 						}
 					});
 	                  	 
					}
				}
 				else {}
            }
			
            public Action[] getActions(Object target, Object sender) {

                                  // Context menu for an item
                    return new Action[] { REMOVE_ITEM_ACTION };
            }
        });
		addComponent(tabelaFiltro());
		setComponent(modoLayoutTable);
		isRemover = true;
	}

    public VerticalLayout tabelaFiltro(){
    
    	modoLayoutTable = new VerticalLayout();
		modoLayoutTable.setHeight("500px");
		modoLayoutTable.setMargin(true);
		
		modoLayoutTable.addComponent(tabelaFiltro);
		return modoLayoutTable;
    }

    public VerticalLayout visualizarView(){
    	
    	modoLayoutView = new VerticalLayout();
		modoLayoutView.setHeight("1192px");
		modoLayoutView.setMargin(false);
		despesaForm = new DespesaForm(despesasController.getDespesa(), despesasController);
		modoLayoutView.addComponent(despesaForm);
		
		/*Collection<ItensDespesas> idespesa = (Collection<ItensDespesas>) despesasController.getDespesa().getItensDespesa();
       	
    	for (Iterator iterator = idespesa.iterator(); iterator.hasNext();) {
    		ItensDespesas wo = (ItensDespesas) iterator.next();
    		addItensDespesas(wo);
       	}*/
		return modoLayoutView;
    }
  
  	private AbsoluteLayout adicionarView() {
		modoLayoutAdd = new AbsoluteLayout();
		modoLayoutAdd.setHeight("1192px");
		modoLayoutAdd.setMargin(false);
		despesaForm = new DespesaForm(new Despesas(), despesasController);
		modoLayoutAdd.addComponent(despesaForm);
		return modoLayoutAdd;
	}

	private CssLayout buildHorizontalLayoutLeft() {
		// common part: create layout
		CssLayout left = new CssLayout();
		left.setSizeUndefined();
		left.addStyleName("left");
		addComponent(left);
		
		// buttonVoltar
		buttonVoltar = new Button("Voltar",new Button.ClickListener() {
            // inline click-listener
            public void buttonClick(ClickEvent event) {
            	  voltar();
          }
        });
		buttonVoltar.setWidth("-1px");
		buttonVoltar.setHeight("-1px");
		buttonVoltar.setVisible(false);
		left.addComponent(buttonVoltar);
		
		return left;
	}

	private CssLayout buildHorizontalLayoutRight() {
		// common part: create layout
		CssLayout right = new CssLayout();
		right.setSizeUndefined();
		right.addStyleName("right");
		addComponent(right);
		
		// buttonEditar
				buttonEditar = new Button("Salvar Edi��o",new Button.ClickListener() {
		            // inline click-listener
		            public void buttonClick(ClickEvent event) {
		                salvar();	           
		            }
		        });
				buttonEditar.addStyleName("border-normal");
				buttonEditar.setWidth("-1px");
				buttonEditar.setHeight("-1px");
				buttonEditar.setVisible(false);
				right.addComponent(buttonEditar);
		
		//buttonDeletar
		buttonDeletar = new Button("Remover",new Button.ClickListener() {
		    // inline click-listener
		    public void buttonClick(ClickEvent event) {
		    	remover(event.getSource());
		    	buttonEditar.setVisible(false);
		    	buttonImprimir.setVisible(false);
		    }
		});
		buttonDeletar.addStyleName("border-normal");
		buttonDeletar.setWidth("-1px");
		buttonDeletar.setHeight("-1px");
		buttonDeletar.setVisible(false);		
		right.addComponent(buttonDeletar);
			
		// buttonSalvar
		buttonSalvar = new Button("Salvar",new Button.ClickListener() {
            // inline click-listener
            public void buttonClick(ClickEvent event) {
                salvar();
            }
        });
		buttonSalvar.addStyleName("border-normal");
		buttonSalvar.setWidth("-1px");
		buttonSalvar.setHeight("-1px");
		buttonSalvar.setVisible(false);
		right.addComponent(buttonSalvar);
		
		// buttonClonar
		buttonClonar = new Button();
		buttonClonar.setCaption("Clonar");
		buttonClonar.addStyleName("border-normal");
		buttonClonar.setWidth("-1px");
		buttonClonar.setHeight("-1px");
		buttonClonar.setVisible(false);
		right.addComponent(buttonClonar);
		
		// buttonAdicionar
		buttonAdicionar = new Button("Adicionar",new Button.ClickListener() {
            // inline click-listener
            public void buttonClick(ClickEvent event) {
                despesasController.adicionar();
            }
        });
		buttonAdicionar.addStyleName("border-normal");
		buttonAdicionar.setWidth("-1px");
		buttonAdicionar.setHeight("-1px");
		right.addComponent(buttonAdicionar);
		
		// buttonImprimir
				buttonImprimir = new Button("Imprimir",new Button.ClickListener() {
				// inline click-listener
					public void buttonClick(ClickEvent event) {
			           getWindow().open(despesasController.geraRelatorio(idAtivo, getApplication()), "_new");
					}
				});
				buttonImprimir.setWidth("-1px");
				buttonImprimir.setHeight("-1px");
				buttonImprimir.setVisible(false);
				right.addComponent(buttonImprimir);
		
		return right;
	}

	/*public void filtro(TextChangeEvent event){
	
		this.despesasController.filtro(event.getText());  
	}*/
    
    public void adicionar(){
    	removeComponent(getComponent());
    	addComponent(adicionarView());
        setComponent(modoLayoutAdd);
    
    	buttonVoltar.setVisible(true);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(true);
    	buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(false);
   	 	buttonImprimir.setVisible(false);	
    }
    
	public void remover(Object target) {
		if (isRemover) {
			Despesas despesas = (Despesas)target;
	      	this.despesasController.remover(despesas.getId());
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Despesa?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							despesasController.removerButton(despesaForm.getDespesas());
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
									"Despesa removida",  
									new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
							mb.show();
						}
						else {}
					}
				});
			}
			catch(Exception e){	
				MessageBox mb = new MessageBox(getWindow(), 
					"Erro", 
					MessageBox.Icon.ERROR, 
					e.getMessage(),  
					new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
				mb.show();;
			}
		}
	}
    
    public void voltar(){
    	removeComponent(getComponent());
    	addComponent(modoLayoutTable);
    	setComponent(modoLayoutTable);
    	isRemover = true;
    			//BOTOES//
    	buttonVoltar.setVisible(false);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(false);
    	buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(true);
    	buttonDeletar.setVisible(false);
    	buttonImprimir.setVisible(false);
    }

     public void salvar(){
    	 
    	 try {
    		 if (despesaForm.getDespesas() != null) {
    			 this.despesasController.salvar(despesaForm.getDespesas());
    			  
    			 removeComponent(getComponent());
    			 addComponent(modoLayoutTable);
    			 setComponent(modoLayoutTable);
    			 MessageBox mb = new MessageBox(getWindow(), 
					"Salvar", 
                    MessageBox.Icon.INFO, 
                    "Despesa salva",  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
    			 mb.show();

    		     buttonVoltar.setVisible(false);
    		     buttonEditar.setVisible(false);
    		   	 buttonSalvar.setVisible(false);
    		     buttonClonar.setVisible(false);
    		   	 buttonAdicionar.setVisible(true);
    	    	 buttonDeletar.setVisible(false);
   		     	 buttonImprimir.setVisible(false);
    		 }
    	 }
    	 catch (Exception e) {
    		 MessageBox mb = new MessageBox(getWindow(), 
  					"Erro", 
                      MessageBox.Icon.ERROR, 
                      e.getMessage(),  
                      new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
             mb.show();
		}
     }
     
	public void visualizar(){
    	    	 
        removeComponent(getComponent());
        addComponent(visualizarView());
      	setComponent(modoLayoutView);
       	isRemover = false;
      	
		//BOTOES//
        buttonVoltar.setVisible(true);
     	buttonEditar.setVisible(true);
     	buttonSalvar.setVisible(false);
     	buttonClonar.setVisible(false);
     	buttonAdicionar.setVisible(false);
     	buttonDeletar.setVisible(true);
     	buttonImprimir.setVisible(true);
    }
	
	public void defaultTable(){
		//BeanItemContainer<DespesasInfo> beanDespesas = (BeanItemContainer<DespesasInfo>) despesas.listAll();
		tabelaFiltro.tableMain.setContainerDataSource(despesasController.getDespesa().listaBens());
		tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id", "nomeInterno", "funcionario","centroDeCusto" , "status", "dataAprovacao"});
	}
	
     public void setComponent(Component componet){
           this.modoLayoutAtivo = componet;           	 
     }
     
     public Component getComponent(){
    	  return this.modoLayoutAtivo; 
     }
}