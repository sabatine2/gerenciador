package com.midiasocial.view.adapter;

import com.vaadin.data.Container;

public interface ResultadoBusca extends Container.Indexed {

    public static final String MENSAGEM = "mensagem";
    public static final String DATA_CRIACAO_MIDIA = "dataCriacaoMidia";
    public static final String FOTO_URL = "fotoUrl";
    public static final String REDE_SOCIAL = "redeSocial";
    public static final String NOME = "nomeUsuario";
    
    public abstract void refresh();
    public abstract void setQuery(String queryString);
    public abstract void setEntitiesIncluded(boolean included);
    public abstract boolean isEntitiesIncluded();
    public abstract void setMaxResults(int results);
    public abstract int getMaxResults();


}