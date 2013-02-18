package com.midiasocial.model;


import java.util.ArrayList;
import java.util.Collection;
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
import com.midiasocial.dao.UsuarioPubMidiaSocialDAO;

@Entity
@Table(name = "MidiaUsuarioPub")
public class UsuarioPubMidiaSocial {

	@Id
	@GeneratedValue
	@Column(name = "idInterno", unique = true)
	private Long idInterno;
	
	@Column(name = "idmidia", unique = true)
	private String idMidia;
	private String nome;
	private String email;
	private String localizacao;
	@Column(name = "screenname")
	private String screenName;
	@Column(name = "fotourl")
	private String fotoUrl;
	@Column(name = "fotoperfil")
	private String fotoPerfilUrl;
	@Column(name = "urlperfil")
	private String urlPerfil = " ";
	@Column(name = "redesocial")
	private String nomeRedeSocial = " ";
	
	@OneToMany(mappedBy = "usuarioPubMidiaSocial", targetEntity = Publicacao.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    private Collection<Publicacao> publicacao = new ArrayList<Publicacao>();
	
	public Long getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(Long idInterno) {
		this.idInterno = idInterno;
	}

	public String getIdMidia() {
		return idMidia;
	}

	public void setIdMidia(String id) {
		this.idMidia = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getFotoPerfilUrl() {
		return fotoPerfilUrl;
	}

	public void setFotoPerfilUrl(String fotoPerfilUrl) {
		this.fotoPerfilUrl = fotoPerfilUrl;
	}

	public Collection<Publicacao> getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Collection<Publicacao> publicacao) {
		this.publicacao = publicacao;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the urlPerfil
	 */
	public String getUrlPerfil() {
		return urlPerfil;
	}

	/**
	 * @param urlPerfil the urlPerfil to set
	 */
	public void setUrlPerfil(String urlPerfil) {
		this.urlPerfil = urlPerfil;
	}

	/**
	 * @return the nomeRedeSocial
	 */
	public String getNomeRedeSocial() {
		return nomeRedeSocial;
	}

	/**
	 * @param nomeRedeSocial the nomeRedeSocial to set
	 */
	public void setNomeRedeSocial(String nomeRedeSocial) {
		this.nomeRedeSocial = nomeRedeSocial;
	}
	
	
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public static List<UsuarioPubMidiaSocial> listaUsuario(){
		UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(UsuarioPubMidiaSocial.class);
		return  usuarioDAO.list();
	
	}
	
	public static UsuarioPubMidiaSocial pesquisaUsuarioIdMidia(String id){
		UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(UsuarioPubMidiaSocial.class);
		UsuarioPubMidiaSocial usuario = usuarioDAO.pesquisaUsuarioIdMidia(id);
		return usuario;
		
	}
	
	public UsuarioPubMidiaSocial pesquisaUsuarioID(){
		
		UsuarioPubMidiaSocial usuario = null;
		
		try{
		 	UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO( UsuarioPubMidiaSocial.class);
	        usuario = usuarioDAO.load(idInterno);
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public static UsuarioPubMidiaSocial pesquisaUsuarioID(Long id){
		
		UsuarioPubMidiaSocial usuario = null;
		
		try{
			UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(UsuarioPubMidiaSocial.class);
	    	usuario = usuarioDAO.load(id);
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}	
	}
	
	public boolean salvar(){
		try{
		  	UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(UsuarioPubMidiaSocial.class);
	        usuarioDAO.save(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean remover(){
		try{
		  	UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(UsuarioPubMidiaSocial.class);
	        usuarioDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean alterar(){
	
		try{
			UsuarioPubMidiaSocialDAO usuarioDAO = new UsuarioPubMidiaSocialDAO(UsuarioPubMidiaSocial.class);
			usuarioDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}
	
}
