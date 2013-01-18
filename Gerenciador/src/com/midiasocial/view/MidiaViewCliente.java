package com.midiasocial.view;

import com.midiasocial.controller.MidiaSocialContatoController;
import com.midiasocial.helper.TipoMidia;
import com.midiasocial.model.MidiaSocial;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.EventListener;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "static-access"})
public class MidiaViewCliente extends  AbsoluteLayout {

	private   Button            buttonRemoverMedia;
	private   Button            buttonVisualizarMedia;
	private   Button            buttonInserirMedia;
	
	private   TextField         textFieldEnderecoMedia;
	private   ComboBox          comboBoxTipoDeMedia;
    private   MidiaSocial       mediaSocial;
  	protected MidiaSocialContatoController contatoController;
    
    public MidiaViewCliente(MidiaSocial mediaCliente,final MidiaSocialContatoController contatoController) {
    	
    	this.mediaSocial = mediaCliente;
        this.contatoController = contatoController;
        
        setImmediate(false);
		setWidth("600px");
		setHeight("30px");
		setMargin(false);
		
		// comboBoxTipoDeMedia
		comboBoxTipoDeMedia = new ComboBox();
		comboBoxTipoDeMedia.setInputPrompt("Tipo de Media");
		comboBoxTipoDeMedia.setWidth("-1px");
		comboBoxTipoDeMedia.setHeight("-1px");
		comboBoxTipoDeMedia.setContainerDataSource(TipoMidia.getTipoMediaContainer());
		comboBoxTipoDeMedia.setValue(mediaCliente.getTipoMedia());
		addComponent(comboBoxTipoDeMedia,
				"top:1.0px;left:20.0px;;");
		
		comboBoxTipoDeMedia.addListener(new Property.ValueChangeListener() {
			
			public void valueChange(ValueChangeEvent event) {
				setTipoMedia(event.getProperty().toString());
	           }});

		// textFieldEnderecoMedia
		textFieldEnderecoMedia = new TextField();
		textFieldEnderecoMedia.setInputPrompt("Contato Eletronico");
		textFieldEnderecoMedia.setWidth("180px");
		textFieldEnderecoMedia.setHeight("-1px");
		textFieldEnderecoMedia.setValue(mediaCliente.getEndereco());
		addComponent(textFieldEnderecoMedia,
				"top:1.0px;left:180.0px;");
		
		Resource res = new ThemeResource("../reindeer/Icons/binocular.png");
		buttonVisualizarMedia = new Button();
		buttonVisualizarMedia.setIcon(res);
		buttonVisualizarMedia.setDescription("Visualizar");
		buttonVisualizarMedia.setImmediate(false);
		buttonVisualizarMedia.setWidth("-1px");
		buttonVisualizarMedia.setHeight("-1px");
		buttonVisualizarMedia.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	visualizar();
            }
        });
		addComponent(buttonVisualizarMedia,"top:1.0px;left:370.0px;");
	
		res = new ThemeResource("../reindeer/Icons/database.png");
		buttonInserirMedia = new Button();
		buttonInserirMedia.setIcon(res);
		buttonInserirMedia.setDescription("Gravar");
		buttonInserirMedia.setImmediate(false);
		buttonInserirMedia.setWidth("-1px");
		buttonInserirMedia.setHeight("-1px");
		buttonInserirMedia.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	gravar();
            }
        });
		addComponent(buttonInserirMedia,"top:1.0px;left:420.0px;");
	
		res = new ThemeResource("../reindeer/Icons/minus.png");
		buttonRemoverMedia = new Button();
		buttonRemoverMedia.setIcon(res);
		buttonRemoverMedia.setDescription("Remover");
		buttonRemoverMedia.setImmediate(false);
		buttonRemoverMedia.setWidth("-1px");
		buttonRemoverMedia.setHeight("-1px");
		addComponent(buttonRemoverMedia,"top:1.0px;left:470.0px;");	
		
		buttonRemoverMedia.addListener(new Button.ClickListener() {
	            public void buttonClick(ClickEvent event) {
	            	remover();
	            }
	        });
	}
    
    public void gravar(){
    	this.mediaSocial.setTipoMedia(comboBoxTipoDeMedia.getValue().toString());
    	this.mediaSocial.setEndereco(textFieldEnderecoMedia.getValue().toString());
    	contatoController.addMedia(mediaSocial);
    }
    
    public void visualizar(){
    	this.mediaSocial.setTipoMedia(comboBoxTipoDeMedia.getValue().toString());
    	this.mediaSocial.setEndereco(textFieldEnderecoMedia.getValue().toString());
    	contatoController.visualizar(mediaSocial);
    }
    
    public void setTipoMedia(String media){
    	  this.mediaSocial.setTipoMedia(media);
    }
    
    public void remover(){
    	MessageBox mb = new MessageBox(getWindow(), "Remover Media", MessageBox.Icon.QUESTION, "Deseja Continuar?",  
                new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
                new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
         
    	mb.show(new EventListener() {
			
			public void buttonClicked(ButtonType buttonType) {
			  System.out.println("This button was pressed: " + buttonType);
			  if(buttonType.equals(buttonType.YES)){
				if(contatoController.removeMedia(mediaSocial)){
			       setVisible(false); 
			     }
			  }
			}  
        });
    }
	
	public TextField getTextFieldEnderecoMedia() {
		return textFieldEnderecoMedia;
	}

	public void setTextFieldEnderecoMedia(TextField textFieldEnderecoMedia) {
		this.textFieldEnderecoMedia = textFieldEnderecoMedia;
	}

	public ComboBox getComboBoxTipoDeMedia() {
		return comboBoxTipoDeMedia;
	}

	public void setComboBoxTipoDeMedia(ComboBox comboBoxTipoDeMedia) {
		this.comboBoxTipoDeMedia = comboBoxTipoDeMedia;
	}
	
	public boolean commit() {
		
		boolean erro = true;
    	
        try{
        this.mediaSocial.setEndereco(textFieldEnderecoMedia.getValue().toString());
        }
        catch (Exception e) {
        	System.out.println("erro2 "+e.getMessage());
        	erro = false; 
		}
  	
        return erro;
	}
	
	public MidiaSocial getMediaSocial(){
		return this.mediaSocial;
	}
	
	 
}