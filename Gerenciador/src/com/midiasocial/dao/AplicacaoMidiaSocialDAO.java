package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.AplicacaoMidiaSocial;
import com.principal.helper.HibernateHelper;
import com.abstracts.dao.DAO;
	
	public class AplicacaoMidiaSocialDAO extends DAO<AplicacaoMidiaSocial> {
		
		public AplicacaoMidiaSocialDAO(Class<?> classe) {
			super(classe);
		}
		
		@SuppressWarnings("unchecked")
		public List<AplicacaoMidiaSocial> pesquisaApps(String nome){
			Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(AplicacaoMidiaSocial.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	}