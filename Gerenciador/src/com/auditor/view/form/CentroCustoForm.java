package com.auditor.view.form;

import java.util.Arrays;

import com.auditor.model.CentroCusto;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CentroCustoForm extends VerticalLayout {

	final Form centroForm;
	public CentroCusto centroCusto;
	public TextField org;
	
    public CentroCustoForm(CentroCusto centroCusto) {
    	
    	this.centroCusto = centroCusto;
        BeanItem<CentroCusto> centroCustoItem = new BeanItem<CentroCusto>(centroCusto); // item from
        																							    // POJO
        // Create the Form
        centroForm = new FormWithComplexLayout(centroCustoItem,"Centro Custo");
        org.setReadOnly(false);
        org.setValue(centroCusto.getOrganizacao().getNomeFantasia());	
        org.setReadOnly(true);
        
        // Add form to layout
        addComponent(centroForm);

        centroForm.getFooter().setMargin(true);
        centroForm.setWidth("-1px");
    }

    public CentroCusto getCentro(){
    	
    	centroForm.commit();
    	return centroCusto;    	
    }

	public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<CentroCusto> centroCustoItem, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(2, 3);

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);

            setLayout(ourLayout);

            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(centroCustoItem); // bind to POJO via BeanItem
            
            ourLayout.addComponent(org, 0, 2);

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] {"nome", "email", "responsavel", "org", "telefone"}));
        
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0, 1, 0);
            } else if (propertyId.equals("email")) {
                ourLayout.addComponent(field, 0, 1);
            } else if (propertyId.equals("responsavel")) {
                ourLayout.addComponent(field, 1, 1);
            } else if (propertyId.equals("org")) {
                ourLayout.addComponent(field, 0, 2);
            } else if (propertyId.equals("telefone")) {
                ourLayout.addComponent(field, 1, 2);
            }
        }
    }
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
		final TextField nome = new TextField("Nome");
		final TextField email = new TextField("Email");
		final TextField responsavel = new TextField("Responsavel");	
		final TextField telefone = new TextField("Telefone");		

	    public PersonFieldFactory() {
	    	
	    	org =  new TextField("Organizacao");
	    	org.setWidth("19em");
            org.setRequired(true);
            org.setRequiredError("Por favor selecione uma Organizacao");
            org.addValidator(new StringLengthValidator(
                    "Por favor selecione uma Organizacao", 1, 50, false));
	        
            nome.setWidth("40em");
            nome.setRequired(true);
            nome.setRequiredError("Por favor insira um Nome");
            nome.addValidator(new StringLengthValidator(
                    "Nome deve conter 2-50 caracteres", 2, 50, false));
            
            email.setWidth("19em");
            email.setRequired(true);
            email.setRequiredError("Por favor insira um Email");
            email.addValidator(new StringLengthValidator(
                    "Email deve conter 5-50 caracteres", 2, 50, false));
            
            responsavel.setWidth("19em");
            responsavel.setRequired(true);
            responsavel.setRequiredError("Por favor insira um Responsavel");
            responsavel.addValidator(new StringLengthValidator(
                    "Responsavel deve conter 2-50 caracteres", 2, 50, false));
            
            telefone.setWidth("19em");
            telefone.setRequired(true);
            telefone.setRequiredError("Por favor insira um Telefone");
            telefone.addValidator(new StringLengthValidator(
                    "Telefone deve conter 8 caracteres", 8, 8, false));
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("nome".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return nome;
                
            } else if ("email".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return email;
                
            } else if ("responsavel".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return responsavel;
                
            } else if ("org".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return org;
                
            } else if ("telefone".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return telefone;
                
            }else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    }
}