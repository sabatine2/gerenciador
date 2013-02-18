package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.Comentario;
import com.midiasocial.model.Publicacao;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.principal.helper.HibernateHelper;
import com.abstracts.dao.DAO;
	
	public class ComentarioDAO extends DAO<Comentario> {
		
		public ComentarioDAO(Class<?> classe) {
			super(classe);
		}
		
		public Comentario pesquisaComentarioID(Long id) {
			return (Comentario) load(id);
		}
		
		public Comentario pesquisaComentarioNome(String nome) {
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			return (Comentario)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Comentario> pesquisaComentarios(String nome){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			return c.list();
		}
	
		
		public List<Comentario> listaOffCurtir(){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("curtirOffline", true));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Comentario> listaOffComentario(){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("comentarOffline", true));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Comentario> listaOffCurtirRemover(){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("curtirRemoverOffline", true));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Comentario> listaOffDeletar(){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("deletarOffline", true));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Comentario> listaComentarioPublicacao(Publicacao publicacao){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("publicacao", publicacao));
			return c.list();
		}
		
		/**
		 * Utilizando HQL 
		 * @param usuarioAppMidiaSocial
		 * @return
		 */
		public List<Comentario> buscaComentariosOff(UsuarioAppMidiaSocial usuarioAppMidiaSocial){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query q = session.createQuery("select p from " + Comentario.class.getName() + " as p inner join p.publicacao as pub where pub.usuarioAppMidiaSocial eq :usuarioAppMidiaSocial");
			q.setParameter("usuarioAppMidiaSocial", usuarioAppMidiaSocial);
			return (List<Comentario>) q.list();
		}    
		    
		public Comentario buscaComentarios(String idMidia){
			org.hibernate.Session session = HibernateHelper.currentSession();
			Query q = session.createQuery("select p from " + Comentario.class.getName() + " as p where p.idMidia like :idMidia");
			q.setParameter("idMidia", idMidia);
			return (Comentario)q.uniqueResult();
		}
		
	}