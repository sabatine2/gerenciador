package com.ticket.view;

import com.ticket.controller.OrdemController;
import com.ticket.model.Nota;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

public class NotaView extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout verticalLayoutPrincipal;
	private HorizontalLayout horizontalLayoutAcao;
	private NativeButton nativeButtonLimpar;
	private NativeButton nativeButtonEnviar;
	private RichTextArea richTextAreaNota;
	private TextField textFieldTitulo;
    private Nota nota;
    private OrdemController ordemController;
    
	public NotaView(OrdemController ordemController, Nota nota) {
		this.nota = nota;
		this.ordemController = ordemController;
		setCaption("Notas");
		setImmediate(false);
		setDescription("Nota interna do departamento");
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
		verticalLayoutPrincipal.setStyleName("selecionado");
		
		
		// textFieldTitulo
		textFieldTitulo = new TextField();
		textFieldTitulo.setCaption("Titulo");
		textFieldTitulo.setImmediate(false);
		textFieldTitulo.setWidth("100.0%");
		textFieldTitulo.setHeight("24px");
		textFieldTitulo.setRequired(true);
		textFieldTitulo.setValue(this.nota.getTitulo());
		textFieldTitulo.setRequiredError("Titulo em branco");
		verticalLayoutPrincipal.addComponent(textFieldTitulo);
		verticalLayoutPrincipal.setComponentAlignment(textFieldTitulo, new Alignment(
				33));
		
		// richTextAreaNota
		richTextAreaNota = new RichTextArea();
		richTextAreaNota.setCaption("Nota");
		richTextAreaNota.setImmediate(false);
		richTextAreaNota.setWidth("100.0%");
		richTextAreaNota.setHeight("-1px");
		richTextAreaNota.setRequired(true);
		richTextAreaNota.setRequiredError("Nota em branco");
		richTextAreaNota.setValue(this.nota.getNota());
		verticalLayoutPrincipal.addComponent(richTextAreaNota);
		verticalLayoutPrincipal.setComponentAlignment(richTextAreaNota, new Alignment(
				33));
		
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
		nativeButtonEnviar.setImmediate(false);
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
		
		
		// nativeButtonLimpar
		nativeButtonLimpar = new NativeButton();
		nativeButtonLimpar.setCaption("Limpar");
		nativeButtonLimpar.setImmediate(false);
		nativeButtonLimpar.setWidth("-1px");
		nativeButtonLimpar.setHeight("-1px");
		nativeButtonLimpar.addListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				richTextAreaNota.setValue("");
				textFieldTitulo.setValue("");
			}
		});
		horizontalLayoutAcao.addComponent(nativeButtonEnviar);
		horizontalLayoutAcao.addComponent(nativeButtonLimpar);
		
		return horizontalLayoutAcao;
	}
	
	public void modoResposta(){
		getHorizontalLayoutAcao().setVisible(true);
	}
	
	public void modoInserir(){
		getHorizontalLayoutAcao().setVisible(false);
	}
	
	public HorizontalLayout getHorizontalLayoutAcao(){
		if(horizontalLayoutAcao == null){
			horizontalLayoutAcao = new HorizontalLayout();
		}
		return horizontalLayoutAcao;
	}
	
	
	public void commit(){
		richTextAreaNota.validate();
		textFieldTitulo.validate();
		
		nota.setNota(richTextAreaNota.getValue().toString());
		nota.setTitulo(textFieldTitulo.getValue().toString());
		//nota.setFuncionario(Session.getInstance().getUsuario().getFuncionario());
		}
	
    public Nota getNota(){
    	return nota;
    }
   
    public void salvar(){
		try {
			commit();
		    if(ordemController.salvarNota(nota)){
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
}
