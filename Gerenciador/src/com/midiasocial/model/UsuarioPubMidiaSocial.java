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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.midiasocial.dao.UsuarioPubMidiaSocialDAO;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;

@Entity
@Table(name = "usuariopubmediasocial")
public class UsuarioPubMidiaSocial {

	@Id
	@GeneratedValue
	@Column(name = "userpub_idInterno")
	private Long idInterno;
	
	@Column(name = "userpub_idmidia", unique = true)
	private String idMidia;
	
	@Column(name = "userapp_nome")
	private String nome;
	
	@Column(name = "userapp_screenName")
	private String screenName;
	
	@Column(name = "userapp_foto")
	private String fotoUrl;
	
	@Column(name = "userpub_fotoPerfil")
	private String fotoPerfilUrl;
	
	@Column(name = "userpub_urlPerfil")
	private String urlPerfil = " ";
	
	@Column(name = "userpub_redeSocial")
	private String nomeRedeSocial = " ";
	
	@OneToMany(mappedBy = "usuarioPubMidiaSocial", targetEntity = Publicacao.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    private Collection<Publicacao> publicacao = new ArrayList<Publicacao>();
	
	public Long getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(Long idInterno) {
		this.idInterno = idInterno;
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

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getFotoPerfilUrl() {
		return fotoPerfilUrl;
	}

	public void setFotoPerfilUrl(String fotoPerfilUrl) {
		this.fotoPerfilUrl = fotoPerfilUrl;
	}

	public Collection<Publicacao> getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Collection<Publicacao> publicacao) {
		this.publicacao = publicacao;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	
	/**
	 * @return the urlPerfil
	 */
	public String getUrlPerfil() {
		return urlPerfil;
	}

	/**
	 * @param urlPerfil the urlPerfil to set
	 */
	public void setUrlPerfil(String urlPerfil) {
		this.urlPerfil = urlPerfil;
	}

	/**
	 * @return the nomeRedeSocial
	 */
	public String getNomeRedeSocial() {
		return nomeRedeSocial;
	}

	/**
	 * @param nomeRedeSocial the nomeRedeSocial to set
	 */
	public void setNomeRedeSocial(String nomeRedeSocial) {
		this.nomeRedeSocial = nomeRedeSocial;
	}

	@SuppressWarnings("rawtypes")
	public static BeanItemContainer listaBens(){
		BeanItemContainer<UsuarioPubMidiaSocial>beans = new BeanItemContainer<UsuarioPubMidiaSocial>(UsuarioPubMidiaSocial.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(s, UsuarioPubMidiaSocial.class);
	  
		List workouts = usuarioDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			UsuarioPubMidiaSocial wo = (UsuarioPubMidiaSocial) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaUsuario(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(s, UsuarioPubMidiaSocial.class);
	  
		List workouts = usuarioDAO.list();
		
		 //s.close();
		 return workouts;
	}
	
	@SuppressWarnings("rawtypes")
	public static UsuarioPubMidiaSocial pesquisaUsuarioIdMidia(String id){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(s, UsuarioPubMidiaSocial.class);
	  
		return usuarioDAO.pesquisaUsuarioIdMidia(id);
		
	}
	
	public UsuarioPubMidiaSocial pesquisaUsuarioID(){
		
		UsuarioPubMidiaSocial usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(s, UsuarioPubMidiaSocial.class);
	        usuario = usuarioDAO.buscaUsuarios(idInterno);
	    	s.close();
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static UsuarioPubMidiaSocial pesquisaUsuarioID(Long id){
		
		UsuarioPubMidiaSocial usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(s, UsuarioPubMidiaSocial.class);
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
		
		UsuarioPubMidiaSocial usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(s, UsuarioPubMidiaSocial.class);
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
		
		UsuarioPubMidiaSocial usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(s, UsuarioPubMidiaSocial.class);
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
	
		UsuarioPubMidiaSocial usuario = null;
	
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(s, UsuarioPubMidiaSocial.class);
			usuarioDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
	
}
