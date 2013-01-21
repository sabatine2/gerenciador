package com.midiasocial.view.adapter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.midiasocial.service.MidiaSocialService;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

/**
 * A container that uses the Twitter GET API to retrieve its results
 * 
 * @author 
 * 
 */
@SuppressWarnings("serial")
public class ResultadoBuscaAdapter extends IndexedContainer implements
	ResultadoBusca {

  
    protected Logger logger = Logger.getLogger(ResultadoBuscaAdapter.class
	    .getName());

    private boolean includeEntities = false;

    private String query = "";
  
   private int maxResults = 100;

    private short currentPage = 1;

    private boolean querying = false;

    private boolean queryIsQueued = false;
    
    private com.midiasocial.model.ResultadoBusca resultado;

    /**
     * Default constructor
     * 
     * @param queryString
     *            The query string to search for
     */
    public ResultadoBuscaAdapter() {
	     addContainerProperty(NOME, String.class, null);
    	 addContainerProperty(FOTO_URL, String.class, null);
    	 addContainerProperty(MENSAGEM, String.class, null);
    	 addContainerProperty(DATA_CRIACAO_MIDIA, Date.class, null);
    	 addContainerProperty(REDE_SOCIAL, String.class, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.twitterquerycontainer.SocialContainer#refresh()
     */
    public void refresh() {
	    removeAllItems();
	    doQuery();
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.example.twitterquerycontainer.SocialContainer#setQuery(java.lang.
     * String)
     */
    public void setQuery(String queryString) {
	if (queryString != null && !this.query.equals(queryString)) {
	    this.query = queryString;
	}
    }

    /**
     * Does the query and re-populates the container
     */
    protected synchronized void doQuery() {
	
    	int resultsProcessed = loadResponse();
	
    }

    
    protected int loadResponse() {
	 	ArrayList<com.midiasocial.model.ResultadoBusca> list = MidiaSocialService.busca(query);
    	
	 	for (Iterator<com.midiasocial.model.ResultadoBusca> i = list.iterator(); i.hasNext();) {
    		com.midiasocial.model.ResultadoBusca resultado = i.next();
		    Item item = getItem(addItem());
		    if (item != null) {
		        item.getItemProperty(REDE_SOCIAL).setValue(resultado.getRedeSocial());
		        item.getItemProperty(NOME).setValue(resultado.getNomeUsuario());
			    item.getItemProperty(FOTO_URL).setValue(resultado.getFotoUrl());
			    item.getItemProperty(MENSAGEM).setValue(resultado.getMensagem());
			    item.getItemProperty(DATA_CRIACAO_MIDIA).setValue(resultado.getDataCriacaoMidia());
			    this.resultado = resultado;
			    System.out.println(resultado.getMensagem());
			 }
	    }
    	return list.size();
	  }

    /**
     * Makes the HTTP Request
     * 
     * @param uri
     *            The uri to make the request to
     * @return Returns a reader for the results
     * @throws IOException
     */
    protected Reader doRequest(String uri) throws IOException {
	URL url = new URL(uri);
	URLConnection connection = url.openConnection();
	logger.log(Level.INFO, "Making a HTTP request to " + url);
	return new BufferedReader(new InputStreamReader(
		connection.getInputStream()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.example.twitterquerycontainer.SocialContainer#setEntitiesIncluded
     * (boolean)
     */
    public void setEntitiesIncluded(boolean included) {
	if (includeEntities != included) {
	    includeEntities = included;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.example.twitterquerycontainer.SocialContainer#isEntitiesIncluded()
     */
    public boolean isEntitiesIncluded() {
	return includeEntities;
    }

     /*
     * (non-Javadoc)
     * 
     * @see com.example.twitterquerycontainer.SocialContainer#setMaxResults(int)
     */
    public void setMaxResults(int results) {
	if (maxResults != results && results > 0) {
	    this.maxResults = results;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.example.twitterquerycontainer.SocialContainer#getMaxResults()
     */
    public int getMaxResults() {
	return maxResults;
    }

	/**
	 * @return the publicacao
	 */
	public com.midiasocial.model.ResultadoBusca getResultadoBusca() {
		return resultado;
	}

	/**
	 * @param publicacao the publicacao to set
	 */
	public void setPublicacao(com.midiasocial.model.ResultadoBusca resultado) {
		this.resultado = resultado;
	}
    
    

}