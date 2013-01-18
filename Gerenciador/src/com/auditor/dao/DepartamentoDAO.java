package com.auditor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.Departamento;

public class DepartamentoDAO extends DAO<Departamento> {
	
	public DepartamentoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public Departamento pesquisaDepartamentoId(Long id) {
		System.out.print("pesquisaDepartamentoId : " + id);
		return (Departamento) session.load(Departamento.class, id);
	}
	
	public Departamento pesquisaDepartamentoNome(String nome) {
		System.out.print("pesquisaDepartamentoNome : " + nome);
		Criteria c = session.createCriteria(Departamento.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));

		return (Departamento)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Departamento> pesquisaDepartamentos(String nome){
		Criteria c = session.createCriteria(Departamento.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public Departamento buscaDepartamento(Long id){
		Query q = session.createQuery("select p from " + Departamento.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (Departamento)q.uniqueResult();
	}
}