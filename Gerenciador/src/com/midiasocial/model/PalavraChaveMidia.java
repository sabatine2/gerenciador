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

import com.midiasocial.dao.PalavraChaveMidiaDAO;
import com.principal.helper.HibernateUtil;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="palavraChaveMidia")
public class PalavraChaveMidia {
	
	@Id
	@GeneratedValue
	@Column(name="pChave_id")
	private Long id = null;
	
	@Column(name="palavra_chave", unique = true)
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
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	PalavraChaveMidiaDAO pChaveDAO = new PalavraChaveMidiaDAO(s, PalavraChaveMidia.class);
	    	palavraChave = pChaveDAO.buscaPalavraChaves(id);
;	    	s.close();
	    	return palavraChave;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		
		try{    		
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	PalavraChaveMidiaDAO pChaveDAO = new PalavraChaveMidiaDAO(s, PalavraChaveMidia.class);
	    	pChaveDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		PalavraChaveMidia pChave = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	PalavraChaveMidiaDAO pChaveDAO = new PalavraChaveMidiaDAO(s, PalavraChaveMidia.class);
	        pChaveDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
	
		PalavraChaveMidia pChave = null;
	
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			PalavraChaveMidiaDAO pChaveDAO = new PalavraChaveMidiaDAO(s, PalavraChaveMidia.class);
			pChaveDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
