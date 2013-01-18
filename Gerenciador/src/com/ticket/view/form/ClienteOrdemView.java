package com.ticket.view.form;

import java.util.Arrays;
import com.cliente.model.Cliente;
import com.contato.model.Contato;
import com.telefone.model.TelefoneContato;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ClienteOrdemView extends VerticalLayout{

	private static final long serialVersionUID = 1L;
	
	private Form contatoForm;
    private ContatoOrdem contatoOrdem;

	public ClienteOrdemView(Contato contato, String modo) {
		
		this.contatoOrdem = new ContatoOrdem(contato.getCliente(), contato, null);
	    BeanItem<ContatoOrdem> ordemItem = new BeanItem<ContatoOrdem>(contatoOrdem); 
	    
	    // Create the Form
	    contatoForm = new FormWithComplexLayout(ordemItem,"Dados Cliente", modo);
	    contatoForm.setWidth("100%");
	   
	    // Add form to layout
	    addComponent(contatoForm);
		
	}
	
	public Contato getContato(){
		contatoForm.commit();
		return contatoOrdem.getContato();
	}

	public class ContatoOrdem{
		
		private Cliente cliente;
		private Contato contato;
		private TelefoneContato telefone;
		
		
		public ContatoOrdem(Cliente cliente, Contato contato,
				TelefoneContato telefone) {
			super();
			this.cliente = cliente;
			this.contato = contato;
			this.telefone = telefone;
		}
		/**
		 * @return the cliente
		 */
		public Cliente getCliente() {
			return cliente;
		}
		
		/**
		 * @return the cliente
		 */
		public BeanItemContainer<Contato> listContato() {
			BeanItemContainer<Contato> beans = new BeanItemContainer<Contato>(Contato.class);
			beans.addAll(cliente.getContato());	
			return beans;
		}
		/**
		 * @param cliente the cliente to set
		 */ 
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}
		/**
		 * @return the contato
		 */
		public Contato getContato() {
			return contato;
		}
		/**
		 * @param contato the contato to set
		 */
		public void setContato(Contato contato) {
			this.contato = contato;
		}
		/**
		 * @return the telefone
		 */
		public TelefoneContato getTelefone() {
			return telefone;
		}
		/**
		 * @param telefone the telefone to set
		 */
		public void setTelefone(TelefoneContato telefone) {
			this.telefone = telefone;
		}
		
		
		
	}

	public class FormWithComplexLayout extends Form {
	    /**
		 * CRIA GRID E OS CAMPOS
		 */
		public GridLayout ourLayout;
	    public String modo;
	    
	    public FormWithComplexLayout(BeanItem<ContatoOrdem> ordemItem, String titulo, String modo) {
	        this.modo = modo;
	        ourLayout = new GridLayout(2, 4);
	        ourLayout.setWidth("100%");
	        ourLayout.setMargin(false, false, false, false);
	        ourLayout.setSpacing(true);
	        setLayout(ourLayout);
	        
	        setWriteThrough(false); 
	        setInvalidCommitted(false); 
	        setFormFieldFactory(new PersonFieldFactory());
	        setItemDataSource(ordemItem); 
	        setVisibleItemProperties(Arrays.asList(new String[] { "cliente","contato","telefone"}));
	    	   
	    }
	    
	    @Override
	    protected void attachField(Object propertyId, Field field) {
	        if (propertyId.equals("cliente")) {
	            ourLayout.addComponent(field, 0, 0, 1, 0);
	        } else if (propertyId.equals("contato")) {
	            ourLayout.addComponent(field, 0, 1,1,1);
	        } else if (propertyId.equals("telefone")) {
	            ourLayout.addComponent(field, 0, 2,1,2);
	        }   
	    }
	  
	private class PersonFieldFactory extends DefaultFieldFactory {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ComboBox       comboBoxTelefone;
		private ComboBox       comboBoxContato;
		private ComboBox       comboBoxCliente;
		private TextField	   textFieldCliente;

	    public PersonFieldFactory() {
	    	
	    	comboBoxCliente = new ComboBox();
			comboBoxCliente.setImmediate(true);
			comboBoxCliente.setWidth("100.0%");
			comboBoxCliente.setHeight("-1px");
			comboBoxCliente.setCaption("Cliente");
			comboBoxCliente.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
		    comboBoxCliente.setContainerDataSource(Cliente.listaBeansAtivo());
			comboBoxCliente.setRequired(true);
			comboBoxCliente.setRequiredError("Selecione um cliente");
			comboBoxCliente.setItemCaptionPropertyId("infoCliente");
			comboBoxCliente.addListener(new Property.ValueChangeListener() {
				
				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					// TODO Auto-generated method stub
					 if(event.getProperty().getValue() instanceof Cliente){
					 Cliente cliente = (Cliente)event.getProperty().getValue();
					 System.out.println(cliente.toString());
					 atualizaCliente(cliente);	
					 }else{
						 atualizaCliente(new Cliente());	
					 }
					
				}
			});
	    	
			// comboBoxContato
			comboBoxContato = new ComboBox();
			comboBoxContato.setImmediate(true);
			comboBoxContato.setWidth("100.0%");
			comboBoxContato.setHeight("-1px");
			comboBoxContato.setCaption("Contato");
			comboBoxContato.setRequired(true);
			comboBoxContato.setRequiredError("Selecione um contato");
			comboBoxContato.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
		    comboBoxContato.setContainerDataSource(contatoOrdem.listContato());
			comboBoxContato.setItemCaptionPropertyId("infoContato");
			comboBoxContato.addListener(new Property.ValueChangeListener() {
				
				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					 if(event.getProperty().getValue() instanceof Contato){
						 Contato contato = (Contato)event.getProperty().getValue();
						 atualizaContato(contato);	
						 }else{
							 atualizaContato(new Contato());	
						 }
							
					
				}
			});
	        
			// comboBoxTelefone
			comboBoxTelefone = new ComboBox();
			comboBoxTelefone.setImmediate(true);
			comboBoxTelefone.setWidth("100.0%");
			comboBoxTelefone.setHeight("-1px");
			comboBoxTelefone.setCaption("Telefone");
			
			
	    }
	
	    public void  atualizaCliente(Cliente cliente){
		    BeanItemContainer<Contato> beans = new BeanItemContainer<Contato>(Contato.class);
			beans.addAll(cliente.getContato());
		    comboBoxContato.setContainerDataSource(beans);
			comboBoxContato.setItemCaptionPropertyId("infoContato");
			
			
	     }
	 
	    public void  atualizaContato(Contato contato){
		    BeanItemContainer<TelefoneContato> beans = new BeanItemContainer<TelefoneContato>(TelefoneContato.class);
			beans.addAll(contato.getTelefoneContato());
		    comboBoxTelefone.setContainerDataSource(beans);
		    comboBoxTelefone.setItemCaptionPropertyId("infoTelefone");
			
	     }
	    
	    @Override
	    public Field createField(Item item, Object propertyId,
	            Component uiContext) {
	        Field f;
	                 
	        if ("cliente".equals(propertyId)) {
	            return comboBoxCliente;
	            
	        } else if ("contato".equals(propertyId)) {
	            // filtering ComboBox w/ country names
	            return comboBoxContato;
	            
	        }  else if ("telefone".equals(propertyId)) {
	            // filtering ComboBox w/ country names
	            return comboBoxTelefone;
	            
	        }  else {
	            // Use the super class to create a suitable field base on the
	            // property type.
	            f = super.createField(item, propertyId, uiContext);
	        }
	        return f;
	    }
	} 
  }
}
