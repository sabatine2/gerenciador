package com.contato.view.form;

import java.util.Arrays;

import com.contato.model.Contato;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ContatoForm extends VerticalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  Contato contato;
	final Form contatoForm;
	
    
    public ContatoForm(Contato contato) {
    	
        this.contato = contato; 
        
        BeanItem<Contato> contatoItem = new BeanItem<Contato>(this.contato); // item from
        																// POJO
        // Create the Form
        contatoForm = new FormWithComplexLayout(contatoItem,"Contato");

        // Add form to layout
        setSpacing(true);
        addComponent(contatoForm);
     }
    
    public Contato getContato() {
		contatoForm.validate();
		try{
		contatoForm.commit();
		return contato;
		}
		catch (Exception e) {
			return null;
		}
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
    
	    
    public class FormWithComplexLayout extends Form {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Contato> contatoItem, String titulo) {
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
            setItemDataSource(contatoItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "tipoContato",
                    "nome", "departamento","emailPrincipal","cpf","rg","orgaoEmissor","status"}));
            
        
        }

        /*
         * Override to get control over where fields are placed.
         */
        @Override
        protected void attachField(Object propertyId, Field field) {
        	if (propertyId.equals("tipoContato")) {
                ourLayout.addComponent(field, 0, 2);
            } else if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("departamento")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("cpf")) {
                ourLayout.addComponent(field, 0, 1); 
        	} else if (propertyId.equals("status")) {
        		ourLayout.addComponent(field, 1, 2);
        	} else if (propertyId.equals("emailPrincipal")) {
                ourLayout.addComponent(field, 1, 1);
	        }  
        }

    }

    @SuppressWarnings("unused")
	private void showPojoState() {
        Window.Notification n = new Window.Notification("POJO state",
                Window.Notification.TYPE_TRAY_NOTIFICATION);
        n.setPosition(Window.Notification.POSITION_CENTERED);
        n.setDescription("Ramal: " + contato.getTipoContato()
                + "<br/>Conta: " + contato.getDepartamento());
        getWindow().showNotification(n);
    }

    private class PersonFieldFactory extends DefaultFieldFactory {

    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		final ComboBox tipoContato = new ComboBox("Tipo de Contato");
    	final ComboBox status = new ComboBox("Status");
    	
        public PersonFieldFactory() {
     
        	tipoContato.setWidth("100%");
            tipoContato.addItem("Proprietário");
            tipoContato.addItem("Técnico");
            tipoContato.addItem("Financeiro");
            tipoContato.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            
            status.setWidth("100%");
            status.addItem("Ativo");
            status.addItem("Inativo");
            status.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("tipoContato".equals(propertyId)) {
            	tipoContato.setRequired(true);
            	tipoContato.setRequiredError("Por favor escolha um Tipo de Contato");
            	tipoContato.setWidth("19em");
                return tipoContato;
            }else if ("status".equals(propertyId)) {
            	status.setRequired(true);
                status.setRequiredError("Por favor selecione um Status");
                status.setRequiredError("Por favor digite um email");
                status.setWidth("19em");
                return status;
            }else if ("senha".equals(propertyId)) {
                f = createPasswordField(propertyId);
            } else {
                f = super.createField(item, propertyId, uiContext);
            }
            
          
             if ("nome".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Por favor insira um Nome");
                tf.setWidth("19em");
                tf.addValidator(new StringLengthValidator(
                        "Nome deve conter 2 a 40 caracteres", 2, 40, false));
            } else if ("departamento".equals(propertyId)) {
            	TextField pf = (TextField) f;
                pf.setWidth("19em");
                pf.addValidator(new StringLengthValidator(
                        "Departamento deve conter 2 a 40 caracteres", 2, 40, false));
            } else if ("cpf".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setNullSettingAllowed(true);
                tf.setWidth("19em");
                tf.addValidator(new StringLengthValidator(
                        "CPF deve conter 11 digitos", 2, 40, false));
            } else if ("emailPrincipal".equals(propertyId)) {
            	TextField pf = (TextField) f;
                pf.setWidth("19em");
                pf.setRequired(true);
                pf.setRequiredError("Por favor digite um email");
                pf.addValidator(new EmailValidator("Email Incorreto"));
            }
            return f;
        }

        private PasswordField createPasswordField(Object propertyId) {
            PasswordField pf = new PasswordField();
            pf.setCaption(DefaultFieldFactory
                    .createCaptionByPropertyId(propertyId));
            return pf;
        }
    }
 
}