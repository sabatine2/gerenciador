package com.midiasocial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.exception.DAOException;
import com.midiasocial.dao.PalavraChaveMidiaDAO;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="MidiaPalavraChave")
public class PalavraChaveMidia {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id = null;
	
	@Column(name="palavrachave", unique = true)
	private String palavraChave = "";
	
	@ManyToOne
	@JoinColumn( name = "criterio_id")
	private Criterio criterio;
	
	public PalavraChaveMidia(){}
	
	//GETTER AND SETTERS
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPalavraChave() {
		return palavraChave;
	}
	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public Criterio getCriterio() {
		return criterio;
	}

	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}
	
	//METODOS

	public static PalavraChaveMidia pesquisaTipoID(Long id){
		
		PalavraChaveMidia palavraChave = null;
		
		try{
		  	PalavraChaveMidiaDAO pChaveDAO = new PalavraChaveMidiaDAO(PalavraChaveMidia.class);
	    	palavraChave = pChaveDAO.buscaPalavraChaves(id);
	    	return palavraChave;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		try{    		
		 	PalavraChaveMidiaDAO pChaveDAO = new PalavraChaveMidiaDAO(PalavraChaveMidia.class);
	    	pChaveDAO.save(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean remover(){
		try{
			PalavraChaveMidiaDAO pChaveDAO = new PalavraChaveMidiaDAO(PalavraChaveMidia.class);
	        pChaveDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		try{
			PalavraChaveMidiaDAO pChaveDAO = new PalavraChaveMidiaDAO(PalavraChaveMidia.class);
			pChaveDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}
}
