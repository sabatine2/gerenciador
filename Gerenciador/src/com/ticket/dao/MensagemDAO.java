package com.ticket.dao;

import org.hibernate.Session;

import com.abstracts.dao.DAO;
import com.ticket.model.Mensagem;

public class MensagemDAO extends DAO<Mensagem> {
	
	public MensagemDAO(Session session, Class<?> classe) {
		super(session, classe);
	}

}
