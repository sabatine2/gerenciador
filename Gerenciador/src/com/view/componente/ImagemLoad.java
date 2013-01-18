package com.view.componente;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import net.coobird.thumbnailator.Thumbnails;

import com.principal.helper.Hash;
import com.vaadin.terminal.FileResource;
import com.vaadin.ui.*;

@SuppressWarnings("serial")
public class ImagemLoad extends Window
                        implements Upload.SucceededListener,
                                   Upload.FailedListener,
                                   Upload.Receiver
                                   {

    private File file;         
    private File fileMod;
    private Label label = new Label(); 
    public  Button alterar = new Button("Alterar");
    private Long id;
    private Date data = new Date();
    private String tituloPanel;
    
    public ImagemLoad(String tituloPanel, Long id, String height, String width) {
    	
    	this.id = id;
    	this.tituloPanel = tituloPanel;
        
    	setCaption(tituloPanel);
        center();
        setHeight(height);
        setWidth(width);
        setModal(true);
        
        // Create the Upload component.
        final Upload upload =
                new Upload("Carregar Arquivo", this);
        
        // Use a custom button caption instead of plain "Upload".
        upload.setButtonCaption("Carregar");

       
        // Listen for events regarding the success of upload.
        upload.addListener((Upload.SucceededListener) this);
        upload.addListener((Upload.FailedListener) this);

        addComponent(upload);
        addComponent(new Label("Click em ''Escolher Arquivo''"+
                "depois em ''Carregar''."));
    }

    // Callback method to begin receiving the upload.
    public OutputStream receiveUpload(String filename,
                                      String MIMEType) {
        FileOutputStream fos = null; // Output stream to write to
        file = new File(filename);
        try {
            // Open the file for writing.
           fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            // Error while opening the file. Not reported here.
            e.printStackTrace();
            return null;
        }

        return fos; 
    }

    public void uploadSucceeded(Upload.SucceededEvent event) {
        
    	label.setCaption("Uploading "
                + event.getFilename() + " para o tipo '"
                + event.getMIMEType());
        if (id != null) {
        	boolean success = false;
		
        	try {
        		File rootDir = File.listRoots()[0];
        		File dir = new File(new File (new File (new File(new File(rootDir, "tmp"), "persys"),"image"),tituloPanel),Hash.shaCodigo(String.valueOf(id)));
        		if (!dir.exists()){
        			dir.mkdirs();
        		}
        		success = dir.isDirectory();
			
        	} catch (Exception e2) {
        		e2.getMessage();
        	}
       
        	if (success) {
        		System.out.println("Directory: created");
        	}  
    
        	createImageMod(16, 16);
        	createImageMod(32, 32);
        	createImageMod(48, 48);
        	createImageMod(64, 64);
        	createImageMod(96, 96);
        }
        
        try{
       		FileResource imageResource = new FileResource(file, getApplication());
       		Embedded em = new Embedded("", imageResource);
       		removeAllComponents();
        	addComponent(em);
        	addComponent(alterar);
        	imageResource = null;
       	} catch (Exception e) {
       		addComponent(new Label("Arquivo não encontrado"));
       	}  
    }

    public File createImageMod(int x, int y){
    	String url = "/"+data.getTime()+x+"x"+y+".jpg";
	    fileMod = null;
		try {
			File rootDir = File.listRoots()[0];
            fileMod = new File(new File(new File (new File(new File(new File(rootDir, "tmp"), "persys"),"image"),tituloPanel), Hash.shaCodigo(String.valueOf(id))) + url);
			try {
			  Thumbnails.of(file)
					.size(x, y)
					.toFile(fileMod);
			} catch (IOException e1) {
					e1.printStackTrace();
		   }
		} catch (NoSuchAlgorithmException e2) {
			System.out.println(e2.getMessage());
		}
		catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
		return fileMod;
    }
    
    // This is called if the upload fails.
    public void uploadFailed(Upload.FailedEvent event) {
        // Log the failure on screen.
        addComponent(new Label("Uploading "
                + event.getFilename() + " para o tipo '"
                + event.getMIMEType() + "' falhou."));
    }

    public void limpa(){
    	  removeAllComponents();
    }
    
    public String getFilePath(){
    	
    	String url = file.getAbsolutePath();
    	return url;
    }
    
	public String getFile() {
		try {
			File rootDir = File.listRoots()[0];
			File imagem = new File(new File (new File(new File(new File(rootDir, "tmp"), "persys"),"image"),tituloPanel), Hash.shaCodigo(String.valueOf(id)));
			file = null;
	    	fileMod = null;
	        return imagem.getAbsolutePath()+"/"+data.getTime();
		} catch (NoSuchAlgorithmException e) {
			file = null;
	    	fileMod = null;
			e.printStackTrace();
			return null;
		}
	}

	public void setFile(File file) {
		this.file = file;
	}
}