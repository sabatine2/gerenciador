package com.ticket.controller;

import org.hibernate.Session;

import com.principal.helper.HibernateUtil;
import com.ticket.model.Mensagem;
import com.ticket.model.Nota;
import com.ticket.model.Ordem;
import com.ticket.view.OrdemView;
import com.ticket.view.menu.MenuOrdem;
import com.usuario.model.Usuario;
import com.vaadin.data.util.BeanItemContainer;

public class OrdemController {

	private OrdemView  ordemView= null;
    private Ordem      ordem = null;
    private MenuOrdem  menuOrdem;
    public  Session    session;
    private Usuario    usuario;
    
	public OrdemController(Usuario usuario) {
		super();
		session = HibernateUtil.openSession();
		this.usuario = usuario;
		menuOrdem = new MenuOrdem(this);
		
	}
	
	public BeanItemContainer<Ordem> listOrdem(){
		if (usuario.getNivel().equals("funcionario")){
			return Ordem.listaBeans(session,usuario.getFuncionario().getDepartamento());	
		}else{
			return Ordem.listaBeans(session);
	       	
		}
	}

	public void visualizar(Object event){
		 ordem = (Ordem)event;
	     ordemView.visualizar();
	}
	
	/**
	 * @return the ordemView
	 */
	public OrdemView getOrdemView() {
		if(ordemView == null){
			ordemView = new OrdemView(this);
		}
		return ordemView;
	}

	/**
	 * @param ordemView the ordemView to set
	 */
	public void setOrdemView(OrdemView ordemView) {
		this.ordemView = ordemView;
	}

	/**
	 * @return the ordem
	 */
	public Ordem getOrdem() {
		if(ordem == null){
			ordem = new Ordem();
		}
		return ordem;
	}

	/**
	 * @param ordem the ordem to set
	 */
	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}

	/**
	 * @return the menuOrdem
	 */
	public MenuOrdem getMenuOrdem() {
		return menuOrdem;
	}

	/**
	 * @param menuOrdem the menuOrdem to set
	 */
	public void setMenuOrdem(MenuOrdem menuOrdem) {
		this.menuOrdem = menuOrdem;
	}

	public boolean salvar() {
		
		if(this.ordem.salvar()){
			ordemView.voltar();
			return true;
		}
		else
		 return false;
	}
	
	public boolean salvarNota(Nota nota){
		nota.setOrdem(getOrdem());
		if(nota.salvar()){
		nota.setFuncionario(usuario.getFuncionario());
		ordem.getNota().add(nota);
		ordemView.novoEvento();
		return true;
		}
		else{return false;}
	}
	
	public boolean salvarMensagem(Mensagem mensagem){
		mensagem.setOrdem(getOrdem());
		if(mensagem.salvar()){
		mensagem.setFuncionario(usuario.getFuncionario());
		ordem.getMensagem().add(mensagem);
		ordemView.novoEvento();
		return true;
		}
		else{return false;}
	}

	
}
