package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.principal.helper.HibernateHelper;
import com.abstracts.dao.DAO;
	
@SuppressWarnings("unchecked")
	public class UsuarioAppMidiaSocialDAO extends DAO<UsuarioAppMidiaSocial> {
		
		public UsuarioAppMidiaSocialDAO(Class<?> classe) {
			super(classe);
		}
		
		public UsuarioAppMidiaSocial pesquisaUsuarioNome(String nome) {
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(UsuarioAppMidiaSocial.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (UsuarioAppMidiaSocial)c.uniqueResult();
		}
		
		public List<UsuarioAppMidiaSocial> pesquisaUsuarios(String nome){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(UsuarioAppMidiaSocial.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		public List<UsuarioAppMidiaSocial> listaAtivo(){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(UsuarioAppMidiaSocial.class);
			c.add(Restrictions.eq("status", "Ativo"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
		
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public UsuarioAppMidiaSocial buscaUsuarios(Long id){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query q = session.createQuery("select p from " + UsuarioAppMidiaSocial.class.getName() + " as p where p.idInterno like :idInterno");
			q.setParameter("idInterno", id);
			return (UsuarioAppMidiaSocial)q.uniqueResult();
		}
		
		public List<UsuarioAppMidiaSocial> buscaStatus(){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query q = session.createQuery("select * from " + UsuarioAppMidiaSocial.class.getName() + " where user_status = 'Ativo'");
			return q.list();			
		}
	}