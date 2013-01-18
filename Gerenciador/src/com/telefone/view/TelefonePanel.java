package com.telefone.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.telefone.controller.TelefoneController;
import com.telefone.model.TelefoneContato;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class TelefonePanel extends Panel{

	ArrayList<TelefoneView> telefoneView = null;
	private  Button buttonAdicionarItemTelefone;

	public TelefonePanel(Collection<TelefoneContato> listaTelefone, final TelefoneController telefoneController){
		setImmediate(false);
		setWidth("570px");
		setHeight("-1px");

		final VerticalLayout verticalLayoutTelefone = new VerticalLayout();
		verticalLayoutTelefone.setImmediate(false);
		verticalLayoutTelefone.setWidth("530px");
		verticalLayoutTelefone.setHeight("-1px");
		verticalLayoutTelefone.setMargin(false);

		telefoneView = new ArrayList<TelefoneView>();

		for (Iterator iteratorContatos = listaTelefone.iterator(); iteratorContatos.hasNext();) {
			TelefoneContato c = (TelefoneContato) iteratorContatos.next();
			TelefoneView telefone = new TelefoneView(c, telefoneController);
			telefoneView.add(telefone);
			verticalLayoutTelefone.addComponent(telefone);
		}

		HorizontalLayout horizontalLayoutTelefone = new HorizontalLayout();
		horizontalLayoutTelefone.setImmediate(false);
		horizontalLayoutTelefone.setWidth("520px");
		horizontalLayoutTelefone.setHeight("40px");
		horizontalLayoutTelefone.setMargin(false);

		com.vaadin.ui.Label label = new com.vaadin.ui.Label("Cadastro de Telefone");
		label.setContentMode(label.CONTENT_XHTML);
		horizontalLayoutTelefone.addComponent(label);
		horizontalLayoutTelefone.setComponentAlignment(label, Alignment.TOP_LEFT);
		// buttonAdicionarItem
		Resource res = new ThemeResource("../reindeer/Icons/plus.png");
		buttonAdicionarItemTelefone = new Button("",new Button.ClickListener() {
			// inline click-listener
			public void buttonClick(ClickEvent event) {
				verticalLayoutTelefone.addComponent(new TelefoneView(new TelefoneContato(),telefoneController));
			}
		});
		buttonAdicionarItemTelefone.setImmediate(true);
		buttonAdicionarItemTelefone.setWidth("-1px");
		buttonAdicionarItemTelefone.setHeight("-1px");
		buttonAdicionarItemTelefone.setTabIndex(12);
		buttonAdicionarItemTelefone.setIcon(res);
		horizontalLayoutTelefone.addComponent(buttonAdicionarItemTelefone);
		horizontalLayoutTelefone.setComponentAlignment(buttonAdicionarItemTelefone, Alignment.TOP_RIGHT);

		addComponent(horizontalLayoutTelefone);
		// verticalLayoutTelefone
		addComponent(verticalLayoutTelefone);

	}
}
