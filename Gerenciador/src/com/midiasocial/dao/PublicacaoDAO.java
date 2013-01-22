package com.midiasocial.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.midiasocial.model.Publicacao;
import com.midiasocial.model.UsuarioAppMidiaSocial;
import com.abstracts.dao.DAO;
	
	public class PublicacaoDAO extends DAO<Publicacao> {
		
		public PublicacaoDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Publicacao pesquisaPostID(Long id) {
			System.out.print("pesquisaPostId : " + id);
			return (Publicacao) session.load(Publicacao.class, id);
		}
		
		public Publicacao pesquisaPostNome(String nome) {
			System.out.print("pesquisaPostNome : " + nome);
			Criteria c = session.createCriteria(Publicacao.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (Publicacao)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Publicacao> pesquisaPosts(String nome){
			Criteria c = session.createCriteria(Publicacao.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Publicacao> pesquisaPosts(UsuarioAppMidiaSocial app){
			Criteria c = session.createCriteria(Publicacao.class);
			c.add(Restrictions.eq("usuarioAppMidiaSocial", app));
			c.addOrder(Order.desc("dataCriacaoMidia"));
			
			return c.list();
		}
		
       
       public List<Publicacao> listaOffPublicacao(UsuarioAppMidiaSocial usuario){
			
			Criteria c = session.createCriteria(Publicacao.class);
			c.add(Restrictions.eq("publicarOffline", true));
			c.add(Restrictions.eq("usuarioAppMidiaSocial", usuario));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		
		public List<Publicacao> listaOffCurtir(UsuarioAppMidiaSocial usuario){
			
			Criteria c = session.createCriteria(Publicacao.class);
			c.add(Restrictions.eq("curtirOffline", true));
			c.add(Restrictions.eq("usuarioAppMidiaSocial", usuario));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Publicacao> listaOffCurtirRemover(UsuarioAppMidiaSocial usuario){
			
			Criteria c = session.createCriteria(Publicacao.class);
			c.add(Restrictions.eq("curtirRemoverOffline", true));
			c.add(Restrictions.eq("usuarioAppMidiaSocial", usuario));
			c.addOrder(Order.asc("dataCriacao"));
			
			return c.list();
		}
		
		public List<Publicacao> listaOffDeletar(UsuarioAppMidiaSocial usuario){
			
			Criteria c = session.createCriteria(Publicacao.class);
			c.add(Restrictions.eq("deletarOffline", true));
			c.add(Restrictions.eq("usuarioAppMidiaSocial", usuario));
			c.addOrder(Order.asc("dataCriacao"));
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		
		public List<Publicacao> listlastUpdate(String idUsuarioMidia){
			
			Criteria c = session.createCriteria(Publicacao.class);
			c.add(Restrictions.eq("idUsuario", idUsuarioMidia));
			c.addOrder(Order.desc("dataCriacaoMidia"));
			c.setMaxResults(3);
			return c.list();
		}

		public Publicacao buscaPosts(Long id){
			Query q = session.createQuery("select p from " + Publicacao.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Publicacao)q.uniqueResult();
		}
		
		public Publicacao buscaPosts(String idMidia){
			Query q = session.createQuery("select p from " + Publicacao.class.getName() + " as p where p.idMidia like :idMidia");
			
			q.setParameter("idMidia", idMidia);
			
			return (Publicacao)q.uniqueResult();
		}
	}