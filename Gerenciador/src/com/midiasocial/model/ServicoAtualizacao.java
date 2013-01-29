package com.midiasocial.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.midiasocial.dao.ServicoAtualizacaoDAO;
import com.principal.helper.HibernateUtil;

@Entity
@Table(name = "servicoatualizacao")
public class ServicoAtualizacao {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
		
	@Column(name = "datacriacao")
	private Date dataCriacao;
		
	@Column(name = "dataencerramento")
	private Date dataEncerramento;
	
	@Column(name = "homeMonitorada")
	private String homeMonitorada;
	
	@Column(name = "mensagemerro", length = 65000)
	private String mensagemErro;
	
	public ServicoAtualizacao(){}

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

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getHomeMonitorada() {
		return homeMonitorada;
	}

	public void setHomeMonitorada(String homeMonitorada) {
		this.homeMonitorada = homeMonitorada;
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaServico(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO(s, ServicoAtualizacao.class);
	  
		List workouts = servicoDAO.list();
		
		return workouts;
	}
	
	public static ServicoAtualizacao pesquisaUltimaAtualizacao(){
		org.hibernate.Session s = HibernateUtil.openSession();
	    ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO(s, ServicoAtualizacao.class);
	    ServicoAtualizacao servicoAtualizacao = servicoDAO.pesquisaUltimaAtualizacao();
	    s.close();
	    return servicoAtualizacao;
	}
	
	public void salvar(){
		org.hibernate.Session s = HibernateUtil.openSession();
	    ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO(s, ServicoAtualizacao.class);
	    servicoDAO.save(this);
	    s.close();
	}
	
	public boolean remover(){
	
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO(s, ServicoAtualizacao.class);
	        servicoDAO.delete(this);
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
			ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO(s, ServicoAtualizacao.class);
			servicoDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
