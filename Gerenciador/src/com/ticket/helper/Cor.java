package com.ticket.helper;

import java.util.ArrayList;

import com.ticket.model.Prioridade;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;

public class Cor {
	
	public static String AMARELO  = "Amarelo - #FFFF00" ;
	public static String VERMELHO = "Vermelho - #FF0000";
	public static String LARANJA  = "Laranja - #FFA500";
	public static String AZUL     = "Azul - #87CEEB"; 
	public static String VERDE    = "Verde - #00FF00"; 
	public static String MARRON   = "Marron - #8B4513";
	public static String CINZA    = "Cinza - #CDC9C9";
	
	public static BeanItemContainer<String> getCores(){
		  BeanItemContainer<String> cores = new BeanItemContainer<String>(String.class);
		  cores.addItem(AMARELO);
		  cores.addItem(AZUL);
		  cores.addItem(CINZA);
		  cores.addItem(LARANJA);
		  cores.addItem(MARRON);
		  cores.addItem(VERMELHO);
		  cores.addItem(VERDE);
		  return cores;
	}
	
}
