package com.auditorservico.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.bind.CycleRecoverable;
import com.ticket.model.Ordem;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="atividade")
public class Atividade  implements Serializable , CycleRecoverable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long            id;
	@Column(name = "datacriacao")
	private Date            dataCriacao;
	@Column(name = "dataalteracao")
	private Date            dataAlteracao;
	@Column(name = "dataencerramento")
	private Date  		    dataEncerramento;
	
	private Ordem           ordem;
	
	private Collection<AtividadeEventos> atividadeEventos = new ArrayList<AtividadeEventos>();
	
	public Atividade() {
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



	public Date getDataAlteracao() {
		return dataAlteracao;
	}



	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}



	public Date getDataEncerramento() {
		return dataEncerramento;
	}



	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}



	public Ordem getOrdem() {
		return ordem;
	}



	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}



	public Collection<AtividadeEventos> getAtividadeEventos() {
		return atividadeEventos;
	}



	public void setAtividadeEventos(Collection<AtividadeEventos> atividadeEventos) {
		this.atividadeEventos = atividadeEventos;
	}
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((atividadeEventos == null) ? 0 : atividadeEventos.hashCode());
		result = prime * result
				+ ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime
				* result
				+ ((dataEncerramento == null) ? 0 : dataEncerramento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
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
		Atividade other = (Atividade) obj;
		if (atividadeEventos == null) {
			if (other.atividadeEventos != null)
				return false;
		} else if (!atividadeEventos.equals(other.atividadeEventos))
			return false;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null)
				return false;
		} else if (!dataAlteracao.equals(other.dataAlteracao))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (dataEncerramento == null) {
			if (other.dataEncerramento != null)
				return false;
		} else if (!dataEncerramento.equals(other.dataEncerramento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", dataCriacao=" + dataCriacao
				+ ", dataAlteracao=" + dataAlteracao + ", dataEncerramento="
				+ dataEncerramento + ", ordem=" + ordem + ", atividadeEventos="
				+ atividadeEventos + "]";
	}



	@Override
	public Object onCycleDetected(Context arg0) {
		Atividade atividade = new Atividade();
		return atividade;
	}
}
