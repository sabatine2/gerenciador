package com.midiasocial.dao;

import org.hibernate.Session;

import com.abstracts.dao.DAO;
import com.midiasocial.model.Servico;
	
	public class ServicoDAO extends DAO<Servico> {
		
		public ServicoDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Servico pesquisaUsuarioID(Long id) {
			System.out.print("pesquisaUsuarioId : " + id);
			return (Servico) session.load(Servico.class, id);
		}
		
	}