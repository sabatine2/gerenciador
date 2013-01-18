package com.auditor.view.form;

import java.util.Arrays;

import com.auditor.model.Unidade;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings({"serial", "rawtypes"})
public class UnidadeForm extends VerticalLayout {

	public Unidade unidade;
	public final Form unidadeForm;
	public ComboBox centro;
	public BeanItemContainer listaCentro;

    public UnidadeForm(Unidade unidade,BeanItemContainer centroList) {

    	this.listaCentro = centroList;
    	this.unidade = unidade;
        BeanItem<Unidade> unidadeItem = new BeanItem<Unidade>(unidade);  // item from
        																// POJO
        // Create the Form
        unidadeForm = new FormWithComplexLayout(unidadeItem,"Unidade");

        // Add form to layout
        addComponent(unidadeForm);
        
        unidadeForm.getFooter().setMargin(true);
        unidadeForm.setWidth("-1px");
    }
    
    public Unidade getUnidade(){
    	unidadeForm.commit();
    	return unidade; 
    }

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Unidade> unidadeItem, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(2, 2);

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);

            setLayout(ourLayout);

            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(unidadeItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "nome", "email", "centro"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("email")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("centro")) {
                ourLayout.addComponent(field, 0, 1);
            }
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
    	
    	public PersonFieldFactory() {
    		
    		centro = new ComboBox("Centro Custo");
            centro.setContainerDataSource(listaCentro);
            centro.setItemCaptionPropertyId("nome");
    		centro.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
        	centro.setWidth("19.7em");
            centro.setRequired(true);
            centro.setRequiredError("Por favor selecione um Centro Custo");
            centro.setNullSelectionAllowed(false);           
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;    
            
            if ("centro".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return centro; 
             
            }  else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
                
            if ("nome".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Por favor insira um Nome");
                tf.setWidth("19em");
                tf.addValidator(new StringLengthValidator(
                        "Nome deve conter 2-50 caracteres", 2, 50, false));
       
            } else if ("email".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Por favor insira um Email");
                tf.setWidth("19em");
                tf.addValidator(new StringLengthValidator(
                        "Email deve conter 2-50 caracteres", 2, 50, false));       
            }
            return f;
        }
    } 
}