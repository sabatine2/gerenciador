package com.contato.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import com.cliente.model.Cliente;
import com.contato.dao.ContatoDAO;
import com.endereco.model.Endereco;
import com.funcionario.model.Funcionario;
import com.midiasocial.model.MidiaSocial;
import com.principal.helper.HibernateUtil;
import com.sun.xml.internal.bind.CycleRecoverable;
import com.telefone.model.TelefoneContato;
import com.ticket.model.Ordem;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="contato")
public class Contato implements Serializable , CycleRecoverable{ /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	@Column(name = "contatoid")
	private Long id_contato = null;
	@Column(name = "contatotipocontato")
	private String tipoContato;
	@Column(name = "contatonome")
	private String nome="";
	@Column(name = "contatodepartamento")
	private String departamento="";
	@Column(name = "contatocpf")
	private String cpf="";
	@Column(name = "contatodatacadastro")
	private Date dataCadastro;
	@Column(name = "contatostatus")
	private String status;
	@Column(name = "contatoemailprincipal")
	private String emailPrincipal = "";
	
	@Transient
	private String infoContato;
	
	
	@OneToMany(mappedBy = "contato" , targetEntity = MidiaSocial.class, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private Collection<MidiaSocial> media = new ArrayList<MidiaSocial>();

	@OneToMany(mappedBy = "contato" , targetEntity = TelefoneContato.class, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private Collection<TelefoneContato> telefoneContato = new ArrayList<TelefoneContato>();
	
	@OneToOne
	@JoinColumn(name = "enderecoid")
	private Endereco endereco = null;
	
	@ManyToOne
	@JoinColumn( name = "idcliente")
	private Cliente cliente = null;
	
	@ManyToOne
	@JoinColumn( name = "idfuncionario")
	private Funcionario funcionario;
	
	@OneToMany(mappedBy = "contato", targetEntity = Ordem.class, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Collection<Ordem> ordem = new ArrayList<Ordem>();
	
	/**
	 * @return the ordem
	 */
	public Collection<Ordem> getOrdem() {
		return ordem;
	}

	/**
	 * @param ordem the ordem to set
	 */
	public void setOrdem(Collection<Ordem> ordem) {
		this.ordem = ordem;
	}

	public Contato(){
		
		dataCadastro = new Date();
	
	}
	
	//GETTERS AND SETTERS
	
	public Long getId() {
		try{
		return id_contato;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public void setId(long id_contato) {
		this.id_contato = id_contato;
	}
	
	public void setIdNull() {
		this.id_contato = null;
	}
	
	public String getTipoContato() {
		return tipoContato;
	}
	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<MidiaSocial> getMedia() {
		return media;
	}
	public void setMedia(Collection<MidiaSocial> media) {
		this.media = media;
	}

	public Collection<TelefoneContato> getTelefoneContato() {
		return telefoneContato;
	}
	
	public void setTelefoneContato(Collection<TelefoneContato> telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	
	public Cliente getCliente() {
		if(cliente == null){
			cliente = new Cliente();
		}
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	 public Endereco getEndereco() {
		 if(endereco == null){
			 endereco = new Endereco();
		 }
			return endereco;
		}
	
	 public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}

	/**
		 * @return the id_contato
		 */
		public Long getId_contato() {
			return id_contato;
		}

		/**
		 * @param id_contato the id_contato to set
		 */
		public void setId_contato(Long id_contato) {
			this.id_contato = id_contato;
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
		
	    /**
		 * @return the infoContato
		 */
		public String getInfoContato() {
			return infoContato = "Nome: " + nome +" Email: "+emailPrincipal+" Endereco: "+ getEndereco().getLogradouro()+" "+getEndereco().getNumero()+" "+getEndereco().getBairro()+" "+getEndereco().getCidade()+" "+getEndereco().getEstado()+" "+getEndereco().getCep();
		}
		
				/**
		 * @param infoContato the infoContato to set
		 */
		public void setInfoContato(String infoContato) {
			this.infoContato = infoContato;
		}

		/**
		 * @return the emailPrincipal
		 */
		public String getEmailPrincipal() {
			return emailPrincipal;
		}

		/**
		 * @param emailPrincipal the emailPrincipal to set
		 */
		public void setEmailPrincipal(String emailPrincipal) {
			this.emailPrincipal = emailPrincipal;
		}

		public Contato pesquisaContatoID(){
	
		Contato contato = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.currentSession();
	    	ContatoDAO contatoDAO = new ContatoDAO(s, Contato.class);
	        contato = contatoDAO.buscaContato(id_contato);
	      	return contato;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	    
  public static Contato pesquisaContatoID(long id){
	    	
			Contato contato = null;
			
			try{
			  	org.hibernate.Session s = HibernateUtil.openSession();
		    	ContatoDAO contatoDAO = new ContatoDAO(s, Contato.class);
		        contato = contatoDAO.buscaContato(id);
		        s.close();
		        contatoDAO = null;
		      	return contato;
		    }
			catch (Exception e) {
				return null;
			}	
		}
	
	public boolean salvarAlterar(){
		
		boolean result = false;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.currentSession();
	    	ContatoDAO contatoDAO = new ContatoDAO(s, Contato.class);
	    	result = contatoDAO.saveOrMerge(this);
	    	return result; 
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean salvar(){
		
		boolean result = false;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ContatoDAO contatoDAO = new ContatoDAO(s, Contato.class);
	    	result = contatoDAO.save(this);
	    	s.close();
	    	contatoDAO = null;
	    	return result; 
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean remover(){
		 
		boolean result = false;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
			ContatoDAO contatoDAO = new ContatoDAO(s, Contato.class);
	     	result = contatoDAO.delete(this);
	     	s.close();
	    	return result;
	    }
		catch (Exception e) {
			return result;
		}	
	}
	
	public boolean alterar(){
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ContatoDAO contatoDAO = new ContatoDAO(s, Contato.class);
	        contatoDAO.merge(this);
	        s.close();
	        contatoDAO = null;
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
		result = prime * result
				+ ((id_contato == null) ? 0 : id_contato.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (!(obj instanceof Contato))
			return false;
		Contato other = (Contato) obj;
		if (id_contato == null) {
			if (other.id_contato != null)
				return false;
		} else if (!id_contato.equals(other.id_contato))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contato [id_contato=" + id_contato + ", tipoContato="
				+ tipoContato + ", nome=" + nome + ", departamento="
				+ departamento + ", cpf=" + cpf + ", dataCadastro="
				+ dataCadastro + ", status=" + status + ", emailPrincipal="
				+ emailPrincipal + ", infoContato=" + infoContato + ", media="
				+ media + ", telefoneContato=" + telefoneContato
				+ ", endereco=" + endereco + "]";
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		Contato contato = new Contato();
		return contato;
	}

	
	
	
}
