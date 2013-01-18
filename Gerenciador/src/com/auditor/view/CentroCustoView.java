package com.auditor.view;

import java.util.Iterator;
import java.util.List;

import com.abstracts.view.ViewComponenteV2;
import com.auditor.model.CentroCusto;
import com.auditor.model.Unidade;
import com.auditor.model.contato.EnderecoAuditor;
import com.auditor.view.form.CentroCustoForm;
import com.auditor.view.form.EnderecoAuditorForm;
import com.auditor.controller.CentroCustoController;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.PainelInfo;
import com.view.componente.TabelaTreeFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class CentroCustoView extends ViewComponenteV2 {

	private CentroCustoForm centroForm;
	private CentroCustoController centroController;
	private EnderecoAuditorForm enderecoForm;
	public  TabelaTreeFiltro tabelaFiltro;
	private PainelInfo painelInfo;
	public boolean isRemover;
	
	public CentroCustoView(CentroCustoController centroController){
		this.centroController = centroController;
	}
	
	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaTreeFiltro("Centro Custo");
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
					if (event.getItemId() instanceof CentroCusto) {
						centroController.visualizar(event.getItemId());
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
 							"Remover Centro de Custo?",
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
 										"Centro Custo Removido com Sucesso",  
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
					
		
			addComponent(modoTabelaFiltro());
			setComponent(modoLayoutTable);
			isRemover = true;
	}

	@Override
	public VerticalLayout modoTabelaFiltro() {
		modoLayoutTable = new VerticalLayout();
		modoLayoutTable.setHeight("500px");
		modoLayoutTable.setMargin(true);
		modoLayoutTable.addComponent(tabelaFiltro);
		return modoLayoutTable;
	}

	@Override
	public VerticalLayout modoVisualizarView() {
		VerticalLayout visualizarView = new VerticalLayout();
		painelInfo = new PainelInfo("Organizacao", centroController.organizacaoController.getOrganizacao().getId(),
				centroController.organizacaoController.getOrganizacao().getNomeFantasia(),
				centroController.organizacaoController.getOrganizacao().getDataCriacao(),
				centroController.organizacaoController.getOrganizacao().getDataUpdate(),
				"teste");
		
		centroForm = new CentroCustoForm(this.centroController.getCentro());
	    enderecoForm = new EnderecoAuditorForm(this.centroController.getCentro().getEndereco());
	
		visualizarView.addComponent(painelInfo);
		visualizarView.addComponent(centroForm);
		visualizarView.addComponent(enderecoForm);
		
		return visualizarView;
	}

	@Override
	public VerticalLayout modoAdicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    centroForm = new CentroCustoForm(this.centroController.getNovoCentro());
	    enderecoForm = new EnderecoAuditorForm(new EnderecoAuditor());

		addView.addComponent(centroForm);
		addView.addComponent(enderecoForm);
		return addView;
	}

	@Override
	public void editar() {
		
		try{
			if(this.centroController.alterar(centroForm.getCentro())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Centro Custo Alterado com Sucesso",  
                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
              	mb.show();
	 		
				buttonAdicionar.setVisible(true);
				buttonDeletar.setVisible(false);
				buttonExtra.setVisible(false);
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
	
		modoLayoutAdd = modoAdicionarView();
		if(getComponent() != null){
			removeComponent(getComponent());
		}
		addComponent(modoLayoutAdd);
		setComponent(modoLayoutAdd);
		
		buttonAdicionar.setVisible(false);
		buttonExtra.setVisible(false);
		buttonEditar.setVisible(false);
		buttonSalvar.setVisible(true);
		buttonDeletar.setVisible(false);
		buttonVoltar.setVisible(true);
	}

	@Override
	public void remover(Object target) {
		
		if (isRemover) {
			CentroCusto centro = (CentroCusto)target;
	      	this.centroController.remover(centro.getId());
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Centro de Custo?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							centroController.removerButton(centroForm.getCentro());
							removeComponent(getComponent());
							addComponent(modoLayoutTable);
							setComponent(modoLayoutTable);
							isRemover = true;
					
							buttonAdicionar.setVisible(true);
							buttonDeletar.setVisible(false);
							buttonExtra.setVisible(false);
							buttonEditar.setVisible(false);
							buttonSalvar.setVisible(false);
							buttonVoltar.setVisible(false);
							
							MessageBox mb = new MessageBox(getWindow(), 
								"Remover", 
								MessageBox.Icon.INFO, 
								"Centro Custo Removido com Sucesso",  
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
		buttonExtra.setVisible(false);
		buttonEditar.setVisible(false);
		buttonDeletar.setVisible(false);
		buttonVoltar.setVisible(false);
		
		carregaTabela();
	}

	@Override
	public void salvar() {
		try{
				if(this.centroController.salvar(centroForm.getCentro())){
					removeComponent(getComponent());
					addComponent(modoLayoutTable);
					setComponent(modoLayoutTable);
					MessageBox mb = new MessageBox(getWindow(), 
							"Cadastrar", 
	                        MessageBox.Icon.INFO, 
	                        "Centro Custo Cadastrado com Sucesso",  
	                        new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
	              	mb.show(); 
				
					buttonAdicionar.setVisible(true);
					buttonDeletar.setVisible(false);
					buttonExtra.setVisible(false);
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
	public void visualizar() {
		modoLayoutView = modoVisualizarView();
		if(getComponent() != null){
			removeComponent(getComponent());
		}
		addComponent(modoLayoutView);
		setComponent(modoLayoutView);
		isRemover = false;
		//enderecoForm.buttonOff();
		
		buttonAdicionar.setVisible(false);
		buttonDeletar.setVisible(true);
		buttonExtra.setVisible(false);
		buttonEditar.setVisible(true);
		buttonSalvar.setVisible(false);
		buttonVoltar.setVisible(true);
	}
	
	@Override
	public void carregaTabela() {
		tabelaFiltro.tableMain.removeAllItems();
		tabelaFiltro.tableMain.addContainerProperty("Id", Label.class, "");
		tabelaFiltro.tableMain.addContainerProperty("Nome", Label.class, "");
		tabelaFiltro.tableMain.addContainerProperty("Email", Label.class, "");		
		tabelaFiltro.tableMain.addContainerProperty("Endereco", Label.class, "");	
		
		List<CentroCusto> lista = (List<CentroCusto>) centroController.organizacaoController.getOrganizacao().getCentroCusto();
		
		for (Iterator<CentroCusto> iterator = lista.iterator(); iterator.hasNext();) {
			CentroCusto wo = iterator.next();
			Label lId = new Label(String.valueOf(wo.getId()));
			lId.setWidth("1px");
			
			Label lNome = new Label(wo.getNome());
			lNome.setWidth("1px");
			
			Label lEmail = new Label(wo.getEmail());
			lEmail.setWidth("1px");

			String estado = wo.getEndereco().getEstado();
			String sigla = estado.substring(0, 2);
			Label lEndereco = new Label(wo.getEndereco().getLogradouro()+" "+ wo.getEndereco().getNumero()+", "+ wo.getEndereco().getCidade()+" / "+sigla);
			lEndereco.setWidth("1px");
			
			Object centroC = tabelaFiltro.tableMain.addItem(new Object[]{lId, lNome, lEmail, lEndereco}, wo);
			
			List<Unidade> listaUnidade = (List<Unidade>) wo.getUnidade();
						
			for (int i = 0; i < wo.getUnidade().size(); i++) {
				Unidade u = listaUnidade.get(i);
				Label lUnidadeId = new Label(String.valueOf(u.getId()));
				lUnidadeId.setWidth("1px");
				
				Label lUnidadeNome = new Label(u.getNome());
				lUnidadeNome.setWidth("1px");
				
				Label lUnidadeEmail = new Label(u.getEmail());
				lUnidadeNome.setWidth("1px");
				
				Label lUnidadeEndereco = new Label(u.getEndereco().getLogradouro()+" "+ wo.getEndereco().getNumero()+", "+ wo.getEndereco().getCidade()+" / "+sigla);
				lUnidadeNome.setWidth("1px");
				
				Object unidade = tabelaFiltro.tableMain.addItem(new Object[]{lUnidadeId+"- Unidade", lUnidadeNome, lUnidadeEmail, lUnidadeEndereco}, u);
				tabelaFiltro.tableMain.setParent(unidade, centroC);
				tabelaFiltro.tableMain.setChildrenAllowed(u, false);
			}
		}
		
	}
	
	public void msgErro(Exception e){
		MessageBox mb = new MessageBox(getWindow(), 
				"Erro", 
                MessageBox.Icon.ERROR, 
                e.getMessage(),  
                new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
      	mb.show(); 
	}
	
	public CentroCustoForm getCentroForm() {
		return centroForm;
	}

	public void setCentroForm(CentroCustoForm centroForm) {
		this.centroForm = centroForm;
	}

	public EnderecoAuditorForm getEnderecoForm() {
		return enderecoForm;
	}

	public void setEnderecoForm(EnderecoAuditorForm enderecoForm) {
		this.enderecoForm = enderecoForm;
	}


}