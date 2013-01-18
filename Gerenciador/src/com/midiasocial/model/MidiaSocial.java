package com.midiasocial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.contato.model.Contato;
import com.midiasocial.dao.MidiaDAO;
import com.principal.helper.HibernateUtil;

@Entity
@Table(name="mediasocial")
public class MidiaSocial {

	@Id
	@GeneratedValue
	private Long   id_media = null;
	private String tipoMedia = "";
	private String endereco = "";
	
	@ManyToOne
	@JoinColumn( name = "id_contato")
	private Contato contato;
	
	public MidiaSocial(){}
	
	//GETTERS AND SETTERS
	
	public Long getId_media() {
		return id_media;
	}
	public void setId_media(Long id_media) {
		this.id_media = id_media;
	}
	public String getTipoMedia() {
		return tipoMedia;
	}
	public void setTipoMedia(String tipoMedia) {
		this.tipoMedia = tipoMedia;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	//METODOS
	
	public MidiaSocial pesquisaMediaID(){
		
		MidiaSocial media = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.currentSession();
	    	MidiaDAO mediaDAO = new MidiaDAO(s, MidiaSocial.class);
	        media = mediaDAO.buscaMedia(id_media);
	    	return media;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		
		Boolean result = false;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.currentSession();
	    	MidiaDAO mediaDAO = new MidiaDAO(s, MidiaSocial.class);
	    	result = mediaDAO.save(this);
	    	return result;
	    }
		catch (Exception e) {
			return result;
		}	
	}
	
	public boolean remover(){
		
		Boolean result = false;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.currentSession();
	    	MidiaDAO mediaDAO = new MidiaDAO(s, MidiaSocial.class);
	    	result = mediaDAO.delete(this);
	    	return result;
	    }
		catch (Exception e) {
			return result;
		}	
	}
	
	public boolean alterar(){
		
		Boolean result = false;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.currentSession();
	    	MidiaDAO MediaDAO = new MidiaDAO(s, MidiaSocial.class);
	    	result = MediaDAO.merge(this);
	    	return result;
	    }
		catch (Exception e) {
			return result;
		}	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((tipoMedia == null) ? 0 : tipoMedia.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MidiaSocial other = (MidiaSocial) obj;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id_media != other.id_media)
			return false;
		if (tipoMedia == null) {
			if (other.tipoMedia != null)
				return false;
		} else if (!tipoMedia.equals(other.tipoMedia))
			return false;
		return true;
	}	
	
	
}
