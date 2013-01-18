package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.PalavraChaveMidia;
import com.abstracts.dao.DAO;
	
	public class PalavraChaveMidiaDAO extends DAO<PalavraChaveMidia> {
		
		public PalavraChaveMidiaDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public PalavraChaveMidia pesquisaPalavraChaveMidiaID(Long id) {
			System.out.print("pesquisaPalavraChaveMidiaId : " + id);
			return (PalavraChaveMidia) session.load(PalavraChaveMidia.class, id);
		}
		
		public PalavraChaveMidia pesquisaTipoServicoDescricao(String descricao) {
			System.out.print("pesquisaPalavraChaveMidiaDescricao : " + descricao);
			Criteria c = session.createCriteria(PalavraChaveMidia.class);
			c.add(Restrictions.ilike("nome", "%" + descricao + "%"));

			return (PalavraChaveMidia)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<PalavraChaveMidia> pesquisaPalavraChaves(String nome){
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
			Query q = session.createQuery("select p from " + PalavraChaveMidia.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (PalavraChaveMidia)q.uniqueResult();
		}
	}