package com.funcionario.controller;

import java.util.Date;
import org.hibernate.Session;
import com.contato.controller.ContatoController;
import com.funcionario.model.Funcionario;
import com.funcionario.view.admin.FuncionarioAdminView;
import com.funcionario.view.menu.MenuFuncionario;
import com.principal.helper.HibernateUtil;
import com.usuario.model.Usuario;

public class FuncionarioController {


	private Funcionario            funcionario       = null;
	private ContatoController      contatoController = null;
	private FuncionarioAdminView   funcionarioAdmin  = null;
	private String                 estado;            
	public  Session                session;
	private MenuFuncionario        menuFuncionario;
	
	public FuncionarioController() {
		funcionarioAdmin = new FuncionarioAdminView(this);
		menuFuncionario = new MenuFuncionario(this);
		session = HibernateUtil.openSession();
		funcionarioAdmin.modoTabela();
	}
	
	public boolean salvar(Funcionario funcionario){
		
		if(funcionario == null){
			return false;
		}
		
		this.funcionario = funcionario;
		
		if(estado.equals("visualizar")){
			funcionario.setDataUltimaAlteracao(new Date());
			boolean result = funcionario.alterar();
			funcionarioAdmin.update();
			funcionario = null;
			return result;
		}
		
		else if(estado.equals("livro")){
			contatoController.alterar();
			funcionario.setDataUltimaAlteracao(new Date());
			if(funcionario.alterar()){
				contatoController.atualizaDetalhes();
				funcionarioAdmin.update();
				livroContato();
				funcionario = null;
				return true;
			}
			else {return false;}
		}
		
		else if(estado.equals("addlivro")){
			contatoController.getContato().setCliente(null);
			if(contatoController.salvar()){
			    funcionario.getContato().add(contatoController.getContato());
	   	        funcionario.setDataUltimaAlteracao(new Date());
	     	    livroContato();
	     	    funcionario.alterar();
	     	    funcionario = null;
	     	    return true;
			    }
			else {return false;}
	    	}
		
		else{
			 funcionario.getUsuario().setFuncionario(funcionario);
			 if(funcionario.salvar()){
				funcionarioAdmin.insereFuncionarioLista(funcionario);
				visualizar(funcionario);
				return true;
			 }
			else{
			 return false;
		   } 
		}
	}
	
	public boolean remover(){
		Usuario u = funcionario.getUsuario();
		if(funcionario.remover(session)){
			u.remover();
			setFuncionario(null);
			funcionarioAdmin.update();
			return true;
			}else{
				return false;
			}
	}
	
	public void alterar(Object[] dados){
		funcionario.alterar();
	}
	
	public void visualizar(Object event){
		funcionario = (Funcionario) event;
		visualizar();
	}

	public void visualizar(){
		funcionarioAdmin.visualizar(funcionario);
		menuFuncionario.moduloVisualizarCliente();
		estado = "visualizar";
	}
	
	public void livroContato(){
		contatoController = new ContatoController(this);
		funcionarioAdmin.modoLivroContato();
		estado = "livro";
	}
	
	public void agenda(){
		funcionarioAdmin.modoAgendaView();
	}
	
	public void voltar(){
		menuFuncionario.moduloVoltar();
	}
	
	public void modoAdicionar(){
		setFuncionario(null);
		getContatoController().setContato(null);
		estado = "novo";
	}
	
	public Funcionario getFuncionario() {
	
		if(funcionario == null){
			funcionario = new Funcionario();
			funcionario.setDataCadastro(new Date());
			funcionario.setDataUltimaAlteracao(new Date());
		}
		return funcionario;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public ContatoController getContatoController(){
		
		if(contatoController == null){
		    contatoController = new ContatoController(this);
		}
		return contatoController;
	}
	
	public FuncionarioAdminView getFuncionarioAdmin() {
		return funcionarioAdmin;
	}
	
	public void setContatoController(ContatoController contatoController) {
		this.contatoController = contatoController;
	}

	public FuncionarioAdminView getView() {
		return funcionarioAdmin;
	}

	public void setFuncionarioAdmin(FuncionarioAdminView funcionarioAdmin) {
		this.funcionarioAdmin = funcionarioAdmin;
	}

	/**
	 * @return the menuFuncionario
	 */
	public MenuFuncionario getMenuFuncionario() {
		return menuFuncionario;
	}

	/**
	 * @param menuFuncionario the menuFuncionario to set
	 */
	public void setMenuFuncionario(MenuFuncionario menuFuncionario) {
		this.menuFuncionario = menuFuncionario;
	}
	
	public void libera(){
		funcionario = null;
		funcionarioAdmin = null;
		System.gc();
	}
}


