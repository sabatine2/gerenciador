package com.ticket.view.form;


import java.util.Arrays;
import org.vaadin.hene.expandingtextarea.ExpandingTextArea;
import com.ticket.model.Mensagem;
import com.ticket.model.Nota;
import com.ticket.model.Ordem;
import com.ticket.model.Prioridade;
import com.ticket.view.NotaView;
import com.ticket.view.RespostaView;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class OrdemForm extends VerticalLayout {

	private Ordem ordem;
	private Form ordemForm;
	private ExpandingTextArea assunto;
	private ComboBox  fonte;
	private ComboBox  prioridade;
	private ComboBox  status;
	private ClienteOrdemView clienteOrdem;
	private FuncionarioOrdemView funcionarioOrdem;
	private RespostaView respostaView;
	private NotaView     notaView;
	
	
	public OrdemForm(Ordem ordem, String modo) {

    	this.ordem = ordem;
        BeanItem<Ordem> ordemItem = new BeanItem<Ordem>(ordem); 
        
        // Create the Form
        ordemForm = new FormWithComplexLayout(ordemItem,"Ordem Servico", modo);
        ordemForm.setWidth("100%");
       
        // Add form to layout
        addComponent(ordemForm);

    }
	  
    public Ordem getOrdem(){
   
     	ordem.setContato(clienteOrdem.getContato());
     
     	ordemForm.commit();
        respostaView.commit();
    	notaView.commit();
    	funcionarioOrdem.commit();
    	
    	ordem.setDepartamento(funcionarioOrdem.getDepartamento());
    	
    	Mensagem consulta = new Mensagem(ordem.getAssunto());
    	consulta.setOrdem(ordem);
    	consulta.setTipo("Consulta");
    	ordem.getMensagem().add(consulta);
    	
    	Mensagem resposta = respostaView.getResposta();
    	resposta.setOrdem(ordem);
    	resposta.setTipo("Resposta");
    	ordem.getMensagem().add(resposta);
    	
    	Nota nota = notaView.getNota();
    	nota.setOrdem(ordem);
    	ordem.getNota().add(nota);
    	
    	return ordem; 	
    }
    
   

    public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */
		public GridLayout ourLayout;
        public String modo;
        
        public FormWithComplexLayout(BeanItem<Ordem> ordemItem, String titulo, String modo) {
            
        	this.modo = modo;
        	setCaption(titulo);
            ourLayout = new GridLayout(3, 11);
            ourLayout.setWidth("100%");
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);
            setLayout(ourLayout);

            setWriteThrough(false); 
            setInvalidCommitted(false); 
            setFormFieldFactory(new PersonFieldFactory());
            setItemDataSource(ordemItem); 
            setVisibleItemProperties(Arrays.asList(new String[] { "fonte", "status","assunto","prioridade"}));
            if(modo.contains("inserir")){
            	modoInserir();
            }else if(modo.contains("editar")){
            	modoEditar();
            }else if(modo.contains("visualizar")){
            	modoVisualizar();
            }
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("assunto")) {
                ourLayout.addComponent(field, 0, 1, 2, 1);
            } else if (propertyId.equals("status")) {
                ourLayout.addComponent(field, 1, 0);
            } else if (propertyId.equals("fonte")) {
                ourLayout.addComponent(field, 0, 0);
            } else if (propertyId.equals("prioridade")) {
                ourLayout.addComponent(field, 2, 0);
            }   
        }
        
        public void modoInserir(){
        	clienteOrdem = new ClienteOrdemView(ordem.getContato(), modo);
        	
        	funcionarioOrdem = new FuncionarioOrdemView(ordem.getDepartamento());
        	funcionarioOrdem.adicionar();
        	
        	notaView = new NotaView(null,new Nota());
        	notaView.setTabIndex(9);
        	
        	notaView.modoInserir();
        	
        	respostaView = new RespostaView(null,new Mensagem());
        	respostaView.setTabIndex(8);
        	
        	respostaView.modoInserir();
        	
            ourLayout.addComponent(clienteOrdem, 0, 3,2,3);
            ourLayout.addComponent(respostaView, 0, 5,2,5);
            ourLayout.addComponent(notaView, 0, 7,2,7);
            ourLayout.addComponent(funcionarioOrdem, 0, 9,2,9);
        }
        
        public void modoVisualizar(){
        	ourLayout.setRows(7);
        	clienteOrdem = new ClienteOrdemView(ordem.getContato(),modo);
        	funcionarioOrdem = new FuncionarioOrdemView(ordem.getDepartamento());
        	funcionarioOrdem.visualizar();
            ourLayout.addComponent(clienteOrdem, 0, 3,2,3);
        	ourLayout.addComponent(funcionarioOrdem, 0, 5,2,5);
       
        }
      
        public void modoEditar(){
        	clienteOrdem = new ClienteOrdemView(ordem.getContato(),modo);
        	funcionarioOrdem = new FuncionarioOrdemView(ordem.getDepartamento());
            ourLayout.addComponent(clienteOrdem, 0, 3,2,3);
            ourLayout.addComponent(funcionarioOrdem, 0, 6,2,6);
        }
    }
    
    
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURA��O DOS CAMPOS
		 */
        public PersonFieldFactory() {
        	
        	assunto = new ExpandingTextArea("Assunto");
        	assunto.setRequired(true);
        	assunto.setWidth("100%");
         	assunto.setRequiredError("Por favor insira um assunto");
        	assunto.addValidator(new StringLengthValidator(
                    "Assunto deve conter 2-5000 caracteres", 2, 5000, false));
        	assunto.setTabIndex(8);
        	
        	fonte = new ComboBox("Fonte");
 	        fonte.setWidth("19.7em");
 	        fonte.addItem("Web");
            fonte.addItem("Telefone");
            fonte.addItem("Zabbix");
            fonte.addItem("Email");
            fonte.addItem("Interno");
            fonte.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            fonte.setRequired(true);
            fonte.setRequiredError("Por favor selecione uma Fonte");
            fonte.setTabIndex(5);
            
        	status = new ComboBox("Status");
	        status.setWidth("19.7em");
            status.addItem("Aberto");
            status.addItem("Em andamento");
            status.addItem("Concluido");
            status.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            status.setRequired(true);
            status.setRequiredError("Por favor selecione Status");
            status.setTabIndex(6);
            
            
            prioridade = new ComboBox("Prioridade");
	        prioridade.setWidth("19.7em");
	        prioridade.setContainerDataSource(Prioridade.listaBeans());
            prioridade.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
            prioridade.setRequired(true);
            prioridade.setRequiredError("Por favor selecione Prioridade");
            prioridade.setNullSelectionAllowed(false);
            prioridade.setTabIndex(7);
            
           
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
                     
            if ("assunto".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return assunto;
                
            } else if ("status".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return status;
                
            }  else if ("fonte".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return fonte;
                
            }  else if ("prioridade".equals(propertyId)) {
                // filtering ComboBox w/ country names
                return prioridade;
                
            }  else {
                // Use the super class to create a suitable field base on the
                // property type.
                f = super.createField(item, propertyId, uiContext);
            }
            return f;
        }
    } 
}