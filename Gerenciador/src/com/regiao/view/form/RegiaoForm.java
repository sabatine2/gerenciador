package com.regiao.view.form;

import java.util.Arrays;

import com.regiao.model.Regiao;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class RegiaoForm extends VerticalLayout {

	public final Form regiaoForm;
	public Regiao regiao;
	public ComboBox status;
	public TextField nome;

    public RegiaoForm(Regiao regiao) {

    	this.regiao = regiao;
        BeanItem<Regiao> regiaoItem = new BeanItem<Regiao>(regiao); // item from
        																												// POJO
        // Create the Form
        regiaoForm = new FormWithComplexLayout(regiaoItem,"Região");

        // Add form to layout
        addComponent(regiaoForm);

        
        regiaoForm.getFooter().setMargin(true);
        regiaoForm.setWidth("-1px");
    }

    public Regiao getRegiao(){
    	
    	regiaoForm.commit();
    	return regiao; 
    }
    
    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Regiao> regiaoItem, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(2, 1);

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);

            setLayout(ourLayout);

            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(regiaoItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "nome", "status"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("status")) {
                ourLayout.addComponent(field, 1, 0);
            }
        }
    }
    
    public class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
		
        public PersonFieldFactory() {
     
        	status = new ComboBox("Status");
        	status.setWidth("19em");
            status.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            status.setRequired(true);
            status.setRequiredError("Por favor selecione um Status");
            status.setNullSelectionAllowed(false);
            status.addItem("Ativo");
            status.addItem("Inativo");
            
            nome = new TextField("Nome");
            nome.setWidth("19em");
            nome.setRequired(true);
            nome.setRequiredError("Por favor insira um Nome");
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("status".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return status;
                
            } else if ("nome".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return nome;
                
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    }  
}