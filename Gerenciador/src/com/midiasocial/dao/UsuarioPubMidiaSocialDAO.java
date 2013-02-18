package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.UsuarioPubMidiaSocial;
import com.principal.helper.HibernateHelper;
import com.abstracts.dao.DAO;
	
@SuppressWarnings("unchecked")
	public class UsuarioPubMidiaSocialDAO extends DAO<UsuarioPubMidiaSocial> {
		
		public UsuarioPubMidiaSocialDAO(Class<?> classe) {
			super(classe);
		}
		
		public UsuarioPubMidiaSocial pesquisaUsuarioNome(String nome) {
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(UsuarioPubMidiaSocial.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (UsuarioPubMidiaSocial)c.uniqueResult();
		}
		
		public UsuarioPubMidiaSocial pesquisaUsuarioIdMidia(String id){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(UsuarioPubMidiaSocial.class);
			c.add(Restrictions.eq("idMidia", id));
			return (UsuarioPubMidiaSocial) c.uniqueResult();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public UsuarioPubMidiaSocial buscaUsuarios(Long id){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query q = session.createQuery("select p from " + UsuarioPubMidiaSocial.class.getName() + " as p where p.idInterno like :idInterno");
			q.setParameter("idInterno", id);
			return (UsuarioPubMidiaSocial)q.uniqueResult();
		}
		
		public List<UsuarioPubMidiaSocial> buscaStatus(){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query q = session.createQuery("select * from " + UsuarioPubMidiaSocial.class.getName() + " where user_status = 'Ativo'");
			return q.list();			
		}
	}