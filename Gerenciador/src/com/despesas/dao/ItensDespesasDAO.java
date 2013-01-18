package com.despesas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.despesas.model.ItensDespesas;

public class ItensDespesasDAO extends DAO<ItensDespesas> {
	
	public ItensDespesasDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public ItensDespesas pesquisaItensDespesasById(Long id) {
		System.out.print("pesquisaItensDespesasById : " + id);
		return (ItensDespesas) session.load(ItensDespesas.class, id);
	}
	
	public ItensDespesas pesquisaItensDespesasByNome(String nome) {
		System.out.print("pesquisaItensDespesasByNome : " + nome);
		Criteria c = session.createCriteria(ItensDespesas.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));

		return (ItensDespesas)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItensDespesas> pesquisaItensDespesass(String nome){
		Criteria c = session.createCriteria(ItensDespesas.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public ItensDespesas buscaItensDespesas(Long id){
		Query q = session.createQuery("select p from " + ItensDespesas.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (ItensDespesas)q.uniqueResult();
	}
}