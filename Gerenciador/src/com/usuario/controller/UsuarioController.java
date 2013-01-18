package com.usuario.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.usuario.model.Usuario;
import com.usuario.view.UsuarioConectadosView;
import com.usuario.view.UsuarioView;
import com.vaadin.Application;
import com.vaadin.terminal.StreamResource;

public class UsuarioController {

	UsuarioView usuarioView = null;
	UsuarioConectadosView usuarioConectadosView = null;
	
	Usuario usuario;
	
	public UsuarioController(){
	}
	
	public boolean salvar(){
		  	
		 return true;
	}
	
    public boolean alterarSenha(){
		
		return true;
	}
	
	public void visualizar(Object event){
		setUsuario((Usuario)event);
	}
	
	
	public UsuarioView getView(){
		if(usuarioView == null){
		
			usuarioView = new UsuarioView(this);
			usuarioView.modoTabela();
		}
		usuarioConectadosView = null;
		return this.usuarioView;
		
	}
	

	public UsuarioConectadosView getConectadosView(){
		if(usuarioConectadosView == null){
			usuarioConectadosView = new UsuarioConectadosView(this);
			usuarioConectadosView.modoTabela();
		}
		usuarioView = null;
		return this.usuarioConectadosView;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public StreamResource geraRelatorio(Application app) throws JRException{
		 StreamResource.StreamSource source = new StreamResource.StreamSource() {
			    
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public InputStream getStream() {
				JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(Usuario.listaUsuario());
				JasperPrint j=null;
				try {
					j = JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream("UsuarioRelatorio.jasper"), new HashedMap(),beanCollection);
				} catch (JRException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
				byte[] b = null;
				try {
					b = JasperExportManager.exportReportToPdf(j);
				} catch (JRException ex) {
	          } 
		  	  return new ByteArrayInputStream(b);
			  }
			 };
			 
			StreamResource resource = new StreamResource(source, "relatorio2.pdf", app);
	        resource.setMIMEType("application/pdf");
	        
	      return resource;
	}
}
