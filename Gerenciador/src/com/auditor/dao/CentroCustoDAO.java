package com.auditor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.CentroCusto;

public class CentroCustoDAO extends DAO<CentroCusto> {
	
	public CentroCustoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public CentroCusto pesquisaCentroCustoId(Long id) {
		System.out.print("pesquisaCentroCustoId : " + id);
		return (CentroCusto) session.load(CentroCusto.class, id);
	}
	
	public CentroCusto pesquisaCentroCustoNome(String nome) {
		System.out.print("pesquisaCentroCustoNome : " + nome);
		Criteria c = session.createCriteria(CentroCusto.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));

		return (CentroCusto)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<CentroCusto> pesquisaCentroCustos(String nome){
		Criteria c = session.createCriteria(CentroCusto.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public CentroCusto buscaCentroCusto(Long id){
		Query q = session.createQuery("select p from " + CentroCusto.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (CentroCusto)q.uniqueResult();
	}
}