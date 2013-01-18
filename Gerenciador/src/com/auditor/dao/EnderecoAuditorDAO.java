package com.auditor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.contato.EnderecoAuditor;

public class EnderecoAuditorDAO extends DAO<EnderecoAuditor> {
	
	public EnderecoAuditorDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public EnderecoAuditor pesquisaEnderecoAuditorId(Long id) {
		System.out.print("pesquisaEnderecoAuditorId : " + id);
		return (EnderecoAuditor) session.load(EnderecoAuditor.class, id);
	}
	
	public EnderecoAuditor pesquisaEnderecoAuditorTipo(String tipo) {
		System.out.print("pesquisaEnderecoAuditorTipo : " + tipo);
		Criteria c = session.createCriteria(EnderecoAuditor.class);
		c.add(Restrictions.ilike("nome", "%" + tipo + "%"));

		return (EnderecoAuditor)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<EnderecoAuditor> pesquisaEnderecoAuditors(String nome){
		Criteria c = session.createCriteria(EnderecoAuditor.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public EnderecoAuditor buscaEnderecoAuditor(Long id){
		Query q = session.createQuery("select p from " + EnderecoAuditor.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (EnderecoAuditor)q.uniqueResult();
	}
}