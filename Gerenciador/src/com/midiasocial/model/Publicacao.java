package com.midiasocial.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.exception.DAOException;
import com.midiasocial.dao.PublicacaoDAO;

@Entity
@Table(name = "MidiaPublicacao")
public class Publicacao {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "idmidia", unique = true)
	private String idMidia;

	@Column(name = "datacriacao")
	private Date dataCriacao;

	@Column(name = "datacriacaomidia")
	private Date dataCriacaoMidia;
	
	@Column(name = "dataconsumido")
	private Date dataConsumido;
	
	@Column(name = "mensagem", length=65000)
	private String mensagem;
	
	@Column(name = "idusuario")
	private String idUsuario;
	
	@Column(name = "nomeusuario")
	private String nomeUsuario;
		
	@Column(name = "curtir")
	private boolean curtir;
	
	
	@Column(name = "deletado")
	private boolean deletado;
	
	@Column(name = "datadeletado")
	private Date dataDeletado;
	
	@Column(name = "fotourl")
	private String fotoUrl;
	
	@Column(name = "iddestino")
	private String idDestino;
	
	@Column(name = "publicaroffline")
	private boolean publicarOffline;
	
	@Column(name = "curtiroffline")
	private boolean curtirOffline;
	
	@Column(name = "curtirremoveroffline")
	private boolean curtirRemoverOffline;
	
	@Column(name = "deletaroffline")
	private boolean deletarOffline;
	
	@ManyToOne
	@JoinColumn(name = "usuarioapp")
	private UsuarioAppMidiaSocial usuarioAppMidiaSocial;
	
	@ManyToOne
	@JoinColumn( name = "idinterno")
    private UsuarioPubMidiaSocial usuarioPubMidiaSocial;
	
	@OneToMany(mappedBy = "publicacao", targetEntity = Comentario.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    private Collection<Comentario> comentario = new ArrayList<Comentario>(); 
	
	@OneToMany(mappedBy = "publicacao", targetEntity = Anexo.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    private Collection<Anexo> anexo = new ArrayList<Anexo>(); 

	public Publicacao(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataCriacaoMidia() {
		return dataCriacaoMidia;
	}

	public void setDataCriacaoMidia(Date dataCriacaoMidia) {
		this.dataCriacaoMidia = dataCriacaoMidia;
	}

	public Date getDataConsumido() {
		return dataConsumido;
	}

	public void setDataConsumido(Date dataConsumido) {
		this.dataConsumido = dataConsumido;
	}

	public boolean isCurtir() {
		return curtir;
	}

	public void setCurtir(boolean curtir) {
		this.curtir = curtir;
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Collection<Comentario> getComentario() {
		return comentario;
	}

	public void setComentario(Collection<Comentario> comentario) {
		this.comentario = comentario;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

		/**
	 * @return the idDestino
	 */
	public String getIdDestino() {
		return idDestino;
	}

	/**
	 * @param idDestino the idDestino to set
	 */
	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
	}

	/**
	 * @return the publicarOffline
	 */
	public boolean isPublicarOffline() {
		return publicarOffline;
	}

	/**
	 * @param publicarOffline the publicarOffline to set
	 */
	public void setPublicarOffline(boolean publicarOffline) {
		this.publicarOffline = publicarOffline;
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

		/**
	 * @return the usuarioAppMidiaSocial
	 */
	public UsuarioAppMidiaSocial getUsuarioAppMidiaSocial() {
		return usuarioAppMidiaSocial;
	}

	/**
	 * @param usuarioAppMidiaSocial the usuarioAppMidiaSocial to set
	 */
	public void setUsuarioAppMidiaSocial(UsuarioAppMidiaSocial usuarioAppMidiaSocial) {
		this.usuarioAppMidiaSocial = usuarioAppMidiaSocial;
	}

	public UsuarioPubMidiaSocial getUsuarioPubMidiaSocial() {
		return usuarioPubMidiaSocial;
	}

	public void setUsuarioPubMidiaSocial(UsuarioPubMidiaSocial usuarioPubMidiaSocial) {
		this.usuarioPubMidiaSocial = usuarioPubMidiaSocial;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	
	
	//METODOS
		
	public Collection<Anexo> getAnexo() {
		return anexo;
	}

	public void setAnexo(Collection<Anexo> anexo) {
		this.anexo = anexo;
	}
	
	public void addAnexo(Anexo anexo) {
		this.anexo.add(anexo);
	}

	public static List<Publicacao> listaPost(){
		PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		return postDAO.list();
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaPublicacao(UsuarioAppMidiaSocial app){
		PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		List workouts = postDAO.pesquisaPosts(app);
		return workouts;
	}
	
	
	public static List<Publicacao> listOffPublicacao(){
		PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		List<Publicacao> listPub = postDAO.listaOffPublicacao();
		return listPub;
	}
	
	public static List<Publicacao> listOffCurtir(UsuarioAppMidiaSocial usuario){
		PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		List<Publicacao> listPub = postDAO.listaOffCurtir(usuario);
		return listPub;
	}
	
	public static List<Publicacao> listOffCurtirRemover(UsuarioAppMidiaSocial usuario){
		PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		List<Publicacao> listPub = postDAO.listaOffCurtirRemover(usuario);
		return listPub;
	}
	
	public static List<Publicacao> listOffDeletar(){
		PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		List<Publicacao> listPub = postDAO.listaOffDeletar();
		return listPub;
	}
	
	public static List<Publicacao> listLastUpdate(String idUsuarioMidia){
	
		PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		List<Publicacao> list = postDAO.listlastUpdate(idUsuarioMidia);
		return list;
	}

	
	public Publicacao pesquisaPostID(){
			
		Publicacao post = null;
			
		try{
		    PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		    post = postDAO.load(id);
		  return post;
		}
		catch (Exception e) {
			return null;
		}	
	}
		
	public static Publicacao pesquisaPostID(Long id){
			
		Publicacao post = null;
			
		try{
			PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
	    	post = postDAO.load(id);
		   	return post;
		}
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Publicacao pesquisaPostIdMidia(String idMidia){
		
		Publicacao post = null;
			
		try{
		  	PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
	    	post = postDAO.buscaPosts(idMidia);
		   	return post;
		}
		catch (Exception e) {
			return null;
		}	
	}
		
	public boolean salvar(){
		
		setDataCriacao(new Date());
			
		try{
		  	PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		    postDAO.save(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
		
	public boolean remover(){
		try{
		  	PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
		    postDAO.delete(this);
	    	return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
		
	public boolean alterar(){
		
		try{
			PublicacaoDAO postDAO = new PublicacaoDAO(Publicacao.class);
			postDAO.merge(this);
        	return true;
		}
		catch (DAOException e) {
			return false;
		}	
	}
}
