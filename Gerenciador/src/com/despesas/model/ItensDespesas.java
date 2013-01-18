package com.despesas.model;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.despesas.dao.ItensDespesasDAO;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;

@Entity
@Table(name="itemDespesas")
public class ItensDespesas {
	
	@Id
	@GeneratedValue
	private Long idi;
	
	@Column (name = "itensdespesasnome", nullable=false, length=30)
	private String nome = "";
	
	@Column (name = "itensdespesasvalor")
	private BigDecimal valor = null;
	
	@Column (name = "itensdespesasobservacao")
	private String observacao = "";
	
	@Column (name = "itensdespesasdeducao")
	private BigDecimal deducao = null;
	
	@Column (name = "imagem")
	private String imagem = "";
	
	@Column(name = "ativo")
	private Boolean ativo = true;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Despesas despesas;
	
	public ItensDespesas(){}
	
	//GETTERS AND SETTERS
	
	public Long getId() {
		return idi;
	}
	public void setId(Long id) {
		this.idi = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObervacao() {
		return observacao;
	}
	public void setObervacao(String obervacao) {
		this.observacao = obervacao;
	}
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getDeducao() {
		return deducao;
	}
	public void setDeducao(BigDecimal deducao) {
		this.deducao = deducao;
	}

	public Despesas getDespesas() {
		return despesas;
	}
	public void setDespesas(Despesas despesas) {
		this.despesas = despesas;
	}

	//METODOS
	
	@SuppressWarnings("rawtypes")
	public BeanItemContainer listaBens(){
		BeanItemContainer<ItensDespesas>beans = new BeanItemContainer<ItensDespesas>(ItensDespesas.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ItensDespesasDAO itensDespesasDAO = new ItensDespesasDAO(s, ItensDespesas.class);
	  
		List workouts = itensDespesasDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			ItensDespesas wo = (ItensDespesas) iterator.next();
			beans.addBean(wo);
       	}	
		 s.close();
		 return beans;
	}
	
	public boolean remover(){
		
		//try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ItensDespesasDAO itensDespesasDAO = new ItensDespesasDAO(s, ItensDespesas.class);
	    	s.beginTransaction().begin();
	        itensDespesasDAO.delete(this);
	        System.out.println("Removendo Item");
	        s.beginTransaction().commit();
	    	s.close();
	    	return true;
	    /*}
		catch (Exception e) {
			return false;
		}*/	
	}
	
	public boolean alterar(){
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ItensDespesasDAO itensDespesasDAO = new ItensDespesasDAO(s, ItensDespesas.class);
	    	s.beginTransaction().begin();
	        itensDespesasDAO.merge(this);
	        s.beginTransaction().commit();
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
		    return false;
		}	
	}
	
	public ItensDespesas salvar(){
		
		ItensDespesas itensDespesas = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ItensDespesasDAO itensDespesasDAO = new ItensDespesasDAO(s, ItensDespesas.class);
	    	s.beginTransaction().begin();
	        itensDespesasDAO.save(this);
	        s.beginTransaction().commit();
	    	s.close();
	    	return itensDespesas;
	    }
		catch (Exception e) {
			return null;
		}	
	}
}