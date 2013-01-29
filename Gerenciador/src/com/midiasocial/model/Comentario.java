package com.midiasocial.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.midiasocial.dao.ComentarioDAO;
import com.principal.helper.HibernateUtil;

@Entity
@Table(name = "comentario")
public class Comentario {

	@Id
	@GeneratedValue
	@Column(name = "id_comentario")
	private Long idInterno;
	
	@Column(name = "id_midia", unique = true)
	private String idMidia;
	
	@Column(name = "comentario_idusuario")
	private String idUsuario;
	
	@Column(name = "comentario_nomeusuario")
	private String nomeUsuario;
	
	@Column(name = "comentario_mensagem")
	private String mensagem;
	
	@Column(name = "comentario_datacriacao")
	private Date dataCriacao;
	
	@Column(name = "comentario_datacriaacao_midia")
	private Date dataCriacaoMidia;
	
	@Column(name = "comentario_curtir")
	private boolean curtir;
	
	@Column(name = "comentario_deletado")
	private boolean deletado;
	
	@Column(name = "comentario_datadeletado")
	private Date dataDeletado;
		
	@Column(name = "comentario_comentaroffline")
	private boolean comentarOffline;
	
	@Column(name = "comentario_curtiroffline")
	private boolean curtirOffline;
	
	@Column(name = "comentario_curtirremoveroffline")
	private boolean curtirRemoverOffline;
	
	@Column(name = "comentario_deletaroffline")
	private boolean deletarOffline;
	
	@ManyToOne
	@JoinColumn( name = "id_publicacao")
    private Publicacao publicacao;
	
	public Comentario(){}

	public Long getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(Long id) {
		this.idInterno = id;
	}

	public String getIdMidia() {
		String converte = this.idMidia;
		if(converte.split(":").length > 1){
			return converte.split(":")[1];
		}	
		return idMidia;
	}

	public void setIdMidia(String idMidia) {
		this.idMidia = idMidia;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isDeletado() {
		return deletado;
	}

	public void setDeletado(boolean deletado) {
		this.deletado = deletado;
	}

	public Date getDataDeletado() {
		return dataDeletado;
	}

	public void setDataDeletado(Date dataDeletado) {
		this.dataDeletado = dataDeletado;
	}

	public boolean isCurtir() {
		return curtir;
	}

	public void setCurtir(boolean curtir) {
		this.curtir = curtir;
	}

	public Date getDataCriacaoMidia() {
		return dataCriacaoMidia;
	}

	public void setDataCriacaoMidia(Date dataCriacaoMidia) {
		this.dataCriacaoMidia = dataCriacaoMidia;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao post) {
		this.publicacao = post;
	}

	public String getIdUsuario() {
		String converte = this.idUsuario;
		if(converte.split(":").length > 1){
			return converte.split(":")[1];
		}	
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	/**
	 * @return the comentarOffline
	 */
	public boolean isComentarOffline() {
		return comentarOffline;
	}

	/**
	 * @param comentarOffline the comentarOffline to set
	 */
	public void setComentarOffline(boolean comentarOffline) {
		this.comentarOffline = comentarOffline;
	}

	/**
	 * @return the curtirOffline
	 */
	public boolean isCurtirOffline() {
		return curtirOffline;
	}

	/**
	 * @param curtirOffline the curtirOffline to set
	 */
	public void setCurtirOffline(boolean curtirOffline) {
		this.curtirOffline = curtirOffline;
	}

	/**
	 * @return the curtirRemoverOffline
	 */
	public boolean isCurtirRemoverOffline() {
		return curtirRemoverOffline;
	}

	/**
	 * @param curtirRemoverOffline the curtirRemoverOffline to set
	 */
	public void setCurtirRemoverOffline(boolean curtirRemoverOffline) {
		this.curtirRemoverOffline = curtirRemoverOffline;
	}

	/**
	 * @return the deletarOffline
	 */
	public boolean isDeletarOffline() {
		return deletarOffline;
	}

	/**
	 * @param deletarOffline the deletarOffline to set
	 */
	public void setDeletarOffline(boolean deletarOffline) {
		this.deletarOffline = deletarOffline;
	}
		
	@SuppressWarnings("rawtypes")
	public static List listaComentario(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
		  
		List workouts = comentarioDAO.list();
			
		//s.close();
		return workouts;
	}
		
	public Comentario pesquisaComentarioID(){
			
		Comentario comentario = null;
			
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
		    ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
		    comentario = comentarioDAO.buscaComentarios(idInterno);
		    s.close();
	    	return comentario;
		}
		catch (Exception e) {
			return null;
		}	
	}
		
	public static Comentario pesquisaComentarioID(Long id){
			
		Comentario comentario = null;
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
	    	comentario = comentarioDAO.buscaComentarios(id);
		   	s.close();
		   	return comentario;
		}
		catch (Exception e) {
			return null;
		}	
	}
	
	
	public static Comentario pesquisaComentarioIdMidia(String idMidia){
		
		Comentario com = null;
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
	    	com = comentarioDAO.buscaComentarios(idMidia);
		   	s.close();
		   	return com;
		}
		catch (Exception e) {
			return null;
		}	
	}
	
	public static List<Comentario> listOffCurtir(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
		
		List<Comentario> listCom = comentarioDAO.listaOffCurtir();
		return listCom;
	}

	public static List<Comentario> listOffComentario(){
	
		org.hibernate.Session s = HibernateUtil.openSession();
		ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
	
		List<Comentario> listCom = comentarioDAO.listaOffComentario();
		return listCom;
	}
	
	public static List<Comentario> listOffComentario(UsuarioAppMidiaSocial usuario){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
	
		List<Comentario> listCom = comentarioDAO.buscaComentariosOff(usuario);
		return listCom;
	}
	
	public static List<Comentario> listOffCurtirRemover(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
	
		List<Comentario> listCom = comentarioDAO.listaOffCurtirRemover();
		return listCom;
	}
	
	public static List<Comentario> listOffDeletar(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
	
		List<Comentario> listCom = comentarioDAO.listaOffDeletar();
		return listCom;
	}
	
	public static List<Comentario> listComentarioPublicacao(Publicacao publicacao){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
	
		List<Comentario> listCom = comentarioDAO.listaComentarioPublicacao(publicacao);
	
		return listCom;
	}
		
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		Comentario comentario = null;
		setDataCriacao(new Date());
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
		    comentarioDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
		
	@SuppressWarnings("unused")
	public boolean remover(){
			
		Comentario comentario = null;
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
		    comentarioDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
		
	@SuppressWarnings("unused")
	public boolean alterar(){
		
		Comentario comentario = null;
		
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			ComentarioDAO comentarioDAO = new ComentarioDAO(s, Comentario.class);
			comentarioDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
