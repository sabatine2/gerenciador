package com.endereco.view;

import java.util.Arrays;
import com.endereco.model.Endereco;
import com.googlemaps.CepGeocoder;
import com.googlemaps.MapaSimples;
import com.principal.helper.TipoEstado;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings("serial")
public class EnderecoForm extends VerticalLayout {

	final Form enderecoForm;
	public Endereco enderecoAuditor;
	public EnderecoView enderecoView;
	public Button validar;
	public Button mapa;
	public TextField logradouro;
	public ComboBox estado;
	public TextField bairro;
	public TextField cidade;
	public TextField cep;
	public ComboBox listaEndereco;
	public ComboBox status;
	public ComboBox tipo;
	public TextArea obs;		
	public TextField numero;		
	public TextField complemento;
	
    public EnderecoForm(Endereco enderecoAuditor) {

    	this.enderecoAuditor = enderecoAuditor;
        BeanItem<Endereco> enderecoAuditorItem = new BeanItem<Endereco>(this.enderecoAuditor); // item from
        							
        // POJO
        // Create the Form
        enderecoForm = new FormWithComplexLayout(enderecoAuditorItem,"Endereco");
        
        // Add form to layout
        addComponent(enderecoForm);

        // The cancel / apply buttons
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        
        
        
        enderecoForm.getFooter().addComponent(buttons);
        enderecoForm.getFooter().setMargin(true);
        enderecoForm.setWidth("-1px");
    }

    public Endereco getEndereco(){
    	enderecoForm.commit();
    	return enderecoAuditor;
    }
    
    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;
		private GridLayout ourLayout2;
		private AbsoluteLayout buttonLayout;

        public FormWithComplexLayout(BeanItem<Endereco> enderecoAuditorItem, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(2, 7);
            ourLayout2 = new GridLayout(3, 1);

            buttonLayout = new AbsoluteLayout();
            
            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);
            
            //ourLayout2.setMargin(true, false, false, true);
            ourLayout2.setSpacing(true);
            
            setLayout(ourLayout);
            
            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(enderecoAuditorItem); // bind to POJO via BeanItem
            buttonLayout.addComponent(validar, "top:13px;left:0px;");
            buttonLayout.addComponent(mapa, "top:13px;left:77px;");
            ourLayout.addComponent(buttonLayout, 1, 2);
            
            ourLayout.addComponent(listaEndereco, 0, 0, 1, 0);
            ourLayout.addComponent(ourLayout2, 0, 4, 1, 4);
            
            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "tipo", "status", "logradouro", "numero", "bairro", "cep",
            		"complemento", "cidade", "estado", "obs"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("tipo")) {
                ourLayout.addComponent(field, 0, 1);
            } else if (propertyId.equals("status")) {
                ourLayout.addComponent(field, 1, 1);
            } else if (propertyId.equals("logradouro")) {
                ourLayout.addComponent(field, 0, 3, 1, 3);
            } else if (propertyId.equals("numero")) {
                ourLayout2.addComponent(field, 0, 0);
            } else if (propertyId.equals("bairro")) {
                ourLayout2.addComponent(field, 2, 0);
            } else if (propertyId.equals("cep")) {
                ourLayout.addComponent(field, 0, 2);
            } else if (propertyId.equals("complemento")) {
                ourLayout2.addComponent(field, 1, 0);
            } else if (propertyId.equals("cidade")) {
                ourLayout.addComponent(field, 0, 5);
            } else if (propertyId.equals("estado")) {
                ourLayout.addComponent(field, 1, 5);
            } else if (propertyId.equals("obs")) {
                ourLayout.addComponent(field, 0, 6, 1, 6);
            }
        }
    }
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURA��O DOS CAMPOS
		 */
        public PersonFieldFactory() {
     
        	tipo = new ComboBox("Tipo");
        	tipo.setWidth("100.0%");
            tipo.addItem("Residencial");
            tipo.addItem("Comercial");
            tipo.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            tipo.setRequired(true);
            tipo.setRequiredError("Por favor selecione um Tipo");
            tipo.setNullSelectionAllowed(false);
            tipo.setTabIndex(12);
            
            status = new ComboBox("Status");
            status.setWidth("19em");
            status.addItem("Ativo");
            status.addItem("Inativo");
            status.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            status.setRequired(true);
            status.setRequiredError("Por favor selecione uma Status");
            status.setNullSelectionAllowed(false);
            status.setTabIndex(13);
            
            listaEndereco = new ComboBox("Lista de Endere�os");
            //listaEndereco.setWidth("48em");
            listaEndereco.setWidth("100.0%");
            listaEndereco.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            listaEndereco.setContainerDataSource(Endereco.ListaBens());
            listaEndereco.setItemCaptionPropertyId("enderecoCompleto");
            listaEndereco.setNullSelectionAllowed(false);
            listaEndereco.setImmediate(true);
            listaEndereco.setTabIndex(10);
            listaEndereco.addListener( new Property.ValueChangeListener() {
            	
				public void valueChange(ValueChangeEvent event) {
				    Endereco end  = (Endereco) event.getProperty().getValue();
				    
				    enderecoAuditor = end;
				    
				    logradouro.setValue(end.getLogradouro());
				    bairro.setValue(end.getBairro());
				    cep.setValue(end.getCep());
				    numero.setValue(end.getNumero());
				    cidade.setValue(end.getCidade());
				    estado.setValue(end.getEstado());
				    tipo.setValue(end.getTipo());
				}
			});
            
            obs = new TextArea("Observa��o");		
            obs.setWidth("100.0%");
            obs.setTabIndex(23);
                      
            validar = new Button("Validar", new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                	
                    try {
    					if (((String) cep.getValue()).length() == 8) {
    						CepGeocoder cepG = new CepGeocoder(cep.toString());
    						int result = Integer.parseInt(cepG.getCepService().getResultado().toString());
    						
    						if (result == 1) {
    							bairro.setValue(cepG.getCepService().getBairro());
    							cidade.setValue(cepG.getCepService().getCidade());
    							logradouro.setValue(cepG.getCepService().getLogradouro());
    							estado.setValue(cepG.getCepService().getUf());
    							
    						}
    						else {
    							MessageBox mb = new MessageBox(getWindow(), 
    									"Erro", 
    					                MessageBox.Icon.ERROR, 
    					                "CEP n�o encontrado",  
    					                new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
    					      	mb.show(); 
    						}
    					}
    					else {
    					}
                    } catch (Exception e) {
                    	
                    }
                }
            });
            validar.setTabIndex(15);
            
            mapa = new Button("Carregar Mapa", new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                	
                	try {
                    	enderecoForm.commit();
                    	enderecoAuditor.getCoodernadas();
                    	MapaSimples mapa = new MapaSimples(enderecoAuditor.getLongitude(), enderecoAuditor.getLatitude());
                    	getWindow().addWindow(mapa);
					} catch (Exception e) {
						//enderecoView.msnErro(e);
						System.out.println("ERRO: "+e.getMessage());
					}
                }
            });
            mapa.setTabIndex(16);
                       
            estado = new ComboBox("Estado");
            estado.setWidth("19em");
            estado.setContainerDataSource(TipoEstado.getTipoEstadoContainer());
            estado.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            estado.setRequired(true);
            estado.setRequiredError("Por favor selecione um Estado");
            estado.setNullSelectionAllowed(false);
            estado.addValidator(new StringLengthValidator(
                    "Por favor selecione um Estado", 1, 50, false));
            estado.setTabIndex(22);
            
            logradouro = new TextField("Logradouro");
            logradouro.setWidth("40em");
            logradouro.setCaption("Logradouro");
            logradouro.setRequired(true);
            logradouro.setRequiredError("Por favor insira um Logradouro");
            logradouro.addValidator(new StringLengthValidator(
                    "Logradouro deve conter 2-50 caracteres", 2, 50, false));
            logradouro.setTabIndex(17);
            
            numero = new TextField("N�mero");		
            numero.setWidth("5em");
            numero.setCaption("N�mero");
            numero.setRequired(true);
            numero.setRequiredError("Por favor insira um N�mero");
            numero.addValidator(new StringLengthValidator(
                    "N�mero deve conter 1-6 caracteres", 1, 6, false));
            numero.setTabIndex(18);
            
            bairro = new TextField("Bairro");
            bairro.setWidth("18.4em");
            bairro.setCaption("Bairro");
            bairro.setRequired(true);
            bairro.setRequiredError("Por favor insira um Bairro");
            bairro.addValidator(new StringLengthValidator(
                    "Bairro deve conter 2-50 caracteres", 2, 50, false));
            bairro.setTabIndex(20);
            
            cep = new TextField("CEP");
            cep.setWidth("100.0%");
            cep.setCaption("CEP");
            cep.setRequired(true);
            cep.setRequiredError("Por favor insira um CEP");
            cep.addValidator(new StringLengthValidator(
                    "CEP deve conter 8 caracteres", 8, 8, false));
            cep.setTabIndex(14);
            
            complemento = new TextField("complemento");
            complemento.setWidth("13.4em");
            complemento.setCaption("Complemento");
            complemento.setTabIndex(19);
            
            cidade = new TextField("Cidade");
            cidade.setWidth("20em");
            cidade.setCaption("Cidade");
            cidade.setRequired(true);
            cidade.setRequiredError("Por favor insira um Cidade");
            cidade.addValidator(new StringLengthValidator(
                    "Cidade deve conter 2-20 caracteres", 2, 20, false));
            cidade.setTabIndex(21);
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("tipo".equals(propertyId)) {
                return tipo;
                
            } else if ("status".equals(propertyId)) {
                return status;
                
            } else if ("logradouro".equals(propertyId)) {
                return logradouro;
                
            } else if ("numero".equals(propertyId)) {
                return numero;
                
            } else if ("bairro".equals(propertyId)) {
                return bairro;
                
            } else if ("cep".equals(propertyId)) {
                return cep;
                
            } else if ("complemento".equals(propertyId)) {
                return complemento;
                
            } else if ("cidade".equals(propertyId)) {
                return cidade;
                
            } else if ("estado".equals(propertyId)) {
                return estado;
                
            } else if ("obs".equals(propertyId)) {
                return obs;
                
            } else if ("validar".equals(propertyId)) {
                return validar;
                
            } else if ("mapa".equals(propertyId)) {
                return mapa;
                
            } else if ("listaEndereco".equals(propertyId)) {
                return listaEndereco;
                
            } else {
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    }
    
    public void buttonOff(){
    	
    	validar.setVisible(false);
    	mapa.setVisible(true);
    }
}