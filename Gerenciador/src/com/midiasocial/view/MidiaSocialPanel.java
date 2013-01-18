package com.midiasocial.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.midiasocial.controller.MidiaSocialContatoController;
import com.midiasocial.model.MidiaSocial;
import com.vaadin.data.Container;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings({"serial", "rawtypes", "static-access"})
public class MidiaSocialPanel extends Panel{

	ArrayList<MidiaViewCliente> mediaViewCliente = null;
	private  Button buttonAdicionarItemMedia;

	public MidiaSocialPanel(Collection<MidiaSocial> listaMedia, final MidiaSocialContatoController mediaSocialController){
	setImmediate(false);
	setWidth("570px");
	setHeight("-1px");
	
	// common part: create layout
	final VerticalLayout verticalLayoutMedia = new VerticalLayout();
	verticalLayoutMedia.setImmediate(false);
	verticalLayoutMedia.setWidth("520px");
	verticalLayoutMedia.setHeight("-1px");
	verticalLayoutMedia.setMargin(false);
 		
	mediaViewCliente = new ArrayList<MidiaViewCliente>();
	
	for (Iterator iteratorContatos = listaMedia.iterator(); iteratorContatos.hasNext();) {
		MidiaSocial c = (MidiaSocial) iteratorContatos.next();
		MidiaViewCliente media = new MidiaViewCliente(c,mediaSocialController);
		mediaViewCliente.add(media);
		verticalLayoutMedia.addComponent(media);
		}
	
	HorizontalLayout horizontalLayoutMedia = new HorizontalLayout();
	horizontalLayoutMedia.setImmediate(false);
	horizontalLayoutMedia.setWidth("520px");
	horizontalLayoutMedia.setHeight("40px");
	horizontalLayoutMedia.setMargin(false);
	
	com.vaadin.ui.Label label = new com.vaadin.ui.Label("Midias Sociais");
    label.setContentMode(label.CONTENT_XHTML);
    horizontalLayoutMedia.addComponent(label);
	horizontalLayoutMedia.setComponentAlignment(label, Alignment.TOP_LEFT);
	
	// verticalLayoutMedia
	Resource res = new ThemeResource("../reindeer/Icons/plus.png");
	buttonAdicionarItemMedia = new Button("",new Button.ClickListener() {
		            // inline click-listener
	public void buttonClick(ClickEvent event) {
	    	MidiaSocial mediaSocial = new MidiaSocial(); 
	        MidiaViewCliente media = new MidiaViewCliente(mediaSocial,mediaSocialController);
	    	mediaViewCliente.add(media);
	     	verticalLayoutMedia.addComponent(new MidiaViewCliente(new MidiaSocial(),mediaSocialController));
          }
	 });
	buttonAdicionarItemMedia.setImmediate(true);
	buttonAdicionarItemMedia.setWidth("-1px");
	buttonAdicionarItemMedia.setHeight("-1px");
	buttonAdicionarItemMedia.setTabIndex(12);
	buttonAdicionarItemMedia.setIcon(res);
	horizontalLayoutMedia.addComponent(buttonAdicionarItemMedia);
	horizontalLayoutMedia.setComponentAlignment(buttonAdicionarItemMedia, Alignment.TOP_RIGHT);
	
	addComponent(horizontalLayoutMedia);
	// verticalLayoutMedia
	addComponent(verticalLayoutMedia);

	}
	
	public void createSubWindowTwitter(Container component, String type, String height, String width){
	       Window subwindow = new Window("Twitter" + type);
	
		   VerticalLayout layout = (VerticalLayout) subwindow.getContent();
		   layout.setMargin(true);
		   layout.setSpacing(true);
		   layout.setSizeFull();
			  
		   TwitterView twitter = new TwitterView(component);
		   
	       subwindow.setHeight(height);
		      
	       subwindow.setWidth(width);
	       subwindow.addComponent(twitter);
	       subwindow.center();
		   getWindow().addWindow(subwindow);
		
		   
	}
}
