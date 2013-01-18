package com.auditorservico.dao;

import org.hibernate.Session;

import com.abstracts.dao.DAO;
import com.auditorservico.model.Atividade;

public class AtividadeEventoDAO extends DAO<Atividade> {

	public AtividadeEventoDAO(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
		// TODO Auto-generated constructor stub
	}

}
