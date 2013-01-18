package com.regiao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.regiao.dao.AreaCoberturaDAO;
import com.principal.helper.*;

@SuppressWarnings("unused")
@XmlRootElement
@Entity
@Table(name = "areacobertura")
public class AreaCobertura {

	@Id
	@GeneratedValue
	@Column(name = "areacobertura_id")
	private Long id = null;
	
	@Column(name = "areacobertura_nome")
	private String nome = "";
	
	@Column(name = "areacobertura_dataCriacao")
	private Date dataCriacao = null;
	
	@Column(name = "areacobertura_dataUpdate")
	private Date dataUpdate = null;
	
	@ManyToOne
	@JoinColumn( name = "regiao_id")
    private Regiao regiao;
	
	public AreaCobertura(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	
	public boolean salvar(){
		
		AreaCobertura area = null;
		setDataCriacao(new Date());
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	AreaCoberturaDAO areaCoberturaDAO = new AreaCoberturaDAO(s, AreaCobertura.class);
	        areaCoberturaDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean remover(){
		 
		AreaCobertura area = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	AreaCoberturaDAO areaCoberturaDAO = new AreaCoberturaDAO(s, AreaCobertura.class);
	        areaCoberturaDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		
		AreaCobertura area = null;
		setDataUpdate(new Date());
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	AreaCoberturaDAO areaCoberturaDAO = new AreaCoberturaDAO(s, AreaCobertura.class);
	        areaCoberturaDAO.merge(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
}