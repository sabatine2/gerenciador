package com.auditor.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.ItemConta;
	
	public class ItemContaDAO extends DAO<ItemConta> {
		
		public ItemContaDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public ItemConta pesquisaItemContaID(Long id) {
			System.out.print("pesquisaItemContaId : " + id);
			return (ItemConta) session.load(ItemConta.class, id);
		}
		
		public ItemConta pesquisaItemContaNome(String nome) {
			System.out.print("pesquisaItemContaNome : " + nome);
			Criteria c = session.createCriteria(ItemConta.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (ItemConta)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<ItemConta> pesquisaItemContas(String nome){
			Criteria c = session.createCriteria(ItemConta.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public ItemConta buscaItemContas(Long id){
			Query q = session.createQuery("select p from " + ItemConta.class.getName() + " as p where p.id like :id");

			q.setParameter("id", id);
			
			return (ItemConta)q.uniqueResult();
		}
		
		public void removeAll(Long id){
			
			Query q = session.createQuery("delete from " + ItemConta.class.getName() +" where conta_id = "+ id );
			q.executeUpdate();
		}
	}