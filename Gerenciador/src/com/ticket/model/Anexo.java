package com.ticket.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.ticket.dao.AnexoDAO;
import com.principal.helper.HibernateUtil;
@XmlRootElement
@Entity
@Table(name="anexo")
public class Anexo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "anexoid")
	private Long   id;
	
	@Column(name = "anexodatacriacao")
	private Date dataCriacao = new Date();
	
	@Column(name = "anexodataupdate")
	private Date dataUpdate = new Date(); 
	
	@Column(name = "respostanome")
	private String nome =""; 
	
	@Column(name = "respostacaminho")
	private String caminho =""; 
	
	@Column(name = "respostatamanho")
	private String tamanho = ""; 
	
	@ManyToOne
	@JoinColumn( name = "ordemid")
    private Ordem ordem;

	public Anexo(Long id, Date dataCriacao, Date dataUpdate, String nome,
			String caminho, String tamanho, Ordem ordem) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.dataUpdate = dataUpdate;
		this.nome = nome;
		this.caminho = caminho;
		this.tamanho = tamanho;
		this.ordem = ordem;
	}

	public Anexo() {
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
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the dataUpdate
	 */
	public Date getDataUpdate() {
		return dataUpdate;
	}

	/**
	 * @param dataUpdate the dataUpdate to set
	 */
	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the caminho
	 */
	public String getCaminho() {
		return caminho;
	}

	/**
	 * @param caminho the caminho to set
	 */
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	/**
	 * @return the tamanho
	 */
	public String getTamanho() {
		return tamanho;
	}

	/**
	 * @param tamanho the tamanho to set
	 */
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * @return the ordem
	 */
	public Ordem getOrdem() {
		return ordem;
	}

	/**
	 * @param ordem the ordem to set
	 */
	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}

	public boolean salvar(){
		   
		boolean result;
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	AnexoDAO anexoDAO = new AnexoDAO(s, Anexo.class);
		  	result = anexoDAO.save(this);
		  	s.close();
		  	s = null;
			anexoDAO = null;
			return result;
	}
	
	public boolean salvarOuAlterar(){
		
		try{
			org.hibernate.Session s = HibernateUtil.currentSession();
			AnexoDAO anexoDAO = new AnexoDAO(s, Anexo.class);
			return anexoDAO.saveOrMerge(this);
		    }
			catch (Exception e) {
				return false;
			}	
		}
	
	public boolean remover(org.hibernate.Session s){
		
		boolean result = false;
		
		try{
		  	AnexoDAO anexoDAO = new AnexoDAO(s, Anexo.class);	
		  	s.clear();
		  	result = anexoDAO.delete(this);
			return result;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		  
		    boolean result;
		  	
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	AnexoDAO anexoDAO = new AnexoDAO(s, Anexo.class);
			result = anexoDAO.merge(this);
			s.close();
			s = null;
			anexoDAO = null;
			return result;
	
	}
	
	public boolean update(){
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	AnexoDAO anexoDAO = new AnexoDAO(s, Anexo.class);
		  	s.close();
	    	return anexoDAO.update(this);
	    	
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
		return "Anexo [id=" + id + ", dataCriacao=" + dataCriacao
				+ ", dataUpdate=" + dataUpdate + ", nome=" + nome
				+ ", caminho=" + caminho + ", tamanho=" + tamanho + ", ordem="
				+ ordem + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
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
		if (!(obj instanceof Anexo))
			return false;
		Anexo other = (Anexo) obj;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
