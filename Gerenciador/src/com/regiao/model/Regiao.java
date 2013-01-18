package com.regiao.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.regiao.dao.RegiaoDAO;
import com.principal.helper.*;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings({"unused", "rawtypes"})
@XmlRootElement
@Entity
@Table(name = "regiao")
public class Regiao {

	@Id
	@GeneratedValue
	@Column(name = "regiao_id")
	private Long id = null;
	
	@Column(name = "regiao_nome")
	private String nome = "";
	
	@Column(name = "regiao_status")
	private String status;
	
	@Column(name = "regiao_dataCriacao")
	private Date dataCriacao = null;
	
	@Column(name = "regiao_dataUpdate")
	private Date dataUpdate = null;
	
	 @OneToMany(mappedBy = "regiao", targetEntity = AreaCobertura.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	 private Collection<AreaCobertura> areaCobertura = new ArrayList<AreaCobertura>();
	
	public Regiao(){}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<AreaCobertura> getAreaCobertura() {
		return areaCobertura;
	}

	public void setAreaCobertura(Collection<AreaCobertura> areaCobertura) {
		this.areaCobertura = areaCobertura;
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
	
	public static BeanItemContainer ListaBens(){
		BeanItemContainer<Regiao> beans = new BeanItemContainer<Regiao>(Regiao.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		RegiaoDAO regiaoDAO = new RegiaoDAO(s, Regiao.class);
	  
		List workouts = regiaoDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Regiao wo = ( Regiao ) iterator.next();
			beans.addBean(wo);
       	}	
		//s.close();
		return beans;
	}
	
	public static Regiao pesquisaRegiaoID(Long id){
		
		Regiao regiao = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	RegiaoDAO regiaoDAO = new RegiaoDAO(s, Regiao.class);
	    	regiao = regiaoDAO.buscaRegiaos(id);
	    	s.close();
	    	return regiao;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		
		Regiao regiao = null;
		setDataCriacao(new Date());
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	RegiaoDAO regiaoDAO = new RegiaoDAO(s, Regiao.class);
	        regiaoDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean remover(){
		 
		Regiao regiao = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	RegiaoDAO regiaoDAO = new RegiaoDAO(s, Regiao.class);
	        regiaoDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		
		Regiao regiao = null;
		setDataUpdate(new Date());
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	RegiaoDAO regiaoDAO = new RegiaoDAO(s, Regiao.class);
	        regiaoDAO.merge(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
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
		Regiao other = (Regiao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}