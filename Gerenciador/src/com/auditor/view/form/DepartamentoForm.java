package com.auditor.view.form;

import java.util.Arrays;

import com.auditor.model.Departamento;
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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings({"serial", "rawtypes"})
public class DepartamentoForm extends VerticalLayout {

	final Form departamentoForm;
	public Departamento departamento;
	public ComboBox unidade;
	public BeanItemContainer listaUnidade;
	
    public DepartamentoForm(Departamento departamento, BeanItemContainer centro) {

    	this.listaUnidade = centro;
    	this.departamento = departamento;
        BeanItem<Departamento> departamentoItem = new BeanItem<Departamento>(departamento); // item from
        																					// POJO
        // Create the Form
        departamentoForm = new FormWithComplexLayout(departamentoItem,"Departamento");

        // Add form to layout
        addComponent(departamentoForm);

        departamentoForm.getFooter().setMargin(true);
        departamentoForm.setWidth("-1px");
    }
    
    public Departamento getDepartamento(){
    	departamentoForm.commit();
    	return departamento; 
    }
    
    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Departamento> departamentoItem, String titulo) {
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
            setItemDataSource(departamentoItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "nome",
                    "descricaoDepartamento", "status", "email", "emailTemplate", "unidade", "responsavel"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("status")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("email")) {
                ourLayout.addComponent(field, 0, 1);
            } else if (propertyId.equals("emailTemplate")) {
                ourLayout.addComponent(field, 1, 1);
            }else if (propertyId.equals("unidade")) {
                ourLayout.addComponent(field, 1, 2);
            } else if (propertyId.equals("responsavel")) {
                ourLayout.addComponent(field, 0, 2);
            } else if (propertyId.equals("descricaoDepartamento")) {
                ourLayout.addComponent(field, 0, 3, 1 ,3);
            } 
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURA OS CAMPOS
		 */
    	final ComboBox status = new ComboBox("Status");
    	final TextArea descricaoDepartamento = new TextArea("Descricao Departamento", "");
    	final ComboBox emailTemplate = new ComboBox("Email Template");
    	final TextField nome = new TextField("Nome");
    	final TextField email = new TextField("Email");
    	final TextField responsavel = new TextField("Responsavel");
     
    	public PersonFieldFactory() {
    		
            status.setWidth("19.7em");
            status.addItem("Público");
            status.addItem("Interno");
            status.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            status.setRequired(true);
            status.setRequiredError("Por favor selecione um Status");
            status.setNullSelectionAllowed(false);
            
            emailTemplate.setWidth("19.7em");
            emailTemplate.addItem("Template 1");
            emailTemplate.setNullSelectionAllowed(false);
            
            descricaoDepartamento.setWidth("39em");
            descricaoDepartamento.setHeight("3.5em");
            
            unidade = new ComboBox("Unidade");
            unidade.setContainerDataSource(listaUnidade);
    		unidade.setItemCaptionPropertyId("nome");
    		unidade.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            unidade.setWidth("19.7em");
            unidade.setRequired(true);
            unidade.setRequiredError("Por favor selecione uma Unidade");
            unidade.setNullSelectionAllowed(false);
            
            nome.setRequired(true);
            nome.setRequiredError("Por favor insira um Nome");
            nome.setWidth("19em");
            nome.addValidator(new StringLengthValidator(
                    "Nome deve conter 2-50 caracteres", 2, 50, false));
            
            email.setRequired(true);
            email.setRequiredError("Por favor insira um Email");
            email.setWidth("19em");
            email.addValidator(new StringLengthValidator(
                    "Email deve conter 2-50 caracteres", 2, 50, false));
            
            responsavel.setRequired(true);
            responsavel.setRequiredError("Por favor insira um Responsável");
            responsavel.setWidth("19em");
            responsavel.addValidator(new StringLengthValidator(
                    "Resposnavel deve conter 2-50 caracteres", 2, 50, false));
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("status".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return status;
            	
            } else if ("emailTemplate".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return emailTemplate;
                
            } else if ("descricaoDepartamento".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return descricaoDepartamento; 
             
            } else if ("unidade".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return unidade; 
             
            } else if ("nome".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return nome; 
             
            } else if ("email".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return email; 
             
            } else if ("responsavel".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return responsavel; 
             
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
}