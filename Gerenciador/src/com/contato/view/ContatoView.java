package com.contato.view;

import java.util.Iterator;
import java.util.List;

import com.contato.controller.ContatoController;
import com.contato.model.Contato;
import com.endereco.model.Endereco;
import com.contato.view.form.ContatoForm;
import com.endereco.view.*;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class ContatoView extends VerticalLayout  implements
Button.ClickListener, LayoutClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
    private VerticalLayout contatosDetalhes;
    private ContatoDetalhe contatoDetalhe;
    
    private HorizontalLayout livro;
    
    private EnderecoForm enderecoForm;
    private ContatoForm contatoForm;
    
    private ContatoController contatoController;
   
    private VerticalLayout adicionar = null;
    private Table table = null;
    
    public ContatoView(ContatoController contatoController){
    	this.contatoController = contatoController;
    	
    }
  
    public VerticalLayout viewLayoutAdicionar(){
    	adicionar = new VerticalLayout();
    	adicionar.setWidth("-1px");
    	adicionar.setHeight("100%");
    	adicionar.setMargin(true);
		adicionar.setSpacing(true);
       
		contatoForm = new ContatoForm(contatoController.getContato());
        adicionar.addComponent(contatoForm);
        
        adicionar.addComponent(buildpanelAddMedia());
        adicionar.addComponent(buildpanelAddTelefone());
    	
        if(contatoController.getContato().getEndereco() != null){
        enderecoForm = new EnderecoForm(contatoController.getContato().getEndereco());
        }
        else enderecoForm = new EnderecoForm(new Endereco());
        
        adicionar.addComponent(enderecoForm);
        
        return adicionar;
    }
    
    public VerticalLayout modoAdicionarContato(){
    	
    	removeAllComponents();
        addComponent(viewLayoutAdicionar());
        
        return adicionar;
    }
    
    public VerticalLayout modoLivroContato(){
    	
    	livro = new HorizontalLayout();
    	livro.setWidth("100%");
    	livro.setHeight("100%");
	    	
	     final Button adicionarButton = new Button("Novo contato");
	     adicionarButton.setIcon(new ThemeResource("../reindeer/Icons/plus.png"));
	     addComponent(adicionarButton);
	     adicionarButton.addListener(new Button.ClickListener() {
	              /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
	
				public void buttonClick(ClickEvent event) {
	              	removeComponent(livro);
	              	contatoController.modoAdicionar(null);
	              	addComponent(viewLayoutAdicionar());
	  		        adicionarButton.setVisible(false);
	              }
	          });
	          
	    	contatosDetalhes = new VerticalLayout();
	    	contatosDetalhes.setSpacing(true);
	    	contatosDetalhes.addComponent(adicionarButton);
	    	List<Contato> listaContatos = (List<Contato>) contatoController.getContatos();
			
			for (Iterator<Contato> iteratorContatos = listaContatos.iterator(); iteratorContatos.hasNext();) {
			Contato c = (Contato) iteratorContatos.next();
			ContatoDetalhe contato = new ContatoDetalhe(c);
			contato.addListener(this);
			contato.getRemover().addListener(this);
			contatosDetalhes.addComponent(contato);
			}
	    	
			
		   livro.setMargin(true);
			   	
		   if (listaContatos.size() > 0){
			contatoDetalhe = (ContatoDetalhe) contatosDetalhes.getComponent(1);  
			contatoDetalhe.selecinado();
			contatoController.modoVisualizar(listaContatos.get(0));
			livro.addComponent(contatosDetalhes);
			livro.addComponent(viewLayoutAdicionar());
		   }else{
			  livro.addComponent(new com.vaadin.ui.Label("Sem Contato"));
			  livro.addComponent(contatosDetalhes);
				
		   }
		    removeAllComponents();
	        addComponent(livro);
	       
		    return this;
    	
    }
    
    public void addItemTable(Contato contato){
    	 table.addItem(new Object[]{contato.getNome()},contato);
    }
    
	private Panel buildpanelAddMedia() {
	  return contatoController.getMediaController().getView();
		
	}
   
    private Panel buildpanelAddTelefone() {
	   return contatoController.getTelefoneController().getView();
	}
   
	public EnderecoForm getEnderecoForm() {
		return enderecoForm;
	}

	public void setEnderecoForm(EnderecoForm enderecoForm) {
		this.enderecoForm = enderecoForm;
	}

	public Contato getContato() {
		
		Endereco endereco = enderecoForm.getEndereco();
		Contato contato = contatoForm.getContato();
		if(contato != null){
		  if(endereco != null){	
		     contato.setEndereco(endereco);
		   return contato;
		  }
		   else return null;
		}
		else return null;
	}

	public void setContato(Contato contato) {
		this.contatoForm.setContato(contato);
	}

	public void atualizaDetalhes(Contato contato){
		ContatoDetalhe contatoD = new ContatoDetalhe(contato);
		contatosDetalhes.replaceComponent(contatoDetalhe,  contatoD);
		contatoDetalhe = contatoD;
		contatoDetalhe.selecinado();
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		Button b = (Button) event.getComponent();
		ContatoDetalhe contatoDetalhes = (ContatoDetalhe) b.getData();
		contatoController.remover(contatoDetalhes.getContatoCliente());
		contatoDetalhes = null;
   }

	@Override
	public void layoutClick(LayoutClickEvent event) {
		// TODO Auto-generated method stub
		contatoDetalhe.normal();
		contatoDetalhe = (ContatoDetalhe) event.getComponent();
		contatoDetalhe.selecinado();
		
		contatoController.modoVisualizar((Contato) contatoDetalhe.getData());
		
		if(adicionar!=null){
	    livro.removeComponent(adicionar);
	    }
        livro.addComponent(viewLayoutAdicionar());
	}	
	
}
