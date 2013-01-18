package com.midiasocial.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

import com.midiasocial.dao.AplicacaoMidiaSocialDAO;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;

@Entity
@Table(name = "appmidiasocial")
public class AplicacaoMidiaSocial {

	@Id
	@GeneratedValue
	@Column(name = "app_id")
	private Long id;
	
	@Column(name = "app_nome")
	private String nome = "";
	
	@Column(name = "app_dataCricao")
	private Date dataCriacao;
	
	@Column(name = "app_apikey")
	private String apiKey;
	
	@Column(name = "app_apisecret")
	private String apiSecret;
	
	@Column(name = "app_redesocial")
	private String redeSocial = "";

	@OneToMany(mappedBy = "appMidiaSocial", targetEntity = UsuarioAppMidiaSocial.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    private Collection<UsuarioAppMidiaSocial> usuarioappmediasocial = new ArrayList<UsuarioAppMidiaSocial>();
	
	public String getRedeSocial() {
		return redeSocial;
	}

	public void setRedeSocial(String redeSocial) {
		this.redeSocial = redeSocial;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Collection<UsuarioAppMidiaSocial> getUsuariomediasocial() {
		return usuarioappmediasocial;
	}

	public void setUsuariomediasocial(
			Collection<UsuarioAppMidiaSocial> usuariomediasocial) {
		this.usuarioappmediasocial = usuariomediasocial;
	}

	@SuppressWarnings("rawtypes")
	public static BeanItemContainer listaBeans(){
		BeanItemContainer<AplicacaoMidiaSocial>beans = new BeanItemContainer<AplicacaoMidiaSocial>(AplicacaoMidiaSocial.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(s, AplicacaoMidiaSocial.class);
	  
		List workouts = appDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			AplicacaoMidiaSocial wo = (AplicacaoMidiaSocial) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaApp(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(s, AplicacaoMidiaSocial.class);
	  
		List workouts = appDAO.list();
		
		 //s.close();
		 return workouts;
	}
	
	public AplicacaoMidiaSocial pesquisaAppID(){
		
		AplicacaoMidiaSocial app = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(s, AplicacaoMidiaSocial.class);
	        app = appDAO.buscaApps(id);
	    	s.close();
	    	return app;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static AplicacaoMidiaSocial pesquisaAppID(Long id){
		
		AplicacaoMidiaSocial app = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(s, AplicacaoMidiaSocial.class);
	    	app = appDAO.buscaApps(id);
	    	s.close();
	    	return app;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		AplicacaoMidiaSocial app = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(s, AplicacaoMidiaSocial.class);
	        appDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		AplicacaoMidiaSocial app = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(s, AplicacaoMidiaSocial.class);
	        appDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
	
		AplicacaoMidiaSocial app = null;
	
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(s, AplicacaoMidiaSocial.class);
			appDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
