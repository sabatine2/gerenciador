package com.ticket.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import com.auditor.model.Departamento;
import com.contato.model.Contato;
import com.ticket.dao.OrdemDAO;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@Entity
@Table(name="ordem")
public class Ordem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ordemid")
	private Long   id;
	
	@Column(name = "ordemID")
	private Long   idOrdem;
	
	@Column(name = "ordemdatacriacao")
	private Date dataCriacao = new Date();
	
	@Column(name = "ordemdataupdate")
	private Date dataUpdate = new Date(); 
	
	@Column(name = "ordemdataconclusao")
	private Date dataConclusao;
	
	@Column(name = "ordemdatareaberto")
	private Date dataReaberto; 
	
	@Column(name = "ordemdataultimamensagem")
	private Date dataUltimaMensagem = new Date();
	
	@Column(name = "ordemdataultimaresposta")
	private Date dataUltimaResposta = new Date();
	
	@Column(name = "ordemassunto", nullable=false, length=100)
	private String assunto = "";

	@Column(name = "ordemfonte", nullable=false, length=100)
	private String fonte ="";

	@Column(name = "ordemstatus", nullable=false, length=100)
	private String status =""; //aberto concluido, em andamento
	
	@ManyToOne
	@JoinColumn( name = "departamentoid")
    private Departamento departamento;

	@ManyToOne
	@JoinColumn( name = "contatoid")
    private Contato contato = null;

	
	@OneToOne
	private Prioridade prioridade;
	
	@OneToMany(mappedBy = "ordem", targetEntity = Mensagem.class, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Collection<Mensagem> mensagem = new ArrayList<Mensagem>();
	
	@OneToMany(mappedBy = "ordem", targetEntity = Nota.class, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Collection<Nota> nota = new ArrayList<Nota>();
		
	public Ordem() {
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
	 * @return the idOrdem
	 */
	public Long getIdOrdem() {
		return idOrdem;
	}

	/**
	 * @param idOrdem the idOrdem to set
	 */
	public void setIdOrdem(Long idOrdem) {
		this.idOrdem = idOrdem;
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
	 * @return the dataConclusao
	 */
	public Date getDataConclusao() {
		return dataConclusao;
	}

	/**
	 * @param dataConclusao the dataConclusao to set
	 */
	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	/**
	 * @return the dataReaberto
	 */
	public Date getDataReaberto() {
		return dataReaberto;
	}

	/**
	 * @param dataReaberto the dataReaberto to set
	 */
	public void setDataReaberto(Date dataReaberto) {
		this.dataReaberto = dataReaberto;
	}

	/**
	 * @return the dataUltimaMensagem
	 */
	public Date getDataUltimaMensagem() {
		return dataUltimaMensagem;
	}

	/**
	 * @param dataUltimaMensagem the dataUltimaMensagem to set
	 */
	public void setDataUltimaMensagem(Date dataUltimaMensagem) {
		this.dataUltimaMensagem = dataUltimaMensagem;
	}

	/**
	 * @return the dataUltimaResposta
	 */
	public Date getDataUltimaResposta() {
		return dataUltimaResposta;
	}

	/**
	 * @param dataUltimaResposta the dataUltimaResposta to set
	 */
	public void setDataUltimaResposta(Date dataUltimaResposta) {
		this.dataUltimaResposta = dataUltimaResposta;
	}

	/**
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
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
	 * @return the departamento
	 */
	public Departamento getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the prioridade
	 */
	public Prioridade getPrioridade() {
		return prioridade;
	}

	/**
	 * @param prioridade the prioridade to set
	 */
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	/**
	 * @return the mensagems
	 */
	public Collection<Mensagem> getMensagem() {
		if(mensagem == null){
			mensagem = new ArrayList<Mensagem>();
		}
		return mensagem;
	}

	/**
	 * @param mensagems the mensagems to set
	 */
	public void setMensagem(Collection<Mensagem> mensagems) {
		this.mensagem = mensagems;
	}

	/**
	 * @return the notas
	 */
	public Collection<Nota> getNota() {
		return nota;
	}

	/**
	 * @param notas the notas to set
	 */
	public void setNota(Collection<Nota> nota) {
		this.nota = nota;
	}

	/**
	 * @return the contato
	 */
	public Contato getContato() {
		if(contato == null){
			contato = new Contato();
		}
		return contato;
	}

	/**
	 * @param contato the contato to set
	 */
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public void insereMensagem(Mensagem mensagem){
		getMensagem().add(mensagem);
	}
	
	public static BeanItemContainer<Ordem> listaBeans(org.hibernate.Session s){
		
		BeanItemContainer<Ordem> beans = new BeanItemContainer<Ordem>(Ordem.class);
		
		OrdemDAO ordemDAO = new OrdemDAO(s, Ordem.class);
	    beans.addAll( ordemDAO.list());
		
		return beans;
	}
	
	public static BeanItemContainer<Ordem> listaBeans(org.hibernate.Session s, Departamento departamento){
		
		BeanItemContainer<Ordem> beans = new BeanItemContainer<Ordem>(Ordem.class);
		
		OrdemDAO ordemDAO = new OrdemDAO(s, Ordem.class);
	    beans.addAll( ordemDAO.listaOrdemDepartamento(departamento));
		
		return beans;
	}
	
	public static BeanItemContainer<Ordem> listaBeansStatus(org.hibernate.Session s, String status){
		
		BeanItemContainer<Ordem> beans = new BeanItemContainer<Ordem>(Ordem.class);
		
		OrdemDAO ordemDAO = new OrdemDAO(s, Ordem.class);
	    beans.addAll( ordemDAO.listaOrdemStatus("Aberto"));
		
		return beans;
	}
	
	public static Ordem pesquisaOrdemID(Long id){
		Ordem ordem = null;
		try{
		  	org.hibernate.Session s = HibernateUtil.currentSession();
	    	OrdemDAO ordemDAO = new OrdemDAO(s, Ordem.class);
	    	ordem = ordemDAO.buscaOrdem(id);
	    	ordemDAO = null;
	    	return ordem;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		   
		boolean result;
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	OrdemDAO ordemDAO = new OrdemDAO(s, Ordem.class);
		  	result = ordemDAO.save(this);
		  	s.close();
		  	s = null;
			ordemDAO = null;
			return result;
	}
	
	public boolean salvarOuAlterar(){
		
		try{
			org.hibernate.Session s = HibernateUtil.currentSession();
			OrdemDAO ordemDAO = new OrdemDAO(s, Ordem.class);
			return ordemDAO.saveOrMerge(this);
		    }
			catch (Exception e) {
				return false;
			}	
		}
	
	public boolean remover(){
		
		boolean result = false;
		
		try{
			org.hibernate.Session s = HibernateUtil.currentSession();
			OrdemDAO ordemDAO = new OrdemDAO(s, Ordem.class);	
		  	result = ordemDAO.delete(this);
			return result;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		  
		    boolean result;
		  	
		    org.hibernate.Session s = HibernateUtil.openSession();
		  	OrdemDAO ordemDAO = new OrdemDAO(s, Ordem.class);
			result = ordemDAO.merge(this);
			s.close();
			s = null;
			ordemDAO = null;
			return result;
	
	}
	
	public boolean update(){
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	OrdemDAO ordemDAO = new OrdemDAO(s, Ordem.class);
		  	ordemDAO.update(this);
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
		return "Ordem [id=" + id + ", idOrdem=" + idOrdem + ", dataCriacao="
				+ dataCriacao + ", dataUpdate=" + dataUpdate
				+ ", dataConclusao=" + dataConclusao + ", dataReaberto="
				+ dataReaberto + ", dataUltimaMensagem=" + dataUltimaMensagem
				+ ", dataUltimaResposta=" + dataUltimaResposta + ", assunto="
				+ assunto + ", fonte=" + fonte + ", status=" + status
				+ ", departamento=" + departamento + ", prioridade="
				+ prioridade + ", mensagems="
				+ mensagem + ", notas=" + nota + " contato=" + contato + "]";
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
		if (!(obj instanceof Ordem))
			return false;
		Ordem other = (Ordem) obj;
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
