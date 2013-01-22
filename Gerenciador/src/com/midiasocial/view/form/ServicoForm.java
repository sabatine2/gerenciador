package com.midiasocial.view.form;

import com.midiasocial.model.Servico;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

public class ServicoForm extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Servico servico;
	private ComboBox intervalo;
	private GridLayout ourLayout;

    public ServicoForm(Servico servico) {

    	this.servico = servico;
    	ourLayout = new GridLayout(2, 2);
        ourLayout.setMargin(true, false, false, true);
        ourLayout.setSpacing(true);
  
        intervalo = new ComboBox("Intervalo de duracao (em minutos)");
    	intervalo.setWidth("19.7em");
    	intervalo.addItem(new Long("3"));
    	intervalo.addItem(new Long("10"));
    	intervalo.addItem(new Long("40"));
    	intervalo.addItem(new Long("60"));
    	intervalo.addItem(new Long("120"));
    	intervalo.addItem(new Long("240"));
    	intervalo.addItem(new Long("360"));
    	intervalo.addItem(new Long("560"));
    	intervalo.setValue(servico.getIntervalo());
        ourLayout.addComponent(intervalo, 0, 1);
        addComponent(ourLayout);

    }
    
    public Servico getServico(){
        servico.setIntervalo((Long)intervalo.getValue());
    	return servico; 	
    	}
	}