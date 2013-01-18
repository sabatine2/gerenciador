package com.auditorservico.dao;

import org.hibernate.Session;

import com.abstracts.dao.DAO;
import com.auditorservico.model.Localizacao;

public class LocalizacaoDAO extends DAO<Localizacao>{

	public LocalizacaoDAO(Session session, Class<?> classe) {
		super(session,classe);
		// TODO Auto-generated constructor stub
	}

}
