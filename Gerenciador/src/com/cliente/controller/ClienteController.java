package com.cliente.controller;

import java.util.Date;
import org.hibernate.Session;

import com.cliente.model.Cliente;
import com.cliente.view.ClienteAdminView;
import com.cliente.view.menu.MenuCliente;
import com.contato.controller.ContatoController;
import com.principal.helper.HibernateUtil;
import com.view.componente.Mensagem;

public class ClienteController {

	private Cliente             cliente = null;
	private ContatoController   contatoController  = null;
	private ClienteAdminView    clienteAdmin       = null;
	private String              estado;            
	public Session              session;
	public MenuCliente          menuCliente = null;
	
	public ClienteController(){
		clienteAdmin = new ClienteAdminView(this);
		menuCliente = new MenuCliente(this);
		session = HibernateUtil.getSession();
		clienteAdmin.modoTabela();
	}
	
	public boolean salvar(Cliente cliente){
		
		if(cliente == null){
			return false;
		}
		
		this.cliente = cliente;
		
		if(estado.equals("visualizar")){
			cliente.setDataUltimaAlteracao(new Date());
			boolean result = cliente.alterar();
			clienteAdmin.update();
			cliente = null;
			return result;
		}
		
		else if(estado.equals("livro")){
			contatoController.alterar();
			cliente.setDataUltimaAlteracao(new Date());
			if(cliente.alterar()){
				contatoController.atualizaDetalhes();
				clienteAdmin.update();
				livroContato();
				return true;
			}
			else {return false;}
		}
		
		else if(estado.equals("addlivro")){
			if(contatoController.salvar()){
			   cliente.getContato().add(contatoController.getContato());
	   	       cliente.setDataUltimaAlteracao(new Date());
	   	       cliente.alterar();
	   	       livroContato();
	   	       clienteAdmin.update();
	   	       cliente = null;
	   	       return true;
			   }
			else {return false;}
	    	}
		
		else{
		   if(cliente.salvar()){
			   clienteAdmin.insereClienteLista(cliente);
			   visualizar(cliente);
			   return true;
		   }
		   else{
			   return false;
		   } 
		}
	}
	
	public boolean remover(){
		if(cliente.remover(session)){
		clienteAdmin.removeClienteLista(cliente);
		setCliente(null);
		return true;
		
		}else{
			Mensagem.msgErro(getView(), "Erro ao excluir", cliente.getInfoCliente());
			return false;
		}
		
	}
	
	public boolean alterar(Object[] dados){
		return cliente.alterar();
	}
	
	public void visualizar(Object event){
		cliente = (Cliente) event;
		visualizar();
	}

	public void visualizar(){
		menuCliente.moduloVisualizarCliente();
		clienteAdmin.visualizar(cliente);
		estado = "visualizar";
	}
	
	public void livroContato(){
		contatoController = new ContatoController(this);
		clienteAdmin.modoLivroContato();
		estado = "livro";
	}
	
	public void agenda(){
		clienteAdmin.modoAgendaView();
	}
	
	public void voltar(){
		setCliente(null);
		menuCliente.moduloVoltar();
	}
	
	public void modoAdicionar(){
		setCliente(null);
		getContatoController().setContato(null);
		estado = "novo";
	}
	
	public Cliente getCliente() {
	
		if(cliente == null){
			cliente = new Cliente();
			cliente.setDataCadastro(new Date());
			cliente.setDataUltimaAlteracao(new Date());
		}
		return cliente;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public ContatoController getContatoController(){
		
		if(contatoController == null){
		    contatoController = new ContatoController(this);
		}
		return contatoController;
	}
	
	public ClienteAdminView getClienteAdmin() {
		return clienteAdmin;
	}
	
	public void setContatoController(ContatoController contatoController) {
		this.contatoController = contatoController;
	}

	public ClienteAdminView getView() {
		return clienteAdmin;
	}

	public void setClienteAdmin(ClienteAdminView clienteAdmin) {
		this.clienteAdmin = clienteAdmin;
	}

	/**
	 * @return the menuCliente
	 */
	public MenuCliente getMenuCliente() {
		return menuCliente;
	}

	/**
	 * @param menuCliente the menuCliente to set
	 */
	public void setMenuCliente(MenuCliente menuCliente) {
		this.menuCliente = menuCliente;
	}
	
	
}
