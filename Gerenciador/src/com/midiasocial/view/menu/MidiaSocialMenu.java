package com.midiasocial.view.menu;

import java.util.Iterator;

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
	
	public MidiaSocialMenu(MidiaSocialMenuController midiaSocialMenuController){
		
		//Estilo e posi��o da barra
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
        Label labelFuncionalidades = new Label("Aplicacoes");
        labelFuncionalidades.addStyleName("section");
        menu.addComponent(labelFuncionalidades);
        
        //Botï¿œo App
        NativeButton buttonApp = new NativeButton("Lista Aplicações");
        buttonApp.setHeight("30px");
        buttonApp.addStyleName("selected");
        buttonApp.addListener(this);
        menu.addComponent(buttonApp);
     
        AbsoluteLayout layoutApp = new AbsoluteLayout();
    	layoutApp.setHeight("100.0%");
    	layoutApp.setWidth("100.0%");
    	
    	AplicacaoMidiaSocialController appController = new AplicacaoMidiaSocialController();
    	
    	layoutApp.addComponent(appController.getView(), "top:0.0px;left:0.0px;");
        buttonApp.setData(layoutApp);
        
        //Categoria Usuario
        Label labelUsuarioApp = new Label("Usuario Aplicacao");
        labelUsuarioApp.addStyleName("section");
        menu.addComponent(labelUsuarioApp);
        
        //Botï¿œo Lista Usuario
        NativeButton buttonListaUsuarioApp = new NativeButton("Usuario Aplicacao");
        buttonListaUsuarioApp.setHeight("30px");
        buttonListaUsuarioApp.addStyleName("selected");
        buttonListaUsuarioApp.addListener(this);
        menu.addComponent(buttonListaUsuarioApp);
        
        AbsoluteLayout layoutUsuarioApp = new AbsoluteLayout();
    	layoutUsuarioApp.setHeight("100.0%");
    	layoutUsuarioApp.setWidth("100.0%");
    	
    	UsuarioAppMidiaSocialController usuarioController = new UsuarioAppMidiaSocialController();
    	
    	layoutUsuarioApp.addComponent(usuarioController.getView(), "top:0.0px;left:0.0px;");
        buttonListaUsuarioApp.setData(layoutUsuarioApp);
        
        
        //Categoria Usuario
        Label labelUsuarioPub = new Label("Usuario Publicacao");
        labelUsuarioPub.addStyleName("section");
        menu.addComponent(labelUsuarioPub);
        
        //Botï¿œo Lista Usuario
        NativeButton buttonListaUsuarioPub = new NativeButton("Usuario Publicacao");
        buttonListaUsuarioPub.setHeight("30px");
        buttonListaUsuarioPub.addStyleName("selected");
        buttonListaUsuarioPub.addListener(this);
        menu.addComponent(buttonListaUsuarioPub);
        
        AbsoluteLayout layoutUsuarioPub = new AbsoluteLayout();
    	layoutUsuarioPub.setHeight("100.0%");
    	layoutUsuarioPub.setWidth("100.0%");
    	
    	UsuarioPubMidiaSocialController usuarioPubController = new UsuarioPubMidiaSocialController();
    	
    	layoutUsuarioPub.addComponent(usuarioPubController.getView(), "top:0.0px;left:0.0px;");
        buttonListaUsuarioPub.setData(layoutUsuarioPub);
        
        //Categoria Home
        Label labelHome = new Label("Home");
        labelHome.addStyleName("section");
        menu.addComponent(labelHome);
        
        //Botï¿œo Page
        NativeButton buttonPage = new NativeButton("Pagina");
        buttonPage.setHeight("30px");
        buttonPage.addStyleName("selected");
        buttonPage.addListener(this);
        menu.addComponent(buttonPage);
        
        AbsoluteLayout layoutPage = new AbsoluteLayout();
    	layoutPage.setHeight("100.0%");
    	layoutPage.setWidth("100.0%");
    	
    	PaginaController pageController = new PaginaController();
    	
    	layoutPage.addComponent(pageController.getView(), "top:0.0px;left:0.0px;");
        buttonPage.setData(layoutPage);
        
        //Categoria Pesquisar
        Label labelPesquisar = new Label("Pesquisar");
        labelPesquisar.addStyleName("section");
        menu.addComponent(labelPesquisar);
        
        //Botï¿œo Busca Manual
        NativeButton buttonBuscaManual = new NativeButton("Pesquisa");
        buttonBuscaManual.setHeight("30px");
        buttonBuscaManual.addStyleName("selected");
        buttonBuscaManual.addListener(this);
        menu.addComponent(buttonBuscaManual);
        
        AbsoluteLayout layoutBuscaManual = new AbsoluteLayout();
    	layoutBuscaManual.setHeight("100.0%");
    	layoutBuscaManual.setWidth("100.0%");
    	
    	PesquisaManualController manualController = new PesquisaManualController();
    	
    	layoutBuscaManual.addComponent(manualController.getView(), "top:0.0px;left:0.0px;");
        buttonBuscaManual.setData(layoutBuscaManual);
        
        //Categoria Tï¿œpico
        Label labelTopico = new Label("Criterio");
        labelTopico.addStyleName("section");
        menu.addComponent(labelTopico);
        
        //Botï¿œo Cadastrar Tï¿œpico
        NativeButton buttonTopico = new NativeButton("Criterio");
        buttonTopico.setHeight("30px");
        buttonTopico.addStyleName("selected");
        buttonTopico.addListener(this);
        menu.addComponent(buttonTopico);
        
        AbsoluteLayout layoutTopico = new AbsoluteLayout();
    	layoutTopico.setHeight("100.0%");
    	layoutTopico.setWidth("100.0%");
    	
    	CriterioController topicoController = new CriterioController();
    	
    	layoutTopico.addComponent(topicoController.getView(), "top:0.0px;left:0.0px;");
        buttonTopico.setData(layoutTopico);
        
      //Categoria Servico
        Label labelServico = new Label("Servico");
        labelServico.addStyleName("section");
        menu.addComponent(labelServico);
        
        //Bot�o Cadastrar T�pico
        NativeButton buttonServico = new NativeButton("Servico");
        buttonServico.setHeight("30px");
        buttonServico.addStyleName("selected");
        buttonServico.addListener(this);
        menu.addComponent(buttonServico);
        
        AbsoluteLayout layoutServico = new AbsoluteLayout();
        layoutServico.setHeight("100.0%");
        layoutServico.setWidth("100.0%");
    	
    	ServicoController servicoController = new ServicoController(midiaSocialMenuController.getGerenciadorController().getSession().getUsuario());
    	
    	layoutServico.addComponent(servicoController.getView(), "top:0.0px;left:0.0px;");
    	buttonServico.setData(layoutServico);
      
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
        
        setSecondComponent((Component) event.getButton()
                .getData());
	}
}
