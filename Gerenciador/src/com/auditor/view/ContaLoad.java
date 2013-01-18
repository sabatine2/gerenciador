package com.auditor.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.auditor.helper.LeitorCSV;
import com.auditor.helper.Registro;
import com.auditor.model.Conta;
import com.auditor.model.ItemConta;
import com.auditor.model.Medida;
import com.auditor.model.PalavraChave;
import com.auditor.model.TipoServico;
import com.auditor.view.ContaView;
import com.principal.helper.HibernateUtil;
import com.vaadin.ui.*;

import de.steinwedel.vaadin.MessageBox;

@SuppressWarnings({"serial", "unchecked"})
public class ContaLoad extends Window
                        implements Upload.SucceededListener,
                                   Upload.FailedListener,
                                   Upload.Receiver {

    public File  file;
    private ProgressIndicator pi1;
    private LeitorCSV leitor;
    private Conta conta;  
    private ContaView contaView;
    private boolean contaOk;

    public ContaLoad(String tituloPanel, Conta conta, ContaView contaView) {
        this.conta = conta;
        this.contaView = contaView;
    	
        setCaption(tituloPanel);
        center();
        setHeight("225px");
        setWidth("475px");
        setModal(true);
        // Create the Upload component.
        final Upload upload =
                new Upload("Carregar Arquivo", this);
        
        VerticalLayout verticalLayout = new VerticalLayout();
        addComponent(verticalLayout);
        verticalLayout.setHeight("50px");
        verticalLayout.setWidth("100.0%");
        
        ComboBox cbTemplate = new ComboBox();
        cbTemplate.setCaption("Template");
        cbTemplate.setWidth("18em");
        cbTemplate.setNullSelectionAllowed(false);
        verticalLayout.addComponent(cbTemplate);

        // Use a custom button caption instead of plain "Upload".
        upload.setButtonCaption("Carregar");

        // Listen for events regarding the success of upload.
        upload.addListener((Upload.SucceededListener) this);
        upload.addListener((Upload.FailedListener) this);

        addComponent(upload);
        addComponent(new Label("Click em 'Escolher Arquivo' "+
                "depois em 'Carregar'."));

        // Create a panel for displaying the uploaded image.
    }

    // Callback method to begin receiving the upload.
    public OutputStream receiveUpload(String filename,
                                      String MIMEType) {
        FileOutputStream fos = null; // Output stream to write to
        file = new File("C:\\image\\" + filename);
        //file = new File("C:\\Users\\Victor" + filename);
        try {
            // Open the file for writing.
           fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            // Error while opening the file. Not reported here.
            e.printStackTrace();
            return null;
        }

        return fos; // Return the output stream to write to
    }

    // This is called if the upload is finished.
    public void uploadSucceeded(Upload.SucceededEvent event) {
        // Log the upload on screen.
        addComponent(new Label("File " + event.getFilename()
                + " of type '" + event.getMIMEType()
                + "' uploaded."));
        
        // Display the uploaded file in the image panel.
        //try{
        	leitor = new LeitorCSV(file);
        	itensLista();
        /*} catch (Exception e) {
        	addComponent(
                     new Label("Arquivo n�o encontrado"));
		}*/
    }
    

    // This is called if the upload fails.
    public void uploadFailed(Upload.FailedEvent event) {
        // Log the failure on screen.
        addComponent(new Label("Uploading "
                + event.getFilename() + " para o tipo '"
                + event.getMIMEType() + "' falhou."));
    }

	public String getFile() {
		return file.getPath();
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public void progressionBar(){
		
		 pi1 = new ProgressIndicator();
	        pi1.setIndeterminate(false);        
	        pi1.setEnabled(true);
	        pi1.setValue(0f);
	        addComponent(pi1);       
	}
	
	public void itensLista(){
		
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		List<TipoServico> listTipo =(List<TipoServico>) TipoServico.listaTipo();
		List<Medida> listMedida = Medida.listaMedida();
		
		for (int i = 0; i < leitor.listaRegistros.size(); i++) {
			Registro r = leitor.listaRegistros.get(i);
			ItemConta item = new ItemConta();
			TipoServico tipo = new TipoServico();
			Medida medida = new Medida();
			String numero[] = r.getOrigem().split("-");
			Long getDddNumero = Long.parseLong(numero[0].toString().trim());
			
			System.out.println("NUMERO1: "+conta.getTelefone().getDddNumero());
			System.out.println("DDD: "+getDddNumero);
			
			if (getDddNumero.equals(conta.getTelefone().getDddNumero())) {
			System.out.println("DDD IGUAL");
		
				for (int j = 0; j < listTipo.size(); j++) {
					tipo = listTipo.get(j);
					List<PalavraChave> listPchave = null;
					listPchave = (List<PalavraChave>) tipo.palavraChave;
				
					for (int k = 0; k < listPchave.size(); k++) {
				
						PalavraChave pChave = listPchave.get(k);
						if (r.getDescricao().toUpperCase().contains(pChave.getPalavraChave().toUpperCase())) {
							item.setTipo(tipo);
						}
					}
				}	
				for (int l = 0; l < listMedida.size(); l++) {
					medida = listMedida.get(l);
					List<PalavraChave> listPchave = null;
					listPchave = (List<PalavraChave>) medida.palavraChave;
			
					for (int m = 0; m < listPchave.size(); m++) {
					
						PalavraChave pChave = listPchave.get(m);
						if (r.getUnidade().toUpperCase().contains(pChave.getPalavraChave().toUpperCase())) {		
							item.setMedida(medida);
						}
					}
				}
				item.setCodServico(r.getCodServico());
				item.setComplemento(r.getComplemento());
				item.setLocal(r.getLocalDestino());
				item.setQuantidade(r.getQuantidade().replace(",", "."));
				item.setSequencia(r.getSeq().replace('"', ' '));
				item.setTerminal(r.getTerminalDestino());
				
				BigDecimal bd = new BigDecimal(String.valueOf(r.getImp().replace(",", ".")));
				//item.setValor(Double.parseDouble(String.valueOf(r.getImp()+"0").replace(",", ".")));
				item.setValor(bd);
				item.setPais(r.getPais());
				item.setConta(conta);
				
				if (r.getHoraInicio().trim().length() > 0) {
					
					Date dataInicio = null;  
					String date = r.getDataInicio().toString() +" "+ r.getHoraInicio().toString();
					DateFormat df = new SimpleDateFormat("DD/MM/yyyy hh:mm:ss");
					df = DateFormat.getDateTimeInstance();   
					try {
						dataInicio = df.parse(date);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					item.setDataHoraInicio(dataInicio);
				}
				else {
					
					Date dataInicio = null;  
					String date = r.getDataInicio().toString() +" "+ r.getHoraInicio().toString();
					DateFormat df = new SimpleDateFormat("DD/MM/yyyy");
					df = DateFormat.getDateInstance();   
					try {
						dataInicio = df.parse(date);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					item.setDataHoraInicio(dataInicio);
				}
				
				if (r.getHoraFim().trim().length() > 0) {
					
					Date dataFim = null;  
					String date = r.getDataFim().toString() +" "+ r.getHoraFim().toString();
					DateFormat df = new SimpleDateFormat("DD/MM/yyyy hh:mm:ss");
					df = DateFormat.getDateTimeInstance();   
					try {
						dataFim = df.parse(date);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					item.setDataHoraFim(dataFim);
				}
				else {
					
					Date dataFim = null;  
					String date = r.getDataFim().toString() +" "+ r.getHoraFim().toString();
					DateFormat df = new SimpleDateFormat("DD/MM/yyyy");
					df = DateFormat.getDateInstance();   
					try {
						dataFim = df.parse(date);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					item.setDataHoraFim(dataFim);
				}
				
				session.save(item);
			    if ( i % 20 == 0 ) { //20, same as the JDBC batch size
			        //flush a batch of inserts and release memory:
			        session.flush();
			        session.clear();
			    }
				
				contaView.itemTable(item);
				contaOk = true;
			}
			else {
				contaOk = false;
				break;
			}
		}
		
		tx.commit();
		session.close();
		
		if (contaOk) {
			this.close();
			contaView.msnConta("Adicionada", MessageBox.Icon.INFO, "Itens adicionado com sucesso");
		}
		else {
			this.close();
			contaView.msnConta("Erro", MessageBox.Icon.ERROR, "Conta n�o pertence ao Telefone");
		}
	}
}