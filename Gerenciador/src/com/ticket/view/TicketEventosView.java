package com.ticket.view;

import java.util.Iterator;
import java.util.List;

import com.ticket.model.Mensagem;
import com.ticket.model.Nota;
import com.ticket.view.panel.PanelMensagem;
import com.ticket.view.panel.PanelNota;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.Reindeer;

public class TicketEventosView extends VerticalLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Nota>     listNotas;
	List<Mensagem> listMensagem;
	
	public TicketEventosView(){
		setImmediate(false);
		setWidth("100.0%");
		setHeight("100.0%");
		setMargin(false);
		setSpacing(true);	
	}

	public void panelNota(){
		final VerticalLayout layoutNotas = new VerticalLayout();
		layoutNotas.setImmediate(false);
		layoutNotas.setWidth("100.0%");
		layoutNotas.setHeight("100.0%");
		layoutNotas.setMargin(false);
		layoutNotas.setSpacing(true);
		
		Button notasVisualizar = new Button("Notas Internas", new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				layoutNotas.setVisible(!layoutNotas.isVisible());
			}
		});
		notasVisualizar.setStyleName(Reindeer.BUTTON_LINK);
		
		addComponent(notasVisualizar);
		
		for(Iterator<Nota> i = listNotas.iterator(); i.hasNext(); ){
			Nota nota = i.next();
			com.ticket.view.panel.PanelNota panelNota = new PanelNota(nota);
			layoutNotas.addComponent(panelNota);
		}
		
		addComponent(layoutNotas);
	}
	
	public void panelMensagem(){
		final VerticalLayout layoutMensagem = new VerticalLayout();
		layoutMensagem.setImmediate(false);
		layoutMensagem.setWidth("100.0%");
		layoutMensagem.setHeight("100.0%");
		layoutMensagem.setMargin(false);
		layoutMensagem.setSpacing(true);
		
		Button mensagensVisualizar = new Button("Mensagens", new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				layoutMensagem.setVisible(!layoutMensagem.isVisible());
			}
		});
		
		mensagensVisualizar.setStyleName(Reindeer.BUTTON_LINK);
		
		addComponent(mensagensVisualizar);
		
		for(Iterator<Mensagem> i = listMensagem.iterator(); i.hasNext(); ){
			Mensagem mensagem = i.next();
			PanelMensagem panelMensagem = new PanelMensagem(mensagem);
			layoutMensagem.addComponent(panelMensagem);
		}
		
		addComponent(layoutMensagem);
	}
	
	/**
	 * @return the listNotas
	 */
	public List<Nota> getListNotas() {
		return listNotas;
	}

	/**
	 * @param listNotas the listNotas to set
	 */
	public void setListNotas(List<Nota> listNotas) {
		this.listNotas = listNotas;
	}

	/**
	 * @return the listMensagem
	 */
	public List<Mensagem> getListMensagem() {
		return listMensagem;
	}

	/**
	 * @param listMensagem the listMensagem to set
	 */
	public void setListMensagem(List<Mensagem> listMensagem) {
		this.listMensagem = listMensagem;
	}
}
