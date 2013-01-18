package com.endereco.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.endereco.model.Endereco;

public class EnderecoDAO extends DAO<Endereco> {
	
	public EnderecoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public Endereco pesquisaEnderecoAuditorId(Long id) {
		System.out.print("pesquisaEnderecoAuditorId : " + id);
		return (Endereco) session.load(Endereco.class, id);
	}
	
	public Endereco pesquisaEnderecoAuditorTipo(String tipo) {
		System.out.print("pesquisaEnderecoAuditorTipo : " + tipo);
		Criteria c = session.createCriteria(Endereco.class);
		c.add(Restrictions.ilike("nome", "%" + tipo + "%"));

		return (Endereco)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Endereco> pesquisaEnderecoAuditors(String nome){
		Criteria c = session.createCriteria(Endereco.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public Endereco buscaEnderecoAuditor(Long id){
		Query q = session.createQuery("select p from " + Endereco.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (Endereco)q.uniqueResult();
	}
}