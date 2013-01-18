package com.ticket.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.Departamento;
import com.ticket.model.Ordem;

public class OrdemDAO extends DAO<Ordem> {
	
	public OrdemDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public Ordem buscaOrdem(Long id){
		Query q = session.createQuery("select p from " + Ordem.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (Ordem)q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordem> listaOrdemStatus(String status){
		Criteria c = session.createCriteria(Ordem.class);
		c.add(Restrictions.eq("status",status));
		c.addOrder(Order.asc("id"));
		
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordem> listaOrdemDepartamento(Departamento departamento){
		Criteria c = session.createCriteria(Ordem.class);
		c.add(Restrictions.eq("departamento",departamento));
		c.addOrder(Order.asc("id"));
		
		return c.list();
	}

}
