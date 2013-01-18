package com.auditor.view.form;

import java.util.Arrays;

import com.auditor.model.ReferenciaServico;
import com.auditor.model.contato.PrestadoraTelefonia;
import com.auditor.model.contato.Telefone;
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

@SuppressWarnings({ "rawtypes","serial" })
public class TelefoneForm extends VerticalLayout {

	public Telefone telefone;
	public final Form telefoneForm;
	public ComboBox departamento;
	public ComboBox prestadora;
	public ComboBox telefoneReferencia;
	public ComboBox tipoDispositivo;
	public BeanItemContainer listDepartamento;
	public BeanItemContainer listReferencia;
	public BeanItemContainer listPrestadora;
	public TextField dddNumero;

    public TelefoneForm(Telefone telefone, BeanItemContainer departamentoList, BeanItemContainer referenciaList, BeanItemContainer prestadoraList) {
    	
    	try{
    	for (int i = 0; i < prestadoraList.size(); i++) {
    		PrestadoraTelefonia prestadora = (PrestadoraTelefonia) prestadoraList.getIdByIndex(i);
    		if (prestadora.getNome().contentEquals(telefone.getPrestadora().getNome())) {
    			prestadoraList.removeItem(prestadora);
    			prestadoraList.addItem(telefone.getPrestadora());
    			i = prestadoraList.size();
			}
		}
    	}catch (Exception e) {
		}
    	
    	
    	try{
    	for (int i = 0; i < referenciaList.size(); i++) {
    		ReferenciaServico ref = (ReferenciaServico) referenciaList.getIdByIndex(i);
    		if (ref.getTipo().contentEquals(telefone.getTelefoneReferencia().getTipo())) {
    			referenciaList.removeItem(ref);
    			referenciaList.addItem(telefone.getTelefoneReferencia());
    			i = referenciaList.size();
			}
		}
    	}catch (Exception e) {
		}
    	
    	this.listPrestadora = prestadoraList;
    	this.listReferencia = referenciaList;
    	this.listDepartamento = departamentoList;
    	this.telefone = telefone;
        BeanItem<Telefone> telefoneItem = new BeanItem<Telefone>(telefone); // item from
        																	// POJO
        // Create the Form
        telefoneForm = new FormWithComplexLayout(telefoneItem,"Telefone");

        // Add form to layout
        addComponent(telefoneForm);

        telefoneForm.getFooter().setMargin(true);
        telefoneForm.setWidth("-1px");
    }
    
    public Telefone getTelefone(){  
    	
    	try {
			telefone.setDddNumero(Long.parseLong(dddNumero.getValue().toString()));
		} catch (Exception e) {
			dddNumero.setRequiredError("DDD Número inválido");
			dddNumero.setValue("");
		}
    	
    	telefoneForm.commit();
    	return telefone; 
    }

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;
		private GridLayout ramalLayout;

        public FormWithComplexLayout(BeanItem<Telefone> telefoneItem, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(2, 6);
            ramalLayout = new GridLayout(2, 1);

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);
            
            ramalLayout.setSpacing(true);

            setLayout(ourLayout);

            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(telefoneItem); // bind to POJO via BeanItem
            
            ourLayout.addComponent(ramalLayout, 0, 0);
            
            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "ramal", "dddNumero", "tipo", "finalidade", "ativacao",
            		"obs", "telefoneReferencia", "departamento", "prestadora", "tipoDispositivo" }));
          
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("dddNumero")) {
                ramalLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("ramal")) {
                ramalLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("tipo")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("finalidade")) {
                ourLayout.addComponent(field, 0, 1);
            } else if (propertyId.equals("ativacao")) {
                ourLayout.addComponent(field, 0, 4);
            } else if (propertyId.equals("obs")) {
                ourLayout.addComponent(field, 0, 5, 1, 5);
            } else if (propertyId.equals("telefoneReferencia")) {
                ourLayout.addComponent(field, 0, 2);
            } else if (propertyId.equals("departamento")) {
                ourLayout.addComponent(field, 1, 2);
            } else if (propertyId.equals("prestadora")) {
                ourLayout.addComponent(field, 1, 1);
            }  else if (propertyId.equals("tipoDispositivo")) {
                 ourLayout.addComponent(field, 0, 3);
            }
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURAÇÃO DOS CAMPOS
		 */
		final ComboBox finalidade = new ComboBox("Finalidade");
		final ComboBox tipo = new ComboBox("Tipo");
		final TextArea obs = new TextArea("Observação");
		final TextField ramal = new TextField("Ramal");
						     
        public PersonFieldFactory() {
     
        	departamento = new ComboBox("Departamento");
        	departamento.setContainerDataSource(listDepartamento);
        	departamento.setItemCaptionPropertyId("nome");
	    	departamento.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
        	departamento.setWidth("19.7em");
            departamento.setRequired(true);
            departamento.setRequiredError("Por favor selecione um Departamento");
            departamento.setNullSelectionAllowed(false);
            
            prestadora = new ComboBox("Prestadora Telefonia");
            prestadora.setContainerDataSource(listPrestadora);
            prestadora.setItemCaptionPropertyId("nome");
        	prestadora.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            prestadora.setWidth("19.7em");
            prestadora.setRequired(true);
            prestadora.setRequiredError("Por favor selecione uma Prestadora Telefonia");
            prestadora.setNullSelectionAllowed(false);            
            
            telefoneReferencia = new ComboBox("Referência Servico");
            telefoneReferencia.setContainerDataSource(listReferencia);
            telefoneReferencia.setItemCaptionPropertyId("tipo");
        	telefoneReferencia.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            telefoneReferencia.setWidth("19.7em");
            telefoneReferencia.setRequired(true);
            telefoneReferencia.setRequiredError("Por favor selecione uma Referência Serviço");
            telefoneReferencia.setNullSelectionAllowed(false);
        	
        	tipo.setWidth("19.7em");
            tipo.addItem("Interno");
            tipo.addItem("Externo");
            tipo.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            tipo.setRequired(true);
            tipo.setRequiredError("Por favor selecione um Tipo");
            tipo.setNullSelectionAllowed(false);
            
            ramal.setWidth("5em");
            
            finalidade.setWidth("19.7em");
            finalidade.addItem("Interno");
            finalidade.addItem("Externo");
            finalidade.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            finalidade.setRequired(true);
            finalidade.setRequiredError("Por favor selecione uma Finalidade");
            finalidade.setNullSelectionAllowed(false);
            finalidade.setValue("Interno");
            
            tipoDispositivo = new ComboBox("Tipo de Linha");
            tipoDispositivo.setWidth("19.7em");
            tipoDispositivo.addItem("Fixo");
            tipoDispositivo.addItem("Móvel");
            tipoDispositivo.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            tipoDispositivo.setRequired(true);
            tipoDispositivo.setRequiredError("Por favor selecione uma Tipo de Linha");
            tipoDispositivo.setNullSelectionAllowed(false);
            
            obs.setCaption("Observação");
            obs.setWidth("39em");
            obs.setHeight("3.5em");
            
            dddNumero = new TextField("DDD Número");
            dddNumero.setCaption("DDD Número");
            dddNumero.setNullRepresentation("");
            dddNumero.setWidth("12.3em");
            dddNumero.setRequired(true);
            dddNumero.setRequiredError("Por favor insira um Número");
            dddNumero.addValidator(new StringLengthValidator("Número deve conter 8-15 caracteres", 
            		8, 15, false));
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("tipo".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return tipo;
                
            } else if ("finalidade".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return finalidade;
                
            } else if ("tipoDispositivo".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return tipoDispositivo;
                
            } else if ("ramal".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return ramal;
                
            } else if ("obs".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return obs;
                
            } else if ("dddNumero".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return dddNumero;
                
            } else if ("departamento".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return departamento;
                
            } else if ("prestadora".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return prestadora;
                
            } else if ("telefoneReferencia".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return telefoneReferencia;
                
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 

    public void readOnlyTrue(){
    	dddNumero.setReadOnly(true);
    }
    
    public void readOnlyFalse(){
    	dddNumero.setReadOnly(false);
    }
}