package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.abstracts.dao.DAO;
import com.midiasocial.model.ResultadoBusca;
	
	public class ResultadoBuscaDAO extends DAO<ResultadoBusca> {
		
		public ResultadoBuscaDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public ResultadoBusca pesquisaPostID(Long id) {
			return (ResultadoBusca) session.load(ResultadoBusca.class, id);
		}
		
		public ResultadoBusca pesquisaPostNome(String nome) {
			Criteria c = session.createCriteria(ResultadoBusca.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (ResultadoBusca)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<ResultadoBusca> pesquisaPosts(String nome){
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
		public ResultadoBusca buscaPosts(Long id){
			Query q = session.createQuery("select p from " + ResultadoBusca.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (ResultadoBusca)q.uniqueResult();
		}
		
		public ResultadoBusca buscaPosts(String idMidia){
			Query q = session.createQuery("select p from " + ResultadoBusca.class.getName() + " as p where p.idMidia like :idMidia");
			
			q.setParameter("idMidia", idMidia);
			
			return (ResultadoBusca)q.uniqueResult();
		}
	}