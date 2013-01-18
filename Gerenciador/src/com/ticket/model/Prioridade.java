package com.ticket.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Session;

import com.principal.helper.HibernateUtil;
import com.ticket.dao.PrioridadeDAO;
import com.ticket.dao.TopicoDAO;
import com.vaadin.data.util.BeanItemContainer;

@XmlRootElement
@Entity
@Table(name="prioridade")
public class Prioridade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "prioridadeid")
	private Long   id;
	
	@Column(name = "prioridade", nullable=false, length=100)
	private String prioridade= ""; //alta baixa normal urgente

	@Column(name = "prioridadecor", nullable=false, length=100)
	private String cor ="";

	@Column(name = "prioridadepublico", nullable=false, length=100)
	private String  publico =""; //sim ou nao
	
	@Column(name = "prioridadeurgencia", nullable=false, length=100)
	private Integer prioridadeUrgencia;

	public Prioridade(Long id, String prioridade, String cor, String publico,
			Integer prioridadeUrgencia) {
		super();
		this.id = id;
		this.prioridade = prioridade;
		this.cor = cor;
		this.publico = publico;
		this.prioridadeUrgencia = prioridadeUrgencia;
	}

	public Prioridade() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the prioridade
	 */
	public String getPrioridade() {
		return prioridade;
	}

	/**
	 * @param prioridade the prioridade to set
	 */
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	/**
	 * @return the cor
	 */
	public String getCor() {
		return cor;
	}

	/**
	 * @param cor the cor to set
	 */
	public void setCor(String cor) {
		this.cor = cor;
	}

	/**
	 * @return the publico
	 */
	public String getPublico() {
		return publico;
	}

	/**
	 * @param publico the publico to set
	 */
	public void setPublico(String publico) {
		this.publico = publico;
	}

	/**
	 * @return the prioridadeUrgencia
	 */
	public Integer getPrioridadeUrgencia() {
		return prioridadeUrgencia;
	}

	/**
	 * @param prioridadeUrgencia the prioridadeUrgencia to set
	 */
	public void setPrioridadeUrgencia(Integer prioridadeUrgencia) {
		this.prioridadeUrgencia = prioridadeUrgencia;
	}
	
    public static BeanItemContainer<Prioridade> listaBeans(){
		
    	Session s = HibernateUtil.openSession();
		BeanItemContainer<Prioridade> beans = new BeanItemContainer<Prioridade>(Prioridade.class);
		PrioridadeDAO prioridadeDAO = new PrioridadeDAO(s, Prioridade.class);
	    beans.addAll( prioridadeDAO.list());
		
		return beans;
	}
    
     public static List<Prioridade> listaPrioridade(){
		
    	Session s = HibernateUtil.openSession();
		PrioridadeDAO prioridadeDAO = new PrioridadeDAO(s, Prioridade.class);
	    return prioridadeDAO.list();
	}
	
	public boolean salvar(){
		   
		boolean result;
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	PrioridadeDAO prioridadeDAO = new PrioridadeDAO(s, Prioridade.class);
		  	result = prioridadeDAO.save(this);
		  	s.close();
		  	s = null;
			prioridadeDAO = null;
			return result;
	}
	
	public boolean salvarOuAlterar(){
		
		try{
			org.hibernate.Session s = HibernateUtil.currentSession();
			PrioridadeDAO prioridadeDAO = new PrioridadeDAO(s, Prioridade.class);
			return prioridadeDAO.saveOrMerge(this);
		    }
			catch (Exception e) {
				return false;
			}	
		}
	
	public boolean remover(){
		
		boolean result = false;
		
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			PrioridadeDAO prioridadeDAO = new PrioridadeDAO(s, Prioridade.class);	
		  	result = prioridadeDAO.delete(this);
			return result;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		  
		    boolean result;
		  	
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	PrioridadeDAO prioridadeDAO = new PrioridadeDAO(s, Prioridade.class);
			result = prioridadeDAO.merge(this);
			s.close();
			s = null;
			prioridadeDAO = null;
			return result;
	
	}
	
	public static Prioridade pesquisaOrdemID(Long id){
		
		Prioridade prioridade = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	PrioridadeDAO prioridadeDAO = new PrioridadeDAO(s, Prioridade.class);
	    	prioridade = prioridadeDAO.buscaPrioridade(id);
	    	s.close();
	    	return prioridade;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean update(){
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	PrioridadeDAO prioridadeDAO = new PrioridadeDAO(s, Prioridade.class);
		  	s.close();
	    	return prioridadeDAO.update(this);
	    	
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Prioridade [id=" + id + ", Prioridade=" + prioridade + ", cor="
				+ cor + ", publico=" + publico + ", prioridadeUrgencia="
				+ prioridadeUrgencia + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Prioridade))
			return false;
		Prioridade other = (Prioridade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
