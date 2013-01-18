package com.ticket.view.form;

import java.math.BigDecimal;
import java.util.Arrays;

import com.ticket.helper.Cor;
import com.ticket.helper.GrauDificuldade;
import com.ticket.helper.TipoPrioridade;
import com.ticket.model.Prioridade;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PrioridadeForm extends VerticalLayout {

	public com.ticket.model.Prioridade prioridade;
	public final Form prioridadeForm;
	
	public TextField prioridadeNome;
	public ComboBox  prioridadeUrgencia;
	public ComboBox  publico;
	public ComboBox  cor;

    public PrioridadeForm(Prioridade prioridade) {

    	this.prioridade = prioridade;
        BeanItem<Prioridade> prioridadeItem = new BeanItem<Prioridade>(prioridade); 
        
        prioridadeForm = new FormWithComplexLayout(prioridadeItem,"Prioridades de Serviço");
        prioridadeForm.setWidth("-1px");

        addComponent(prioridadeForm);
        prioridadeForm.getFooter().setMargin(true);
    }
    
    public Prioridade getPrioridade(){
    	
    	prioridadeForm.commit();
    	return prioridade; 	
    }

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Prioridade> topicoItem, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(2, 5);

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);
            setLayout(ourLayout);

            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(topicoItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "prioridade", "publico", "cor", "prioridadeUrgencia"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("prioridade")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("publico")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("cor")) {
                ourLayout.addComponent(field, 0, 1);
            } else if (propertyId.equals("prioridadeUrgencia")) {
                ourLayout.addComponent(field, 1, 1);
            } 
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURA��O DOS CAMPOS
		 */
        public PersonFieldFactory() {
     
        	prioridadeNome = new TextField("Nome");
        	prioridadeNome.setWidth("19em");
        	prioridadeNome.setRequired(true);
        	prioridadeNome.setRequiredError("Por favor insira um titulo");
        	prioridadeNome.addValidator(new StringLengthValidator(
                    "Titulo deve conter 2-50 caracteres", 2, 50, false));
        	prioridadeNome.setTabIndex(1);
        
        	prioridadeUrgencia = new ComboBox("Prioridade Urgencia");
        	prioridadeUrgencia.setWidth("19.7em");
        	prioridadeUrgencia.addItem("1");
        	prioridadeUrgencia.addItem("2");
        	prioridadeUrgencia.addItem("3");
        	prioridadeUrgencia.addItem("4");
        	prioridadeUrgencia.addItem("5");
        	prioridadeUrgencia.addItem("6");
        	prioridadeUrgencia.addItem("7");
        	prioridadeUrgencia.addItem("8");
        	prioridadeUrgencia.addItem("9");
        	prioridadeUrgencia.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
        	prioridadeUrgencia.setRequired(true);
        	prioridadeUrgencia.setRequiredError("Por favor selecione status");
        	prioridadeUrgencia.setNullSelectionAllowed(false);
        	prioridadeUrgencia.setTabIndex(2);
            
            publico = new ComboBox("Publico");
            publico.setWidth("19.7em");
        	publico.addItem("Sim");
        	publico.addItem("Não");
            publico.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            publico.setRequired(true);
            publico.setRequiredError("Por favor selecione Relevancia");
            publico.setNullSelectionAllowed(false);
            publico.setTabIndex(4);
            
            cor = new ComboBox("Cor");
            cor.setWidth("19.7em");
        	cor.setContainerDataSource(Cor.getCores());
            cor.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            cor.setRequired(true);
            cor.setRequiredError("Por favor selecione Relevancia");
            cor.setNullSelectionAllowed(false);
            cor.setTabIndex(3);
        
           
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("prioridade".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return prioridadeNome;
                
            } else if ("prioridadeUrgencia".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return prioridadeUrgencia;
                
            }  else if ("publico".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return publico;
                
            }  else if ("cor".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return cor;
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
}