package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.AplicacaoMidiaSocial;
import com.abstracts.dao.DAO;
	
	public class AplicacaoMidiaSocialDAO extends DAO<AplicacaoMidiaSocial> {
		
		public AplicacaoMidiaSocialDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public AplicacaoMidiaSocial pesquisaAppID(Long id) {
			System.out.print("pesquisaAppId : " + id);
			return (AplicacaoMidiaSocial) session.load(AplicacaoMidiaSocial.class, id);
		}
		
		public AplicacaoMidiaSocial pesquisaAppNome(String nome) {
			System.out.print("pesquisaAppNome : " + nome);
			Criteria c = session.createCriteria(AplicacaoMidiaSocial.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (AplicacaoMidiaSocial)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<AplicacaoMidiaSocial> pesquisaApps(String nome){
			Criteria c = session.createCriteria(AplicacaoMidiaSocial.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public AplicacaoMidiaSocial buscaApps(Long id){
			Query q = session.createQuery("select p from " + AplicacaoMidiaSocial.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (AplicacaoMidiaSocial)q.uniqueResult();
		}
	}