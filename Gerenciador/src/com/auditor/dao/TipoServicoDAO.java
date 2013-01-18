	package com.auditor.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.TipoServico;
	
	public class TipoServicoDAO extends DAO<TipoServico> {
		
		public TipoServicoDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public TipoServico pesquisaTipoServicoID(Long id) {
			System.out.print("pesquisaTipoServicoId : " + id);
			return (TipoServico) session.load(TipoServico.class, id);
		}
		
		public TipoServico pesquisaTipoServicoDescricao(String descricao) {
			System.out.print("pesquisaTipoServicoDescricao : " + descricao);
			Criteria c = session.createCriteria(TipoServico.class);
			c.add(Restrictions.ilike("nome", "%" + descricao + "%"));

			return (TipoServico)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<TipoServico> pesquisaTipoServicos(String nome){
			Criteria c = session.createCriteria(TipoServico.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public TipoServico buscaTipoServicos(Long id){
			Query q = session.createQuery("select p from " + TipoServico.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (TipoServico)q.uniqueResult();
		}
	}