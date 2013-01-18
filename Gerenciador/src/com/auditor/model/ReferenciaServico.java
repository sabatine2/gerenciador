package com.auditor.model;

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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.ReferenciaServicoDAO;
import com.auditor.model.contato.Telefone;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name = "referenciaservico")
public class ReferenciaServico {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "referenciaid")
	private Long   id;

	@Column(name = "referenciatipo", nullable=false, length=100)
	private String tipo = "";

	@Column(name = "referenciaobs", length=500)
	private String obs = "";

	@OneToMany(mappedBy = "telefoneReferencia", targetEntity = Telefone.class, fetch = FetchType.LAZY, cascade={ CascadeType.ALL})
	private Collection<Telefone> telefone = new ArrayList<Telefone>();
	
	private Date dataCriacao = new Date();
	
	private Date dataUpdate = null;
	
	public ReferenciaServico(){}

	//GETTERS AND SETTERS
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
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

	public Collection<Telefone> getTelefone() {
		return telefone;
	}
	public void setTelefone(Collection<Telefone> telefone) {
		this.telefone = telefone;
	}
	
	//METODOS
	
	@SuppressWarnings("unused")
	public static List<ReferenciaServico> ListAll(){
		BeanItemContainer<ReferenciaServico>beans = new BeanItemContainer<ReferenciaServico>(ReferenciaServico.class);
	
		org.hibernate.Session s = HibernateUtil.openSession();
		ReferenciaServicoDAO referenciaServicoDAO = new ReferenciaServicoDAO(s, ReferenciaServico.class);
		List<ReferenciaServico> referenciaServico = referenciaServicoDAO.list();
		//s.close();
		return referenciaServico;
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBens(){
		BeanItemContainer<ReferenciaServico>beans = new BeanItemContainer<ReferenciaServico>(ReferenciaServico.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ReferenciaServicoDAO referenciaServicoDAO = new ReferenciaServicoDAO(s, ReferenciaServico.class);
	  
		List workouts = referenciaServicoDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			ReferenciaServico wo = (ReferenciaServico) iterator.next();
			wo.setTelefone(null);
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	public ReferenciaServico pesquisaReferenciaID(){
			
		ReferenciaServico refServico = null;
			
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
		    ReferenciaServicoDAO refServicoDAO = new ReferenciaServicoDAO(s, ReferenciaServico.class);
		    refServico = refServicoDAO.buscaReferenciaServico(id);
		    s.close();
		    return refServico;
		}
		catch (Exception e) {
			return null;
		}	
	}
		
	@SuppressWarnings("unused")
	public boolean salvar(){
			
		ReferenciaServico refServico = null;
			
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			ReferenciaServicoDAO refServicoDAO = new ReferenciaServicoDAO(s, ReferenciaServico.class);
		    refServicoDAO.save(this);
		    s.close();
		    return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		ReferenciaServico refServico = null;
			
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			ReferenciaServicoDAO refServicoDAO = new ReferenciaServicoDAO(s, ReferenciaServico.class);
		    refServicoDAO.delete(this);
		    s.close();
		    return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
		
		ReferenciaServico refServico = null;
			
		try{
			setDataUpdate(new Date());
			
			org.hibernate.Session s = HibernateUtil.openSession();
			ReferenciaServicoDAO refServicoDAO = new ReferenciaServicoDAO(s, ReferenciaServico.class);
		    refServicoDAO.merge(this);
		    s.close();
		    return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
