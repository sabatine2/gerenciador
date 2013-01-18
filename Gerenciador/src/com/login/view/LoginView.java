package com.login.view;

import com.login.controller.LoginController;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class LoginView extends Window{

	private VerticalLayout layoutMain;
	private Button buttonEntrar;
	private PasswordField passwordFieldSenha;
	private TextField textFieldEmail;
	private LoginController loginController;
	public Component      componetAtivo;

	public LoginView(LoginController loginController) {
		this.loginController = loginController;
	
		setCaption("Login");
		setModal(true);
		setWidth("350px");
		setHeight("230px");
		setClosable(false);
		VerticalLayout layout = (VerticalLayout) getContent();
		layout.addComponent(buildLayout());
		setComponent(layoutMain);
	}

	public VerticalLayout buildLayout(){

		layoutMain = new VerticalLayout();	
		layoutMain.setImmediate(false);
		layoutMain.setWidth("100%");
		layoutMain.setHeight("100%");
		layoutMain.setMargin(true);
		layoutMain.setSpacing(true);


		// textFieldEmail
		textFieldEmail = new TextField();
		textFieldEmail.setImmediate(false);
		textFieldEmail.setWidth("270px");
		textFieldEmail.setHeight("-1px");
		textFieldEmail.setInputPrompt("Digite seu Email");
		layoutMain.addComponent(textFieldEmail);
		layoutMain.setComponentAlignment(textFieldEmail, new Alignment(48));

		// passwordFieldSenha
		passwordFieldSenha = new PasswordField();
		passwordFieldSenha.setImmediate(false);
		passwordFieldSenha.setWidth("270px");
		passwordFieldSenha.setHeight("-1px");
		passwordFieldSenha.setInputPrompt("123456");
		layoutMain.addComponent(passwordFieldSenha);
		layoutMain.setComponentAlignment(passwordFieldSenha, new Alignment(48));

		// buttonEntrar
		buttonEntrar = new Button("Entrar",new Button.ClickListener() {
			// inline click-listener
			public void buttonClick(ClickEvent event) {
				validar();
			}
		});
		buttonEntrar.setImmediate(false);
		buttonEntrar.setWidth("-1px");
		buttonEntrar.setHeight("-1px");
		layoutMain.addComponent(buttonEntrar);
		layoutMain.setComponentAlignment(buttonEntrar, new Alignment(48));

		return	layoutMain;
	}	

	public void validar(){

		if (textFieldEmail.getValue().equals("")) {
			getWindow().showNotification("Erro", "Digite o e-mail", Notification.TYPE_ERROR_MESSAGE);
		}
		
		else if (passwordFieldSenha.getValue().equals("")) {
			getWindow().showNotification("Erro", "Digite a senha", Notification.TYPE_ERROR_MESSAGE);
		}

		else{

			String nome = (String) textFieldEmail.getValue();
			try {
				nome = nome.trim();

			} catch (Exception e) {
				getWindow().showNotification("Erro", "E-mail incorreto", Notification.TYPE_ERROR_MESSAGE);
			}
			
			WebBrowser web = (WebBrowser) getApplication().getMainWindow().getTerminal();
			String url =" "; 
			try{
			   url = web.getAddress();
			}catch (Exception e) {
			}
			
		    Object[] dados = new Object[7];
			dados[0] = nome;
			dados[1] = passwordFieldSenha.getValue();
            dados[2] = url;
            try{
            if( web.isMacOSX())
               dados[3] = "MacOSX";
            if( web.isWindows())
            	dados[3] = "Windows";
            if( web.isLinux() )
            	dados[3] = "Linux";
			if( web.isTouchDevice())
                dados[3] = "Tablet";
            }catch (Exception e) {
				
			}
            try{
                if( web.isFirefox())
                   dados[4] = "Firefox";
                if( web.isChrome())
                	dados[4] = "Chrome";
                if( web.isIE())
                	dados[4] = "IE";
    			if( web.isSafari())
                    dados[4] = "Safari";
                }catch (Exception e) {
    				
    			}
            try{
             dados[5] = web.getScreenHeight();
             dados[6] = web.getScreenWidth();
            }catch (Exception e) {
            	 dados[5] =0;
            	 dados[6] =0;
			}
			
            if(!this.loginController.valida(dados))
				getWindow().showNotification("Erro", "E-mail ou senha incorreta", Notification.TYPE_ERROR_MESSAGE);

			else
				getWindow().showNotification("Login", "Bem-Vindo", Notification.TYPE_TRAY_NOTIFICATION);
		}	
   }	

	public void setComponent(Component componentAtivo){
		this.componetAtivo = componentAtivo;
	}
	
    public Component getComponent(){
	return this.componetAtivo; 
    }
}
