package com.midiasocial.view.form;

import java.util.Arrays;

import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings({"serial"})
public class UsuarioAppMidiaSocialForm extends VerticalLayout {

	public UsuarioAppMidiaSocial user;
	public final Form userForm;
	public TextField nome;
	public ComboBox status;
	public TextField screenName;
	public TextField tokenAccess;
	public TextField tokenAccessSecret;
	public TextField consumerKey;
	public TextField consumerSecret;
	public TextField appMidiaSocial;

    public UsuarioAppMidiaSocialForm(UsuarioAppMidiaSocial user) {

    	this.user = user;
        BeanItem<UsuarioAppMidiaSocial> userItem = new BeanItem<UsuarioAppMidiaSocial>(user); 
        
        // Create the Form
        userForm = new FormWithComplexLayout(userItem,"Usuário");
        userForm.setWidth("-1px");

        // Add form to layout
        addComponent(userForm);

        userForm.getFooter().setMargin(true);
    }
    
    public UsuarioAppMidiaSocial getUser(){
    	userForm.commit();
    	return user; 	
    }
    
    public void facebookMode(){
    	
    	consumerKey.setEnabled(false);
    	consumerSecret.setEnabled(false);
    }
    
    public void fieldOff(){
    	
    	appMidiaSocial.setVisible(false);
    	tokenAccess.setVisible(false);
    	tokenAccessSecret.setVisible(false);
    	consumerKey.setVisible(false);
    	consumerSecret.setVisible(false);
    }
    
    public void fieldOn(){
    	
    	tokenAccess.setVisible(true);
    	tokenAccessSecret.setVisible(true);
    	consumerKey.setVisible(true);
    	consumerSecret.setVisible(true);
    }
    
    public void getUserInfo(String nome, String screenName){
    	
    	this.nome.setValue(nome);
    	this.screenName.setValue(screenName);
    }

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<UsuarioAppMidiaSocial> userItem, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(2, 6);

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);
            setLayout(ourLayout);

            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(userItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "nome", "screenName", "status",
            		"tokenAccess", "tokenAccessSecret", "consumerKey", "consumerSecret"}));
        
            ourLayout.addComponent(appMidiaSocial, 1, 1);
            try{
                appMidiaSocial.setValue(user.getAppMidiaSocial().getNome());
			} catch (Exception e) {
				// TODO: handle exception
			}
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("status")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("screenName")) {
                ourLayout.addComponent(field, 0, 1);
            } else if (propertyId.equals("tokenAccess")) {
                ourLayout.addComponent(field, 0, 2, 1, 2);
            } else if (propertyId.equals("tokenAccessSecret")) {
                ourLayout.addComponent(field, 0, 3, 1, 3);
            } else if (propertyId.equals("consumerKey")) {
                ourLayout.addComponent(field, 0, 4, 1, 4);
            } else if (propertyId.equals("consumerSecret")) {
                ourLayout.addComponent(field, 0, 5, 1, 5);
            } 
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
        public PersonFieldFactory() {
     
        	nome =  new TextField("Nome");
	    	nome.setWidth("19em");

	    	appMidiaSocial = new TextField("Aplicação");
	    	appMidiaSocial.setWidth("19em");
	    	appMidiaSocial.setNullRepresentation("");
	    	
	    	status = new ComboBox("Status");
	    	status.setWidth("19.7em");
	    	status.addItem("Ativo");
	    	status.addItem("Inativo");
	    	status.setNullSelectionAllowed(false);
	    	
	    	screenName = new TextField("Screen Name");
	    	screenName.setWidth("19em");
	    	
			tokenAccess = new TextField("Access Token");
			tokenAccess.setWidth("40em");
			tokenAccess.setNullRepresentation("");
			
			tokenAccessSecret = new TextField("Access Token Secret");
			tokenAccessSecret.setWidth("40em");
			tokenAccessSecret.setNullRepresentation("");
			
			consumerKey = new TextField("Consumer Key");
			consumerKey.setWidth("40em");
			consumerKey.setNullRepresentation("");
			
			consumerSecret = new TextField("Consumer Secret");
			consumerSecret.setWidth("40em");
			consumerSecret.setNullRepresentation("");
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("nome".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return nome;
                
            } else if ("status".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return status;
                
            } else if ("screenName".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return screenName;
                
            } else if ("tokenAccess".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return tokenAccess;
                
            } else if ("tokenAccessSecret".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return tokenAccessSecret;
                
            } else if ("consumerKey".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return consumerKey;
                
            } else if ("consumerSecret".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return consumerSecret;
                
            } else if ("appMidiaSocial".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return appMidiaSocial;
                
            } else {
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
}