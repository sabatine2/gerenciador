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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.midiasocial.dao.PublicacaoDAO;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;

@Entity
@Table(name = "publicacao")
public class Publicacao {

	@Id
	@GeneratedValue
	@Column(name = "publicacao_id")
	private Long id;
	
	@Column(name = "publicacao_idmidia", unique = true)
	private String idMidia;

	@Column(name = "publicacao_datacriacao")
	private Date dataCriacao;

	@Column(name = "publicacao_datacriacaomidia")
	private Date dataCriacaoMidia;
	
	@Column(name = "publicacao_dataconsumido")
	private Date dataConsumido;
	
	@Column(name = "publicacao_mensagem", length=65000)
	private String mensagem;
	
	@Column(name = "publicacao_idusuario")
	private String idUsuario;
	
	@Column(name = "publicacao_nome")
	private String nomeUsuario;
	
	@Column(name = "publicacao_curtir")
	private boolean curtir;
	
	@Column(name = "publicacao_usuariomediaid")
	private Long usuarioMediaId;
	
	@Column(name = "publicacao_deletado")
	private boolean deletado;
	
	@Column(name = "publicacao_datadeletado")
	private Date dataDeletado;
	
	@Column(name = "publicacao_fotourl")
	private String fotoUrl;
	
	@Column(name = "publicacao_anexorl")
	private String anexoUrl;
	
	@Column(name = "publicacao_iddestino")
	private String idDestino;
	
	@Column(name = "publicacao_publicaroffline")
	private boolean publicarOffline;
	
	@Column(name = "publicacao_curtiroffline")
	private boolean curtirOffline;
	
	@Column(name = "publicacao_curtirremoveroffline")
	private boolean curtirRemoverOffline;
	
	@Column(name = "publicacao_deletaroffline")
	private boolean deletarOffline;
	
	@ManyToOne
	@JoinColumn(name = "publicacao_usuarioapp")
	private UsuarioAppMidiaSocial usuarioAppMidiaSocial;
	
	@ManyToOne
	@JoinColumn( name = "userpub_idInterno")
    private UsuarioPubMidiaSocial usuarioPubMidiaSocial;
	
	@OneToMany(mappedBy = "publicacao", targetEntity = Comentario.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    private Collection<Comentario> comentario = new ArrayList<Comentario>(); 
	
	public Publicacao(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdMidia() {
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

	public Long getUsuarioMediaId() {
		return usuarioMediaId;
	}

	public void setUsuarioMediaId(Long usuarioMediaId) {
		this.usuarioMediaId = usuarioMediaId;
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
	 * @return the anexoUrl
	 */
	public String getAnexoUrl() {
		return anexoUrl;
	}

	/**
	 * @param anexoUrl the anexoUrl to set
	 */
	public void setAnexoUrl(String anexoUrl) {
		this.anexoUrl = anexoUrl;
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
	
	@SuppressWarnings("rawtypes")
	public static BeanItemContainer listaBens(){
		BeanItemContainer<Publicacao>beans = new BeanItemContainer<Publicacao>(Publicacao.class);
		
		org.hibernate.Session s = HibernateUtil.openSession();
		PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		  
		List workouts = postDAO.list();
			
		for (Iterator iterator = workouts.iterator(); iterator.hasNext();) {
			Publicacao wo = (Publicacao) iterator.next();
			beans.addBean(wo);
	   	}	
		//s.close();
		return beans;
	}
		
	@SuppressWarnings("rawtypes")
	public static List listaPost(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		  
		List workouts = postDAO.list();
			
		//s.close();
		return workouts;
		
	}
	
	@SuppressWarnings("rawtypes")
	public static List listaPost(UsuarioAppMidiaSocial app){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		List workouts = postDAO.pesquisaPosts(app);
		return workouts;
	}
	
	public static List<Publicacao> listOffPublicacao(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		
		List<Publicacao> listPub = postDAO.listaOffPublicacao();
		return listPub;
	}
	
	public static List<Publicacao> listOffCurtir(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		
		List<Publicacao> listPub = postDAO.listaOffCurtir();
		return listPub;
	}
	
	public static List<Publicacao> listOffCurtirRemover(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		
		List<Publicacao> listPub = postDAO.listaOffCurtirRemover();
		return listPub;
	}
	
	public static List<Publicacao> listOffDeletar(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		
		List<Publicacao> listPub = postDAO.listaOffDeletar();
		return listPub;
	}

	
	public Publicacao pesquisaPostID(){
			
		Publicacao post = null;
			
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
		    PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		    post = postDAO.buscaPosts(id);
		    s.close();
	    	return post;
		}
		catch (Exception e) {
			return null;
		}	
	}
		
	public static Publicacao pesquisaPostID(Long id){
			
		Publicacao post = null;
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
	    	post = postDAO.buscaPosts(id);
		   	s.close();
		   	return post;
		}
		catch (Exception e) {
			return null;
		}	
	}
	
	public static Publicacao pesquisaPostIdMidia(String idMidia){
		
		Publicacao post = null;
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
	    	post = postDAO.buscaPosts(idMidia);
		   	s.close();
		   	return post;
		}
		catch (Exception e) {
			return null;
		}	
	}
		
	@SuppressWarnings("unused")
	public boolean salvar(){
		
		Publicacao post = null;
		setDataCriacao(new Date());
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		    postDAO.save(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
		
	@SuppressWarnings("unused")
	public boolean remover(){
			
		Publicacao post = null;
			
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
		  	PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		    postDAO.delete(this);
	    	s.close();
	    	return true;
	    }
		catch (Exception e) {
			return false;
		}	
	}
		
	public static List<Publicacao> listLastUpdate(String idUsuarioMidia){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
		
		List<Publicacao> list = postDAO.listlastUpdate(idUsuarioMidia);
		
		return list;
	}
	
	@SuppressWarnings("unused")
	public boolean alterar(){
		
		Publicacao post = null;
		
		try{
			org.hibernate.Session s = HibernateUtil.openSession();
			PublicacaoDAO postDAO = new PublicacaoDAO(s, Publicacao.class);
			postDAO.merge(this);
        	s.close();
        	return true;
		}
		catch (Exception e) {
			return false;
		}	
	}
}
