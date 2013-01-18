package com.view.componente;

import com.vaadin.ui.Component;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;


@SuppressWarnings({"serial", "static-access"})
public class Mensagem {

	public static void msg(Window window,String titulo, String msg){
		
	  	 MessageBox mb = new MessageBox(window, titulo, MessageBox.Icon.QUESTION, msg,
	  			 new MessageBox.ButtonConfig(MessageBox.ButtonType.YES, "Sim"),
	  			 new MessageBox.ButtonConfig(MessageBox.ButtonType.NO, "Nao"));
	  	 
	  	 mb.show(new MessageBox.EventListener() {
	  		 
			public void buttonClicked(ButtonType buttonType) {
				
				if (buttonType.equals(buttonType.YES)) {
					
				}
				else {
					
				}
			}
		});
	  	 
	}
	
	public static void msgConf(Component c, String not, String msg){
		
		c.getWindow().showNotification(not,	msg, Notification.TYPE_TRAY_NOTIFICATION);
	}
	
	public static void msgErro(Component c, String not, String msg){
		
		c.getWindow().showNotification(not,	msg, Notification.TYPE_ERROR_MESSAGE);
	}
}
