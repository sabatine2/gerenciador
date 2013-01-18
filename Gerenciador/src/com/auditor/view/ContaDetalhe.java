package com.auditor.view;

import java.util.Date;

import com.auditor.model.Conta;
import com.auditor.model.contato.Telefone;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.view.componente.DecimalField;

@SuppressWarnings("serial")
public class ContaDetalhe extends Panel {

	public AbsoluteLayout absoluteLayout_2;
	public TextField textFieldPrestadora;
	public DecimalField textFieldValorPago;
	public DecimalField textFieldMulta;
	public TextField textFieldUsuario;
	public PopupDateField dataPagamento;
	public PopupDateField dataVencimento;
	public DecimalField textFieldValor;
	public PopupDateField dataReferencia;
	public PopupDateField data;
	public Telefone telefone;

	public ContaDetalhe(Conta conta) {
		
		setWidth("600px");
		setHeight("110px");
		
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("100.0%");
		absoluteLayout_2.setMargin(false);
		setContent(absoluteLayout_2);
				
		// data
		data = new PopupDateField();
		data.setCaption("Data");
		data.setImmediate(false);
		data.setWidth("90px");
		data.setHeight("-1px");
		data.setInvalidAllowed(false);
		data.setTabIndex(1);
		data.setResolution(4);
		data.setValue(conta.getData());
		data.setRequired(true);
		data.setRequiredError("Por favor insira uma Data");
		absoluteLayout_2.addComponent(data, "top:19.0px;left:11.0px;");
		
		// dataReferencia
		dataReferencia = new PopupDateField();
		dataReferencia.setCaption("Refer�ncia");
		dataReferencia.setImmediate(true);
		dataReferencia.setWidth("70px");
		dataReferencia.setHeight("-1px");
		dataReferencia.setInvalidAllowed(false);
		dataReferencia.setTabIndex(2);
		dataReferencia.setResolution(5);
		dataReferencia.setRequired(true);
		dataReferencia.setRequiredError("Por favor insira uma Data de Referencia");
		dataReferencia.setValue(conta.getReferencia());
		absoluteLayout_2.addComponent(dataReferencia,
				"top:19.0px;left:119.0px;");
		
		// textFieldValor
		textFieldValor = new DecimalField();
		textFieldValor.setCaption("Valor");
		textFieldValor.setImmediate(false);
		textFieldValor.setWidth("118px");
		textFieldValor.setHeight("-1px");
		textFieldValor.setTabIndex(7);
		textFieldValor.setRequired(true);
		textFieldValor.setRequiredError("Por favor insira um Valor");
		textFieldValor.getValueDinheiro();
		textFieldValor.setValue(conta.getValor());
		textFieldValor.setNullRepresentation("");
		absoluteLayout_2.addComponent(textFieldValor,
				"top:68.0px;left:206.0px;");
		
		// dataVencimento
		dataVencimento = new PopupDateField();
		dataVencimento.setCaption("Vencimento");
		dataVencimento.setImmediate(false);
		dataVencimento.setWidth("90px");
		dataVencimento.setHeight("-1px");
		dataVencimento.setInvalidAllowed(false);
		dataVencimento.setTabIndex(3);
		dataVencimento.setResolution(4);
		dataVencimento.setRequired(true);
		dataVencimento.setRequiredError("Por favor insira a Data de Vencimento");
		dataVencimento.setValue(conta.getVencimento());
		absoluteLayout_2.addComponent(dataVencimento,
				"top:19.0px;left:220.0px;");
		
		// dataPagamento
		dataPagamento = new PopupDateField();
		dataPagamento.setCaption("Pagamento");
		dataPagamento.setImmediate(false);
		dataPagamento.setWidth("90px");
		dataPagamento.setHeight("-1px");
		dataPagamento.setInvalidAllowed(false);
		dataPagamento.setTabIndex(4);
		dataPagamento.setResolution(4);
		dataPagamento.setRequired(true);
		dataPagamento.setRequiredError("Por favor insira a Data de Pagamento");
		dataPagamento.setValue(conta.getPagamento());
		absoluteLayout_2
				.addComponent(dataPagamento, "top:19.0px;left:333.0px;");
		
		// textFieldUsuario
		textFieldUsuario = new TextField();
		textFieldUsuario.setCaption("Usuario");
		textFieldUsuario.setImmediate(true);
		textFieldUsuario.setWidth("181px");
		textFieldUsuario.setHeight("-1px");
		textFieldUsuario.setTabIndex(6);
		textFieldUsuario.setValue(conta.getUsuario());
		textFieldUsuario.setNullRepresentation("");
		absoluteLayout_2.addComponent(textFieldUsuario,
				"top:68.0px;left:11.0px;");
		
		// textFieldMulta
		textFieldMulta = new DecimalField();
		textFieldMulta.setCaption("Multa");
		textFieldMulta.setImmediate(false);
		textFieldMulta.setWidth("118px");
		textFieldMulta.setHeight("-1px");
		textFieldMulta.setTabIndex(8);
		textFieldMulta.getValueDinheiro();
		textFieldMulta.setValue(conta.getMulta());
		textFieldMulta.setNullRepresentation("");
		absoluteLayout_2.addComponent(textFieldMulta,
				"top:68.0px;left:337.0px;");
		
		// textFieldValorPago
		textFieldValorPago = new DecimalField();
		textFieldValorPago.setCaption("Valor Pago");
		textFieldValorPago.setImmediate(false);
		textFieldValorPago.setWidth("118px");
		textFieldValorPago.setHeight("-1px");
		textFieldValorPago.setTabIndex(9);
		textFieldValorPago.getValueDinheiro();
		textFieldValorPago.setValue(conta.getValorPago());
		textFieldValorPago.setNullRepresentation("");
		absoluteLayout_2.addComponent(textFieldValorPago,
				"top:68.0px;left:467.0px;");
		
		// textFieldPrestadora
		textFieldPrestadora = new TextField();
		textFieldPrestadora.setCaption("Prestadora");
		textFieldPrestadora.setImmediate(false);
		textFieldPrestadora.setWidth("132px");
		textFieldPrestadora.setHeight("-1px");
		textFieldPrestadora.setTabIndex(5);
		textFieldPrestadora.setRequired(true);
		textFieldPrestadora.setRequiredError("Por favor selecione uma Prestadora");
		textFieldPrestadora.setValue(conta.getPrestadora());
		textFieldPrestadora.setNullRepresentation("");
		absoluteLayout_2.addComponent(textFieldPrestadora,
				"top:19.0px;left:453.0px;");
	}
	
	public Conta commit(){
		
		Conta c = new Conta();
		
		c.setData((Date) data.getValue());
		c.setReferencia((Date) dataReferencia.getValue());
		c.setVencimento((Date) dataVencimento.getValue());
		c.setPagamento((Date) dataPagamento.getValue());
		//c.setPrestadora((PrestadoraTelefonia) textFieldPrestadora.getValue());
		c.setUsuario(textFieldUsuario.getValue().toString());
		c.setValor(textFieldValor.getValueDinheiro());
		c.setMulta(textFieldMulta.getValueDinheiro());
		c.setValorPago(textFieldValorPago.getValueDinheiro());
		
		return c;
	}
	
	public Conta alterar(Conta conta){
		
		Conta c = conta;
		
		c.setData((Date) data.getValue());
		c.setReferencia((Date) dataReferencia.getValue());
		c.setVencimento((Date) dataVencimento.getValue());
		c.setPagamento((Date) dataPagamento.getValue());
		//c.setPrestadora((PrestadoraTelefonia) textFieldPrestadora.getValue());
		c.setUsuario(textFieldUsuario.getValue().toString());
		c.setValor(textFieldValor.getValueDinheiro());
		c.setMulta(textFieldMulta.getValueDinheiro());
		c.setValorPago(textFieldValorPago.getValueDinheiro());
		
		return c;
	}
	
	public boolean valida(){
		boolean ok = true;
		
		if (data.getValue() == null) {
			ok = false;
		}
		
		if (dataReferencia.getValue() == null) {
			ok = false;
		}
		
		if (dataVencimento.getValue() == null) {
			ok = false;
		}
		
		if (dataPagamento.getValue() == null) {
			ok = false;
		}
		
		/*if (textFieldPrestadora.getValue() == null) {
			ok = false;
		}*/
		
		if (textFieldValor.getValue() == null) {
			ok = false;
		}
		
		return ok;
	}
	
	public void limpa(){
		
		dataReferencia.setValue(null);
		dataVencimento.setValue(null);
		dataPagamento.setValue(null);
		textFieldPrestadora.setValue(null);
		textFieldValorPago.setValue(null);
		textFieldMulta.setValue(null);
		textFieldUsuario.setValue(null);
		textFieldValor.setValue(null);
		data.setValue(null);	
	}
}