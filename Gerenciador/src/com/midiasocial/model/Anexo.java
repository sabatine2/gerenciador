package com.midiasocial.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MidiaAnexo")
public class Anexo {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "datacriacao")
	private Date dataCriacao;
		
	@Column(name = "anexourl")
	private String anexoUrl;
	
	@ManyToOne
	@JoinColumn(name = "publicacao")
	private Publicacao publicacao;

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

	public String getAnexoUrl() {
		return anexoUrl;
	}

	public void setAnexoUrl(String anexoUrl) {
		this.anexoUrl = anexoUrl;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}

	@Override
	public String toString() {
		return "Anexo [id=" + id + ", dataCriacao=" + dataCriacao
				+ ", anexoUrl=" + anexoUrl + ", publicacao=" + publicacao + "]";
	}
	
	
}
