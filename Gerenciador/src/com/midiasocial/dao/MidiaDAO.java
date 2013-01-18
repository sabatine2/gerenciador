package com.midiasocial.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.MidiaSocial;
import com.abstracts.dao.DAO;

public class MidiaDAO extends DAO<MidiaSocial> {
	
	public MidiaDAO(Session session, Class<?> classe) {
		super(session, classe);
	}
	
	public MidiaSocial pesquisaMediaId(Long id) {
		System.out.print("pesquisaMediaId : " + id);
		return (MidiaSocial) session.load(MidiaSocial.class, id);
	}
	
	public MidiaSocial pesquisaMediaNome(String nome) {
		System.out.print("pesquisaMediaNome : " + nome);
		Criteria c = session.createCriteria(MidiaSocial.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));

		return (MidiaSocial)c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<MidiaSocial> pesquisaMedias(String nome){
		Criteria c = session.createCriteria(MidiaSocial.class);
		c.add(Restrictions.ilike("nome", "%" + nome + "%"));
		c.addOrder(Order.asc("nome"));
		
		return c.list();
	}
	
	/**
	 * Utilizando HQL 
	 * @param id
	 * @return
	 */
	public MidiaSocial buscaMedia(Long id){
		Query q = session.createQuery("select p from " + MidiaSocial.class.getName() + " as p where p.id like :id");
		
		q.setParameter("id", id);
		
		return (MidiaSocial)q.uniqueResult();
	}
}