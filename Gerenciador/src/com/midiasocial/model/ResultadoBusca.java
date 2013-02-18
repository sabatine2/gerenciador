package com.midiasocial.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.exception.DAOException;
import com.midiasocial.dao.ResultadoBuscaDAO;

/**
 * Indica quais buscas foram realizadas
 * 
 * @author 
 * 
 */

@Entity
@Table(name = "MidiaResultadoBusca")
public class ResultadoBusca {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long idInterno;
	
	@Column(name = "idmidia", unique = true)
	private String idMidia;

	@Column(name = "datacriacao")
	private Date dataCriacao;

	@Column(name = "datacriacaomidia")
	private Date dataCriacaoMidia;
	
	@Column(name = "mensagem")
	private String mensagem;
	
	@Column(name = "idusuario")
	private String idUsuario;
	
	@Column(name = "nome")
	private String nomeUsuario;
	
	@Column(name = "redesocial")
	private String redeSocial;
	
	@Column(name = "fotourl")
	private String fotoUrl;
	
	public ResultadoBusca(){}
	
	public Long getIdInterno() {
		return idInterno;
	}


	public void setIdInterno(Long idInterno) {
		this.idInterno = idInterno;
	}

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
	public static List listaResultado(){
		ResultadoBuscaDAO resultadoDAO = new ResultadoBuscaDAO(ResultadoBusca.class);
		return resultadoDAO.list();
	}
		
	public static ResultadoBusca pesquisaResultadoID(Long id){
			
		ResultadoBusca post = null;
			
		try{
		 	ResultadoBuscaDAO resultadoDAO = new ResultadoBuscaDAO(ResultadoBusca.class);
	    	post = resultadoDAO.load(id);
		   	return post;
		}
		catch (Exception e) {
			return null;
		}	
	}
		
	public boolean salvar(){
		try{
		  	ResultadoBuscaDAO resultadoDAO = new ResultadoBuscaDAO(ResultadoBusca.class);
		    resultadoDAO.save(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
		
	public boolean remover(){
		try{
		  	ResultadoBuscaDAO resultadoDAO = new ResultadoBuscaDAO(ResultadoBusca.class);
		    resultadoDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
		
	public boolean alterar(){
		try{
			ResultadoBuscaDAO resultadoDAO = new ResultadoBuscaDAO(ResultadoBusca.class);
			resultadoDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}
}
