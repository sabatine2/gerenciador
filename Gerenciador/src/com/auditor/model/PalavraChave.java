package com.auditor.model;

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

import com.auditor.dao.PalavraChaveDAO;
import com.principal.helper.HibernateUtil;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="palavraChave")
public class PalavraChave {
	
	@Id
	@GeneratedValue
	@Column(name="pChave_id")
	private Long id = null;
	
	@Column(name="palavra_chave", unique = true)
	private String palavraChave = "";
	
	@ManyToOne
	@JoinColumn( name = "tipo_id")
	private TipoServico tipoServico;
	
	@ManyToOne
	@JoinColumn(name="medida_id")
	private Medida medida;
	
	public PalavraChave(){}
	
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

	public Medida getMedida() {
		return medida;
	}
	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	//METODOS

	public static PalavraChave pesquisaTipoID(Long id){
		
		PalavraChave palavraChave = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	PalavraChaveDAO pChaveDAO = new PalavraChaveDAO(s, PalavraChave.class);
	    	palavraChave = pChaveDAO.buscaPalavraChaves(id);
	    	s.close();
	    	return palavraChave;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		
		try{    		
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	PalavraChaveDAO pChaveDAO = new PalavraChaveDAO(s, PalavraChave.class);
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
		
		PalavraChave pChave = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	PalavraChaveDAO pChaveDAO = new PalavraChaveDAO(s, PalavraChave.class);
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
	
		PalavraChave pChave = null;
	
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			PalavraChaveDAO pChaveDAO = new PalavraChaveDAO(s, PalavraChave.class);
			pChaveDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
