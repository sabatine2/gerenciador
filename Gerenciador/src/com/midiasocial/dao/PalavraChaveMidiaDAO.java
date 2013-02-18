package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.PalavraChaveMidia;
import com.principal.helper.HibernateHelper;
import com.abstracts.dao.DAO;
	
	public class PalavraChaveMidiaDAO extends DAO<PalavraChaveMidia> {
		
		public PalavraChaveMidiaDAO(Class<?> classe) {
			super(classe);
		}
		
		public PalavraChaveMidia pesquisaTipoServicoDescricao(String descricao) {
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(PalavraChaveMidia.class);
			c.add(Restrictions.ilike("nome", "%" + descricao + "%"));
			return (PalavraChaveMidia)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<PalavraChaveMidia> pesquisaPalavraChaves(String nome){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(PalavraChaveMidia.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public PalavraChaveMidia buscaPalavraChaves(Long id){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query q = session.createQuery("select p from " + PalavraChaveMidia.class.getName() + " as p where p.id like :id");
			q.setParameter("id", id);
			return (PalavraChaveMidia)q.uniqueResult();
		}
	}