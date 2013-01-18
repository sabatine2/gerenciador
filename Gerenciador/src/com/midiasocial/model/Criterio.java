package com.midiasocial.model;

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

import com.midiasocial.dao.CriterioDAO;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;

@Entity
@Table(name = "criterio")
public class Criterio {

	@Id
	@GeneratedValue
	@Column(name= "criterio_id")
	private Long id;
	
	@Column(name = "criterio_nome")
	private String nome = "";
	
	@Column(name = "criterio_status")
	private String status = "";
	
	@Column(name = "criterio_prioridade")
	private String prioridade = "";
	
	@Column(name = "criterio_observacao")
	private String observacao = "";
	
	@Column(name = "criterio_dataCriacao")
	private Date dataCriacao;
	
	@OneToMany(mappedBy = "criterio", targetEntity = PalavraChaveMidia.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	public Collection<PalavraChaveMidia> palavraChaveMidia = new ArrayList<PalavraChaveMidia>();
	
	public Criterio(){}

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

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Collection<PalavraChaveMidia> getPalavraChaveMidia() {
		return palavraChaveMidia;
	}

	public void setPalavraChaveMidia(Collection<PalavraChaveMidia> palavraChaveMidia) {
		this.palavraChaveMidia = palavraChaveMidia;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	//METODOS
	
	public String getPalavraChaveString(){
		
		String palavra = "";
		for (Iterator<PalavraChaveMidia> i = this.palavraChaveMidia.iterator(); i.hasNext();) {
			PalavraChaveMidia pChave = i.next();
			palavra += pChave.getPalavraChave() + ";";
		}
		return palavra;
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer listaBens(){
		BeanItemContainer<Criterio>beans = new BeanItemContainer<Criterio>(Criterio.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		CriterioDAO criterioDAO = new CriterioDAO(s, Criterio.class);
	  
		List workouts = criterioDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Criterio wo = (Criterio) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaUsuario(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		CriterioDAO criterioDAO = new CriterioDAO(s, Criterio.class);
	  
		List workouts = criterioDAO.list();
		
		 //s.close();
		 return workouts;
	}
	
	public Criterio pesquisaCriterioID(){
		
		Criterio usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	CriterioDAO criterioDAO = new CriterioDAO(s, Criterio.class);
	        usuario = criterioDAO.buscaCriterios(id);
	    	s.close();
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Criterio pesquisaCriterioID(Long id){
		
		Criterio usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	CriterioDAO criterioDAO = new CriterioDAO(s, Criterio.class);
	    	usuario = criterioDAO.buscaCriterios(id);
	    	s.close();
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		Criterio usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	CriterioDAO criterioDAO = new CriterioDAO(s, Criterio.class);
	        criterioDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		Criterio usuario = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	CriterioDAO criterioDAO = new CriterioDAO(s, Criterio.class);
	        criterioDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
	
		Criterio usuario = null;
	
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			CriterioDAO criterioDAO = new CriterioDAO(s, Criterio.class);
			criterioDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
