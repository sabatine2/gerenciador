package com.midiasocial.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.midiasocial.controller.PublicacaoController;
import com.midiasocial.model.Publicacao;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.BaseTheme;

public class PublicacaoView extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridLayout		 layoutPanel;
	private AbsoluteLayout 	 layoutButton;
	private	VerticalLayout	 verticalLayout;
	private HorizontalLayout layoutPost;
	private HorizontalLayout layoutLinkButton;
	private Label            labelMensagem;
	private Button 			 bLike;
	private Button 			 bComent;
	private Button			 showComments;
	private Button			 anexo;
	private Button			 bRemover;
	private Button			 bFind;
	private Label			 lbNome;
	private Embedded 		 embeddedImagem;
	private boolean 		 showHide;
	
	private PublicacaoController publicacaoController;
	
	public PublicacaoView(final PublicacaoController publicacaoController){
		this.publicacaoController = publicacaoController;
		
		//Build panel
		setImmediate(false);
		setWidth("855px");
		setHeight("-1px");
	    
		//Build layout
		layoutPanel = new GridLayout( 2, 4 );
		layoutPanel.setImmediate(false);
		layoutPanel.setWidth("100.0%");
		layoutPanel.setHeight("100.0%");
		layoutPanel.setMargin(true);
		layoutPanel.setSpacing(true);
		setContent(layoutPanel);

		//LinkButton
		
		layoutLinkButton = new HorizontalLayout();
		layoutLinkButton.setWidth("100.0%");
		layoutLinkButton.setHeight("-1px");
		layoutLinkButton.setMargin(false);
		
		showHide = false;
		showComments = new Button("",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (showHide == false) {
					verticalLayout.setVisible(true);
					showHide = true;
				}
				else {
					verticalLayout.setVisible(false);
					showHide = false;
				}
			}
		});
		showComments.setStyleName(BaseTheme.BUTTON_LINK);
		layoutLinkButton.addComponent(showComments);
		
		anexo = new Button("",new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                    getWindow().addWindow(buildAnexoWindow(publicacaoController.getPublicacao().getAnexoUrl()));
                    System.out.println(publicacaoController.getPublicacao().getAnexoUrl());
            }
	    });
	    anexo.setStyleName(BaseTheme.BUTTON_LINK);
	    anexo.setVisible(false);
	    layoutLinkButton.addComponent(anexo);
		
		
		//VerticalLayout
		verticalLayout = new VerticalLayout();
		verticalLayout.setWidth("100.0%");
		verticalLayout.setHeight("100.0%");
		verticalLayout.setMargin(false, false, false, true);
		verticalLayout.setVisible(false);
		
		//Add to layout
		layoutPanel.addComponent(buildButtonLayout(), 0, 0, 1, 0);
		layoutPanel.addComponent(buildPostLayout(), 0, 1, 1, 1);
		layoutPanel.addComponent(layoutLinkButton, 0, 2);
		layoutPanel.addComponent(verticalLayout, 0, 3, 1, 3);
	}
	
	private AbsoluteLayout buildButtonLayout(){
		
		//Build layout
		layoutButton = new AbsoluteLayout();
		layoutButton.setWidth("100.0%");
		layoutButton.setHeight("28px");
		layoutButton.setMargin(false);
		
		//Build buttons
		lbNome = new Label();
		lbNome.setContentMode(Label.CONTENT_XHTML); 
		layoutButton.addComponent(lbNome, "top:8.0px;left:0.0px;");		
		
		bComent = new Button("Comentar",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getWindow().addWindow(buildComentarioWindow());
			}
		});
		layoutButton.addComponent(bComent, "top:0.0px;right:155.0px;");
		
		bLike = new Button("",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (bLike.getIcon().toString().equals("../reindeer/Icons/like.png")) {
					publicacaoController.curtir();
					System.out.println("Curtiu");
					bLike.setIcon(new ThemeResource("../reindeer/Icons/unlike.png"));
					bLike.setDescription("Curtir/Remover");
				}
				else {
					publicacaoController.curtirRemover();
					bLike.setIcon(new ThemeResource("../reindeer/Icons/like.png"));
					bLike.setDescription("Curtir");
					System.out.println("Curtiu/Remover");
				}
			}
		});
		bLike.setWidth("45px");
		bLike.setIcon(new ThemeResource(
				"../reindeer/Icons/like.png"));
		bLike.setDescription("Curtir");
		layoutButton.addComponent(bLike, "top:0.0px;right:104px;");
		
		bFind= new Button("",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getWindow().addWindow(new PerfilInfoView(publicacaoController.getPublicacao().getIdUsuario()));
			}
		});
		bFind.setWidth("45px");
		bFind.setIcon(new ThemeResource(
				"../reindeer/Icons/find2.png"));
		bFind.setDescription("Visualizar perfil");
		layoutButton.addComponent(bFind, "top:0.0px;right:55.0px;");
		
		bRemover = new Button("",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				publicacaoController.deletar();
				//removeComponent(comentarioController.getComentarioView());
			}
		});
		bRemover.setWidth("45px");
		bRemover.setIcon(new ThemeResource(
				"../reindeer/Icons/cancel.png"));
		layoutButton.addComponent(bRemover, "top:0.0px;right:5.0px;");
		
		
		return layoutButton;
	}
	
	private HorizontalLayout buildPostLayout(){
		
		//Build layout
		layoutPost = new HorizontalLayout();
		layoutPost.setWidth("100.0%");
		layoutPost.setHeight("-1px");
		layoutPost.setMargin(false);
		
		//Image
		embeddedImagem = new Embedded();
		embeddedImagem.setImmediate(false);
		embeddedImagem.setWidth("50px");
		embeddedImagem.setHeight("50px");
		/*embeddedImagem.setSource(new ThemeResource(
				"../reindeer/Icons/50x50.png"));*/
		embeddedImagem.setType(1);
		embeddedImagem.setMimeType("image/jpg");
		//embeddedImagem.setType(Embedded.TYPE_BROWSER);
				
		layoutPost.addComponent(embeddedImagem);
		
		//TextArea
		Panel panel = new Panel();
		panel.setWidth("745px");
		panel.setHeight("-1px");
	 	labelMensagem = new Label("",Label.CONTENT_RAW);
		panel.addComponent(labelMensagem);
		layoutPost.addComponent(panel);
		
		return layoutPost;
	}
	
	private Window buildComentarioWindow(){
		
		final Window window = new Window("Coment‡rio");
		window.center();
		window.setWidth("500px");
		window.setHeight("405px");
		window.setModal(true);
		
		VerticalLayout panelLayout = new VerticalLayout();
		panelLayout.setWidth("100%");
		panelLayout.setHeight("100%");
		panelLayout.setMargin(true);
		panelLayout.setSpacing(true);
		
		Panel panel = new Panel();
		panel.setWidth("100%");
		panel.setHeight("100%");
		panel.setContent(panelLayout);
		
		ComboBox cbMensagem = new ComboBox();
		cbMensagem.setWidth("420px");
		panelLayout.addComponent(cbMensagem);
		
		final TextArea textAMensagem = new TextArea("Mensagem");
		textAMensagem.setWidth("420px");
		textAMensagem.setHeight("220px");
		panelLayout.addComponent(textAMensagem);

		Button buttonPublicar = new Button("Publicar",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				publicacaoController.comentar(textAMensagem.getValue().toString());
				window.setVisible(false);
			}
		});
		panelLayout.addComponent(buttonPublicar);
		
		window.addComponent(panel);
		return window;
	}
	
	private Window buildAnexoWindow(String anexo){
		
		final Window window = new Window("Coment‡rio");
		window.center();
		window.setWidth("500px");
		window.setHeight("405px");
		window.setModal(true);
		
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.setMargin(true);
		layout.setSpacing(true);
		
		Embedded embedded = new Embedded();
		embedded.setImmediate(false);
		embedded.setWidth("300%");
		embedded.setHeight("300%");
		embedded.setSource(new ExternalResource(anexo));
		embedded.setType(1);
		embedded.setMimeType("image/png");
		embedded.setType(Embedded.TYPE_BROWSER);
		layout.addComponent(embedded);
		
		window.setContent(layout);
		
		return window;
	}
	
	public void carregarPublicacao(String nomeRede, Publicacao pub){
		
		lbNome.setValue("<b>" + pub.getNomeUsuario() + "</b> ("+ nomeRede + ")    " + converte(pub.getDataCriacaoMidia()));
		embeddedImagem.setType(Embedded.TYPE_BROWSER);
		embeddedImagem.setSource(new ExternalResource(pub.getFotoUrl()));
		
		String text = pub.getMensagem().toString();
		String regex = "(\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
		text.replaceAll(regex, "<a target=\"_blank\" href=\"$1\">$1</a>");
		labelMensagem.setValue(text);

		showComments.setCaption(" "+pub.getComentario().size()+" "+"coment‡rios");
		if (pub.getAnexoUrl() != null) {
			anexo.setVisible(true);
			anexo.setCaption("anexo ");
		}
	}

	public String converte(Date date){
		
		long millisecondsAgo = System.currentTimeMillis()
                - date.getTime();

        if (TimeUnit.MILLISECONDS.toHours(millisecondsAgo) == 0) {
            return String
                    .format("%d min, %d seg",
                            TimeUnit.MILLISECONDS
                                    .toMinutes(millisecondsAgo),
                            TimeUnit.MILLISECONDS
                                    .toSeconds(millisecondsAgo)
                                    - TimeUnit.MINUTES
                                            .toSeconds(TimeUnit.MILLISECONDS
                                                    .toMinutes(millisecondsAgo)));
        } else if (TimeUnit.MILLISECONDS
                .toHours(millisecondsAgo) < 12) {
            return String
                    .format("%d horas, %d min",
                            TimeUnit.MILLISECONDS
                                    .toHours(millisecondsAgo),
                            TimeUnit.MILLISECONDS
                                    .toMinutes(millisecondsAgo)
                                    - TimeUnit.HOURS
                                            .toMinutes(TimeUnit.MILLISECONDS
                                                    .toHours(millisecondsAgo)));
        } else {
        	SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
            try {
				return formataData.parse(date.toString()).toString();
			} catch (ParseException e) {
				System.out.println("erro hora");
				return date.toString();
			}

        }
    }
	
	public VerticalLayout getVerticalLayout() {
		return verticalLayout;
	}

	public void setVerticalLayout(VerticalLayout verticalLayout) {
		this.verticalLayout = verticalLayout;
	}

	public Button getbLike() {
		return bLike;
	}

	public void setbLike(Button bLike) {
		this.bLike = bLike;
	}
}