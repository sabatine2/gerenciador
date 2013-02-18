package com.midiasocial.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.Hibernate;
import com.exception.DAOException;
import com.midiasocial.dao.ServicoDAO;
import com.midiasocial.service.MidiaSocialService;
import com.principal.helper.HibernateHelper;

@Entity
@Table(name="MidiaServico")
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
	
	@Column(name = "ativo")
	private Boolean ativo = false;
	
	@Transient
	private MidiaSocialService midiaSocialService;
	
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

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public static List<Servico> listaServico(){
		ServicoDAO servicoDAO = new ServicoDAO(Servico.class);
	    return servicoDAO.list();
	}
	
	public static Servico pesquisaServicoID(Long id){
		
		Servico servico = null;
		
		try{
		  	ServicoDAO servicoDAO = new ServicoDAO(Servico.class);
	    	servico = servicoDAO.load(id);
	    	Hibernate.initialize(servico);
	    	return servico;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Servico pesquisaUltimoServico(){
		
		Servico servico = null;
		
		try{
		  	ServicoDAO servicoDAO = new ServicoDAO(Servico.class);
	    	servico = servicoDAO.pesquisaUltimoServico();
	    	Hibernate.initialize(servico);
	    	return servico;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		ServicoDAO servicoDAO = new ServicoDAO(Servico.class);
	    try {
			servicoDAO.save(this);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean remover(){
	
		try{
		 	ServicoDAO servicoDAO = new ServicoDAO(Servico.class);
	        servicoDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean alterar(){
	
	   dataAlteracao = new Date();
		
	    try{
			ServicoDAO servicoDAO = new ServicoDAO(Servico.class);
			servicoDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean refresh(){
		
		try{
				org.hibernate.Session s = HibernateHelper.openSession();
				s.refresh(this);
	        	s.close();
	        	return true;
			}
			catch (Exception e) {
				return false;
			}	
		}
	
	public Long numeroAtualizacao(){
		return midiaSocialService.getContador();
	}
	
	public void inicializarServico(){
		setAtivo(true);
		alterar();
	
		midiaSocialService = new MidiaSocialService(this);
		new Thread(midiaSocialService).start();
	}
	
	public void resetServico(){
		setAtivo(true);
		alterar();
		midiaSocialService.setContador(0l);
		
	}
	
	public void pararServico(){
		setAtivo(false);
		alterar();
	}
	
}
