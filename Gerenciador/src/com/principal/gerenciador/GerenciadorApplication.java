package com.principal.gerenciador;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;
import com.principal.controller.GerenciadorController;
import com.principal.model.Aplicacao;
import com.principal.view.install.EtapaUsuarioView;
import com.principal.view.install.EtapaFinal;
import com.usuario.model.Usuario;
import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

public class GerenciadorApplication extends Application implements
WizardProgressListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8386840850098025245L;
    public static Application app = null;
    private GerenciadorController gerenciador;
	VerticalLayout main = new VerticalLayout();

	private Wizard wizard;
	private VerticalLayout mainLayout;
	

    @Override
    public void init() {
  	     // setup the main window
        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        
        if(!Aplicacao.isAtivo()){
	        Window mainWindow = new Window("Persys Gerenciador");
	        mainWindow.setContent(mainLayout);
	        setMainWindow(mainWindow);
	
	        wizard = new Wizard();
	        wizard.setUriFragmentEnabled(true);
	        wizard.addListener(this);
	        wizard.addStep(new EtapaUsuarioView(), "Usuario");
	        wizard.addStep(new EtapaFinal(wizard), "Conclusão");
	        wizard.setHeight("600px");
	        wizard.setWidth("800px");
	
	        mainLayout.addComponent(wizard);
	        mainLayout.setComponentAlignment(wizard, Alignment.TOP_CENTER);
	        setTheme("demo");
        }
        else{
	  	  	setTheme("reindeermods");
	        Window mainWindow = new Window("Gerenciador", main);
	        setMainWindow(mainWindow);
	        app = this;
	        gerenciador = new GerenciadorController(mainWindow, main);
        }
 }
     
    
    @Override
    public void close(){
    super.close();
       if(gerenciador != null)
       gerenciador.logoOff();
    }
    
    public void wizardCompleted(WizardCompletedEvent event) {
        endWizard("Instalação finalizada!");
    }

    public void activeStepChanged(WizardStepActivationEvent event) {
        // display the step caption as the window title
        getMainWindow().setCaption(event.getActivatedStep().getCaption());
    }

    public void stepSetChanged(WizardStepSetChangedEvent event) {
        // NOP, not interested on this event
    }

    public void wizardCancelled(WizardCancelledEvent event) {
        endWizard("Wizard Cancelled!");
    }

    private void endWizard(String message) {
        wizard.setVisible(false);
        getMainWindow().showNotification(message);
        getMainWindow().setCaption(message);
        Button startOverButton = new Button("Iniciar Aplicação",
                new Button.ClickListener() {
                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void buttonClick(ClickEvent event) {
                        GerenciadorApplication.this.close();
                    }
                });
        mainLayout.addComponent(startOverButton);
        mainLayout.setComponentAlignment(startOverButton,
                Alignment.MIDDLE_CENTER);
    }
    
    
}
