package com.ticket.view.panel;

import org.vaadin.hene.expandingtextarea.ExpandingTextArea;

import com.ticket.controller.OrdemController;
import com.ticket.model.Nota;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class PanelNota extends Panel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout verticalLayoutPanel;
	private ExpandingTextArea textAreaNota;
    private Nota nota;
	
    public PanelNota(Nota nota) {
    	this.nota = nota;
		setStyleName("nota");
		setWidth("100%");
		setHeight("-1px");
		setCaption(this.nota.getDataUpdate() +" "+this.nota.getFuncionario().getNome());
		setImmediate(false);
		verticalLayoutPanel = buildVerticalLayoutPanel();
		setContent(verticalLayoutPanel);
	}

	private VerticalLayout buildVerticalLayoutPanel() {
		// common part: create layout
		verticalLayoutPanel = new VerticalLayout();
		verticalLayoutPanel.setImmediate(false);
		verticalLayoutPanel.setWidth("100.0%");
		verticalLayoutPanel.setHeight("100.0%");
		verticalLayoutPanel.setMargin(false);
		verticalLayoutPanel.setSpacing(true);
	
		// textAreaNota
		textAreaNota = new ExpandingTextArea();
		textAreaNota.setImmediate(false);
		textAreaNota.setWidth("100.0%");
		textAreaNota.setCaption(this.nota.getTitulo());
		textAreaNota.setValue(this.nota.getNota());
		verticalLayoutPanel.addComponent(textAreaNota);
		return verticalLayoutPanel;
	}
	
	

	

}
