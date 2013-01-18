package com.cliente.view.form;

import java.util.Arrays;
import com.cliente.model.Cliente;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ClienteForm extends VerticalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
   	final Form clienteForm;
    @SuppressWarnings("unused")
	private static final String COMMON_FIELD_WIDTH = "12em";

    public ClienteForm(Cliente cliente) {

    	  this.cliente = cliente; // a cliente POJO
    	  cliente = null;
          BeanItem<Cliente> clienteItem = new BeanItem<Cliente>(this.cliente); // item from
                                                                      // POJO

          clienteForm = new FormWithComplexLayout(clienteItem,"Cliente");

          // Add form to layout
          addComponent(clienteForm);
      }

      public class FormWithComplexLayout extends Form {

          /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private GridLayout ourLayout;
		public FormWithComplexLayout(BeanItem<Cliente> clienteItem, String titulo) {
              setCaption(titulo);

              ourLayout = new GridLayout(3, 9);

              ourLayout.setMargin(true, false, false, true);
              ourLayout.setSpacing(true);

              setLayout(ourLayout);

              setWriteThrough(false); 
              setInvalidCommitted(false); 

              setFormFieldFactory(new ClienteFieldFactory());
              setItemDataSource(clienteItem);

              // Determines which properties are shown, and in which order:
              setVisibleItemProperties(Arrays.asList(new String[] { "status","tipoCliente",
                      "fidelidade", "gerenciamento","razaoSocial",
                      "nomeFantasia", "cnpj",
                      "ie", "atividadePrincipal","tempoDeEmpresa","site"}));
           }
          /*
           * Override to get control over where fields are placed.
           */
          @Override
          protected void attachField(Object propertyId, Field field) {
               if (propertyId.equals("razaoSocial")) {
                  ourLayout.addComponent(field, 0, 1,2, 1);
              } else if (propertyId.equals("nomeFantasia")) {
                  ourLayout.addComponent(field, 0, 2, 2, 2);
              }else if (propertyId.equals("cnpj")) {
                  ourLayout.addComponent(field, 0, 3);
              } else if (propertyId.equals("ie")) {
                  ourLayout.addComponent(field, 1, 3);
              } else if (propertyId.equals("status")) {
                  ourLayout.addComponent(field, 0, 5);
              } else if (propertyId.equals("tipoCliente")) {
                  ourLayout.addComponent(field, 0, 6);
              } else if (propertyId.equals("fidelidade")) {
                 ourLayout.addComponent(field, 1, 6);
              } else if (propertyId.equals("gerenciamento")) {
                  ourLayout.addComponent(field, 1, 5);
              } else if (propertyId.equals("atividadePrincipal")) {
                  ourLayout.addComponent(field, 0, 4);
              } else if (propertyId.equals("tempoDeEmpresa")) {
                  ourLayout.addComponent(field, 1, 4);
              }    
          }

      }
     
      private class ClienteFieldFactory extends DefaultFieldFactory {

      	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		final ComboBox statusCliente = new ComboBox("Status do Cliente");
      	final ComboBox fidelidade = new ComboBox("Fidelidade");
        final ComboBox gerenciamento = new ComboBox("Gerenciamento");
        final ComboBox tipoCadastro = new ComboBox("Tipo de Cadastro");
      	
          public ClienteFieldFactory() {
       
          	  statusCliente.setWidth("100%");
              statusCliente.addItem("Ativo");
              statusCliente.addItem("Inativo");
              statusCliente.setRequired(true);
              statusCliente.setRequiredError("Status");
              statusCliente.setNullSelectionAllowed(true);
              statusCliente.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
              
              gerenciamento.setWidth("100%");
              gerenciamento.addItem("Sim");
              gerenciamento.addItem("Nao");
              gerenciamento.setRequired(true);
              gerenciamento.setInputPrompt("Monitoramento");
              gerenciamento.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
              gerenciamento.setTabIndex(5);
              
              tipoCadastro.setWidth("100%");
              tipoCadastro.addItem("Fisico");
              tipoCadastro.addItem("Juridico");
              tipoCadastro.setRequired(true);
              tipoCadastro.setInputPrompt("Física/Jurudíca");
              tipoCadastro.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
              tipoCadastro.setTabIndex(6);
              
              fidelidade.setWidth("100%");
              fidelidade.addItem("Sim");
              fidelidade.addItem("Nao");
              fidelidade.setRequired(true);
              fidelidade.setInputPrompt("Funcionário CLT");
              fidelidade.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
              fidelidade.setTabIndex(7);
           
              
              
          }

          @Override
          public Field createField(Item item, Object propertyId,
                  Component uiContext) {
               Field f;
               if ("status".equals(propertyId)) {
                   // filtering ComboBox w/ country names
            	   return statusCliente;
               }
               else if ("fidelidade".equals(propertyId)) {
                   // filtering ComboBox w/ country names
                   return fidelidade;
               }
               else if ("tipoCliente".equals(propertyId)) {
                   // filtering ComboBox w/ country names
                   return tipoCadastro;
               }
               else if ("gerenciamento".equals(propertyId)) {
                   // filtering ComboBox w/ country names
                   return gerenciamento;
               } else f = super.createField(item, propertyId, uiContext);
              
            
              if ("fidelidade".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setRequired(true);
                  tf.setNullSettingAllowed(true);
                  tf.setWidth("4em");
              }
              else if ("razaoSocial".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setRequiredError("Razao Social");
                  tf.setRequired(true);
                  tf.setWidth("40em");
                  tf.setNullSettingAllowed(true);
                
              }
              else if ("nomeFantasia".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setRequired(true);
                  tf.setNullRepresentation("");
                  tf.setRequiredError("Nome Fantasia");
                  tf.setNullSettingAllowed(true);
                  tf.setWidth("40em");
              }
              else if ("ie".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setNullSettingAllowed(true);
                  tf.setWidth("19em");
              }
              else if ("cnpj".equals(propertyId)) {
            	  final TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setNullSettingAllowed(true);
                  tf.setTabIndex(2);
                  tf.setWidth("19em");
                  tf.setRequired(true);
                  tf.setRequiredError("CPF/CNPJ Invalido");
                  tf.addValidator(new AbstractValidator("CPF/CNPJ Invalido") {
      				
      				/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public boolean isValid(Object value) {
      					String cpf = value.toString();
      					
      					if (cpf.length() == 11) {
      						int acumulador1 = 0;
      						int resto1 = 0;
      						
      						for (int i = 0; i < 9; i++) {
      							acumulador1 += Character.getNumericValue(cpf.charAt(i)) * (10-i);
      						}
      						resto1 = 11 - acumulador1 %11;
      						
      						if (resto1 == 10) {
      							resto1 = 0;
      						}
      						
      						if (resto1 == Character.getNumericValue(cpf.charAt(9))) {
      							int acumulador2 = 0;
      							int resto2 = 0;
      							
      							for (int i = 0; i < 10; i++) {
      								
      								acumulador2 += Character.getNumericValue(cpf.charAt(i)) * (11-i);
      							}
      							resto2 = 11 - acumulador2 %11;
      							
      							if (resto2 == 10) {
      								resto2 = 0;
      							}
      							
      							if (resto2 == Character.getNumericValue(cpf.charAt(10))) {
      								
      								return true;
      							}
      							else {
      								tf.setValue("");
      								return false;
      							}
      						}
      						else {
      							tf.setValue("");
      							return false;
      						}
      					}
      					else if(cpf.length() == 14) {
      						int acumulador1 = 0;
      						int resto1 = 0;		
      						
      						acumulador1 = Character.getNumericValue(cpf.charAt(0)) * 5;
      						acumulador1 += Character.getNumericValue(cpf.charAt(1)) * 4;
      						acumulador1 += Character.getNumericValue(cpf.charAt(2)) * 3;
      						acumulador1 += Character.getNumericValue(cpf.charAt(3)) * 2;
      						acumulador1 += Character.getNumericValue(cpf.charAt(4)) * 9;
      						acumulador1 += Character.getNumericValue(cpf.charAt(5)) * 8;
      						acumulador1 += Character.getNumericValue(cpf.charAt(6)) * 7;
      						acumulador1 += Character.getNumericValue(cpf.charAt(7)) * 6;
      						acumulador1 += Character.getNumericValue(cpf.charAt(8)) * 5;
      						acumulador1 += Character.getNumericValue(cpf.charAt(9)) * 4;
      						acumulador1 += Character.getNumericValue(cpf.charAt(10)) * 3;
      						acumulador1 += Character.getNumericValue(cpf.charAt(11)) * 2;
      						
      						resto1 = 11 - acumulador1 %11;
      						
      						if (resto1 == 10) {
      							resto1 = 0;
      						}
      						
      						if (resto1 == Character.getNumericValue(cpf.charAt(12))) {
      							int acumulador2 = 0;
      							int resto2 = 0;		
      							
      							acumulador2 = Character.getNumericValue(cpf.charAt(0)) * 6;
      							acumulador2 += Character.getNumericValue(cpf.charAt(1)) * 5;
      							acumulador2 += Character.getNumericValue(cpf.charAt(2)) * 4;
      							acumulador2 += Character.getNumericValue(cpf.charAt(3)) * 3;
      							acumulador2 += Character.getNumericValue(cpf.charAt(4)) * 2;
      							acumulador2 += Character.getNumericValue(cpf.charAt(5)) * 9;
      							acumulador2 += Character.getNumericValue(cpf.charAt(6)) * 8;
      							acumulador2 += Character.getNumericValue(cpf.charAt(7)) * 7;
      							acumulador2 += Character.getNumericValue(cpf.charAt(8)) * 6;
      							acumulador2 += Character.getNumericValue(cpf.charAt(9)) * 5;
      							acumulador2 += Character.getNumericValue(cpf.charAt(10)) * 4;
      							acumulador2 += Character.getNumericValue(cpf.charAt(11)) * 3;
      							acumulador2 += Character.getNumericValue(cpf.charAt(12)) * 2;
      							
      							resto2 = 11 - acumulador2 %11;
      							
      							if (resto2 == 10) {
      								resto2 = 0;
      							}
      							
      							if (resto2 == Character.getNumericValue(cpf.charAt(13))) {
      								return true;
      							}
      							else {
      								return false;
      							}
      						}
      						else {
      							return false;
      						}
      					}
      					else {
      						return false;
      					}
      				}
      			});
              }
              else if ("atividadePrincipal".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setNullSettingAllowed(true);
                  tf.setWidth("19em");
              }
              else if ("tempoDeEmpresa".equals(propertyId)){
            	  DateField dt = (DateField) f;
            	  dt.setWidth("19em");
            	  dt.setRequired(true);
            	  dt.setDateFormat("dd-MM-yyyy");
            	  dt.setTabIndex(13);
              }
              return f;
          }
       
      }

	public Cliente getCliente() {
		clienteForm.commit();
		return cliente;
		
	}

	public void limpa(){
		clienteForm.discard();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Form getClienteForm() {
		return clienteForm;
	}
      
      
   
}