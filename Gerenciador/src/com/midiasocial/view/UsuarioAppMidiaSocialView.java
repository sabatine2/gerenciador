package com.midiasocial.view;

import java.util.List;

import com.abstracts.view.ViewComponente;
import com.midiasocial.controller.UsuarioAppMidiaSocialController;
import com.midiasocial.model.AplicacaoMidiaSocial;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.view.form.UsuarioAppMidiaSocialForm;
import com.midiasocial.view.oauth.FacebookButton;
import com.midiasocial.view.oauth.TwitterButton;
import com.midiasocial.view.oauth.OAuthButton.OAuthListener;
import com.midiasocial.view.oauth.OAuthButton.User;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access", "rawtypes"})
public class UsuarioAppMidiaSocialView extends ViewComponente {

	public UsuarioAppMidiaSocialController userController;
	public UsuarioAppMidiaSocialForm userForm;
	public com.view.componente.TabelaFiltro tabelaFiltro;
	public boolean isRemover;
	private User user;
	private Button buttonConectar;
	private ComboBox cbAplicacao;
	
	public UsuarioAppMidiaSocialView(UsuarioAppMidiaSocialController userController){
		this.userController = userController;
	}

	@Override
	public void modoTabela() {
		
		tabelaFiltro = new TabelaFiltro("Usuario");
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
					if (event.getItemId() instanceof UsuarioAppMidiaSocial) {
						userController.visualizar(event.getItemId());
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
 							"Remover Usuario?",
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
 										"Usuario Removido",  
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
	    userForm = new UsuarioAppMidiaSocialForm(this.userController.getUsuario());
		visualizarView.addComponent(userForm);
		return visualizarView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		VerticalLayout addView = new VerticalLayout();
	    userForm = new UsuarioAppMidiaSocialForm(this.userController.getNovoUsuario());
	    addView.addComponent(connectPanel());
		addView.addComponent(userForm);
		return addView;
	}

	@Override
	public void editar() {

		try{
			if(this.userController.alterar(userForm.getUser())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				isRemover = true;
				MessageBox mb = new MessageBox(getWindow(), 
						"Alterar", 
                        MessageBox.Icon.INFO, 
                        "Usuario Alterado",  
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
		userForm.fieldOff();
		
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
			UsuarioAppMidiaSocial user = ( UsuarioAppMidiaSocial )target;
			System.out.println("remover target" + " " +user.getIdInterno());
	      	this.userController.remover(user.getIdInterno());
		}
		else {
			try{
				MessageBox mb = new MessageBox(getWindow(), 
					"Remover", 
					MessageBox.Icon.QUESTION, 
					"Remover Usuario?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
				mb.show(new MessageBox.EventListener() {	 
				
					public void buttonClicked(ButtonType buttonType) {	
						if (buttonType.equals(buttonType.YES)) {
							
							userController.removerButton(userForm.getUser());
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
								"Usuario Removido",  
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
			userForm.getUser().setTokenAccess(user.getToken().toString());
			userForm.getUser().setTokenAccessSecret(user.getTokenSecret());
			userForm.getUser().setIdMidia(user.getId());
			userForm.getUser().setFotoUrl(user.getPictureUrl());
			userForm.getUser().setAppMidiaSocial((AplicacaoMidiaSocial) cbAplicacao.getValue());
			
			
			if(this.userController.salvar(userForm.getUser())){
				removeComponent(getComponent());
				addComponent(modoLayoutTable);
				setComponent(modoLayoutTable);
				MessageBox mb = new MessageBox(getWindow(), 
						"Cadastrar", 
                        MessageBox.Icon.INFO, 
                        "Usuario Cadastrado",  
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
			msgErro("Erro ao salvar " +e.getMessage());
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
		userForm.fieldOn();
		
		if (userController.getUsuario().getAppMidiaSocial().getRedeSocial().equals("Facebook")) {
			userForm.facebookMode();
		}
		
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
	
	private VerticalLayout connectPanel(){
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setMargin(true);
		verticalLayout.setSpacing(true);
		
		Panel panelConectar = new Panel();
		panelConectar.setCaption("Conectar");
		panelConectar.setImmediate(true);
		panelConectar.setWidth("500px");
		panelConectar.setHeight("80px");
		
		AbsoluteLayout layoutPanel = new AbsoluteLayout();
		layoutPanel.setImmediate(false);
		layoutPanel.setWidth("100.0%");
		layoutPanel.setHeight("100.0%");
		layoutPanel.setMargin(false);
		
		//cbAplicacao
		cbAplicacao = new ComboBox("Aplicacao");
		cbAplicacao.setWidth("350px");
		cbAplicacao.setImmediate(true);
		cbAplicacao.setContainerDataSource(listaApp());
		cbAplicacao.setItemCaptionPropertyId("nome");
		cbAplicacao.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
		cbAplicacao.setNullSelectionAllowed(false);
		layoutPanel.addComponent(cbAplicacao, "top:20.0px;left:15.0px;");
		
		// buttonConectar
		buttonConectar = new Button("Conectar", new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				
				AplicacaoMidiaSocial app = (AplicacaoMidiaSocial) cbAplicacao.getValue();
				
				if(app.getRedeSocial().equals("Facebook")){
					new FacebookButton(app.getApiKey(), app.getApiSecret(), oauthListener,buttonConectar);
				}	
				
				if(app.getRedeSocial().equals("Twitter")){
					new TwitterButton(app.getApiKey(), app.getApiSecret(), oauthListener,buttonConectar);
				}
			}
		});
		buttonConectar.setImmediate(false);
		buttonConectar.setWidth("-1px");
		buttonConectar.setHeight("-1px");
		
		layoutPanel.addComponent(buttonConectar, "top:17.0px;left:400.0px;");
		panelConectar.setContent(layoutPanel);
		verticalLayout.addComponent(panelConectar);
		return verticalLayout;
	}

	private BeanItemContainer listaApp(){
	
		BeanItemContainer<AplicacaoMidiaSocial> beanItem = new BeanItemContainer<AplicacaoMidiaSocial>(AplicacaoMidiaSocial.class);
		List listApp = AplicacaoMidiaSocial.listaApp();
		for (int i = 0; i < listApp.size(); i++) {
		
			beanItem.addItem(listApp.get(i));
		}
		return beanItem;
	}
	
	public OAuthListener oauthListener = new OAuthListener() {
		 
        public void failed(String reason) {
              System.out.print("Login failed");
        }

		public void userAuthenticated(User user) {
			UsuarioAppMidiaSocialView.this.user = user;
			
			userForm.getUserInfo(user.getName(), user.getScreenName());
		}
    };
}