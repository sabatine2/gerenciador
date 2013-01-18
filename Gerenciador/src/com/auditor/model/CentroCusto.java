package com.auditor.model;


import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.CentroCustoDAO;
import com.auditor.model.contato.EnderecoAuditor;
import com.principal.helper.HibernateUtil;
import com.sun.xml.internal.bind.CycleRecoverable;
import com.vaadin.data.util.BeanItemContainer;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="centrocusto")
public class CentroCusto implements Serializable , CycleRecoverable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "centroid")
	private Long   id;
	
	@Column(name = "centronome", nullable=false, length=100)
	private String nome = "";
	
	@Column(name = "centroemail")
	private String     email = "";
	
	@Column(name = "centroresponsavel")
	private String     responsavel = "";

	@OneToMany(mappedBy = "centro", targetEntity = Unidade.class, fetch = FetchType.LAZY, cascade={ CascadeType.ALL})
    private Collection<Unidade> unidade = new ArrayList<Unidade>();
	
	@ManyToOne
	@JoinColumn( name = "organizacaoid")
    private Organizacao organizacao;

	@OneToOne
	private EnderecoAuditor endereco;
	
	@Column(name = "telefonenumero")
	private String telefone = "";
	
	private Date dataCriacao = new Date();
	
	private Date dataUpdate = null; 
	
	public CentroCusto(){}

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

	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Collection<Unidade> getUnidade() {
		return unidade;
	}
	public void setUnidade(Collection<Unidade> unidade) {
		this.unidade = unidade;
	}
	
	public Organizacao getOrganizacao() {
		return organizacao;
	}
	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}

	public EnderecoAuditor getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoAuditor endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	
	//METODOS

	@SuppressWarnings("unused")
	public static List<CentroCusto> ListAll(){
		BeanItemContainer<CentroCusto>beans = new BeanItemContainer<CentroCusto>(CentroCusto.class);
	
		org.hibernate.Session s = HibernateUtil.openSession();
		CentroCustoDAO centroCustoDAO = new CentroCustoDAO(s, CentroCusto.class);
		List<CentroCusto> centroCusto = centroCustoDAO.list();
		//s.close();
		return centroCusto;
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBens(){
		BeanItemContainer<CentroCusto>beans = new BeanItemContainer<CentroCusto>(CentroCusto.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		CentroCustoDAO centroCustoDAO = new CentroCustoDAO(s, CentroCusto.class);
	  
		List workouts = centroCustoDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			CentroCusto wo = (CentroCusto) iterator.next();
			wo.setUnidade(null);
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	public CentroCusto pesquisaCentroCustoID(){
		
		CentroCusto centroCusto = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	CentroCustoDAO centroCustoDAO = new CentroCustoDAO(s, CentroCusto.class);
	        centroCusto = centroCustoDAO.buscaCentroCusto(id);
	    	s.close();
	    	return centroCusto;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static CentroCusto pesquisaCentroCustoID(Long id){
		
		CentroCusto centroCusto = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	CentroCustoDAO centroCustoDAO = new CentroCustoDAO(s, CentroCusto.class);
	        centroCusto = centroCustoDAO.buscaCentroCusto(id);
	    	s.close();
	    	return centroCusto;
	    }
		catch (Exception e) {
			return null;
		}	
	}

	@SuppressWarnings("unused")
	public boolean salvar(){
		
		CentroCusto centroCusto = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	CentroCustoDAO centroCustoDAO = new CentroCustoDAO(s, CentroCusto.class);
	        centroCustoDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			System.out.println("CentroCusto: "+e.getMessage());
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		CentroCusto centroCusto = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	CentroCustoDAO centroCustoDAO = new CentroCustoDAO(s, CentroCusto.class);
	        centroCustoDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			System.out.println("CentroCusto: "+e);
			return false;
		}	
	}	
	
	@SuppressWarnings("unused")
	public boolean alterar(){
		
		CentroCusto centroCusto = null;
		
		try{
			setDataUpdate(new Date());
			endereco.setDataUpdate(new Date());
			
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	CentroCustoDAO centroCustoDAO = new CentroCustoDAO(s, CentroCusto.class);
	        centroCustoDAO.merge(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		CentroCusto centro = new CentroCusto();
		return centro;
	}
}