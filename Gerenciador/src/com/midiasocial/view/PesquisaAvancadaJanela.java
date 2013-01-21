package com.midiasocial.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class PesquisaAvancadaJanela extends Window {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private class PesquisaCampo extends HorizontalLayout {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private NativeSelect operator;
	
		private TextField field;
	
		private Button addButton;

	public PesquisaCampo() {
	    operator = new NativeSelect(null, Arrays.asList("OR", "AND", "NOT",
		    "FROM", "TO", "HASHTAG", "MENTIONS", "ATTITUDE_GOOD",
		    "ATTITUDE_BAD", "QUESTION"));
	    operator.setNullSelectionAllowed(false);
	    operator.setValue("OR");
	    operator.setImmediate(true);
	    operator.addListener(new Property.ValueChangeListener() {
		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public void valueChange(ValueChangeEvent event) {
		    String op = event.getProperty().getValue().toString();
		    if (op.startsWith("ATTITUDE") || op.equals("QUESTION")) {
			field.setVisible(false);
		    } else {
			field.setVisible(true);
		    }
		}
	    });
	    addComponent(operator);

	    field = new TextField();
	    field.setInputPrompt("Enter a search term..");
	    field.setWidth("100%");
	    addComponent(field);

	    addButton = new Button("+", new Button.ClickListener() {
		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public void buttonClick(ClickEvent event) {
		    addButton.setVisible(false);
		    addField();
		}
	    });
	    addButton.setDescription("Add another search term");
	    addComponent(addButton);

	    setExpandRatio(field, 1);

	}

	public String getQuery() {
	    if (field.getValue() == null) {
		return null;
	    }

	    StringBuilder q = new StringBuilder();

	    if (!isFirstField()) {
		String op = operator.getValue().toString();
		if (op.equals("OR")) {
		    q.append("OR ");
		    q.append(field.getValue().toString());
		} else if (op.equals("AND")) {
		    q.append("AND ");
		    q.append(field.getValue().toString());
		} else if (op.equals("NOT")) {
		    q.append("-");
		    q.append(field.getValue().toString());
		} else if (op.equals("FROM")) {
		    q.append("from:");
		    q.append(field.getValue().toString());
		} else if (op.equals("TO")) {
		    q.append("to:");
		    q.append(field.getValue().toString());
		} else if (op.equals("HASHTAG")) {
		    q.append("#");
		    q.append(field.getValue().toString());
		} else if (op.equals("MENTIONS")) {
		    q.append("@");
		    q.append(field.getValue().toString());
		} else if (op.equals("QUESTION")) {
		    q.append("?");
		} else if (op.equals("ATTITUDE_GOOD")) {
		    q.append(":)");
		} else if (op.equals("ATTITUDE_BAD")) {
		    q.append(":(");
		}
	    } else {
		q.append(field.getValue().toString());
	    }

	    return q.toString();
	}

	public void setFirstField(boolean firstField) {
	    operator.setVisible(!firstField);
	}

	public boolean isFirstField() {
	    return !operator.isVisible();
	}
    }

    private VerticalLayout form;

    private List<PesquisaCampo> fields = new ArrayList<PesquisaCampo>();

    private String query;

    public PesquisaAvancadaJanela() {
	setCaption("Pesquisa Avan�ada");
	setStyleName(Reindeer.WINDOW_LIGHT);
	setResizable(false);
	setClosable(false);
	setDraggable(false);
	setModal(true);
	setWidth("400px");
	setHeight("400px");
	((VerticalLayout) getContent()).setSizeFull();

	form = new VerticalLayout();
	form.setMargin(true);
	form.setSpacing(true);
	addComponent(form);

	addField();

	HorizontalLayout buttons = new HorizontalLayout();
	buttons.setSpacing(true);
	buttons.addComponent(new Button("Pesquisar", new Button.ClickListener() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void buttonClick(ClickEvent event) {
		query = buildQuery();
		close();
	    }
	}));
	buttons.addComponent(new Button("Cancelar", new Button.ClickListener() {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void buttonClick(ClickEvent event) {
		query = null;
		close();
	    }
	}));
	addComponent(buttons);
	((VerticalLayout) getContent()).setExpandRatio(buttons, 1);
	((VerticalLayout) getContent()).setComponentAlignment(buttons,
		Alignment.BOTTOM_RIGHT);
    }

    private String buildQuery() {
	StringBuilder q = new StringBuilder();
	for (PesquisaCampo field : fields) {
	    if (field.getQuery() != null) {
		q.append(field.getQuery());
		q.append(" ");
	    }
	}
	return q.toString();
    }

    private void addField() {
	PesquisaCampo field = new PesquisaCampo();
	field.setFirstField(fields.isEmpty());
	field.setWidth("100%");
	fields.add(field);
	form.addComponent(field);
    }

    public String getQuery() {
	return query;
    }
}