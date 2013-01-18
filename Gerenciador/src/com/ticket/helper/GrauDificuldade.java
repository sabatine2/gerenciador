package com.ticket.helper;

import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GrauDificuldade {

	private static String dificil = "Dif�cil";
	private static String moderado = "Moderado";
	private static String facil = "Facil";
	
	public static String getGrauDificuldade(){
		
		String[] tipoPrioridade = new String[3];
		tipoPrioridade[0] = dificil;
		tipoPrioridade[1] = moderado;
		tipoPrioridade[2] = facil;
		
		return tipoPrioridade[3];
	}
	
	public static BeanItemContainer getGrauDificuldadeContainer(){
		
		BeanItemContainer beans = new BeanItemContainer<String>(String.class);
		
		beans.addBean(dificil);
		beans.addBean(moderado);
		beans.addBean(facil);
		return beans;
	}
}
