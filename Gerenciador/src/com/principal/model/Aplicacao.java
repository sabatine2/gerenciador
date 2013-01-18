package com.principal.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.principal.dao.AplicacaoDAO;
import com.principal.helper.HibernateUtil;
import com.vaadin.data.util.BeanItemContainer;

@XmlRootElement(name = "aplicacao")
@XmlAccessorType(XmlAccessType.FIELD)
public class Aplicacao {
	
	@XmlElement(name = "id")
	private Long    id;
	@XmlElement(name = "datacriacao")
	private Date    dataCriacao;
	@XmlElement(name = "ativo")
	private Boolean ativo = false;
	@XmlElement(name = "nome")
	private String  nomeAplicacao = "Gerenciador Persys";
	@XmlElement(name = "versao")
	private String  versaoAplicacao = "1.0";
	@XmlElement(name = "versaodata")
	private Date  versaoAplicacaoData = new Date();
	
	public Aplicacao(){}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNomeAplicacao() {
		return nomeAplicacao;
	}

	public void setNomeAplicacao(String nomeAplicacao) {
		this.nomeAplicacao = nomeAplicacao;
	}

	public String getVersaoAplicacao() {
		return versaoAplicacao;
	}

	public void setVersaoAplicacao(String versaoAplicacao) {
		this.versaoAplicacao = versaoAplicacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVersaoAplicacaoData() {
		return versaoAplicacaoData;
	}

	public void setVersaoAplicacaoData(Date versaoAplicacaoData) {
		this.versaoAplicacaoData = versaoAplicacaoData;
	}

	@Override
	public String toString() {
		return "Aplicacao [id=" + id + ", dataCriacao=" + dataCriacao
				+ ", ativo=" + ativo + ", nomeAplicacao=" + nomeAplicacao
				+ ", versaoAplicacao=" + versaoAplicacao
				+ ", versaoAplicacaoData=" + versaoAplicacaoData + "]";
	}
	
	public String geraArquivo(Object obj, String fileName){
		final StringWriter out = new StringWriter();
		Marshaller marshaller = null;
		JAXBContext context = null;
		try{
			context = JAXBContext.newInstance(obj.getClass());
			marshaller = context.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		}
		catch(PropertyException e){
			e.printStackTrace();
		}
		catch (JAXBException e) {
		    e.printStackTrace();
		}
		
		Writer writer = null;
		try{
			writer = new FileWriter(fileName);
			marshaller.marshal(obj, writer);
		}
		catch(JAXBException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
			  if(writer != null)
				writer.close();
			  }
			catch (Exception e) {
				e.getMessage();
			}
		}
		return out.toString();
	}
	   
	public static boolean isAtivo(){
	   
	   Aplicacao app = (Aplicacao) carregaArquivo(Aplicacao.class, "app.xml");
	   if(app == null){
		  app = new Aplicacao();
		  app.geraArquivo(app, "app.xml"); 
	   }
	   
	   return app.getAtivo();
	} 
	
	public static Object carregaArquivo(Class classe,String fileName){
		
		JAXBContext context = null;
		try{
			context = JAXBContext.newInstance(classe);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(new File(fileName));
		}
		catch (JAXBException e) {
		    e.printStackTrace();
		}
		return null;
	}
	
}
