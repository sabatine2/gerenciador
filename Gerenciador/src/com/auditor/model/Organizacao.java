package com.auditor.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.OrganizacaoDAO;
import com.auditor.model.contato.EnderecoAuditor;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="organizacao")
public class Organizacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "organizacaoid")
	private Long       id;
	
	@Column(name = "organizacaorazaosocial")
	private String     razaoSocial = ""; 
	
	@Column(name = "organizacaonomefantasia")
	private String     nomeFantasia = ""; 
	
	@Column(name = "organizacaocnpj")
	private String     cnpj = "";
	
	@Column(name = "organizacaoinscricaoestadual")
	private String     inscricaoEstadual = "";
	
	@Column(name = "organizacaoinscricaomunicipal")
	private String     inscricaoMunicipal = "";
	
	@Column(name = "organizacaocnae")
	private String     cnaeFiscal = "";
	
	@Column(name = "organizacaoinscricaoestadualsubst")
	private String    inscricaoEstadualSubstTributario = "";
	
	@Column(name = "organizacaoregimetributario")
	private String    regimeTributario;
	
	@Column(name = "organizacaoemail")
	private String     email = "";
	
	@Column(name = "organizacalogo")
	private String     logo = "";
	
	@OneToOne/*(cascade={CascadeType.ALL})*/
	@JoinColumn(name = "enderecoid")
	private EnderecoAuditor endereco;
	
	@Column(name = "telefonenumero")
	private String telefone = "";
	
    @OneToMany(mappedBy = "organizacao", targetEntity = CentroCusto.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    private Collection<CentroCusto> centroCusto = new ArrayList<CentroCusto>();

    private Date dataCriacao = new Date();
    
    private Date dataUpdate = null;
    
    public Organizacao(){}
  
  //GETTERS AND SETTERS

  	public Long getId() {
  		return id;
  	}
  	public void setId(Long id) {
  		this.id = id;
  	}

  	public String getRazaoSocial() {
  		return razaoSocial;
  	}
  	public void setRazaoSocial(String razaoSocial) {
  		this.razaoSocial = razaoSocial;
  	}

  	public String getNomeFantasia() {
  		return nomeFantasia;
  	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}
	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getCnaeFiscal() {
		return cnaeFiscal;
	}
	public void setCnaeFiscal(String cnaeFiscal) {
		this.cnaeFiscal = cnaeFiscal;
	}

	public String getInscricaoEstadualSubstTributario() {
		return inscricaoEstadualSubstTributario;
	}
	public void setInscricaoEstadualSubstTributario(String inscricaoEstadualSubstTributario) {
		this.inscricaoEstadualSubstTributario = inscricaoEstadualSubstTributario;
	}

	public String getRegimeTributario() {
		return regimeTributario;
	}
	public void setRegimeTributario(String regimeTributario) {
		this.regimeTributario = regimeTributario;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public EnderecoAuditor getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoAuditor endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
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

	public Collection<CentroCusto> getCentroCusto() {
		return centroCusto;
	}
	public void setCentroCusto(Collection<CentroCusto> centroCusto) {
		this.centroCusto = centroCusto;
	}
	
	//METODOS
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBeas(){
		BeanItemContainer<Organizacao>beans = new BeanItemContainer<Organizacao>(Organizacao.class);
	
		org.hibernate.Session s = HibernateUtil.openSession();
		OrganizacaoDAO organizacaoDAO = new OrganizacaoDAO(s, Organizacao.class);
  
		List workouts = organizacaoDAO.list();
	
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Organizacao wo = (Organizacao) iterator.next();
			wo.setCentroCusto(null);
			beans.addBean(wo);
		}	
		//s.close();
		return beans;
	}
	
	public static List<Organizacao> ListaOrganizacao(){
	
		org.hibernate.Session s = HibernateUtil.openSession();
		OrganizacaoDAO organizacaoDAO = new OrganizacaoDAO(s, Organizacao.class);
		
		return organizacaoDAO.list();
		
	}
	
	public Organizacao pesquisaOrganizacaoID(){
	
		Organizacao organizacao = null;
	
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			OrganizacaoDAO organizacaoDAO = new OrganizacaoDAO(s, Organizacao.class);
			organizacao = organizacaoDAO.buscaOrganizacao(id);
			//s.close();
			return organizacao;
		}
		catch (Exception e) {
			return null;
		}	
	}

	@SuppressWarnings("unused")
	public Boolean salvar(){
	
		Organizacao organizacao = null;
	
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			OrganizacaoDAO organizacaoDAO = new OrganizacaoDAO(s, Organizacao.class);
			organizacaoDAO.save(this);
			s.close();
			return true;
		}
		catch (Exception e) {
			System.out.println("CentroCusto: "+e);
			return false;
		}	
	}

	@SuppressWarnings("unused")
	public boolean remover(){
	
		Organizacao organizacao = null;
	
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			OrganizacaoDAO organizacaoDAO = new OrganizacaoDAO(s, Organizacao.class);
			organizacaoDAO.delete(this);
			s.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}	
	}

	public Organizacao alterar(){
	
		Organizacao organizacao = null;
	
		try{
			setDataUpdate(new Date());
			endereco.setDataUpdate(new Date());
			
			org.hibernate.Session s = HibernateUtil.openSession();
			OrganizacaoDAO organizacaoDAO = new OrganizacaoDAO(s, Organizacao.class);
    		organizacaoDAO.merge(this);
    		s.close();
    		return organizacao;
		}
		catch (Exception e) {
			return null;
		}	
	}
}