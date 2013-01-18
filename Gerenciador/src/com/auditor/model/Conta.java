package com.auditor.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.ContasDAO;
import com.auditor.model.contato.PrestadoraTelefonia;
import com.auditor.model.contato.Telefone;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="conta")
public class Conta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "conta_id")
	private Long id;
	
	@Column(name = "conta_data")
	private Date data = new Date();
	
	@Column(name = "conta_referencia")
	private Date referencia;
	
	@Column(name = "conta_vencimento")
	private Date vencimento;
	
	@Column(name = "conta_pagamento")
	private Date pagamento;
	
	@Column(name = "conta_valor")
	private Double valor;
	
	@Column(name = "conta_multa")
	private Double multa;
	
	@Column(name = "conta_valorPago")
	private Double valorPago;
	
	@Column(name = "conta_usuario")
	private String usuario;
	
	@OneToOne
	@JoinColumn(name = "prestadoracodigo")
	public PrestadoraTelefonia prestadora;
	
	@ManyToOne
	@JoinColumn(name = "telefonenumero")
	public Telefone telefone;
	
	@OneToMany(mappedBy = "conta", targetEntity = ItemConta.class, fetch = FetchType.LAZY, cascade={ CascadeType.ALL})
	private Collection<ItemConta> itemConta = new ArrayList<ItemConta>();
	
	public Conta(){}

	//GETTER AND SETTER

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	public Date getReferencia() {
		return referencia;
	}
	public void setReferencia(Date referencia) {
		this.referencia = referencia;
	}

	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getPagamento() {
		return pagamento;
	}
	public void setPagamento(Date pagamento) {
		this.pagamento = pagamento;
	}

	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getMulta() {
		return multa;
	}
	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public PrestadoraTelefonia getPrestadora() {
		return prestadora;
	}
	public void setPrestadora(PrestadoraTelefonia prestadora) {
		this.prestadora = prestadora;
	}
	
	public Collection<ItemConta> getItemConta() {
		return itemConta;
	}
	public void setItemConta(Collection<ItemConta> itemConta) {
		this.itemConta = itemConta;
	}

	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	//METODOS
	
	@Override
	public String toString() {
		return "Conta [id=" + id + ", data=" + data + ", referencia="
				+ referencia + ", vencimento=" + vencimento + ", pagamento="
				+ pagamento + ", valor=" + valor + ", multa=" + multa
				+ ", valorPago=" + valorPago + ", usuario=" + usuario
				+ ", prestadora=" + prestadora + ", telefone=" + telefone + "]";
	}
	
	

	public Conta pesquisaContaID(){
		
		Conta contas = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ContasDAO contasDAO = new ContasDAO(s, Conta.class);
	        contas = contasDAO.buscaContas(id);
	    	s.close();
	    	return contas;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Conta pesquisaContaID(Long id){
		
		Conta contas = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ContasDAO contasDAO = new ContasDAO(s, Conta.class);
	        contas = contasDAO.buscaContas(id);
	    	s.close();
	    	return contas;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer listaBens(){
		BeanItemContainer <Conta>beans = new BeanItemContainer<Conta>(Conta.class);
	
		org.hibernate.Session s = HibernateUtil.openSession();
		ContasDAO contasDAO = new ContasDAO(s, Conta.class);
  
		List workouts = contasDAO.list();
	
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Conta wo = (Conta) iterator.next();
			//wo.setMedia(null);
			beans.addBean(wo);
		}	
		//s.close();
		return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public List listItemConta(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ContasDAO contasDAO = new ContasDAO(s, Conta.class);
		List workouts =  (List) contasDAO.pesquisaContaID(id).getItemConta();
		//s.close();
		return workouts;
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		Conta contas = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ContasDAO contasDAO = new ContasDAO(s, Conta.class);
	        contasDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		 
		Conta contas = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ContasDAO contasDAO = new ContasDAO(s, Conta.class);
	        contasDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ContasDAO contasDAO = new ContasDAO(s, Conta.class);
	        contasDAO.merge(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public HashMap<String, Double> pesquisaTipoServico(){
		
		try{
	        return ContasDAO.pesquisaGrafico("SELECT tipo_descricao, SUM(itemconta_valor) AS valor_total "+
						"FROM itemconta INNER JOIN tiposervico ON itemconta.tipo_id = tiposervico.tipo_id "+
						"WHERE itemconta.conta_id = 4 "+
						"GROUP BY itemconta.tipo_id");
	    }
		catch (Exception e) {
			return new HashMap<String, Double>();
		}	
	}
	
	public HashMap<String, Double> pesquisaItens(){
		
		try{
	        return ContasDAO.pesquisaGrafico("SELECT itemconta.tipo_id, COUNT(itemconta_id) "+
	        			"FROM itemconta INNER JOIN tiposervico ON itemconta.tipo_id = tiposervico.tipo_id "+
	        			"WHERE itemconta.conta_id = 4 "+
	        			"GROUP BY itemconta.tipo_id");
	    }
		catch (Exception e) {
			return new HashMap<String, Double>();
		}	
	}
}
