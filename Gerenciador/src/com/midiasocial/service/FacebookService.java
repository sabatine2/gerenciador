package com.midiasocial.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

import com.auditor.helper.Conexao;
import com.midiasocial.controller.PublicacaoController;
import com.midiasocial.model.Comentario;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.ResultadoBusca;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.model.UsuarioPubMidiaSocial;
import com.midiasocial.view.PublicacaoView;
import com.midiasocial.view.PaginaView;
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

	public static Connection<Post> getUserTimeLineRT(UsuarioAppMidiaSocial usuario){
		
		Connection<Post> myFeed = null;
		
		try{
			UsuarioAppMidiaSocial user = (UsuarioAppMidiaSocial) usuario;
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			myFeed = facebookClient.fetchConnection(usuario.getFanpageScreenName()+"/feed", Post.class);
			return myFeed;
		}catch (Exception e) {
			return myFeed;
		}	
	}
	
	public Connection<Post> getUserTimeLine(UsuarioAppMidiaSocial usuario){
		UsuarioAppMidiaSocial user = (UsuarioAppMidiaSocial) usuario;
		FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
		Connection<Post> myFeed = facebookClient.fetchConnection(usuario.getFanpageScreenName()+"/feed", Post.class);
		return myFeed;
	}
	

	public List<Post> getUserTimeLineData(UsuarioAppMidiaSocial usuario){
		
		UsuarioAppMidiaSocial user = (UsuarioAppMidiaSocial) usuario;
		FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
		String query = "SELECT post_id, actor_id, target_id, message FROM stream WHERE source_id = 423421014392436 AND created_time < "+ System.currentTimeMillis() +"LIMIT 50 ";
		List<Post> usuario2 = facebookClient.executeQuery(query, Post.class);
		for(Iterator<Post> i = usuario2.iterator(); i.hasNext(); ){
			Post p = i.next();
			System.out.println("mensagemm "+ p.getMessage());
		}
	    return usuario2;
	}
	
	
	private static boolean isUserLikes(UsuarioAppMidiaSocial usuario, String idComment) throws JSONException{
		
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			JsonObject jsonObj = facebookClient.fetchObject(idComment, JsonObject.class);
			System.out.println(jsonObj.toString());
			boolean userLikes = jsonObj.getBoolean("user_likes");
			return userLikes;
		
	}
	
	public void comentar(String id, String mensagem, UsuarioAppMidiaSocial usuario){
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			FacebookType publishCommentResponse = facebookClient.publish(id + "/comments", FacebookType.class, 
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
			    	comment = getCommentById(publishCommentResponse.getId(), usuario);
			    }catch (Exception e) {
					e.printStackTrace();
					tentativas++;
				}
			}while( (comment == null) && (tentativas < 3) );
			
			salvarComentario(comment, id, usuario);
			
		} catch (FacebookNetworkException fe) {
			
			Publicacao pub = new Publicacao().pesquisaPostIdMidia(id);
			
			Comentario com = new Comentario();
			com.setComentarOffline(true);
			com.setDataCriacao(new Date());
			com.setIdUsuario(usuario.getIdMidia());
			com.setMensagem(mensagem);
			com.setNomeUsuario(usuario.getNome());
			com.setPublicacao(pub);
			com.salvar();
		}
	}
	
	public void publicar(String id, String mensagem, UsuarioAppMidiaSocial usuario){
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			FacebookType publishMessageResponse = facebookClient.publish(id+"/feed", FacebookType.class,
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
			    	post = getPostById(publishMessageResponse.getId(), usuario);
			    }catch (Exception e) {
					e.printStackTrace();
					tentativas++;
				}
			}while( (post == null) && (tentativas < 3) );
			
			salvarPublicacao(post, usuario);
			
		} catch (FacebookNetworkException fe) {
			
			Publicacao pub = new Publicacao();
			pub.setDataCriacao(new Date());
			pub.setMensagem(mensagem);
			pub.setFotoUrl(usuario.getFotoUrl());
			pub.setIdUsuario(usuario.getIdMidia());
			pub.setNomeUsuario(usuario.getNome());
			pub.setUsuarioMediaId(usuario.getIdInterno());
			pub.setUsuarioAppMidiaSocial(usuario);
			pub.setPublicarOffline(true);
			pub.setIdDestino(id);
			pub.salvar();
		}	
	}
	
	public void postarFoto(String id, String caminhoImagem, String mensagem, UsuarioAppMidiaSocial usuario) throws IOException{

		try{
			InputStream is = new FileInputStream(caminhoImagem);
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.publish(id + "/picture", FacebookType.class,
					BinaryAttachment.with("img" , is),
					Parameter.with("message", mensagem));
					//Parameter.with("link", caminhoImagem));
			is.close();
			
		} catch (FacebookNetworkException fe) {
			
		}
	}
	
	public void curtirComentario(String id, UsuarioAppMidiaSocial usuario){

		Comentario com = Comentario.pesquisaComentarioIdMidia(id);
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.publish(id + "/likes", Boolean.class);
			
			com.setCurtir(true);
			com.alterar();
			
		} catch (FacebookNetworkException fe) {
			com.setCurtirOffline(true);
			com.alterar();
		}
	}
	
	public void curtirPublicacao(String id, UsuarioAppMidiaSocial usuario){
		
		Publicacao pub = Publicacao.pesquisaPostIdMidia(id);
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.publish(id + "/likes", Boolean.class);
			pub.setCurtir(true);
			pub.alterar();
			
		} catch (FacebookNetworkException fe) {
			
			pub.setCurtirOffline(true);
			pub.alterar();
		}
	}
	
	public void deletarPublicacao(String id, UsuarioAppMidiaSocial usuario){
		
		Publicacao pub = Publicacao.pesquisaPostIdMidia(id);
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.deleteObject(id);
			
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
			
			pub.setDeletarOffline(true);
			pub.alterar();
		}
	}
	
	public void deletarComentario(String id, UsuarioAppMidiaSocial usuario){
		
		Comentario com = Comentario.pesquisaComentarioIdMidia(id);
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.deleteObject(id);
			
			com.setDeletado(true);
			com.setDataDeletado(new Date());
			com.alterar();
			
		} catch (FacebookNetworkException fe) {
			
			com.setDeletarOffline(true);
			com.alterar();
		}
	}
	
	public void curtirRemoverPublicacao(String id, UsuarioAppMidiaSocial usuario){

		Publicacao pub = Publicacao.pesquisaPostIdMidia(id);
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.deleteObject(id + "/likes");
			
			pub.setCurtir(false);
			pub.alterar();
			
		} catch (FacebookNetworkException fe) {
		
			pub.setCurtirRemoverOffline(true);
			pub.alterar();
		}
	}
	
	public void curtirRemoverComentario(String id, UsuarioAppMidiaSocial usuario){

		Comentario com = Comentario.pesquisaComentarioIdMidia(id);
		
		try{
			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			facebookClient.deleteObject(id + "/likes");
			
			com.setCurtir(false);
			com.alterar();
			
		} catch (FacebookNetworkException fe) {
			
			com.setCurtirRemoverOffline(true);
			com.alterar();
		}
	}
	
	public static User getUserById(String id, UsuarioAppMidiaSocial usuario){

			FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
			User user = facebookClient.fetchObject( id, User.class );
			return user;
	}
	
	public static Post getPostById(String id, UsuarioAppMidiaSocial usuario){
		
		FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
		Post post = facebookClient.fetchObject( id, Post.class );
		return post;
	}
	
	public static Comment getCommentById(String id, UsuarioAppMidiaSocial usuario){
		FacebookClient facebookClient = new DefaultFacebookClient(usuario.getTokenAccess());
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
	
	public Publicacao salvarPublicacao(Post post, UsuarioAppMidiaSocial usuario){
		
		Publicacao pub = new Publicacao();
		pub.setMensagem(post.getMessage());
		pub.setIdMidia(post.getId());
		pub.setFotoUrl("https://graph.facebook.com/"+post.getFrom().getId()+"/picture");
		pub.setIdUsuario(post.getFrom().getId());
		pub.setNomeUsuario(post.getFrom().getName());
		pub.setUsuarioMediaId(usuario.getIdInterno());
		pub.setUsuarioAppMidiaSocial(usuario);
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
		
		UsuarioPubMidiaSocial usuarioPub = UsuarioPubMidiaSocial.pesquisaUsuarioIdMidia(post.getFrom().getId()); 
		
		if(usuarioPub != null){
			pub.setUsuarioPubMidiaSocial(usuarioPub);
		}
		else{
			pub.setUsuarioPubMidiaSocial(salvarUsuarioPub(pub, usuario));
		}
		
		pub.salvar();		
		return pub;
	}
	
	public UsuarioPubMidiaSocial salvarUsuarioPub(Publicacao pub, UsuarioAppMidiaSocial usuario){
		
		User user = getUserById(pub.getIdUsuario(), usuario);
		UsuarioPubMidiaSocial usuarioPub = new UsuarioPubMidiaSocial();
		
		usuarioPub.setIdMidia(user.getId());
		usuarioPub.setNome(user.getName());
		usuarioPub.setScreenName(user.getUsername());
		usuarioPub.setFotoUrl("https://graph.facebook.com/"+user.getId()+"/picture");
		usuarioPub.setFotoPerfilUrl("https://graph.facebook.com/"+user.getId()+"/picture?width=148&height=148");
		usuarioPub.setNomeRedeSocial("Facebook");
		usuarioPub.setUrlPerfil("http://www.facebook.com/"+user.getUsername());
		usuarioPub.salvar();
		
		return usuarioPub;
	}
	
	public Comentario salvarComentario(Comment comment, String idMidia, UsuarioAppMidiaSocial usuario){
		
		Comentario c = new Comentario();	

		c.setIdMidia(comment.getId());
		c.setIdUsuario(comment.getFrom().getId());
		c.setMensagem(comment.getMessage());
		c.setNomeUsuario(comment.getFrom().getName());
		c.setComentarOffline(false);
		c.setCurtirOffline(false);
		c.setCurtirRemoverOffline(false);
		Publicacao p = new Publicacao().pesquisaPostIdMidia(idMidia);
		c.setPublicacao(p);
			
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
				if (isUserLikes(usuario, c.getIdMidia())) {
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
	
	public ArrayList<Publicacao> postToPublish(Connection<Post> posts, UsuarioAppMidiaSocial usuario){
		
		ArrayList<Publicacao> pubs = new ArrayList<Publicacao>();
		
		for (List<Post> myFeedConnectionPage : posts){
 			
			for (Post post : myFeedConnectionPage){
 				 if (post.getMessage() != null) {
 					
 					Publicacao pub = salvarPublicacao(post, usuario);
 					
 					List<Comment> listComment = post.getComments().getData();
     				for (Iterator<Comment> i = listComment.iterator();i.hasNext();) {
     					
     					salvarComentario(i.next(), pub.getIdMidia(), usuario);
					 }
 				 }
 			 }
 		}
		return pubs;
	}
}