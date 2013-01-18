package com.midiasocial.view;

import java.io.IOException;
import java.util.List;

import org.apache.tools.ant.taskdefs.Sleep;
import org.json.JSONException;
import org.json.JSONObject;

import com.auditor.helper.Conexao;
import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.midiasocial.controller.ComentarioController;
import com.midiasocial.controller.PublicacaoController;
import com.midiasocial.controller.PaginaController;
import com.midiasocial.model.Comentario;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.vaadin.data.util.BeanItemContainer;
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
import com.view.componente.ImagemLoad;


@SuppressWarnings({"serial", "rawtypes", "unchecked", "static-access", "unused"})
public class PaginaView extends Panel {

	private PaginaController pageController;
	private PublicacaoController publicacaoController;
	private ComentarioController comentarioController;
	
	private ComboBox cbUsuario;
	private Panel panelConectar;
	private Panel panelPost;
	private AbsoluteLayout layoutPanel;
	private Button buttonCarregar;
	private VerticalLayout verticalLayout;
	private UsuarioAppMidiaSocial user;
	private Button bAnexar;
	private String url;
	private boolean isAnexo = false;
	
	private VerticalLayout publicacaoLayout;
	
	public PaginaView(PaginaController pageController){
		this.pageController = pageController;
		
		setWidth("100%");
		setHeight("100%");
		
		verticalLayout = new VerticalLayout();
    	verticalLayout.setMargin(true);
		verticalLayout.setSpacing(true);
		verticalLayout.addComponent(buildPanel());
		verticalLayout.setHeight("-1px");
		verticalLayout.setWidth("-1px");
		
		publicacaoLayout = new VerticalLayout();
		
		setContent(verticalLayout);
	}
	

	private Panel buildPanel(){
		
		panelConectar = new Panel();
		panelConectar.setCaption("Pagina");
		panelConectar.setImmediate(true);
		panelConectar.setWidth("855px");
		panelConectar.setHeight("90px");
		
		layoutPanel = new AbsoluteLayout();
		layoutPanel.setImmediate(false);
		layoutPanel.setWidth("100.0%");
		layoutPanel.setHeight("100.0%");
		layoutPanel.setMargin(false);
		
		cbUsuario = new ComboBox();
		cbUsuario.setCaption("Usuario");
		cbUsuario.setWidth("700px");
		cbUsuario.setImmediate(true);
		cbUsuario.setNullSelectionAllowed(false);
		cbUsuario.setContainerDataSource(listaUsuario());
		cbUsuario.setItemCaptionPropertyId("nome");
		cbUsuario.setFilteringMode(ComboBox.FILTERINGMODE_STARTSWITH);
		layoutPanel.addComponent(cbUsuario, "top:27.0px;left:15.0px;");
		
		buttonCarregar = new Button("Conectar", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					if (user == null) {
						addComponent(buildPanelPost());
						loadUserTimeLine();
						verticalLayout.addComponent(publicacaoLayout);
						final Refresher refresher = new Refresher();
						refresher.setRefreshInterval(20000);
						addComponent(refresher);
						refresher.addListener(new DatabaseListener());
						
					}
					else {
						//loadUserTimeLine();
					}
				} catch (Exception e) {
					//SELECIONE UM USUÁRIO
				}
			}
		});
		buttonCarregar.setImmediate(false);
		buttonCarregar.setWidth("-1px");
		buttonCarregar.setHeight("-1px");
				
		layoutPanel.addComponent(buttonCarregar, "top:24.0px;right:30.0px;");
		panelConectar.setContent(layoutPanel);
		
		return panelConectar;
	}
	
	private BeanItemContainer listaUsuario(){
		
		BeanItemContainer<UsuarioAppMidiaSocial> beanItem = new BeanItemContainer<UsuarioAppMidiaSocial>(UsuarioAppMidiaSocial.class);
		List listUser = UsuarioAppMidiaSocial.listaUsuario();
		for (int i = 0; i < listUser.size(); i++) {
			
			beanItem.addItem(listUser.get(i));
		}
		return beanItem;
	}
	
	private void loadUserTimeLine(){
		
		publicacaoLayout.removeAllComponents();
		publicacaoController = new PublicacaoController(user, null);
		
		user = (UsuarioAppMidiaSocial)cbUsuario.getValue();
		List<Publicacao> listPub = new Publicacao().listaPost(user);
		
		for (int i = 0; i < listPub.size(); i++) {
			
			Publicacao pub = listPub.get(i);
			
				PublicacaoController publicacaoController = new PublicacaoController(user, pub);
				publicacaoLayout.addComponent(publicacaoController.getPublicacaoView());
				publicacaoController.carregarPublicacao();
			
				if (pub.isCurtir()) {
					publicacaoController.getPublicacaoView().getbLike().setIcon(new ThemeResource(
							"../reindeer/Icons/unlike.png"));
				publicacaoController.getPublicacaoView().getbLike().setDescription("Curtir/Desfazer");
				}
				else {
					//NÃO CURTIU
				}
				
				List<Comentario> listComentario = (List<Comentario>) pub.getComentario();
				for (int j = 0; j < listComentario.size(); j++) {
				
					Comentario comentario = listComentario.get(j);
				
					comentarioController = new ComentarioController(user, comentario);
					comentarioController.carregarComentario();
					publicacaoController.getPublicacaoView().getVerticalLayout().addComponent(comentarioController.getComentarioView());
					
					if (comentario.isCurtir()) {
						comentarioController.getComentarioView().getLike().setIcon(new ThemeResource(
								"../reindeer/Icons/unlike.png"));
						comentarioController.getComentarioView().getLike().setDescription("Curtir/Desfazer");
					}
					else {
						//NÃO CURTIU
					}
				}
		  }
	}
	
	private boolean isUserLikes(String json) throws JSONException{
		
		String jsonString = json;
		JSONObject obj = new JSONObject(jsonString);
		boolean userLikes = obj.getBoolean("user_likes");
		
		return userLikes;
	}
	
	private Panel buildPanelPost(){
		
		user = (UsuarioAppMidiaSocial)cbUsuario.getValue();
		
		//Build panel
		panelPost = new Panel("Publicar");
		panelPost.setImmediate(false);
		panelPost.setWidth("855px");
		panelPost.setHeight("-1px");
		
		GridLayout layoutPanel = new GridLayout( 2, 2 );
		layoutPanel.setImmediate(false);
		layoutPanel.setWidth("100.0%");
		layoutPanel.setHeight("100.0%");
		layoutPanel.setMargin(true);
		layoutPanel.setSpacing(true);
		
		//Build buttons
		AbsoluteLayout layoutButton = new AbsoluteLayout();
		layoutButton.setWidth("100.0%");
		layoutButton.setHeight("28px");
		layoutButton.setMargin(false);
		
		Label lbNome = new Label();
		lbNome.setContentMode(Label.CONTENT_XHTML);
		lbNome.setValue("<b>"+user.getNome()+"</b>");
		layoutButton.addComponent(lbNome, "top:8.0px;left:0.0px;");		
		
		final TextArea textAFeed = new TextArea();
		
		Button bPublicar = new Button("Publicar",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				if (!isAnexo) {
					publicacaoController.publicar(textAFeed.getValue().toString());
				}
				else {
					//PUBLICAÇÃO COM FOTO
					try {
						publicacaoController.publicarFoto("persystiteste", textAFeed.getValue().toString(), url);
						System.out.println("foto publicada");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					url = null;
					isAnexo = false;
					bAnexar.setIcon(new ThemeResource("../reindeer/Icons/attach.png"));
					bAnexar.setDescription("Anexar imagem");
				}
				textAFeed.setValue("");
			}
		});
		layoutButton.addComponent(bPublicar, "top:0.0px;right:63.0px;");
		
		bAnexar = new Button("",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				if (!isAnexo) {
					//NÃO TEM ANEXO
					final ImagemLoad img = new ImagemLoad("Carregar Imagem", null, "500px", "500px");
					img.alterar.setCaption("Anexar");
				
					img.alterar.addListener(new Button.ClickListener() {
						public void buttonClick(ClickEvent event) {
							url = img.getFilePath();
							img.setVisible(false);
							isAnexo = true;
							bAnexar.setIcon(new ThemeResource("../reindeer/Icons/attached.png"));
							bAnexar.setDescription("Remover anexo");
						}
					});
					getWindow().addWindow(img);
				}
				else {
					//FOTO ANEXADA
					url = null;
					isAnexo = false;
					bAnexar.setIcon(new ThemeResource("../reindeer/Icons/attach.png"));
					bAnexar.setDescription("Anexar imagem");
				}
			}
		});
		bAnexar.setIcon(new ThemeResource("../reindeer/Icons/attach.png"));
		bAnexar.setDescription("Anexar imagem");
		
		layoutButton.addComponent(bAnexar, "top:0.0px;right:5px;");
		
		//Build post
		HorizontalLayout layoutPost = new HorizontalLayout();
		layoutPost.setWidth("100.0%");
		layoutPost.setHeight("-1px");
		layoutPost.setMargin(false);
				
		Embedded embeddedImagem = new Embedded();
		embeddedImagem.setImmediate(false);
		embeddedImagem.setWidth("50px");
		embeddedImagem.setHeight("50px");
		//if (Conexao.valida()) {
			embeddedImagem.setType(Embedded.TYPE_BROWSER);
			//embeddedImagem.setSource(new ExternalResource("https://graph.facebook.com/"+ user.getId() +"/picture"));
			embeddedImagem.setSource(new ExternalResource(user.getFotoUrl()));
		/*}
		else {
			embeddedImagem.setSource(new ThemeResource("../reindeer/Icons/mediaFoto.png"));
		}*/
		embeddedImagem.setType(1);
		embeddedImagem.setMimeType("image/jpg");
						
		layoutPost.addComponent(embeddedImagem);
				
		textAFeed.setWidth("745px");
		textAFeed.setHeight("50px");
		textAFeed.setImmediate(true);
		layoutPost.addComponent(textAFeed);
		
		layoutPanel.addComponent(layoutButton, 0, 0, 1, 0);
		layoutPanel.addComponent(layoutPost, 0, 1, 1, 1);
		
		panelPost.setContent(layoutPanel);
		return panelPost;
	}
	
	public class DatabaseListener implements RefreshListener {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = -8765221895426102605L;
	    
	    @Override
	    public void refresh(final Refresher source) {
	         loadUserTimeLine();
	    }
	  }
   }
