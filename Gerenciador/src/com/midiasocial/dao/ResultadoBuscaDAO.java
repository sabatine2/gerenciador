package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.abstracts.dao.DAO;
import com.midiasocial.model.ResultadoBusca;
import com.principal.helper.HibernateHelper;
	
	public class ResultadoBuscaDAO extends DAO<ResultadoBusca> {
		
		public ResultadoBuscaDAO(Class<?> classe) {
			super(classe);
		}
		
		public ResultadoBusca pesquisaPostNome(String nome) {
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(ResultadoBusca.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (ResultadoBusca)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<ResultadoBusca> pesquisaPosts(String nome){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(ResultadoBusca.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
		
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		
		
		public ResultadoBusca buscaPostsMidia(String idMidia){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query q = session.createQuery("select p from " + ResultadoBusca.class.getName() + " as p where p.idMidia like :idMidia");
			q.setParameter("idMidia", idMidia);
			return (ResultadoBusca)q.uniqueResult();
		}
	}