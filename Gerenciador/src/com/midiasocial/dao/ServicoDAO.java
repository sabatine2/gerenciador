package com.midiasocial.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.abstracts.dao.DAO;
import com.midiasocial.model.Servico;
import com.principal.helper.HibernateHelper;
	
	public class ServicoDAO extends DAO<Servico> {
		
		public ServicoDAO(Class<?> classe) {
			super(classe);
		}
		
		public Servico pesquisaUltimoServico(){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(Servico.class);
			c.addOrder( Order.desc( "id" ) );  
			c.setMaxResults(1);
			return (Servico)c.uniqueResult();
		}
		
		
	}