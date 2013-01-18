package com.telefone.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.contato.model.Contato;
import com.principal.helper.HibernateUtil;
import com.telefone.dao.TelefoneContatoDAO;
@XmlRootElement
@Entity
@Table (name="telefonecontato")
public class TelefoneContato {

	@Id
	@GeneratedValue
	@Column(name = "telefoneid")
	private Long id = null; 
	
	@Column(name = "telefonenumero")
	private Long dddNumero; //ddd+telefone
	
	@Column(name = "telefoneramal")
	private Long ramal; //interno ou externo
	
	@Column(name = "telefonetipo")
	private String tipo; //interno ou externo
	
	@Column(name = "telefoneFinalidade")
	private String finalidade; //interno ou externo
	
	@Temporal(TemporalType.DATE)
	@Column(name="telefoneativacao")
	private Date ativacao;

	@Column(name = "telefoneobs", length=255)
	private String obs = "";
	
	private String prestadora;
	   
	private Date dataCriacao = new Date();
	
	private Date dataUpdate = null;;
	
	private String infoTelefone ="";
	
	@ManyToOne
	@JoinColumn( name = "id_contato")
	private Contato contato;
	
	public TelefoneContato(){}
	
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getDddNumero() {
		return dddNumero;
	}

	public void setDddNumero(Long dddNumero) {
		this.dddNumero = dddNumero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public Date getAtivacao() {
		return ativacao;
	}

	public void setAtivacao(Date ativacao) {
		this.ativacao = ativacao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getPrestadora() {
		return prestadora;
	}

	public void setPrestadora(String prestadora) {
		this.prestadora = prestadora;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	
	//METODOS

	public Long getRamal() {
		return ramal;
	}


	public void setRamal(Long ramal) {
		this.ramal = ramal;
	}


	
	/**
	 * @return the infoTelefone
	 */
	public String getInfoTelefone() {
		if(ramal == null){
		return infoTelefone = "Numero=" + dddNumero	+ ", Ramal=" + ramal;
		}
		else return infoTelefone = "Numero=" + dddNumero;
		
	}



	/**
	 * @param infoTelefone the infoTelefone to set
	 */
	public void setInfoTelefone(String infoTelefone) {
		this.infoTelefone = infoTelefone;
	}



	public TelefoneContato pesquisaTelefoneID(){
		
		TelefoneContato telContato = null;
	  	org.hibernate.Session s = HibernateUtil.openSession();
	  	TelefoneContatoDAO telContatoDAO = new TelefoneContatoDAO(s, TelefoneContato.class);
	    telContato = telContatoDAO.buscaTelefoneContato(dddNumero);
	    s.close();
	    s = null;
	    telContatoDAO = null;
	    return telContato;
	    
	}
	
	public boolean salvar(){

		Boolean result = false;
		
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TelefoneContatoDAO telContatoDAO = new TelefoneContatoDAO(s, TelefoneContato.class);
	    	result = telContatoDAO.save(this);
	    	s.close();
	    	s = null;
		    telContatoDAO = null;
	    	return result;
	   
	
	}
	
	public boolean remover(){
		
		Boolean result = false;
		
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TelefoneContatoDAO telContatoDAO = new TelefoneContatoDAO(s, TelefoneContato.class);
	    	result = telContatoDAO.delete(this);
	    	s.close();
	    	s = null;
	        telContatoDAO =  null;
	    	return result;
		
	}
	
	public boolean alterar(){
		
		    Boolean resultado = false;
		    
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TelefoneContatoDAO telContatoDAO = new TelefoneContatoDAO(s, TelefoneContato.class);
	        resultado = telContatoDAO.merge(this);
	        s.close();
	        telContatoDAO = null;
	        s = null;
	        return resultado;
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
		TelefoneContato other = (TelefoneContato) obj;
		if (ativacao == null) {
			if (other.ativacao != null)
				return false;
		} else if (!ativacao.equals(other.ativacao))
			return false;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (dataUpdate == null) {
			if (other.dataUpdate != null)
				return false;
		} else if (!dataUpdate.equals(other.dataUpdate))
			return false;
		if (dddNumero == null) {
			if (other.dddNumero != null)
				return false;
		} else if (!dddNumero.equals(other.dddNumero))
			return false;
		if (finalidade == null) {
			if (other.finalidade != null)
				return false;
		} else if (!finalidade.equals(other.finalidade))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (prestadora == null) {
			if (other.prestadora != null)
				return false;
		} else if (!prestadora.equals(other.prestadora))
			return false;
		if (ramal == null) {
			if (other.ramal != null)
				return false;
		} else if (!ramal.equals(other.ramal))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TelefoneContato [id=" + id + ", dddNumero=" + dddNumero
				+ ", ramal=" + ramal + ", tipo=" + tipo + ", finalidade="
				+ finalidade + ", ativacao=" + ativacao + ", obs=" + obs
				+ ", prestadora=" + prestadora + ", dataCriacao=" + dataCriacao
				+ ", dataUpdate=" + dataUpdate + ", infoTelefone="
				+ infoTelefone + "]";
	}

	
	
}
