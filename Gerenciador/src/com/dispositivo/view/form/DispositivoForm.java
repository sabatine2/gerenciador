package com.dispositivo.view.form;

import java.util.Arrays;

import com.dispositivo.model.Dispositivo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings({"serial", "rawtypes"})
public class DispositivoForm extends VerticalLayout {

	public final Form dispositivoForm;
	public Dispositivo dispositivo;
	public PopupDateField dataAquisicao;
	public ComboBox status;
	public TextField marca;
	public TextField sistemaOperacional;
	public ComboBox wifi;
	public ComboBox tresG;
	public TextField numeroModelo;
	public ComboBox departamento;
	public BeanItemContainer listDepartamento;

	public DispositivoForm(Dispositivo dispositivo, BeanItemContainer departamentoList) {
    	
    	this.dispositivo = dispositivo;
    	this.listDepartamento = departamentoList;
        BeanItem<Dispositivo> dispositivoItem = new BeanItem<Dispositivo>( dispositivo );
        
        // Create the Form
        dispositivoForm = new FormWithComplexLayout( dispositivoItem,"Dispositivo" );
        
        // Add form to layout
        addComponent( dispositivoForm );

        dispositivoForm.getFooter().setMargin(true);
        dispositivoForm.setWidth("-1px");
    }

    public Dispositivo getDispostivo(){
    	
    	dispositivoForm.commit();
    	return dispositivo;    	
    }

	public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Dispositivo> dispositivoItem, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(2, 4);

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);

            setLayout(ourLayout);

            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(dispositivoItem); // bind to POJO via BeanItem
            
            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] {"dataAquisicao", "status", "marca", "sistemaOperacional", 
            													 "wifi", "tresG", "numeroModelo", "departamento" }));
        
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("dataAquisicao")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("status")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("marca")) {
                ourLayout.addComponent(field, 0, 2);
            } else if (propertyId.equals("sistemaOperacional")) {
                ourLayout.addComponent(field, 1, 2);
            } else if (propertyId.equals("wifi")) {
                ourLayout.addComponent(field, 0, 3);
            } else if (propertyId.equals("tresG")) {
                ourLayout.addComponent(field, 1, 3);
            } else if (propertyId.equals("numeroModelo")) {
                ourLayout.addComponent(field, 1,1);
            } else if (propertyId.equals("departamento")) {
                ourLayout.addComponent(field, 0, 1);
            }
        }
    }
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
	    public PersonFieldFactory() {
	    	
	    	dataAquisicao = new PopupDateField("Data Aquisição");
	    	dataAquisicao.setWidth("19.7em");
	    	dataAquisicao.setRequired(true);
	        dataAquisicao.setRequiredError("Por favor selecione uma Data");
	        dataAquisicao.setTabIndex(1);
	    	
	        status = new ComboBox("Status");
	        status.setWidth("19.7em");
            status.addItem("Ativo");
            status.addItem("Inativo");
            status.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            status.setRequired(true);
            status.setRequiredError("Por favor selecione um Status");
            status.setNullSelectionAllowed(false);
            status.setTabIndex(2);
            
            numeroModelo = new TextField("Número Modelo");
            numeroModelo.setWidth("19em");
            numeroModelo.setTabIndex(4);
            
            marca = new TextField("Marca");
            marca.setWidth("19em");
            marca.setTabIndex(5);
            
            sistemaOperacional = new TextField("Sistema Operacional");		
            sistemaOperacional.setWidth("19em");
            sistemaOperacional.setTabIndex(6);
            
            wifi = new ComboBox( "Wi-Fi" );
	        wifi.setWidth("19.7em");
            wifi.addItem("Sim");
            wifi.addItem("Não");
            wifi.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            wifi.setRequired(true);
            wifi.setRequiredError("Por favor selecione Wi-Fi");
            wifi.setNullSelectionAllowed(false);
            wifi.setTabIndex(7);
            
            tresG = new ComboBox("3G");
	        tresG.setWidth("19.7em");
            tresG.addItem("Sim");
            tresG.addItem("Não");
            tresG.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            tresG.setRequired(true);
            tresG.setRequiredError("Por favor selecione 3G");
            tresG.setNullSelectionAllowed(false);
            tresG.setTabIndex(8);
            
            departamento = new ComboBox("Departamento");
        	departamento.setContainerDataSource(listDepartamento);
        	departamento.setItemCaptionPropertyId("nome");
	    	departamento.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
        	departamento.setWidth("19.7em");
            departamento.setRequired(true);
            departamento.setRequiredError("Por favor selecione um Departamento");
            departamento.setNullSelectionAllowed(false);
            departamento.setTabIndex(3);
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("dataAquisicao".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return dataAquisicao;
                
            } else if ("status".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return status;
                
            } else if ("marca".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return marca;
                
            } else if ("sistemaOperacional".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return sistemaOperacional;
                
            } else if ("wifi".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return wifi;
                
            } else if ("tresG".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return tresG;
                
            } else if ("numeroModelo".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return numeroModelo;
                
            } else if ("departamento".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return departamento;
                
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    }
}