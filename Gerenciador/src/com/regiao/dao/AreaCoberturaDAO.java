package com.regiao.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.regiao.model.AreaCobertura;
	
	public class AreaCoberturaDAO extends DAO<AreaCobertura> {
		
		public AreaCoberturaDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public AreaCobertura pesquisaAreaCoberturaID(Long id) {
			System.out.print("pesquisaAreaCoberturaId : " + id);
			return (AreaCobertura) session.load(AreaCobertura.class, id);
		}
		
		public AreaCobertura pesquisaAreaCoberturaNome(String nome) {
			System.out.print("pesquisaAreaCoberturaNome : " + nome);
			Criteria c = session.createCriteria(AreaCobertura.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (AreaCobertura)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<AreaCobertura> pesquisaAreaCoberturas(String nome){
			Criteria c = session.createCriteria(AreaCobertura.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public AreaCobertura buscaAreaCoberturas(Long id){
			Query q = session.createQuery("select p from " + AreaCobertura.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (AreaCobertura)q.uniqueResult();
		}
	}