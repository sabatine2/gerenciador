package com.midiasocial.view.form;

import java.util.Arrays;

import com.midiasocial.model.Criterio;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CriterioForm extends VerticalLayout {

	public Criterio criterio;
	public final Form criterioForm;
	private TextField nome;
	private ComboBox status;
	private ComboBox prioridade;
	private TextArea observacao;

    public CriterioForm(Criterio criterio) {

    	this.criterio = criterio;
        BeanItem<Criterio> criterioItem = new BeanItem<Criterio>(criterio); 
        
        // Create the Form
        criterioForm = new FormWithComplexLayout(criterioItem,"Criterio");
        criterioForm.setWidth("-1px");

        // Add form to layout
        addComponent(criterioForm);

        criterioForm.getFooter().setMargin(true);
    }
    
    public Criterio getCriterio(){
    	criterioForm.commit();
    	return criterio; 	
    }

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Criterio> criterioItem, String titulo) {
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
            setItemDataSource(criterioItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "nome", "status", "observacao", "prioridade" }));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("status")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("observacao")) {
                ourLayout.addComponent(field, 0, 1, 1, 1);
            } else if (propertyId.equals("prioridade")) {
                ourLayout.addComponent(field, 0, 2);
            }  
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
        public PersonFieldFactory() {
        	
        	nome = new TextField("Nome");
        	nome.setWidth("19em");
        	
        	status = new ComboBox("Status");
        	status.setWidth("19.7em");
        	status.addItem("Ativo");
        	status.addItem("Inativo");
        	status.setNullSelectionAllowed(false);
        	
        	observacao = new TextArea("Observação");
        	observacao.setWidth("40em");
        	
        	prioridade = new ComboBox("Prioridade");
        	prioridade.setWidth("19.7em");
        	prioridade.addItem("Alta");
        	prioridade.addItem("Média");
        	prioridade.addItem("Baixa");
        	prioridade.setNullSelectionAllowed(false);
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("nome".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return nome;
                
             }else if ("status".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return status;
                
            } else if ("observacao".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return observacao;
                
            } else if ("prioridade".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return prioridade;
                
            } else {
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
}