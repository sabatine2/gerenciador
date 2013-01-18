package com.usuario.view.form;

import java.util.Arrays;


import com.usuario.model.Usuario;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;


import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;
import de.steinwedel.vaadin.MessageBox.Icon;

public class UsuarioForm extends VerticalLayout {

	/**
	 * 
	*/
	private static final long serialVersionUID = 1L;
	private final Form     usuarioForm;
	private Usuario        usuario;
	private TextField      textFieldUser;
	private PasswordField  textFieldSenha;
	private PasswordField  textFieldSenhaNovaConfirmacao;
	private PasswordField  textFieldSenhaNova;
	private Button		   buttonAlterar;

    public UsuarioForm(Usuario usuario) {

    	this.usuario = usuario;
        BeanItem<Usuario> usuarioItem = new BeanItem<Usuario>(usuario); // item from POJO
        usuarioForm = new FormWithComplexLayout(usuarioItem,"Usuario");
        addComponent(usuarioForm);

        usuarioForm.getFooter().setMargin(true);
     }

    public Usuario getUsuario(){
    	usuarioForm.commit();
    	textFieldSenhaNovaConfirmacao.validate();
    	usuario.setSenha(textFieldSenhaNova.getValue().toString());
    	usuario.setSenhaConfirmacao(textFieldSenhaNovaConfirmacao.getValue().toString());
    	return usuario; 
    }
    

	public void moduloAdicionar(){
		textFieldSenha.setVisible(false);
		buttonAlterar.setVisible(false);
	}
	
	public void moduloAlterar(){
		textFieldSenhaNovaConfirmacao.setVisible(true);
		textFieldSenha.setVisible(true);
		textFieldSenhaNova.setVisible(true);
		buttonAlterar.setVisible(true);
		textFieldUser.setReadOnly(true);
	}
	
	public void moduloVisualizar(){
		textFieldSenhaNovaConfirmacao.setVisible(false);
		textFieldSenha.setVisible(false);
		textFieldSenhaNova.setVisible(false);
		textFieldUser.setReadOnly(true);
	}
	
	public void getUsuarioAlteracao(){
		  Usuario u = Usuario.autenticaUsu(textFieldUser.getValue().toString(), textFieldSenha.getValue().toString());
		  if(u != null){
			  if(getUsuario()!= null){
		         u.setSenha(textFieldSenhaNova.getValue().toString());
		      if(u.alterar()){
		    	  new MessageBox(this.getWindow(), "Alteração de Senha", Icon.INFO, "Senha Alterada com Sucesso",  new MessageBox.ButtonConfig(ButtonType.OK, "Ok")).show(); 
		     }
		      else { new MessageBox(this.getWindow(), "Alteração de Senha", Icon.INFO, "Senha diferentes",  new MessageBox.ButtonConfig(ButtonType.OK, "Ok")).show(); }
			  }
		  }else { new MessageBox(this.getWindow(), "Alteração de Senha", Icon.INFO, "Senha atual invalida",  new MessageBox.ButtonConfig(ButtonType.OK, "Ok")).show(); }
		  
		}
    
    public class FormWithComplexLayout extends Form {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Usuario> usuarioItem, String titulo) {
            setCaption(titulo);
            
            ourLayout = new GridLayout(4, 2);

            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);

            setLayout(ourLayout);
            setWriteThrough(false); 
            setInvalidCommitted(false);
  
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(usuarioItem);
            setVisibleItemProperties(Arrays.asList(new String[] { "nome"}));
            ourLayout.addComponent(buttonAlterar,  1, 0);
            ourLayout.setComponentAlignment(buttonAlterar,
    				new Alignment(24));
            ourLayout.addComponent(textFieldSenha, 2, 1);
            ourLayout.addComponent(textFieldSenhaNova, 0, 1);
            ourLayout.addComponent(textFieldSenhaNovaConfirmacao, 1, 1);
            
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0);
            }
            
        }
    }
    
    public class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * CONFIGURACAO DOS CAMPOS
		 */
		
        public PersonFieldFactory() {
            buttonAlterar = new Button();
         	buttonAlterar.setCaption("Alterar senha");
    		buttonAlterar.setImmediate(false);
    		buttonAlterar.setWidth("-1px");
    		buttonAlterar.setHeight("-1px");
    		buttonAlterar.addListener(new Button.ClickListener() {
    			
    			/**
    			 * 
    			 */
    			private static final long serialVersionUID = 1L;

    			public void buttonClick(ClickEvent event) {
    			  if(event.getButton().getCaption().contentEquals("Alterar senha")){	
    	           moduloAlterar();
    	           event.getButton().setCaption("Salvar senha");
    			  }
    			  else{
    				 try{
    					 textFieldSenha.validate();
    					 textFieldSenhaNova.validate();
    					 textFieldSenhaNovaConfirmacao.validate();
    					 moduloVisualizar();
        				 getUsuarioAlteracao();
        		         event.getButton().setCaption("Alterar senha");
    				 } 
    				 catch (Exception e) {
    					  moduloAlterar();
    					 new MessageBox(getWindow(), "Alteração de Senha", Icon.INFO, e.getMessage(),  new MessageBox.ButtonConfig(ButtonType.OK, "Ok")).show();
    		    	 }
    			  }
    	    }});
    		
    		
            textFieldSenhaNova = new PasswordField();
        	textFieldSenhaNova.setCaption("Senha Nova");
        	textFieldSenhaNova.setImmediate(false);
        	textFieldSenhaNova.setRequired(true);
        	textFieldSenhaNova.setValue("");
        	textFieldSenhaNova.setRequiredError("Entre com a senha");
         
    		textFieldSenha = new PasswordField();
    		textFieldSenha.setCaption("Senha atual");
    		textFieldSenha.setImmediate(false);
    		textFieldSenha.setRequired(true);
    		textFieldSenha.setValue("");
    		textFieldSenha.setRequiredError("Entre com a senha atual");
    		
    	    textFieldSenhaNovaConfirmacao = new PasswordField();
         		textFieldSenhaNovaConfirmacao.setCaption("Senha Nova Confirma��o");
         		textFieldSenhaNovaConfirmacao.setImmediate(true);
         		textFieldSenhaNovaConfirmacao.setRequired(true);
         		textFieldSenhaNovaConfirmacao.setRequiredError("Entre com a senha de confirma��o");
         		textFieldSenhaNovaConfirmacao.addValidator(new AbstractValidator("Senhas Diferentes") {
         			
         			/**
 					 * 
 					 */
 					private static final long serialVersionUID = 1L;

 					@Override
         			public boolean isValid(Object value) {
         				 if(textFieldSenhaNova.getValue().toString().equals(textFieldSenhaNovaConfirmacao.getValue().toString())){
         					   return true;
         				    }
         				    else {
         				    return false;
         				 }
         			}
         		});
         }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("nome".equals(propertyId)) {
            	textFieldUser = new TextField();
        		textFieldUser.setCaption("Usuario");
        		textFieldUser.setImmediate(true);
        		textFieldUser.setValue(usuario.getNome());
        		textFieldUser.setHeight("-1px");
        		textFieldUser.setRequired(true);
        		textFieldUser.setRequiredError("Ja Existe");
        		textFieldUser.addValidator(new AbstractValidator("Usuario ja existe") {
        			
        			/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
        			public boolean isValid(Object value) {
        				 if(value.toString().length() <= 4){
        					 setErrorMessage("Nome do Usuario com menos de 4 caracteres");
        					 return false;	 
        				 }
        				 
        				 if(((Usuario.pesquisaNome(value.toString())== null) || (value.toString().contentEquals(usuario.getNome())))){
        					  return true;
        				    }
        				    else {
        				     setErrorMessage("Usu�rio j� existe");
        					 return false;
        				   }
        			}
        		});
                return textFieldUser;
                
            }  else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    }  
}