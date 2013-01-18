package com.despesas.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.Where;

import com.despesas.dao.DespesasDAO;
import com.funcionario.model.Funcionario;
import com.principal.helper.HibernateUtil;
import com.principal.helper.Session;
import com.vaadin.data.util.BeanItemContainer;

@Entity
@Table(name="despesas")
@Where(clause = "ativo = 1")
@SQLDelete(sql = "update despesas set ativo = 0 where id = ?")
@SuppressWarnings({"rawtypes", "unused"})
public class Despesas {

	@Id
	@GeneratedValue
	private Long id = null;
	
	@Column(name = "despesascentro", length=30)
	private String centroDeCusto = "";
	
	@Column(name = "despesasdestino", length=30)
	private String cidadeDestino = "";
	
	@Column(name = "despesasdatainicio", length=30)
	private Date dataInicio = null;
	
	@Column(name = "despesasdatafinal", length=30)
	private Date dataFinal = null;
	
	@Column(name = "despesasmotivo")
	private Boolean motivoDaViagemProspec = false;
	
	@Column(name = "despesasinstalacao")
	private Boolean motivoDaViagemInstalacao = false;
	
	@Column(name = "despesasimplantacao")
	private Boolean motivoDaViagemImplantacao = false;

	@Column(name = "despesastreinamento")
	private Boolean motivoDaViagemTreinamento = false;
	
	@Column(name = "despesasoutros")
	private Boolean motivoDaViagemOutros = false;
	
	@Column(name = "despesastextfieldoutros", length=30)
	private String outros = "";
	
	@Column(name = "despesasgasto")
	private BigDecimal totalGasto = null;
	
	@Column(name = "despesasrecebido")
	private BigDecimal adianRecebido = null;
	
	@Column(name = "despesassaldo")
	private BigDecimal saldo = null;
	
	@Column(name = "despesasdeducao")
	private BigDecimal deducaoGasto = null;
	
	@Column(name = "despesassaldofinal")
	private BigDecimal saldoFinal = null;
	
	@Column(name = "despesasbanco", length=20)
	private String banco = "";
	
	@Column(name = "despesasagencia", length=20)
	private String agencia = "";
	
	@Column(name = "despesasconta", length=20)
	private String nConta = "";
	
	@Column(name = "despesasdataliberacao", length=30)
	private Date dataLiberacao = null;
	
	@Column(name = "despesasnomeinterno", length=50)
	private String nomeInterno = "";
	
	@Column(name = "despesasstatus", length=15)
	private String status = "";
	
	@Column(name = "despesasobservacao", length=150)
	private String observacao = "";
	
	@Column(name = "despesasdatacriacao", length=30)
	private Date dataCriacao = null;
	
	@Column(name = "despesasdataupdate", length=30)
	private Date dataUpdate = null;
	
	@Column(name = "despesasdataaprovacao", length=30)
	private Date dataAprovacao = null;
	
	@Column(name = "ativo", length=1)
	private Boolean ativo = true;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@OneToMany(mappedBy = "despesas" , targetEntity = ItensDespesas.class, fetch = FetchType.EAGER, cascade={ CascadeType.ALL})
	private Collection<ItensDespesas> itensDespesa = new ArrayList<ItensDespesas>();
	
	public Despesas(){
		dataCriacao = new Date();
	}

	//GETTERS AND SETTERS
	
	public Despesas(Funcionario funcionario) {
		super();
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getCentroDeCusto() {
		return centroDeCusto;
	}
	public void setCentroDeCusto(String centroDeCusto) {
		this.centroDeCusto = centroDeCusto;
	}

	public String getCidadeDestino() {
		return cidadeDestino;
	}
	public void setCidadeDestino(String cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Boolean getMotivoDaViagemProspec() {
		return motivoDaViagemProspec;
	}
	public void setMotivoDaViagemProspec(Boolean motivoDaViagemProspec) {
		this.motivoDaViagemProspec = motivoDaViagemProspec;
	}

	public Boolean getMotivoDaViagemInstalacao() {
		return motivoDaViagemInstalacao;
	}
	public void setMotivoDaViagemInstalacao(Boolean motivoDaViagemInstalacao) {
		this.motivoDaViagemInstalacao = motivoDaViagemInstalacao;
	}

	public Boolean getMotivoDaViagemImplantacao() {
		return motivoDaViagemImplantacao;
	}
	public void setMotivoDaViagemImplantacao(Boolean motivoDaViagemImplantacao) {
		this.motivoDaViagemImplantacao = motivoDaViagemImplantacao;
	}

	public Boolean getMotivoDaViagemTreinamento() {
		return motivoDaViagemTreinamento;
	}
	public void setMotivoDaViagemTreinamento(Boolean motivoDaViagemTreinamento) {
		this.motivoDaViagemTreinamento = motivoDaViagemTreinamento;
	}

	public Boolean getMotivoDaViagemOutros() {
		return motivoDaViagemOutros;
	}
	public void setMotivoDaViagemOutros(Boolean motivoDaViagemOutros) {
		this.motivoDaViagemOutros = motivoDaViagemOutros;
	}

	public Collection<ItensDespesas> getItensDespesa() {
		return itensDespesa;
	}
	public void setItensDespesa(Collection<ItensDespesas> itensDespesa) {
		this.itensDespesa = itensDespesa;
	}

	public String getOutros() {
		return outros;
	}
	public void setOutros(String outros) {
		this.outros = outros;
	}

	public BigDecimal getTotalGasto() {
		if (totalGasto == null) {
			totalGasto = new BigDecimal(0.00);
		}
		return totalGasto;
	}
	public void setTotalGasto(BigDecimal totalGasto) {
		this.totalGasto = totalGasto;
	}

	public BigDecimal getAdianRecebido() {
		if (adianRecebido == null) {
			adianRecebido = new BigDecimal(0.00);
		}
		return adianRecebido;
	}
	public void setAdianRecebido(BigDecimal adianRecebido) {
		this.adianRecebido = adianRecebido;
	}

	public BigDecimal getSaldo() {
		if (saldo == null) {
			saldo = new BigDecimal(0.00);
		}
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getDeducaoGasto() {
		if (deducaoGasto == null) {
			deducaoGasto = new BigDecimal(0.00);
		}
		return deducaoGasto;
	}
	public void setDeducaoGasto(BigDecimal deducaoGasto) {
		this.deducaoGasto = deducaoGasto;
	}

	public BigDecimal getSaldoFinal() {
		if (saldoFinal == null) {
			saldoFinal = new BigDecimal(0.00);
		}
		return saldoFinal;
	}
	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getnConta() {
		return nConta;
	}
	public void setnConta(String nConta) {
		this.nConta = nConta;
	}

	public Date getDataLiberacao() {
		return dataLiberacao;
	}
	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public String getNomeInterno() {
		return nomeInterno;
	}
	public void setNomeInterno(String nomeInterno) {
		this.nomeInterno = nomeInterno;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}
	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}
	
	public void addDespesa(ItensDespesas itensDespesas){
			
		this.itensDespesa.add(itensDespesas);
	}
	
	@Override
	public String toString() {
		return "Despesas [id=" + id + ", funcionario=" + funcionario
				+ ", centroDeCusto=" + centroDeCusto + ", cidadeDestino="
				+ cidadeDestino + ", dataInicio=" + dataInicio + ", dataFinal="
				+ dataFinal + ", motivoDaViagemProspec="
				+ motivoDaViagemProspec + ", motivoDaViagemInstalacao="
				+ motivoDaViagemInstalacao + ", motivoDaViagemImplantacao="
				+ motivoDaViagemImplantacao + ", motivoDaViagemTreinamento="
				+ motivoDaViagemTreinamento + ", motivoDaViagemOutros="
				+ motivoDaViagemOutros + ", outros=" + outros + ", totalGasto="
				+ totalGasto + ", adianRecebido=" + adianRecebido + ", saldo="
				+ saldo + ", deducaoGasto=" + deducaoGasto + ", saldoFinal="
				+ saldoFinal + ", banco=" + banco + ", agencia=" + agencia
				+ ", nConta=" + nConta + ", dataLiberacao=" + dataLiberacao
				+ ", nomeInterno=" + nomeInterno + ", status=" + status
				+ ", observacao=" + observacao + "]";
	}
	
	public BeanItemContainer listaBens(){
		BeanItemContainer<Despesas>beans = new BeanItemContainer<Despesas>(Despesas.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		DespesasDAO despesasDAO = new DespesasDAO(s, Despesas.class);
	  
		List workouts = despesasDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Despesas wo = (Despesas) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	public static BeanItemContainer<DespesasInfo> listAll(){
		BeanItemContainer<DespesasInfo> listDespesa = new BeanItemContainer<DespesasInfo>(DespesasInfo.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		DespesasDAO despesasDAO = new DespesasDAO(s, Despesas.class);
		
		List workouts;
		
		workouts = despesasDAO.list();
			
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Despesas wo = (Despesas) iterator.next();
			
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	       	
			String dataApro;
			
			if(wo.getDataAprovacao() == null){
	       		dataApro ="N�o Aprovado"; 
	       	}
	       	else {
	       	 dataApro =	df.format(wo.getDataAprovacao());
	       	}	
		    /*DespesasInfo de = new DespesasInfo(wo.getId(), wo.getFuncionario().getNome(), wo.getStatus(), df.format(wo.getDataInicio()), dataApro);
			System.out.println(de.toString());
            listDespesa.addBean(de);*/
		}
		 //s.close();
		 return listDespesa;
	}
	
	public BeanItemContainer listaItens(){
		BeanItemContainer<ItensDespesas> beans = new BeanItemContainer<ItensDespesas>(ItensDespesas.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		DespesasDAO despesasDAO = new DespesasDAO(s, Despesas.class);
		List workouts =  (List) despesasDAO.pesquisaDespesasById(id).getItensDespesa();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			ItensDespesas wo = (ItensDespesas) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	public static Despesas pesquisaDespesaID(Long id){
		
		Despesas despesas = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	DespesasDAO despesasDAO = new DespesasDAO(s, Despesas.class);
	    	s.beginTransaction().begin();
	        despesas = despesasDAO.buscaDespesas(id);
	    	s.close();
	    	return despesas;
	    }
		catch (Exception e) {
			return null;
		}
	}
	
	public boolean salvar(){
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	DespesasDAO despesasDAO = new DespesasDAO(s, Despesas.class);
	    	s.beginTransaction().begin();
	        despesasDAO.save(this);
	        s.beginTransaction().commit();
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean remover(){
		
		Despesas despesas = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	DespesasDAO despesasDAO = new DespesasDAO(s, Despesas.class);
	    	s.beginTransaction().begin();
	        despesasDAO.delete(this);
	        s.beginTransaction().commit();
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}

	public boolean alterar(){
		
		dataUpdate = new Date();
		
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			DespesasDAO despesasDAO = new DespesasDAO(s, Despesas.class);
			s.beginTransaction().begin();
			despesasDAO.merge(this);
       		s.beginTransaction().commit();
       		s.close();
       		return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}