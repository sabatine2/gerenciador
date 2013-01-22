package com.midiasocial.controller;

import com.midiasocial.model.Comentario;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.service.FacebookService;
import com.midiasocial.service.TwitterService;
import com.midiasocial.view.ComentarioView;

public class ComentarioController {

	private ComentarioView comentarioView;
	private Comentario comentario;
	private UsuarioAppMidiaSocial user;
	
	public ComentarioController(UsuarioAppMidiaSocial user, Comentario comentario){
		this.comentario = comentario;
		this.user = user;
		
		comentarioView = new ComentarioView(this);
	}
	
	public void carregarComentario(){
		
		comentarioView.carregarComentario(comentario);
	}
	
	public void curtir(){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			FacebookService fbService = new FacebookService();
			fbService.curtirComentario(comentario.getIdMidia(), user);
		
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.curtirComentario(Long.parseLong(comentario.getIdMidia()), TwitterService.MODO_ON);
       }
	}
	
	public void curtirRemover(){
	    if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
	    	FacebookService fbService = new FacebookService();
			fbService.curtirRemoverComentario(comentario.getIdMidia(), user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
			  TwitterService twService = new TwitterService(user);
	          twService.curtirRemoverComentario(Long.parseLong(comentario.getIdMidia()), TwitterService.MODO_ON);
	    }
		
	}

	public void deletar(){
	    if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
	    	FacebookService fbService = new FacebookService();
			fbService.deletarComentario(comentario.getIdMidia(), user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
			  TwitterService twService = new TwitterService(user);
	          twService.deletarComentario(Long.parseLong(comentario.getIdMidia()),TwitterService.MODO_ON);
	    }
    }

	public ComentarioView getComentarioView() {
		return comentarioView;
	}

	public void setComentarioView(ComentarioView comentarioView) {
		this.comentarioView = comentarioView;
	}
}
