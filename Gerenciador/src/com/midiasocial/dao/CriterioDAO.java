package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.Criterio;
import com.principal.helper.HibernateHelper;
import com.abstracts.dao.DAO;
	
	public class CriterioDAO extends DAO<Criterio> {
		
		public CriterioDAO(Class<?> classe) {
			super(classe);
		}
		
		
		public Criterio pesquisaCriterioNome(String nome) {
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(Criterio.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			return (Criterio)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Criterio> pesquisaCriterios(String nome){
			org.hibernate.Session session = HibernateHelper.currentSession();
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
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query q = session.createQuery("select p from " + Criterio.class.getName() + " as p where p.id like :id");
			q.setParameter("id", id);
			return (Criterio)q.uniqueResult();
		}
	}