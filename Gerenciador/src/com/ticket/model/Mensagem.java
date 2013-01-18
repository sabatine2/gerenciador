package com.ticket.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.funcionario.model.Funcionario;
import com.ticket.dao.MensagemDAO;
import com.principal.helper.HibernateUtil;
@XmlRootElement
@Entity
@Table(name="mensagem")
public class Mensagem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "mensagemid")
	private Long   id;
	
	@Column(name = "mensagemdatacriacao")
	private Date dataCriacao = new Date();
	
	@Column(name = "mensagemdataupdate")
	private Date dataUpdate = new Date(); 
	
	@Column(name = "mensagemfonte", length=100)
	private String fonte =""; //web telefone zabbix;
	
	@Column(name = "mensagemmensagem", length=5000)
	@Lob
	private String mensagem ="";;
	
	@Column(name = "mensagemtipo", length=100)
	private String tipo =""; 
	
	@Column(name = "mensagemip")
	private String ipEndereco = ""; 
	
	@ManyToOne
	@JoinColumn( name = "funcionarioid")
    private Funcionario funcionario;

	@ManyToOne
	@JoinColumn( name = "ordemid")
    private Ordem ordem;

	
	public Mensagem() {
		super();
		dataCriacao = new Date();
		dataUpdate = new Date();
		}


	public Mensagem(String mensagem) {
		super();
		dataCriacao = new Date();
		dataUpdate = new Date();
		this.mensagem = mensagem;
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
	 * @return the fonte
	 */
	public String getFonte() {
		return fonte;
	}

	/**
	 * @param fonte the fonte to set
	 */
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	/**
	 * @return the ipEndereco
	 */
	public String getIpEndereco() {
		return ipEndereco;
	}

	/**
	 * @param ipEndereco the ipEndereco to set
	 */
	public void setIpEndereco(String ipEndereco) {
		this.ipEndereco = ipEndereco;
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
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}


	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
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
		  	MensagemDAO mensagemDAO = new MensagemDAO(s, Mensagem.class);
		  	result = mensagemDAO.save(this);
		  	s.close();
		  	s = null;
			mensagemDAO = null;
			return result;
	}
	
	public boolean salvarOuAlterar(){
		
		try{
			org.hibernate.Session s = HibernateUtil.currentSession();
			MensagemDAO mensagemDAO = new MensagemDAO(s, Mensagem.class);
			return mensagemDAO.saveOrMerge(this);
		    }
			catch (Exception e) {
				return false;
			}	
		}
	
	public boolean remover(org.hibernate.Session s){
		
		boolean result = false;
		
		try{
		  	MensagemDAO mensagemDAO = new MensagemDAO(s, Mensagem.class);	
		  	s.clear();
		  	result = mensagemDAO.delete(this);
			return result;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		  
		    boolean result;
		  	
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	MensagemDAO mensagemDAO = new MensagemDAO(s, Mensagem.class);
			result = mensagemDAO.merge(this);
			s.close();
			s = null;
			mensagemDAO = null;
			return result;
	
	}
	
	public boolean update(){
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	com.ticket.dao.MensagemDAO mensagemDAO = new MensagemDAO(s, Mensagem.class);
		  	s.close();
	    	return mensagemDAO.update(this);
	    	
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
		return "Mensagem [id=" + id + ", dataCriacao=" + dataCriacao
				+ ", dataUpdate=" + dataUpdate + ", fonte=" + fonte
				+ ", mensagem=" + mensagem + ", ipEndereco=" + ipEndereco
				;
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
		if (!(obj instanceof Mensagem))
			return false;
		Mensagem other = (Mensagem) obj;
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
