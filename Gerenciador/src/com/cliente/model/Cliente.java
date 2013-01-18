package com.cliente.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import com.cliente.dao.*;
import com.contato.model.Contato;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue
	@Column(name = "clienteid")
	private Long   id;
	@Column(name = "clientecreate")
	private Date   dataCadastro;
	@Column(name = "clienteupdate")
	private Date   dataUltimaAlteracao;
	@Column(name = "clientestatus")
	private String status;
	@Column(name = "clientetipocliente")
	private String tipoCliente = null;
	@Column(name = "clientefidelidade")
	private String fidelidade =null;
	@Column(name = "clientegerenciamento")
	private String gerenciamento;
	@Column(name = "clienterazaosocial", length = 150)
	private String razaoSocial = null;
	@Column(name = "clientefantasia", length = 150)
	private String nomeFantasia = null;
	@Column(name = "clientesite")
	private String site;
	@Column(name = "clientecnpj", unique=true, length = 20)
	private String cnpj = null;
	@Column(name = "clienteie", length = 20)
	private String ie = null;
	@Column(name = "clienteatividadeprincipal")
	private String atividadePrincipal = null;
	@Column(name = "clientetempoempresa")
	private Date tempoDeEmpresa;	
	@Column(name = "clienteimagem")
	private String imagem;
    
	@Transient
    private String infoCliente;
    
	@OneToMany(mappedBy = "cliente", targetEntity = Contato.class, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private Collection<Contato> contato = new ArrayList<Contato>();
	
	public Cliente(){
		dataCadastro = new Date();
		dataUltimaAlteracao = new Date();
	}
	
	public Collection<Contato> getContato() {
		return contato;
	}
	
	public void setContato(Collection<Contato> contato) {
		this.contato = contato;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	
	public String getStatus() {
		if(status == null){
		   status = new String();
		}
		return status;
	}
	
	public void setStatus(String status) {
			this.status = status;
	}
	
	public String getTipoCliente() {
		if(tipoCliente == null){
			   tipoCliente = new String();
		}
	
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getFidelidade() {
		if(fidelidade == null){
			fidelidade = new String();
		}
	
		return fidelidade;
	}
	public void setFidelidade(String fidelidade) {
		this.fidelidade = fidelidade;
	}
	public String getGerenciamento() {
		if(gerenciamento == null){
			   gerenciamento = new String();
		}
	
		return gerenciamento;
	}
	public void setGerenciamento(String gerenciamento) {
		this.gerenciamento = gerenciamento;
	}
	public String getRazaoSocial() {
		if(razaoSocial== null){
			razaoSocial= new String();
		}
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getNomeFantasia() {
		if(nomeFantasia == null){
			   nomeFantasia = new String();
		}
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getCnpj() {
		if(cnpj == null){
			   cnpj = new String();
		}
	
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getIe() {
		if(ie == null){
			   ie = new String();
		}
	
		return ie;
	}
	public void setIe(String ie) {
		this.ie = ie;
	}
	public String getAtividadePrincipal() {
		if(atividadePrincipal == null){
			   atividadePrincipal = new String();
		}
		return atividadePrincipal;
	}
	public void setAtividadePrincipal(String atividadePrincipal) {
		this.atividadePrincipal = atividadePrincipal;
	}
	public Date getTempoDeEmpresa() {
		return tempoDeEmpresa;
	}
	public void setTempoDeEmpresa(Date tempoDeEmpresa) {
		this.tempoDeEmpresa = tempoDeEmpresa;
	}
	
	//METODOS
	
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	/**
	 * @return the infoCliente
	 */
	public String getInfoCliente() {
		infoCliente = razaoSocial +" - Nome Fantasia: "+ nomeFantasia+" - CNPJ: "+ cnpj;
		return infoCliente;
	}

	/**
	 * @param infoCliente the infoCliente to set
	 */
	public void setInfoCliente(String infoCliente) {
		this.infoCliente = infoCliente;
	}
	
	public static BeanItemContainer<Cliente> listaBens(org.hibernate.Session s){
	 
		BeanItemContainer<Cliente> beans = new BeanItemContainer<Cliente>(Cliente.class);
		ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
	    s.clear();
	    s.flush();
		beans.addAll(clienteDAO.list());
		return beans;
	}
	
	public static Map<Long, Cliente> listaClienteHash(){
		
		Map<Long, Cliente> cliente = new HashMap<Long, Cliente>();
		
		org.hibernate.Session s = HibernateUtil.currentSession();
		ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
		for(Iterator<Cliente> i = clienteDAO.list().iterator(); i.hasNext();){
			Cliente c = i.next();
	        cliente.put(c.getId(), c);
		}
		return cliente;
	   }
	
     public static List<Cliente> listaCliente(org.hibernate.Session s){
		ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
		return clienteDAO.list();
	   }
	
	public static BeanItemContainer<Cliente> listaBeansAtivo(){
		
		BeanItemContainer<Cliente> beans = new BeanItemContainer<Cliente>(Cliente.class);
		
		org.hibernate.Session s = HibernateUtil.currentSession();
		ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
			
		beans.addAll(clienteDAO.pesquisaClientesAtivo());
		
		return beans;
	}
	
    public static List<Cliente> listaClienteAtivo(org.hibernate.Session s){
		ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
	    return  clienteDAO.pesquisaClientesAtivo();
	}
	
	
	public static Cliente pesquisaClienteID(Long id){
		Cliente cliente = null;
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
	    	cliente = clienteDAO.buscaCliente(id);
	    	clienteDAO = null;
	    	s.close();
	    	return cliente;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		   
		boolean result;
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
		  	result = clienteDAO.save(this);
		  	s.close();
		  	s = null;
			clienteDAO = null;
			return result;
	}
	
	public boolean salvarOuAlterar(){
		
		try{
			org.hibernate.Session s = HibernateUtil.currentSession();
			ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
			return clienteDAO.saveOrMerge(this);
		    }
			catch (Exception e) {
				return false;
			}	
		}
	
	public boolean remover(org.hibernate.Session s){
		
		boolean result = false;
		
		try{
		  	ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);	
		  	s.clear();
		  	result = clienteDAO.delete(this);
			return result;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		  
		    boolean result;
		  	
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
			result = clienteDAO.merge(this);
			s.close();
			s = null;
			clienteDAO = null;
			return result;
	
	}
	
	public boolean update(){
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	ClienteDAO clienteDAO = new ClienteDAO(s, Cliente.class);
		  	s.close();
	    	return clienteDAO.update(this);
	    	
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
		result = prime
				* result
				+ ((atividadePrincipal == null) ? 0 : atividadePrincipal
						.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime
				* result
				+ ((dataUltimaAlteracao == null) ? 0 : dataUltimaAlteracao
						.hashCode());
		result = prime * result
				+ ((fidelidade == null) ? 0 : fidelidade.hashCode());
		result = prime * result
				+ ((gerenciamento == null) ? 0 : gerenciamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ie == null) ? 0 : ie.hashCode());
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((tempoDeEmpresa == null) ? 0 : tempoDeEmpresa.hashCode());
		result = prime * result
				+ ((tipoCliente == null) ? 0 : tipoCliente.hashCode());
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
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		if (atividadePrincipal == null) {
			if (other.atividadePrincipal != null)
				return false;
		} else if (!atividadePrincipal.equals(other.atividadePrincipal))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataUltimaAlteracao == null) {
			if (other.dataUltimaAlteracao != null)
				return false;
		} else if (!dataUltimaAlteracao.equals(other.dataUltimaAlteracao))
			return false;
		if (fidelidade == null) {
			if (other.fidelidade != null)
				return false;
		} else if (!fidelidade.equals(other.fidelidade))
			return false;
		if (gerenciamento == null) {
			if (other.gerenciamento != null)
				return false;
		} else if (!gerenciamento.equals(other.gerenciamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ie == null) {
			if (other.ie != null)
				return false;
		} else if (!ie.equals(other.ie))
			return false;
		if (imagem == null) {
			if (other.imagem != null)
				return false;
		} else if (!imagem.equals(other.imagem))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tempoDeEmpresa == null) {
			if (other.tempoDeEmpresa != null)
				return false;
		} else if (!tempoDeEmpresa.equals(other.tempoDeEmpresa))
			return false;
		if (tipoCliente == null) {
			if (other.tipoCliente != null)
				return false;
		} else if (!tipoCliente.equals(other.tipoCliente))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dataCadastro=" + dataCadastro
				+ ", dataUltimaAlteracao=" + dataUltimaAlteracao + ", status="
				+ status + ", tipoCliente=" + tipoCliente + ", fidelidade="
				+ fidelidade + ", gerenciamento=" + gerenciamento
				+ ", razaoSocial=" + razaoSocial + ", nomeFantasia="
				+ nomeFantasia + ", site=" + site + ", cnpj=" + cnpj + ", ie="
				+ ie + ", atividadePrincipal=" + atividadePrincipal
				+ ", tempoDeEmpresa=" + tempoDeEmpresa + ", imagem=" + imagem
				+ ", contato=" + contato + "]";
	}
	

}
