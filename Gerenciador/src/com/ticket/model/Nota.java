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

import com.funcionario.model.Funcionario;
import com.ticket.dao.NotaDAO;
import com.principal.helper.HibernateUtil;
@XmlRootElement
@Entity
@Table(name="nota")
public class Nota {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "notaid")
	private Long   id;
	
	@Column(name = "notadatacriacao")
	private Date dataCriacao = new Date();
	
	@Column(name = "notadataupdate")
	private Date dataUpdate = new Date(); 
	
	@Column(name = "notatitulo", length=100)
	private String titulo = ""; 
	
	@Column(name = "notanota", length = 5000)
	private String nota = ""; 
	
	@ManyToOne
	@JoinColumn( name = "ordemid")
    private Ordem ordem;
	
	@ManyToOne
	@JoinColumn( name = "funcionarioid")
    private Funcionario funcionario = null;

	public Nota(Long id, Date dataCriacao, Date dataUpdate, String titulo,
			String nota, Ordem ordem, Funcionario funcionario) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.dataUpdate = dataUpdate;
		this.titulo = titulo;
		this.nota = nota;
		this.ordem = ordem;
		this.funcionario = funcionario;
	}

	public Nota() {
		super();
		dataCriacao = new Date();
		dataUpdate = new Date();
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
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the nota
	 */
	public String getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(String nota) {
		this.nota = nota;
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

	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		if(funcionario== null){
			funcionario = new Funcionario();
		}
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public boolean salvar(){
		   
		boolean result;
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	NotaDAO notaDAO = new NotaDAO(s, Nota.class);
		  	result = notaDAO.save(this);
		  	s.close();
		  	s = null;
			notaDAO = null;
			return result;
	}
	
	public boolean salvarOuAlterar(){
		
		try{
			org.hibernate.Session s = HibernateUtil.currentSession();
			NotaDAO notaDAO = new NotaDAO(s, Nota.class);
			return notaDAO.saveOrMerge(this);
		    }
			catch (Exception e) {
				return false;
			}	
		}
	
	public boolean remover(org.hibernate.Session s){
		
		boolean result = false;
		
		try{
		  	NotaDAO notaDAO = new NotaDAO(s, Nota.class);	
		  	s.clear();
		  	result = notaDAO.delete(this);
			return result;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		  
		    boolean result;
		  	
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	NotaDAO notaDAO = new NotaDAO(s, Nota.class);
			result = notaDAO.merge(this);
			s.close();
			s = null;
			notaDAO = null;
			return result;
	
	}
	
	public boolean update(){
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	com.ticket.dao.NotaDAO notaDAO = new NotaDAO(s, Nota.class);
		  	s.close();
	    	return notaDAO.update(this);
	    	
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
		return "Nota [id=" + id + ", dataCriacao=" + dataCriacao
				+ ", dataUpdate=" + dataUpdate + ", titulo=" + titulo
				+ ", nota=" + nota + "]";
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
		if (!(obj instanceof Nota))
			return false;
		Nota other = (Nota) obj;
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
