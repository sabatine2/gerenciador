package com.auditor.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.Medida;
	
	public class MedidaDAO extends DAO<Medida> {
		
		public MedidaDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Medida pesquisaMedidaID(Long id) {
			System.out.print("pesquisaMedidaId : " + id);
			return (Medida) session.load(Medida.class, id);
		}
		
		public Medida pesquisaMedidaDescricao(String descricao) {
			System.out.print("pesquisaMedidaDescricao : " + descricao);
			Criteria c = session.createCriteria(Medida.class);
			c.add(Restrictions.ilike("nome", "%" + descricao + "%"));

			return (Medida)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Medida> pesquisaMedidas(String nome){
			Criteria c = session.createCriteria(Medida.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public Medida buscaMedidas(Long id){
			Query q = session.createQuery("select p from " + Medida.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Medida)q.uniqueResult();
		}
	}