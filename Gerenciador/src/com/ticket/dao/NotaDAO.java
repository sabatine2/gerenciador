package com.ticket.dao;

import org.hibernate.Session;
import com.ticket.model.Nota;

public class NotaDAO extends com.abstracts.dao.DAO<Nota> {
	
	public NotaDAO(Session session, Class<?> classe) {
		super(session, classe);
	}

}
