package com.googlemaps;

import java.awt.geom.Point2D;

import org.vaadin.hezamu.googlemapwidget.GoogleMap;
import org.vaadin.hezamu.googlemapwidget.GoogleMap.MapControl;
import org.vaadin.hezamu.googlemapwidget.overlay.BasicMarker;
import com.vaadin.ui.*;

@SuppressWarnings("serial")
public class MapaSimples extends Window {
	
	public MapaSimples(Double lat, Double lon) {
		// Create a new map instance centered on the IT Mill offices
	
		setWidth("680px");
		setHeight("550px");
		GoogleMap googleMap =null;
		try{
			googleMap = new GoogleMap(getApplication(),
				  new Point2D.Double(lat, lon), 15);
		
				googleMap.setWidth("640px");
				googleMap.setHeight("480px");
				// Create a marker at the IT Mill offices
				googleMap.addMarker(new BasicMarker(1L, new Point2D.Double(lat,
				   lon), "Test marker"));
	    googleMap.addControl(MapControl.ScaleControl);
	    googleMap.addControl(MapControl.MapTypeControl);
	    //googleMap.addControl(MapControl.MenuMapTypeControl);
	    googleMap.addControl(MapControl.ScaleControl);
		}
		catch (Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}
	   addComponent(googleMap);
	}
	
	public MapaSimples(Double lat, Double lon, String titulo) {
		// Create a new map instance centered on the IT Mill offices
	
		setWidth("680px");
		setHeight("550px");
		GoogleMap googleMap =null;
		try{
			googleMap = new GoogleMap(getApplication(),
				  new Point2D.Double(lat, lon), 15);
		
				googleMap.setWidth("640px");
				googleMap.setHeight("480px");
				// Create a marker at the IT Mill offices
				googleMap.addMarker(new BasicMarker(1L, new Point2D.Double(lat,
				   lon), titulo));
	    googleMap.addControl(MapControl.ScaleControl);
	    googleMap.addControl(MapControl.MapTypeControl);
	    //googleMap.addControl(MapControl.MenuMapTypeControl);
	    googleMap.addControl(MapControl.ScaleControl);
	    
		addComponent(googleMap);
		}
		catch (Exception e) {
			System.out.println("ERRO: "+e.getMessage());
			addComponent(new Label("Erro ao carregar o mapa"));
		}
	}
}