package com.midiasocial.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.abstracts.dao.DAO;
import com.midiasocial.model.ServicoAtualizacao;
	
	public class ServicoAtualizacaoDAO extends DAO<ServicoAtualizacao> {
		
		public ServicoAtualizacaoDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		@SuppressWarnings("unchecked")
		public java.util.List<ServicoAtualizacao> listAll() {
			Criteria c = session.createCriteria(ServicoAtualizacao.class);
			return c.list();
		}
		
		public ServicoAtualizacao pesquisaUltimaAtualizacao() {
			Criteria c = session.createCriteria(ServicoAtualizacao.class);
			c.addOrder( Order.desc( "id" ) );  
			c.setMaxResults(1);
			return (ServicoAtualizacao)c.uniqueResult();
		}
	}