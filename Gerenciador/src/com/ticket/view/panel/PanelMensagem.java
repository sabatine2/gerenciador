package com.ticket.view.panel;

import org.vaadin.hene.expandingtextarea.ExpandingTextArea;

import com.ticket.model.Mensagem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class PanelMensagem extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout verticalLayoutPanel;
	private ExpandingTextArea textAreaMensagem;
    private Mensagem mensagem;
    
	public PanelMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
		mensagem =null;
		
		setStyleName("mensagem");
		setImmediate(true);
		setWidth("100.0%");
		setHeight("-1px");
		setCaption(this.mensagem.getDataUpdate() +" "+this.mensagem.getFonte());
		verticalLayoutPanel = buildVerticalLayoutPanel();
		setContent(verticalLayoutPanel);
	}

	private VerticalLayout buildVerticalLayoutPanel() {
		// common part: create layout
		verticalLayoutPanel = new VerticalLayout();
		verticalLayoutPanel.setImmediate(true);
		verticalLayoutPanel.setWidth("100.0%");
		verticalLayoutPanel.setHeight("100.0%");
		verticalLayoutPanel.setMargin(false);
		verticalLayoutPanel.setSpacing(true);
		
		// textAreaMensagem
		textAreaMensagem = new ExpandingTextArea();
		textAreaMensagem.setImmediate(false);
		textAreaMensagem.setWidth("100.0%");
		textAreaMensagem.setValue(this.mensagem.getMensagem());
		verticalLayoutPanel.addComponent(textAreaMensagem);
		
		return verticalLayoutPanel;
	}
	
}
