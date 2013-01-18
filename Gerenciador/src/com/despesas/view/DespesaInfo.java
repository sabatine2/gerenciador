package com.despesas.view;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DespesaInfo extends AbsoluteLayout{

	private Panel panelAddDespesa;
	private VerticalLayout verticalLayout_1;
	private Button buttonAdicionarItem;
	
	public DespesaInfo(){
		
		// panelAddDespesa
				panelAddDespesa = buildPanelAddDespesa();
				addComponent(panelAddDespesa, "top:359.0px;left:20.0px;");
	}

	private Panel buildPanelAddDespesa() {
		// common part: create layout
		panelAddDespesa = new Panel();
		panelAddDespesa.setCaption("Despesas");
		panelAddDespesa.setImmediate(false);
		panelAddDespesa.setWidth("840px");
		panelAddDespesa.setHeight("141px");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		panelAddDespesa.setContent(verticalLayout_1);
		
		return panelAddDespesa;
	}

	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		
		// buttonAdicionarItem
		buttonAdicionarItem = new Button();
		buttonAdicionarItem.setCaption("Adicionar");
		buttonAdicionarItem.setImmediate(true);
		buttonAdicionarItem.setWidth("-1px");
		buttonAdicionarItem.setHeight("-1px");
		buttonAdicionarItem.setTabIndex(12);
		verticalLayout_1.addComponent(buttonAdicionarItem);
		
		
		
		return verticalLayout_1;
	}
	
}
