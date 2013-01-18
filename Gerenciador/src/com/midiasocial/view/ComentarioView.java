package com.midiasocial.view;

import com.midiasocial.controller.ComentarioController;
import com.midiasocial.model.Comentario;
import com.restfb.types.Comment;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings({"serial", "deprecation", "unused"})
public class ComentarioView extends HorizontalLayout{

	private TextArea		textAComments;
	private VerticalLayout 	commentsButton;
	private Button			like;
	private Button			remover;
	
	private ComentarioController comentarioController;
	
	public ComentarioView(final ComentarioController comentarioController){
		this.comentarioController = comentarioController;
			
		setWidth("-1px");
		setHeight("-1px");
		setMargin(true, false, false, false);
		setSpacing(true);
			
		//TextArea
		textAComments = new TextArea();
		textAComments.setWidth("725px");
		textAComments.setHeight("52px");
		textAComments.setImmediate(true);
		
		addComponent(textAComments);
			
		//Layout buttons
		commentsButton = new VerticalLayout();
		commentsButton.setWidth("-1px");
		commentsButton.setHeight("-1px");
		commentsButton.setMargin(true, false, false, false);
		commentsButton.setSpacing(false);
			
		//Buttons
		like = new Button("",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (like.getIcon().toString().equals("../reindeer/Icons/like.png")) {
					comentarioController.curtir();
					System.out.println("Curtiu");
					like.setIcon(new ThemeResource("../reindeer/Icons/unlike.png"));
					like.setDescription("Curtir/Remover");
				}
				else {
					comentarioController.curtirRemover();
					like.setIcon(new ThemeResource("../reindeer/Icons/like.png"));
					like.setDescription("Curtir");
					System.out.println("Curtiu/Remover");
				}
			}
		});
		like.setWidth("45px");
		like.setIcon(new ThemeResource(
				"../reindeer/Icons/like.png"));
		commentsButton.addComponent(like);
		like.setDescription("Curtir");
			
		remover = new Button("",new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				comentarioController.deletar();
				//removeComponent(comentarioController.getComentarioView());
			}
		});
		remover.setWidth("45px");
		remover.setIcon(new ThemeResource(
				"../reindeer/Icons/cancel.png"));
		commentsButton.addComponent(remover);
			
		addComponent(commentsButton);
	}
	
	public void showComments(Comment comment){
	
		textAComments.setValue(comment.getMessage());
		textAComments.setCaption(comment.getFrom().getName().toString() + "   -   "
		+ comment.getCreatedTime().toLocaleString());
	}

	public void carregarComentario(Comentario comentario){
		
		textAComments.setValue(comentario.getMensagem());
		textAComments.setCaption(comentario.getNomeUsuario() + "   -   "
		+ comentario.getDataCriacaoMidia());
	}
	
	public Button getLike() {
		return like;
	}

	public void setLike(Button like) {
		this.like = like;
	}
}