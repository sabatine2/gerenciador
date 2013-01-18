package com.auditor.model.contato;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.TelefoneDAO;
import com.auditor.model.Conta;
import com.auditor.model.Departamento;
import com.auditor.model.ReferenciaServico;
import com.dispositivo.model.Dispositivo;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="telefone")
public class Telefone {

	@Id
	@Column(name = "telefonenumero")
	private Long dddNumero; //ddd+telefone
	
	@Column(name = "telefonetipo")
	private String tipo; //interno ou externo
	
	@Column(name = "telefoneFinalidade")
	private String finalidade; //interno ou externo
	
	@Column(name = "telefoneramal")
	private String ramal = "";
	
	@Temporal(TemporalType.DATE)
	@Column(name="telefoneativacao")
	private Date ativacao;

	@Column(name = "telefoneobs", nullable=false, length=255)
	private String obs = "";
	
	@ManyToOne//(cascade={ CascadeType.ALL})
	@JoinColumn( name = "referenciaid")
	private ReferenciaServico telefoneReferencia;
	
	@ManyToOne
	@JoinColumn( name = "departamentoid")
	private Departamento departamento;

	@ManyToOne//(cascade={ CascadeType.ALL})
	@JoinColumn( name = "prestadoraid")
	private PrestadoraTelefonia prestadora;
	
	@OneToMany(mappedBy = "telefone", targetEntity = Conta.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	private Collection<Conta> conta = new ArrayList<Conta>();

	@Column( name = "telefone_tipodispositivo" )
	private String tipoDispositivo;
	
	@ManyToOne
	@JoinColumn(name = "dispositivoid")
	private Dispositivo dispositivo;
	
	private Date dataCriacao = new Date();
	
	private Date dataUpdate = null;
	
	public Telefone(){}

	//GETTERS AND SETTERS
	
	public Long getDddNumero() {
		return dddNumero;
	}
	public void setDddNumero(Long dddNumero) {
		this.dddNumero = dddNumero;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getFinalidade() {
		return finalidade;
	}
	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public Date getAtivacao() {
		return ativacao;
	}
	public void setAtivacao(Date ativacao) {
		this.ativacao = ativacao;
	}

	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}

	public ReferenciaServico getTelefoneReferencia() {
		return telefoneReferencia;
	}
	public void setTelefoneReferencia(ReferenciaServico telefoneReferencia) {
		this.telefoneReferencia = telefoneReferencia;
	}

	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public Collection<Conta> getConta() {
		return conta;
	}
	public void setConta(Collection<Conta> conta) {
		this.conta = conta;
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

	public Dispositivo getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getTipoDispositivo() {
		return tipoDispositivo;
	}
	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public PrestadoraTelefonia getPrestadora() {
		return prestadora;
	}
	public void setPrestadora(PrestadoraTelefonia prestadora) {
		this.prestadora = prestadora;
	}
	
	//METODOS
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBens(){
		BeanItemContainer<Telefone>beans = new BeanItemContainer<Telefone>(Telefone.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		TelefoneDAO telefoneDAO = new TelefoneDAO(s, Telefone.class);
	  
		List workouts = telefoneDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Telefone wo = (Telefone) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public  BeanItemContainer ListaBensConta(){
		BeanItemContainer<Conta>beans = new BeanItemContainer<Conta>(Conta.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		TelefoneDAO telefoneDAO = new TelefoneDAO(s, Telefone.class);
		List workouts =  (List) telefoneDAO.pesquisaTelefoneId(dddNumero).getConta();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Conta wo = (Conta) iterator.next();
			beans.addBean(wo);
       	}	
		//s.close();
		return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public List listBensConta(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		TelefoneDAO telefoneDAO = new TelefoneDAO(s, Telefone.class);
		List workouts =  (List) telefoneDAO.pesquisaTelefoneId(dddNumero).getConta();
		//s.close();
		return workouts;
	}
	
	public Telefone pesquisaTelefoneID(){
		
		Telefone telefone = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TelefoneDAO telefoneDAO = new TelefoneDAO(s, Telefone.class);
	        telefone = telefoneDAO.buscaTelefone(dddNumero);
	    	s.close();
	    	return telefone;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Telefone pesquisaTelefoneID(Long id){
		
		Telefone telefone = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TelefoneDAO telefoneDAO = new TelefoneDAO(s, Telefone.class);
	        telefone = telefoneDAO.buscaTelefone(id);
	    	s.close();
	    	return telefone;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public ArrayList<Telefone> departamentoID(){
		
		ArrayList<Telefone> telefone = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TelefoneDAO telefoneDAO = new TelefoneDAO(s, Telefone.class);
	    	System.out.println(telefone);
	        telefone = (ArrayList<Telefone>) telefoneDAO.pesquisaDepartamento(this.getDepartamento().getId());
	    	s.close();
	    	return telefone;
	    }
		catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		Telefone telefone = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TelefoneDAO telefoneDAO = new TelefoneDAO(s, Telefone.class);
	        telefoneDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		Telefone telefone = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	TelefoneDAO telefoneDAO = new TelefoneDAO(s, Telefone.class);
	        telefoneDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
	
		Telefone telefone = null;
	
		try{
			setDataUpdate(new Date());
			
			org.hibernate.Session s = HibernateUtil.openSession();
			TelefoneDAO telefoneDAO = new TelefoneDAO(s, Telefone.class);
			telefoneDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public void select(){
		
		Connection conn;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/gerenciadorteste?user=root&password=");
			Statement stm = conn.createStatement();  
			ResultSet rs = stm.executeQuery("SELECT conta_id, SUM(itemconta_valor) as valor_total "+
											"FROM itemconta "+
											"GROUP BY conta_id"); 
			
			while (rs.next()) {  
				
			    long tipo_id = rs.getLong("conta_id");  
			    double itemconta_valor = rs.getDouble("valor_total");			   
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
}