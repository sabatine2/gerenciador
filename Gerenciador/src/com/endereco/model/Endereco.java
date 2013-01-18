package com.endereco.model;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.endereco.dao.EnderecoDAO;
import com.googlemaps.GeocodedLocation;
import com.googlemaps.GeocodingException;
import com.googlemaps.GoogleGeocoder;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@Entity
@Table(name="endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "enderecoid")
	private Long   id;

	@Column(name="enderecodatacriacao")
	private Date dataCriacao = new Date();	//interno
	
	@Column(name="enderecodataUpdate")
	private Date dataUpdate = null;	//interno
	
	@Column(name="enderecotipo")
	private String tipo; //{residencial, comercial}
	
	@Column(name="enderecostatus")
	private String status; //{ativo, inativo}
	
	@Column(name="enderecologradouro")
	private String logradouro = ""; 
	
	@Column(name="endereconumero")
	private String numero = ""; 
	
	@Column(name="enderecocomplemento")
	private String complemento = ""; 
	
	@Column(name="enderecobairro")
	private String bairro = ""; 
	
	@Column(name="enderecocidade")
	private String cidade = ""; 
	
	@Column(name="enderecoestado")
	private String estado; 
	
	@Column(name="enderecoCEP")
	private String cep = "";
	
	@Column(name="enderecoObs")
	private String obs = ""; //{ex inativo pq mudou }
	
	@Column(name="enderecolatitude")
	private Double latitude;
	
	@Column(name="enderecolongitude")
	private Double longitude;
	
	@Column(name="enderecoaltura")
	private String altura = "";
	
	@Transient
	private String enderecoCompleto;
	
	public Endereco(){
		
	}

	//GETTERS AND SETTERS
	
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

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}
	 
	//METODOS

	public static Endereco pesquisaId(Long id){
		
		Endereco endereco = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	com.endereco.dao.EnderecoDAO enderecoAuditorDAO = new EnderecoDAO(s, Endereco.class);
	    	endereco = enderecoAuditorDAO.buscaEnderecoAuditor(id);
	    	s.close();
	    	return endereco;
	    }
		catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer ListaBens(){
		BeanItemContainer <Endereco>beans = new BeanItemContainer<Endereco>(Endereco.class);
	
		org.hibernate.Session s = HibernateUtil.openSession();
		EnderecoDAO enderecoAuditorDAO = new EnderecoDAO(s, Endereco.class);
  
		List workouts = enderecoAuditorDAO.list();
	
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Endereco wo = (Endereco) iterator.next();
			wo.setEnderecoCompleto(wo.getLogradouro()+", "+wo.getNumero()+", "+wo.getBairro()+", "+wo.getCep()+", "+wo.getCidade()+", "+wo.getEstado());
			beans.addBean(wo);
		}	
		s.close();
		return beans;
	}
	
	public Endereco pesquisaEnderecoAuditorID(){
		
		Endereco enderecoAuditor = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.currentSession();
	    	EnderecoDAO enderecoAuditorDAO = new EnderecoDAO(s, Endereco.class);
	    	enderecoAuditor = enderecoAuditorDAO.buscaEnderecoAuditor(id);
	    	return enderecoAuditor;
	    }
		catch (Exception e) {
			return null;
		}	
	}

	@SuppressWarnings("unused")
	public Boolean salvar(){
		
		Endereco enderecoAuditor = null;
		getCoodernadas();
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	EnderecoDAO enderecoAuditorDAO = new EnderecoDAO(s, Endereco.class);
	    	enderecoAuditorDAO.save(this);
	    	s.close();
	    	enderecoAuditor = null;
	      	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		Endereco enderecoAuditor = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	EnderecoDAO enderecoAuditorDAO = new EnderecoDAO(s, Endereco.class);
	    	enderecoAuditorDAO.delete(this);
	    	s.close();
	    	enderecoAuditor = null;
	       return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}

	@SuppressWarnings("unused")
	public boolean alterar(){
	
		Endereco enderecoAuditor = null;
	
		try{
			setDataUpdate(new Date());
			
			org.hibernate.Session s = HibernateUtil.openSession();
			EnderecoDAO enderecoAuditorDAO = new EnderecoDAO(s, Endereco.class);
    		enderecoAuditorDAO.merge(this);
    		s.close();
    		enderecoAuditor = null;
    		return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
	
	public void getCoodernadas(){
		try {
		GoogleGeocoder  g = GoogleGeocoder.getInstance();
		Collection<GeocodedLocation> loc;
			loc = g.geocode(this.logradouro+", "+this.numero+", "+this.cep+", "+this.cidade+", "+this.estado+", Brazil");
		
		for (Iterator<GeocodedLocation> i = loc.iterator(); i.hasNext();){
			  GeocodedLocation b =  i.next();
			  this.latitude = b.getLat();
			  this.longitude = b.getLon();
		}
		} catch (GeocodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}
}