package com.auditor.model.contato;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.auditor.dao.EnderecoAuditorDAO;
import com.auditor.helper.Conexao;
import com.googlemaps.GeocodedLocation;
import com.googlemaps.GeocodingException;
import com.googlemaps.GoogleGeocoder;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="enderecoauditor")
public class EnderecoAuditor {

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
	
	public EnderecoAuditor(){
		
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

	public static EnderecoAuditor pesquisaId(Long id){
		
		EnderecoAuditor endereco = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	EnderecoAuditorDAO enderecoAuditorDAO = new EnderecoAuditorDAO(s, EnderecoAuditor.class);
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
		BeanItemContainer <EnderecoAuditor>beans = new BeanItemContainer<EnderecoAuditor>(EnderecoAuditor.class);
	
		org.hibernate.Session s = HibernateUtil.openSession();
		EnderecoAuditorDAO enderecoAuditorDAO = new EnderecoAuditorDAO(s, EnderecoAuditor.class);
  
		List workouts = enderecoAuditorDAO.list();
	
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			EnderecoAuditor wo = (EnderecoAuditor) iterator.next();
			wo.setEnderecoCompleto(wo.getLogradouro()+", "+wo.getNumero()+", "+wo.getBairro()+", "+wo.getCep()+", "+wo.getCidade()+", "+wo.getEstado());
			beans.addBean(wo);
		}	
		s.close();
		return beans;
	}
	
	public EnderecoAuditor pesquisaEnderecoAuditorID(){
		
		EnderecoAuditor enderecoAuditor = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	EnderecoAuditorDAO enderecoAuditorDAO = new EnderecoAuditorDAO(s, EnderecoAuditor.class);
	        enderecoAuditor = enderecoAuditorDAO.buscaEnderecoAuditor(id);
	    	s.close();
	    	return enderecoAuditor;
	    }
		catch (Exception e) {
			return null;
		}	
	}

	@SuppressWarnings("unused")
	public Boolean salvar() throws GeocodingException{
		
		EnderecoAuditor enderecoAuditor = null;
		getCoodernadas();
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	EnderecoAuditorDAO enderecoAuditorDAO = new EnderecoAuditorDAO(s, EnderecoAuditor.class);
	        enderecoAuditorDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	@SuppressWarnings("unused")
	public boolean remover(){
		
		EnderecoAuditor enderecoAuditor = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	EnderecoAuditorDAO enderecoAuditorDAO = new EnderecoAuditorDAO(s, EnderecoAuditor.class);
	        enderecoAuditorDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}

	@SuppressWarnings("unused")
	public boolean alterar() throws GeocodingException{
	
		EnderecoAuditor enderecoAuditor = null;
		getCoodernadas();
	
		try{
			setDataUpdate(new Date());
			
			org.hibernate.Session s = HibernateUtil.openSession();
			EnderecoAuditorDAO enderecoAuditorDAO = new EnderecoAuditorDAO(s, EnderecoAuditor.class);
    		enderecoAuditorDAO.merge(this);
    		s.close();
    		return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
	
	public void getCoodernadas() throws GeocodingException{
		
		if (Conexao.valida()) {

			GoogleGeocoder g = GoogleGeocoder.getInstance();
			Collection<GeocodedLocation> loc =	g.geocode(this.logradouro+", "+this.numero+", "+this.cep+", "+this.cidade+", "+this.estado+", Brazil");
			System.out.println(" Teste " + loc.toString() +"  "+ loc.size());
			for (Iterator<GeocodedLocation> i = loc.iterator(); i.hasNext();){
				  GeocodedLocation b =  i.next();
				  this.latitude = b.getLat();
				  this.longitude = b.getLon();
			}
			System.out.println("Latitude: "+this.latitude);
			System.out.println("Longitude: "+this.longitude);
		}
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}
}