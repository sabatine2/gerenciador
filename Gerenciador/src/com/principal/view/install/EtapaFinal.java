package com.principal.view.install;

import java.util.Date;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;

import com.principal.model.Aplicacao;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class EtapaFinal implements WizardStep {

    private Wizard owner;
    private VerticalLayout content;
    
    public EtapaFinal(Wizard owner) {
        this.owner = owner;
    }

    public String getCaption() {
        return "Conclusão";
    }

    
    public Component getContent() {
        content = new VerticalLayout();
    	content.addComponent(getText());
        return content;
    }
    
    private Label getText() {
        return new Label(
                "<h2>Persys TI</h2><p>O procedimento de instalação foi concluído"
                        + "com sucesso</p><p>Agradecemos!</p><h3>Informações adicionais</h3>"
                        + "<ul><li><a href=\"http://www.persys.com.br\">Persys</a> Acesse o site da Persys </li></ul>",
                Label.CONTENT_XHTML);
    }

    public boolean gerarAplicacao(){
    	try{
	    	Aplicacao app = new Aplicacao();
	    	app.setAtivo(true);
	    	app.setDataCriacao(new Date());
	    	app.geraArquivo(app, "app.xml");
	    	content.getApplication().getMainWindow()
              .showNotification("Fim da instalação!");
			  
	    	return true;
    	}
    	catch(Exception e){ 
    		content.getApplication().getMainWindow()
            .showNotification("Erro ao concluir");
    		return false;
    	} 
    }

    public boolean onAdvance() {
    	return gerarAplicacao();
    }

    public boolean onBack() {
        return false;
    }
}