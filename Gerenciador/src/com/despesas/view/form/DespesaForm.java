package com.despesas.view.form;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.despesas.controller.DespesasController;
import com.despesas.model.Despesas;
import com.despesas.model.ItensDespesas;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.Action;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "unchecked", "static-access"})
public class DespesaForm extends AbsoluteLayout {

	private Panel panelAddDespesa;
	private VerticalLayout verticalLayout_1;
	public Table tableItensDespesas;
	private Button buttonAdicionarItem;
	private Panel panelInteerno;
	private AbsoluteLayout absoluteLayout_7;
	private TextArea textAreaObs;
	private ComboBox comboBoxStatus;
	private TextField textFieldNomeInterno;	
	private Panel panelDadosPagamento;	
	private AbsoluteLayout absoluteLayout_6;	
	private PopupDateField popupDateFieldDataLiberacao;	
	private TextField textFieldNconta;	
	private TextField textFieldAgencia;	
	private TextField textFieldBanco;	
	private Panel panelGasto;	
	private AbsoluteLayout absoluteLayout_5;	
	private Label labelIgual2;	
	private TextField textFieldSaldoFinal;	
	private TextField textFieldDeducaoG;	
	private Label labelIgual;	
	private TextField textFieldSaldo;	
	private TextField textFieldAdiantamento;	
	private TextField textFieldTotalGasto;	
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
	private ComboBox comboBoxInfo;
	private Button buttonAddItem;
	private NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	public Button buttonAdicionar;
	public Button buttonClonar;
	public Button buttonSalvar;
	public Button buttonEditar;
	public Button buttonVoltar;
	public Button buttonAlterarSenha;
	public Button buttonDeletar;
	public Button buttonImprimir;
	public TabelaFiltro tabelaFiltro;
    public VerticalLayout modoLayoutTable;
    public VerticalLayout modoLayoutView;
    public Component      modoLayoutAtivo;
    public Boolean isEditar = false;
    public ArrayList<ItensDespesas> listRemover = new ArrayList<ItensDespesas>();
    public BeanItemContainer <ItensDespesas> beans;
	public DespesasController despesasController;
	private static final Action REMOVE_ITEM_ACTION = new Action("Remove");
	public Despesas despesas;
	public ItemDespesaForm itemForm;
	public boolean isRemover = true;
	
	public DespesaForm(Despesas despesa, DespesasController despesasController){
		this.despesas = despesa;
		this.despesasController = despesasController;
		
		setImmediate(false);
		setWidth("100%");
		setHeight("100%");
		setMargin(false);
		
		// panelDados
		panelDados = buildPanelDados();
		addComponent(panelDados, "top:21.0px;left:20.0px;");
		
		// panelGasto
		panelGasto = buildPanelGasto();
		addComponent(panelGasto, "top:754.0px;left:20.0px;");
		
		// panelDadosPagamento
		panelDadosPagamento = buildPanelDadosPagamento();
		addComponent(panelDadosPagamento, "top:917.0px;left:20.0px;");

		// panelInteerno
		panelInteerno = buildPanelInteerno();
		addComponent(panelInteerno, "top:1036.0px;left:21.0px;");
		
		//if(Session.getInstance().getUsuario().getNivel().equals("user")){
		//	panelInteerno.setVisible(false)	;
		//}
		
		// panelAddDespesa
		panelAddDespesa = buildPanelAddDespesa();
		addComponent(panelAddDespesa, "top:359.0px;left:20.0px;");
	}
	
	private Panel buildPanelDados() {
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
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("101.95%");
		absoluteLayout_2.setHeight("104.92%");
		absoluteLayout_2.setMargin(false);
		
		comboBoxInfo = new ComboBox();
		comboBoxInfo.setCaption("Nome");
		comboBoxInfo.setValue("");
		comboBoxInfo.setImmediate(false);
		comboBoxInfo.setWidth("540px");
		comboBoxInfo.setHeight("-1px");
		comboBoxInfo.setTabIndex(6);
		absoluteLayout_2.addComponent(comboBoxInfo, "top:21.0px;left:21.0px;");
    	
		// textFieldCentroCusto
		textFieldCentroCusto = new TextField();
		textFieldCentroCusto.setCaption("Centro de Custo");
		textFieldCentroCusto.setValue(despesas.getCentroDeCusto());
		textFieldCentroCusto.setImmediate(false);
		textFieldCentroCusto.setWidth("237px");
		textFieldCentroCusto.setHeight("-1px");
		textFieldCentroCusto.setTabIndex(8);
		absoluteLayout_2.addComponent(textFieldCentroCusto,
				"top:21.0px;left:584.0px;");
		
		// textFieldDestinoCidade
		textFieldDestinoCidade = new TextField();
		textFieldDestinoCidade.setCaption("Destino Cidades");
		textFieldDestinoCidade.setValue(despesas.getCidadeDestino());
		textFieldDestinoCidade.setImmediate(false);
		textFieldDestinoCidade.setWidth("800px");
		textFieldDestinoCidade.setHeight("-1px");
		textFieldDestinoCidade.setTabIndex(4);
		textFieldDestinoCidade.setRequired(true);
		textFieldDestinoCidade.setRequiredError("Insira Cidade Destino");
		absoluteLayout_2.addComponent(textFieldDestinoCidade,
				"top:69.0px;left:22.0px;");
		
		// popupDateFieldInicio
		popupDateFieldInicio = new PopupDateField();
		popupDateFieldInicio.setCaption("Data In�cio");
		popupDateFieldInicio.setValue(despesas.getDataInicio());
		popupDateFieldInicio.setData(new Date());
		popupDateFieldInicio.setImmediate(false);
		popupDateFieldInicio.setWidth("220px");
		popupDateFieldInicio.setHeight("-1px");
		popupDateFieldInicio.setInvalidAllowed(false);
		popupDateFieldInicio.setTabIndex(5);
		popupDateFieldInicio.setResolution(4);
		popupDateFieldInicio.setRequired(true);
		popupDateFieldInicio.setRequiredError("Insira Data In�cio");
		absoluteLayout_2.addComponent(popupDateFieldInicio,
				"top:117.0px;left:21.0px;");
		
		// popupDateFieldFinal
		popupDateFieldFinal = new PopupDateField();
		popupDateFieldFinal.setCaption("Data Final");
		popupDateFieldFinal.setValue(despesas.getDataFinal());
		popupDateFieldFinal.setImmediate(false);
		popupDateFieldFinal.setWidth("220px");
		popupDateFieldFinal.setHeight("-1px");
		popupDateFieldFinal.setInvalidAllowed(false);
		popupDateFieldFinal.setTabIndex(6);
		popupDateFieldFinal.setResolution(4);
		popupDateFieldFinal.setRequired(true);
		popupDateFieldFinal.setRequiredError("Insira Data Final");
		absoluteLayout_2.addComponent(popupDateFieldFinal,
				"top:117.0px;left:321.0px;");
		
		// panelMotivo
		panelMotivo = buildPanelMotivo();
		absoluteLayout_2.addComponent(panelMotivo, "top:152.0px;left:22.0px;");
		
		return absoluteLayout_2;
	}

	private Panel buildPanelMotivo() {
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
		absoluteLayout_3 = new AbsoluteLayout();
		absoluteLayout_3.setImmediate(false);
		absoluteLayout_3.setWidth("100.0%");
		absoluteLayout_3.setHeight("100.0%");
		absoluteLayout_3.setMargin(false);
		
		// checkBoxProspe
		checkBoxProspe = new CheckBox();
		checkBoxProspe.setCaption("Prospec��o");
		checkBoxProspe.setValue(despesas.getMotivoDaViagemProspec());
		checkBoxProspe.setImmediate(false);
		checkBoxProspe.setWidth("-1px");
		checkBoxProspe.setHeight("-1px");
		checkBoxProspe.setTabIndex(7);
		absoluteLayout_3
				.addComponent(checkBoxProspe, "top:24.0px;left:44.0px;");
		
		// checkBoxInstalacao
		checkBoxInstalacao = new CheckBox();
		checkBoxInstalacao.setCaption("Instala��o");
		checkBoxInstalacao.setValue(despesas.getMotivoDaViagemInstalacao());
		checkBoxInstalacao.setImmediate(false);
		checkBoxInstalacao.setWidth("-1px");
		checkBoxInstalacao.setHeight("-1px");
		checkBoxInstalacao.setTabIndex(8);
		absoluteLayout_3.addComponent(checkBoxInstalacao,
				"top:24.0px;left:244.0px;");
		
		// checkBoxImplantacao
		checkBoxImplantacao = new CheckBox();
		checkBoxImplantacao.setCaption("Implanta��o");
		checkBoxImplantacao.setValue(despesas.getMotivoDaViagemImplantacao());
		checkBoxImplantacao.setImmediate(false);
		checkBoxImplantacao.setWidth("-1px");
		checkBoxImplantacao.setHeight("-1px");
		checkBoxImplantacao.setTabIndex(9);
		absoluteLayout_3.addComponent(checkBoxImplantacao,
				"top:24.0px;left:445.0px;");
		
		// checkBoxTreinamento
		checkBoxTreinamento = new CheckBox();
		checkBoxTreinamento.setCaption("Treinamento");
		checkBoxTreinamento.setValue(despesas.getMotivoDaViagemTreinamento());
		checkBoxTreinamento.setImmediate(false);
		checkBoxTreinamento.setWidth("-1px");
		checkBoxTreinamento.setHeight("-1px");
		checkBoxTreinamento.setTabIndex(10);
		absoluteLayout_3.addComponent(checkBoxTreinamento,
				"top:24.0px;left:649.0px;");
		
		// checkBoxOutros
		checkBoxOutros = new CheckBox();
		checkBoxOutros.setCaption("Outros");
		checkBoxOutros.setValue(despesas.getMotivoDaViagemOutros());
		checkBoxOutros.setImmediate(false);
		checkBoxOutros.setWidth("-1px");
		checkBoxOutros.setHeight("-1px");
		checkBoxOutros.setTabIndex(11);
		absoluteLayout_3
				.addComponent(checkBoxOutros, "top:69.0px;left:43.0px;");
		
		// textFieldOutros
		textFieldOutros = new TextField();
		textFieldOutros.setCaption("Outros");
		textFieldOutros.setValue(despesas.getOutros());
		textFieldOutros.setWidth("490px");
		textFieldOutros.setHeight("-1px");
		textFieldOutros.setTabIndex(12);
		absoluteLayout_3.addComponent(textFieldOutros,
				"top:69.0px;left:247.0px;");
		
		return absoluteLayout_3;
	}
	
	private Panel buildPanelGasto() {
		panelGasto = new Panel();
		panelGasto.setCaption("Gastos");
		panelGasto.setImmediate(false);
		panelGasto.setWidth("840px");
		panelGasto.setHeight("142px");
		
		// absoluteLayout_3
		absoluteLayout_5 = buildAbsoluteLayout_4();
		panelGasto.setContent(absoluteLayout_5);
		
		return panelGasto;
	}

	private AbsoluteLayout buildAbsoluteLayout_4() {
		absoluteLayout_5 = new AbsoluteLayout();
		absoluteLayout_5.setImmediate(false);
		absoluteLayout_5.setWidth("100.0%");
		absoluteLayout_5.setHeight("100.0%");
		absoluteLayout_5.setMargin(false);
		
		// textFieldTotalGasto
		textFieldTotalGasto = new TextField();
		textFieldTotalGasto.setCaption("Total de Gastos");
		textFieldTotalGasto.setValue(nf.format(despesas.getTotalGasto()));
		textFieldTotalGasto.setNullRepresentation("");
		textFieldTotalGasto.setInputPrompt("0,00");
		textFieldTotalGasto.setImmediate(true);
		textFieldTotalGasto.setWidth("210px");
		textFieldTotalGasto.setHeight("-1px");
		textFieldTotalGasto.setTabIndex(20);
		textFieldTotalGasto.setReadOnly(true);
		textFieldTotalGasto.addListener(new Property.ValueChangeListener() {  
			public void valueChange(ValueChangeEvent event) {

				textFieldSaldo.setReadOnly(false);
				textFieldSaldoFinal.setReadOnly(false);
				textFieldTotalGasto.setReadOnly(false);
				
				textFieldSaldo.setValue(nf.format(new BigDecimal(converter(textFieldTotalGasto.getValue().toString())).subtract(new BigDecimal(converter(textFieldAdiantamento.getValue().toString())))));
				textFieldSaldoFinal.setValue(nf.format(new BigDecimal(converter(textFieldSaldo.getValue().toString())).subtract(new BigDecimal(converter(textFieldDeducaoG.getValue().toString())))));
				textFieldTotalGasto.setValue(nf.format(new BigDecimal(converter(textFieldTotalGasto.getValue().toString()))));
				
				textFieldSaldo.setReadOnly(true);
				textFieldSaldoFinal.setReadOnly(true);
				textFieldTotalGasto.setReadOnly(true);
			}
		});
		absoluteLayout_5.addComponent(textFieldTotalGasto, "top:31.0px;left:28.0px;");
	
		// textFieldAdiantamento
		textFieldAdiantamento = new TextField();
		textFieldAdiantamento.setCaption("Adiantamento Recebido");
		textFieldAdiantamento.setInputPrompt("0,00");
		textFieldAdiantamento.setValue(nf.format(despesas.getAdianRecebido()));
		textFieldAdiantamento.setNullRepresentation("");
		textFieldAdiantamento.setImmediate(true);
		textFieldAdiantamento.setWidth("216px");
		textFieldAdiantamento.setHeight("-1px");
		textFieldAdiantamento.setTabIndex(21);
		textFieldAdiantamento.addListener(new Property.ValueChangeListener() {  
			public void valueChange(ValueChangeEvent event) {
				try{
					
					textFieldSaldo.setReadOnly(false);
					textFieldSaldoFinal.setReadOnly(false);
					
					textFieldSaldo.setValue(nf.format(new BigDecimal(converter(textFieldTotalGasto.getValue().toString())).subtract(new BigDecimal(converter(textFieldAdiantamento.getValue().toString())))));
					textFieldSaldoFinal.setValue(nf.format(new BigDecimal(converter(textFieldSaldo.getValue().toString())).subtract(new BigDecimal(converter(textFieldDeducaoG.getValue().toString())))));
					textFieldAdiantamento.setValue(nf.format(new BigDecimal(converter(textFieldAdiantamento.getValue().toString()))));
					
					textFieldSaldo.setReadOnly(true);
					textFieldSaldoFinal.setReadOnly(true);
				}
				catch(Exception e){
					if (textFieldAdiantamento.getValue().equals("")) {
						textFieldAdiantamento.setValue("R$ 0,00");
					}
				}
			}
		});
		textFieldAdiantamento.addValidator(new AbstractValidator("Adiantamento inv�lido") {
			public boolean isValid(Object value) {
				try{
					new BigDecimal(Double.parseDouble(converter(value.toString())));
					return true;
				}catch (Exception e) {
					return false;
				}
			}
		});
		absoluteLayout_5.addComponent(textFieldAdiantamento, "top:31.0px;left:292.0px;");
		
		// textFieldSaldo
		textFieldSaldo = new TextField();
		textFieldSaldo.setCaption("Saldo");
		textFieldSaldo.setInputPrompt("0,00");
		textFieldSaldo.setValue(nf.format(despesas.getSaldo()));
		textFieldSaldo.setNullRepresentation("");
		textFieldSaldo.setImmediate(true);
		textFieldSaldo.setWidth("210px");
		textFieldSaldo.setHeight("-1px");
		textFieldSaldo.setTabIndex(22);
		
		textFieldSaldo.setReadOnly(true);
		textFieldSaldo.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				
					textFieldSaldo.setReadOnly(false);
					textFieldSaldoFinal.setReadOnly(false);
					
					textFieldSaldo.setValue(nf.format(new BigDecimal(converter(textFieldTotalGasto.getValue().toString())).subtract(new BigDecimal(converter(textFieldAdiantamento.getValue().toString())))));
					textFieldSaldoFinal.setValue(nf.format(new BigDecimal(converter(textFieldSaldo.getValue().toString())).subtract(new BigDecimal(converter(textFieldDeducaoG.getValue().toString())))));
					
					textFieldSaldo.setReadOnly(true);
					textFieldSaldo.setReadOnly(true);
			}
		});
		absoluteLayout_5.addComponent(textFieldSaldo, "top:31.0px;left:598.0px;");
		
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
		textFieldDeducaoG.setValue(nf.format(despesas.getDeducaoGasto()));
		textFieldDeducaoG.setNullRepresentation("");
		textFieldDeducaoG.setInputPrompt("0,00");
		textFieldDeducaoG.setImmediate(true);
		textFieldDeducaoG.setWidth("216px");
		textFieldDeducaoG.setHeight("-1px");
		textFieldDeducaoG.setTabIndex(23);
		textFieldDeducaoG.setReadOnly(true);
		textFieldDeducaoG.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				
				textFieldSaldoFinal.setReadOnly(false);
				textFieldDeducaoG.setReadOnly(false);
				
				textFieldSaldoFinal.setValue(nf.format(new BigDecimal(converter(textFieldSaldo.getValue().toString())).subtract(new BigDecimal(converter(textFieldDeducaoG.getValue().toString())))));
				textFieldDeducaoG.setValue(nf.format(new BigDecimal(converter(textFieldDeducaoG.getValue().toString()))));
				
				textFieldSaldoFinal.setReadOnly(true);
				textFieldDeducaoG.setReadOnly(true);
			}
		});
		absoluteLayout_5.addComponent(textFieldDeducaoG, "top:77.0px;left:292.0px;");
		
		// textFieldSaldoFinal
		textFieldSaldoFinal = new TextField();
		textFieldSaldoFinal.setCaption("Saldo Final");
		textFieldSaldoFinal.setInputPrompt("0,00");
		textFieldSaldoFinal.setValue(nf.format(despesas.getSaldoFinal()));
		textFieldSaldoFinal.setNullRepresentation("");
		textFieldSaldoFinal.setImmediate(true);
		textFieldSaldoFinal.setWidth("210px");
		textFieldSaldoFinal.setHeight("-1px");
		textFieldSaldoFinal.setTabIndex(24);
		textFieldSaldoFinal.setReadOnly(true);
		absoluteLayout_5.addComponent(textFieldSaldoFinal, "top:77.0px;left:598.0px;");
		
		// labelIgual2
		labelIgual2 = new Label();
		labelIgual2.setImmediate(false);
		labelIgual2.setWidth("-1px");
		labelIgual2.setHeight("-1px");
		labelIgual2.setValue("=");
		absoluteLayout_5.addComponent(labelIgual2, "top:79.0px;left:548.0px;");
		
		return absoluteLayout_5;
	}
	
	private Panel buildPanelDadosPagamento() {
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
		absoluteLayout_6 = new AbsoluteLayout();
		absoluteLayout_6.setImmediate(false);
		absoluteLayout_6.setWidth("100.0%");
		absoluteLayout_6.setHeight("100.0%");
		absoluteLayout_6.setMargin(false);
		
		// textFieldBanco
		textFieldBanco = new TextField();
		textFieldBanco.setCaption("Banco");
		textFieldBanco.setValue(despesas.getBanco());
		textFieldBanco.setImmediate(false);
		textFieldBanco.setWidth("160px");
		textFieldBanco.setHeight("-1px");
		textFieldBanco.setTabIndex(25);
		textFieldBanco.setRequired(true);
		textFieldBanco.setRequiredError("Insira Banco");
		absoluteLayout_6
				.addComponent(textFieldBanco, "top:31.0px;left:27.0px;");
		
		// textFieldAgencia
		textFieldAgencia = new TextField();
		textFieldAgencia.setCaption("Ag�ncia");
		textFieldAgencia.setValue(despesas.getAgencia());
		textFieldAgencia.setImmediate(false);
		textFieldAgencia.setWidth("160px");
		textFieldAgencia.setHeight("-1px");
		textFieldAgencia.setTabIndex(26);
		textFieldAgencia.setRequired(true);
		textFieldAgencia.setRequiredError("Insira Ag�ncia");
		absoluteLayout_6.addComponent(textFieldAgencia,
				"top:31.0px;left:224.0px;");
		
		// textFieldNconta
		textFieldNconta = new TextField();
		textFieldNconta.setCaption("N� Conta Corrente");
		textFieldNconta.setValue(despesas.getnConta());
		textFieldNconta.setImmediate(false);
		textFieldNconta.setWidth("160px");
		textFieldNconta.setHeight("-1px");
		textFieldNconta.setTabIndex(27);
		textFieldNconta.setRequired(true);
		textFieldNconta.setRequiredError("Insira N� da Conta");
		absoluteLayout_6.addComponent(textFieldNconta,
				"top:31.0px;left:423.0px;");
		
		// popupDateFieldDataLiberacao
		popupDateFieldDataLiberacao = new PopupDateField();
		popupDateFieldDataLiberacao.setCaption("Data Libera��o Pagamento");
		popupDateFieldDataLiberacao.setValue(despesas.getDataLiberacao());
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

	private Panel buildPanelInteerno() {
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
		textFieldNomeInterno.setValue(despesas.getNomeInterno());
		textFieldNomeInterno.setImmediate(false);
		textFieldNomeInterno.setWidth("357px");
		textFieldNomeInterno.setHeight("-1px");
		textFieldNomeInterno.setTabIndex(29);
		absoluteLayout_7.addComponent(textFieldNomeInterno,
				"top:34.0px;left:27.0px;");
		
		// comboBoxStatus
		comboBoxStatus = new ComboBox();
		comboBoxStatus.setCaption("Status");
		comboBoxStatus.setValue(despesas.getStatus());
		comboBoxStatus.setImmediate(true);
		comboBoxStatus.setWidth("181px");
		comboBoxStatus.setHeight("-1px");
		comboBoxStatus.setTabIndex(30);
		comboBoxStatus.setNullSelectionAllowed(false);
		comboBoxStatus.addItem("Aprovado");
		comboBoxStatus.addItem("N�o Aprovado");
		comboBoxStatus.addItem("Pendente");
	  	comboBoxStatus.setValue("Pendente");
		comboBoxStatus.setData("Pendente");
		absoluteLayout_7
				.addComponent(comboBoxStatus, "top:79.0px;left:28.0px;");
		
		// textAreaObs
		textAreaObs = new TextArea();
		textAreaObs.setCaption("Observa��o");
		textAreaObs.setValue(despesas.getObservacao());
		textAreaObs.setImmediate(false);
		textAreaObs.setWidth("373px");
		textAreaObs.setHeight("69px");
		textAreaObs.setTabIndex(31);
		absoluteLayout_7.addComponent(textAreaObs, "top:34.0px;left:437.0px;");
		
		return absoluteLayout_7;
	}
	
	private Panel buildPanelAddDespesa() {
		// common part: create layout
		panelAddDespesa = new Panel();
		panelAddDespesa.setCaption("Despesas");
		panelAddDespesa.setImmediate(false);
		panelAddDespesa.setWidth("840px");
		panelAddDespesa.setHeight("373px");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		panelAddDespesa.setContent(verticalLayout_1);
		
		return panelAddDespesa;
	}

	private VerticalLayout buildVerticalLayout_1() {
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		
		// buttonAdicionarItem
		buttonAdicionarItem = new Button("Adicionar",new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	addItem();
            }
        });
		buttonAdicionarItem.setImmediate(true);
		buttonAdicionarItem.setWidth("-1px");
		buttonAdicionarItem.setHeight("-1px");
		buttonAdicionarItem.setTabIndex(12);
		verticalLayout_1.addComponent(buttonAdicionarItem);
		
		// tableItensDespesas
		tableItensDespesas = new Table();
		tableItensDespesas.setImmediate(false);
		tableItensDespesas.setWidth("660px");
		tableItensDespesas.setHeight("245px");
		verticalLayout_1.addComponent(tableItensDespesas);
		verticalLayout_1.setComponentAlignment(tableItensDespesas, new Alignment(20));
		beans = new BeanItemContainer<ItensDespesas>(ItensDespesas.class);
 		tableItensDespesas.setContainerDataSource(beans);
 		tableItensDespesas.setVisibleColumns(new Object[]{"nome" , "valor" , "deducao" , "obervacao"});
 		tableItensDespesas.setSelectable(true);
 		tableItensDespesas.addActionHandler(new Action.Handler() {
 			/**
			 * ITEM CLICK BOTAO DIREITO EVENTO
			 */
			public void handleAction(Action action, Object sender, final Object target) {
				if (target != null) {
					if (action == REMOVE_ITEM_ACTION) {
 						MessageBox mb = new MessageBox(getWindow(), 
 							"Remover", 
 							MessageBox.Icon.QUESTION, 
 							"Remover item?",
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
 							new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
 						mb.show(new MessageBox.EventListener() {
 				  		 
 						public void buttonClicked(ButtonType buttonType) {
 							if (buttonType.equals(buttonType.YES)) {
 								//try {
 									
 									ItensDespesas item = (ItensDespesas) target;
			  	                    subsaldo(item.getValor(), item.getDeducao());
			  	                    
 									tableItensDespesas.removeItem(item);
 									beans.removeItem(item);
 									
 									MessageBox mb = new MessageBox(getWindow(), 
 										"Remover", 
 										MessageBox.Icon.INFO, 
 										"Item removido",  
 										new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 									mb.show();
 								//} catch (Exception e) {}
 							}
 							else {}
 						}
 					});
					}
				}
 				else {}
            }
			
            public Action[] getActions(Object target, Object sender) {
                    return new Action[] { REMOVE_ITEM_ACTION };
            }
        });
		return verticalLayout_1;
	}
	
	public Despesas getDespesas() {
		
		try {
			textFieldDestinoCidade.validate();
			popupDateFieldInicio.validate();
			popupDateFieldFinal.validate();
			textFieldAdiantamento.validate();
			textFieldBanco.validate();
			textFieldAgencia.validate();
			textFieldNconta.validate();
			textFieldCentroCusto.validate();
			//textFieldCpf.validate();
			textFieldDeducaoG.validate();
			//textFieldNome.validate();
			textFieldNomeInterno.validate();
			textFieldOutros.validate();
			textFieldSaldo.validate();
			textFieldSaldoFinal.validate();
			textFieldTotalGasto.validate();
			comboBoxStatus.validate();
			checkBoxImplantacao.validate();
			checkBoxInstalacao.validate();
			checkBoxOutros.validate();
			checkBoxProspe.validate();
			checkBoxTreinamento.validate();
			popupDateFieldDataLiberacao.validate();
	    		
			String status = comboBoxStatus.getValue().toString();
			Date dataAprovacao = null;
			
			if (status.equals("Aprovado")) {
		   	     dataAprovacao = new Date();
			}
			
			//despesas.setFuncionario(funcionario)
	    	despesas.setCentroDeCusto(textFieldCentroCusto.getValue().toString());
			despesas.setCidadeDestino(textFieldDestinoCidade.getValue().toString());
			despesas.setDataInicio((Date) popupDateFieldInicio.getValue());
			despesas.setDataFinal((Date) popupDateFieldFinal.getValue());
			despesas.setMotivoDaViagemProspec((Boolean) checkBoxProspe.getValue());
			despesas.setMotivoDaViagemInstalacao((Boolean)checkBoxInstalacao.getValue());
			despesas.setMotivoDaViagemImplantacao((Boolean) checkBoxImplantacao.getValue());
			despesas.setMotivoDaViagemTreinamento((Boolean) checkBoxTreinamento.getValue());
			despesas.setMotivoDaViagemOutros((Boolean) checkBoxOutros.getValue());
			despesas.setOutros((String) textFieldOutros.getValue());
			despesas.setTotalGasto(new BigDecimal(converter(textFieldTotalGasto.getValue().toString())));
			despesas.setAdianRecebido(new BigDecimal(converter(textFieldAdiantamento.getValue().toString())));
			despesas.setSaldo(new BigDecimal(converter(textFieldSaldo.getValue().toString())));
			despesas.setDeducaoGasto(new BigDecimal(converter(textFieldDeducaoG.getValue().toString())));
			despesas.setSaldoFinal(new BigDecimal(converter(textFieldSaldoFinal.getValue().toString())));
			despesas.setBanco(textFieldBanco.getValue().toString());
			despesas.setAgencia(textFieldAgencia.getValue().toString());
			despesas.setnConta(textFieldNconta.getValue().toString());
			despesas.setDataLiberacao((Date) popupDateFieldDataLiberacao.getValue());
			despesas.setNomeInterno(textFieldNomeInterno.getValue().toString());
			despesas.setStatus(comboBoxStatus.getValue().toString());
			despesas.setObservacao(textAreaObs.getValue().toString());
			despesas.setDataAprovacao(dataAprovacao);
			despesas.setItensDespesa(beans.getItemIds());
			
			return despesas;
		} 
		catch (Exception e) {
			MessageBox mb = new MessageBox(getWindow(), 
					"Erro", 
                    MessageBox.Icon.ERROR, 
                    e.getMessage(),  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
          	mb.show();
			e.getMessage();
			return null;
		}
	}
	
	public void addItem(){
		
    	itemForm = new ItemDespesaForm(new ItensDespesas());
    	VerticalLayout itemLayout = new VerticalLayout();
    	itemLayout.addComponent(itemForm);
    	
    	final Window win = new Window("Item Conta");
     	win.center();
     	win.setHeight("500px");
     	win.setWidth("580px");
     	win.setModal(true);
     	
     	buttonAddItem = new Button("Adicionar", new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                	
                    ItensDespesas itens = itemForm.getItens();
                    
                    if (itens != null) {
                  		itens.setDespesas(despesas);
                  		
                  		//try {
                  			BigDecimal b = new BigDecimal(converter(textFieldTotalGasto.getValue().toString()));
                  			BigDecimal c = new BigDecimal(converter(textFieldDeducaoG.getValue().toString()));

        					textFieldTotalGasto.setReadOnly(false);
        					textFieldDeducaoG.setReadOnly(false);
                  			
                  			textFieldTotalGasto.setValue(b.add((itens.getValor())));
                			textFieldDeducaoG.setValue(c.add(itens.getDeducao()));
                			
        					textFieldTotalGasto.setReadOnly(true);
        					textFieldDeducaoG.setReadOnly(true);
						//}
                  		/* catch (Exception e) {
                  			textFieldTotalGasto.setReadOnly(false);
        					textFieldDeducaoG.setReadOnly(false);
        					
                    		textFieldTotalGasto.setValue(new BigDecimal(Double.parseDouble(converter(textFieldTotalGasto.getValue().toString()))).add(new BigDecimal(Double.parseDouble(converter(itens.getValor().toString())))));
                			textFieldDeducaoG.setValue(new BigDecimal(Double.parseDouble(converter(textFieldDeducaoG.getValue().toString()))).add(new BigDecimal(Double.parseDouble(converter(itens.getDeducao().toString())))));
                		
                			textFieldTotalGasto.setReadOnly(true);
        					textFieldDeducaoG.setReadOnly(true);
                  		}
                  		*/
        				buttonAddItem.setTabIndex(34);	
        				win.setVisible(false);
        				addItensDespesas(itens);
        		
        				itemForm.limpa();
					}
                    else {}
                }
         });
		 itemLayout.addComponent(buttonAddItem);
	     win.addComponent(itemLayout);
	     getWindow().addWindow(win);
	}
	
	public void addItensDespesas(ItensDespesas itens){
   	 
  		beans.addBean(itens);
  		tableItensDespesas.addItem(itens);
 		tableItensDespesas.setVisibleColumns(new Object[]{"nome" , "valor" , "deducao" , "obervacao"});
	}
	
	public String converter(String valor){
		valor = valor.replace("R$", "");
		valor = valor.replace(" ", "");
		
		if (valor.contains(",")) {
			valor = valor.replace(".", "");
			valor = valor.replace(",", ".");			
		}
		return valor;
	}
	
	public void subsaldo(BigDecimal saldo, BigDecimal deducao){
		//try {
			textFieldDeducaoG.setReadOnly(false);
			textFieldTotalGasto.setReadOnly(false);

			textFieldTotalGasto.setValue(nf.format(new BigDecimal(converter(textFieldTotalGasto.getValue().toString())).subtract(new BigDecimal(converter(saldo.toString())))));
			
			textFieldDeducaoG.setValue(nf.format(new BigDecimal(converter(textFieldDeducaoG.getValue().toString())).subtract(new BigDecimal(converter(deducao.toString())))));

			textFieldDeducaoG.setReadOnly(true);
			textFieldTotalGasto.setReadOnly(true);
			
			if (textFieldTotalGasto.getValue().toString().equals("R$ 0,00")) {
				textFieldAdiantamento.setValue("R$ 0,00");
			}
		/*}
		catch (Exception e) {
			System.out.println("ERRO SUBSALDO" +e.getMessage());
		}*/
	}
	
	public BeanItemContainer<ItensDespesas> getBeans(){
		return beans;
	}
	
	public void setBeans(){
		beans = despesas.listaItens();
		tableItensDespesas.setContainerDataSource(beans);
		tableItensDespesas.setVisibleColumns(new Object[]{"nome" , "valor" , "deducao" , "obervacao"});
	}
}