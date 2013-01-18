package com.auditor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.UnidadeDAO;
import com.auditor.model.contato.EnderecoAuditor;
import com.principal.helper.HibernateUtil;
import com.sun.xml.internal.bind.CycleRecoverable;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="unidade")
public class Unidade implements Serializable , CycleRecoverable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="unidadeid")
	private Long id;
		
	@Column(name="unidadenome")
	private String nome = ""; 

	@Column(name="unidadeemail")
	private String email = "";

	@ManyToOne
	@JoinColumn( name = "centroid")
    private CentroCusto centro;
	
	@OneToMany(mappedBy = "unidade", targetEntity = Departamento.class, fetch = FetchType.LAZY, cascade={ CascadeType.ALL})
    private Collection<Departamento> departamento = new ArrayList<Departamento>();
	
	@OneToOne
	private EnderecoAuditor endereco;
	
	private Date dataCriacao = new Date();
	
	private Date dataupdate = null;
	
	public Unidade (){}

	//GETTERS AND SETTERS
	
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

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public CentroCusto getCentro() {
		return centro;
	}
	public void setCentro(CentroCusto centro) {
		this.centro = centro;
	}

	public Collection<Departamento> getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Collection<Departamento> Departamento) {
		this.departamento = Departamento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataupdate() {
		return dataupdate;
	}

	public void setDataupdate(Date dataupdate) {
		this.dataupdate = dataupdate;
	}

	public EnderecoAuditor getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoAuditor endereco) {
		this.endereco = endereco;
	}
	
	//METODOS
	
	@SuppressWarnings("unused")
	public static List<Unidade> ListAll(){
		BeanItemContainer<Unidade>beans = new BeanItemContainer<Unidade>(Unidade.class);
	
		org.hibernate.Session s = HibernateUtil.openSession();
		UnidadeDAO unidadeDAO = new UnidadeDAO(s, Unidade.class);
		List<Unidade> unidade = unidadeDAO.list();
		//s.close();
		return unidade;
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBens(){
		BeanItemContainer<Unidade>beans = new BeanItemContainer<Unidade>(Unidade.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		UnidadeDAO unidadeDAO = new UnidadeDAO(s, Unidade.class);
	  
		List workouts = unidadeDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Unidade wo = (Unidade) iterator.next();
			wo.setDepartamento(null);
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	public Unidade pesquisaUnidadeID(){
		
		Unidade unidade = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UnidadeDAO unidadeDAO = new UnidadeDAO(s, Unidade.class);
	        unidade = unidadeDAO.buscaUnidade(id);
	    	s.close();
	    	return unidade;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Unidade pesquisaUnidadeID(Long id){
		
		Unidade unidade = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UnidadeDAO unidadeDAO = new UnidadeDAO(s, Unidade.class);
	        unidade = unidadeDAO.buscaUnidade(id);
	    	s.close();
	    	return unidade;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		Unidade unidade = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UnidadeDAO unidadeDAO = new UnidadeDAO(s, Unidade.class);
	        unidadeDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		Unidade unidade = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UnidadeDAO unidadeDAO = new UnidadeDAO(s, Unidade.class);
	        unidadeDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public Unidade alterar(){
		
		Unidade unidade = null;
		
		try{
			setDataupdate(new Date());
			endereco.setDataUpdate(new Date());
			
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	UnidadeDAO unidadeDAO = new UnidadeDAO(s, Unidade.class);
	        unidadeDAO.merge(this);
	    	s.close();
	    	return unidade;
	    }
		catch (Exception e) {
			return null;
		}	
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		Unidade unidade = new Unidade();
		return unidade;
	}
}