package com.funcionario.view.form;

import java.util.Arrays;
import com.auditor.model.Departamento;
import com.funcionario.model.Funcionario;
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

public class FuncionarioViewForm extends VerticalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
   	final Form funForm;
    final ComboBox departamento = new ComboBox("Departamento");
    
   	@SuppressWarnings("unused")
	private static final String COMMON_FIELD_WIDTH = "12em";

    public FuncionarioViewForm(Funcionario funcionario) {
          
    	  this.funcionario = funcionario; //funcionario POJO
    	  funcionario = null;
          BeanItem<Funcionario> funcionarioItem = new BeanItem<Funcionario>(this.funcionario); // item from
                                                                      // POJO

          funForm = new FormWithComplexLayout(funcionarioItem,"Funcionario");

          // Add form to layout
          addComponent(funForm);
      }

      public class FormWithComplexLayout extends Form {

          /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private GridLayout ourLayout;
	      
          public FormWithComplexLayout(BeanItem<Funcionario> funcionarioItem, String titulo) {
              setCaption(titulo);

              // Create our layout (3x3 GridLayout)
              ourLayout = new GridLayout(3, 11);

              // Use top-left margin and spacing
              ourLayout.setMargin(true, false, false, true);
              ourLayout.setSpacing(true);

              setLayout(ourLayout);

              // Set up buffering
              setWriteThrough(false); // we want explicit 'apply'
              setInvalidCommitted(false); // no invalid values in datamodel

              // FieldFactory for customizing the fields and adding validators
              setFormFieldFactory(new FuncionarioFieldFactory());
              setItemDataSource(funcionarioItem); // bind to POJO via BeanItem

              // Determines which properties are shown, and in which order:
              setVisibleItemProperties(Arrays.asList(new String[] {"nome", "cpf", "rg", "status","tipoFuncionario","gerenciamento",
            		  "fidelidade", "tempoDeEmpresa","departamento", "nomePai","nomeMae","numeroPis","dataNascimento" }));

          }
          /*
           * Override to get control over where fields are placed.
           */
          @Override
          protected void attachField(Object propertyId, Field field) {
              if (propertyId.equals("nome")) {
                  ourLayout.addComponent(field, 0, 1,2, 1);
              } else if (propertyId.equals("cpf")) {
                  ourLayout.addComponent(field, 0, 2);
              } else if (propertyId.equals("rg")) {
                  ourLayout.addComponent(field, 1, 2);
              } else if (propertyId.equals("status")) {
                  ourLayout.addComponent(field, 0, 3);
              }else if (propertyId.equals("gerenciamento")) {
                  ourLayout.addComponent(field, 1, 3);
              }else if (propertyId.equals("tipoFuncionario")) {
                  ourLayout.addComponent(field, 0, 4);
              } else if (propertyId.equals("fidelidade")) {
                 ourLayout.addComponent(field, 1, 5);
              } else if (propertyId.equals("tempoDeEmpresa")) {
                  ourLayout.addComponent(field, 1, 4);
              } else if (propertyId.equals("departamento")) {
                  ourLayout.addComponent(field, 0, 5);
              } else if (propertyId.equals("nomeMae")) {
                  ourLayout.addComponent(field, 0, 7, 1, 7);
              } else if (propertyId.equals("nomePai")) {
                  ourLayout.addComponent(field, 0, 8, 1, 8);
              } else if (propertyId.equals("numeroPis")) {
                  ourLayout.addComponent(field, 0, 9);
              } else if (propertyId.equals("dataNascimento")) {
                  ourLayout.addComponent(field, 1, 6);
              }    
          }
      }
     
      private class FuncionarioFieldFactory extends DefaultFieldFactory {

      	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		final ComboBox statusFuncionario = new ComboBox("Status do Funcionario");
      	final ComboBox fidelidade = new ComboBox("Fidelidade");
        final ComboBox gerenciamento = new ComboBox("Gerenciamento");
        final ComboBox tipoCadastro = new ComboBox("Tipo de Cadastro");
        final ComboBox departamento = new ComboBox("Departamento");
      	
          public FuncionarioFieldFactory() {
       
          	  statusFuncionario.setWidth("100%");
              statusFuncionario.addItem("Ativo");
              statusFuncionario.addItem("Inativo");
              statusFuncionario.addItem("Ferias");
              statusFuncionario.addItem("Afastado");
              statusFuncionario.setRequired(true);
              statusFuncionario.setRequiredError("Campo Status Funcionario invalido");
              statusFuncionario.setInputPrompt("Selecione um status");
              statusFuncionario.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
              statusFuncionario.setTabIndex(4);
           
              gerenciamento.setWidth("100%");
              gerenciamento.addItem("Sim");
              gerenciamento.addItem("Nao");
              gerenciamento.setRequired(true);
              gerenciamento.setRequiredError("Campo Gerenciamento inválido");
              gerenciamento.setInputPrompt("Monitoramento");
              gerenciamento.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
              gerenciamento.setTabIndex(5);
              
              tipoCadastro.setWidth("100%");
              tipoCadastro.addItem("Fisico");
              tipoCadastro.addItem("Juridico");
              tipoCadastro.setRequired(true);
              tipoCadastro.setRequiredError("Campo Tipo Cadastro inválido");
              tipoCadastro.setInputPrompt("Física/Jurudíca");
              tipoCadastro.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
              tipoCadastro.setTabIndex(6);
              
              fidelidade.setWidth("100%");
              fidelidade.addItem("Sim");
              fidelidade.addItem("Nao");
              fidelidade.setRequired(true);
              fidelidade.setRequiredError("Campo Fidelidade invalido");
              fidelidade.setInputPrompt("Funcionário CLT");
              fidelidade.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
              fidelidade.setTabIndex(7);
           
            
              departamento.setWidth("100%");
              departamento.setRequiredError("Campo departamento invalido");
              departamento.setInputPrompt("Selecione um depatamento");
              departamento.setContainerDataSource(Departamento.listaBens());
        	  departamento.setItemCaptionPropertyId("departamentoInfo");
              departamento.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
              departamento.setRequired(true);
              departamento.setTabIndex(9);
          }

          @Override
          public Field createField(Item item, Object propertyId,
                  Component uiContext) {
        	 
               Field f;
               if ("status".equals(propertyId)) {
                   // filtering ComboBox w/ country names
                   return statusFuncionario;
               }
               else if ("fidelidade".equals(propertyId)) {
                   // filtering ComboBox w/ country names
                   return fidelidade;
               }
               else if ("tipoFuncionario".equals(propertyId)) {
                   // filtering ComboBox w/ country names
                   return tipoCadastro;
               }
               else if ("gerenciamento".equals(propertyId)) {
                   // filtering ComboBox w/ country names
                   return gerenciamento;
               } 
               else if ("departamento".equals(propertyId)) {
                   // filtering ComboBox w/ country names
                   return departamento;
               } 
               else f = super.createField(item, propertyId, uiContext);
              
            
              if ("nome".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setRequiredError("Campo nome Inválido");
                  tf.setRequired(true);
                  tf.setWidth("40em");
                  tf.setNullSettingAllowed(true);
                  tf.setTabIndex(1);
              }
              else if ("nomePai".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setRequiredError("Campo Nome Pai Invalido");
                  tf.setWidth("40em");
                  fidelidade.setTabIndex(10);
                  tf.setNullSettingAllowed(true);
              }
              else if ("nomeMae".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setRequiredError("Campo Nome Mae Invalido");
                  tf.setWidth("40em");
                  fidelidade.setTabIndex(11);
                  tf.setNullSettingAllowed(true);
              }
              else if ("cpf".equals(propertyId)) {
                  final TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setNullSettingAllowed(true);
                  tf.setTabIndex(2);
                  tf.setWidth("19em");
                  tf.setRequired(true);
                  tf.setRequiredError("Campo CPF/CNPJ Invalido");
                  tf.addValidator(new AbstractValidator("CPF/CNPJ Invalido") {
      				
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
              else if ("rg".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setTabIndex(3);
                  tf.setNullRepresentation("");
                  tf.setNullSettingAllowed(true);
                  tf.setWidth("19em");
              }
              else if ("numeroPis".equals(propertyId)) {
                  TextField tf = (TextField) f;
                  tf.setNullRepresentation("");
                  tf.setNullSettingAllowed(true);
                  tf.setWidth("19em");
              }
              else if ("tempoDeEmpresa".equals(propertyId)){
            	  DateField dt = (DateField) f;
            	  dt.setWidth("19em");
            	  dt.setRequired(true);
            	  dt.setRequiredError("Campo Tempo de Empresa Invalido");
            	  dt.setDateFormat("dd-MM-yyyy");
            	  dt.setTabIndex(8);
              }
              else if ("dataNascimento".equals(propertyId)){
            	  DateField dt = (DateField) f;
            	  dt.setWidth("19em");
            	  dt.setRequired(true);
            	  dt.setRequiredError("Campo Data de Nascimento Invalido");
            	  dt.setDateFormat("dd-MM-yyyy");
            	  dt.setTabIndex(13);
              }
              return  f;
              
          }
         
       
      }

	public Funcionario getFuncionario() {
		funForm.commit();
		return funcionario;
	}

	public void limpa(){
		funForm.discard();
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Form getFuncionarioForm() {
		return funForm;
	}
      
      
   
}