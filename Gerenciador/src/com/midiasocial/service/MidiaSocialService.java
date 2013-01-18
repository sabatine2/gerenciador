package com.midiasocial.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import com.midiasocial.model.ResultadoBusca;
import com.midiasocial.model.UsuarioAppMidiaSocial;

public class MidiaSocialService extends Thread{

	private Long intervalo = 100l;
	private Boolean parar = true;
	
	public MidiaSocialService(Long intervalo){
		  this.intervalo = intervalo;
	}
	
	public void run(){
		
		while(parar){
		  	
			@SuppressWarnings("unchecked")
			ArrayList<UsuarioAppMidiaSocial> usuarioLista = (ArrayList<UsuarioAppMidiaSocial>) UsuarioAppMidiaSocial.listaUsuario();
			for (Iterator<UsuarioAppMidiaSocial> i = usuarioLista.iterator();i.hasNext();){
				UsuarioAppMidiaSocial u = i.next();
			
				if(u.getAppMidiaSocial().getRedeSocial().contentEquals("Facebook")){
					servicoFacebook(u);
				}
				else if(u.getAppMidiaSocial().getRedeSocial().contentEquals("Twitter")){
					servicoTwitter(u);
				}
			}
			
			try {
				sleep(intervalo*10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			interrupt();
			finalize();
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	
	}
	
	public void servicoTwitter(UsuarioAppMidiaSocial usuarioMidiaSocial){
		TwitterService s = new TwitterService(usuarioMidiaSocial); 
	    s.postToPublish(s.getInteracao());	
	    s.postToPublish(s.getUserTimeLine());
	}
	
	public void servicoFacebook(UsuarioAppMidiaSocial usuarioMidiaSocial){
		FacebookService s = new FacebookService(); 
	    s.postToPublish(s.getUserTimeLine(usuarioMidiaSocial),usuarioMidiaSocial);	
	   
	}
	
	public static ArrayList<ResultadoBusca> busca(String termoBusca){
		
		ArrayList<ResultadoBusca> listaResultado = new ArrayList<ResultadoBusca>();
		
		TwitterService t = new TwitterService(); 
		FacebookService f = new FacebookService();
		listaResultado = t.pesquisarTweetsSemAPP(termoBusca,listaResultado);
		listaResultado = f.pesqusiar(termoBusca,listaResultado);
		
		Collections.sort(listaResultado, new Comparator<ResultadoBusca>() {
			  public int compare(ResultadoBusca o1, ResultadoBusca o2) {
			      if (o1.getDataCriacaoMidia() == null || o2.getDataCriacaoMidia() == null)
			        return 0;
			      return o2.getDataCriacaoMidia().compareTo(o1.getDataCriacaoMidia());
			  }
			});
		
		return listaResultado;
	}
	
	public void parar(){
		parar = false;
	}
	
}
