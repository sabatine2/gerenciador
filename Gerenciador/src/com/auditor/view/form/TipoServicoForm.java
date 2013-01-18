package com.auditor.view.form;

import java.util.Arrays;

import com.auditor.model.TipoServico;
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
public class TipoServicoForm extends VerticalLayout {

	public TipoServico tipo;
	public final Form tipoForm;
	public TextField descricao;
	public TextArea observacao;

    public TipoServicoForm(TipoServico tipo) {

    	this.tipo = tipo;
        BeanItem<TipoServico> tipoServicoItem = new BeanItem<TipoServico>(tipo); 
        
        // Create the Form
        tipoForm = new FormWithComplexLayout(tipoServicoItem,"Tipo Servico");
        tipoForm.setWidth("-1px");

        // Add form to layout
        addComponent(tipoForm);

        tipoForm.getFooter().setMargin(true);
    }
    
    public TipoServico getTipo(){
    	tipoForm.commit();
    	return tipo; 	
    }

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<TipoServico> tipoServicoItem, String titulo) {
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
            setItemDataSource(tipoServicoItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "descricao", "observacao"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("descricao")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("observacao")) {
                ourLayout.addComponent(field, 0, 1, 1, 1);
            }
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
        public PersonFieldFactory() {
     
        	descricao =  new TextField("Descricao");
	    	descricao.setWidth("19em");
            descricao.setRequired(true);
            descricao.setRequiredError("Por favor insira um Descrição");
            descricao.addValidator(new StringLengthValidator(
                    "Descrição deve conter 2-50 caracteres", 2, 50, false));
            
            observacao = new TextArea("Observacao");
            observacao.setWidth("40em");
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("descricao".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return descricao;
                
            } else if ("observacao".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return observacao;
                
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
}