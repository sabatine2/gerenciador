package com.despesas.view;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

public class PagamentoInfo {

	private AbsoluteLayout mainLayout;
	private Panel panelDadosPagamento;
	private AbsoluteLayout absoluteLayout_6;
	private PopupDateField popupDateFieldDataLiberacao;
	private TextField textFieldNconta;
	private TextField textFieldAgencia;
	private TextField textFieldBanco;
	
	
	public PagamentoInfo(){
		
		panelDadosPagamento = buildPanelDadosPagamento();
		mainLayout
				.addComponent(panelDadosPagamento, "top:681.0px;left:20.0px;");
	}

	private Panel buildPanelDadosPagamento() {
		// common part: create layout
		panelDadosPagamento = new Panel();
		panelDadosPagamento.setCaption("Dados de Pagamento");
		panelDadosPagamento.setImmediate(false);
		panelDadosPagamento.setWidth("840px");
		panelDadosPagamento.setHeight("100px");
		
		// absoluteLayout_6
		absoluteLayout_6 = buildAbsoluteLayout_6();
		panelDadosPagamento.setContent(absoluteLayout_6);
		
		return panelDadosPagamento;
	}

	private AbsoluteLayout buildAbsoluteLayout_6() {
		// common part: create layout
		absoluteLayout_6 = new AbsoluteLayout();
		absoluteLayout_6.setImmediate(false);
		absoluteLayout_6.setWidth("100.0%");
		absoluteLayout_6.setHeight("100.0%");
		absoluteLayout_6.setMargin(false);
		
		// textFieldBanco
		textFieldBanco = new TextField();
		textFieldBanco.setCaption("Banco");
		textFieldBanco.setImmediate(false);
		textFieldBanco.setWidth("160px");
		textFieldBanco.setHeight("-1px");
		textFieldBanco.setTabIndex(25);
		absoluteLayout_6
				.addComponent(textFieldBanco, "top:31.0px;left:27.0px;");
		
		// textFieldAgencia
		textFieldAgencia = new TextField();
		textFieldAgencia.setCaption("Ag�ncia");
		textFieldAgencia.setImmediate(false);
		textFieldAgencia.setWidth("160px");
		textFieldAgencia.setHeight("-1px");
		textFieldAgencia.setTabIndex(26);
		absoluteLayout_6.addComponent(textFieldAgencia,
				"top:31.0px;left:224.0px;");
		
		// textFieldNconta
		textFieldNconta = new TextField();
		textFieldNconta.setCaption("N� Conta Corrente");
		textFieldNconta.setImmediate(false);
		textFieldNconta.setWidth("160px");
		textFieldNconta.setHeight("-1px");
		textFieldNconta.setTabIndex(27);
		absoluteLayout_6.addComponent(textFieldNconta,
				"top:31.0px;left:423.0px;");
		
		// popupDateFieldDataLiberacao
		popupDateFieldDataLiberacao = new PopupDateField();
		popupDateFieldDataLiberacao.setCaption("Data Libera��o Pagamento");
		popupDateFieldDataLiberacao.setImmediate(false);
		popupDateFieldDataLiberacao.setWidth("180px");
		popupDateFieldDataLiberacao.setHeight("-1px");
		popupDateFieldDataLiberacao.setInvalidAllowed(false);
		popupDateFieldDataLiberacao.setTabIndex(28);
		popupDateFieldDataLiberacao.setResolution(4);
		absoluteLayout_6.addComponent(popupDateFieldDataLiberacao,
				"top:31.0px;left:627.0px;");
		
		return absoluteLayout_6;
	}

}
