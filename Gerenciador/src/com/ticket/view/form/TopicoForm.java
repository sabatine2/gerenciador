package com.ticket.view.form;

import java.math.BigDecimal;
import java.util.Arrays;

import com.ticket.helper.GrauDificuldade;
import com.ticket.helper.TipoPrioridade;
import com.ticket.model.Topico;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TopicoForm extends VerticalLayout {

	public com.ticket.model.Topico topico;
	public final Form topicoForm;
	
	public TextField nome;
	public RichTextArea descricao;
	public ComboBox relevancia;
	public ComboBox prioridade;
	public TextField custo;
	public ComboBox status;
	public TextArea observacao;

    public TopicoForm(Topico topico) {

    	this.topico = topico;
        BeanItem<Topico> topicoItem = new BeanItem<Topico>(topico); 
        
        // Create the Form
        topicoForm = new FormWithComplexLayout(topicoItem,"Topicos de Serviço");
        topicoForm.setWidth("-1px");

        // Add form to layout
        addComponent(topicoForm);

        topicoForm.getFooter().setMargin(true);
    }
    
    public Topico getTopico(){
    	
    	try {
    		topico.setCusto(BigDecimal.valueOf(Double.parseDouble(custo.getValue().toString())));
		} catch (Exception e) {
			custo.setRequiredError("Custo inv�lido");
			custo.setValue("");
		}
    	
    	topicoForm.commit();
    	return topico; 	
    }

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		private GridLayout ourLayout;

        public FormWithComplexLayout(BeanItem<Topico> topicoItem, String titulo) {
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
            setItemDataSource(topicoItem); // bind to POJO via BeanItem

            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] { "nome", "status", "descricao", "relevancia", "prioridade", "custo", "observacao"}));
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("status")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("descricao")) {
                ourLayout.addComponent(field, 0, 1, 1, 1);
            } else if (propertyId.equals("relevancia")) {
                ourLayout.addComponent(field, 0, 2);
            } else if (propertyId.equals("prioridade")) {
                ourLayout.addComponent(field, 1, 2);
            } else if (propertyId.equals("custo")) {
                ourLayout.addComponent(field, 0, 3);
            } else if (propertyId.equals("observacao")) {
                ourLayout.addComponent(field, 0, 4, 1, 4);
            }
        }
    }
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURA��O DOS CAMPOS
		 */
        public PersonFieldFactory() {
     
        	nome = new TextField("Nome");
        	nome.setWidth("19em");
        	nome.setRequired(true);
        	nome.setRequiredError("Por favor insira um Nome");
        	nome.addValidator(new StringLengthValidator(
                    "Nome deve conter 2-50 caracteres", 2, 50, false));
        	nome.setTabIndex(1);
        	
        	status = new ComboBox("status");
	        status.setWidth("19.7em");
            status.addItem("Ativo");
            status.addItem("Inativo");
            status.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            status.setRequired(true);
            status.setRequiredError("Por favor selecione Status");
            status.setNullSelectionAllowed(false);
            status.setTabIndex(2);
            
            descricao = new RichTextArea("Descri��o");
            descricao.setWidth("40em");
            descricao.setTabIndex(3);
            
            relevancia = new ComboBox("Dificuldade");
	        relevancia.setWidth("19.7em");
	        relevancia.setContainerDataSource(GrauDificuldade.getGrauDificuldadeContainer());
            relevancia.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            relevancia.setRequired(true);
            relevancia.setRequiredError("Por favor selecione Relevancia");
            relevancia.setNullSelectionAllowed(false);
            relevancia.setTabIndex(4);
            
            prioridade = new ComboBox("Prioridade");
	        prioridade.setWidth("19.7em");
	        prioridade.setContainerDataSource(TipoPrioridade.getTipoPrioridadeContainer());
            prioridade.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            prioridade.setRequired(true);
            prioridade.setRequiredError("Por favor selecione Prioridade");
            prioridade.setNullSelectionAllowed(false);
            prioridade.setTabIndex(5);
            
            custo = new TextField("Custo");
        	custo.setWidth("19em");
        	custo.setNullRepresentation("");
        	custo.setRequired(true);
        	custo.setRequiredError("Por favor insira um custo");
        	custo.addValidator(new StringLengthValidator(
                    "Custo invalido", 1, 50, false));
        	custo.setTabIndex(6);
            
            observacao = new TextArea("Observação");
            observacao.setWidth("40em");
            observacao.setTabIndex(7);
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
                
            }  else if ("descricao".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return descricao;
                
            }  else if ("relevancia".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return relevancia;
                
            }  else if ("prioridade".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return prioridade;
                
            }  else if ("custo".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return custo;
                
            }  else if ("observacao".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return observacao;
                
            } else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
}