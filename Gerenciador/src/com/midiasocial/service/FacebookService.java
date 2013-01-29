package com.midiasocial.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;

import com.midiasocial.model.Comentario;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.ResultadoBusca;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.model.UsuarioPubMidiaSocial;
import com.restfb.types.User;
import com.restfb.BinaryAttachment;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.exception.FacebookNetworkException;
import com.restfb.json.JsonObject;
import com.restfb.types.Comment;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;

@SuppressWarnings({"deprecation", "static-access", "unused"})
public class FacebookService {

	public static Connection<Post> getUserTimeLineRT(UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		Connection<Post> myFeed = null;
		
		try{
			UsuarioAppMidiaSocial user = (UsuarioAppMidiaSocial) usuarioMidiaSocial;
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			myFeed = facebookClient.fetchConnection(usuarioMidiaSocial.getFanpageScreenName()+"/feed", Post.class);
			return myFeed;
		}catch (Exception e) {
			return myFeed;
		}	
	}
	
	public Connection<Post> getUserTimeLine(UsuarioAppMidiaSocial usuarioMidiaSocial){
		UsuarioAppMidiaSocial user = (UsuarioAppMidiaSocial) usuarioMidiaSocial;
		FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
		Connection<Post> myFeed = facebookClient.fetchConnection(usuarioMidiaSocial.getFanpageScreenName()+"/feed", Post.class);
		return myFeed;
	}
	
	public Connection<Post> getUserTimeLineData(UsuarioAppMidiaSocial usuarioMidiaSocial, Date dataInicial){
		UsuarioAppMidiaSocial user = (UsuarioAppMidiaSocial) usuarioMidiaSocial;
		System.out.println("data inicial "+ dataInicial.toLocaleString());
		Parameter.with("since",dataInicial.toLocaleString());
		FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
		Connection<Post> myFeed = facebookClient.fetchConnection(usuarioMidiaSocial.getFanpageScreenName()+"/feed", Post.class);
		return myFeed;
	}
	
	private static boolean isUserLikes(UsuarioAppMidiaSocial usuarioMidiaSocial, String idComment) throws JSONException{
		
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			JsonObject jsonObj = facebookClient.fetchObject(idComment, JsonObject.class);
			System.out.println(jsonObj.toString());
			boolean userLikes = jsonObj.getBoolean("user_likes");
			return userLikes;
		
	}
	
	public void comentar(Publicacao publicacao, String mensagem, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			FacebookType publishCommentResponse = facebookClient.publish(publicacao.getIdMidia() + "/comments", FacebookType.class, 
					Parameter.with("message", mensagem));
			
			Comment comment = null;
			int tentativas = 0;
			do{
				try {
					Thread.sleep(10l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			    try{
			    	comment = getCommentById(publishCommentResponse.getId(), usuarioMidiaSocial);
			    }catch (Exception e) {
					e.printStackTrace();
					tentativas++;
				}
			}while( (comment == null) && (tentativas < 3) );
			
			salvarComentario(comment, publicacao, usuarioMidiaSocial);
			
		} catch (FacebookNetworkException fe) {
			
			Comentario com = new Comentario();
			com.setComentarOffline(true);
			com.setDataCriacao(new Date());
			com.setIdUsuario(usuarioMidiaSocial.getAppMidiaSocial().getRedeSocial()+":"+usuarioMidiaSocial.getIdMidia());
			com.setMensagem(mensagem);
			com.setNomeUsuario(usuarioMidiaSocial.getNome());
			com.setPublicacao(publicacao);
			com.salvar();
		}
	}
	
	public void publicar(String idDestino, String mensagem, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			FacebookType publishMessageResponse = facebookClient.publish(idDestino+"/feed", FacebookType.class,
					Parameter.with("message", mensagem));
			
			Post post = null;
			int tentativas = 0;
			do{
				try {
					Thread.sleep(10l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			    try{
			    	post = getPostById(publishMessageResponse.getId(), usuarioMidiaSocial);
			    }catch (Exception e) {
					e.printStackTrace();
					tentativas++;
				}
			}while( (post == null) && (tentativas < 3) );
			
			salvarPublicacao(post, usuarioMidiaSocial);
			
		} catch (FacebookNetworkException fe) {
			
			Publicacao pub = new Publicacao();
			pub.setDataCriacao(new Date());
			pub.setMensagem(mensagem);
			pub.setFotoUrl(usuarioMidiaSocial.getFotoUrl());
			pub.setIdUsuario(usuarioMidiaSocial.getIdMidia());
			pub.setNomeUsuario(usuarioMidiaSocial.getNome());
			pub.setUsuarioAppMidiaSocial(usuarioMidiaSocial);
			pub.setPublicarOffline(true);
			pub.setIdDestino(idDestino);
			pub.salvar();
		}	
	}
	
	public void postarFoto(String id, String caminhoImagem, String mensagem, UsuarioAppMidiaSocial usuarioMidiaSocial) throws IOException{

		try{
			InputStream is = new FileInputStream(caminhoImagem);
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			facebookClient.publish(id + "/picture", FacebookType.class,
					BinaryAttachment.with("img" , is),
					Parameter.with("message", mensagem));
					//Parameter.with("link", caminhoImagem));
			is.close();
			
		} catch (FacebookNetworkException fe) {
			
		}
	}
	
	public void curtirComentario(Comentario comentario, UsuarioAppMidiaSocial usuarioMidiaSocial){

		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			try{
			facebookClient.publish(comentario.getIdMidia() + "/likes", Boolean.class);
			}catch (NullPointerException e) {
				
			}
			comentario.setCurtir(true);
			comentario.alterar();
			
		} catch (FacebookNetworkException fe) {
			comentario.setCurtirOffline(true);
			comentario.setCurtir(true);
			comentario.alterar();
		}
	}
	
	public void curtirPublicacao(Publicacao pub, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			facebookClient.publish(pub.getIdMidia() + "/likes", Boolean.class);
			pub.setCurtir(true);
			pub.alterar();
			
		} catch (FacebookNetworkException fe) {
			pub.setCurtir(true);
			pub.setCurtirOffline(true);
			pub.alterar();
		}
	}
	
	public void deletarPublicacao(Publicacao pub, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			facebookClient.deleteObject(pub.getIdMidia());
			
			pub.setDeletado(true);
			pub.setDataDeletado(new Date());
			pub.alterar();
			
			List<Comentario> listaComentario = Comentario.listComentarioPublicacao(pub);
			
			for(Iterator<Comentario> i = listaComentario.iterator(); i.hasNext();){
				Comentario comentario = i.next();
				comentario.setDeletado(true);
				comentario.setDataDeletado(new Date());
				comentario.alterar();
			}
			
		} catch (FacebookNetworkException fe) {
			pub.setDeletado(true);
			pub.setDeletarOffline(true);
			pub.alterar();
		}
	}
	
	public void deletarComentario(Comentario com, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			facebookClient.deleteObject(com.getIdMidia());
			com.setDeletado(true);
			com.setDataDeletado(new Date());
			com.alterar();
			
		} catch (FacebookNetworkException fe) {
			com.setDeletado(true);
			com.setDeletarOffline(true);
			com.alterar();
		}
	}
	
	public void curtirRemoverPublicacao(Publicacao pub, UsuarioAppMidiaSocial usuarioMidiaSocial){

		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			facebookClient.deleteObject(pub.getIdMidia() + "/likes");
			
			pub.setCurtir(false);
			pub.alterar();
			
		} catch (FacebookNetworkException fe) {
		
			pub.setCurtirRemoverOffline(true);
			pub.alterar();
		}
	}
	
	public void curtirRemoverComentario(Comentario com, UsuarioAppMidiaSocial usuarioMidiaSocial){

		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			facebookClient.deleteObject(com.getIdMidia() + "/likes");
			
			com.setCurtir(false);
			com.alterar();
			
		} catch (FacebookNetworkException fe) {
			
			com.setCurtirRemoverOffline(true);
			com.alterar();
		}
	}
	
	public static User getUserById(String id, UsuarioAppMidiaSocial usuarioMidiaSocial){

			FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
			User user = facebookClient.fetchObject( id, User.class );
			return user;
	}
	
	public static Post getPostById(String id, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
		Post post = facebookClient.fetchObject( id, Post.class );
		return post;
	}
	
	public static Comment getCommentById(String id, UsuarioAppMidiaSocial usuarioMidiaSocial){
		FacebookClient facebookClient = new DefaultFacebookClient(usuarioMidiaSocial.getTokenAccess());
		Comment comment = facebookClient.fetchObject( id, Comment.class );
		return comment;
	}
	
	public ArrayList<ResultadoBusca> pesquisar(String palavra, ArrayList<ResultadoBusca> listaResultadoFacebook){
		
			FacebookClient facebookClient = new DefaultFacebookClient();		
			Connection<Post> publicSearch =	facebookClient.fetchConnection("search", Post.class,
					Parameter.with("q", palavra), Parameter.with("type", "post"));
			
			for (List<Post> posts : publicSearch){
	 			
				for (Post post : posts){
					  ResultadoBusca resultado = new ResultadoBusca();
						
	 				 if (post.getMessage() != null) {
		 				resultado.setIdMidia(post.getId());
						resultado.setMensagem(post.getMessage());
						resultado.setFotoUrl("https://graph.facebook.com/"+post.getFrom().getId()+"/picture");
						resultado.setIdUsuario(post.getFrom().getId());
						resultado.setNomeUsuario(post.getFrom().getName());
						Date dataPost = null;  
						String datePost = post.getCreatedTime().toLocaleString();
						DateFormat dfPost = new SimpleDateFormat("DD/MM/yyyy HH:mm:ss");
						try {
							dataPost = dfPost.parse(datePost);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						resultado.setDataCriacaoMidia(dataPost);
						resultado.setRedeSocial("Facebook");
					    listaResultadoFacebook.add(resultado);
					 }
				}
			}
			return listaResultadoFacebook;
	}
	
	public Publicacao salvarPublicacao(Post post, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		Publicacao pub = new Publicacao();
		pub.setMensagem(post.getMessage());
		pub.setIdMidia(usuarioMidiaSocial.getAppMidiaSocial().getRedeSocial()+":"+post.getId());
		pub.setFotoUrl("https://graph.facebook.com/"+post.getFrom().getId()+"/picture");
		pub.setIdUsuario(post.getFrom().getId());
		pub.setNomeUsuario(post.getFrom().getName());
		pub.setUsuarioAppMidiaSocial(usuarioMidiaSocial);
		pub.setAnexoUrl(post.getPicture());
			
		Date dataPost = null;  
		String datePost = post.getCreatedTime().toLocaleString();
		DateFormat dfPost = new SimpleDateFormat("DD/MM/yyyy HH:mm:ss");
		try {
			dataPost = dfPost.parse(datePost);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		pub.setDataCriacaoMidia(dataPost);
		
		UsuarioPubMidiaSocial usuarioMidiaSocialPub = UsuarioPubMidiaSocial.pesquisaUsuarioIdMidia(post.getFrom().getId()); 
		
		if(usuarioMidiaSocialPub != null){
			pub.setUsuarioPubMidiaSocial(usuarioMidiaSocialPub);
		}
		else{
			pub.setUsuarioPubMidiaSocial(salvarUsuarioPub(pub, usuarioMidiaSocial));
		}
		
		pub.salvar();		
		return pub;
	}
	
	public UsuarioPubMidiaSocial salvarUsuarioPub(Publicacao pub, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		User user = getUserById(pub.getIdUsuario(), usuarioMidiaSocial);
		UsuarioPubMidiaSocial usuarioMidiaSocialPub = new UsuarioPubMidiaSocial();
		
		usuarioMidiaSocialPub.setIdMidia(user.getId());
		usuarioMidiaSocialPub.setNome(user.getName());
		usuarioMidiaSocialPub.setScreenName(user.getUsername());
		usuarioMidiaSocialPub.setFotoUrl("https://graph.facebook.com/"+user.getId()+"/picture");
		usuarioMidiaSocialPub.setFotoPerfilUrl("https://graph.facebook.com/"+user.getId()+"/picture?width=148&height=148");
		usuarioMidiaSocialPub.setNomeRedeSocial("Facebook");
		usuarioMidiaSocialPub.setUrlPerfil("http://www.facebook.com/"+user.getUsername());
		usuarioMidiaSocialPub.salvar();
		
		return usuarioMidiaSocialPub;
	}
	
	public Comentario salvarComentario(Comment comment, Publicacao pub, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		Comentario c = new Comentario();	

		c.setIdMidia(usuarioMidiaSocial.getAppMidiaSocial().getRedeSocial()+":"+comment.getId());
		c.setIdUsuario(usuarioMidiaSocial.getAppMidiaSocial().getRedeSocial()+":"+comment.getFrom().getId());
		c.setMensagem(comment.getMessage());
		c.setNomeUsuario(comment.getFrom().getName());
		c.setComentarOffline(false);
		c.setCurtirOffline(false);
		c.setCurtirRemoverOffline(false);
		c.setPublicacao(pub);
			
		Date dataComment = null;  
		String dateComment = comment.getCreatedTime().toLocaleString();
		DateFormat dfComment = new SimpleDateFormat("DD/MM/yyyy HH:mm:ss");
		try {
			dataComment = dfComment.parse(dateComment);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		c.setDataCriacaoMidia(dataComment);
			
         try {
				if (isUserLikes(usuarioMidiaSocial, c.getIdMidia())) {
					c.setCurtir(true);
				}
				else {
					c.setCurtir(false);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			c.salvar();
			return c;
	}
	
	public ArrayList<Publicacao> postToPublish(Connection<Post> posts, UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		ArrayList<Publicacao> pubs = new ArrayList<Publicacao>();
		
		for (List<Post> myFeedConnectionPage : posts){
 			
			for (Post post : myFeedConnectionPage){
 				 if (post.getMessage() != null) {
 					
 					Publicacao pub = salvarPublicacao(post, usuarioMidiaSocial);
 					
 					List<Comment> listComment = post.getComments().getData();
     				for (Iterator<Comment> i = listComment.iterator();i.hasNext();) {
     					pub = Publicacao.pesquisaPostIdMidia(usuarioMidiaSocial.getAppMidiaSocial().getRedeSocial()+":"+post.getId());
     					salvarComentario(i.next(), pub, usuarioMidiaSocial);
					 }
 				 }
 			 }
 		}
		return pubs;
	}
}