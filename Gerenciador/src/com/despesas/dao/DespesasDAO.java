	package com.despesas.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.despesas.model.Despesas;
import com.funcionario.model.Funcionario;

	public class DespesasDAO extends DAO<Despesas> {
		
		public DespesasDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Despesas pesquisaDespesasById(Long id) {
			System.out.print("pesquisaDespesasById : " + id);
			return (Despesas) session.load(Despesas.class, id);
		}
		
		public Despesas pesquisaDespesasByNome(String nome) {
			System.out.print("pesquisaDespesasByNome : " + nome);
			Criteria c = session.createCriteria(Despesas.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (Despesas)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Despesas> pesquisaDespesass(Funcionario cpf){
			Criteria c = session.createCriteria(Despesas.class);
			c.add( Restrictions.eq("funcionario.cpf", cpf ));
			return c.list();
		}
		
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public Despesas buscaDespesas(Long id){
			Query q = session.createQuery("select p from " + Despesas.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Despesas)q.uniqueResult();
		}
			
	}
