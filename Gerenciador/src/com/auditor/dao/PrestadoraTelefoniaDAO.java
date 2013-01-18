package com.auditor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.contato.PrestadoraTelefonia;

public class PrestadoraTelefoniaDAO extends DAO<PrestadoraTelefonia> {
	
	public PrestadoraTelefoniaDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public PrestadoraTelefonia pesquisaPrestadoraTelefoniaId(Long id) {
		System.out.print("pesquisaPrestadoraTelefoniaId : " + id);
		return (PrestadoraTelefonia) session.load(PrestadoraTelefonia.class, id);
	}
	
	public PrestadoraTelefonia pesquisaPrestadoraTelefoniaNome(String nome) {
		System.out.print("pesquisaPrestadoraTelefoniaNome : " + nome);
		Criteria c = session.createCriteria(PrestadoraTelefonia.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));

		return (PrestadoraTelefonia)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<PrestadoraTelefonia> pesquisaPrestadoraTelefonias(String nome){
		Criteria c = session.createCriteria(PrestadoraTelefonia.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public PrestadoraTelefonia buscaPrestadoraTelefonia(Long id){
		Query q = session.createQuery("select p from " + PrestadoraTelefonia.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (PrestadoraTelefonia)q.uniqueResult();
	}
}