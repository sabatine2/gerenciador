package com.midiasocial.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.exception.DAOException;
import com.midiasocial.dao.ServicoAtualizacaoDAO;

@Entity
@Table(name = "MidiaServicoAtualizacao")
public class ServicoAtualizacao {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
		
	@Column(name = "datacriacao")
	private Date dataCriacao;
		
	@Column(name = "dataencerramento")
	private Date dataEncerramento;
	
	@Column(name = "homemonitorada")
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
		ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO( ServicoAtualizacao.class);
	  List workouts = servicoDAO.list();
		return workouts;
	}
	
	public static ServicoAtualizacao pesquisaUltimaAtualizacao(){
		ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO( ServicoAtualizacao.class);
	    ServicoAtualizacao servicoAtualizacao = servicoDAO.pesquisaUltimaAtualizacao();
	    return servicoAtualizacao;
	}
	
	public boolean salvar(){
		ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO( ServicoAtualizacao.class);
	    try {
			servicoDAO.save(this);
			return true;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	  }
	
	public boolean remover(){
	
		try{
			ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO( ServicoAtualizacao.class);
	        servicoDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	
	public boolean alterar(){
		
	    try{
			ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO( ServicoAtualizacao.class);
			servicoDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}
	
	public static boolean removerAll(){
		 try{
				ServicoAtualizacaoDAO servicoDAO = new ServicoAtualizacaoDAO( ServicoAtualizacao.class);
				servicoDAO.removerAll();
	        	return true;
			}
			catch (Exception e) {
				return false;
			}	
	}
}
