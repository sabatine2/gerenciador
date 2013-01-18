package com.auditor.view;

import com.auditor.model.Medida;
import com.auditor.model.TipoServico;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class ItemContaView extends Panel {

	public AbsoluteLayout absoluteLayout;
	public Button buttonGravar;
	public Button buttonLimpar;
	public TextField textFieldValor;
	public ComboBox comboBoxMedida;
	public TextField textFieldQuantidade;
	public TextField textFieldPais;
	public TextField textFieldComplemento;
	public TextField textFieldLocal;
	public TextField textFieldCodServico;
	public ComboBox comboBoxTipo;
	public TextField textFieldTerminal;
	public PopupDateField popupDateFieldFim;
	public PopupDateField popupDateFieldInicio;
	public TextField textFieldSeq;
	public ContaView contaView;
	
	public ItemContaView(final ContaView contaView) {
		this.contaView = contaView;
		
		setCaption("Adicionar Item");
		setWidth("600px");
		setHeight("175px");
	
		absoluteLayout = new AbsoluteLayout();
		absoluteLayout.setImmediate(false);
		absoluteLayout.setWidth("100.0%");
		absoluteLayout.setHeight("100.0%");
		absoluteLayout.setMargin(false);
		setContent(absoluteLayout);
		
		// textFieldSeq
		textFieldSeq = new TextField();
		textFieldSeq.setCaption("Sequência");
		textFieldSeq.setImmediate(false);
		textFieldSeq.setWidth("60px");
		textFieldSeq.setHeight("-1px");
		textFieldSeq.setTabIndex(10);
		absoluteLayout.addComponent(textFieldSeq, "top:19.0px;left:11.0px;");
		
		// popupDateFieldInicio
		popupDateFieldInicio = new PopupDateField();
		popupDateFieldInicio.setCaption("Data Inicio");
		popupDateFieldInicio.setImmediate(false);
		popupDateFieldInicio.setWidth("110px");
		popupDateFieldInicio.setHeight("-1px");
		popupDateFieldInicio.setInvalidAllowed(false);
		popupDateFieldInicio.setRequired(true);
		popupDateFieldInicio.setTabIndex(12);
		absoluteLayout.addComponent(popupDateFieldInicio,
				"top:19.0px;left:235.0px;");
		
		// popupDateFieldFim
		popupDateFieldFim = new PopupDateField();
		popupDateFieldFim.setCaption("Data Fim");
		popupDateFieldFim.setImmediate(false);
		popupDateFieldFim.setWidth("110px");
		popupDateFieldFim.setHeight("-1px");
		popupDateFieldFim.setInvalidAllowed(false);
		popupDateFieldFim.setRequired(true);
		popupDateFieldFim.setTabIndex(13);
		absoluteLayout.addComponent(popupDateFieldFim,
				"top:19.0px;left:358.0px;");
		
		// textFieldTerminal
		textFieldTerminal = new TextField();
		textFieldTerminal.setCaption("Terminal");
		textFieldTerminal.setImmediate(false);
		textFieldTerminal.setWidth("110px");
		textFieldTerminal.setHeight("-1px");
		textFieldTerminal.setRequired(true);
		textFieldTerminal.setTabIndex(14);
		absoluteLayout.addComponent(textFieldTerminal,
				"top:19.0px;left:481.0px;");
		
		// comboBoxTipo
		comboBoxTipo = new ComboBox();
		comboBoxTipo.setCaption("Tipo Serviço");
		comboBoxTipo.setImmediate(false);
		comboBoxTipo.setWidth("140px");
		comboBoxTipo.setHeight("-1px");
		comboBoxTipo.setContainerDataSource(TipoServico.ListaBens());
		comboBoxTipo.setItemCaptionPropertyId("descricao");
		comboBoxTipo.setRequired(true);
		comboBoxTipo.setTabIndex(11);
		absoluteLayout.addComponent(comboBoxTipo, "top:19.0px;left:81.0px;");
		comboBoxTipo.setNullSelectionAllowed(false);
		
		// textFieldCodServico
		textFieldCodServico = new TextField();
		textFieldCodServico.setCaption("Cod. Serviço");
		textFieldCodServico.setImmediate(false);
		textFieldCodServico.setWidth("140px");
		textFieldCodServico.setHeight("-1px");
		textFieldCodServico.setTabIndex(20);
		absoluteLayout.addComponent(textFieldCodServico,
				"top:118.0px;left:11.0px;");
		
		// textFieldLocal
		textFieldLocal = new TextField();
		textFieldLocal.setCaption("Local");
		textFieldLocal.setImmediate(false);
		textFieldLocal.setWidth("140px");
		textFieldLocal.setHeight("-1px");
		textFieldLocal.setTabIndex(15);
		absoluteLayout.addComponent(textFieldLocal, "top:68.0px;left:11.0px;");
		
		// textFieldComplemento
		textFieldComplemento = new TextField();
		textFieldComplemento.setCaption("Complemento");
		textFieldComplemento.setImmediate(false);
		textFieldComplemento.setWidth("120px");
		textFieldComplemento.setHeight("-1px");
		textFieldComplemento.setTabIndex(16);
		absoluteLayout.addComponent(textFieldComplemento,
				"top:68.0px;left:161.0px;");
		
		// textFieldPais
		textFieldPais = new TextField();
		textFieldPais.setCaption("País");
		textFieldPais.setImmediate(false);
		textFieldPais.setWidth("120px");
		textFieldPais.setHeight("-1px");
		textFieldPais.setTabIndex(17);
		absoluteLayout.addComponent(textFieldPais, "top:68.0px;left:291.0px;");
		
		// textFieldQuantidade
		textFieldQuantidade = new TextField();
		textFieldQuantidade.setCaption("Quantidade");
		textFieldQuantidade.setImmediate(false);
		textFieldQuantidade.setWidth("70px");
		textFieldQuantidade.setHeight("-1px");
		textFieldQuantidade.setRequired(true);
		textFieldQuantidade.setTabIndex(18);
		absoluteLayout.addComponent(textFieldQuantidade,
				"top:68.0px;left:421.0px;");
		
		// comboBoxMedida
		comboBoxMedida = new ComboBox();
		comboBoxMedida.setCaption("Medida");
		comboBoxMedida.setImmediate(false);
		comboBoxMedida.setWidth("89px");
		comboBoxMedida.setHeight("-1px");
		comboBoxMedida.setContainerDataSource(Medida.ListaBens());
		comboBoxMedida.setItemCaptionPropertyId("descricao");
		comboBoxMedida.setRequired(true);
		comboBoxMedida.setTabIndex(19);
		absoluteLayout.addComponent(comboBoxMedida, "top:68.0px;left:501.0px;");
		comboBoxMedida.setNullSelectionAllowed(false);
		
		// textFieldValor
		textFieldValor = new TextField();
		textFieldValor.setCaption("Valor");
		textFieldValor.setImmediate(false);
		textFieldValor.setWidth("120px");
		textFieldValor.setHeight("-1px");
		textFieldValor.setRequired(true);
		textFieldValor.setTabIndex(21);
		absoluteLayout
				.addComponent(textFieldValor, "top:118.0px;left:161.0px;");
		
		// buttonLimpar
		buttonLimpar = new Button("Limpar", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				contaView.limpa();
			}
		});
		buttonLimpar.setImmediate(true);
		buttonLimpar.setWidth("-1px");
		buttonLimpar.setHeight("-1px");
		absoluteLayout.addComponent(buttonLimpar, "top:115.0px;left:449.0px;");
		
		// buttonGravar
		buttonGravar = new Button("Gravar", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				contaView.salvar();
			}
		});
		buttonGravar.setImmediate(true);
		buttonGravar.setWidth("-1px");
		buttonGravar.setHeight("-1px");
		absoluteLayout.addComponent(buttonGravar, "top:115.0px;left:524.0px;");
		
	}
}