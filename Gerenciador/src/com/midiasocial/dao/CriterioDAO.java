package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.Criterio;
import com.abstracts.dao.DAO;
	
	public class CriterioDAO extends DAO<Criterio> {
		
		public CriterioDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Criterio pesquisaCriterioID(Long id) {
			System.out.print("pesquisaCriterioId : " + id);
			return (Criterio) session.load(Criterio.class, id);
		}
		
		public Criterio pesquisaCriterioNome(String nome) {
			System.out.print("pesquisaCriterioNome : " + nome);
			Criteria c = session.createCriteria(Criterio.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (Criterio)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Criterio> pesquisaCriterios(String nome){
			Criteria c = session.createCriteria(Criterio.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public Criterio buscaCriterios(Long id){
			Query q = session.createQuery("select p from " + Criterio.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Criterio)q.uniqueResult();
		}
	}