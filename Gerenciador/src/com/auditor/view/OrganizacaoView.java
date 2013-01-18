package com.auditor.view;

import com.abstracts.view.ViewComponente;
import com.auditor.controller.OrganizacaoController;
import com.auditor.model.CentroCusto;
import com.auditor.model.Organizacao;
import com.auditor.model.contato.EnderecoAuditor;
import com.auditor.view.form.EnderecoAuditorForm;
import com.auditor.view.form.OrganizacaoForm;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.PainelInfo;
import com.view.componente.TabelaTreeFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class OrganizacaoView extends ViewComponente {

	private OrganizacaoForm organizacaoForm;
	private OrganizacaoController organizacaoController;
	private EnderecoAuditorForm enderecoForm;
	private PainelInfo  painelInfo;
	public TabelaTreeFiltro tabelaFiltro;
	public boolean isRemover;
		
	public OrganizacaoView(OrganizacaoController organizacaoController){
		this.organizacaoController = organizacaoController;
	}
	
	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaTreeFiltro("Organizacao");
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
					if (event.getItemId() instanceof Organizacao) {
						organizacaoController.visualizar(event.getItemId());
					}
					else if(event.getItemId() instanceof CentroCusto){
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
 							"Remover Organiza��o?",
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
 										"Organiza��o Removida com Sucesso",  
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
		painelInfo = new PainelInfo("Organizacao", organizacaoController.getOrganizacao().getId(),
				organizacaoController.getOrganizacao().getNomeFantasia(),
				organizacaoController.getOrganizacao().getDataCriacao(),
				organizacaoController.getOrganizacao().getDataUpdate(),
				"Teste");
		
		organizacaoForm = new OrganizacaoForm(this.organizacaoController.getOrganizacao());
		enderecoForm = new EnderecoAuditorForm(this.organizacaoController.getOrganizacao().getEndereco());
		this.organizacaoController.menuAuditor.moduloVisualizar();
		
		visualizarView.addComponent(painelInfo);
		visualizarView.addComponent(organizacaoForm);
		visualizarView.addComponent(enderecoForm);
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {		
		VerticalLayout addView = new VerticalLayout();
		organizacaoForm = new OrganizacaoForm(this.organizacaoController.getNovaOrganizacao());
		enderecoForm = new EnderecoAuditorForm(new EnderecoAuditor());

		addView.addComponent(organizacaoForm);
		addView.addComponent(enderecoForm);
		return addView;
	}

	@Override
	public void editar() {
		
		try{
			if(this.organizacaoController.alterar(organizacaoForm.getOrganizacao())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				organizacaoController.menuAuditor.moduloVoltar();
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Organiza��o Alterada com Sucesso",  
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
			Organizacao organizacao = (Organizacao)target;
	      	this.organizacaoController.remover(organizacao.getId());
	      	System.out.println("TESTE REMOVER EVENT");
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Organiza��o?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							organizacaoController.removerButton(organizacaoForm.getOrganizacao());
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
								"Organiza��o Removida com Sucesso",  
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
		
    	organizacaoController.menuAuditor.moduloVoltar();
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
			if(this.organizacaoController.salvar(organizacaoForm.getOrganizacao())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				organizacaoController.menuAuditor.moduloVoltar();
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
                        MessageBox.Icon.INFO, 
                        "Organiza��o Cadastrada com Sucesso",  
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
		//enderecoForm.buttonOff();
		
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

	public OrganizacaoForm getOrganizacaoForm() {
		return organizacaoForm;
	}

	public void setOrganizacaoForm(OrganizacaoForm organizacaoForm) {
		this.organizacaoForm = organizacaoForm;
	}

	public EnderecoAuditorForm getEnderecoForm() {
		return enderecoForm;
	}

	public void setEnderecoForm(EnderecoAuditorForm enderecoForm) {
		this.enderecoForm = enderecoForm;
	}
}	