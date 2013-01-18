package com.midiasocial.controller;

import java.util.Collection;

import com.contato.model.Contato;
import com.midiasocial.model.MidiaSocial;
import com.midiasocial.view.MidiaSocialPanel;

import fi.jasoft.twitterquerycontainer.TwitterQueryContainer;

public class MidiaSocialContatoController {

	private Collection<MidiaSocial> listaMedia;
	private MidiaSocialPanel mediaView;
	private Contato contato;
	
	public MidiaSocialContatoController(Collection<MidiaSocial> listaMedia, Contato contato){
		this.listaMedia = listaMedia;
		this.contato = contato;
	}
	
	public MidiaSocialPanel getView(){
	   mediaView = new MidiaSocialPanel(listaMedia,this);
	   return mediaView;
	}
	
	public void addMedia(MidiaSocial media){
		media.setContato(contato);
		if(media.getId_media() == null){
			listaMedia.add(media);
		}
    }
	
	public void visualizar(MidiaSocial media){
		
	  // if(media.getTipoMedia() == TipoMedia.TWITTER){
	       TwitterQueryContainer container = new TwitterQueryContainer();
		   container.setQuery(media.getEndereco());
		   container.refresh();
		   mediaView.createSubWindowTwitter(container, media.getEndereco(), "100%", "740px");
	  // }
	}
	
	public boolean removeMedia(MidiaSocial media){
		if(media.getId_media() != null){
		    media.remover();
		}
		
		listaMedia.remove(media);	
		return true;
		
	}

	/**
	 * @return the listaMedia
	 */
	public Collection<MidiaSocial> getListaMedia() {
		return listaMedia;
	}

	/**
	 * @param listaMedia the listaMedia to set
	 */
	public void setListaMedia(Collection<MidiaSocial> listaMedia) {
		this.listaMedia = listaMedia;
	}
	
	
}
