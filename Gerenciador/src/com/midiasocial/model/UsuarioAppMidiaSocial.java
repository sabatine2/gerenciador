package com.midiasocial.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import com.vaadin.data.util.BeanItemContainer;
import com.midiasocial.dao.UsuarioAppMidiaSocialDAO;
import com.principal.helper.HibernateUtil;

@Entity
@Table(name = "usuarioappmediasocial")
public class UsuarioAppMidiaSocial {

	@Id
	@GeneratedValue
	@Column(name = "userapp_idInterno")
	private Long idInterno;
	
	@Column(name = "userapp_id")
	private String idMidia;
	
	@Column(name = "userapp_nome")
	private String nome = "";
	
	@Column(name = "userapp_screenName")
	private String screenName = "";
	
	@Column(name = "userapp_foto")
	private String fotoUrl;
	
	@Column(name = "userapp_status")
	private String status;
		
	@Column(name = "userapp_tokenaccess")
	private String tokenAccess = "";
	
	@Column(name = "userapp_tokenaccess_secret")
	private String tokenAccessSecret = "";
	
	@Column(name = "userapp_consumerkey")
	private String consumerKey = "";
	
	@Column(name = "userapp_consumersecret")
	private String consumerSecret = "";
	
	@ManyToOne
	@JoinColumn( name = "app_id")
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
	public static BeanItemContainer listaBens(){
		BeanItemContainer<UsuarioAppMidiaSocial>beans = new BeanItemContainer<UsuarioAppMidiaSocial>(UsuarioAppMidiaSocial.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO(s, UsuarioAppMidiaSocial.class);
	  
		List workouts = usuarioDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			UsuarioAppMidiaSocial wo = (UsuarioAppMidiaSocial) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaUsuario(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO(s, UsuarioAppMidiaSocial.class);
	  
		List workouts = usuarioDAO.list();
		
		 //s.close();
		 return workouts;
	}
	
	public UsuarioAppMidiaSocial pesquisaUsuarioID(){
		
		UsuarioAppMidiaSocial usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO(s, UsuarioAppMidiaSocial.class);
	        usuario = usuarioDAO.buscaUsuarios(idInterno);
	    	s.close();
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static UsuarioAppMidiaSocial pesquisaUsuarioID(Long id){
		
		UsuarioAppMidiaSocial usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO(s, UsuarioAppMidiaSocial.class);
	    	usuario = usuarioDAO.buscaUsuarios(id);
	    	s.close();
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		UsuarioAppMidiaSocial usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO(s, UsuarioAppMidiaSocial.class);
	        usuarioDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		UsuarioAppMidiaSocial usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO(s, UsuarioAppMidiaSocial.class);
	        usuarioDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
	
		UsuarioAppMidiaSocial usuario = null;
	
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			UsuarioAppMidiaSocialDAO usuarioDAO = new UsuarioAppMidiaSocialDAO(s, UsuarioAppMidiaSocial.class);
			usuarioDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
