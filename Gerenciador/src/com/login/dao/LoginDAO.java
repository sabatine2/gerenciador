package com.login.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.login.model.Login;
import com.usuario.model.Usuario;

public class LoginDAO extends DAO<Login> {
	
	public LoginDAO(Session session, Class<?> classe) {
		super(session, classe);
	}

	public List<Login> buscaLoginUsuario(Usuario usario) {
		Criteria c = session.createCriteria(Login.class);
		c.add(Restrictions.eq("usuario", usario));
    	return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Login> buscaLoginUsuarioAtivo(Usuario usuario){
	    Query q =  session.createQuery("select p from " + Login.class.getName() + " as p where p.ativo = 1 and p.usuario like :usuario");
		q.setParameter("usuario", usuario);
		return q.list();
	}
	

	@SuppressWarnings("unchecked")
	public List<Login> buscaLoginAtivos(){
	    Query q =  session.createQuery("select p from " + Login.class.getName() + " as p where p.ativo = 1");
		return q.list();
	}
	
	public Login utimoLogin(Usuario usario){
		Criteria c = session.createCriteria(Login.class);
		c.add(Restrictions.eq("usuario", usario));
		c.addOrder(Order.desc("dataCreate"));
		c.setMaxResults(1);
		return (Login) c.uniqueResult();
	}
	
}
