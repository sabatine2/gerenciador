package com.midiasocial.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.exception.DAOException;
import com.midiasocial.dao.AplicacaoMidiaSocialDAO;

/**
 * Cadastrar e gerenciar as aplicações criadas nas redes sociais
 * 
 * @author 
 * 
 */

@Entity
@Table(name = "MidiaApp")
public class AplicacaoMidiaSocial {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome = "";
	
	@Column(name = "datacriacao")
	private Date dataCriacao;
	
	@Column(name = "apikey")
	private String apiKey;
	
	@Column(name = "apisecret")
	private String apiSecret;
	
	@Column(name = "redesocial")
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
	public static List listaApp(){
		AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(AplicacaoMidiaSocial.class);
	    List workouts = appDAO.list();
		return workouts;
	}
	
	public AplicacaoMidiaSocial pesquisaAppID(){
		
		AplicacaoMidiaSocial app = null;
		
		try{
		  	AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(AplicacaoMidiaSocial.class);
	        app = appDAO.load(id);
	    	return app;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static AplicacaoMidiaSocial pesquisaAppID(Long id){
		
		AplicacaoMidiaSocial app = null;
		
		try{
		  	AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(AplicacaoMidiaSocial.class);
	    	app = appDAO.load(id);
	    	return app;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		try{
		  	AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(AplicacaoMidiaSocial.class);
	        appDAO.save(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean remover(){
		try{
		  	AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(AplicacaoMidiaSocial.class);
	        appDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			System.out.println("Erro Deletando" + e.getMessage());
			return false;
		}	
	}
	
	public boolean alterar(){
		try{
			AplicacaoMidiaSocialDAO appDAO = new AplicacaoMidiaSocialDAO(AplicacaoMidiaSocial.class);
			appDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}
}
