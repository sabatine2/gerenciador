package com.midiasocial.view.menu;

import java.util.Iterator;

import com.abstracts.controller.Controller;
import com.midiasocial.controller.AplicacaoMidiaSocialController;
import com.midiasocial.controller.CriterioController;
import com.midiasocial.controller.MidiaSocialMenuController;
import com.midiasocial.controller.PaginaController;
import com.midiasocial.controller.PesquisaManualController;
import com.midiasocial.controller.ServicoController;
import com.midiasocial.controller.UsuarioAppMidiaSocialController;
import com.midiasocial.controller.UsuarioPubMidiaSocialController;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.NativeButton;

@SuppressWarnings("serial")
public class MidiaSocialMenu extends HorizontalSplitPanel implements ClickListener{
	
	private CssLayout menu;
	private MidiaSocialMenuController midiaSocialMenuController;
	private AbsoluteLayout layoutApp;
	
	public MidiaSocialMenu(MidiaSocialMenuController midiaSocialMenuController){
		
		layoutApp = new AbsoluteLayout();
		layoutApp.setHeight("100.0%");
		layoutApp.setWidth("100.0%");
		  
		//Estilo e posiÔøΩÔøΩo da barra
	    addStyleName("small blue white");
	    setSplitPosition(16);
	    setWidth("100%");
	    setHeight("100%");
	    setMargin(true);
	        
	    //Define CSS
	    menu = new CssLayout();
	    menu.addStyleName("menu");
	    menu.setWidth("100%");
	    setFirstComponent(menu);
	    
	    //Categoria App
        Label labelFuncionalidades = new Label("Aplicacões");
        labelFuncionalidades.addStyleName("section");
        menu.addComponent(labelFuncionalidades);
        
        //Bot√Ø¬ø≈ìo App
        NativeButton buttonApp = new NativeButton("Aplicacões");
        buttonApp.setHeight("30px");
        buttonApp.addListener(this);
        menu.addComponent(buttonApp);
     
        AplicacaoMidiaSocialController appController = new AplicacaoMidiaSocialController();
        appController.carregaControle();
        buttonApp.setData(appController);
        
        layoutApp.addComponent(appController.getView(), "top:0.0px;left:0.0px;");
        setSecondComponent(layoutApp);
        
        //Categoria Usuario
        Label labelUsuarioApp = new Label("Usuario Aplicação");
        labelUsuarioApp.addStyleName("section");
        menu.addComponent(labelUsuarioApp);
        
        //Bot√Ø¬ø≈ìo Lista Usuario
        NativeButton buttonListaUsuarioApp = new NativeButton("Usuario Aplicação");
        buttonListaUsuarioApp.setHeight("30px");
        buttonListaUsuarioApp.addListener(this);
        menu.addComponent(buttonListaUsuarioApp);
        UsuarioAppMidiaSocialController usuarioController = new UsuarioAppMidiaSocialController();
    	buttonListaUsuarioApp.setData(usuarioController);
        
        
        //Categoria Usuario
        Label labelUsuarioPub = new Label("Usuario Publicação");
        labelUsuarioPub.addStyleName("section");
        menu.addComponent(labelUsuarioPub);
        
        //Bot√Ø¬ø≈ìo Lista Usuario
        NativeButton buttonListaUsuarioPub = new NativeButton("Usuario Publicação");
        buttonListaUsuarioPub.setHeight("30px");
        buttonListaUsuarioPub.addListener(this);
        menu.addComponent(buttonListaUsuarioPub);
        UsuarioPubMidiaSocialController usuarioPubController = new UsuarioPubMidiaSocialController();
    	buttonListaUsuarioPub.setData(usuarioPubController);
        
        //Categoria Home
        Label labelHome = new Label("Home");
        labelHome.addStyleName("section");
        menu.addComponent(labelHome);
        
        //Bot√Ø¬ø≈ìo Page
        NativeButton buttonPage = new NativeButton("Página");
        buttonPage.setHeight("30px");
        buttonPage.addListener(this);
        menu.addComponent(buttonPage);
        PaginaController pageController = new PaginaController();
    	buttonPage.setData(pageController);
        
        //Categoria Pesquisar
        Label labelPesquisar = new Label("Pesquisar");
        labelPesquisar.addStyleName("section");
        menu.addComponent(labelPesquisar);
        
        //Bot√Ø¬ø≈ìo Busca Manual
        NativeButton buttonBuscaManual = new NativeButton("Pesquisa");
        buttonBuscaManual.setHeight("30px");
        buttonBuscaManual.addListener(this);
        menu.addComponent(buttonBuscaManual);
        
        PesquisaManualController manualController = new PesquisaManualController();
    	buttonBuscaManual.setData(manualController);
        
        //Categoria T√Ø¬ø≈ìpico
        Label labelTopico = new Label("Criterio");
        labelTopico.addStyleName("section");
        menu.addComponent(labelTopico);
        
        //Bot√Ø¬ø≈ìo Cadastrar T√Ø¬ø≈ìpico
        NativeButton buttonTopico = new NativeButton("Criterio");
        buttonTopico.setHeight("30px");
        buttonTopico.addListener(this);
        menu.addComponent(buttonTopico);
        CriterioController topicoController = new CriterioController();
    	buttonTopico.setData(topicoController);
        
        //Categoria Servico
        Label labelServico = new Label("Serviço");
        labelServico.addStyleName("section");
        menu.addComponent(labelServico);
        
        //BotÔøΩo Cadastrar TÔøΩpico
        NativeButton buttonServico = new NativeButton("Serviço");
        buttonServico.setHeight("30px");
        buttonServico.addListener(this);
        menu.addComponent(buttonServico);
        
    	ServicoController servicoController = new ServicoController(midiaSocialMenuController.getGerenciadorController().getSession().getUsuario());
    	buttonServico.setData(servicoController);
      
	}
	
	@SuppressWarnings("rawtypes")
	public void buttonClick(ClickEvent event) {
		System.gc();
		
		ComponentContainer p = (ComponentContainer) event.getButton()
                .getParent();
        for (Iterator iterator = p.getComponentIterator(); iterator
                .hasNext();) {
            ((AbstractComponent) iterator.next())
                    .removeStyleName("selected");
        }
        event.getButton().addStyleName("selected");
        
        Controller controler = (Controller) event.getButton().getData();
	    controler.carregaControle();
	    
	    layoutApp.removeAllComponents();
	    layoutApp.addComponent(controler.getView(), "top:0.0px;left:0.0px;");
        setSecondComponent(layoutApp);
	}
}
