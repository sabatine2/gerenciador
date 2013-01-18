package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.abstracts.dao.DAO;
	
@SuppressWarnings("unchecked")
	public class UsuarioAppMidiaSocialDAO extends DAO<UsuarioAppMidiaSocial> {
		
		public UsuarioAppMidiaSocialDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public UsuarioAppMidiaSocial pesquisaUsuarioID(Long id) {
			System.out.print("pesquisaUsuarioId : " + id);
			return (UsuarioAppMidiaSocial) session.load(UsuarioAppMidiaSocial.class, id);
		}
		
		public UsuarioAppMidiaSocial pesquisaUsuarioNome(String nome) {
			System.out.print("pesquisaUsuarioNome : " + nome);
			Criteria c = session.createCriteria(UsuarioAppMidiaSocial.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (UsuarioAppMidiaSocial)c.uniqueResult();
		}
		
		public List<UsuarioAppMidiaSocial> pesquisaUsuarios(String nome){
			Criteria c = session.createCriteria(UsuarioAppMidiaSocial.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public UsuarioAppMidiaSocial buscaUsuarios(Long id){
			Query q = session.createQuery("select p from " + UsuarioAppMidiaSocial.class.getName() + " as p where p.idInterno like :idInterno");
			
			q.setParameter("idInterno", id);
			
			return (UsuarioAppMidiaSocial)q.uniqueResult();
		}
		
		public List<UsuarioAppMidiaSocial> buscaStatus(){
			Query q = session.createQuery("select * from " + UsuarioAppMidiaSocial.class.getName() + " where user_status = 'Ativo'");
			
			return q.list();			
		}
	}