package com.principal.view.install;


import org.vaadin.teemu.wizards.WizardStep;

import com.exception.DAOException;
import com.principal.helper.AplicacaoHelper;
import com.principal.model.Aplicacao;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

public class EtapaBancoDadosView implements WizardStep {

	private Boolean permitir = false;
	private VerticalLayout content;
	
    public String getCaption() {
        return "Banco de Dados";
    }
   
    public Component getContent() {
      
    	content = new VerticalLayout();
        content.setMargin(true);
        content.setSpacing(true);
        
        
        final TextField urlTextField = new TextField("URL");
    	final TextField userTextField = new TextField("Usuario");
    	final PasswordField senhaPasswordField = new PasswordField("Senha");
    	final Button salvarButton = new Button("Salvar");
    	
    	content.addComponent(getText());
    	content.addComponent(urlTextField);
    	content.addComponent(userTextField);
    	content.addComponent(senhaPasswordField);
    	content.addComponent(salvarButton);
    	
    	salvarButton.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				try{
				    Aplicacao app = new Aplicacao();
				    app.setUrl(urlTextField.getValue().toString());
				    app.setUsuarioBanco(userTextField.getValue().toString());
				    app.setSenhaBanco(senhaPasswordField.getValue().toString());
				    try{
				    	app.testaConexao();
				    	if(app.getSucessoBanco()){
						    	AplicacaoHelper.APLICACAO = app;
						 permitir = true;  
						}
					}catch (DAOException e) {
						MessageBox mb = new MessageBox(content.getWindow(), 
									"Falha", 
									MessageBox.Icon.INFO, 
									e.getMessage(),  
									new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
								mb.show();
					}
				}catch (Exception e) {
					MessageBox mb = new MessageBox(content.getWindow(), 
							"Falha", 
							MessageBox.Icon.INFO, 
							e.getMessage(),  
							new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
						mb.show();
				}
			}
		});
    	
        Embedded arrow = getArrow();
        content.addComponent(arrow);

        return content;
    }

    private Label getText() {
        return new Label(
                "<h2>Gerenciador</h2><p>Inicio da aplicacao: "
                        + "criacao da conexao com o banco de dados adminstrador.</p><p>Informe o nome e a senha do usuario adminstrador.</p>",
                Label.CONTENT_XHTML);
    }

  

    private Embedded getArrow() {
        Embedded arrow = new Embedded("", new ThemeResource(
                "img/arrow-down.png"));
        arrow.setStyleName("intro-arrow");
        return arrow;
    }

    public boolean onAdvance() {
    	 if (!permitir) {
            content.getApplication().getMainWindow()
                     .showNotification("Nao permitido");
         }
    	 return permitir;
    }

    public boolean onBack() {
        return true;
    }

}