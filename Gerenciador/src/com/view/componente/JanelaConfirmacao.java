package com.view.componente;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class JanelaConfirmacao extends Window {

    private VerticalLayout layoutV;
    private HorizontalLayout layoutH;
	private Button buttonSim;
	private Button buttonNao;
	 public Component componetAtivo;

	public JanelaConfirmacao(String titulo, String msg) {
		super(titulo);
		center();
		setWidth("320px");
		setHeight("130px");
		layoutV= new VerticalLayout();
		addComponent(layoutV);
		layoutV.setMargin(false);
		
		Label message = new Label(msg);
        layoutV.addComponent(message);
        
		layoutH= new HorizontalLayout();
		layoutV.addComponent(layoutH);
		layoutH.setMargin(false);
		
		// buttonSim
		buttonSim = new Button();
		buttonSim.setCaption("Sim");
		buttonSim.setImmediate(true);
		buttonSim.setWidth("-1px");
		buttonSim.setHeight("-1px");
		buttonSim.setTabIndex(18);
		layoutH.addComponent(buttonSim);
		layoutH.setComponentAlignment(buttonSim, new Alignment(10));
		
		// buttonNao
		buttonNao = new Button();
		buttonNao.setCaption("N�o");
		buttonNao.setImmediate(true);
		buttonNao.setWidth("-1px");
		buttonNao.setHeight("-1px");
		buttonNao.setTabIndex(18);
		layoutH.addComponent(buttonNao);
		layoutH.setComponentAlignment(buttonSim, new Alignment(10));
		
		return;
	}
	
	public Button getButtonSim() {
		return buttonSim;
	}

	public void setButtonSim(Button buttonSim) {
		this.buttonSim = buttonSim;
	}

	public Button getButtonNao() {
		return buttonNao;
	}

	public void setButtonNao(Button buttonNao) {
		this.buttonNao = buttonNao;
	}
}