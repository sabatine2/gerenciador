package com.relatorio;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.mysql.jdbc.Connection;
import com.principal.helper.HibernateUtil;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Window;

public class IreportConexao {

	public Connection getConexao() throws SQLException { 
		Connection connection = null; 
		String driver = "com.mysql.jdbc.Driver"; 
		String url = "jdbc:mysql://localhost/gerenciadordespesas"; 
		String login = "root"; 
		String senha = ""; 
		
		try { 
		Class.forName(driver); 
		connection = (Connection) DriverManager.getConnection(url, login, senha); 
		} catch (ClassNotFoundException e) { 
		e.printStackTrace(); 
		} catch (SQLException e) { 
		e.printStackTrace();	
		} 
		return connection; 
	} 
	
	 public StreamResource.StreamSource gerarel(final Long id, final String nomeRelatorio){
		 StreamResource.StreamSource source = new StreamResource.StreamSource() {

			 
			@SuppressWarnings("unchecked")
         	public InputStream getStream() {
				Map parametros = new HashMap(); 
				parametros.put("id", id); 
				byte[] b = null;
				try {
					org.hibernate.Session s = HibernateUtil.openSession();
					b = JasperRunManager.runReportToPdf(getClass().getClassLoader().getResourceAsStream(nomeRelatorio),parametros);
				} catch (JRException ex) {
              
				} 
				//throw new UnsupportedOperationException("Not supported yet.");
				return new ByteArrayInputStream(b);
			}
		 };
     return source;
	 }
}