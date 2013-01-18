package com.ticket.view.panel;

import com.ticket.controller.OrdemController;
import com.ticket.model.Mensagem;
import com.ticket.model.Nota;
import com.ticket.view.NotaView;
import com.ticket.view.RespostaView;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

public class PanelOperacao extends VerticalLayout implements
TabSheet.SelectedTabChangeListener {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private TabSheet t;

	public PanelOperacao(OrdemController ordemController) {
		// Tab 1 content
		VerticalLayout l1 = new VerticalLayout();
		l1.setMargin(true);
		
		RespostaView r = new RespostaView(ordemController, new Mensagem());
		r.modoResposta();
		l1.addComponent(r);
	
		VerticalLayout l2 = new VerticalLayout();
		l2.setMargin(true);
		
		NotaView nota = new NotaView(ordemController, new Nota());
		nota.modoResposta();
		
		l2.addComponent(nota);
		// Tab 3 content
		VerticalLayout l3 = new VerticalLayout();
		l3.setMargin(true);
		l3.addComponent(new com.vaadin.ui.Label("Transferencia"));
		
		t = new TabSheet();
		t.setHeight("-1px");
		t.setWidth("100%");
		
		t.addTab(l1, "Mensagem");
		t.addTab(l2, "Notas");
		t.addTab(l3, "Transferência");
		t.addListener(this);
		
		addComponent(t);
	}
	
	@Override
	public void selectedTabChange(SelectedTabChangeEvent event) {
	TabSheet tabsheet = event.getTabSheet();
	Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());
		if (tab != null) {
		    getWindow().showNotification("Selected tab: " + tab.getCaption());
		}
	}
}
