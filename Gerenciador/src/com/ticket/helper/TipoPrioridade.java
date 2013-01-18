package com.ticket.helper;

import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TipoPrioridade {

	private static String alta = "Alta";
	private static String media = "M�dia";
	private static String baixa = "Baixa";
	
	public static String getTipoPrioridade(){
		
		String[] tipoPrioridade = new String[3];
		tipoPrioridade[0] = alta;
		tipoPrioridade[1] = media;
		tipoPrioridade[2] = baixa;
		
		return tipoPrioridade[3];
	}
	
	public static BeanItemContainer getTipoPrioridadeContainer(){
		
		BeanItemContainer beans = new BeanItemContainer<String>(String.class);
		
		beans.addBean(alta);
		beans.addBean(media);
		beans.addBean(baixa);
		return beans;
	}
}
