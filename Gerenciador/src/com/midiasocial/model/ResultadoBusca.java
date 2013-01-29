package com.midiasocial.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.midiasocial.dao.ResultadoBuscaDAO;
import com.principal.helper.HibernateUtil;

@Entity
@Table(name = "resultadoBusca")
public class ResultadoBusca {

	@Id
	@Column(name = "resultado_idMidia", unique = true)
	private String idMidia;

	@Column(name = "resultado_datacriacao")
	private Date dataCriacao;

	@Column(name = "resultado_datacriacaomidia")
	private Date dataCriacaoMidia;
	
	@Column(name = "resultado_mensagem")
	private String mensagem;
	
	@Column(name = "resultado_idusuario")
	private String idUsuario;
	
	@Column(name = "resultado_nome")
	private String nomeUsuario;
	
	@Column(name = "resultado_redeSocial")
	private String redeSocial;
	
	@Column(name = "resultado_fotourl")
	private String fotoUrl;
	
	public ResultadoBusca(){}
	
	
	//METODOS
	/**
	 * @return the idMidia
	 */
	public String getIdMidia() {
		return idMidia;
	}


	/**
	 * @param idMidia the idMidia to set
	 */
	public void setIdMidia(String idMidia) {
		this.idMidia = idMidia;
	}


	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}


	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	/**
	 * @return the dataCriacaoMidia
	 */
	public Date getDataCriacaoMidia() {
		return dataCriacaoMidia;
	}


	/**
	 * @param dataCriacaoMidia the dataCriacaoMidia to set
	 */
	public void setDataCriacaoMidia(Date dataCriacaoMidia) {
		this.dataCriacaoMidia = dataCriacaoMidia;
	}


	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}


	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}


	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}


	/**
	 * @return the nomeUsuario
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}


	/**
	 * @param nomeUsuario the nomeUsuario to set
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	/**
	 * @return the redeSocial
	 */
	public String getRedeSocial() {
		return redeSocial;
	}


	/**
	 * @param redeSocial the redeSocial to set
	 */
	public void setRedeSocial(String redeSocial) {
		this.redeSocial = redeSocial;
	}


	/**
	 * @return the fotoUrl
	 */
	public String getFotoUrl() {
		return fotoUrl;
	}


	/**
	 * @param fotoUrl the fotoUrl to set
	 */
	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	@SuppressWarnings("rawtypes")
	public static List listaPost(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ResultadoBuscaDAO postDAO = new ResultadoBuscaDAO(s, ResultadoBusca.class);
		  
		List workouts = postDAO.list();
			
		//s.close();
		return workouts;
		
	}
		
	public static ResultadoBusca pesquisaPostID(Long id){
			
		ResultadoBusca post = null;
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ResultadoBuscaDAO postDAO = new ResultadoBuscaDAO(s, ResultadoBusca.class);
	    	post = postDAO.buscaPosts(id);
		   	s.close();
		   	return post;
		}
		catch (Exception e) {
			return null;
		}	
	}
	
	public static ResultadoBusca pesquisaPostIdMidia(String idMidia){
		
		ResultadoBusca post = null;
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ResultadoBuscaDAO postDAO = new ResultadoBuscaDAO(s, ResultadoBusca.class);
	    	post = postDAO.buscaPosts(idMidia);
		   	s.close();
		   	return post;
		}
		catch (Exception e) {
			return null;
		}	
	}
		
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		ResultadoBusca post = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	ResultadoBuscaDAO postDAO = new ResultadoBuscaDAO(s, ResultadoBusca.class);
		    postDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
		
	@SuppressWarnings("unused")
	public boolean remover(){
			
		ResultadoBusca post = null;
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	ResultadoBuscaDAO postDAO = new ResultadoBuscaDAO(s, ResultadoBusca.class);
		    postDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
		
	@SuppressWarnings("unused")
	public boolean alterar(){
		
		ResultadoBusca post = null;
		
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			ResultadoBuscaDAO postDAO = new ResultadoBuscaDAO(s, ResultadoBusca.class);
			postDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
