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

import com.midiasocial.model.Comentario;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.ResultadoBusca;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;

public class MidiaSocialService extends Thread{

	private Long intervalo = 100l;
	private Boolean parar = true;
	
	public MidiaSocialService(Long intervalo){
		  this.intervalo = intervalo;
	}
	
	public void run(){
		
		while(parar){
		  	
			@SuppressWarnings("unchecked")
			ArrayList<UsuarioAppMidiaSocial> usuarioLista = (ArrayList<UsuarioAppMidiaSocial>) UsuarioAppMidiaSocial.listaUsuario();
			for (Iterator<UsuarioAppMidiaSocial> i = usuarioLista.iterator();i.hasNext();){
				UsuarioAppMidiaSocial u = i.next();
			
				if(u.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
					servicoFacebook(u);
				}
				else if(u.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
					servicoTwitter(u);
				}
			}
			
			try {
				sleep(intervalo*10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			interrupt();
			finalize();
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	
	}
	
	public void servicoTwitter(UsuarioAppMidiaSocial usuarioMidiaSocial){
		TwitterService s = new TwitterService(usuarioMidiaSocial); 
	    s.postToPublish(s.getInteracao());	
	    s.postToPublish(s.getUserTimeLine());
	}
	
	public void servicoFacebook(UsuarioAppMidiaSocial usuarioMidiaSocial){
		FacebookService s = new FacebookService(); 
	    s.postToPublish(s.getUserTimeLine(usuarioMidiaSocial),usuarioMidiaSocial);	
	   
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
		
		List<Publicacao> listPub = Publicacao.listOffPublicacao();
		
		for (int i = 0; i < listPub.size(); i++) {
			
			Publicacao pub = listPub.get(i);
			
			if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
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
			}
		   else if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
			
		   
		   }
		}
		listPub = null;
	}
	
	public void atualizarDeletarOff(UsuarioAppMidiaSocial usuario){
		
		List<Publicacao> listPub = Publicacao.listOffDeletar();
		List<Comentario> listCom = Comentario.listOffDeletar();
		
		for (int i = 0; i < listPub.size(); i++) {
			
			Publicacao pub = listPub.get(i);
			if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				
				FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
				facebookClient.deleteObject(pub.getIdMidia());
				
				pub.setDeletado(true);
				pub.setDataDeletado(new Date());
				pub.setDeletarOffline(false);
				pub.alterar();
			}
			else if(pub.getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
					
				   
			}
		}
		
		for (int i = 0; i < listCom.size(); i++) {
			
			Comentario com = listCom.get(i);
			
			if(com.getPublicacao().getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
				
				FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
				facebookClient.deleteObject(com.getIdMidia());
				
				com.setDeletado(true);
				com.setDataDeletado(new Date());
				com.setDeletarOffline(false);
				com.alterar();
			}
			else if(com.getPublicacao().getUsuarioAppMidiaSocial().getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
				
				   
			}
		}
	
		listCom = null;
		listPub = null;
	}
	
	public void atualizarComentarioOff(UsuarioAppMidiaSocial usuario){
		
		List<Comentario> listCom = Comentario.listOffComentario();
		
		for (int i = 0; i < listCom.size(); i++) {
			
			Comentario com = listCom.get(i);
			
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			FacebookType publishMessageResponse = facebookClient.publish(com.getPublicacao() + "/feed", FacebookType.class,
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
		}
	}

	public void atualizarCurtirOff(UsuarioAppMidiaSocial usuario){
		
		List<Publicacao> listPub = Publicacao.listOffCurtir();
		List<Comentario> listCom = Comentario.listOffCurtir();
		
		for (int i = 0; i < listPub.size(); i++) {
			
			Publicacao pub = listPub.get(i);
			
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.publish(pub.getIdMidia() + "/likes", Boolean.class);
			
			pub.setCurtir(true);
			pub.setCurtirOffline(false);
			pub.alterar();
		}
		
		for (int i = 0; i < listCom.size(); i++) {
			
			Comentario com = listCom.get(i);
			
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.publish(com.getIdMidia() + "/likes", Boolean.class);
			
			com.setCurtir(true);
			com.setCurtirOffline(false);
			com.alterar();
		}
	}
	
	public void atualizarCurtirRemoverOff(UsuarioAppMidiaSocial usuario){
		
		List<Publicacao> listPub = Publicacao.listOffCurtirRemover();
		List<Comentario> listCom = Comentario.listOffCurtirRemover();
		
		for (int i = 0; i < listPub.size(); i++) {
			
			Publicacao pub = listPub.get(i);
			
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.publish(pub.getIdMidia() + "/likes", Boolean.class);
			
			pub.setCurtir(false);
			pub.setCurtirRemoverOffline(false);
			pub.alterar();
		}
		
		for (int i = 0; i < listCom.size(); i++) {
			
			Comentario com = listCom.get(i);
			
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.publish(com.getIdMidia() + "/likes", Boolean.class);
			
			com.setCurtir(false);
			com.setCurtirRemoverOffline(false);
			com.alterar();
		}
	}
	
}
