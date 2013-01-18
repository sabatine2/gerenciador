package com.despesas.view;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class GastoInfo {

	private AbsoluteLayout mainLayout;
	private Panel panelGasto;
	private AbsoluteLayout absoluteLayout_5;
	private Label labelIgual2;
	private TextField textFieldSaldoFinal;
	private TextField textFieldDeducaoG;
	private Label labelIgual;
	private TextField textFieldSaldo;
	private TextField textFieldAdiantamento;
	private TextField textFieldTotalGasto;

	public GastoInfo(){
		
		panelGasto = buildPanelGasto();
		mainLayout.addComponent(panelGasto, "top:520.0px;left:20.0px;");
	}
	
	
	private Panel buildPanelGasto() {
		// common part: create layout
		panelGasto = new Panel();
		panelGasto.setCaption("Gastos");
		panelGasto.setImmediate(false);
		panelGasto.setWidth("840px");
		panelGasto.setHeight("142px");
		
		// absoluteLayout_5
		absoluteLayout_5 = buildAbsoluteLayout_5();
		panelGasto.setContent(absoluteLayout_5);
		
		return panelGasto;
	}

	
	private AbsoluteLayout buildAbsoluteLayout_5() {
		// common part: create layout
		absoluteLayout_5 = new AbsoluteLayout();
		absoluteLayout_5.setImmediate(false);
		absoluteLayout_5.setWidth("100.0%");
		absoluteLayout_5.setHeight("100.0%");
		absoluteLayout_5.setMargin(false);
		
		// textFieldTotalGasto
		textFieldTotalGasto = new TextField();
		textFieldTotalGasto.setCaption("Total de Gastos");
		textFieldTotalGasto.setImmediate(false);
		textFieldTotalGasto.setWidth("210px");
		textFieldTotalGasto.setHeight("-1px");
		textFieldTotalGasto.setTabIndex(20);
		absoluteLayout_5.addComponent(textFieldTotalGasto,
				"top:31.0px;left:28.0px;");
		
		// textFieldAdiantamento
		textFieldAdiantamento = new TextField();
		textFieldAdiantamento.setCaption("Adiantamento Recebido");
		textFieldAdiantamento.setImmediate(false);
		textFieldAdiantamento.setWidth("216px");
		textFieldAdiantamento.setHeight("-1px");
		textFieldAdiantamento.setTabIndex(21);
		absoluteLayout_5.addComponent(textFieldAdiantamento,
				"top:31.0px;left:292.0px;");
		
		// textFieldSaldo
		textFieldSaldo = new TextField();
		textFieldSaldo.setCaption("Saldo");
		textFieldSaldo.setImmediate(false);
		textFieldSaldo.setWidth("210px");
		textFieldSaldo.setHeight("-1px");
		textFieldSaldo.setTabIndex(22);
		absoluteLayout_5.addComponent(textFieldSaldo,
				"top:31.0px;left:598.0px;");
		
		// labelIgual
		labelIgual = new Label();
		labelIgual.setImmediate(false);
		labelIgual.setWidth("-1px");
		labelIgual.setHeight("-1px");
		labelIgual.setValue("=");
		absoluteLayout_5.addComponent(labelIgual, "top:31.0px;left:548.0px;");
		
		// textFieldDeducaoG
		textFieldDeducaoG = new TextField();
		textFieldDeducaoG.setCaption("Dedu��o");
		textFieldDeducaoG.setImmediate(false);
		textFieldDeducaoG.setWidth("216px");
		textFieldDeducaoG.setHeight("-1px");
		textFieldDeducaoG.setTabIndex(23);
		absoluteLayout_5.addComponent(textFieldDeducaoG,
				"top:77.0px;left:292.0px;");
		
		// textFieldSaldoFinal
		textFieldSaldoFinal = new TextField();
		textFieldSaldoFinal.setCaption("Saldo Final");
		textFieldSaldoFinal.setImmediate(false);
		textFieldSaldoFinal.setWidth("210px");
		textFieldSaldoFinal.setHeight("-1px");
		textFieldSaldoFinal.setTabIndex(24);
		absoluteLayout_5.addComponent(textFieldSaldoFinal,
				"top:77.0px;left:598.0px;");
		
		// labelIgual2
		labelIgual2 = new Label();
		labelIgual2.setImmediate(false);
		labelIgual2.setWidth("-1px");
		labelIgual2.setHeight("-1px");
		labelIgual2.setValue("=");
		absoluteLayout_5.addComponent(labelIgual2, "top:79.0px;left:548.0px;");
		
		return absoluteLayout_5;
	}
	
}
