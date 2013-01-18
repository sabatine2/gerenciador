package com.principal.helper;

import com.vaadin.data.util.BeanItemContainer;

public class TipoEstado {

	private static String acre = "AC - Acre";
	private static String alagoas = "AL - Alagoas";
	private static String amapa = "AP - Amapa";
	private static String amazonas = "AM - Amazonas";
	private static String bahia = "BA - Bahia";
	private static String ceara = "CE - Ceara";
	private static String distritoFederal = "DF - Distrito Federal";
	private static String goias = "GO - Goias";
	private static String espiritoSanto = "ES - Espirito Santo";
	private static String maranhao = "MA - Maranhao";
	private static String matoGrosso = "MT - Mato Grosso";
	private static String matoGrossoDoSul = "MS - Mato Grosso do Sul";
	private static String minasGerais = "MG - Minas Gerais";
	private static String para = "PA - Para";
	private static String paraiba = "PB - Paraiba";
	private static String parana = "PR - Parana";
	private static String pernambuco = "PE - Pernambuco";
	private static String piaui = "PI - Piaui";
	private static String rioDeJaneiro = "RJ - Rio de Janeiro";
	private static String rioGrandeDoNorte = "RN - Rio Grand do Norte";
	private static String rioGrandeDoSul = "RS - Rio Grande do Sul";
	private static String rondonia = "RO - Rondonia";
	private static String roraima = "RR - Roraima";
	private static String saoPaulo = "SP - Sao Paulo";
	private static String santaCatarina = "SC - Santa Catarina";
	private static String sergipe = "SE - Sergipe";
	private static String tocantins = "TO - Tocantins";	
	
	public static String getTipoEstado(){
		
		String[] tipoEstado = new String[28];
		tipoEstado[0] = acre;
		tipoEstado[1] = alagoas;
		tipoEstado[2] = amapa;
		tipoEstado[3] = amazonas;
		tipoEstado[4] = bahia;
		tipoEstado[6] = ceara;
		tipoEstado[7] = distritoFederal;
		tipoEstado[8] = goias;
		tipoEstado[9] = espiritoSanto;
		tipoEstado[10] = maranhao;
		tipoEstado[11] = matoGrosso;
		tipoEstado[12] = matoGrossoDoSul;
		tipoEstado[13] = minasGerais;
		tipoEstado[14] = para;
		tipoEstado[15] = paraiba;
		tipoEstado[16] = parana;
		tipoEstado[17] = pernambuco;
		tipoEstado[18] = piaui;
		tipoEstado[19] = rioDeJaneiro;
		tipoEstado[20] = rioGrandeDoNorte;
		tipoEstado[21] = rioGrandeDoSul;
		tipoEstado[22] = rondonia;
		tipoEstado[23] = roraima;
		tipoEstado[24] = saoPaulo;
		tipoEstado[25] = santaCatarina;
		tipoEstado[26] = sergipe;
		tipoEstado[27] = tocantins;
		
		return tipoEstado[28];
	}
	
	public static BeanItemContainer<String> getTipoEstadoContainer(){
		
		BeanItemContainer<String> beans = new BeanItemContainer<String>(String.class);
		
		beans.addBean(acre);
		beans.addBean(alagoas);
		beans.addBean(amapa);
		beans.addBean(amazonas);
		beans.addBean(bahia);
		beans.addBean(ceara);
		beans.addBean(distritoFederal);
		beans.addBean(goias);
		beans.addBean(espiritoSanto);
		beans.addBean(maranhao);
		beans.addBean(matoGrosso);
		beans.addBean(matoGrossoDoSul);
		beans.addBean(minasGerais);
		beans.addBean(para);
		beans.addBean(paraiba);
		beans.addBean(parana);
		beans.addBean(pernambuco);
		beans.addBean(piaui);
		beans.addBean(rioDeJaneiro);
		beans.addBean(rioGrandeDoNorte);
		beans.addBean(rioGrandeDoSul);
		beans.addBean(rondonia);
		beans.addBean(roraima);
		beans.addBean(saoPaulo);
		beans.addBean(santaCatarina);
		beans.addBean(sergipe);
		beans.addBean(tocantins);
		return beans;
	}
}