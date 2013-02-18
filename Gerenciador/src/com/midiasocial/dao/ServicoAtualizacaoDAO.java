package com.midiasocial.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import com.abstracts.dao.DAO;
import com.midiasocial.model.ServicoAtualizacao;
import com.principal.helper.HibernateHelper;
	
	public class ServicoAtualizacaoDAO extends DAO<ServicoAtualizacao> {
		
		public ServicoAtualizacaoDAO(Class<?> classe) {
			super(classe);
		}
		
		@SuppressWarnings("unchecked")
		public java.util.List<ServicoAtualizacao> listAll() {
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(ServicoAtualizacao.class);
			return c.list();
		}
		
		public ServicoAtualizacao pesquisaUltimaAtualizacao() {
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(ServicoAtualizacao.class);
			c.addOrder( Order.desc( "id" ) );  
			c.setMaxResults(1);
			return (ServicoAtualizacao)c.uniqueResult();
		}
		
		public int removerAll(){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query query = session.createQuery("DELETE FROM " + ServicoAtualizacao.class);
			return query.executeUpdate();
		}
	}