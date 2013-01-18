package com.auditorservico.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.bind.CycleRecoverable;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="atividadeevento")
public class AtividadeEventos  implements Serializable , CycleRecoverable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long   id;
	@Column(name = "datacriacao")
	private Date   dataCriacao;
	@Column(name = "dataencerramento")
	private Date   dataEncerramento;
	@Column(name = "status", length = 100)
	private String status;
	@Lob
	@Column(name = "observacao")
	private String observacao;
	
	
	public AtividadeEventos() {
		super();
		dataCriacao = new Date();
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public Date getDataEncerramento() {
		return dataEncerramento;
	}


	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtividadeEventos other = (AtividadeEventos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AtividadeEventos [id=" + id + ", dataCriacao=" + dataCriacao
				+ ", dataEncerramento=" + dataEncerramento + ", status="
				+ status + ", observacao=" + observacao + "]";
	}
	
	@Override
	public Object onCycleDetected(Context arg0) {
		AtividadeEventos evento = new AtividadeEventos();
		return evento;
	}
	
}