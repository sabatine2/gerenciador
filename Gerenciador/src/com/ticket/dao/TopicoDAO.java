package com.ticket.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.ticket.model.Topico;

	
	public class TopicoDAO extends DAO<Topico> {
		
		public TopicoDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Topico pesquisaTopicoID(Long id) {
			System.out.print("pesquisaTopicoId : " + id);
			return (Topico) session.load(Topico.class, id);
		}
		
		public Topico pesquisaTopicoNome(String nome) {
			System.out.print("pesquisaTopicoNome : " + nome);
			Criteria c = session.createCriteria(Topico.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (Topico)c.uniqueResult();
		}
		
		
		
		@SuppressWarnings("unchecked")
		public List<Topico> listaTopicosStatus(String status){
			Criteria c = session.createCriteria(Topico.class);
			c.add(Restrictions.ilike("status", "%" + status + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Topico> pesquisaTopicos(String nome){
			Criteria c = session.createCriteria(Topico.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public Topico buscaTopicos(Long id){
			Query q = session.createQuery("select p from " + Topico.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Topico)q.uniqueResult();
		}
	}