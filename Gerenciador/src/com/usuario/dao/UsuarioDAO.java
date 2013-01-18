	package com.usuario.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.usuario.model.Usuario;
    
    public class UsuarioDAO extends DAO<Usuario> {
		
		public UsuarioDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Usuario pesquisaUsuarioById(Long id) {
			System.out.print("pesquisaUsuarioById : " + id);
			return (Usuario) session.load(Usuario.class, id);
		}
		
		public Usuario pesquisaUsuarioByNome(String nome) {
			System.out.print("pesquisaUsuarioByNome : " + nome);
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.eq("nome", nome));
        	return (Usuario)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Usuario> pesquisaUsuarios(String nome){
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
		
		public Usuario buscaUsuario(Long id){
			Query q = session.createQuery("select p from " + Usuario.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Usuario)q.uniqueResult();
		}
		
		public Usuario autenticaUsuario(String nome, String senha){
			Query q = session.createQuery("select p from " + Usuario.class.getName() + " as p where p.nome like :nome and p.senha = :senha");
			
			q.setParameter("nome", nome);
			q.setParameter("senha", senha);
					
			return (Usuario)q.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Usuario> listaUsuario(){
			Query q = session.createQuery("select p.nome, p.senha, p.nivel from " + Usuario.class.getName() + " as p");
			return q.list();
		}
		
		public void deleteUsuarioNome(String nome){
			Query q = session.createQuery("delete from " + Usuario.class.getName() + " as p where p.nome = "+nome);
			q.executeUpdate();
			
		}
	}
