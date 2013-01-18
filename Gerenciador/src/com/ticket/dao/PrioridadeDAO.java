package com.ticket.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.abstracts.dao.DAO;
import com.ticket.model.Prioridade;

public class PrioridadeDAO extends DAO<Prioridade> {
	
	public PrioridadeDAO(Session session, Class<?> classe) {
		super(session, classe);
	}

	public Prioridade buscaPrioridade(Long id){
		Query q = session.createQuery("select p from " + Prioridade.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (Prioridade)q.uniqueResult();
	}
}
