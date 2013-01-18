package com.ticket.dao;

import org.hibernate.Session;

import com.abstracts.dao.DAO;
import com.ticket.model.Anexo;

public class AnexoDAO extends DAO<Anexo> {
	
	public AnexoDAO(Session session, Class<?> classe) {
		super(session, classe);
	}

}
