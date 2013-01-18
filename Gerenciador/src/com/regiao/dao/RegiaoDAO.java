	package com.regiao.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.regiao.model.Regiao;
	
	public class RegiaoDAO extends DAO<Regiao> {
		
		public RegiaoDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Regiao pesquisaRegiaoID(Long id) {
			System.out.print("pesquisaRegiaoId : " + id);
			return (Regiao) session.load(Regiao.class, id);
		}
		
		public Regiao pesquisaRegiaoNome(String nome) {
			System.out.print("pesquisaRegiaoNome : " + nome);
			Criteria c = session.createCriteria(Regiao.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (Regiao)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Regiao> pesquisaRegiaos(String nome){
			Criteria c = session.createCriteria(Regiao.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public Regiao buscaRegiaos(Long id){
			Query q = session.createQuery("select p from " + Regiao.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Regiao)q.uniqueResult();
		}
	}