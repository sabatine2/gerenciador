package com.auditor.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.ItemContaDAO;
import com.auditor.model.contato.PrestadoraTelefonia;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="itemconta")
public class ItemConta {

	@Id
	@GeneratedValue
	@Column(name="itemconta_id")
	private Long id;
	
	@Column(name="itemconta_sequencia")
	private String sequencia;
	
	@OneToOne
	@JoinColumn(name = "tipo_id")
	private TipoServico tipo;
	
	@Column(name="itemconta_datahotainicio")
	private Date dataHoraInicio;
	
	@Column(name="itemconta_datahorafim")
	private Date dataHoraFim;
	
	@Column(name="itemconta_terminal")
	private String terminal;
	
	@Column(name="itemconta_codservico")
	private String codServico;

	@Column(name="itemconta_local")
	private String local;
	
	@Column(name="itemconta_complemento")
	private String complemento;
	
	@Column(name="itemconta_pais")
	private String pais;
	
	@Column(name="itemconta_quantidade")
	private String quantidade;
	
	@OneToOne
	@JoinColumn(name = "medida_id")
	private Medida medida;
	
	@Column(name="itemconta_valor")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	@OneToOne
	@JoinColumn(name = "prestadoracodigo")
	private PrestadoraTelefonia operadora;
	
	public ItemConta(){}

	//GETTERS AND SETTERS
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getSequencia() {
		return sequencia;
	}
	public void setSequencia(String sequencia) {
		this.sequencia = sequencia;
	}

	public TipoServico getTipo() {
		return tipo;
	}
	public void setTipo(TipoServico tipo) {
		this.tipo = tipo;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public PrestadoraTelefonia getOperadora() {
		return operadora;
	}
	public void setOperadora(PrestadoraTelefonia operadora) {
		this.operadora = operadora;
	}

	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getCodServico() {
		return codServico;
	}
	public void setCodServico(String codServico) {
		this.codServico = codServico;
	}

	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}

	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Medida getMedida() {
		return medida;
	}
	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	//METODOS
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBens(){
		BeanItemContainer<ItemConta>beans = new BeanItemContainer<ItemConta>(ItemConta.class);
		
		org.hibernate.Session s = HibernateUtil.currentSession();
		ItemContaDAO itemContaDAO = new ItemContaDAO(s, ItemConta.class);
	  
		List workouts = itemContaDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			ItemConta wo = (ItemConta) iterator.next();
			//wo.setTelefone(null);
			beans.addBean(wo);
       	}	
		//s.close();
		return beans;
	}
	
	public boolean salvar(){
		
		try{
			org.hibernate.Session s = HibernateUtil.currentSession();
	    	ItemContaDAO itemContaDAO = new ItemContaDAO(s, ItemConta.class);
	    	itemContaDAO.save(this);
	    	
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean remover(){
		
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
	    	ItemContaDAO itemContaDAO = new ItemContaDAO(s, ItemConta.class);
	    	itemContaDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public static void removeAll(Long id){
		
		try{
			org.hibernate.Session s = HibernateUtil.currentSession();
			ItemContaDAO itemContaDAO = new ItemContaDAO(s, ItemConta.class);
			itemContaDAO.removeAll(id);
		}
		catch (Exception e) {
			System.out.println("ERRO_REMOVEALL: "+e.getMessage());
		}
	}
}