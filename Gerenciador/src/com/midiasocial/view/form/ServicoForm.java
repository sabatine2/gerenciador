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
  
        intervalo = new ComboBox("Intervalo de duracao");
    	intervalo.setWidth("19.7em");
    	intervalo.addItem(new Long("5"));
    	intervalo.addItem(new Long("15"));
    	intervalo.addItem(new Long("25"));
    	intervalo.addItem(new Long("45"));
    	intervalo.addItem(new Long("65"));
    	intervalo.addItem(new Long("125"));
    	intervalo.addItem(new Long("185"));
    	intervalo.addItem(new Long("245"));
    	intervalo.addItem(new Long("605"));
    	intervalo.setValue(servico.getIntervalo());
        ourLayout.addComponent(intervalo, 0, 1);
        addComponent(ourLayout);

    }
    
    public Servico getServico(){
        servico.setIntervalo((Long)intervalo.getValue());
    	return servico; 	
    	}
	}