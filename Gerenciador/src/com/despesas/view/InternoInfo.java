package com.despesas.view;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class InternoInfo {

	private AbsoluteLayout mainLayout;
	private Panel panelInteerno;
	private AbsoluteLayout absoluteLayout_7;
	private TextArea textAreaObs;
	private ComboBox comboBoxStatus;
	private TextField textFieldNomeInterno;

	
	public InternoInfo(){
		
		// panelInteerno
				panelInteerno = buildPanelInteerno();
				mainLayout.addComponent(panelInteerno, "top:799.0px;left:21.0px;");
	}

	private Panel buildPanelInteerno() {
		// common part: create layout
		panelInteerno = new Panel();
		panelInteerno.setCaption("Interno");
		panelInteerno.setImmediate(false);
		panelInteerno.setWidth("840px");
		panelInteerno.setHeight("138px");
		
		// absoluteLayout_7
		absoluteLayout_7 = buildAbsoluteLayout_7();
		panelInteerno.setContent(absoluteLayout_7);
		
		return panelInteerno;
	}

	private AbsoluteLayout buildAbsoluteLayout_7() {
		// common part: create layout
		absoluteLayout_7 = new AbsoluteLayout();
		absoluteLayout_7.setImmediate(false);
		absoluteLayout_7.setWidth("100.0%");
		absoluteLayout_7.setHeight("100.0%");
		absoluteLayout_7.setMargin(false);
		
		// textFieldNomeInterno
		textFieldNomeInterno = new TextField();
		textFieldNomeInterno.setCaption("Nome");
		textFieldNomeInterno.setImmediate(false);
		textFieldNomeInterno.setWidth("357px");
		textFieldNomeInterno.setHeight("-1px");
		textFieldNomeInterno.setTabIndex(29);
		absoluteLayout_7.addComponent(textFieldNomeInterno,
				"top:34.0px;left:27.0px;");
		
		// comboBoxStatus
		comboBoxStatus = new ComboBox();
		comboBoxStatus.setCaption("Status");
		comboBoxStatus.setImmediate(false);
		comboBoxStatus.setWidth("181px");
		comboBoxStatus.setHeight("-1px");
		comboBoxStatus.setTabIndex(30);
		absoluteLayout_7
				.addComponent(comboBoxStatus, "top:79.0px;left:28.0px;");
		
		// textAreaObs
		textAreaObs = new TextArea();
		textAreaObs.setCaption("Observa��o");
		textAreaObs.setImmediate(false);
		textAreaObs.setWidth("373px");
		textAreaObs.setHeight("69px");
		textAreaObs.setTabIndex(31);
		absoluteLayout_7.addComponent(textAreaObs, "top:34.0px;left:437.0px;");
		
		return absoluteLayout_7;
	}
	
}
