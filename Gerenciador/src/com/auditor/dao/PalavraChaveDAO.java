package com.auditor.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.PalavraChave;
	
	public class PalavraChaveDAO extends DAO<PalavraChave> {
		
		public PalavraChaveDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public PalavraChave pesquisaPalavraChaveID(Long id) {
			System.out.print("pesquisaPalavraChaveId : " + id);
			return (PalavraChave) session.load(PalavraChave.class, id);
		}
		
		public PalavraChave pesquisaTipoServicoDescricao(String descricao) {
			System.out.print("pesquisaPalavraChaveDescricao : " + descricao);
			Criteria c = session.createCriteria(PalavraChave.class);
			c.add(Restrictions.ilike("nome", "%" + descricao + "%"));

			return (PalavraChave)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<PalavraChave> pesquisaPalavraChaves(String nome){
			Criteria c = session.createCriteria(PalavraChave.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public PalavraChave buscaPalavraChaves(Long id){
			Query q = session.createQuery("select p from " + PalavraChave.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (PalavraChave)q.uniqueResult();
		}
	}