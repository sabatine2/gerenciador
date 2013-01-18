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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.TipoServicoDAO;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="tipoServico")
public class TipoServico {

	@Id
	@GeneratedValue
	@Column(name="tipo_id")
	public Long id = null;
	
	@Column(name="tipo_descricao")
	public String descricao = "";
	
	@Column(name="tipo_observacao")
	public String observacao = "";
	
	@OneToMany(mappedBy = "tipoServico", targetEntity = PalavraChave.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	public Collection<PalavraChave> palavraChave = new ArrayList<PalavraChave>();
	
	@Column(name="tipo_dataCriacao")
	public Date dataCriacao = new Date();
	
	@Column(name="tipo_dataUpdate")
	public Date dataUpdate = null;
	
	@OneToOne
	private ItemConta item;
	
	public TipoServico(){}

	//GETTERS AND SETTERS

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public ItemConta getItem() {
		return item;
	}
	public void setItem(ItemConta item) {
		this.item = item;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}
	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Collection<PalavraChave> getPalavraChave() {
		return palavraChave;
	}
	public void setPalavraChave(Collection<PalavraChave> palavraChave) {
		this.palavraChave = palavraChave;
	}
	
	//METODOS
	
	public String getPalavraChaveString(){
		
		String palavra = "";
		for (Iterator<PalavraChave> i = this.palavraChave.iterator(); i.hasNext();) {
			PalavraChave pChave = i.next();
			palavra += pChave.getPalavraChave() + ";";
		}
		return palavra;
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBens(){
		BeanItemContainer<TipoServico>beans = new BeanItemContainer<TipoServico>(TipoServico.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		TipoServicoDAO tipoDAO = new TipoServicoDAO(s, TipoServico.class);
	  
		List workouts = tipoDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			TipoServico wo = (TipoServico) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaTipo(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		TipoServicoDAO tipoDAO = new TipoServicoDAO(s, TipoServico.class);
	  
		List workouts = tipoDAO.list();
		
		 //s.close();
		 return workouts;
	}
	
	public TipoServico pesquisaTipoID(){
		
		TipoServico tipoServico = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TipoServicoDAO tipoDAO = new TipoServicoDAO(s, TipoServico.class);
	        tipoServico = tipoDAO.buscaTipoServicos(id);
	    	s.close();
	    	return tipoServico;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static TipoServico pesquisaTipoID(Long id){
		
		TipoServico tipoServico = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TipoServicoDAO tipoDAO = new TipoServicoDAO(s, TipoServico.class);
	    	tipoServico = tipoDAO.buscaTipoServicos(id);
	    	s.close();
	    	return tipoServico;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		TipoServico tipoServico = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TipoServicoDAO tipoDAO = new TipoServicoDAO(s, TipoServico.class);
	        tipoDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		TipoServico tipoServico = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TipoServicoDAO tipoDAO = new TipoServicoDAO(s, TipoServico.class);
	        tipoDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
	
		TipoServico tipoServico = null;
	
		try{
			setDataUpdate(new Date());
			
			org.hibernate.Session s = HibernateUtil.openSession();
			TipoServicoDAO tipoDAO = new TipoServicoDAO(s, TipoServico.class);
			tipoDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
