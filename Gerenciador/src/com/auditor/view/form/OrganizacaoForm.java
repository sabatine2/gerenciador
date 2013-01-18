package com.auditor.view.form;

import java.util.Arrays;

import com.auditor.model.Organizacao;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
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
import com.view.componente.ImagemLoad;

@SuppressWarnings("serial")
public class OrganizacaoForm extends VerticalLayout {

	public Organizacao organizacao;
	final Form organizacaoForm;
	public ImagemLoad logo;
	public TextField nomeFantasia;

    public OrganizacaoForm(Organizacao organizacao) {

        this.organizacao = organizacao;
        BeanItem<Organizacao> organizacaoItem = new BeanItem<Organizacao>(organizacao); // item from
        																                // POJO
        // Create the Form
        organizacaoForm = new FormWithComplexLayout(organizacaoItem,"Organizacao");

        // Add form to layout
        addComponent(organizacaoForm);
        
        organizacaoForm.getFooter().setMargin(true);
        organizacaoForm.setWidth("-1px");
    }

    public Organizacao getOrganizacao(){
    	organizacaoForm.commit();    	
    	return organizacao;     	
    }    
    
    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Organizacao> organizacaoItem, String titulo) {
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
            setItemDataSource(organizacaoItem); // bind to POJO via BeanItem
            //ourLayout.addComponent(logo, 0, 6, 1, 6);

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "nomeFantasia", "razaoSocial", "cnpj",
            		"inscricaoEstadual", "inscricaoMunicipal", "cnaeFiscal",
            		"inscricaoEstadualSubstTributario", "regimeTributario", "telefone",
            "email", "logo"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nomeFantasia")) {
                ourLayout.addComponent(field, 0, 0, 1, 0);
            } else if (propertyId.equals("razaoSocial")) {
                ourLayout.addComponent(field, 0, 1, 1, 1);
            } else if (propertyId.equals("cnpj")) {
                ourLayout.addComponent(field, 0, 2);
            } else if (propertyId.equals("inscricaoEstadual")) {
                ourLayout.addComponent(field, 1, 2);
            } else if (propertyId.equals("inscricaoMunicipal")) {
                ourLayout.addComponent(field, 0, 3);
            } else if (propertyId.equals("cnaeFiscal")) {
                ourLayout.addComponent(field, 1, 3);
            } else if (propertyId.equals("inscricaoEstadualSubstTributario")) {
                ourLayout.addComponent(field, 0, 4);
            } else if (propertyId.equals("regimeTributario")) {
                ourLayout.addComponent(field, 1, 4);
            } else if (propertyId.equals("email")) {
                ourLayout.addComponent(field, 0, 5);
            } else if (propertyId.equals("telefone")) {
                ourLayout.addComponent(field, 1, 5);
            } 
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURA��O DOS CAMPOS
		 */
    	final ComboBox regimeTributario = new ComboBox("Regime Tributario");
    	final TextField inscricaoEstadualSubstTributario = new TextField("Inscriao Estadual Substituto Tributario");
    	//final TextField nomeFantasia = new TextField("Nome Fantasia");
    	final TextField razaoSocial = new TextField("Razao Social");
    	final TextField cnpj = new TextField("CNPJ");
    	final TextField inscricaoEstadual = new TextField("Inscricao Estadual");
    	final TextField inscricaoMunicipal = new TextField("Inscricao Municipal");
    	final TextField cnaeFiscal = new TextField("CNAE Fiscal");
    	final TextField email = new TextField("Email");
    	final TextField telefone = new TextField("Telefone");
    	
        public PersonFieldFactory() {
            
            regimeTributario.setWidth("19.7em");
            regimeTributario.addItem("Lucro Real");
            regimeTributario.addItem("Lucro Presumido");
            regimeTributario.addItem("Simples Nacional");
            regimeTributario.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            regimeTributario.setRequired(true);
            regimeTributario.setRequiredError("Por favor selecione um Regime Tributario");
            regimeTributario.setNullSelectionAllowed(false);
            regimeTributario.addValidator(new StringLengthValidator(
                    "Por favor selecione um Regime Tributario", 1, 50, false));
            
            inscricaoEstadualSubstTributario.setWidth("19em");
            nomeFantasia = new TextField("Nome Fantasia");
            nomeFantasia.setWidth("40em");
            nomeFantasia.setRequired(true);
            nomeFantasia.setRequiredError("Por favor insira um Nome Fantasia");
            nomeFantasia.addValidator(new StringLengthValidator(
                    "Nome Fantasia deve conter 2-50 caracteres", 2, 50, false));
            
            razaoSocial.setWidth("40em");
            razaoSocial.setRequired(true);
            razaoSocial.setRequiredError("Por favor insira uma Razao Social");
            razaoSocial.addValidator(new StringLengthValidator(
                    "Raz�o Social deve conter 2-50 caracteres", 2, 50, false));
            
            telefone.setWidth("19em");
            telefone.setRequired(true);
            telefone.setRequiredError("Por favor insira um Telefone");
            telefone.addValidator(new StringLengthValidator(
                    "Telefone deve conter 8 caracteres", 8, 8, false));
            
            cnpj.setWidth("19em");
            cnpj.setRequired(true);
            cnpj.setRequiredError("Preencha corretamento CNPJ");
            cnpj.addValidator(new Validator() {
				
				public void validate(Object value) throws InvalidValueException {
					System.out.println(value.toString());

				}
				
				public boolean isValid(Object value) {
					String cpf = value.toString();
					
					if (cpf.length() == 11) {
						int acumulador1 = 0;
						int resto1 = 0;
						
						for (int i = 0; i < 9; i++) {
						
							acumulador1 += Character.getNumericValue(cpf.charAt(i)) * (10-i);
							System.out.println("Acumulador1: " + acumulador1);
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
								System.out.println("Acumulador2: " + acumulador2);
							}
							resto2 = 11 - acumulador2 %11;
							
							if (resto2 == 10) {
								resto2 = 0;
							}
							
							if (resto2 == Character.getNumericValue(cpf.charAt(10))) {
								
								System.out.println("Digito1: " + resto1);
								System.out.println("Digito2: " + resto2);
								return true;
							}
							else {
								System.out.println("ERRO CPF INVALIDO 2");
								PersonFieldFactory.this.cnpj.setValue("");
								//Mensagem.msgErro(usuarioView.getComponent(), "Erro", "CPF inv�lido");
								return false;
							}
						}
						else {
							System.out.println("ERRO CPF INVALIDO 1");
							PersonFieldFactory.this.cnpj.setValue("");
							//Mensagem.msgErro(usuarioView.getComponent(), "Erro", "CPF inv�lido");
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
								
								System.out.println("D�gito1: " + resto1);
								System.out.println("D�gito2: " + resto2);
								return true;
							}
							else {
								System.out.println("ERRO CNPJ INVALIDO 2");
								//Mensagem.msgErro(usuarioView.getComponent(), "Erro", "CNPJ inv�lido");
								return false;
							}
						}
						else {
							System.out.println("ERRO CNPJ INVALIDO 1");
							//Mensagem.msgErro(usuarioView.getComponent(), "Erro", "CNPJ inv�lido");
							return false;
						}
					}
					else {
						return false;
					}
				}
			});
            
            inscricaoEstadual.setWidth("19em");
            inscricaoEstadual.setRequired(true);
            inscricaoEstadual.setRequiredError("Por favor insira uma Inscri��o Estadual");
            inscricaoEstadual.addValidator(new StringLengthValidator(
                    "Inscri��o Estadual deve conter 2-20 caracteres", 2, 20, false));
            
            inscricaoMunicipal.setWidth("19em");
            inscricaoMunicipal.setRequired(true);
            inscricaoMunicipal.setRequiredError("Por favor insira uma Inscri��o Municipal");
            inscricaoMunicipal.addValidator(new StringLengthValidator(
                    "Inscri��o Municipal deve conter 2-20 caracteres", 2, 20, false));
            
            cnaeFiscal.setWidth("19em");
            cnaeFiscal.setRequired(true);
            cnaeFiscal.setRequiredError("Por favor insira um CNAE Fiscal");
            cnaeFiscal.addValidator(new StringLengthValidator(
                    "CNAE Fiscal deve conter 2-20 caracteres", 2, 20, false));
            
            email.setWidth("19em");
            email.setRequired(true);
            email.setRequiredError("Por favor insira um Email");
            email.addValidator(new StringLengthValidator(
                    "Email deve conter 2-50 caracteres", 2, 50, false));
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
             if ("inscricaoEstadualSubstTributario".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return inscricaoEstadualSubstTributario;
            	
            } else if ("regimeTributario".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return regimeTributario;
            	
            } /*else if ("logo".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return (Field) logo;
            	
            } */else if ("nomeFantasia".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return nomeFantasia;
            	
            } else if ("razaoSocial".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return razaoSocial;
            	
            } else if ("cnpj".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return cnpj;
            	
            } else if ("inscricaoEstadual".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return inscricaoEstadual;
            	
            } else if ("inscricaoMunicipal".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return inscricaoMunicipal;
            	
            } else if ("cnaeFiscal".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return cnaeFiscal;
            	
            } else if ("email".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return email;
            	
            } else if ("telefone".equals(propertyId)) {
                // filtering ComboBox w/ country names
            	return telefone;
            	
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
}