package com.auditor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.contato.Telefone;

public class TelefoneDAO extends DAO<Telefone> {
	
	public TelefoneDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public Telefone pesquisaTelefoneId(Long id) {
		System.out.print("pesquisaTelefoneId : " + id);
		return (Telefone) session.load(Telefone.class, id);
	}
	
	public Telefone pesquisaTelefoneTipo(String tipo) {
		System.out.print("pesquisaTelefoneTipo : " + tipo);
		Criteria c = session.createCriteria(Telefone.class);
		c.add(Restrictions.ilike("nome", "%" + tipo + "%"));

		return (Telefone)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> pesquisaTelefones(String nome){
		Criteria c = session.createCriteria(Telefone.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> pesquisaDepartamento(Long id){
		Criteria c = session.createCriteria(Telefone.class);
		c.add(Restrictions.eq("telefoneFinalidade", "Interno" ));
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Telefone> buscaTelefone2(Long id){
		Query q = session.createQuery("select p from " + Telefone.class.getName() + " as p where p.departamentoid = :id");
		
		q.setParameter("id", id);
		
		return q.list();
	}
	
	public Telefone buscaTelefone(Long id){
		Query q = session.createQuery("select p from " + Telefone.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (Telefone)q.uniqueResult();
	}
}