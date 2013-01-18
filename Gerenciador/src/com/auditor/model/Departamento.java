package com.auditor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import com.auditor.dao.DepartamentoDAO;
import com.auditor.model.contato.Telefone;
import com.dispositivo.model.Dispositivo;
import com.funcionario.model.Funcionario;
import com.sun.xml.internal.bind.CycleRecoverable;
import com.ticket.model.Ordem;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="departamento")
public class Departamento implements Serializable , CycleRecoverable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "departamentoid")
	private Long   id;
		
	@Column(name = "departamentonome", nullable=false, length=100)
	private String nome = "";
		
	@Column(name = "departamentodescricao", nullable=false)
	private String descricaoDepartamento = ""; //{area de texto}

	@Column(name = "departamentostatus", nullable=false, length=100)
	private String status = ""; //{ publico, interno}
	
	@Column(name = "departamentoemail", nullable=false, length=100)
	private String email = ""; 
	
	@Column(name = "departamentoresponsavel", nullable=false, length=100)
	private String responsavel = ""; 
		
	@Column(name = "departamentoemailTemplate", length=100)
	private String emailTemplate; //{ template de resposta}
		
	private Date dataCriacao;
	private Date dataUpdate = null;
	
	@OneToMany(mappedBy = "departamento", targetEntity = Telefone.class, fetch = FetchType.LAZY, cascade={ CascadeType.ALL})
	private Collection<Telefone> telefone = new ArrayList<Telefone>();

	@ManyToOne
	@JoinColumn( name = "unidadeid")
	private Unidade unidade;
		
	@OneToMany(mappedBy = "departamento", targetEntity = Dispositivo.class, fetch = FetchType.LAZY, cascade={ CascadeType.ALL})
    private Collection<Dispositivo> dispositivo = new ArrayList<Dispositivo>();
	
	@OneToMany(mappedBy = "departamento", targetEntity = Funcionario.class, fetch = FetchType.LAZY)
    private Collection<Funcionario> funcionario = new ArrayList<Funcionario>();
	
	@OneToMany(mappedBy = "departamento", targetEntity = Ordem.class, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Collection<Ordem> ordem = new ArrayList<Ordem>();

	@Transient
	private String departamentoInfo = "";
	
	public Departamento(){
		dataCriacao = new Date();
		dataUpdate = new Date();
	}

	//GETTERS AND SETTERS
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricaoDepartamento() {
		return descricaoDepartamento;
	}
	public void setDescricaoDepartamento(String descricaoDepartamento) {
		this.descricaoDepartamento = descricaoDepartamento;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}


	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getEmailTemplate() {
		return emailTemplate;
	}
	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public Collection<Telefone> getTelefone() {
		return telefone;
	}
	public void setTelefone(Collection<Telefone> telefone) {
		this.telefone = telefone;
	}
	public Collection<Dispositivo> getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Collection<Dispositivo> dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	//METODOS
		
	/**
	 * @return the funcionario
	 */
	public Collection<Funcionario> getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Collection<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the departamentoInfo
	 */
	public String getDepartamentoInfo() {
		return nome +" "+ responsavel;
	}
	
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

	/**
	 * @param departamentoInfo the departamentoInfo to set
	 */
	public void setDepartamentoInfo(String departamentoInfo) {
		this.departamentoInfo = departamentoInfo;
	}

	public static List<Departamento> listaAll(){
		org.hibernate.Session s = HibernateUtil.openSession();
		DepartamentoDAO departamentoDAO = new DepartamentoDAO(s, Departamento.class);
		return departamentoDAO.list();
	}
	
	public static BeanItemContainer<Departamento> listaBens(){
		BeanItemContainer<Departamento>beans = new BeanItemContainer<Departamento>(Departamento.class);
		org.hibernate.Session s = HibernateUtil.openSession();
		DepartamentoDAO departamentoDAO = new DepartamentoDAO(s, Departamento.class);
	    beans.addAll(departamentoDAO.list());
		return beans;
	}
	
	public  BeanItemContainer<Telefone> listaBensTel(){
		BeanItemContainer<Telefone>beans = new BeanItemContainer<Telefone>(Telefone.class);
		org.hibernate.Session s = HibernateUtil.openSession();
		DepartamentoDAO departamentoDAO = new DepartamentoDAO(s, Departamento.class);
		beans.addAll(departamentoDAO.pesquisaDepartamentoId(id).getTelefone());
	    return beans;
	}
	
	public Departamento pesquisaDepartamentoID(){
			
		Departamento departamento = null;
			
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
		    DepartamentoDAO departamentoDAO = new DepartamentoDAO(s, Departamento.class);
		    departamento = departamentoDAO.buscaDepartamento(id);
		    s.close();
		    return departamento;
		}
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Departamento pesquisaDepartamentoID(Long id){
		
		Departamento departamento = null;
			
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
		    DepartamentoDAO departamentoDAO = new DepartamentoDAO(s, Departamento.class);
		    departamento = departamentoDAO.buscaDepartamento(id);
		    s.close();
		    return departamento;
		}
		catch (Exception e) {
			return null;
		}	
	}

	@SuppressWarnings("unused")
	public boolean salvar(){
			
		Departamento departamento = null;
			
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
		    DepartamentoDAO departamentoDAO = new DepartamentoDAO(s, Departamento.class);
		    departamentoDAO.save(this);
		    s.close();
		    return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
			
		Departamento departamento = null;
			
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
		    DepartamentoDAO departamentoDAO = new DepartamentoDAO(s, Departamento.class);
		    departamentoDAO.delete(this);
		    s.close();
		    return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
			
		Departamento departamento = null;
			
		try{
			setDataUpdate(new Date());
			
			org.hibernate.Session s = HibernateUtil.openSession();
		    DepartamentoDAO departamentoDAO = new DepartamentoDAO(s, Departamento.class);
		    departamentoDAO.merge(this);
		    s.close();
		    return true;
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
		return "Departamento [id=" + id + ", nome=" + nome
				+ ", descricaoDepartamento=" + descricaoDepartamento
				+ ", status=" + status + ", email=" + email + ", responsavel="
				+ responsavel + ", emailTemplate=" + emailTemplate
				+ ", dataCriacao=" + dataCriacao + ", dataUpdate=" + dataUpdate
				+ ", telefone=" + telefone + ", unidade=" + unidade
				+ ", dispositivo=" + dispositivo + ", funcionario="
				+ funcionario + ", departamentoInfo=" + departamentoInfo + "]";
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
		if (!(obj instanceof Departamento))
			return false;
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		Departamento departamento = new Departamento();
		return departamento;
	}
	
	
	
}
