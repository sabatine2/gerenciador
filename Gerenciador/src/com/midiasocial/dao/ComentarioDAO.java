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
import com.abstracts.dao.DAO;
	
	public class ComentarioDAO extends DAO<Comentario> {
		
		public ComentarioDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Comentario pesquisaComentarioID(Long id) {
			System.out.print("pesquisaComentarioId : " + id);
			return (Comentario) session.load(Comentario.class, id);
		}
		
		public Comentario pesquisaComentarioNome(String nome) {
			System.out.print("pesquisaComentarioNome : " + nome);
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (Comentario)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Comentario> pesquisaComentarios(String nome){
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		
		public List<Comentario> listaOffCurtir(){
				
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("curtirOffline", true));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Comentario> listaOffComentario(){
			
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("comentarOffline", true));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Comentario> listaOffCurtirRemover(){
			
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("curtirRemoverOffline", true));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Comentario> listaOffDeletar(){
			
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("deletarOffline", true));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Comentario> listaComentarioPublicacao(Publicacao publicacao){
			
			Criteria c = session.createCriteria(Comentario.class);
			c.add(Restrictions.eq("publicacao", publicacao));
			return c.list();
		}
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public List<Comentario> buscaComentariosOff(UsuarioAppMidiaSocial usuarioAppMidiaSocial){
			Query q = session.createQuery("select p from " + Comentario.class.getName() + " as p inner join p.publicacao as pub where pub.usuarioAppMidiaSocial eq :usuarioAppMidiaSocial");
			
			q.setParameter("usuarioAppMidiaSocial", usuarioAppMidiaSocial);
			
			return (List<Comentario>) q.list();
		}    
		    
		public Comentario buscaComentarios(String idMidia){
			Query q = session.createQuery("select p from " + Comentario.class.getName() + " as p where p.idMidia like :idMidia");
			
			q.setParameter("idMidia", idMidia);
			
			return (Comentario)q.uniqueResult();
		}
		
		public Comentario buscaComentarios(Long id){
			Query q = session.createQuery("select p from " + Comentario.class.getName() + " as p where p.idInterno like :idInterno");
			
			q.setParameter("idInterno", id);
			
			return (Comentario)q.uniqueResult();
		}
	}