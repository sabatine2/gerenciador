package com.midiasocial.view;

import java.util.Date;

import com.midiasocial.controller.AplicacaoMidiaSocialController;
import com.midiasocial.model.AplicacaoMidiaSocial;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.TabelaFiltro;
import com.abstracts.view.ViewComponente;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class AplicacaoMidiaSocialView extends ViewComponente {

	public AplicacaoMidiaSocialController appController;
	public TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	private ComboBox cbRedeSocial;
	private TextField textFieldApiSecret;
	private TextField textFieldApiID;
	private AplicacaoMidiaSocial app;
	private TextField txtNome;
	
	public AplicacaoMidiaSocialView(AplicacaoMidiaSocialController appController){
		this.appController = appController;
	}

	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Aplicações");
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
					if (event.getItemId() instanceof AplicacaoMidiaSocial) {
						appController.visualizar(event.getItemId());
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
 							"Remover Aplicação?",
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
 										"Aplicação Removida",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 									System.out.println("RESULTADO TARGET: "+target);
 								} catch (Exception e) {
 									msgErro("remover.view: "+e.getMessage() + isRemover);
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
	    visualizarView.addComponent(appPanel());
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    addView.addComponent(appPanel());
		return addView;
	}

	@Override
	public void editar() {

		try{
			if(this.appController.alterar(getApp())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Aplicação Alterada",  
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
	    appController.setApp(null);
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
			AplicacaoMidiaSocial app = ( AplicacaoMidiaSocial )target;
			System.out.println("remover target" + " " +app.getId());
	      	this.appController.remover(app.getId());
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Aplicação?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							appController.removerButton(appController.getApp());
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
								"Aplicação Removida",  
								new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
							mb.show();
						}
						else {}
					}
				});
			}catch(Exception e){	
				msgErro("remover.view.button: "+e.getMessage());
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
			if(this.appController.salvar(getApp())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
                        MessageBox.Icon.INFO, 
                        "Aplicação Cadastrada",  
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
			msgErro("Conecte-se antes de salvar");
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
		textFieldApiID.setValue(dados[0]);
		textFieldApiSecret.setValue(dados[1]);
		txtNome.setValue(dados[2]);
		cbRedeSocial.setValue(dados[3]);
		
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
	
	private VerticalLayout appPanel(){
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setMargin(true);
		verticalLayout.setSpacing(true);
		
		Panel panelConectar = new Panel();
		panelConectar.setCaption("Cadastrar Aplicação");
		panelConectar.setImmediate(true);
		panelConectar.setWidth("500px");
		panelConectar.setHeight("130px");
		
		AbsoluteLayout layoutPanel = new AbsoluteLayout();
		layoutPanel.setImmediate(false);
		layoutPanel.setWidth("100.0%");
		layoutPanel.setHeight("100.0%");
		layoutPanel.setMargin(false);
		
		//textFieldApiID
		textFieldApiID = new TextField("API ID");
		textFieldApiID.setWidth("165px");
		textFieldApiID.setValue("378193395598785");
		textFieldApiID.setImmediate(true);
		textFieldApiID.setRequired(true);
		layoutPanel.addComponent(textFieldApiID, "top:27.0px;left:15.0px;");
		
		//textFieldApiSecret
		textFieldApiSecret = new TextField("API Secret");
		textFieldApiSecret.setWidth("288px");
		textFieldApiSecret.setImmediate(true);
		textFieldApiSecret.setValue("5744bd8cf01dd161f848ca160d18d8a3");
		textFieldApiSecret.setRequired(true);
		layoutPanel.addComponent(textFieldApiSecret, "top:27.0px;left:195.0px;");
		
		//cbRedeSocial
		cbRedeSocial = new ComboBox("Rede Social");
		cbRedeSocial.setWidth("165px");
		cbRedeSocial.setImmediate(true);
		cbRedeSocial.addItem("Facebook");
		cbRedeSocial.addItem("LinkedIn");
		cbRedeSocial.addItem("Twitter");
		cbRedeSocial.setNullSelectionAllowed(false);
		cbRedeSocial.setRequired(true);
		layoutPanel.addComponent(cbRedeSocial, "top:75.0px;left:15.0px;");
		
		txtNome = new TextField("Nome");
		txtNome.setWidth("288px");		
		txtNome.setRequired(true);
		layoutPanel.addComponent(txtNome, "top:73.0px;left:195.0px;");
		
		panelConectar.setContent(layoutPanel);
		verticalLayout.addComponent(panelConectar);
		return verticalLayout;
	}
	
	/*public OAuthListener oauthListener = new OAuthListener() {
		 
        public void failed(String reason) {
              System.out.print("Login failed");
        }

		public void userAuthenticated(User user) {
			this.user = user;
			
			userForm.getUserInfo(user.getName(), user.getScreenName());
		}
    };*/
	
	private AplicacaoMidiaSocial getApp(){
		
		app = appController.getApp();
		app.setApiKey(textFieldApiID.getValue().toString());
		app.setApiSecret(textFieldApiSecret.getValue().toString());
		app.setNome(txtNome.getValue().toString());
		app.setRedeSocial(cbRedeSocial.getValue().toString());
		app.setDataCriacao(new Date());
		
		return app;
	}
}