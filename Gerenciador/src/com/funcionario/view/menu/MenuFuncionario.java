package com.funcionario.view.menu;

import java.util.Iterator;

import com.funcionario.controller.FuncionarioController;
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
public class MenuFuncionario extends HorizontalSplitPanel implements ClickListener{
	
	private FuncionarioController funcionarioController;
	private CssLayout menu;
	private NativeButton buttonFuncinario;
	private AbsoluteLayout verticalLayoutFuncionario;
	
	private Label labelFuncionalidades;
	private NativeButton buttonDados;
	private NativeButton buttonLivroContato;
	private NativeButton buttonAgenda;
	private NativeButton buttonListaPedido;

	public MenuFuncionario(final FuncionarioController funcionarioController){
		this.funcionarioController = funcionarioController;
		
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
	    labelFuncionalidades = new Label("Funcionario");
	    labelFuncionalidades.setImmediate(true);
	    labelFuncionalidades.addStyleName("section");
	    menu.addComponent(labelFuncionalidades);
	    
	    buttonFuncinario = new NativeButton("Funcionario");
	    buttonFuncinario.setHeight("30px");
	    buttonFuncinario.addStyleName("selected");
	    buttonFuncinario.addListener(this);
        menu.addComponent(buttonFuncinario);
     
	    verticalLayoutFuncionario = new AbsoluteLayout();
    	verticalLayoutFuncionario.setHeight("100.0%");
    	verticalLayoutFuncionario.setWidth("100.0%");
    	verticalLayoutFuncionario.addComponent(funcionarioController.getView(), "top:0.0px;left:0.0px;");
        buttonFuncinario.setData(verticalLayoutFuncionario);
	    
        buttonDados = new NativeButton("Dados Funcionario",new Button.ClickListener() {
	        
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
                 MenuFuncionario.this.funcionarioController.visualizar();
            }
        });
        buttonDados.addListener(this);
		buttonDados.setHeight("30px");
		buttonDados.setVisible(false);
		menu.addComponent(buttonDados);
	
	     
		buttonLivroContato = new NativeButton("Livro de Contatos",new Button.ClickListener() {
	    
			private static final long serialVersionUID = 1L;
	
			public void buttonClick(ClickEvent event) {
	        	MenuFuncionario.this.funcionarioController.livroContato();
	        }
	    });
		buttonLivroContato.setHeight("30px");
		buttonLivroContato.setVisible(false);
		buttonLivroContato.addListener(this);
		menu.addComponent(buttonLivroContato);
		
	    
	    buttonAgenda = new NativeButton("Agenda",new Button.ClickListener() {
	 
	    	private static final long serialVersionUID = 1L;
	
			public void buttonClick(ClickEvent event) {
	        	MenuFuncionario.this.funcionarioController.agenda();
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
        
	    setSecondComponent(verticalLayoutFuncionario);
	}
	
	   public void moduloVisualizarCliente(){
        setSplitPosition(16);
		
		labelFuncionalidades.setValue(funcionarioController.getFuncionario().getNome());
	    buttonFuncinario.setVisible(false);
	    buttonAgenda.setVisible(true);
	    buttonDados.setVisible(true);
	    buttonLivroContato.setVisible(true);
	    
	
	}
	
	public void moduloVoltar(){

		labelFuncionalidades.setValue("Funcionario");
	    buttonFuncinario.setVisible(true);
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
