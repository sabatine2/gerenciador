package com.midiasocial.controller;

import java.io.IOException;

import com.abstracts.controller.Controller;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.service.FacebookService;
import com.midiasocial.service.TwitterService;
import com.midiasocial.view.PublicacaoView;
import com.vaadin.ui.Component;

public class PublicacaoController extends Controller{

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
			fbService.comentar(publicacao, mensagem, user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
             twService.comentar(publicacao, mensagem, TwitterService.MODO_ON);
        }
	}
	
	public void carregarPublicacao(){
		
		publicacaoView.carregarPublicacao(user.getAppMidiaSocial().getRedeSocial(), publicacao);
	}
	
	public void publicar(String mensagem){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			fbService = new FacebookService();
			fbService.publicar(String.valueOf(user.getFanpageScreenName()), mensagem, user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.publicar(mensagem,user, 1);
        }
	}
	
	public void publicarFoto(String id, String mensagem, String caminhoImagem) throws IOException{
		fbService = new FacebookService();
		fbService.postarFoto(id, caminhoImagem, mensagem, user);
	}
	
	public void curtir(){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			fbService = new FacebookService();
			fbService.curtirPublicacao(publicacao, user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.curtirPublicacao(publicacao,TwitterService.MODO_ON);
        }	
	}
	
	public void curtirRemover(){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			fbService = new FacebookService();
			fbService.curtirRemoverPublicacao(publicacao, user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.curtirRemoverPublicacao(publicacao, TwitterService.MODO_ON);
        }
	}
	
	public void deletar(){
		if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
			fbService = new FacebookService();
			fbService.deletarPublicacao(publicacao, user);
		}
		else if(user.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
            TwitterService twService = new TwitterService(user);
            twService.deletarPublicacao(publicacao,TwitterService.MODO_ON);
        }	
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

	@Override
	public void carregaControle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
