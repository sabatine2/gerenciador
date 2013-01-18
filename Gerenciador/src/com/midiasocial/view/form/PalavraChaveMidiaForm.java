package com.midiasocial.view.form;

import java.util.Arrays;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PalavraChaveMidiaForm extends VerticalLayout {

	public String pChave;
	public final Form pChaveForm;
	public TextField palavraChave;

    public PalavraChaveMidiaForm(String pChave) {

    	this.pChave = pChave;
        BeanItem<String> pChaveItem = new BeanItem<String>(pChave); 
        
        // Create the Form
        pChaveForm = new FormWithComplexLayout(pChaveItem,"Palavra Chave");
        pChaveForm.setWidth("-1px");

        // Add form to layout
        addComponent(pChaveForm);

        pChaveForm.getFooter().setMargin(true);
    }
    
    public String getChave(){		
    	pChaveForm.commit();
    	return pChave; 	
    }

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<String> pChaveItem, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(1, 1);

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);

            setLayout(ourLayout);

            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(pChaveItem); // bind to POJO via BeanItem
            ourLayout.addComponent(palavraChave, 0, 0);
            
            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "palavraChave"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("palavraChave")) {
                ourLayout.addComponent(field, 0, 0);
            } 
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
        public PersonFieldFactory() {
     
        	palavraChave =  new TextField("Palavra-Chave");
	    	palavraChave.setWidth("40em");
	    	palavraChave.setInputPrompt("INSIRA AS PALVRAS CHAVES SEPARANDO POR '' ; ''");
	    	palavraChave.setValue(pChave);
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("palavraChave".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return palavraChave;
                
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
    
    public void readOnlyTrue(){
    	palavraChave.setReadOnly(true);
    }
    
    public void readOnlyFalse(){
    	palavraChave.setReadOnly(false);
    }
}