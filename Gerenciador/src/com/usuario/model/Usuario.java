package com.usuario.model;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.exception.DAOException;
import com.login.model.Login;
import com.principal.helper.Hash;
import com.principal.helper.HibernateHelper;
import com.sun.xml.internal.bind.CycleRecoverable;
import com.usuario.dao.*;
import com.vaadin.data.util.BeanItemContainer;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name="Midia_usuario")
public class Usuario implements Serializable , CycleRecoverable{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id = null;
	
	@XmlElement
	@Column (name = "nome", unique = true , nullable = false, length=50)
	private String nome = "";
	
	@Column (name = "senha")
	private String senha = "";
	
	@Transient
	private String senhaConfirmacao = "";
	
	@Column (name = "nivel", nullable = false, length=15)
	private String nivel = "";
	
	@Transient
	private Login login = null;

	
	public Usuario(){}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		try {
			this.senha = Hash.shaCodigo(senha);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * @return the senhaConfirmacao
	 */
	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	/**
	 * @param senhaConfirmacao the senhaConfirmacao to set
	 */
	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}


	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public static boolean autentica(String nome, String senha){
		
		Usuario usuario = Usuario.autenticaUsuario(nome, senha);
			if(usuario != null){
				 return true;
			}
			else return false;
		
	}
	
	public static Usuario autenticaUsu(String nome, String senha){
		
		Usuario usuario;
		try {
			usuario = Usuario.autenticaUsuario(nome, Hash.shaCodigo(senha));
		    return usuario;
		}catch (Exception e) {
			return null;
		}
		
	}
	
	
	public static ArrayList<Usuario> listaUsuario(){
		UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
		ArrayList<Usuario> workouts = new ArrayList<Usuario>();
		workouts.addAll(usuarioDAO.list());
		
		return workouts;
	}

	public static BeanItemContainer<Usuario> listaBens(){
		BeanItemContainer<Usuario>beans = new BeanItemContainer<Usuario>(Usuario.class);
		UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
	 	beans.addAll(usuarioDAO.list());
		return beans;
	}
	
	public static Usuario autenticaUsuario(String nome, String senha){
         Usuario usuario = null;
		
		try{
		  	UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
	        usuario = usuarioDAO.autenticaUsuario(nome, senha);
	    	return usuario;
	    }
		catch (Exception e) {
			return null;
		}
			
	}
	
	public static Usuario pesquisaNome(String nome){
		
		Usuario usuario = null;
		
		try{
	    	UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
	        usuario = usuarioDAO.pesquisaUsuarioByNome(nome);
	      return usuario;
	    }
		catch (Exception e) {
			return null;
		}
		
	}
	
	public boolean salvar(){
		
		try{
		  UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
	    	usuarioDAO.save(this);
	    	usuarioDAO = null;
	        return true;
	    }
		catch (DAOException e) {
			return false;
		}	
	}
	
	public boolean remover(){
		
		boolean result = false;
		
		try{
		  	UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
	    	result = usuarioDAO.delete(this);
	        usuarioDAO = null;
	        return result;
	    }
		catch (DAOException e) {
			return false;
		}	
	}

	public boolean alterar(){
		
		boolean result = false;
		
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
			result = usuarioDAO.merge(this);
			usuarioDAO = null;
			return result;
		}
		catch (DAOException e) {
			return false;
		}	
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", senha=" + senha
				+ ", nivel=" + nivel +"]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		Usuario obj = new Usuario();  
		  
		return obj;  
	}
	
	
}