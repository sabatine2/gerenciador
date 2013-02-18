package com.midiasocial.view;
import java.util.Date;
import java.util.List;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.UsuarioPubMidiaSocial;

@SuppressWarnings({"serial", "static-access"})
public class PerfilInfoView extends Window {

	private AbsoluteLayout mainLayout;
	private Panel panel4;
	private VerticalLayout verticalLayout;
	private Button btAdicionar;
	private Panel panel2;
	private AbsoluteLayout layout2;
	private Embedded embedded;
	private Panel panel1;
	private AbsoluteLayout layout1;
	private Label lbRede;
	private UsuarioPubMidiaSocial usuario;

	public PerfilInfoView(String id) {
		
		this.usuario  = UsuarioPubMidiaSocial.pesquisaUsuarioIdMidia(id);

		setCaption("Perfil Usuario");
		setStyleName(Reindeer.WINDOW_LIGHT);
		setResizable(false);
		setClosable(true);
		setDraggable(false);
		setModal(true);
		setWidth("418px");
		setHeight("546px");
	
		((VerticalLayout) getContent()).setSizeFull();

		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// panel1
		panel1 = buildPanel1();
		mainLayout.addComponent(panel1, "top:20.0px;left:18.0px;");

		// panel2
		panel2 = buildPanel2();
		mainLayout.addComponent(panel2, "top:56.0px;left:18.0px;");

		// panel4
		panel4 = buildPanel4();
		mainLayout.addComponent(panel4, "top:269.0px;left:18.0px;");

		setContent(mainLayout);
		setModal(true);
		center();
		
		List<Publicacao> listPubs = new Publicacao().listLastUpdate(usuario.getIdMidia());
		for (int i = 0; i < listPubs.size(); i++) {
	
			Publicacao publicacao = listPubs.get(i);
			verticalLayout.addComponent(buildPanelLastUpdate(publicacao.getDataCriacaoMidia(), publicacao.getMensagem()));
		}
	}

	private Panel buildPanel1() {

		// common part: create layout
		panel1 = new Panel();
		panel1.setImmediate(false);
		panel1.setWidth("380px");
		panel1.setHeight("35px");

		// layout1
		layout1 = buildLayout1();
		panel1.setContent(layout1);
		return panel1;
	}

	private AbsoluteLayout buildLayout1() {

		layout1 = new AbsoluteLayout();
		layout1.setImmediate(false);
		layout1.setWidth("100.0%");
		layout1.setHeight("100.0%");
		layout1.setMargin(false);

		// lbRede
		lbRede = new Label();
		lbRede.setImmediate(false);
		lbRede.setWidth("-1px");
		lbRede.setHeight("-1px");
		lbRede.setValue("<b>"+usuario.getNome()+" - "+ usuario.getNomeRedeSocial()+"</b>");
		lbRede.setContentMode(Label.CONTENT_XHTML); 
		layout1.addComponent(lbRede, "top:5.0px;left:5.0px;");

		//btAdicionar
		btAdicionar = new Button();
		btAdicionar.setCaption("Adicionar");
		btAdicionar.setImmediate(false);
		btAdicionar.setWidth("-1px");
		btAdicionar.setHeight("-1px");
		layout1.addComponent(btAdicionar, "top:3.0px;right:5.0px;");
		return layout1;
	}

	private Panel buildPanel2() {

		panel2 = new Panel();
		panel2.setImmediate(false);
		panel2.setWidth("380px");
		panel2.setHeight("210px");
		
		// layout2
		layout2 = buildLayout2();
		panel2.setContent(layout2);
		return panel2;
	}

	private AbsoluteLayout buildLayout2() {

		layout2 = new AbsoluteLayout();
		layout2.setImmediate(false);
		layout2.setWidth("100.0%");
		layout2.setHeight("100.0%");
		layout2.setMargin(false);

		// embedded
		embedded = new Embedded();
		embedded.setImmediate(false);
		embedded.setWidth("148px");
		embedded.setHeight("148px");
		embedded.setSource(new ExternalResource(usuario.getFotoPerfilUrl()));
		embedded.setType(1);
		embedded.setMimeType("image/png");
		embedded.setType(Embedded.TYPE_BROWSER);
		layout2.addComponent(embedded, "top:18.0px;left:114.0px;");

		String url;
		if(usuario.getNomeRedeSocial().contentEquals("Facebook")){

			url = "http://www.facebook.com/";
		}
		else url = "http://www.twitter.com/";

		Link linkScreenName = new Link(usuario.getScreenName(),  new ExternalResource(url+usuario.getScreenName()));
		
		layout2.addComponent(linkScreenName, "top:175.0px;left:115.0px;");
		return layout2;
	}

	private Panel buildPanel4() {

		panel4 = new Panel();
		panel4.setImmediate(false);
		panel4.setWidth("380px");
		panel4.setHeight("-1px");

		// verticalLayout
		verticalLayout = new VerticalLayout();
		verticalLayout.setImmediate(false);
		verticalLayout.setWidth("100.0%");
		verticalLayout.setHeight("100.0%");
		verticalLayout.setMargin(false);
		verticalLayout.setSpacing(true);
		panel4.setContent(verticalLayout);
		return panel4;
	}

	private Panel buildPanelLastUpdate(Date data, String mensagem){
		
		//Build panel
		Panel panelPost = new Panel();
		panelPost.setImmediate(false);
		panelPost.setWidth("100.0%");
		panelPost.setHeight("-1px");
		//Build post
		HorizontalLayout layoutPost = new HorizontalLayout();
		layoutPost.setWidth("100.0%");
		layoutPost.setHeight("-1px");
		layoutPost.setMargin(true);
		
		Embedded embeddedImagem = new Embedded();
		embeddedImagem.setImmediate(false);
		embeddedImagem.setWidth("50px");
		embeddedImagem.setHeight("50px");
		//if (Conexao.valida()) {
			embeddedImagem.setType(Embedded.TYPE_BROWSER);
			//embeddedImagem.setSource(new ExternalResource("https://graph.facebook.com/"+ user.getId() +"/picture"));
			embeddedImagem.setSource(new ExternalResource(usuario.getFotoUrl()));
		/*}
		else {
			embeddedImagem.setSource(new ThemeResource("../reindeer/Icons/mediaFoto.png"));
		}*/
		embeddedImagem.setType(1);
		embeddedImagem.setMimeType("image/jpg");
						
		layoutPost.addComponent(embeddedImagem);
		
		Panel panelMensagem = new Panel();
		panelMensagem.setWidth("290px");
		panelMensagem.setHeight("50px");
		String regex = "(\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
		mensagem.replaceAll(regex, "<a target=\"_blank\" href=\"$1\">$1</a>");
		Label labelMensagem = new Label(data.toString()+": "+mensagem,Label.CONTENT_RAW);
		panelMensagem.addComponent(labelMensagem);
		layoutPost.addComponent(panelMensagem);
		
		panelPost.setContent(layoutPost);
		return panelPost;
	}
}
