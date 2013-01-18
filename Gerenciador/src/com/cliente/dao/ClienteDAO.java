package com.cliente.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.cliente.model.Cliente;
    
	
	public class ClienteDAO extends DAO<Cliente> {
		
		public ClienteDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Cliente pesquisaClienteID(Long id) {
			System.out.print("pesquisaClienteId : " + id);
			return (Cliente) session.load(Cliente.class, id);
		}
		
		public Cliente pesquisaClienteNomeFantasia(String nome) {
			System.out.print("pesquisaClienteNomeFantasia : " + nome);
			Criteria c = session.createCriteria(Cliente.class);
			c.add(Restrictions.ilike("razaoSocial", "%" + nome + "%"));

			return (Cliente)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Cliente> pesquisaClientes(String nome){
			Criteria c = session.createCriteria(Cliente.class);
			c.add(Restrictions.ilike("razaoSocial", "%" + nome + "%"));
			c.addOrder(Order.asc("razaoSocial"));
			
			return c.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Cliente> pesquisaClientesAtivo(){
			Criteria c = session.createCriteria(Cliente.class);
			c.add(Restrictions.eq("status", "Ativo"));
			c.addOrder(Order.asc("razaoSocial"));
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public Cliente buscaCliente(Long id){
			Query q = session.createQuery("select p from " + Cliente.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Cliente)q.uniqueResult();
		}
		
		public Cliente buscaClienteCNPJ(String cnpj){
			Query q = session.createQuery("select p from " + Cliente.class.getName() + "as p where p.cnpj like :cnpj");
			
			q.setParameter("cnpj", cnpj);
			
			return (Cliente)q.uniqueResult();
		}	
	}