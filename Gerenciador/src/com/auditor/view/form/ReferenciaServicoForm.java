package com.auditor.view.form;

import java.util.Arrays;

import com.auditor.model.ReferenciaServico;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ReferenciaServicoForm extends VerticalLayout {

	public ReferenciaServico referencia;
	public final Form referenciaForm;

    public ReferenciaServicoForm(ReferenciaServico referencia) {

    	this.referencia = referencia;
        BeanItem<ReferenciaServico> referenciaServicoItem = new BeanItem<ReferenciaServico>(referencia); // item from
        																										// POJO
        // Create the Form
        referenciaForm = new FormWithComplexLayout(referenciaServicoItem,"Referencia Servico");

        // Add form to layout
        addComponent(referenciaForm);

        referenciaForm.getFooter().setMargin(true);
        referenciaForm.setWidth("-1px");
    }
    
    public ReferenciaServico getReferencia(){
    	
    	referenciaForm.commit();
    	return referencia; 	
    }

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<ReferenciaServico> referenciaServicoItem, String titulo) {
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
            setItemDataSource(referenciaServicoItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "tipo", "obs"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("tipo")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("obs")) {
                ourLayout.addComponent(field, 0, 1, 1, 1);
            }
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
		final TextArea obs = new TextArea("Observacao");
		final TextField tipo = new TextField("Tipo");
     
        public PersonFieldFactory() {
     
        	obs.setWidth("39em");
        	
        	tipo.setRequired(true);
            tipo.setRequiredError("Por favor insira um Tipo");
            tipo.setWidth("19em");
            tipo.addValidator(new StringLengthValidator(
                    "Tipo deve conter 2-30 caracteres", 2, 30, false));   
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("obs".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return obs;
                
            } else if ("tipo".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return tipo;
                
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
}