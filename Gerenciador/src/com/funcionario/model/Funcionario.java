package com.funcionario.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


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
import org.hibernate.annotations.CascadeType;
import com.auditor.model.Departamento;
import com.contato.model.Contato;
import com.funcionario.dao.FuncionarioDAO;
import com.principal.helper.HibernateUtil;
import com.sun.xml.internal.bind.CycleRecoverable;
import com.ticket.model.Mensagem;
import com.usuario.model.Usuario;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable , CycleRecoverable{ /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "funcionarioid")
	private Long   id;
	@Column(name = "funcionariocreate")
	private Date   dataCadastro;
	@Column(name = "funcionarioupdate")
	private Date   dataUltimaAlteracao;
	@Column(name = "funcionariostatus")
	private String status;
	@Column(name = "funcionariotipofuncionario")
	private String tipoFuncionario;
	@Column(name = "funcionariofidelidade")
	private String fidelidade;
	@Column(name = "clientegerenciamento")
	private String gerenciamento;
	@Column(name = "funcionarionome", length = 150)
	private String nome;
	@Column(name = "funcionariocpf", unique=true, length = 20)
	private String cpf;
	@Column(name = "funcionariorg", unique=true, length = 20)
	private String rg;
	@Column(name = "funcionariodesligamento")
	private String  motivoDesligamento;
	@Column(name = "funcionariodatanascimento")
	private Date dataNascimento;
	@Column(name = "funcionarionomepai")
	private String nomePai;
	@Column(name = "funcionarionomemae")
	private String nomeMae;
	@Column(name = "funcionarionumeropis")
	private String numeroPis;
	@Column(name = "funcionariotempoempresa")
	private Date tempoDeEmpresa;	
	@Column(name = "funcionarioimagem")
	private String imagem;
    
	@Transient
    private String infoFuncionario;
    
	@OneToOne
	@JoinColumn(name = "usuario")
	@Cascade(CascadeType.ALL)
	private Usuario usuario = null;
	
	@ManyToOne
	@JoinColumn(name = "departamentoid")
	private Departamento departamento = null;
	
	@OneToMany(mappedBy = "funcionario", targetEntity = Contato.class, fetch = FetchType.LAZY)
	@Cascade(CascadeType.DELETE)
	private Collection<Contato> contato = new ArrayList<Contato>();
	
	@OneToMany(mappedBy = "funcionario", targetEntity = Mensagem.class, fetch = FetchType.LAZY)
	@Cascade(CascadeType.DELETE)
	private Collection<Mensagem> mensagem = new ArrayList<Mensagem>();
	
	public Funcionario(){
		dataCadastro = new Date();
		dataUltimaAlteracao = new Date();
	}
	
	public Collection<Contato> getContato() {
		return contato;
	}
	
	public void setContato(Collection<Contato> contato) {
		this.contato = contato;
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
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the dataUltimaAlteracao
	 */
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}         

	/**
	 * @param dataUltimaAlteracao the dataUltimaAlteracao to set
	 */
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the tipo funcionario
	 */
	public String getTipoFuncionario() {
		return tipoFuncionario;
	}

	/**
	 * @param tipofuncionario the tipo funcionario to set
	 */
	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	/**
	 * @return the fidelidade
	 */
	public String getFidelidade() {
		return fidelidade;
	}

	/**
	 * @param fidelidade the fidelidade to set
	 */
	public void setFidelidade(String fidelidade) {
		this.fidelidade = fidelidade;
	}

	/**
	 * @return the gerenciamento
	 */
	public String getGerenciamento() {
		return gerenciamento;
	}

	/**
	 * @param gerenciamento the gerenciamento to set
	 */
	public void setGerenciamento(String gerenciamento) {
		this.gerenciamento = gerenciamento;
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
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

     
	/**
	 * @return the desligamento
	 */
	public String getDesligamento() {
		return motivoDesligamento;
	}

	/**
	 * @param desligamento the desligamento to set
	 */
	public void setDesligamento(String desligamento) {
		this.motivoDesligamento = desligamento;
	}

	/**
	 * @return the tempoDeEmpresa
	 */
	public Date getTempoDeEmpresa() {
		return tempoDeEmpresa;
	}

	/**
	 * @param tempoDeEmpresa the tempoDeEmpresa to set
	 */
	public void setTempoDeEmpresa(Date tempoDeEmpresa) {
		this.tempoDeEmpresa = tempoDeEmpresa;
	}

	/**
	 * @return the imagem
	 */
	public String getImagem() {
		return imagem;
	}

	/**
	 * @param imagem the imagem to set
	 */
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	
	/**
	 * @return the rg
	 */
	public String getRg() {
		return rg;
	}

	/**
	 * @param rg the rg to set
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}

	
	/**
	 * @return the datanascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param datanascimento the datanascimento to set
	 */
	public void setDataNascimento(Date datanascimento) {
		this.dataNascimento = datanascimento;
	}

	/**
	 * @return the nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * @param nomePai the nomePai to set
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * @return the nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * @param nomeMae the nomeMae to set
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * @return the numeropis
	 */
	public String getNumeroPis() {
		return numeroPis;
	}

	/**
	 * @param numeropis the numeropis to set
	 */
	public void setNumeroPis(String numeropis) {
		this.numeroPis = numeropis;
	}

	public String getInfoFuncionario() {
		infoFuncionario = nome +" - CPF: "+ cpf;
		return infoFuncionario;
	}

	public void setInfoFuncionario(String infoFuncionario) {
		this.infoFuncionario = infoFuncionario;
	}
	
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
		/**
	 * @return the motivoDesligamento
	 */
	public String getMotivoDesligamento() {
		return motivoDesligamento;
	}

	/**
	 * @param motivoDesligamento the motivoDesligamento to set
	 */
	public void setMotivoDesligamento(String motivoDesligamento) {
		this.motivoDesligamento = motivoDesligamento;
	}
	

	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

    public Collection<Mensagem> getMensagem() {
		return mensagem;
	}

    public void setMensagem(Collection<Mensagem> mensagem) {
		this.mensagem = mensagem;
	}
 
	public static BeanItemContainer<Funcionario> listaBeans(org.hibernate.Session s){
	
		BeanItemContainer<Funcionario> beans = new BeanItemContainer<Funcionario>(Funcionario.class);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(s, Funcionario.class);
	    beans.addAll( funcionarioDAO.list());
		
		return beans;
	}
	
	public static BeanItemContainer<Funcionario> listaBeans(){
		org.hibernate.Session s = HibernateUtil.currentSession();
		BeanItemContainer<Funcionario> beans = new BeanItemContainer<Funcionario>(Funcionario.class);
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(s, Funcionario.class);
	    beans.addAll( funcionarioDAO.list());
		return beans;
	}
	
	public static List<Funcionario> listaFuncionario(){
		org.hibernate.Session s = HibernateUtil.currentSession();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(s, Funcionario.class);
	   return  funcionarioDAO.list();
	}
	
	
	public static Funcionario pesquisaFuncionarioID(Long id){
		
		Funcionario cliente = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	FuncionarioDAO funcionarioDAO = new FuncionarioDAO(s, Funcionario.class);
	    	cliente = funcionarioDAO.buscaFuncionario(id);
	    	funcionarioDAO = null;
	    	s.close();
	    	s= null;
	    	return cliente;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		Boolean result = false;
		
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	FuncionarioDAO funcionarioDAO = new FuncionarioDAO(s, Funcionario.class);
			result = funcionarioDAO.save(this);
			funcionarioDAO = null;
			s.close();
			s = null;
			return result;
	}
	
	public boolean salvarOuAlterar(){
		Boolean result = false;
			org.hibernate.Session s = HibernateUtil.openSession();
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO(s, Funcionario.class);
		    result = funcionarioDAO.saveOrMerge(this);
		    s.close();
		    s = null;
		    funcionarioDAO = null;
		    return result;
		}
	
	public boolean remover(org.hibernate.Session s){
		
		Boolean result = false;
		  	FuncionarioDAO funcionarioDAO = new FuncionarioDAO(s, Funcionario.class);
	    	result = funcionarioDAO.delete(this);
	        funcionarioDAO = null;
	        return result;
	}
	
	public boolean alterar(){
		
		Boolean result = false;
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	FuncionarioDAO funcionarioDAO = new FuncionarioDAO(s, Funcionario.class);
		  	result = funcionarioDAO.merge(this);
	    	s.close();
	    	s = null;
	        funcionarioDAO = null;
	        return result;	
	}
	
	public boolean update(){
		
		boolean result = false;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	FuncionarioDAO funcionarioDAO = new FuncionarioDAO(s, Funcionario.class);
	    	result = funcionarioDAO.update(this);
	    	funcionarioDAO = null;
	    	s = null;
	        return result;
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
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Funcionario))
			return false;
		Funcionario other = (Funcionario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		Funcionario funcionario = new Funcionario();
		return funcionario;
	}


	

}
