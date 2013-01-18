package com.auditor.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.MedidaDAO;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="medida")
public class Medida {

	@Id
	@GeneratedValue
	@Column(name="medida_id")
	private Long id = null;
	
	@Column(name="medida_descricao")
	private String descricao = "";
	
	@Column(name="medida_obs")
	private String observacao = "";
	
	@Column(name="tipo_dataCriacao")
	public Date dataCriacao = new Date();
	
	@Column(name="tipo_dataUpdate")
	public Date dataUpdate = null;
	
	@OneToMany(mappedBy = "medida", targetEntity = PalavraChave.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	public Collection<PalavraChave> palavraChave = new ArrayList<PalavraChave>();
	
	@OneToOne
	public ItemConta item;
	
	public Medida(){}
	
	//GETTER AND SETTER
	
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
	
	public ItemConta getItem() {
		return item;
	}
	public void setItem(ItemConta item) {
		this.item = item;
	}


	public Collection<PalavraChave> getPalavraChave() {
		return palavraChave;
	}
	public void setPalavraChave(Collection<PalavraChave> palavraChave) {
		this.palavraChave = palavraChave;
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
		BeanItemContainer<Medida>beans = new BeanItemContainer<Medida>(Medida.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		MedidaDAO medidaDAO = new MedidaDAO(s, Medida.class);
	  
		List workouts = medidaDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Medida wo = (Medida) iterator.next();
			beans.addBean(wo);
       	}	
		//s.close();
		return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaMedida(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		MedidaDAO medidaDAO = new MedidaDAO(s, Medida.class);
	  
		List workouts = medidaDAO.list();
		//s.close();
		return workouts;
	}
	
	public Medida pesquisaTipoID(){
		
		Medida medida = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	MedidaDAO medidaDAO = new MedidaDAO(s, Medida.class);
	        medida = medidaDAO.buscaMedidas(id);
	    	s.close();
	    	return medida;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Medida pesquisaTipoID(Long id){
		
		Medida medida = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	MedidaDAO medidaDAO = new MedidaDAO(s, Medida.class);
	    	medida = medidaDAO.buscaMedidas(id);
	    	s.close();
	    	return medida;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		Medida medida = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	MedidaDAO medidaDAO = new MedidaDAO(s, Medida.class);
	        medidaDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		Medida medida = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	MedidaDAO medidaDAO = new MedidaDAO(s, Medida.class);
	        medidaDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
	
		Medida medida = null;
	
		try{
			setDataUpdate(new Date());
			
			org.hibernate.Session s = HibernateUtil.openSession();
			MedidaDAO medidaDAO = new MedidaDAO(s, Medida.class);
			medidaDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
