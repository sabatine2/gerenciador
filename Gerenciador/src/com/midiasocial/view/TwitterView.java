package com.midiasocial.view;

import java.util.Iterator;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings({"serial", "rawtypes"})
public class TwitterView extends VerticalLayout {

	
	public TwitterView(Container container) {
		// TODO Auto-generated constructor stub
		
		setWidth("720px");
		setHeight("100%");
		
		Table table = new Table();
		table.addContainerProperty("Twitter",TwitterItemView.class, null);
		//table.addContainerProperty("image", Embedded.class, null);
		//table.addContainerProperty("text", Resource.class, null);
	    table.setSizeFull();
		
		for (Iterator a = container.getItemIds().iterator(); a.hasNext();){
		     Object b = a.next();
		     TwitterItemView area = new TwitterItemView();
		     area.setTexto( container.getItem(b).getItemProperty("text").toString());
		     area.setLabelNome(container.getItem(b).getItemProperty("from_user_name").toString());
		     area.setEmbeddedImagem(container.getItem(b).getItemProperty("profile_image_url_https").toString());
		     area.setLabelHora(container.getItem(b).getItemProperty("created_at").toString());
		    
		     table.addItem(new Object[] {area}, container.getItem(b));
		   }
		
		addComponent(table);
		}
}
