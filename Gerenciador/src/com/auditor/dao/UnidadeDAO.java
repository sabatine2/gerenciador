package com.auditor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.Unidade;
public class UnidadeDAO extends DAO<Unidade> {
	
	public UnidadeDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public Unidade pesquisaUnidadeId(Long id) {
		System.out.print("pesquisaUnidadeId : " + id);
		return (Unidade) session.load(Unidade.class, id);
	}
	
	public Unidade pesquisaUnidadeNome(String nome) {
		System.out.print("pesquisaUnidadeNome : " + nome);
		Criteria c = session.createCriteria(Unidade.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));

		return (Unidade)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidade> pesquisaUnidades(String nome){
		Criteria c = session.createCriteria(Unidade.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public Unidade buscaUnidade(Long id){
		Query q = session.createQuery("select p from " + Unidade.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (Unidade)q.uniqueResult();
	}
}