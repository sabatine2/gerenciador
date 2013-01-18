package com.auditor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.Organizacao;
public class OrganizacaoDAO extends DAO<Organizacao> {
	
	public OrganizacaoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public Organizacao pesquisaOrganizacaoId(Long id) {
		System.out.print("pesquisaOrganizacaoId : " + id);
		return (Organizacao) session.load(Organizacao.class, id);
	}
	
	public Organizacao pesquisaOrganizacaoNome(String nome) {
		System.out.print("pesquisaOrganizacaoNome : " + nome);
		Criteria c = session.createCriteria(Organizacao.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));

		return (Organizacao)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Organizacao> pesquisaOrganizacaos(String nome){
		Criteria c = session.createCriteria(Organizacao.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public Organizacao buscaOrganizacao(Long id){
		Query q = session.createQuery("select p from " + Organizacao.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (Organizacao)q.uniqueResult();
	}
}