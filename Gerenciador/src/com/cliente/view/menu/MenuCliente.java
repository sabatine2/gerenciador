package com.cliente.view.menu;

import java.util.Iterator;

import com.cliente.controller.ClienteController;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.NativeButton;

@SuppressWarnings("serial")
public class MenuCliente extends HorizontalSplitPanel implements ClickListener{
	
	private ClienteController clienteController;
	private CssLayout menu;
	private NativeButton buttonCliente;
	private AbsoluteLayout verticalLayoutCliente;
	
	private Label labelFuncionalidades;
	private NativeButton buttonDados;
	private NativeButton buttonLivroContato;
	private NativeButton buttonAgenda;
	private NativeButton buttonListaPedido;

	public MenuCliente(final ClienteController clienteController){
		this.clienteController = clienteController;
		
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
	    labelFuncionalidades = new Label("Cliente");
	    labelFuncionalidades.setImmediate(true);
	    labelFuncionalidades.addStyleName("section");
	    menu.addComponent(labelFuncionalidades);
	    
	    buttonCliente= new NativeButton("Clientes");
	    buttonCliente.setHeight("30px");
	    buttonCliente.addStyleName("selected");
	    buttonCliente.addListener(this);
        menu.addComponent(buttonCliente);
     
	    verticalLayoutCliente = new AbsoluteLayout();
    	verticalLayoutCliente.setHeight("100.0%");
    	verticalLayoutCliente.setWidth("100.0%");
    	verticalLayoutCliente.addComponent(clienteController.getView(), "top:0.0px;left:0.0px;");
        buttonCliente.setData(verticalLayoutCliente);
	    
        buttonDados = new NativeButton("Dados Cliente",new Button.ClickListener() {
	        
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
            	clienteController.visualizar();
            }
        });
        buttonDados.addListener(this);
		buttonDados.setHeight("30px");
		buttonDados.setVisible(false);
		menu.addComponent(buttonDados);
	
	     
		buttonLivroContato = new NativeButton("Livro de Contatos",new Button.ClickListener() {
	    
			private static final long serialVersionUID = 1L;
	
			public void buttonClick(ClickEvent event) {
	        	clienteController.livroContato();
	        }
	    });
		buttonLivroContato.setHeight("30px");
		buttonLivroContato.setVisible(false);
		buttonLivroContato.addListener(this);
		menu.addComponent(buttonLivroContato);
		
	    
	    buttonAgenda = new NativeButton("Agenda",new Button.ClickListener() {
	 
	    	private static final long serialVersionUID = 1L;
	
			public void buttonClick(ClickEvent event) {
	        	clienteController.agenda();
	        }
	    });
	    buttonAgenda.setHeight("30px");
	    buttonAgenda.setVisible(false);
	    buttonAgenda.addListener(this);
		menu.addComponent(buttonAgenda);
	
	    buttonListaPedido = new NativeButton("Lista de Pedido");
	    buttonListaPedido.setVisible(false);
	    buttonListaPedido.setHeight("30px");
	    menu.addComponent(buttonListaPedido);
        
	    setSecondComponent(verticalLayoutCliente);
	}
	
	   public void moduloVisualizarCliente(){
        setSplitPosition(16);
		
		labelFuncionalidades.setValue(clienteController.getCliente().getNomeFantasia());
	    buttonCliente.setVisible(false);
	    buttonAgenda.setVisible(true);
	    buttonDados.setVisible(true);
	    buttonLivroContato.setVisible(true);
	    
	
	}
	
	public void moduloVoltar(){

		labelFuncionalidades.setValue("Cliente");
	    buttonCliente.setVisible(true);
	    buttonAgenda.setVisible(false);
	    buttonDados.setVisible(false);
	    buttonLivroContato.setVisible(false);
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
