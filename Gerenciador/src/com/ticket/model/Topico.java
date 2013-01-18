package com.ticket.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.model.Conta;
import com.principal.helper.HibernateUtil;
import com.ticket.dao.TopicoDAO;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@Entity
@Table(name = "topico")
public class Topico {

	@Id
	@GeneratedValue
	@Column(name="topico_id")
	private Long id;
	
	@Column(name="topico_nome")
	private String nome = "";
	
	@Column(name="topico_status")
	private String status;
	
	@Column(name="topico_descricao")
	private String descricao = "";
	
	@Column(name="topico_relevancia")
	private String relevancia;
	
	@Column(name="topico_prioridade")
	private String prioridade;
	
	@Column(name="topico_custo")
	private BigDecimal custo;
	
	@Column(name="topico_observacao")
	private String observacao = "";
	
	@Column(name="topico_dataCriacao")
	private Date dataCriacao;
	
	@Column(name="topico_dataUpdate")
	private Date dataUpdate = null;

	@Column(name = "topico_pchave")
	public String palavraChave = "";
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRelevancia() {
		return relevancia;
	}

	public void setRelevancia(String relevancia) {
		this.relevancia = relevancia;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
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

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	//METODOS
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer listaBeans(){
		BeanItemContainer<Topico>beans = new BeanItemContainer<Topico>(Topico.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		TopicoDAO ordemServicoDAO = new TopicoDAO(s, Topico.class);
	  
		beans.addAll(ordemServicoDAO.list());
		return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer listaBeansAtivos(){
		BeanItemContainer<Topico>beans = new BeanItemContainer<Topico>(Topico.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		TopicoDAO ordemServicoDAO = new TopicoDAO(s, Topico.class);
	  
		beans.addAll(ordemServicoDAO.listaTopicosStatus("Ativo"));
		return beans;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static List listaTopico(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		TopicoDAO ordemServicoDAO = new TopicoDAO(s, Topico.class);
	  
		List workouts = ordemServicoDAO.list();
		
		 //s.close();
		 return workouts;
	}
	
	public Topico pesquisaOrdemID(){
		
		Topico ordemServico = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TopicoDAO ordemServicoDAO = new TopicoDAO(s, Topico.class);
	        ordemServico = ordemServicoDAO.buscaTopicos(id);
	    	s.close();
	    	return ordemServico;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Topico pesquisaOrdemID(Long id){
		
		Topico ordemServico = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TopicoDAO ordemServicoDAO = new TopicoDAO(s, Topico.class);
	    	ordemServico = ordemServicoDAO.buscaTopicos(id);
	    	s.close();
	    	return ordemServico;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		Topico ordemServico = null;
		
		try{
			setDataCriacao(new Date());
			
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TopicoDAO ordermServicoDAO = new TopicoDAO(s, Conta.class);
	        ordermServicoDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		 
		Topico ordemServico = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TopicoDAO ordermServicoDAO = new TopicoDAO(s, Conta.class);
	        ordermServicoDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
		
		Topico ordemServico = null;
		
		try{
			setDataUpdate(new Date());
			
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TopicoDAO ordermServicoDAO = new TopicoDAO(s, Conta.class);
	        ordermServicoDAO.merge(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
}