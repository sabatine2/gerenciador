package com.telefone.view;
import java.util.Date;
import java.util.Iterator;

import com.telefone.controller.TelefoneController;
import com.telefone.model.TelefoneContato;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;
import de.steinwedel.vaadin.MessageBox.EventListener;

@SuppressWarnings("serial")
public class TelefoneView extends AbsoluteLayout {

	private ComboBox comboBoxOperadora;
	private ComboBox comboBoxTipoTelefone;
	private TextField textFieldNumeroTelefone;
	private TextField textFieldRamal;
	private TextField textFieldFinalidade;
	private TextArea  textAreaObs;
	
	private Button buttonRemoverTelefone;
	private Button buttonLigar;
	private Button buttonInserirTelefone;
	
	private TelefoneContato telefoneContato;
	protected TelefoneController telefoneController;
	
	public TelefoneView(TelefoneContato telefoneContato, final TelefoneController telefoneController) {
	
	    this.telefoneController = telefoneController;
		this.telefoneContato = telefoneContato;
		
		setWidth("630px");
		setHeight("85px");
		setMargin(false);
			
		// textFieldNumeroTelefone
		textFieldNumeroTelefone = new TextField();
		textFieldNumeroTelefone.setInputPrompt("DDD + N�mero");
		textFieldNumeroTelefone.setImmediate(false);
		textFieldNumeroTelefone.setWidth("120px");
		textFieldNumeroTelefone.setHeight("-1px");
		textFieldNumeroTelefone.setTabIndex(2);
		textFieldNumeroTelefone.setMaxLength(11);
		if(telefoneContato.getDddNumero() != null){
		textFieldNumeroTelefone.setValue(telefoneContato.getDddNumero());
		}
		addComponent(textFieldNumeroTelefone,
				"top:1.0px;left:20.0px;");
		
		// textFieldRamal
		textFieldRamal = new TextField();
		textFieldRamal.setInputPrompt("Ramal");
		textFieldRamal.setImmediate(false);
		textFieldRamal.setWidth("40px");
		textFieldRamal.setHeight("-1px");
		textFieldRamal.setTabIndex(3);
		textFieldRamal.setMaxLength(4);
		if(telefoneContato.getRamal() != null){
		textFieldRamal.setValue(telefoneContato.getRamal());
		}
		addComponent(textFieldRamal, "top:1.0px;left:153.0px;");
		
	    // comboBoxTipoTelefone
		comboBoxTipoTelefone = new ComboBox();
		comboBoxTipoTelefone.setInputPrompt("Tipo de Telefone");
		comboBoxTipoTelefone.addItem("Comercial");
		comboBoxTipoTelefone.setImmediate(false);
		comboBoxTipoTelefone.setWidth("135px");
		comboBoxTipoTelefone.setHeight("-1px");
		comboBoxTipoTelefone.setTabIndex(4);
		comboBoxTipoTelefone.setValue(telefoneContato.getTipo());
		addComponent(comboBoxTipoTelefone,
				"top:1.0px;left:212.0px;");
		
		// comboBoxOperadora
		comboBoxOperadora = new ComboBox();
		comboBoxOperadora.setInputPrompt("Operadora");
		comboBoxOperadora.addItem("Teste");
		comboBoxOperadora.setImmediate(false);
		comboBoxOperadora.setWidth("135px");
		comboBoxOperadora.setHeight("-1px");
		comboBoxOperadora.setTabIndex(5);
		comboBoxOperadora.setValue(telefoneContato.getPrestadora());
		addComponent(comboBoxOperadora, "top:1.0px;left:365.0px;");
		
	    textFieldFinalidade = new TextField();
	    textFieldFinalidade.setInputPrompt("Finalidade");
	    textFieldFinalidade.setImmediate(false);
	    textFieldFinalidade.setWidth("120px");
	    textFieldFinalidade.setHeight("-1px");
	    textFieldFinalidade.setImmediate(true);
	    addComponent(textFieldFinalidade,"top:30.0px;left:20.0px;");
	        
		textAreaObs = new TextArea();
		textAreaObs.setRows(2);
		textAreaObs.setInputPrompt("Observa��o");
		textAreaObs.setColumns(16);
		textAreaObs.setImmediate(true);
        addComponent(textAreaObs,"top:30.0px;left:153.0px;");

      
		Resource res = new ThemeResource("../reindeer/Icons/binocular.png");
		buttonLigar = new Button();
		buttonLigar.setIcon(res);
		buttonLigar.setDescription("Visualizar");
		buttonLigar.setImmediate(false);
		buttonLigar.setWidth("-1px");
		buttonLigar.setHeight("-1px");
		buttonLigar.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	ligar();
            }
        });
		addComponent(buttonLigar,"top:50.0px;left:370.0px;");
	
		res = new ThemeResource("../reindeer/Icons/database.png");
		buttonInserirTelefone = new Button();
		buttonInserirTelefone.setIcon(res);
		buttonInserirTelefone.setDescription("Gravar");
		buttonInserirTelefone.setImmediate(false);
		buttonInserirTelefone.setWidth("-1px");
		buttonInserirTelefone.setHeight("-1px");
		buttonInserirTelefone.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	gravar();
            }
        });
		addComponent(buttonInserirTelefone,"top:50.0px;left:420.0px;");
	
		res = new ThemeResource("../reindeer/Icons/minus.png");
		buttonRemoverTelefone = new Button();
		buttonRemoverTelefone.setIcon(res);
		buttonRemoverTelefone.setDescription("Remover");
		buttonRemoverTelefone.setImmediate(false);
		buttonRemoverTelefone.setWidth("-1px");
		buttonRemoverTelefone.setHeight("-1px");
		addComponent(buttonRemoverTelefone,
				"top:50.0px;left:470.0px;");
		buttonRemoverTelefone.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	remover();
            }
        });
	}
	
	public void remover(){
    	MessageBox mb = new MessageBox(getWindow(), "Remover Media", MessageBox.Icon.QUESTION, "Deseja Continuar?",  
                new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
                new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
         
    	mb.show(new EventListener() {
			
			public void buttonClicked(ButtonType buttonType) {
			  System.out.println("This button was pressed: " + buttonType);
			  if(buttonType.equals(ButtonType.YES)){
				if(telefoneController.removerTelefone(telefoneContato)){
			        setVisible(false); 
			     }
			  }
			}  
        });
    }
	
	public void gravar(){
		try{
			this.telefoneContato.setDddNumero(Long.parseLong(textFieldNumeroTelefone.getValue().toString()));
		}catch (Exception e) {
			new MessageBox(getWindow(), "Erro", MessageBox.Icon.ERROR, "DDD+Telefone - Somente N�mero");
	 	}
		try{
			this.telefoneContato.setRamal(Long.parseLong(textFieldRamal.getValue().toString()));
		}catch (Exception e) {
			new MessageBox(getWindow(), "Erro", MessageBox.Icon.ERROR, "Ramal - Somente N�mero");
		}
		
		this.telefoneContato.setFinalidade(textFieldFinalidade.getValue().toString());
		this.telefoneContato.setObs(textAreaObs.getValue().toString());
		try{
			this.telefoneContato.setPrestadora(comboBoxOperadora.getValue().toString());
		}catch (Exception e) {
			this.telefoneContato.setPrestadora("");
		}
		try{
			this.telefoneContato.setTipo(comboBoxTipoTelefone.getValue().toString());
		}catch (Exception e) {
			this.telefoneContato.setTipo("");
		}
		
		this.telefoneContato.setDataUpdate(new Date());
    	
		telefoneController.addTelefone(telefoneContato);
    }
    
	public TelefoneContato getTelefoneCliente(){
		return this.telefoneContato;
	}
	
	public void ligar(){
		 this.telefoneController.ligar(textFieldNumeroTelefone.getValue().toString());
	}
	
	public void limpa(){
	   	 
		for (Iterator<?> iterator = getComponentIterator(); iterator.hasNext();) {
			Object obj = iterator.next();
	   	
	   		if (obj instanceof TextField) {
				TextField tex = (TextField) obj;
				tex.setReadOnly(false);
				tex.setValue("");
			}
	   		if (obj instanceof ComboBox) {
	   			ComboBox box = (ComboBox) obj;
	   			box.setReadOnly(false);
	   			box.select(box.getNullSelectionItemId());
	   		}
	   	 }	 
	}
}