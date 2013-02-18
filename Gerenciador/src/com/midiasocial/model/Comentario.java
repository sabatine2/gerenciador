package com.midiasocial.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.exception.DAOException;
import com.midiasocial.dao.ComentarioDAO;

@Entity
@Table(name = "MidiaComentario")
public class Comentario {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long idInterno;
	
	@Column(name = "idmidia", unique = true)
	private String idMidia;
	
	@Column(name = "idusuario")
	private String idUsuario;
	
	@Column(name = "nomeusuario")
	private String nomeUsuario;
	
	@Column(name = "mensagem")
	private String mensagem;
	
	@Column(name = "datacriacao")
	private Date dataCriacao;
	
	@Column(name = "datacriaacaomidia")
	private Date dataCriacaoMidia;
	
	@Column(name = "curtir")
	private boolean curtir;
	
	@Column(name = "deletado")
	private boolean deletado;
	
	@Column(name = "datadeletado")
	private Date dataDeletado;
		
	@Column(name = "comentaroffline")
	private boolean comentarOffline;
	
	@Column(name = "curtiroffline")
	private boolean curtirOffline;
	
	@Column(name = "curtirremoveroffline")
	private boolean curtirRemoverOffline;
	
	@Column(name = "deletaroffline")
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
		
		ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		List workouts = comentarioDAO.list();
	
		return workouts;
	}
		
	public Comentario pesquisaComentarioID(){
			
		Comentario comentario = null;
			
		try{
			 ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		    comentario = comentarioDAO.load(idInterno);
		
	    	return comentario;
		}
		catch (Exception e) {
			return null;
		}	
	}
		
	public static Comentario pesquisaComentarioID(Long id){
			
		Comentario comentario = null;
			
		try{
		 	ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
	    	comentario = comentarioDAO.load(id);
		   	return comentario;
		}
		catch (Exception e) {
			return null;
		}	
	}
	
	
	public static Comentario pesquisaComentarioIdMidia(String idMidia){
		
		Comentario com = null;
			
		try{
		  	ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
	    	com = comentarioDAO.buscaComentarios(idMidia);
		   	return com;
		}
		catch (Exception e) {
			return null;
		}	
	}
	
	public static List<Comentario> listOffCurtir(){
		ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		List<Comentario> listCom = comentarioDAO.listaOffCurtir();
		return listCom;
	}

	public static List<Comentario> listOffComentario(){
		ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		List<Comentario> listCom = comentarioDAO.listaOffComentario();
		return listCom;
	}
	
	public static List<Comentario> listOffComentario(UsuarioAppMidiaSocial usuario){
		ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		List<Comentario> listCom = comentarioDAO.buscaComentariosOff(usuario);
		return listCom;
	}
	
	public static List<Comentario> listOffCurtirRemover(){
		ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		List<Comentario> listCom = comentarioDAO.listaOffCurtirRemover();
		return listCom;
	}
	
	public static List<Comentario> listOffDeletar(){
		ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		List<Comentario> listCom = comentarioDAO.listaOffDeletar();
		return listCom;
	}
	
	public static List<Comentario> listComentarioPublicacao(Publicacao publicacao){
		ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		List<Comentario> listCom = comentarioDAO.listaComentarioPublicacao(publicacao);
		return listCom;
	}
		
	public boolean salvar(){
		
		setDataCriacao(new Date());
			
		try{
		  	ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		    comentarioDAO.save(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
		
	public boolean remover(){
			
		try{
		  	ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
		    comentarioDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
		
	public boolean alterar(){
		try{
			ComentarioDAO comentarioDAO = new ComentarioDAO(Comentario.class);
			comentarioDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}

	@Override
	public String toString() {
		return "Comentario [idInterno=" + idInterno + ", idMidia=" + idMidia
				+ ", idUsuario=" + idUsuario + ", nomeUsuario=" + nomeUsuario
				+ ", mensagem=" + mensagem + ", dataCriacao=" + dataCriacao
				+ ", dataCriacaoMidia=" + dataCriacaoMidia + ", curtir="
				+ curtir + ", deletado=" + deletado + ", dataDeletado="
				+ dataDeletado + ", comentarOffline=" + comentarOffline
				+ ", curtirOffline=" + curtirOffline
				+ ", curtirRemoverOffline=" + curtirRemoverOffline
				+ ", deletarOffline=" + deletarOffline + "]";
	}
	
	
}
