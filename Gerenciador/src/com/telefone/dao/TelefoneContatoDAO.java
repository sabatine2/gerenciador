package com.telefone.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.telefone.model.TelefoneContato;

public class TelefoneContatoDAO extends DAO<TelefoneContato> {
	
	public TelefoneContatoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public TelefoneContato pesquisaTelefoneContatoId(Long id) {
		System.out.print("pesquisaTelefoneContatoId : " + id);
		return (TelefoneContato) session.load(TelefoneContato.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<TelefoneContato> pesquisaTelefoneContatos(String nome){
		Criteria c = session.createCriteria(TelefoneContato.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public TelefoneContato buscaTelefoneContato(Long id){
		Query q = session.createQuery("select p from " + TelefoneContato.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (TelefoneContato)q.uniqueResult();
	}
}