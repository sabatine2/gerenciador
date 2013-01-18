package com.despesas.view;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class DadosInfo extends AbsoluteLayout {
	

	private Panel panelDados;
	private AbsoluteLayout absoluteLayout_2;
	private Panel panelMotivo;
	private AbsoluteLayout absoluteLayout_3;
	private TextField textFieldOutros;
	private CheckBox checkBoxOutros;
	private CheckBox checkBoxTreinamento;
	private CheckBox checkBoxImplantacao;
	private CheckBox checkBoxInstalacao;
	private CheckBox checkBoxProspe;
	private PopupDateField popupDateFieldFinal;
	private PopupDateField popupDateFieldInicio;
	private TextField textFieldDestinoCidade;
	private TextField textFieldCentroCusto;
	private TextField textFieldCpf;
	private TextField textFieldNome;
	
	public DadosInfo(){
	// panelDados
			panelDados = buildPanelDados();
			addComponent(panelDados, "top:21.0px;left:20.0px;");
			setWidth("840px");
			setHeight("319px");
	}
	
	private Panel buildPanelDados() {
		// common part: create layout
		panelDados = new Panel();
		panelDados.setCaption("Dados In�ciais");
		panelDados.setImmediate(false);
		panelDados.setWidth("840px");
		panelDados.setHeight("319px");
		
		// absoluteLayout_2
		absoluteLayout_2 = buildAbsoluteLayout_2();
		panelDados.setContent(absoluteLayout_2);
		
		return panelDados;
	}

	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("101.95%");
		absoluteLayout_2.setHeight("104.92%");
		absoluteLayout_2.setMargin(false);
		
		// textFieldNome
		textFieldNome = new TextField();
		textFieldNome.setCaption("Nome");
		textFieldNome.setImmediate(false);
		textFieldNome.setWidth("340px");
		textFieldNome.setHeight("-1px");
		textFieldNome.setTabIndex(1);
		absoluteLayout_2.addComponent(textFieldNome, "top:21.0px;left:21.0px;");
		
		// textFieldCpf
		textFieldCpf = new TextField();
		textFieldCpf.setCaption("CPF");
		textFieldCpf.setImmediate(false);
		textFieldCpf.setWidth("-1px");
		textFieldCpf.setHeight("-1px");
		textFieldCpf.setTabIndex(2);
		absoluteLayout_2.addComponent(textFieldCpf, "top:21.0px;left:401.0px;");
		
		// textFieldCentroCusto
		textFieldCentroCusto = new TextField();
		textFieldCentroCusto.setCaption("Area / Centro de Custo");
		textFieldCentroCusto.setImmediate(false);
		textFieldCentroCusto.setWidth("237px");
		textFieldCentroCusto.setHeight("-1px");
		textFieldCentroCusto.setTabIndex(3);
		absoluteLayout_2.addComponent(textFieldCentroCusto,
				"top:21.0px;left:584.0px;");
		
		// textFieldDestinoCidade
		textFieldDestinoCidade = new TextField();
		textFieldDestinoCidade.setCaption("Destino Cidades");
		textFieldDestinoCidade.setImmediate(false);
		textFieldDestinoCidade.setWidth("800px");
		textFieldDestinoCidade.setHeight("-1px");
		textFieldDestinoCidade.setTabIndex(4);
		absoluteLayout_2.addComponent(textFieldDestinoCidade,
				"top:69.0px;left:22.0px;");
		
		// popupDateFieldInicio
		popupDateFieldInicio = new PopupDateField();
		popupDateFieldInicio.setCaption("Data In�cio");
		popupDateFieldInicio.setImmediate(false);
		popupDateFieldInicio.setWidth("220px");
		popupDateFieldInicio.setHeight("-1px");
		popupDateFieldInicio.setInvalidAllowed(false);
		popupDateFieldInicio.setTabIndex(5);
		popupDateFieldInicio.setResolution(4);
		absoluteLayout_2.addComponent(popupDateFieldInicio,
				"top:117.0px;left:21.0px;");
		
		// popupDateFieldFinal
		popupDateFieldFinal = new PopupDateField();
		popupDateFieldFinal.setCaption("Data Final");
		popupDateFieldFinal.setImmediate(false);
		popupDateFieldFinal.setWidth("220px");
		popupDateFieldFinal.setHeight("-1px");
		popupDateFieldFinal.setInvalidAllowed(false);
		popupDateFieldFinal.setTabIndex(6);
		popupDateFieldFinal.setResolution(4);
		absoluteLayout_2.addComponent(popupDateFieldFinal,
				"top:117.0px;left:321.0px;");
		
		// panelMotivo
		panelMotivo = buildPanelMotivo();
		absoluteLayout_2.addComponent(panelMotivo, "top:152.0px;left:22.0px;");
		
		return absoluteLayout_2;
	}
	
	private Panel buildPanelMotivo() {
		// common part: create layout
		panelMotivo = new Panel();
		panelMotivo.setCaption("Motivo da Viagem");
		panelMotivo.setImmediate(false);
		panelMotivo.setWidth("800px");
		panelMotivo.setHeight("135px");
		
		// absoluteLayout_3
		absoluteLayout_3 = buildAbsoluteLayout_3();
		panelMotivo.setContent(absoluteLayout_3);
		
		return panelMotivo;
	}

	private AbsoluteLayout buildAbsoluteLayout_3() {
		// common part: create layout
		absoluteLayout_3 = new AbsoluteLayout();
		absoluteLayout_3.setImmediate(false);
		absoluteLayout_3.setWidth("100.0%");
		absoluteLayout_3.setHeight("100.0%");
		absoluteLayout_3.setMargin(false);
		
		// checkBoxProspe
		checkBoxProspe = new CheckBox();
		checkBoxProspe.setCaption("Prospec��o");
		checkBoxProspe.setImmediate(false);
		checkBoxProspe.setWidth("-1px");
		checkBoxProspe.setHeight("-1px");
		checkBoxProspe.setTabIndex(7);
		absoluteLayout_3
				.addComponent(checkBoxProspe, "top:24.0px;left:44.0px;");
		
		// checkBoxInstalacao
		checkBoxInstalacao = new CheckBox();
		checkBoxInstalacao.setCaption("Instala��o");
		checkBoxInstalacao.setImmediate(false);
		checkBoxInstalacao.setWidth("-1px");
		checkBoxInstalacao.setHeight("-1px");
		checkBoxInstalacao.setTabIndex(8);
		absoluteLayout_3.addComponent(checkBoxInstalacao,
				"top:24.0px;left:244.0px;");
		
		// checkBoxImplantacao
		checkBoxImplantacao = new CheckBox();
		checkBoxImplantacao.setCaption("Implanta��o");
		checkBoxImplantacao.setImmediate(false);
		checkBoxImplantacao.setWidth("-1px");
		checkBoxImplantacao.setHeight("-1px");
		checkBoxImplantacao.setTabIndex(9);
		absoluteLayout_3.addComponent(checkBoxImplantacao,
				"top:24.0px;left:445.0px;");
		
		// checkBoxTreinamento
		checkBoxTreinamento = new CheckBox();
		checkBoxTreinamento.setCaption("Treinamento");
		checkBoxTreinamento.setImmediate(false);
		checkBoxTreinamento.setWidth("-1px");
		checkBoxTreinamento.setHeight("-1px");
		checkBoxTreinamento.setTabIndex(10);
		absoluteLayout_3.addComponent(checkBoxTreinamento,
				"top:24.0px;left:649.0px;");
		
		// checkBoxOutros
		checkBoxOutros = new CheckBox();
		checkBoxOutros.setCaption("Outros");
		checkBoxOutros.setImmediate(false);
		checkBoxOutros.setWidth("-1px");
		checkBoxOutros.setHeight("-1px");
		checkBoxOutros.setTabIndex(11);
		absoluteLayout_3
				.addComponent(checkBoxOutros, "top:69.0px;left:43.0px;");
		
		// textFieldOutros
		textFieldOutros = new TextField();
		textFieldOutros.setCaption("Outros");
		textFieldOutros.setImmediate(false);
		textFieldOutros.setWidth("490px");
		textFieldOutros.setHeight("-1px");
		textFieldOutros.setTabIndex(12);
		absoluteLayout_3.addComponent(textFieldOutros,
				"top:69.0px;left:247.0px;");
		
		return absoluteLayout_3;
	}
	
}
