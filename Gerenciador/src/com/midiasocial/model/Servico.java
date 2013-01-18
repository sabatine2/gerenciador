package com.midiasocial.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.midiasocial.dao.ServicoDAO;
import com.midiasocial.service.MidiaSocialService;
import com.principal.helper.HibernateUtil;
import com.usuario.model.Usuario;
import com.vaadin.data.util.BeanItemContainer;

@Entity
@Table(name="servico")
public class Servico {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "datacriacao")
	private Date dataCriacao;

	@Column(name = "dataalteracao")
	private Date dataAlteracao;
	
	@Column(name = "intervalo")
	private Long intervalo = new Long(100l);
	
	@OneToOne
	private Usuario usuario;

	@Transient
	MidiaSocialService midiaServico;
	
	
	public Servico(){
		dataCriacao = new Date();
	    dataAlteracao = new Date();
	}
	
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

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Long getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Long intervalo) {
		this.intervalo = intervalo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//METODOS
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer listaBens(){
		BeanItemContainer<Servico>beans = new BeanItemContainer<Servico>(Servico.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ServicoDAO servicoDAO = new ServicoDAO(s, Servico.class);
	  
		List workouts = servicoDAO.list();
		
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Servico wo = (Servico) iterator.next();
			beans.addBean(wo);
       	}	
		 //s.close();
		 return beans;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaUsuario(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ServicoDAO servicoDAO = new ServicoDAO(s, Servico.class);
	  
		List workouts = servicoDAO.list();
		
		 //s.close();
		 return workouts;
	}
	
	public static Servico pesquisaServicoID(Long id){
		
		Servico servico = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ServicoDAO servicoDAO = new ServicoDAO(s, Servico.class);
	    	servico = servicoDAO.pesquisaUsuarioID(id);
	    	s.close();
	    	return servico;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public void salvar(){
		org.hibernate.Session s = HibernateUtil.openSession();
	    ServicoDAO servicoDAO = new ServicoDAO(s, Servico.class);
	    servicoDAO.save(this);
	    s.close();
	}
	
	public boolean remover(){
	
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ServicoDAO servicoDAO = new ServicoDAO(s, Servico.class);
	        servicoDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
	
	public boolean alterar(){
	
	   dataAlteracao = new Date();
		
	    try{
			org.hibernate.Session s = HibernateUtil.openSession();
			ServicoDAO servicoDAO = new ServicoDAO(s, Servico.class);
			servicoDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
	
	@Transient
	public boolean servicoAtivo(){
		
		try {
			if(midiaServico.isAlive()){
				return true;
			}
			else return false;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public void iniciarServico(){
	
		if(midiaServico == null)
			midiaServico = new MidiaSocialService(getIntervalo());
		
		midiaServico.start();
		
	}
	
	public void pararServico(){
		
		midiaServico.parar();
		midiaServico = null;
		
	}

}
