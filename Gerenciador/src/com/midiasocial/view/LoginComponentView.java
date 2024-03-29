package com.midiasocial.view;

import java.util.Iterator;
import java.util.List;

import com.abstracts.view.ViewComponente;
import com.midiasocial.view.oauth.FacebookButton;
import com.midiasocial.view.oauth.OAuthButton.OAuthListener;
import com.midiasocial.view.oauth.OAuthButton.User;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Comment;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.Post;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings({"serial", "unused"})
public class LoginComponentView extends ViewComponente {
	
	private Panel panelConectar;
	private AbsoluteLayout layoutPanel;
	private Button buttonConectar;
	private TextField textFieldApiID;
	private TextField textFieldApiSecret;
	private VerticalLayout visualizarView;

	public LoginComponentView() {
		
		// panelConectar
		panelConectar = buildPanelConectar();
		visualizarView = new VerticalLayout();
		visualizarView.setMargin(true);
		visualizarView.setSpacing(true);
		visualizarView.addComponent(panelConectar);
		addComponent(visualizarView);
	}

	@AutoGenerated
	private Panel buildPanelConectar() {
		// common part: create layout
		panelConectar = new Panel();
		panelConectar.setCaption("Conectar");
		panelConectar.setImmediate(false);
		panelConectar.setWidth("500px");
		panelConectar.setHeight("130px");
		
		// layoutPanel
		layoutPanel = buildLayoutPanel();
		panelConectar.setContent(layoutPanel);
		
		return panelConectar;
	}

	@AutoGenerated
	private AbsoluteLayout buildLayoutPanel() {
		// common part: create layout
		layoutPanel = new AbsoluteLayout();
		layoutPanel.setImmediate(false);
		layoutPanel.setWidth("100.0%");
		layoutPanel.setHeight("100.0%");
		layoutPanel.setMargin(false);
		
		//textFieldApiID
		textFieldApiID = new TextField();
		textFieldApiID.setCaption("API ID");
		textFieldApiID.setWidth("165px");
		textFieldApiID.setValue("");
		textFieldApiID.setImmediate(true);
		layoutPanel.addComponent(textFieldApiID, "top:27.0px;left:15.0px;");
		
		//textFieldApiSecret
		textFieldApiSecret = new TextField();
		textFieldApiSecret.setCaption("API Secret");
		textFieldApiSecret.setWidth("288px");
		textFieldApiSecret.setImmediate(true);
		textFieldApiSecret.setValue("");
		layoutPanel.addComponent(textFieldApiSecret, "top:27.0px;left:195.0px;");
		
		// buttonConectar
		buttonConectar = new Button("Conectar", new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				new FacebookButton(textFieldApiID.getValue().toString().trim(), textFieldApiSecret.getValue().toString().trim(), oauthListener,buttonConectar);
			}
		});
		buttonConectar.setImmediate(false);
		buttonConectar.setWidth("-1px");
		buttonConectar.setHeight("-1px");
		
		
		layoutPanel.addComponent(buttonConectar, "top:72.0px;left:15.0px;");
		
		return layoutPanel;
	}
	
	public OAuthListener oauthListener = new OAuthListener() {
		 
        public void failed(String reason) {
              System.out.print("Login failed");
        }

		@Override
		public void userAuthenticated(User user) {
		 
		}
    };

	@Override
	public void modoTabela() {
		
	}

	@Override
	public VerticalLayout modotabelaFiltro() {
		return null;
	}

	@Override
	public VerticalLayout modovisualizarView() {
		
		return null;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		return null;
	}

	@Override
	public void editar() {
		
	}

	@Override
	public void adicionar() {
		
	}

	@Override
	public void remover(Object target) {
		
	}

	@Override
	public void voltar() {
		
	}

	@Override
	public void salvar() {
		
	}

	@Override
	public void visualizar(Object[] dados) {
		
	}
}
