package com.principal.view.menu;

import java.util.Iterator;

import com.principal.controller.GerenciadorController;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Button.ClickEvent;

/**
 * Classe gera o menu lateral
 * @author Ricardo
 *
 */
@SuppressWarnings("serial")
public class Menu extends HorizontalSplitPanel implements ClickListener{
	
	public Menu(GerenciadorController gerenciadorController){
		
		    //estilo e posi�‹o da barra!
	        addStyleName("small blue white");
	        setSplitPosition(16);
	        setWidth("100%");
	        setHeight("100%");
	        setMargin(true);
		        
	        //Define CSS
	        CssLayout menu = new CssLayout();
	        menu.addStyleName("menu");
	        menu.setWidth("100%");
	        setFirstComponent(menu);
    
	        //Adiciona Categoria Funcionalidades
	        Label labelFuncionalidades = new Label("Funcionalidades");
	        labelFuncionalidades.addStyleName("section");
	        menu.addComponent(labelFuncionalidades);
	       
	        if(gerenciadorController.getSession().getUsuario().getNivel().equals("admin")){
	       
	        NativeButton buttonUsuarioConectados = new NativeButton("Usuario Conectados");
	        buttonUsuarioConectados.setHeight("30px");
	        buttonUsuarioConectados.addStyleName("selected");
	        buttonUsuarioConectados.addListener(this);
	        menu.addComponent(buttonUsuarioConectados);
	     
	        AbsoluteLayout verticalLayoutMenuConectados = new AbsoluteLayout();
	    	verticalLayoutMenuConectados.setHeight("100.0%");
	    	verticalLayoutMenuConectados.setWidth("100.0%");
	    	
	    	com.usuario.controller.UsuarioController usu = new com.usuario.controller.UsuarioController();
	    	
	    	verticalLayoutMenuConectados.addComponent(usu.getConectadosView(), "top:0.0px;left:0.0px;");
	    	buttonUsuarioConectados.setData(verticalLayoutMenuConectados);
	       
	        NativeButton buttonUsuario = new NativeButton("Usuario");
	        buttonUsuario.setHeight("30px");
	        buttonUsuario.addStyleName("selected");
	        buttonUsuario.addListener(this);
	        menu.addComponent(buttonUsuario);
	      
	        AbsoluteLayout verticalLayoutMenu = new AbsoluteLayout();
	    	verticalLayoutMenu.setHeight("100.0%");
	    	verticalLayoutMenu.setWidth("100.0%");
	    	verticalLayoutMenu.addComponent(usu.getView(), "top:0.0px;left:0.0px;");
	        buttonUsuario.setData(verticalLayoutMenu);
	        }
	       
	       /* //Bot�o Configura��o do Banco
	        NativeButton buttonBanco = new NativeButton("Banco de Dados");
	        buttonBanco.setHeight("30px");
	        buttonBanco.addStyleName("selected");
	        buttonBanco.addListener(this);
	        menu.addComponent(buttonBanco);
	     
	        AbsoluteLayout verticalLayoutBanco = new AbsoluteLayout();
	    	verticalLayoutBanco.setHeight("100.0%");
	    	verticalLayoutBanco.setWidth("100.0%");
	   
	    	BancoController banco = new BancoController();
	    	
	    	verticalLayoutBanco.addComponent(banco.getView(), "top:0.0px;left:0.0px;");
	        buttonBanco.setData(verticalLayoutBanco);*/
	        
		}		
	
		//Eventos para tratar a sele�‹o entre os itens
		  @SuppressWarnings("rawtypes")
	      public void buttonClick(ClickEvent event) {
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
