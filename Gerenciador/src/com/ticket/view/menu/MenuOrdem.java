package com.ticket.view.menu;

import java.util.Iterator;
import com.ticket.controller.OrdemController;
import com.ticket.controller.PrioridadeController;
import com.ticket.controller.TopicoController;
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
public class MenuOrdem extends HorizontalSplitPanel implements ClickListener{
	
	private OrdemController ordemController = null;
	private CssLayout menu;
	private NativeButton   buttonAberto;
	private NativeButton   buttonTopico;
	private NativeButton   buttonPrioridade;
	private AbsoluteLayout verticalLayoutOrdemAberto;
	private AbsoluteLayout verticalLayoutTopico;
	private AbsoluteLayout verticalLayoutPrioridade;
	private Label labelFuncionalidades;

	public MenuOrdem(final OrdemController ordemController){
		this.ordemController = ordemController;
		
		addStyleName("small blue white");
		setWidth("100%");
	    setHeight("100%");
	    setMargin(true);
	    setSplitPosition(16);
		   
	    //Define CSS
	    menu = new CssLayout();
	    menu.addStyleName("menu");
	    menu.setWidth("100%");
	    setFirstComponent(menu);
	    
	    //Adiciona Categoria Despesas
	    labelFuncionalidades = new Label("Ordem");
	    labelFuncionalidades.setImmediate(true);
	    labelFuncionalidades.addStyleName("section");
	    menu.addComponent(labelFuncionalidades);
	    
	    buttonAberto = new NativeButton("Ordens em Aberto");
	    buttonAberto.setHeight("30px");
	    buttonAberto.addStyleName("selected");
	    buttonAberto.addListener(this);
        menu.addComponent(buttonAberto);
     
	    verticalLayoutOrdemAberto = new AbsoluteLayout();
    	verticalLayoutOrdemAberto.setHeight("100.0%");
    	verticalLayoutOrdemAberto.setWidth("100.0%");
    	verticalLayoutOrdemAberto.addComponent(ordemController.getOrdemView(), "top:0.0px;left:0.0px;");
    	buttonAberto.setData(verticalLayoutOrdemAberto);
	  
        buttonTopico = new NativeButton("Topico");
	    buttonTopico.setHeight("30px");
	    buttonTopico.addStyleName("selected");
	    buttonTopico.addListener(this);
        menu.addComponent(buttonTopico);
     
        TopicoController topicoController = new TopicoController();
        
	    verticalLayoutTopico = new AbsoluteLayout();
    	verticalLayoutTopico.setHeight("100.0%");
    	verticalLayoutTopico.setWidth("100.0%");
    	verticalLayoutTopico.addComponent(topicoController.getView(), "top:0.0px;left:0.0px;");
        buttonTopico.setData(verticalLayoutTopico);
      
	   
        buttonPrioridade = new NativeButton("Prioridade");
	    buttonPrioridade.setHeight("30px");
	    buttonPrioridade.addStyleName("selected");
	    buttonPrioridade.addListener(this);
        menu.addComponent(buttonPrioridade);
     
        PrioridadeController prioridade = new PrioridadeController();
        
	    verticalLayoutPrioridade = new AbsoluteLayout();
    	verticalLayoutPrioridade.setHeight("100.0%");
    	verticalLayoutPrioridade.setWidth("100.0%");
    	verticalLayoutPrioridade.addComponent(prioridade.getView(), "top:0.0px;left:0.0px;");
        buttonPrioridade.setData(verticalLayoutPrioridade);
     	
	    setSecondComponent(verticalLayoutOrdemAberto);
	}
	
	   public void moduloVisualizarCliente(){
        setSplitPosition(16);
		
	}
	
	public void moduloVoltar(){

	}
	
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
        
        Component c = (Component) event.getButton()
                .getData();
        if(c != null){
        setSecondComponent(c);
        }
	}
	
}
