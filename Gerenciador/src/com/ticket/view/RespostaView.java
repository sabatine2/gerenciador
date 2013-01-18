package com.ticket.view;

import com.ticket.controller.OrdemController;
import com.ticket.model.Mensagem;
import com.ticket.model.Topico;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

public class RespostaView extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout verticalLayoutPrincipal;
	private HorizontalLayout horizontalLayoutAcao;
	private NativeButton nativeButtonLimpar;
	private NativeButton nativeButtonEnviar;
	private CheckBox checkBoxAssinatura;
	private RichTextArea richTextAreaResposta;
	private ComboBox comboBoxSolucao;
    private Mensagem mensagem;
    private OrdemController ordemController;
    
	public RespostaView(OrdemController ordemController ,Mensagem mensagem) {
		this.mensagem = mensagem;
		this.ordemController = ordemController;
		
		setCaption("Resposta ao Cliente");
		setImmediate(false);
		setDescription("Resposta que seja enviada ao cliente ");
		setWidth("100.0%");
		setHeight("-1px");
		setStyleName(Reindeer.PANEL_LIGHT);
		verticalLayoutPrincipal = buildVerticalLayoutPrincipal();
		setContent(verticalLayoutPrincipal);
	}

	private VerticalLayout buildVerticalLayoutPrincipal() {
		// common part: create layout
		verticalLayoutPrincipal = new VerticalLayout();
		verticalLayoutPrincipal.setImmediate(false);
		verticalLayoutPrincipal.setWidth("100.0%");
		verticalLayoutPrincipal.setHeight("100.0%");
		verticalLayoutPrincipal.setMargin(false);
		verticalLayoutPrincipal.setSpacing(true);
		
		// comboBoxSolucao
		comboBoxSolucao = new ComboBox();
		comboBoxSolucao.setCaption("Base de Conhecimento");
		comboBoxSolucao.setImmediate(true);
		comboBoxSolucao.setWidth("100.0%");
		comboBoxSolucao.setHeight("-1px");
		comboBoxSolucao.setTabIndex(1);
		verticalLayoutPrincipal.addComponent(comboBoxSolucao);
		comboBoxSolucao.setContainerDataSource(Topico.listaBeansAtivos());
		comboBoxSolucao.setItemCaptionPropertyId("nome");
		comboBoxSolucao.addListener(new Property.ValueChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				 if(event.getProperty().getValue() instanceof Topico){
				 Topico topico = (Topico)event.getProperty().getValue();
				 atualizaResposta(topico);	
				 }else{
					 atualizaResposta(new Topico());	
				 }
					
			}
		});
		// richTextAreaResposta
		richTextAreaResposta = new RichTextArea();
		richTextAreaResposta.setCaption("Resposta (seja enviada ao cliente)");
		richTextAreaResposta.setImmediate(true);
		richTextAreaResposta.setWidth("100.0%");
		richTextAreaResposta.setHeight("-1px");
		richTextAreaResposta.setTabIndex(2);
		richTextAreaResposta.setRequired(true);
		richTextAreaResposta.setRequiredError("Resposta em branco");
		richTextAreaResposta.setValue(mensagem.getMensagem());
		verticalLayoutPrincipal.addComponent(richTextAreaResposta);
		
		// checkBoxAssinatura
		checkBoxAssinatura = new CheckBox();
		checkBoxAssinatura.setCaption("Assinatura do Funcionario");
		checkBoxAssinatura.setImmediate(false);
		checkBoxAssinatura.setWidth("-1px");
		checkBoxAssinatura.setHeight("-1px");
		checkBoxAssinatura.setTabIndex(3);
		verticalLayoutPrincipal.addComponent(checkBoxAssinatura);
		
		// horizontalLayoutAcao
		horizontalLayoutAcao = buildHorizontalLayoutAcao();
		verticalLayoutPrincipal.addComponent(horizontalLayoutAcao);
		verticalLayoutPrincipal.setComponentAlignment(horizontalLayoutAcao,
				new Alignment(20));
		
		return verticalLayoutPrincipal;
	}

	private HorizontalLayout buildHorizontalLayoutAcao() {
		// common part: create layout
		horizontalLayoutAcao = new HorizontalLayout();
		horizontalLayoutAcao.setImmediate(false);
		horizontalLayoutAcao.setWidth("-1px");
		horizontalLayoutAcao.setHeight("-1px");
		horizontalLayoutAcao.setMargin(false);
		horizontalLayoutAcao.setSpacing(true);
		
		// nativeButtonEnviar
		nativeButtonEnviar = new NativeButton();
		nativeButtonEnviar.setCaption("Enviar");
		nativeButtonEnviar.setImmediate(true);
		nativeButtonEnviar.setWidth("-1px");
		nativeButtonEnviar.setHeight("-1px");
		nativeButtonEnviar.addListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				salvar();
			}
		});
		
		horizontalLayoutAcao.addComponent(nativeButtonEnviar);
		
		// nativeButtonLimpar
		nativeButtonLimpar = new NativeButton();
		nativeButtonLimpar.setCaption("Limpar");
		nativeButtonLimpar.setImmediate(true);
		nativeButtonLimpar.setWidth("-1px");
		nativeButtonLimpar.setHeight("-1px");
		nativeButtonLimpar.addListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				richTextAreaResposta.setValue("");
				comboBoxSolucao.setValue("");
			}
		});
		horizontalLayoutAcao.addComponent(nativeButtonLimpar);
		
		return horizontalLayoutAcao;
	}

	public void modoResposta(){
		getHorizontalLayoutAcao().setVisible(true);
		checkBoxAssinatura.setVisible(true);
	}
	
	public void modoInserir(){
		getHorizontalLayoutAcao().setVisible(false);
		checkBoxAssinatura.setVisible(false);
	}
	
	public HorizontalLayout getHorizontalLayoutAcao(){
		if(horizontalLayoutAcao == null){
			horizontalLayoutAcao = new HorizontalLayout();
		}
		return horizontalLayoutAcao;
	}
	
	public void atualizaResposta(Topico topico){
		richTextAreaResposta.setValue(topico.getDescricao());
	}

	/**
	 * @return the nativeButtonLimpar
	 */
	public NativeButton getNativeButtonLimpar() {
		return nativeButtonLimpar;
	}

	/**
	 * @param nativeButtonLimpar the nativeButtonLimpar to set
	 */
	public void setNativeButtonLimpar(NativeButton nativeButtonLimpar) {
		this.nativeButtonLimpar = nativeButtonLimpar;
	}

	/**
	 * @return the nativeButtonEnviar
	 */
	public NativeButton getNativeButtonEnviar() {
		return nativeButtonEnviar;
	}

	/**
	 * @param nativeButtonEnviar the nativeButtonEnviar to set
	 */
	public void setNativeButtonEnviar(NativeButton nativeButtonEnviar) {
		this.nativeButtonEnviar = nativeButtonEnviar;
	}
	
	public void commit(){
		richTextAreaResposta.validate();
		mensagem.setMensagem(richTextAreaResposta.getValue().toString());
	}
	
	public void salvar(){
		try {
			commit();
		    if(ordemController.salvarMensagem(mensagem)){
		    	 MessageBox mb = new MessageBox(getWindow(), 
							"Mensagem", 
		                    MessageBox.Icon.INFO, 
		                    "Mensagem enviada com sucesso",  
		                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					 mb.show(); 
		    }
		    else{
		    	 MessageBox mb = new MessageBox(getWindow(), 
							"Mensagem", 
		                    MessageBox.Icon.INFO, 
		                    "Falha ao enviar a mensagem",  
		                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					 mb.show(); 
		    }
		} catch (Exception e) {
			 MessageBox mb = new MessageBox(getWindow(), 
						"Mensagem", 
	                    MessageBox.Icon.INFO, 
	                    "Falha ao enviar a mensagem " + e.getMessage(),  
	                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
				 mb.show(); 
		}
	}
	
	public Mensagem getResposta(){
		return mensagem;
	}
}
