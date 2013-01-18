package com.auditor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.dispositivo.model.Dispositivo;

public class DispositivoDAO extends DAO<Dispositivo> {
	
	public DispositivoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public Dispositivo pesquisaDispositivoId(Long id) {
		System.out.print("pesquisaDispositivoId : " + id);
		return (Dispositivo) session.load(Dispositivo.class, id);
	}
	
	public Dispositivo pesquisaDispositivoNome(String nome) {
		System.out.print("pesquisaDispositivoNome : " + nome);
		Criteria c = session.createCriteria(Dispositivo.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));

		return (Dispositivo)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Dispositivo> pesquisaDispositivos(String nome){
		Criteria c = session.createCriteria(Dispositivo.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public Dispositivo buscaDispositivo(Long id){
		Query q = session.createQuery("select p from " + Dispositivo.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (Dispositivo)q.uniqueResult();
	}
}