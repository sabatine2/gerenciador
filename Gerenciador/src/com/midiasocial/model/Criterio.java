package com.midiasocial.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.exception.DAOException;
import com.midiasocial.dao.CriterioDAO;

@Entity
@Table(name = "MidiaCriterio")
public class Criterio {

	@Id
	@GeneratedValue
	@Column(name= "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome = "";
	
	@Column(name = "status")
	private String status = "";
	
	@Column(name = "prioridade")
	private String prioridade = "";
	
	@Column(name = "observacao")
	private String observacao = "";
	
	@Column(name = "dataCriacao")
	private Date dataCriacao;
	
	@OneToMany(mappedBy = "criterio", targetEntity = PalavraChaveMidia.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	public Collection<PalavraChaveMidia> palavraChaveMidia = new ArrayList<PalavraChaveMidia>();
	
	public Criterio(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Collection<PalavraChaveMidia> getPalavraChaveMidia() {
		return palavraChaveMidia;
	}

	public void setPalavraChaveMidia(Collection<PalavraChaveMidia> palavraChaveMidia) {
		this.palavraChaveMidia = palavraChaveMidia;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	//METODOS
	
	public String getPalavraChaveString(){
		
		String palavra = "";
		for (Iterator<PalavraChaveMidia> i = this.palavraChaveMidia.iterator(); i.hasNext();) {
			PalavraChaveMidia pChave = i.next();
			palavra += pChave.getPalavraChave() + ";";
		}
		return palavra;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static List listaUsuario(){
		CriterioDAO criterioDAO = new CriterioDAO(Criterio.class);
		return criterioDAO.list();
	}
	
	public Criterio pesquisaCriterioID(){
		
		Criterio criterio = null;
		
		try{
			CriterioDAO criterioDAO = new CriterioDAO(Criterio.class);
	        criterio = criterioDAO.load(id);
	    	return criterio;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Criterio pesquisaCriterioID(Long id){
		
		Criterio criterio = null;
		
		try{
			CriterioDAO criterioDAO = new CriterioDAO(Criterio.class);
	    	criterio = criterioDAO.load(id);
	    	return criterio;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		try{
		 	CriterioDAO criterioDAO = new CriterioDAO( Criterio.class);
	        criterioDAO.save(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean remover(){
		try{
		 	CriterioDAO criterioDAO = new CriterioDAO(Criterio.class);
	        criterioDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean alterar(){
		try{
			CriterioDAO criterioDAO = new CriterioDAO(Criterio.class);
			criterioDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}
}
