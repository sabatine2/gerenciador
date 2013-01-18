package com.auditor.view;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.auditor.controller.TelefoneController;
import com.auditor.model.Conta;
import com.auditor.model.ItemConta;
import com.auditor.model.Medida;
import com.auditor.model.TipoServico;
import com.auditor.model.contato.Telefone;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings({"serial", "unchecked", "static-access", "unused"})
public class ContaView extends VerticalLayout {

	private Button buttonAdd;
	private Button buttonCarregar;
	private Button buttonDelete;
	private Button buttonGrafico;
	private Label caption;
	private ContaDetalhe contaDetalhe;
	private AbsoluteLayout buttonLayout;
	private AbsoluteLayout espacoLayout;
	private ItemContaView itemView;
	private VerticalLayout mainLayout;
	public TelefoneController telefoneController;
	public ContaView contaView;
	public Table table;
	public Telefone telefone;
	public Conta conta;
	
	public ContaView(Telefone telefone, TelefoneController telefoneController){
		this.telefone = telefone;
		this.telefoneController = telefoneController;
		
		setMargin(true, false, true, true);
		setSpacing(true);
		setHeight("-1px");
		setWidth("-1px");
	
		caption = new Label("<b>Detalhes da Conta</b>", Label.CONTENT_XHTML);
		addComponent(caption);
	}
	
	public VerticalLayout contaViewLayout(){
		
		final VerticalLayout verticalLayout = new VerticalLayout();
		
		espacoLayout = new AbsoluteLayout();
		espacoLayout.setHeight("15px");
		espacoLayout.setWidth("600px");
		verticalLayout.addComponent(espacoLayout);
		
		buttonLayout = new AbsoluteLayout();
		buttonLayout.setHeight("35px");
		buttonLayout.setWidth("600px");
		
		buttonAdd = new Button("+", new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				
				if (buttonAdd.getCaption() == "+") {
					itemView();
					verticalLayout.addComponent(itemView);
					buttonAdd.setCaption("-");
					mainLayout.setHeight("170px");
					table.setHeight("140px");
				}
				else {
					
					verticalLayout.removeComponent(itemView);
					buttonAdd.setCaption("+");
					mainLayout.setHeight("340px");
					table.setHeight("310px");
				}
			}
		});
    
		buttonCarregar = new Button("Carregar", new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				
                ContaLoad image = new ContaLoad("Carregar Conta", conta, contaView());
                getWindow().addWindow(image);
			}});
		
		buttonDelete = new Button("Deletar", new Button.ClickListener() {
	        
			public void buttonClick(ClickEvent event) {
				
				MessageBox mb = new MessageBox(getWindow(), 
					"Deletar", 
					MessageBox.Icon.QUESTION, 
					"Deletar todos os itens?",
					new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
					new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
			  	mb.show(new MessageBox.EventListener() {
				  		 
					public void buttonClicked(ButtonType buttonType) {
							
						if (buttonType.equals(buttonType.YES)) {
							
							try {								
								telefoneController.deletarAll();
								
								table.removeAllItems();
								
								MessageBox mb = new MessageBox(getWindow(), 
									"Deletar", 
									MessageBox.Icon.INFO, 
									"Itens Deletados com Sucesso",  
									new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
								mb.show();
							}
							catch (Exception e) {
								msnConta("ERRO", MessageBox.Icon.ERROR, e.getMessage());
							}
						}
						else {}
					}
				});
			}});
		
		buttonGrafico = new Button("Grafico", new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				
				/*
				// Add a Pie Chart Image	
				PieChart pc = new PieChart();
				
				pc.setOption("width", 1000);	
				pc.setOption("height", 400);	
				pc.setOption("is3D", true);
				pc.setCaption("Tipos de Servi�o");
				pc.setSizeFull();
				
				PieChart pc2 = new PieChart();
				
				pc2.setOption("width", 1000);	
				pc2.setOption("height", 400);	
				pc2.setOption("is3D", true);
				pc2.setCaption("Grafico 2");
				pc2.setSizeFull();

				PieChart pc3 = new PieChart();
				
				pc3.setOption("width", 1000);	
				pc3.setOption("height", 400);	
				pc3.setOption("is3D", true);
				pc3.setCaption("Grafico 2");
				//pc3.setSizeFull();
				pc3.add("teste1", 50.20);
				pc3.add("teste2", 23.87);
				pc3.add("teste3", 29.32);
				pc3.add("teste4", 8.64);
				
				PieChart pc4 = new PieChart();
				
				pc4.setOption("width", 600);	
				pc4.setOption("height", 600);	
				pc4.setOption("is3D", true);
				pc4.setCaption("Grafico 2");
				//pc4.setSizeFull();
				pc4.add("teste1", 50.20);
				pc4.add("teste2", 103.87);
				pc4.add("teste3", 50.32);
				
				Set<String> teste = conta.pesquisaTipoServico().keySet();
				for (Iterator<String> i = teste.iterator(); i.hasNext();) {
					String key = i.next();
					double valor = conta.pesquisaTipoServico().get(key);
					pc.add(key, valor);
					
				}
				
				Set<String> teste2 = conta.pesquisaItens().keySet();
				for (Iterator<String> i = teste2.iterator(); i.hasNext();) {
					String key2 = i.next();
					double valor2 = conta.pesquisaItens().get(key2);
					pc2.add(key2, valor2);
					
				}
				
				getWindow().addWindow(createWindow(pc, pc2, pc3, pc4, "Gr�fico", "Pizza"));
				*/
			}});

		buttonLayout.addComponent(buttonAdd, "left:562px;");
		buttonLayout.addComponent(buttonCarregar, "left:484px;");
		buttonLayout.addComponent(buttonGrafico, "left:413px;");
		buttonLayout.addComponent(buttonDelete, "left:0px;");
		
		verticalLayout.addComponent(tabelaItens());
		verticalLayout.addComponent(buttonLayout);
		
		return verticalLayout;
	}
	
	public void modoAddConta(Conta conta){
		
		removeAllComponents();
		this.conta = conta;
		contaDetalhe = new ContaDetalhe(new Conta());
		addComponent(contaDetalhe);
		
		if (conta.getId() != null) {
			addComponent(contaViewLayout());
		}
		
	}
	
	public void modoViewConta(Conta conta){
		
		removeAllComponents();
		this.conta = conta;
		contaDetalhe = new ContaDetalhe(conta);
		addComponent(contaDetalhe);
		addComponent(contaViewLayout());
	}
	
	public VerticalLayout tabelaItens(){
		
		mainLayout = new VerticalLayout();
		
		mainLayout.setHeight("340px");
		mainLayout.setWidth("620px");
		
		Label caption = new Label("<b>Itens</b>", Label.CONTENT_XHTML);
		mainLayout.addComponent(caption);
		mainLayout.setComponentAlignment(caption, Alignment.TOP_LEFT);
		
		table = new Table();
		table.setImmediate(true);
		table.setWidth("600px");
		table.setHeight("310px");
		table.setSelectable(true);
		table.addContainerProperty("sequencia", Label.class, "");
		table.addContainerProperty("terminal", Label.class, "");
		table.addContainerProperty("local", Label.class, "");
		table.addContainerProperty("valor", Label.class, "");
		
		try{
			List<ItemConta> itemList = conta.listItemConta();
			ItemConta item;
			for (int j = 0; j < itemList.size(); j++) {
				
				item = itemList.get(j);
				
				itemTable(item);
			}
		}catch(Exception e){}
		
		mainLayout.addComponent(table);
		
		return mainLayout;
	}

	public ContaDetalhe getContaDetalhe() {
		return contaDetalhe;
	}

	public void setContaDetalhe(ContaDetalhe contaDetalhe) {
		this.contaDetalhe = contaDetalhe;
	}
	
	public void limpa(){
		
		itemView.textFieldValor.setValue("");
		itemView.comboBoxMedida.setValue(null);
		itemView.textFieldQuantidade.setValue("");
		itemView.textFieldPais.setValue("");
		itemView.textFieldComplemento.setValue("");
		itemView.textFieldLocal.setValue("");
		itemView.textFieldCodServico.setValue("");
		itemView.comboBoxTipo.setValue(null);
		itemView.textFieldTerminal.setValue("");
		itemView.popupDateFieldFim.setValue(null);
		itemView.popupDateFieldInicio.setValue(null);
		itemView.textFieldSeq.setValue("");
	}
	
	private boolean valida(){
		boolean ok = true;
		
		if (itemView.comboBoxTipo.getValue() == null) {
			ok = false;
		}
		if (itemView.popupDateFieldInicio.getValue() == null) {
			ok = false;
		}
		if (itemView.popupDateFieldFim.getValue() == null) {
			ok = false;
		}
		if (itemView.textFieldTerminal.getValue().toString().equals("")) {
			ok = false;
		}
		if (itemView.textFieldQuantidade.getValue().toString().equals("")) {
			ok = false;
		}
		if (itemView.comboBoxMedida.getValue() == null) {
			ok = false;
		}
		if (itemView.textFieldValor.getValue().toString().equals("")) {
			ok = false;
		}
		
		return ok;
	}
	
	public void salvar(){  //Bot�o Gravar
		
		if (valida()) {
			ItemConta item = new ItemConta();
			item.setMedida((Medida) itemView.comboBoxMedida.getValue());
			item.setQuantidade((String) itemView.textFieldQuantidade.getValue());
			item.setPais((String) itemView.textFieldPais.getValue());
			item.setComplemento((String) itemView.textFieldComplemento.getValue());
			item.setLocal((String) itemView.textFieldLocal.getValue());
			item.setCodServico((String) itemView.textFieldCodServico.getValue());
			item.setTipo((TipoServico) itemView.comboBoxTipo.getValue());
			item.setTerminal((String) itemView.textFieldTerminal.getValue());
			item.setDataHoraFim((Date) itemView.popupDateFieldFim.getValue());
			item.setDataHoraInicio((Date) itemView.popupDateFieldInicio.getValue());
			item.setSequencia((String) itemView.textFieldSeq.getValue());
			item.setConta(conta);
			try {
				//item.setValor(Double.parseDouble(itemView.textFieldValor.getValue().toString()));
				item.setValor(new BigDecimal(itemView.textFieldValor.getValue().toString()));
							
				item.salvar();
				itemTable(item);
				limpa();
			}
			catch (Exception e) {
				MessageBox mb = new MessageBox(getWindow(), 
					"Erro", 
					MessageBox.Icon.ERROR, 
					"Valor deve conter apenas n�meros",  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
				mb.show();
				itemView.textFieldValor.setValue("");
			}
		}
		else {
			MessageBox mb = new MessageBox(getWindow(), 
				"Erro", 
				MessageBox.Icon.ERROR,
				"Preencha os campos obrigat�rios",  
                new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
			mb.show();	
		}
	}
	
	public void itemView(){
		itemView = new ItemContaView(this);
	}
	
	public ContaView contaView(){
		contaView = this;
		return contaView;
	}
	
	public void itemTable(ItemConta item){
		Label itemSeq = new Label(String.valueOf(item.getSequencia()));
		itemSeq.setWidth("1px");
	
		Label itemTerminal = new Label(item.getTerminal());
		itemTerminal.setWidth("1px");
	
		Label itemLocal = new Label(item.getLocal());
		itemLocal.setWidth("1px");	
		
		Label itemValor = new Label(String.valueOf(item.getValor()));
		itemValor.setWidth("1px");
		
		table.addItem(new Object[]{itemSeq, itemTerminal, itemLocal, itemValor}, item);
	}
	
	public void msnConta(String titulo, MessageBox.Icon icone ,String msg){

		MessageBox mb = new MessageBox(getWindow(), 
			titulo, 
			icone, 
			msg,  
            new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
		mb.show();
	}
	
	private Window createWindow(Component component1, Component component2, Component component3, Component component4, String title, String type){
		
		Window subwindow = new Window(title +" - "+ type);
		
		subwindow.setHeight("500px");
		subwindow.setWidth("750px");
		subwindow.center();
        subwindow.setModal(true);
		
			TabSheet t = new TabSheet();
	        // Tab 1 content
			HorizontalLayout hl = new HorizontalLayout();
	        AbsoluteLayout l1 = new AbsoluteLayout();
	        l1.setSizeFull();
	        l1.setMargin(true);
	        l1.addComponent(new Label("Titulo - Teste"));
	        l1.addComponent(component1, "top:13.0px;left:-120.0px;");
	        
	        
	        // Tab 2 content
	        AbsoluteLayout l2 = new AbsoluteLayout();
	        l2.setSizeFull();
	        l2.setMargin(true);
	        l2.addComponent(new Label("Titulo - Teste"));
	        l2.addComponent(component2, "top:13.0px;left:-120.0px;");
	        
	        // Tab 3 content
	        AbsoluteLayout l3 = new AbsoluteLayout();
	        l3.setSizeFull();
	        l3.setMargin(true);
	        l3.addComponent(new Label("Titulo - Teste"));
	        l3.addComponent(component3, "top:13.0px;left:-120.0px;");

	        t.setSizeFull();

	        t.addTab(l1, "Grafico 1", null);
	        t.addTab(l2, "Grafico 2", null);
	        t.addTab(l3, "Grafico 3", null);
	        //t.addListener(this);
	        //addComponent(t);

	        subwindow.setContent(t);	
	        
	    	return subwindow;
	}
}