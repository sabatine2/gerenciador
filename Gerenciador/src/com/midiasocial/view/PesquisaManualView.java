package com.midiasocial.view;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import com.midiasocial.controller.PesquisaManualController;
import com.midiasocial.view.adapter.ResultadoBusca;
import com.midiasocial.view.adapter.ResultadoBuscaAdapter;
import com.ticket.model.Mensagem;
import com.ticket.model.Ordem;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;

import fi.jasoft.twitterquerycontainer.TwitterQueryContainer;

@SuppressWarnings({"serial", "unused"})
public class PesquisaManualView extends VerticalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ResultadoBuscaAdapter publicacaoAdapter = new ResultadoBuscaAdapter();
	private PesquisaManualController pesquisaManualController;
	private Table table;

    public PesquisaManualView(PesquisaManualController pesquisaManualController) {
    	
        setSizeFull();
        setSpacing(true);
        setMargin(true);
        this.pesquisaManualController = pesquisaManualController;
        Label header = new Label("Pesquisa Manual");
        header.setStyleName(Reindeer.LABEL_H1);
        addComponent(header);

        Label description = new Label(
                "Resultados dos ultimos 6 dias ou menos "
                        + "É possível fazer uma pesquisa simples"
                        + "ou com mais parametros em avançados. "
                        + "Click em gravar para iniciar um atendimento.");
        description.setStyleName(Reindeer.LABEL_SMALL);
        addComponent(description);

              
        HorizontalLayout hl = new HorizontalLayout();
        hl.setWidth("100%");
        hl.setSpacing(true);
        addComponent(hl);

        final TextField search = new TextField();
        search.setWidth("100%");
        search.setInputPrompt("Entre com a palavra chave ...");
        search.setImmediate(true);
        search.addListener(new FieldEvents.TextChangeListener() {
            public void textChange(TextChangeEvent event) {
                ResultadoBusca sc = (ResultadoBusca) table
                        .getContainerDataSource();
                sc.setQuery(event.getText());
                sc.refresh();
            }
        });
        search.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                ResultadoBusca sc = (ResultadoBusca) table
                        .getContainerDataSource();
                sc.setQuery(event.getProperty().getValue().toString());
                sc.refresh();
            }
        });
        hl.addComponent(search);

        Button advanced = new Button("Pesquisa Avançada",
                new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        PesquisaAvancadaJanela win = new PesquisaAvancadaJanela();
                        win.addListener(new Window.CloseListener() {
                       	@Override
							public void windowClose(CloseEvent e) {
								// TODO Auto-generated method stub
								 search.setValue(((PesquisaAvancadaJanela) e
	                                        .getWindow()).getQuery());
							}
                        });
                        getWindow().addWindow(win);

                    }
                });
        hl.addComponent(advanced);
        hl.setExpandRatio(search, 1);

        table = new Table(null);
        table.setSizeFull();

        table.addListener(new ItemClickListener() {
			
			/**
			 * ITEM CLICK EVENTO
			 */
			public void itemClick(ItemClickEvent event) {
				
				if (event.isDoubleClick()) {	
					System.out.println(event.getItem());
				}
			}
		});
        
        addGeneratedColumnsToTable();
        setTableContainer(publicacaoAdapter);

        addComponent(table);
        setExpandRatio(table, 1);

        FormLayout options = new FormLayout();
        options.setCaption("Opções");
        options.setMargin(true);
        Panel optionPanel = new Panel(options);
        optionPanel.setWidth("100%");
        addComponent(optionPanel);
    }

    private void addGeneratedColumnsToTable() {
        table.addGeneratedColumn(ResultadoBusca.FOTO_URL,
                new Table.ColumnGenerator() {
                    public Object generateCell(Table source, Object itemId,
                            Object columnId) {
                        Object url = source.getItem(itemId)
                                .getItemProperty(columnId).getValue();
                        if (url == null) {
                            url = source
                                    .getItem(itemId)
                                    .getItemProperty(
                                           ResultadoBusca.FOTO_URL)
                                    .getValue();
                        }
                        if (url != null) {
                            Embedded image = new Embedded(null,
                                    new ExternalResource(url.toString()));
                            image.setWidth("48px");
                            image.setHeight("48px");
                            return image;
                        }
                        return url;
                    }
                });

        // Replace username and user name columns with one custom column
        table.addGeneratedColumn(ResultadoBusca.REDE_SOCIAL,
                new Table.ColumnGenerator() {
                    public Object generateCell(Table source, Object itemId,
                            Object columnId) {
                        Object name = source
                                .getItem(itemId)
                                .getItemProperty(
                                        ResultadoBusca.REDE_SOCIAL)
                                .getValue();
                       
                        if (name != null) {
                            return name;
                        }
                        return null;
                    }
                });
        

        // Replace username and user name columns with one custom column
        table.addGeneratedColumn(ResultadoBusca.NOME,
                new Table.ColumnGenerator() {
                    public Object generateCell(Table source, Object itemId,
                            Object columnId) {
                        Object name = source
                                .getItem(itemId)
                                .getItemProperty(
                                        ResultadoBusca.NOME)
                                .getValue();
                       
                        if (name != null) {
                            return name;
                        }
                        return null;
                    }
                });

        // Format the timestamp
        table.addGeneratedColumn(ResultadoBusca.DATA_CRIACAO_MIDIA,
                new Table.ColumnGenerator() {
                    public Object generateCell(Table source, Object itemId,
                            Object columnId) {
                        Object value = source.getItem(itemId)
                                .getItemProperty(columnId).getValue();
                        if (value != null) {
                            Date date = (Date) value;
                            long millisecondsAgo = System.currentTimeMillis()
                                    - date.getTime();

                            if (TimeUnit.MILLISECONDS.toHours(millisecondsAgo) == 0) {
                                // xx min, xx sec ago
                                return String
                                        .format("%d min, %d sec ago",
                                                TimeUnit.MILLISECONDS
                                                        .toMinutes(millisecondsAgo),
                                                TimeUnit.MILLISECONDS
                                                        .toSeconds(millisecondsAgo)
                                                        - TimeUnit.MINUTES
                                                                .toSeconds(TimeUnit.MILLISECONDS
                                                                        .toMinutes(millisecondsAgo)));
                            } else if (TimeUnit.MILLISECONDS
                                    .toHours(millisecondsAgo) < 12) {
                                // xx hours, xx min ago
                                return String
                                        .format("%d hours, %d min ago",
                                                TimeUnit.MILLISECONDS
                                                        .toHours(millisecondsAgo),
                                                TimeUnit.MILLISECONDS
                                                        .toMinutes(millisecondsAgo)
                                                        - TimeUnit.HOURS
                                                                .toMinutes(TimeUnit.MILLISECONDS
                                                                        .toHours(millisecondsAgo)));
                            } else {
                                return date;
                            }

                        }
                        return value;
                    }
                });

        // Replace urls with actual links
        table.addGeneratedColumn(ResultadoBusca.MENSAGEM,
                new Table.ColumnGenerator() {
                    public Object generateCell(Table source, Object itemId,
                            Object columnId) {
                        Object value = source.getItem(itemId)
                                .getItemProperty(columnId).getValue();
                        if (value != null) {
                            String text = value.toString();

                            // Replace links
                            String regex = "(\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";

                            value = text.replaceAll(regex, "<a target=\"_blank\" href=\"$1\">$1</a>");

                            Label lbl = new Label(value.toString(),
                                    Label.CONTENT_RAW);
                            lbl.setSizeFull();
                            CssLayout layout = new CssLayout();
                            layout.setSizeFull();
                            layout.addComponent(lbl);
                            value = layout;
                        }

                        return value;
                    }
                });
        

        // Replace urls with actual links
        table.addGeneratedColumn("botao",
                new Table.ColumnGenerator() {
                    public Object generateCell(final Table source, Object itemId,
                            Object columnId) {
                       
	                    	Button detailsField = new Button("gravar");
	                        detailsField.setData(itemId);
	                        detailsField.addListener(new Button.ClickListener() {
	                            public void buttonClick(ClickEvent event) {
	                                // Get the item identifier from the user-defined data.
	                                Integer itemId = (Integer)event.getButton().getData();
	                                String mensagem = (String) source
	                                        .getItem(itemId)
	                                        .getItemProperty(
	                                                ResultadoBusca.MENSAGEM)
	                                        .getValue();
	                                
	                                String usuario = (String) source
	                                        .getItem(itemId)
	                                        .getItemProperty(
	                                                ResultadoBusca.NOME)
	                                        .getValue();
	                                
	                                Date data = (Date) source
	                                        .getItem(itemId)
	                                        .getItemProperty(
	                                                ResultadoBusca.DATA_CRIACAO_MIDIA)
	                                        .getValue();
	                                
	                                try {
	                                	
	                                	Ordem ordem = new Ordem();
	 	                                ordem.setFonte("Web");
	 	                                ordem.setDataCriacao(new Date());
	 	                                ordem.setAssunto(mensagem);
	 	                                ordem.setStatus("Aberto");
	 	                                ordem.salvarOuAlterar();
	 	                                
	 	                                Mensagem m = new Mensagem();
	 	                                m.setDataCriacao(data);
	 	                                m.setFonte("Web");
	 	                                m.setMensagem(mensagem);
	 	                                m.setOrdem(ordem);
	 	                                m.salvar();
	                                	getWindow().showNotification("Ordem gerada.");	
	                                	
									} catch (Exception e) {
										getWindow().showNotification("Erro ao criar ordem " + e.getMessage());		
									}
	                               
	                                
	                                
	                              
	                            } 
	                        });
                           detailsField.addStyleName("link");
                           return detailsField;
                    }
                });

    }

    private void setTableContainer(ResultadoBusca container) {
        table.setContainerDataSource(container);

        table.setVisibleColumns(new Object[] {
                ResultadoBusca.FOTO_URL,
                ResultadoBusca.DATA_CRIACAO_MIDIA,
                ResultadoBusca.NOME,ResultadoBusca.REDE_SOCIAL, ResultadoBusca.MENSAGEM, "botao" });

        table.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        table.setColumnWidth(ResultadoBusca.FOTO_URL, 50);
        table.setColumnWidth(ResultadoBusca.MENSAGEM, 300);
        table.setColumnExpandRatio(ResultadoBusca.MENSAGEM, 1);
      }
}

	