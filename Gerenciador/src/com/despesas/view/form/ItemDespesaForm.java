package com.despesas.view.form;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

import com.despesas.model.ItensDespesas;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings({"serial"})
public class ItemDespesaForm extends VerticalLayout {

	public final Form itensDespesaForm;
	public ItensDespesas itens;
	public TextField nome;
	public TextField valor;
	public TextField deducao;
	public TextArea observacao;
	private GridLayout ourLayout;
	private NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	public ItemDespesaForm(ItensDespesas itens) {
    	
    	this.itens = itens;
        BeanItem<ItensDespesas> item = new BeanItem<ItensDespesas>( itens );
        
        // Create the Form
        itensDespesaForm = new FormWithComplexLayout( item,"Item Despesa" );
        
        // Add form to layout
        addComponent( itensDespesaForm );

        itensDespesaForm.getFooter().setMargin(true);
        itensDespesaForm.setWidth("-1px");
    }

    public ItensDespesas getItens(){
    	//try {
    		ItensDespesas itens = new ItensDespesas();
    		
    		itens.setNome(nome.getValue().toString());
    		itens.setValor(new BigDecimal(converter(valor.getValue().toString())));
    		itens.setDeducao(new BigDecimal(converter(deducao.getValue().toString())));
    		itens.setObervacao(observacao.getValue().toString());
        	//itens.salvar();
    		//itensDespesaForm.commit();
        	return itens;    	
		/*}
    	catch (Exception e) {
    		System.out.println("ErroCommit: "+e.getMessage());
        	return null;    	
		}*/
    }
    
    public String converter(String valor){
		valor = valor.replace("R$", "");
		valor = valor.replace(" ", "");
		
		if (valor.contains(",")) {
			valor = valor.replace(".", "");
			valor = valor.replace(",", ".");			
		}
		return valor;
	}
    
    public void limpa(){
    	
    	itensDespesaForm.discard();
    }

	public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */

        public FormWithComplexLayout(BeanItem<ItensDespesas> item, String titulo) {
            setCaption(titulo);

            // Create our layout (3x3 GridLayout)
            ourLayout = new GridLayout(2, 4);

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);
            
            setLayout(ourLayout);

            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(item); // bind to POJO via BeanItem
            
            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] {"nome", "observacao",}));
        
            ourLayout.addComponent(valor, 0, 1);
            ourLayout.addComponent(deducao, 1, 1);
            ourLayout.addComponent(observacao, 0, 2, 1, 2);
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("nome")) {
                ourLayout.addComponent(field, 0, 0, 1, 0);
            } else if (propertyId.equals("imagem")) {
                ourLayout.addComponent(field, 0, 3, 1, 3 );
            }
        }
    }
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURA��O DOS CAMPOS
		 */
	    public PersonFieldFactory() {
	    	
	    	nome = new TextField("Nome");
	    	nome.setWidth("40em");
	    	nome.setRequired(true);
	        nome.setRequiredError("Por favor insira um Nome");
	        nome.setTabIndex(30);
	    	
	        valor = new TextField("Valor");
	    	valor.setWidth("19em");
	    	valor.setRequired(true);
	        valor.setRequiredError("Por favor insira um Valor");
	        valor.setNullRepresentation("");
            valor.setTabIndex(31);
            valor.setImmediate(true);
            valor.setValue("R$ 0,00");
            valor.addListener(new Property.ValueChangeListener() {  
    			public void valueChange(ValueChangeEvent event) {
    				try {
						BigDecimal value = new BigDecimal(converter(valor.getValue().toString()));
						valor.setValue(nf.format(value));
						value = null;
					} 
    				catch (Exception e) {
						//valor.setValue("R$ 0,00");
					}
    			}
    		});
            
            deducao = new TextField("Dedu�ao");
	    	deducao.setWidth("19em");
	    	deducao.setRequired(true);
	        deducao.setRequiredError("Por favor insira um Dedu�ao");
	        deducao.setNullRepresentation("");
            deducao.setTabIndex(32);
            deducao.setImmediate(true);
            deducao.setValue("R$ 0,00");
            deducao.addListener(new Property.ValueChangeListener() {  
    			public void valueChange(ValueChangeEvent event) {
    				try {
						BigDecimal value = new BigDecimal(converter(deducao.getValue().toString()));
						deducao.setValue(nf.format(value));
						value = null;
					} 
    				catch (Exception e) {
						//valor.setValue("R$ 0,00");
					}
    			}
    		});
            
            observacao = new TextArea("Observa�ao");
            observacao.setWidth("40em");
            observacao.setTabIndex(33);
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("nome".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return nome;
                
            } else if ("valor".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return valor;
                
            } else if ("deducao".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return deducao;
                
            } else if ("observacao".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return observacao;
                
            } else {
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    }
}