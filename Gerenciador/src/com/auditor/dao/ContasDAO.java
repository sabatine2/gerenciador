package com.auditor.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abstracts.dao.DAO;
import com.auditor.model.Conta;
	
	public class ContasDAO extends DAO<Conta> {
		
		public ContasDAO(Session session, Class<?> classe) {
			super(session, classe);
		}
		
		public Conta pesquisaContaID(Long id) {
			System.out.print("pesquisaContaId : " + id);
			return (Conta) session.load(Conta.class, id);
		}
		
		public Conta pesquisaContaNome(String nome) {
			System.out.print("pesquisaContaNome : " + nome);
			Criteria c = session.createCriteria(Conta.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));

			return (Conta)c.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Conta> pesquisaContas(String nome){
			Criteria c = session.createCriteria(Conta.class);
			c.add(Restrictions.ilike("nome", "%" + nome + "%"));
			c.addOrder(Order.asc("nome"));
			
			return c.list();
		}
	
		/**
		 * Utilizando HQL 
		 * @param id
		 * @return
		 */
		public Conta buscaContas(Long id){
			Query q = session.createQuery("select p from " + Conta.class.getName() + " as p where p.id like :id");
			
			q.setParameter("id", id);
			
			return (Conta)q.uniqueResult();
		}
		
		public static HashMap<String, Double> pesquisaGrafico(String comandoSQL){
			
			HashMap<String, Double> dadoGrafico = new HashMap<String, Double>();
			
			Connection conn;
			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost/gerenciadorteste?user=root&password=");
				Statement stm = conn.createStatement();  
				ResultSet rs = stm.executeQuery(comandoSQL);
		
				
				
				while (rs.next()) {  
				
					BigDecimal itemconta_valor = new BigDecimal(rs.getDouble("valor_total"));
				    String itemconta_tipo = rs.getString("tipo_descricao");
				    dadoGrafico.put(itemconta_tipo, itemconta_valor.doubleValue());
				    
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return dadoGrafico;
		}
	}