package com.login.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Session;

import com.exception.DAOException;
import com.login.dao.LoginDAO;
import com.principal.helper.HibernateHelper;
import com.usuario.model.Usuario;
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@Entity
@Table(name = "Midia_login")
public class Login {

	@Id
	@GeneratedValue
	private Long    id;
	private Date    dataCreate;
	private Date    dataEncerramento;
	private String  ip;
	private Boolean ativo;
	private String  sistemasOperacional;
	private String  navegador;
	private Integer resAltura;
	private Integer resLargura;
	
	@OneToOne
	private Usuario usuario;
	
	public Login(){}
	
	public Login(String navegador, String sistemasOperacional, String ip, Usuario usuario, Boolean ativo, Integer resAltura, Integer resLargura) {
		super();
		this.dataCreate = new Date();
		this.ip = ip;
		this.usuario = usuario;
		this.ativo = ativo;
		this.sistemasOperacional = sistemasOperacional;
		this.navegador = navegador;
		this.resAltura = resAltura;
		this.resLargura = resLargura;
		
	}
	
	public Date getDataCreate() {
		return dataCreate;
	}
	public void setDataCreate(Date dataCreate) {
		this.dataCreate = dataCreate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	
	public String getSistemasOperacional() {
		return sistemasOperacional;
	}

	public void setSistemasOperacional(String sistemasOperacional) {
		this.sistemasOperacional = sistemasOperacional;
	}
	
	

	public String getNavegador() {
		return navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	public int getResAltura() {
		return resAltura;
	}

	public void setResAltura(int resAltura) {
		this.resAltura = resAltura;
	}

	public int getResLargura() {
		return resLargura;
	}

	public void setResLargura(int resLargura) {
		this.resLargura = resLargura;
	}

	public static List<Login> buscaAtivo(){
		try{
		  LoginDAO loginDAO = new LoginDAO(Login.class);
		  return loginDAO.buscaLoginAtivos();
		}
		catch (Exception e){
			return null;
		}
	}
	
	public static List<Login> buscaLoginUsuarioAtivo(Usuario usuario){
		try{
		  LoginDAO loginDAO = new LoginDAO(Login.class);
		  return loginDAO.buscaLoginUsuarioAtivo(usuario);
		}
		catch (Exception e){
			return null;
		}
	}
	
	public List<Login> buscaLoginUsuarioAtivo(){
		try{
		  LoginDAO loginDAO = new LoginDAO(Login.class);
		  return loginDAO.buscaLoginUsuario(this.getUsuario());
		}
		catch (Exception e){
			return null;
		}
	}
	
	public static List<Login> buscaLoginUsuario(Usuario usuario){
		try{
		  LoginDAO loginDAO = new LoginDAO(Login.class);
		  return loginDAO.buscaLoginUsuario(usuario);
		}
		catch (Exception e){
			return null;
		}
	}
	
	public static Login ultimoLogin(Usuario usuario){
		try{
		  LoginDAO loginDAO = new LoginDAO( Login.class);
		  return loginDAO.utimoLogin(usuario);
		}
		catch (Exception e){
			return null;
		}
	}
	
	public void salvar(){
		LoginDAO loginDAO = new LoginDAO(Login.class);
		try {
			loginDAO.save(this);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginDAO = null;
		
	}
	
	public void alterar(){
		LoginDAO loginDAO = new LoginDAO(Login.class);
		try {
			loginDAO.merge(this);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginDAO = null;
	}
	
	@Override
	public String toString() {
		return "Login [dataCreate=" + dataCreate + ", dataEncerramento="
				+ dataEncerramento + ", ip=" + ip + ", usuario=" + usuario
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		return true;
	}
	
	
	
}
