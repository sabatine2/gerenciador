package com.midiasocial.helper;

import com.vaadin.data.util.BeanItemContainer;

public class TipoMidia {

	public static String EMAIL = "E-mail";
	public static String EMAIL2 = "E-mail Secund�rio";
	public static String FACEBOOK = "Facebook";
	public static String GTALK = "Google Talk";
	public static String SKYPE = "Skype";
	public static String TWITTER = "Twitter";
	public static String OUTROS = "Outros";
	
	public static String getTipoMedia(){
		
		String[] tipoMedia = new String[7];
		tipoMedia[0] = EMAIL;
		tipoMedia[1] = EMAIL2;
		tipoMedia[2] = FACEBOOK;
		tipoMedia[3] = GTALK;
		tipoMedia[4] = SKYPE;
		tipoMedia[5] = TWITTER;
		tipoMedia[6] = OUTROS;
		
		return tipoMedia[7];
	}
	
	public static BeanItemContainer<String> getTipoMediaContainer(){
		
		BeanItemContainer beans = new BeanItemContainer<String>(String.class);
		
		beans.addBean(EMAIL);
		beans.addBean(EMAIL2);
		beans.addBean(FACEBOOK);
		beans.addBean(GTALK);
		beans.addBean(SKYPE);
		beans.addBean(TWITTER);
		beans.addBean(OUTROS);
		return beans;
	}
}