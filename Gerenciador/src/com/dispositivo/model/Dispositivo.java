package com.dispositivo.model;

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
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.DispositivoDAO;
import com.auditor.model.Departamento;
import com.auditor.model.contato.Telefone;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@Entity
@Table(name="dispositivo")
public class Dispositivo {

	@Id
	@GeneratedValue
	@Column( name = "dispositivoid" )
	private Long id;
	
	@Column(name = "datacriacao")
	private Date dataCriacao;
	
	@Column(name = "dataupdate")
	private Date dataUpdate = null;
	
	@Column(name = "dataaquisicao")
	private Date dataAquisicao = null;
	
	@Column(name = "numeromodelo")
	private String numeroModelo = "";
	
	@Column(name = "sistemaoperacional")
	private String sistemaOperacional = "";
	
	@Column(name = "status")
	private String status; //ATIVO OU INATIVO
	
	@Column(name = "marca")
	private String marca = "";
	
	@Column(name = "tresg")
	private String tresG; //SIM OU NAO
	
	@Column(name = "wifi")
	private String wifi; //SIM OU NAO
	
	@ManyToOne
	@JoinColumn(name = "departamentoid")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "dispositivo", targetEntity = Telefone.class, fetch = FetchType.LAZY, cascade={CascadeType.MERGE})
    private Collection<Telefone> numeroTelefone = new ArrayList<Telefone>();

    //GETTERS AND SETTER
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getNumeroModelo() {
		return numeroModelo;
	}

	public void setNumeroModelo(String numeroModelo) {
		this.numeroModelo = numeroModelo;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTresG() {
		return tresG;
	}

	public void setTresG(String tresG) {
		this.tresG = tresG;
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Collection<Telefone> getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(Collection<Telefone> numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	
	//METODOS

	@SuppressWarnings("unused")
	public static List<Dispositivo> ListDispositivos(){
		BeanItemContainer<Dispositivo> beans = new BeanItemContainer<Dispositivo>(Dispositivo.class);
	
		org.hibernate.Session s = HibernateUtil.openSession();
		DispositivoDAO dispositivoDAO = new DispositivoDAO(s, Dispositivo.class);
		List<Dispositivo> dispositivo = dispositivoDAO.list();
		//s.close();
		return dispositivo;
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBens(){
		BeanItemContainer<Dispositivo> beans = new BeanItemContainer<Dispositivo>(Dispositivo.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		DispositivoDAO dispositivoDAO = new DispositivoDAO(s, Dispositivo.class);
	  
		List workouts = dispositivoDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Dispositivo wo = (Dispositivo) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}

	@SuppressWarnings("rawtypes")
	public  BeanItemContainer ListaBensTel(){
		BeanItemContainer<Telefone>beans = new BeanItemContainer<Telefone>(Telefone.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		DispositivoDAO dispositivoDAO = new DispositivoDAO(s, Dispositivo.class);
		List workouts =  (List) dispositivoDAO.pesquisaDispositivoId(id).getNumeroTelefone();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Telefone wo = (Telefone) iterator.next();
			beans.addBean(wo);
       	}	
		//s.close();
		return beans;
	}
	
	public Dispositivo pesquisaDispositivoID(){
		
		Dispositivo dispositivo = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	DispositivoDAO dispositivoDAO = new DispositivoDAO(s, Dispositivo.class);
	        dispositivo = dispositivoDAO.buscaDispositivo(id);
	    	s.close();
	    	return dispositivo;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Dispositivo pesquisaDispositivoID(Long id){
		
		Dispositivo dispositivo = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	DispositivoDAO dispositivoDAO = new DispositivoDAO(s, Dispositivo.class);
	        dispositivo = dispositivoDAO.buscaDispositivo(id);
	    	s.close();
	    	return dispositivo;
	    }
		catch (Exception e) {
			return null;
		}	
	}

	@SuppressWarnings("unused")
	public boolean salvar(){
		
		Dispositivo dispositivo = null;
		
		try{
			setDataCriacao(new Date());
			
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	DispositivoDAO dispositivoDAO = new DispositivoDAO(s, Dispositivo.class);
	        dispositivoDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			System.out.println("Dispositivo: "+e.getMessage());
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		Dispositivo dispositivo = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	DispositivoDAO dispositivoDAO = new DispositivoDAO(s, Dispositivo.class);
	        dispositivoDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			System.out.println("Dispositivo: "+e);
			return false;
		}	
	}	
	
	@SuppressWarnings("unused")
	public boolean alterar(){
		
		Dispositivo dispositivo = null;
		
		try{
			setDataUpdate(new Date());
			
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	DispositivoDAO dispositivoDAO = new DispositivoDAO(s, Dispositivo.class);
	        dispositivoDAO.merge(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			System.out.println("Dispositivo: "+e);
			return false;
		}	
	}
}