package com.contato.view;

import java.util.Iterator;

import com.contato.model.Contato;
import com.midiasocial.model.MidiaSocial;
import com.telefone.model.TelefoneContato;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ContatoDetalhe extends VerticalLayout  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label labelInfo;
	private Button remover;
	
    private Contato contatoCliente;

	public ContatoDetalhe(Contato contatoCliente) {
		
		this.contatoCliente = contatoCliente;
		
		setWidth("240px");
		setHeight("100.0%");
		setMargin(true);
		setStyleName("normal");
		
		HorizontalLayout layoutPrincipal= new HorizontalLayout();
		layoutPrincipal.setImmediate(false);
		layoutPrincipal.setWidth("100.0%");
		layoutPrincipal.setHeight("100.0%");
		layoutPrincipal.setMargin(false);
		
		HorizontalLayout layoutBotao= new HorizontalLayout();
		layoutBotao.setImmediate(false);
		layoutBotao.setWidth("100.0%");
		layoutBotao.setHeight("100.0%");
		layoutBotao.setMargin(false);
		
		// labelinfo
		Label labelInfoNome = new Label();
		labelInfoNome.setImmediate(false);
		labelInfoNome.setWidth("-1px");
		labelInfoNome.setHeight("-1px");
		labelInfoNome.setValue("<b>"+contatoCliente.getNome()+"</b>");
		labelInfoNome.setContentMode(Label.CONTENT_XHTML);
		layoutPrincipal.addComponent(labelInfoNome);
				
		// remover
		remover = new Button();
		remover.setIcon(new ThemeResource("../reindeer/Icons/minus.png"));
		remover.setImmediate(true);
		remover.setWidth("-1px");
		remover.setHeight("-1px");
		remover.setData(this);
		layoutBotao.addComponent(remover);
	
		layoutPrincipal.addComponent(layoutBotao);
		addComponent(layoutPrincipal);
		
		// labelinfo
		labelInfo = new Label();
		labelInfo.setImmediate(false);
		labelInfo.setWidth("-1px");
		labelInfo.setHeight("-1px");
		labelInfo.setValue(geraInfo());
		labelInfo.setContentMode(Label.CONTENT_XHTML);
		addComponent(labelInfo);
		setData(contatoCliente);
	
		
	}

	public String geraInfo(){
		
		String info="";
		
		String tipo = (contatoCliente.getTipoContato()!=null) ? contatoCliente.getTipoContato() : " ";
		String departamento = (contatoCliente.getDepartamento()!=null) ? contatoCliente.getDepartamento() : " ";
		StringBuffer mediaString = new StringBuffer("");
		StringBuffer telefoneString = new StringBuffer("");
		
		for (Iterator<?> i = contatoCliente.getMedia().iterator();i.hasNext();){
			MidiaSocial media = (MidiaSocial) i.next();
			mediaString.append("<p>"+media.getTipoMedia()+": "+media.getEndereco()+"</p>");
		}
		
		for (Iterator<?> i = contatoCliente.getTelefoneContato().iterator();i.hasNext();){
			TelefoneContato telefone = (TelefoneContato) i.next();
			telefoneString.append(telefone.getDddNumero()+" ");
		}
		
		if(contatoCliente.getEndereco() != null){
		 info =
           "<p>"+tipo+" "+departamento+"</p>"
         + "<p>"+contatoCliente.getEndereco().getLogradouro()+", "+contatoCliente.getEndereco().getNumero()+", "+ contatoCliente.getEndereco().getBairro()+", "+contatoCliente.getEndereco().getCidade()+"</p>"
         + "<p>"+contatoCliente.getEndereco().getEstado()+", "+contatoCliente.getEndereco().getCep()+"</p>"
         + mediaString
         + "<p>"+telefoneString+"</p>";
		}
		else
			 info = "";
		
		
		return info;
		
	}
  	
	/**
	 * @return the contatoCliente
	 */
	public Contato getContatoCliente() {
		return contatoCliente;
	}

	/**
	 * @param contatoCliente the contatoCliente to set
	 */
	public void setContatoCliente(Contato contatoCliente) {
		this.contatoCliente = contatoCliente;
		this.setData(contatoCliente);
	}

	
	public void selecinado(){
		setStyleName("selecionado");
	}
	
	public void normal(){
		setStyleName("normal");
	}

	public Button getRemover(){
		return this.remover;
	}

}
