package com.auditor.model.contato;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.PrestadoraTelefoniaDAO;
import com.auditor.model.Conta;
import com.auditor.model.ItemConta;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name = "prestadoraTelefonia")
public class PrestadoraTelefonia {

	@Id
	@Column(name = "prestadoracodigo", nullable=false, length=100)
	private Long codigo;
	
	@Column(name = "prestadoradatacriacao", nullable=false, length=100)
    private Date dataCriacao = new Date();
	
	@Column(name = "prestadoradataupdate", nullable=false, length=100)
	private Date dataUpdate = new Date();
	
	@Column(name = "prestadoracnmpj", nullable=false, length=20)
	private String cnpj = "";
	
	@Column(name = "pestradoranome", nullable=false, length=100)
	private String nome = "";
	
	@Column(name = "prestadoraestado", length=100)
	private String estado;

	@XmlElement
	@OneToMany(mappedBy = "prestadora", targetEntity = Telefone.class, fetch = FetchType.LAZY, cascade={ CascadeType.ALL})
	private Collection<Telefone> telefone = new ArrayList<Telefone>();
	
	@XmlElement
	@OneToOne
	private Conta conta;
	
	@XmlElement
	@OneToOne
	private ItemConta itemConta;
	
	public PrestadoraTelefonia() {}

	public PrestadoraTelefonia(String prestadoraNome,
			Long prestadoraCodigo, String prestadoraEstado) {
		this.nome = prestadoraNome;
		this.codigo = prestadoraCodigo;
		this.estado = prestadoraEstado;
	}

	public PrestadoraTelefonia(String prestadoraNome,
			Long prestadoraCodigo) {
		
		this.nome = prestadoraNome;
		this.codigo = prestadoraCodigo;
	}

	//GETTERS AND SETTERS

	public String getNome() {
		return nome;
	}
	public void setNome(String prestadoraNome) {
		this.nome = prestadoraNome;
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long prestadoraCodigo) {
		this.codigo = prestadoraCodigo;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String prestadoraEstado) {
		this.estado = prestadoraEstado;
	}

	public Collection<Telefone> getTelefone() {
		return telefone;
	}
	public void setTelefone(Collection<Telefone> telefone) {
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

	public ItemConta getItemConta() {
		return itemConta;
	}
	public void setItemConta(ItemConta itemConta) {
		this.itemConta = itemConta;
	}

	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String prestadoraCnpj) {
		this.cnpj = prestadoraCnpj;
	}
	
	//METODOS
	
	@SuppressWarnings("unused")
	public static List<PrestadoraTelefonia> ListAll(){
		BeanItemContainer<PrestadoraTelefonia>beans = new BeanItemContainer<PrestadoraTelefonia>(PrestadoraTelefonia.class);
	
		org.hibernate.Session s = HibernateUtil.openSession();
		PrestadoraTelefoniaDAO prestadoraTelefoniaDAO = new PrestadoraTelefoniaDAO(s, PrestadoraTelefonia.class);
		List<PrestadoraTelefonia> prestadoraTelefonia = prestadoraTelefoniaDAO.list();
		//s.close();
		return prestadoraTelefonia;
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBens(){
		BeanItemContainer<PrestadoraTelefonia>beans = new BeanItemContainer<PrestadoraTelefonia>(PrestadoraTelefonia.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		PrestadoraTelefoniaDAO prestadoraTelefoniaDAO = new PrestadoraTelefoniaDAO(s, PrestadoraTelefonia.class);
	  
		List workouts = prestadoraTelefoniaDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			PrestadoraTelefonia wo = (PrestadoraTelefonia) iterator.next();
			wo.setTelefone(null);
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	public PrestadoraTelefonia pesquisaPrestadoraID(){
		
		PrestadoraTelefonia presTelefonia = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	PrestadoraTelefoniaDAO presTelefoniaDAO = new PrestadoraTelefoniaDAO(s, PrestadoraTelefonia.class);
	        presTelefonia = presTelefoniaDAO.buscaPrestadoraTelefonia(codigo);
	    	s.close();
	    	return presTelefonia;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		PrestadoraTelefonia presTelefonia = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	PrestadoraTelefoniaDAO presTelefoniaDAO = new PrestadoraTelefoniaDAO(s, PrestadoraTelefonia.class);
	    	presTelefoniaDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		PrestadoraTelefonia presTelefonia = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	PrestadoraTelefoniaDAO presTelefoniaDAO = new PrestadoraTelefoniaDAO(s, PrestadoraTelefonia.class);
	    	presTelefoniaDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
		
		PrestadoraTelefonia presTelefonia = null;
		
		try{
			setDataUpdate(new Date());
			
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	PrestadoraTelefoniaDAO presTelefoniaDAO = new PrestadoraTelefoniaDAO(s, PrestadoraTelefonia.class);
	    	presTelefoniaDAO.merge(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result
				+ ((dataUpdate == null) ? 0 : dataUpdate.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		if (!(obj instanceof PrestadoraTelefonia))
			return false;
		PrestadoraTelefonia other = (PrestadoraTelefonia) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (dataUpdate == null) {
			if (other.dataUpdate != null)
				return false;
		} else if (!dataUpdate.equals(other.dataUpdate))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
}