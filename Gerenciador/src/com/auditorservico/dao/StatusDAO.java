package com.auditorservico.dao;

import org.hibernate.Session;

import com.abstracts.dao.DAO;
import com.auditorservico.model.Status;

public class StatusDAO extends DAO<Status> {

	public StatusDAO(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
		// TODO Auto-generated constructor stub
	}

}
