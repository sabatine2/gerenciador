package com.midiasocial.service;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.midiasocial.model.Comentario;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.ResultadoBusca;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.midiasocial.model.UsuarioPubMidiaSocial;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterService{

	private String consumerKey =" ";
	private String consumerSecret=" ";
	private String accessT = " ";
	private String accessTokenSecret= " ";
	private UsuarioAppMidiaSocial usuarioMidiaSocial;
	
	private short currentPage = 1;
	private boolean querying = false;
	private boolean queryIsQueued = false;
	
	public TwitterService(String consumerKey, String consumerSecret, String accessT, String accessTokenSecret){
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessT = accessT;
		this.accessTokenSecret = accessTokenSecret;
	}
	
	public TwitterService(UsuarioAppMidiaSocial usuarioMidiaSocial){
		
		this.consumerKey = usuarioMidiaSocial.getAppMidiaSocial().getApiKey();
		this.consumerSecret = usuarioMidiaSocial.getAppMidiaSocial().getApiSecret();
		this.accessT = usuarioMidiaSocial.getTokenAccess();
		this.accessTokenSecret = usuarioMidiaSocial.getTokenAccessSecret();
	
		this.usuarioMidiaSocial = usuarioMidiaSocial;
	}

	public TwitterService(){
	}
	
	public void getAccessToken(String consumerKey, String consumerSecret ) {
		 	AccessToken accessToken = null;
	       try {
	           Twitter twitter = new TwitterFactory().getInstance();
	           RequestToken requestToken = twitter.getOAuthRequestToken();
	            
	           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	           while (null == accessToken) {
	               try {
	                   Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
	               } catch (IOException ignore) {
	               } catch (URISyntaxException e) {
	                   throw new AssertionError(e);
	               }
	               System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
	               String pin = br.readLine();
	               try {
	                   if (pin.length() > 0) {
	                       accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	                   } else {
	                       accessToken = twitter.getOAuthAccessToken(requestToken);
	                   }
	               } catch (TwitterException te) {
	                   if (401 == te.getStatusCode()) {
	                       System.out.println("Unable to get the access token.");
	                   } else {
	                       te.printStackTrace();
	                   }
	               }
	           }
	       }catch (Exception e) {
					// TODO: handle exception
			}
	 }
	
	public List<Status> getUserTimeLine(){
		
		try {
		     AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			 TwitterFactory factory = new TwitterFactory();
			 Twitter twitter = factory.getInstance();
			 twitter.setOAuthConsumer(consumerKey,consumerSecret);
			 twitter.setOAuthAccessToken(accessToken);
		  	 return twitter.getUserTimeline();
		} catch (TwitterException te) {
		   te.printStackTrace();
		   return null;
		}
		
	}
	
	public List<Status> getHomeTimeline(){
	
		try {
		     
			 AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			 TwitterFactory factory = new TwitterFactory();
			 Twitter twitter = factory.getInstance();
			 twitter.setOAuthConsumer(consumerKey,consumerSecret);
			 twitter.setOAuthAccessToken(accessToken);
		     return twitter.getHomeTimeline();
	   } catch (TwitterException te) {
	       return null;
	   }
	}
	
	public List<Status> getInteracao(){
		
		try {
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
		    return twitter.getMentionsTimeline();
		} catch (TwitterException te) {
	       return null;
	   }
	}
	
	public void pesquisarTweets(String termoBusca){
	
		try {
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
		   
		   Query query = new Query(termoBusca);
	       QueryResult result;
	       do {
	           result = twitter.search(query);
	           List<Status> tweets = result.getTweets();
	           for (Status tweet : tweets) {
	        	   	   System.out.println("Resultado " + tweet.getId() + "@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	    	   }
	       } while ((query = result.nextQuery()) != null);
	   } catch (TwitterException te) {
	   }
	}
	
	public ArrayList<ResultadoBusca> pesquisarTweetsSemAPP(String termoBusca, ArrayList<ResultadoBusca> listaResultadoTwitter) {
		
		StringBuilder urlBuilder = new StringBuilder("http://search.twitter.com/search.json?");
		
		if (!querying) {
		    currentPage = 1;
		    listaResultadoTwitter.clear();
		} else {
		    queryIsQueued = true;
		}
		
		try {
		    urlBuilder.append("q=" + URLEncoder.encode(termoBusca, "UTF-8"));
		} catch (UnsupportedEncodingException uee) {
		    return listaResultadoTwitter;
		}

		urlBuilder.append("&rpp=" + 100);
		
		urlBuilder.append("&include_entities=" + false);
		urlBuilder.append("&result_type="
			+ "MIXED");
		urlBuilder.append("&page=" + currentPage);

		Reader resultReader;
		try {
			URL url = new URL(urlBuilder.toString());
			URLConnection connection = url.openConnection();
			resultReader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		  if (resultReader == null) {
			return listaResultadoTwitter;
		    }
		} catch (IOException e) {
		    // Thrown when page no longer can be found
		    return listaResultadoTwitter;
		}

		JsonObject json = new JsonParser().parse(resultReader).getAsJsonObject();
		JsonArray results = json.get("results").getAsJsonArray();
		for (int r = 0; r < results.size(); r++) {
		    JsonObject result = results.get(r).getAsJsonObject();
		    ResultadoBusca resultado = new ResultadoBusca();
			
			resultado.setIdMidia(result.get("id_str").getAsString());
			resultado.setMensagem(result.get("text").getAsString());
		    resultado.setNomeUsuario(result.get("from_user_name").getAsString());
		    resultado.setFotoUrl(result.get("profile_image_url_https").getAsString());
		    String data = result.get("created_at").getAsString();
		    Date date = null;
			try {
				SimpleDateFormat sf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
				sf.setLenient(true);
				date = sf.parse(data);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    resultado.setDataCriacaoMidia(date);
		    resultado.setDataCriacao(new Date());
		    resultado.setRedeSocial("Twitter");
		    listaResultadoTwitter.add(resultado);
		 }
		
		return listaResultadoTwitter;
	}
	
	public void mostrarUsuario(String nomeUsuario){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
		    User user = twitter.showUser(nomeUsuario);
	       
		    if (user.getStatus() != null) {
	               System.out.println("@" + user.getScreenName() + " - " + user.getStatus().getText() +" - "+user.getFollowersCount() +" - "+user.getFriendsCount()+" - "+user.getId()+" - "+user.getName());
	           } else {
	               // the user is protected
	               System.out.println("@" + user.getScreenName());
	           }
	       
		} catch (TwitterException te) {
	           te.printStackTrace();
	           System.out.println("Failed to delete status: " + te.getMessage());
	       }
	   }
	
	public void publicar(String mensagem){
	
		try {
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
			Status status = twitter.updateStatus(mensagem);
		
			salvarPublicacao(status);
	
          } catch (TwitterException te) {
        	  Publicacao pub = new Publicacao();
	  			pub.setDataCriacao(new Date());
	  			pub.setMensagem(mensagem);
	  			pub.setFotoUrl(usuarioMidiaSocial.getFotoUrl());
	  			pub.setIdUsuario(usuarioMidiaSocial.getIdMidia());
	  			pub.setNomeUsuario(usuarioMidiaSocial.getNome());
	  			pub.setUsuarioMediaId(usuarioMidiaSocial.getIdInterno());
	  			pub.setUsuarioAppMidiaSocial(usuarioMidiaSocial);
	  			pub.setPublicarOffline(true);
	  			pub.setIdDestino("");
	  			pub.salvar();
		  }
	}
	
	public void comentar(Long id, String texto){
		
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
			Status status = twitter.updateStatus(new StatusUpdate(texto).inReplyToStatusId(id)); 
	        salvarComentario(status);
			
		} catch (TwitterException te) {
			
			Publicacao pub = Publicacao.pesquisaPostIdMidia(String.valueOf(id));
			
			Comentario com = new Comentario();
			com.setComentarOffline(true);
			com.setDataCriacao(new Date());
			com.setIdUsuario(usuarioMidiaSocial.getIdMidia());
			com.setMensagem(texto);
			com.setNomeUsuario(usuarioMidiaSocial.getNome());
			com.setPublicacao(pub);
			com.salvar();
		  }
	}
	
	public Status mostrarStatusPublicacao(Long id){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	        return twitter.showStatus(id);
	     } catch (TwitterException te) {
	           te.printStackTrace();
	           return null;
	     }
	}
	
	public void curtirPublicacao(Long id){
	
		Publicacao pub = Publicacao.pesquisaPostIdMidia(String.valueOf(id));
		
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
		    twitter.setOAuthAccessToken(accessToken);
	        twitter.retweetStatus(id);
	        pub.setCurtir(true);
			pub.alterar();
	      
	   } catch (TwitterException te) {
		   pub.setCurtirOffline(true);
		   pub.alterar();
	   }
	}  
	
	public void curtirComentario(Long id){
		
		Comentario com = Comentario.pesquisaComentarioIdMidia(String.valueOf(id));
		
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
		    twitter.setOAuthAccessToken(accessToken);
	        twitter.retweetStatus(id);
	    	com.setCurtir(true);
			com.alterar();
	      
	   } catch (TwitterException te) {
		   com.setCurtirOffline(true);
		   com.alterar();
	   }
	}  
	
	public void curtirRemoverPublicacao(Long id){
		
		Publicacao pub = Publicacao.pesquisaPostIdMidia(String.valueOf(id));
		
		
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
		   twitter.setOAuthConsumer(consumerKey,consumerSecret);
		   twitter.setOAuthAccessToken(accessToken);
	       twitter.destroyStatus(id);
            pub.setCurtir(false);
			pub.alterar();
	      
	   } catch (TwitterException te) {
		   pub.setCurtirRemoverOffline(true);
		   pub.alterar();
	   }
	}  
	
	public void curtirRemoverComentario(Long id){
		
		Comentario com = Comentario.pesquisaComentarioIdMidia(String.valueOf(id));
		
		try {
			     
		   AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
		   TwitterFactory factory = new TwitterFactory();
		   Twitter twitter = factory.getInstance();
		   twitter.setOAuthConsumer(consumerKey,consumerSecret);
		   twitter.setOAuthAccessToken(accessToken);
	       twitter.destroyStatus(id);
	       com.setCurtir(false);
		   com.alterar();
	      
	   } catch (TwitterException te) {
		   com.setCurtirRemoverOffline(true);
		   com.alterar();
	   }
	}  
	
	public void deletarPublicacao(Long id){
	
		Publicacao pub = Publicacao.pesquisaPostIdMidia(String.valueOf(id));
		
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	        twitter.destroyStatus(id);
	        pub.setDeletado(true);
			pub.setDataDeletado(new Date());
			pub.alterar();
			
	    } catch (TwitterException te) {
	    	pub.setDeletarOffline(true);
			pub.alterar();
	   }
	}
	
	public void deletarComentario(Long id){
		
		Comentario com = Comentario.pesquisaComentarioIdMidia(String.valueOf(id));
		
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	        twitter.destroyStatus(id);
	        com.setDeletado(true);
			com.setDataDeletado(new Date());
			com.alterar();
			
	    } catch (TwitterException te) {
	    	com.setDeletarOffline(true);
			com.alterar();
	   }
	}
	
	public void favorito(Long id){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	       twitter.createFavorite(id);
	   } catch (TwitterException te) {
	       te.printStackTrace();
	       System.out.println("Failed to favorite status: " + te.getMessage());
	   }
	}   
	
	public void desfazerFavorito(Long id){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	       twitter.destroyFavorite(id);
	    } catch (TwitterException te) {
	       te.printStackTrace();
	       System.out.println("Failed to unfavorite status: " + te.getMessage());
	   }
	}
	
	public void listaFavoritos(){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	       List<Status> statuses = twitter.getFavorites();
	       for (Status status : statuses) {
	           System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	       }
	   } catch (TwitterException te) {
	       te.printStackTrace();
	       System.out.println("Failed to get favorites: " + te.getMessage());
	   }
	}
	
	
	public void atualizaImagemBackgroundPerfil(String caminhoImagem){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	       twitter.updateProfileBackgroundImage(new File(caminhoImagem), true);
	   } catch (TwitterException te) {
	       te.printStackTrace();
	       System.out.println("Failed to update profile background image: " + te.getMessage());
	   }
	}
	
	public void atualizaImagemPerfil(String consumerKey, String consumerSecret, String accessT, String accessTokenSecret, String caminhoImagem){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	       twitter.updateProfileImage(new File(caminhoImagem));
	   } catch (TwitterException te) {
	       te.printStackTrace();
	       System.out.println("Failed to update profile image: " + te.getMessage());
	   }
	}   
	
	public void bloquearUsuario(String consumerKey, String consumerSecret, String accessT, String accessTokenSecret, String nomeUsuario){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	       twitter.createBlock(nomeUsuario);
	   } catch (TwitterException te) {
	       te.printStackTrace();
	       System.out.println("Failed to block user: " + te.getMessage());
	       System.exit(-1);
	   }
	}
	
	public void desbloquearUsuario(String consumerKey, String consumerSecret, String accessT, String accessTokenSecret, String nomeUsuario){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	       twitter.destroyBlock(nomeUsuario);
	   } catch (TwitterException te) {
	       te.printStackTrace();
	       System.out.println("Failed to unblock user: " + te.getMessage());
	   }
	}
	
	public void listaUsuarioBloqueados(String consumerKey, String consumerSecret, String accessT, String accessTokenSecret){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	       int page = 1;
	       List<User> users;
	       do {
	           users = twitter.getBlocksList(page);
	           for (User user : users) {
	               System.out.println("@" + user.getScreenName());
	           }
	           page++;
	        } while (users.size() > 0 && page <= 10);
	   } catch (TwitterException te) {
	       te.printStackTrace();
	       System.out.println("Failed to get blocking users: " + te.getMessage());
	   }
	}
	
	public void denunciarSpam(String consumerKey, String consumerSecret, String accessT, String accessTokenSecret, String nomeUsuario){
	
		try {
			     
			AccessToken accessToken = new AccessToken(accessT,accessTokenSecret);
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer(consumerKey,consumerSecret);
			twitter.setOAuthAccessToken(accessToken);
	       twitter.reportSpam(nomeUsuario);
	   } catch (TwitterException te) {
	       te.printStackTrace();
	       System.out.println("Failed to report spam: " + te.getMessage());
	       System.exit(-1);
	   }
	 }
	
	public ArrayList<Publicacao> postToPublish( List<Status> statuses){
		
		ArrayList<Publicacao> pubs = new ArrayList<Publicacao>();
	  
		for (Status status : statuses) {
	       
	      	   if(status.getInReplyToStatusId() == -1l){
		    	  pubs.add(salvarPublicacao(status));
 			   }
	      	   else{
	      		 salvarComentario(status);  
	      	   }
			}
		   return pubs;
		 }
	
	 public Publicacao salvarPublicacao(Status status){
		  
		 Publicacao p = new Publicacao();
   	      p.setIdMidia(String.valueOf(status.getId()));
   	      p.setMensagem(status.getText());
   	      p.setCurtir(status.isRetweetedByMe());
   	      p.setFotoUrl(status.getUser().getMiniProfileImageURL());
   	      p.setIdUsuario(String.valueOf(status.getUser().getId()));	
   	      p.setNomeUsuario(status.getUser().getName());
		  p.setUsuarioMediaId(usuarioMidiaSocial.getIdInterno());
		  p.setUsuarioAppMidiaSocial(usuarioMidiaSocial);
			
			Date dataPost = null;  
			String datePost = status.getCreatedAt().toLocaleString();
			DateFormat dfPost = new SimpleDateFormat("DD/MM/yyyy HH:mm:ss");
			try {
				dataPost = dfPost.parse(datePost);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			p.setDataCriacaoMidia(dataPost);
			
			User user = status.getUser();
			UsuarioPubMidiaSocial usuarioPub = new UsuarioPubMidiaSocial();
			usuarioPub.setIdMidia(String.valueOf(user.getId()));
			usuarioPub.setNome(user.getName());
			usuarioPub.setScreenName(user.getScreenName());
			usuarioPub.setFotoUrl(user.getOriginalProfileImageURL());
			usuarioPub.setFotoPerfilUrl(user.getOriginalProfileImageURL());
			usuarioPub.setUrlPerfil(user.getURL());
			usuarioPub.setNomeRedeSocial(usuarioMidiaSocial.getAppMidiaSocial().getRedeSocial());
			usuarioPub.salvar();
			p.setUsuarioPubMidiaSocial(usuarioPub);
			p.salvar();
			
			return p;
	}
	  
	public Comentario salvarComentario(Status status){
	    
		Comentario c = new Comentario();
	 	c.setIdMidia(String.valueOf(status.getId()));
		c.setIdUsuario(String.valueOf(status.getUser().getId()));
		c.setMensagem(status.getText());
		c.setNomeUsuario(status.getUser().getName());
		
		System.out.println(status.getText());
		
		Publicacao pub = Publicacao.pesquisaPostIdMidia(String.valueOf(status.getInReplyToStatusId()));
		
		if(pub == null){
			try{
			salvarPublicacao(mostrarStatusPublicacao(status.getInReplyToStatusId()));
			pub = Publicacao.pesquisaPostIdMidia(String.valueOf(status.getInReplyToStatusId()));
			}catch (Exception e) {
				return null;
			}
		}
		c.setPublicacao(pub);
		c.salvar();
		
		return c;
		
	}
	 
 }
