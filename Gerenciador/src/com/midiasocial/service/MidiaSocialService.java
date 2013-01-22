package com.midiasocial.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import twitter4j.Status;

import com.midiasocial.model.Comentario;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.ResultadoBusca;
import com.midiasocial.model.ServicoAtualizacao;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.model.UsuarioPubMidiaSocial;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;

public class MidiaSocialService extends Thread{

	private Long intervalo = 100l;
	private Boolean parar = true;
	private StringBuilder mensagemErro = null;
	
	public MidiaSocialService(Long intervalo){
		  this.intervalo = intervalo;
	}
	
	public void run(){
		
		while(parar){
		  	
			mensagemErro = new StringBuilder();
			
			@SuppressWarnings("unchecked")
			ServicoAtualizacao service = new ServicoAtualizacao();
			service.setDataCriacao(new Date());
			StringBuilder listaHome = new StringBuilder();
			
			ArrayList<UsuarioAppMidiaSocial> usuarioLista = (ArrayList<UsuarioAppMidiaSocial>) UsuarioAppMidiaSocial.listaUsuario();
			for (Iterator<UsuarioAppMidiaSocial> i = usuarioLista.iterator();i.hasNext();){
				UsuarioAppMidiaSocial u = i.next();
			
				listaHome.append(" "+u.getNome());
				if(u.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
					servicoFacebook(u);
				}
				else if(u.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
					servicoTwitter(u);
				}
				
				atualizarPublicacaoOff(u);
				atualizarComentarioOff();
				atualizarCurtirOff(u);
				atualizarCurtirRemoverOff(u);
				atualizarDeletarOff(u);
			}
			
			service.setDataEncerramento(new Date());
			service.setHomeMonitorada(listaHome.toString());
			service.setMensagemErro(mensagemErro.toString());
			service.salvar();
			
			mensagemErro = null;
			
			try {
				sleep(intervalo*60*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			interrupt();
			finalize();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	  	
	}
	
	public void servicoTwitter(UsuarioAppMidiaSocial usuarioMidiaSocial){
		TwitterService s = new TwitterService(usuarioMidiaSocial); 
		try{
			s.postToPublish(s.getInteracao());	
		    s.postToPublish(s.getUserTimeLine());
		}catch (Exception e) {
		    mensagemErro.append("Erro: Twitter Service Home "+usuarioMidiaSocial.getNome()+" "+e.getMessage());
	    }
	}
	
	public void servicoFacebook(UsuarioAppMidiaSocial usuarioMidiaSocial){
		FacebookService s = new FacebookService();
		try{
			s.postToPublish(s.getUserTimeLine(usuarioMidiaSocial),usuarioMidiaSocial);	
		}catch (Exception e) {
		    mensagemErro.append("Erro: Facebook Service Home "+usuarioMidiaSocial.getNome()+" "+ e.getMessage());
	    }
	}
	
	public static ArrayList<ResultadoBusca> busca(String termoBusca){
		
		ArrayList<ResultadoBusca> listaResultado = new ArrayList<ResultadoBusca>();
		
		TwitterService t = new TwitterService(); 
		FacebookService f = new FacebookService();
		listaResultado = t.pesquisarTweetsSemAPP(termoBusca,listaResultado);
		listaResultado = f.pesquisar(termoBusca,listaResultado);
		
		Collections.sort(listaResultado, new Comparator<ResultadoBusca>() {
			  public int compare(ResultadoBusca o1, ResultadoBusca o2) {
			      if (o1.getDataCriacaoMidia() == null || o2.getDataCriacaoMidia() == null)
			        return 0;
			      return o2.getDataCriacaoMidia().compareTo(o1.getDataCriacaoMidia());
			  }
			});
		
		return listaResultado;
	}
	
	public void parar(){
		parar = false;
	}
	
	public void atualizarPublicacaoOff(UsuarioAppMidiaSocial usuario){
		
		List<Publicacao> listPub = Publicacao.listOffPublicacao(usuario);
		
		for (int i = 0; i < listPub.size(); i++) {
			
			Publicacao pub = listPub.get(i);
			
			if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				
				try{
					FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
					FacebookType publishMessageResponse = facebookClient.publish(pub.getIdDestino() + "/feed", FacebookType.class,
							Parameter.with("message", pub.getMensagem()));
					Post post = FacebookService.getPostById(publishMessageResponse.getId(), usuario);
					 
					pub.setIdMidia(post.getId());
		
					Date dataPost = null;  
					String datePost = post.getCreatedTime().toLocaleString();
					DateFormat dfPost = new SimpleDateFormat("DD/MM/yyyy HH:mm:ss");
					try {
						dataPost = dfPost.parse(datePost);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					pub.setDataCriacaoMidia(dataPost);
					pub.setPublicarOffline(false);
					pub.setIdDestino(null);
					pub.alterar();
					pub = null;
				
				}catch (Exception e) {
				    mensagemErro.append("Facebook "+usuario.getNome()+" "+ e.getMessage());
				    if(e.getMessage().contains("Duplicate status message")){
				    	Publicacao.pesquisaPostID(pub.getId()).remover();
				    }
				}
			}
		   else if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){

				try{
				    TwitterService twService = new TwitterService(usuario);
				    Status status = twService.publicar(pub.getMensagem(),usuario,TwitterService.MODO_OFF);
				    pub.setPublicarOffline(false);
				    twService.alterarPublicacao(pub, status);
				    
				}catch (Exception e) {
				    mensagemErro.append("Erro: Twitter off publicacao "+usuario.getNome()+" "+ e.getMessage());
				}
		   }
		}
		listPub = null;
	}
	
	public void atualizarDeletarOff(UsuarioAppMidiaSocial usuario){
		
		List<Publicacao> listPub = Publicacao.listOffDeletar(usuario);
		
		for (int i = 0; i < listPub.size(); i++) {
			
			Publicacao pub = listPub.get(i);
			if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				
				try{
					FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
					facebookClient.deleteObject(pub.getIdMidia());
					
					pub.setDeletado(true);
					pub.setDataDeletado(new Date());
					pub.setDeletarOffline(false);
					pub.alterar();
				}catch (Exception e) {
				    mensagemErro.append("Erro: Facebook off deleta"+usuario.getNome()+" "+ e.getMessage());
				}	
			}
			else if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
				 TwitterService twService = new TwitterService(usuario);
				 twService.deletarPublicacao(Long.parseLong(pub.getIdMidia()),TwitterService.MODO_OFF);
			}
		}
		
		List<Comentario> listCom = Comentario.listOffDeletar();
		
		for (int i = 0; i < listCom.size(); i++) {
			
			Comentario com = listCom.get(i);
			usuario = com.getPublicacao().getUsuarioAppMidiaSocial();
			
			if(com.getPublicacao().getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				
				try{
					FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
					facebookClient.deleteObject(com.getIdMidia());
					
					com.setDeletado(true);
					com.setDataDeletado(new Date());
					com.setDeletarOffline(false);
					com.alterar();
				}catch (Exception e) {
				    mensagemErro.append("Erro: Facebook off deleta "+usuario.getNome()+" "+ e.getMessage());
				}
			}
			else if(com.getPublicacao().getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
				try{
					 TwitterService twService = new TwitterService(usuario);
					 twService.deletarComentario(Long.parseLong(com.getIdMidia()),TwitterService.MODO_OFF);
					   
				}catch (Exception e) {
				    mensagemErro.append("Twitter off deleta: "+usuario.getNome()+" "+ e.getMessage());
				}
			}
		}
	
		listCom = null;
		listPub = null;
	}
	
	public void atualizarComentarioOff(){
		
		List<Comentario> listCom = Comentario.listOffComentario();
		
		for (int i = 0; i < listCom.size(); i++) {
			
			Comentario com = listCom.get(i);
			UsuarioAppMidiaSocial usuario = com.getPublicacao().getUsuarioAppMidiaSocial();
			
			if(usuario.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				
				try{
					FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
					FacebookType publishMessageResponse = facebookClient.publish(com.getPublicacao().getIdMidia() + "/comments", FacebookType.class,
							Parameter.with("message", com.getMensagem()));
					
					 Comment comment = FacebookService.getCommentById(publishMessageResponse.getId(), usuario);
					 
					 com.setIdMidia(comment.getId());
		
					Date dataComment = null;  
					String dateComment = comment.getCreatedTime().toLocaleString();
					DateFormat dfComment = new SimpleDateFormat("DD/MM/yyyy HH:mm:ss");
					try {
						dataComment = dfComment.parse(dateComment);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					com.setDataCriacaoMidia(dataComment);
					com.setComentarOffline(false);
					com.alterar();
					
				}catch (Exception e) {
				    mensagemErro.append("Erro: Facebook Off Comentario "+usuario.getNome()+" "+ e.getMessage());
				}
			}
			else if(usuario.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
				try{ 
					  TwitterService twService = new TwitterService(usuario);
					  Status status = twService.comentar(Long.parseLong(com.getPublicacao().getIdMidia()),com.getMensagem(),TwitterService.MODO_OFF);
					  com.setComentarOffline(false);
					  twService.alterarComentario(com, status);
					}catch (Exception e) {
					    mensagemErro.append("Erro: Twitter Off Comentario "+usuario.getNome()+" "+ e.getMessage());
					} 
			}
		}
	}

	public void atualizarCurtirOff(UsuarioAppMidiaSocial usuario){
		
		List<Publicacao> listPub = Publicacao.listOffCurtir(usuario);
		
		for (int i = 0; i < listPub.size(); i++) {
			
			Publicacao pub = listPub.get(i);
			
			if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				try{
					FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
					facebookClient.publish(pub.getIdMidia() + "/likes", Boolean.class);
					pub.setCurtir(true);
					pub.setCurtirOffline(false);
					pub.alterar();
					
				}catch (Exception e) {
				    mensagemErro.append("Erro: Facebook curtir off "+usuario.getNome()+" "+ e.getMessage());
				}
			}
			else if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
				try{
					 TwitterService twService = new TwitterService(usuario);
					 twService.curtirPublicacao(Long.parseLong(pub.getIdMidia()),TwitterService.MODO_OFF);
				}catch (Exception e) {
				    mensagemErro.append("Erro: Twitter curtir off"+usuario.getNome()+" "+ e.getMessage());
				}
				   
			}
		}
		
		List<Comentario> listCom = Comentario.listOffCurtir();
		
		for (int i = 0; i < listCom.size(); i++) {
			
			Comentario com = listCom.get(i);
			if(com.getPublicacao().getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				
				try{
					FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
					facebookClient.publish(com.getIdMidia() + "/likes", Boolean.class);
					
					com.setCurtir(true);
					com.setCurtirOffline(false);
					com.alterar();
				}catch (Exception e) {
				    mensagemErro.append("Erro: Facebook curtir off "+usuario.getNome()+" "+ e.getMessage());
				}
			}
			else if(com.getPublicacao().getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
				try{
					 TwitterService twService = new TwitterService(usuario);
					 twService.curtirComentario(Long.parseLong(com.getIdMidia()),TwitterService.MODO_OFF);
				}catch (Exception e) {
				    mensagemErro.append("Erro: Twitter curtir off "+usuario.getNome()+" "+ e.getMessage());
				}
				   
			}
		}
	}
	
	public void atualizarCurtirRemoverOff(UsuarioAppMidiaSocial usuario){
		
		List<Publicacao> listPub = Publicacao.listOffCurtirRemover(usuario);
			
		for (int i = 0; i < listPub.size(); i++) {
			
			Publicacao pub = listPub.get(i);
			if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				try{
				
					FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
					facebookClient.publish(pub.getIdMidia() + "/likes", Boolean.class);
					
					pub.setCurtir(false);
					pub.setCurtirRemoverOffline(false);
					pub.alterar();
				}catch (Exception e) {
				    mensagemErro.append("Facebook remove curtir off "+usuario.getNome()+" "+ e.getMessage());
				}
			}
			else if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
				try{
					TwitterService twService = new TwitterService(usuario);
					twService.curtirRemoverPublicacao(Long.parseLong(pub.getIdMidia()),TwitterService.MODO_OFF);
				}catch (Exception e) {
				    mensagemErro.append("Twitter remove curtir off "+usuario.getNome()+" "+ e.getMessage());
				}
			}
		}
		
		List<Comentario> listCom = Comentario.listOffCurtirRemover();
		
		for (int i = 0; i < listCom.size(); i++) {
			
			Comentario com = listCom.get(i);
			if(com.getPublicacao().getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				
				try{
					FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
					facebookClient.publish(com.getIdMidia() + "/likes", Boolean.class);
					
					com.setCurtir(false);
					com.setCurtirRemoverOffline(false);
					com.alterar();
				}catch (Exception e) {
				    mensagemErro.append("Facebook remove curtir off "+usuario.getNome()+" "+ e.getMessage());
				}
			}
			else if(com.getPublicacao().getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
				try{
					TwitterService twService = new TwitterService(usuario);
					twService.curtirRemoverComentario(Long.parseLong(com.getPublicacao().getIdMidia()),TwitterService.MODO_OFF);
				}catch (Exception e) {
				    mensagemErro.append("Twitter remove curtir off"+usuario.getNome()+" "+ e.getMessage());
				}
			}
		}
	}
	
}
