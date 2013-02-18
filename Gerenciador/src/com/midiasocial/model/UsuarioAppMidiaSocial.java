package com.midiasocial.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.exception.DAOException;
import com.midiasocial.dao.UsuarioAppMidiaSocialDAO;
import com.midiasocial.service.MidiaSocialService;

@Entity
@Table(name = "MidiaUsuarioApp")
public class UsuarioAppMidiaSocial {

	@Id
	@GeneratedValue
	@Column(name = "idinterno")
	private Long idInterno;
	
	@Column(name = "id")
	private String idMidia;
	
	@Column(name = "nome")
	private String nome = "";
	
	@Column(name = "screenName")
	private String screenName = "";
	
	@Column(name = "fotourl")
	private String fotoUrl;
	
	@Column(name = "status")
	private String status;
		
	@Column(name = "tokenaccess")
	private String tokenAccess = "";
	
	@Column(name = "tokenaccesssecret")
	private String tokenAccessSecret = "";
	
	@Column(name = "consumerkey")
	private String consumerKey = "";
	
	@Column(name = "consumersecret")
	private String consumerSecret = "";
	
	
@Column(name = "fanpageid")
	private String fanpageId;

	@Column(name = "fanpagescreenname")
	private String fanpageScreenName = "";
	
	@ManyToOne
	@JoinColumn( name = "idapp")
    private AplicacaoMidiaSocial appMidiaSocial;
	
	@OneToMany(mappedBy = "usuarioAppMidiaSocial", targetEntity = Publicacao.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    private Collection<Publicacao> publicacao = new ArrayList<Publicacao>();
	
	public UsuarioAppMidiaSocial(){}

	public Long getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(Long id) {
		this.idInterno = id;
	}

	public String getIdMidia() {
		return idMidia;
	}

	public void setIdMidia(String id) {
		this.idMidia = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public String getTokenAccess() {
		return tokenAccess;
	}

	public void setTokenAccess(String tokenAccess) {
		this.tokenAccess = tokenAccess;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	public String getFanpageId() {
		return fanpageId;
	}

	public void setFanpageId(String fanpageId) {
		this.fanpageId = fanpageId;
	}

	public String getFanpageScreenName() {
		return fanpageScreenName;
	}

	public void setFanpageScreenName(String fanpageScreenName) {
		this.fanpageScreenName = fanpageScreenName;

	}

	public String getTokenAccessSecret() {
		return tokenAccessSecret;
	}

	public void setTokenAccessSecret(String tokenAccessSecret) {
		this.tokenAccessSecret = tokenAccessSecret;
	}

	public AplicacaoMidiaSocial getAppMidiaSocial() {
		return appMidiaSocial;
	}

	public void setAppMidiaSocial(AplicacaoMidiaSocial appMidiaSocial) {
		this.appMidiaSocial = appMidiaSocial;
	}
	
		/**
	 * @return the publicacao
	 */
	public Collection<Publicacao> getPublicacao() {
		return publicacao;
	}

	/**
	 * @param publicacao the publicacao to set
	 */
	public void setPublicacao(Collection<Publicacao> publicacao) {
		this.publicacao = publicacao;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaUsuario(){
		UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO(UsuarioAppMidiaSocial.class);
	    List workouts = usuarioDAO.list();
		return workouts;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaUsuarioAtivo(){
		UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO( UsuarioAppMidiaSocial.class);
	    List workouts = usuarioDAO.listaAtivo();
		return workouts;
	}
	
	public UsuarioAppMidiaSocial pesquisaUsuarioID(){
		
		UsuarioAppMidiaSocial usuario = null;
		
		try{
		  	UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO( UsuarioAppMidiaSocial.class);
	        usuario = usuarioDAO.buscaUsuarios(idInterno);
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static UsuarioAppMidiaSocial pesquisaUsuarioID(Long id){
		
		UsuarioAppMidiaSocial usuario = null;
		
		try{
		  	UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO( UsuarioAppMidiaSocial.class);
	    	usuario = usuarioDAO.load(id);
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		try{
		    UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO(UsuarioAppMidiaSocial.class);
	        usuarioDAO.save(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean remover(){
		try{
		  	UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO( UsuarioAppMidiaSocial.class);
	        usuarioDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		try{
			UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO( UsuarioAppMidiaSocial.class);
			usuarioDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}
	
	public void importarDados(){
		MidiaSocialService midia = new MidiaSocialService();
		midia.servico(this);
	}
}
