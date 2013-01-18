package com.auditor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.ReferenciaServico;

public class ReferenciaServicoDAO extends DAO<ReferenciaServico> {
	
	public ReferenciaServicoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public ReferenciaServico pesquisaReferenciaServicoId(Long id) {
		System.out.print("pesquisaReferenciaServicoId : " + id);
		return (ReferenciaServico) session.load(ReferenciaServico.class, id);
	}
	
	public ReferenciaServico pesquisaReferenciaServicoTipo(String tipo) {
		System.out.print("pesquisaReferenciaServicoTipoe : " + tipo);
		Criteria c = session.createCriteria(ReferenciaServico.class);
		c.add(Restrictions.ilike("nome", "%" + tipo + "%"));

		return (ReferenciaServico)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReferenciaServico> pesquisaReferenciaServicos(String nome){
		Criteria c = session.createCriteria(ReferenciaServico.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public ReferenciaServico buscaReferenciaServico(Long id){
		Query q = session.createQuery("select p from " + ReferenciaServico.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (ReferenciaServico)q.uniqueResult();
	}
}