package com.despesas.view.menu;

import java.util.Iterator;

import com.despesas.controller.DespesasController;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class MenuDespesas extends HorizontalSplitPanel implements ClickListener{

	private CssLayout menu;
	private AbsoluteLayout verticalLayoutDespesas;
	private DespesasController despesaController;
	private NativeButton buttonDespesas;
	
	public MenuDespesas(DespesasController despesaController){
		this.despesaController = despesaController;
		
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

	    buttonDespesas= new NativeButton("Despesas");
	    verticalLayoutDespesas = new AbsoluteLayout();
    	verticalLayoutDespesas.setHeight("100.0%");
    	verticalLayoutDespesas.setWidth("100.0%");
    	verticalLayoutDespesas.addComponent(despesaController.getView(), "top:0.0px;left:0.0px;");
        buttonDespesas.setData(verticalLayoutDespesas);
	    
        setSecondComponent(verticalLayoutDespesas);
	}
	
	public void moduloVisualizar(){
        setSplitPosition(16); 
        
	    //Adiciona Categoria Despesas
	    Label labelFuncionalidades = new Label("Despesas");
	    labelFuncionalidades.addStyleName("section");
	    menu.addComponent(labelFuncionalidades);
	    
	    //Bot�o Despesas
        buttonDespesas = new NativeButton("Despesas");
        buttonDespesas.setHeight("30px");
        buttonDespesas.addStyleName("selected");
        buttonDespesas.addListener(this);
        menu.addComponent(buttonDespesas);
     
        verticalLayoutDespesas = new AbsoluteLayout();
    	verticalLayoutDespesas.setHeight("100%");
    	verticalLayoutDespesas.setWidth("100.0%");
    	despesaController = new DespesasController();
    	verticalLayoutDespesas.addComponent(despesaController.getView(), "top:0.0px;left:0.0px;");
        buttonDespesas.setData(verticalLayoutDespesas);
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
        setSecondComponent((Component) event.getButton()
                .getData());
	}
}
