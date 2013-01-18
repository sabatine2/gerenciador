package com.funcionario.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.funcionario.model.Funcionario;
	
	public class FuncionarioDAO extends DAO<Funcionario> {
		
		public FuncionarioDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Funcionario pesquisaFuncionarioID(Long id) {
			System.out.print("pesquisaFuncionarioId : " + id);
			return (Funcionario) session.load(Funcionario.class, id);
		}
		
		public Funcionario pesquisaFuncionarioNomeFantasia(String nome) {
			System.out.print("pesquisaFuncionarioNome : " + nome);
			Criteria c = session.createCriteria(Funcionario.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (Funcionario)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Funcionario> pesquisaFuncionarios(String nome){
			Criteria c = session.createCriteria(Funcionario.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public Funcionario buscaFuncionario(Long id){
			Query q = session.createQuery("select p from " + Funcionario.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Funcionario)q.uniqueResult();
		}
		
		public Funcionario buscaFuncionarioCNPJ(String cpf){
			Query q = session.createQuery("select p from " + Funcionario.class.getName() + "as p where p.cpf like :cpf");
			
			q.setParameter("cpf", cpf);
			
			return (Funcionario)q.uniqueResult();
		}	
	}