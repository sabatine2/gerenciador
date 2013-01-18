package com.auditor.view.form;

import java.util.Arrays;

import com.auditor.model.contato.PrestadoraTelefonia;
import com.principal.helper.TipoEstado;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PrestadoraTelefoniaForm extends VerticalLayout {

	public final Form prestadoraForm;
	public PrestadoraTelefonia prestadoraTelefonia;
	public PersonFieldFactory personFieldFactory = new PersonFieldFactory();
	public TextField cnpj;
	public ComboBox estado;
	public TextField nome;
	public TextField codigo;
 

    public PrestadoraTelefoniaForm(PrestadoraTelefonia prestadora) {

    	prestadoraTelefonia = new PrestadoraTelefonia(); // a person POJO
        BeanItem<PrestadoraTelefonia> prestadoraTelefoniaItem = new BeanItem<PrestadoraTelefonia>(prestadoraTelefonia); // item from
        																												// POJO
        // Create the Form
        prestadoraForm = new FormWithComplexLayout(prestadoraTelefoniaItem,"Prestadora Telefonia");

        // Add form to layout
        addComponent(prestadoraForm);

        
        prestadoraForm.getFooter().setMargin(true);
        prestadoraForm.setWidth("-1px");
    }

    public PrestadoraTelefonia getPrestadora(){
    	
    	//prestadoraForm.commit();
    	try {

        	prestadoraTelefonia.setCodigo(Long.parseLong(codigo.getValue().toString()));
		} catch (Exception e) {
			codigo.setRequiredError("Codigo invalido");
			codigo.setValue("");
		}
    	
    	prestadoraTelefonia.setCnpj(cnpj.getValue().toString());
    	prestadoraTelefonia.setNome(nome.getValue().toString());
    	prestadoraTelefonia.setEstado(estado.getValue().toString());
    	
    	prestadoraForm.commit();
    	
    	return prestadoraTelefonia; 
    }
    
    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<PrestadoraTelefonia> prestadoraTelefoniaItem, String titulo) {
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
            
            setFormFieldFactory(personFieldFactory);
            setItemDataSource(prestadoraTelefoniaItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "codigo", "nome", "cnpj", "estado"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("codigo")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("cnpj")) {
                ourLayout.addComponent(field, 0, 1);
            } else if (propertyId.equals("estado")) {
                ourLayout.addComponent(field, 1, 1);
            }
        }
    }
    
    public class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURA��O DOS CAMPOS
		 */
		
        public PersonFieldFactory() {
     
        	estado = new ComboBox("Estado");
        	estado.setWidth("19em");
            estado.setContainerDataSource(TipoEstado.getTipoEstadoContainer());
            estado.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            estado.setRequired(true);
            estado.setRequiredError("Por favor selecione um Estado");
            estado.setNullSelectionAllowed(false);
            estado.addValidator(new StringLengthValidator(
                    "Por favor selecione um Estado", 1, 50, false));
            
            codigo = new TextField("Codigo");
            codigo.setWidth("19em");
            codigo.setNullRepresentation("");
            codigo.setRequired(true);
            codigo.setRequiredError("Por favor insira um Codigo");
            
            cnpj = new TextField("CNPJ");
            cnpj.setWidth("19em");
            cnpj.setRequired(true);
            cnpj.setRequiredError("Preencha corretamento CNPJ");
            cnpj.addValidator(new StringLengthValidator(
                    "CNPJ deve conter 14 caracteres", 14, 14, false));
            
            nome= new TextField("Nome");
            nome.setWidth("19em");
            nome.setRequired(true);
            nome.setRequiredError("Por favor insira um Nome");
            nome.addValidator(new StringLengthValidator(
                    "Nome deve conter 2-50 caracteres", 2, 50, false));
            
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("estado".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return estado;
                
            } else if ("codigo".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return codigo;
                
            } else if ("cnpj".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return cnpj;
                
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