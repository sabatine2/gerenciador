package com.midiasocial.controller;

import java.io.IOException;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.service.FacebookService;
import com.midiasocial.service.TwitterService;
import com.midiasocial.view.PublicacaoView;
import com.restfb.types.User;

public class PublicacaoController {

	private FacebookService fbService;
	private PublicacaoView publicacaoView;
	private UsuarioAppMidiaSocial user;
	private Publicacao publicacao;
	
	public PublicacaoController(UsuarioAppMidiaSocial user, Publicacao publicacao){
		this.user = user;
		this.publicacao = publicacao;
		
		
		publicacaoView = new PublicacaoView(this);
	}
	
	public void comentar(String mensagem){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			fbService = new FacebookService();
			fbService.comentar(publicacao.getIdMidia(), mensagem, user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.comentar(Long.parseLong(publicacao.getIdMidia()), mensagem);
        }
	}
	
	public void carregarPublicacao(){
		
		publicacaoView.carregarPublicacao(user.getAppMidiaSocial().getRedeSocial(), publicacao);
	}
	
	public void publicar(String mensagem){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			fbService = new FacebookService();
			fbService.publicar(String.valueOf(user.getIdMidia()), mensagem, user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.publicar(mensagem);
        }
	}
	
	public void publicarFoto(String id, String mensagem, String caminhoImagem) throws IOException{
		fbService = new FacebookService();
		fbService.postarFoto(id, caminhoImagem, mensagem, user);
	}
	
	public void curtir(){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			fbService = new FacebookService();
			fbService.curtirPublicacao(publicacao.getIdMidia(), user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.curtirPublicacao(Long.parseLong(publicacao.getIdMidia()));
        }	
	}
	
	public void curtirRemover(){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			fbService = new FacebookService();
			fbService.curtirRemoverPublicacao(publicacao.getIdMidia(), user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.curtirRemoverPublicacao(Long.parseLong(publicacao.getIdMidia()));
        }
	}
	
	public void deletar(){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			fbService = new FacebookService();
			fbService.deletarPublicacao(publicacao.getIdMidia(), user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.deletarPublicacao(Long.parseLong(publicacao.getIdMidia()));
        }	
	}
	
	public User getUserById(String id){
		
		User u;
		fbService = new FacebookService();
		u = fbService.getUserById(id, user);
		return u;
	}

	public PublicacaoView getPublicacaoView() {
		return publicacaoView;
	}

	public void setPublicacaoView(PublicacaoView publicacaoView) {
		this.publicacaoView = publicacaoView;
	}

	public UsuarioAppMidiaSocial getUser() {
		return user;
	}

	public void setUser(UsuarioAppMidiaSocial user) {
		this.user = user;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}
}
