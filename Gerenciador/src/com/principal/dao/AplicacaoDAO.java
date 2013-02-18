package com.principal.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.principal.helper.HibernateHelper;
import com.principal.model.Aplicacao;

public class AplicacaoDAO extends DAO<Aplicacao> {

	public AplicacaoDAO(Class<Aplicacao> persistentClass) {
		super(persistentClass);
	}

	public Boolean isAtivo() {
		Session session = HibernateHelper.openSession();
		Criteria c = session.createCriteria(Aplicacao.class);
		if(c.uniqueResult() != null){
			System.out.print("isAtivo ");
			return true;
		}
		System.out.print("not is Ativo : ");
		session.close();
		return false;
	}
	
}
