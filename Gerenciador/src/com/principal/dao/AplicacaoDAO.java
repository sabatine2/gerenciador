package com.principal.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.principal.model.Aplicacao;

public class AplicacaoDAO extends DAO<Aplicacao> {

	public AplicacaoDAO(Session session, Class<Aplicacao> persistentClass) {
		super(session, persistentClass);
	}

	public Boolean isAtivo() {
		Criteria c = session.createCriteria(Aplicacao.class);
		if(c.uniqueResult() != null){
			System.out.print("isAtivo ");
			return true;
		}
		System.out.print("not is Ativo : ");
		return false;
	}
	
}
