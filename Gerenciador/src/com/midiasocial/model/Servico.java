package com.midiasocial.model;

import java.util.ArrayList;
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
	
	@Column(name = "ativo")
	private Boolean ativo = false;
	
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

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@SuppressWarnings("rawtypes")
	public static List<Servico> listaServico(){
		
		org.hibernate.Session s = HibernateUtil.openSession();
		ServicoDAO servicoDAO = new ServicoDAO(s, Servico.class);
		return servicoDAO.list();
	  
	}
	
	public static Servico pesquisaServicoID(Long id){
		
		Servico servico = null;
		
		try{
		  	org.hibernate.Session s = HibernateUtil.openSession();
	    	ServicoDAO servicoDAO = new ServicoDAO(s, Servico.class);
	    	servico = servicoDAO.pesquisaServicoID(id);
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
	
	public void ativar(){
		try{
			StringBuilder mensagemErro = new StringBuilder();
			MidiaSocialService midiaSocialService = new MidiaSocialService(mensagemErro);	
					
			ServicoAtualizacao servicoAtualizar = new ServicoAtualizacao();
			servicoAtualizar.setDataCriacao(new Date());
			StringBuilder listaHome = new StringBuilder();
					
			@SuppressWarnings("unchecked")
			ArrayList<UsuarioAppMidiaSocial> usuarioLista = (ArrayList<UsuarioAppMidiaSocial>) UsuarioAppMidiaSocial.listaUsuario();
			for (Iterator<UsuarioAppMidiaSocial> i = usuarioLista.iterator();i.hasNext();){
				UsuarioAppMidiaSocial u = i.next();
					
				listaHome.append(" "+u.getNome());
				if(u.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
					midiaSocialService.servicoFacebook(u);
				}
				else if(u.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
					midiaSocialService.servicoTwitter(u);
				}
						
				midiaSocialService.atualizarPublicacaoOff(u);
		     	midiaSocialService.atualizarCurtirOff(u);
				midiaSocialService.atualizarCurtirRemoverOff(u);
				midiaSocialService.atualizarDeletarOff(u);
			}		
					
				midiaSocialService.atualizarComentarioOff();
					
				servicoAtualizar.setDataEncerramento(new Date());
				servicoAtualizar.setHomeMonitorada(listaHome.toString());
				servicoAtualizar.setMensagemErro(mensagemErro.toString());
				servicoAtualizar.salvar();
					
				mensagemErro = null;
					
				try {
					Thread.sleep(intervalo*60*1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
			
			} catch (Exception e) {
				//servico.setAtivo(false);
			}
		
	   }
	
}
