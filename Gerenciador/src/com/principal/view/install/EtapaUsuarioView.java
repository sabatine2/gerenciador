package com.principal.view.install;


import org.vaadin.teemu.wizards.WizardStep;

import com.usuario.model.Usuario;
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

public class EtapaUsuarioView implements WizardStep {

	private Boolean permitir = false;
	private VerticalLayout content;
	
    public String getCaption() {
        return "Usuario";
    }
   
    public Component getContent() {
      
    	content = new VerticalLayout();
        content.setMargin(true);
        content.setSpacing(true);
        
        
        
    	final TextField nomeTextField = new TextField("Usuário");
    	final PasswordField senhaPasswordField = new PasswordField("Senha");
    	final Button salvarButton = new Button("Salvar");
    	
    	content.addComponent(getText());
    	content.addComponent(nomeTextField);
    	content.addComponent(senhaPasswordField);
    	content.addComponent(salvarButton);
    	
    	salvarButton.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				try{
				    Usuario usuario = new Usuario();
				    usuario.setNome(nomeTextField.getValue().toString());
				    usuario.setSenha(senhaPasswordField.getValue().toString());
				    usuario.setSenhaConfirmacao(senhaPasswordField.getValue().toString());
				    usuario.setNivel("admin");
				    usuario.salvar();
				    content.getApplication().getMainWindow()
	                .showNotification("Usuario criado com sucesso");
				    permitir = true;
					}catch (Exception e) {
						 content.getApplication().getMainWindow()
	                     .showNotification("Erro ao criar o usuario "+e.getMessage());
					}
			    
			}
		});
    	
        Embedded arrow = getArrow();
        content.addComponent(arrow);

        return content;
    }

    private Label getText() {
        return new Label(
                "<h2>Gerenciador Persys</h2><p>Início da aplicação: "
                        + "criação do usuário adminstrador.</p><p>Informe o nome e a senha do usuário adminstrador.</p>",
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
                     .showNotification("Não é permitido");
         }
    	 return permitir;
    }

    public boolean onBack() {
        return true;
    }

}