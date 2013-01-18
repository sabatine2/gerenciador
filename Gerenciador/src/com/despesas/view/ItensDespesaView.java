package com.despesas.view;

import java.util.Iterator;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.view.componente.DecimalField;

@SuppressWarnings("serial")
public class ItensDespesaView extends Window {

    private HorizontalLayout layout;
	private Button buttonSalvar;
	private TextArea textAreaObs;
	private com.view.componente.DecimalField textFieldPosDeducao;
	private com.view.componente.DecimalField textFieldDeducao;
	private com.view.componente.DecimalField textFieldValorItemDespesa;
	private TextField textFieldNomeItemDespesa;
	  public Component componetAtivo;


	public ItensDespesaView() {
		center();
		setWidth("840px");
		setHeight("180px");
		layout= new HorizontalLayout();
		addComponent(layout);
		
		layout.setMargin(false);
	
		// textFieldNomeItemDespesa
		textFieldNomeItemDespesa = new TextField();
		textFieldNomeItemDespesa.setCaption("Nome");
		textFieldNomeItemDespesa.setRequired(true);
		textFieldNomeItemDespesa.setImmediate(false);
		textFieldNomeItemDespesa.setWidth("320px");
		textFieldNomeItemDespesa.setHeight("-1px");
		textFieldNomeItemDespesa.setTabIndex(13);
		layout.addComponent(textFieldNomeItemDespesa);
		layout.setComponentAlignment(textFieldNomeItemDespesa, new Alignment(20));
		
		// textFieldValorItemDespesa
		textFieldValorItemDespesa = new DecimalField();
		textFieldValorItemDespesa.setCaption("Valor");
		textFieldValorItemDespesa.setImmediate(false);
		textFieldValorItemDespesa.setWidth("-1px");
		textFieldValorItemDespesa.setHeight("-1px");
		textFieldValorItemDespesa.setTabIndex(14);
		layout.addComponent(textFieldValorItemDespesa);
		layout.setComponentAlignment(textFieldValorItemDespesa, new Alignment(20));
		
		// textFieldDeducao
		textFieldDeducao = new DecimalField();
		textFieldDeducao.setCaption("Dedu��o");
		textFieldDeducao.setImmediate(false);
		textFieldDeducao.setWidth("-1px");
		textFieldDeducao.setHeight("-1px");
		textFieldDeducao.setTabIndex(15);
		layout.addComponent(textFieldDeducao);
		layout.setComponentAlignment(textFieldDeducao, new Alignment(20));
		
		/*
		 //textFieldPosDeducao
		textFieldPosDeducao = new DecimalField();
		textFieldPosDeducao.setCaption("P�s Dedu��o");
		textFieldPosDeducao.setImmediate(false);
		textFieldPosDeducao.setWidth("-1px");
		textFieldPosDeducao.setHeight("-1px");
		textFieldPosDeducao.setTabIndex(16);
		textFieldPosDeducao.setSecret(false);
		layout.addComponent(textFieldPosDeducao);
		layout.setComponentAlignment(textFieldPosDeducao, new Alignment(20));
		*/
		
		// textAreaObs
		textAreaObs = new TextArea();
		textAreaObs.setCaption("Observa��o");
		textAreaObs.setImmediate(false);
		textAreaObs.setWidth("-1px");
		textAreaObs.setHeight("-1px");
		textAreaObs.setTabIndex(16);
		textAreaObs.setInputPrompt("Digite uma Observa��o");
		layout.addComponent(textAreaObs);
		layout.setComponentAlignment(textAreaObs, new Alignment(20));
		
		// buttonSalvar
		buttonSalvar = new Button();
		buttonSalvar.setCaption("Salvar");
		buttonSalvar.setImmediate(true);
		buttonSalvar.setWidth("-1px");
		buttonSalvar.setHeight("-1px");
		buttonSalvar.setTabIndex(18);
		layout.addComponent(buttonSalvar);
		layout.setComponentAlignment(buttonSalvar, new Alignment(9));
		
		return;
	}


	public Button getButtonSalvar() {
		return buttonSalvar;
	}


	public void setButtonSalvar(Button buttonSalvar) {
		this.buttonSalvar = buttonSalvar;
	}


	public TextArea getTextAreaObs() {
		return textAreaObs;
	}


	public void setTextAreaObs(TextArea textAreaObs) {
		this.textAreaObs = textAreaObs;
	}


	public DecimalField getTextFieldPosDeducao() {
		return textFieldPosDeducao;
	}


	public void setTextFieldPosDeducao(DecimalField textFieldPosDeducao) {
		this.textFieldPosDeducao = textFieldPosDeducao;
	}


	public DecimalField getTextFieldDeducao() {
		return textFieldDeducao;
	}


	public void setTextFieldDeducao(DecimalField textFieldDeducao) {
		this.textFieldDeducao = textFieldDeducao;
	}


	public DecimalField getTextFieldValorItemDespesa() {
		return textFieldValorItemDespesa;
	}


	public void setTextFieldValorItemDespesa(DecimalField textFieldValorItemDespesa) {
		this.textFieldValorItemDespesa = textFieldValorItemDespesa;
	}


	public TextField getTextFieldNomeItemDespesa() {
		return textFieldNomeItemDespesa;
	}


	public void setTextFieldNomeItemDespesa(TextField textFieldNomeItemDespesa) {
		this.textFieldNomeItemDespesa = textFieldNomeItemDespesa;
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void limpa(){
   	 
   	 for (Iterator iterator = layout.getComponentIterator(); iterator.hasNext();) {
	         Object obj = iterator.next();
   		
	      if( obj instanceof Panel) {
			Panel panel = (Panel) obj ;
		 	 for (Iterator iterator2 = panel.getLayout().getComponentIterator(); iterator2.hasNext();) {
		         Object obj2 = iterator2.next();
		    	 if (obj2 instanceof TextField) {
						TextField tex = (TextField) obj2;
						tex.setReadOnly(false);
						tex.setValue("");
					}
		    		 if (obj2 instanceof PasswordField) {
		 				PasswordField tex = (PasswordField) obj2;
		 				tex.setReadOnly(false);
		 				tex.setValue("");
		 			}
		    		 if (obj2 instanceof ComboBox) {
		    			 ComboBox box = (ComboBox) obj2;
		    			 box.setReadOnly(false);
		    			 box.select(box.getNullSelectionItemId());
		    		}
		    		 if (obj2 instanceof TextArea) {
		    			 TextArea area = (TextArea) obj2;
		    			 area.setReadOnly(false);
		    			 area.setValue("");
		    		}
		    		 if (obj2 instanceof PopupDateField) {
		    			 PopupDateField data = (PopupDateField) obj2;
		    			 data.setReadOnly(false);
		    			 //data.setValue("");
		    		}
		 	   }
		    }
	     
	      
   		 if (obj instanceof TextField) {
				TextField tex = (TextField) obj;
				tex.setReadOnly(false);
				tex.setValue("");
			}
   		 if (obj instanceof PasswordField) {
				PasswordField tex = (PasswordField) obj;
				tex.setReadOnly(false);
				tex.setValue("");
			}
   		 if (obj instanceof ComboBox) {
   			 ComboBox box = (ComboBox) obj;
   			 box.setReadOnly(false);
   			 box.select(box.getNullSelectionItemId());
   		}
   		 if (obj instanceof TextArea) {
   			 TextArea area = (TextArea) obj;
   			 area.setReadOnly(false);
   			 area.setValue("");
   		}
   		 if (obj instanceof PopupDateField) {
   			 PopupDateField data = (PopupDateField) obj;
   			 data.setReadOnly(false);
   			 data.setValue("");
   		}
   	}
   	 
    }
	
}
