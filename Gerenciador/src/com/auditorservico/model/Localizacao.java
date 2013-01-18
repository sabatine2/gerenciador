package com.auditorservico.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import com.dispositivo.model.Dispositivo;
import com.funcionario.model.Funcionario;
import com.sun.xml.internal.bind.CycleRecoverable;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="localizacao")
public class Localizacao implements Serializable , CycleRecoverable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long        id = null;
	@Column(name = "datacriacao")
	private Date        dataCriacao = null;
	@Column(name = "dataencerramento")
	private Date        dataEncerramento = null;
	@Column(name = "latitude")
	private Double      latitude;
	@Column(name = "longitude")
	private Double      longitude;
	
	@OneToOne
	@JoinColumn(name = "dispositivoid")
	private Dispositivo dispositivo;
	
	@OneToOne
	@JoinColumn(name = "funcionarioid")
	private Funcionario funcionario;
	
	private Atividade   atividade;
	
	private Status      status;
	
	
	public Localizacao() {
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		Localizacao other = (Localizacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Posicao [id=" + id + ", dataCriacao=" + dataCriacao
				+ ", dataEncerramento=" + dataEncerramento + ", latitude="
				+ latitude + ", longitude=" + longitude + ", dispositivo="
				+ dispositivo + ", funcionario=" + funcionario + ", atividade="
				+ atividade + ", status=" + status + "]";
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		Localizacao localizacao = new Localizacao();
		return localizacao;
	}
	
	
	
	
}
