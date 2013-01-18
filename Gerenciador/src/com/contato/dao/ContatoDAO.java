package com.contato.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.contato.model.Contato;
	
	public class ContatoDAO extends DAO<Contato> {
		
		public ContatoDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Contato pesquisaContatoID(Long id) {
			System.out.print("pesquisaContatoId : " + id);
			return (Contato) session.load(Contato.class, id);
		}
		
		public Contato pesquisaContatoNome(String nome) {
			System.out.print("pesquisaContatoNome : " + nome);
			Criteria c = session.createCriteria(Contato.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (Contato)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Contato> pesquisaContatos(String nome){
			Criteria c = session.createCriteria(Contato.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public Contato buscaContato(Long id){
			Query q = session.createQuery("select p from " + Contato.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Contato)q.uniqueResult();
		}	
		
		public Contato buscaContatoCPF(String cpf){
			Query q = session.createQuery("select p from " + Contato.class.getName() + " as p where p.cpf like :cpf");
			
			q.setParameter("cpf", cpf);
			
			return (Contato)q.uniqueResult();
		}
	}