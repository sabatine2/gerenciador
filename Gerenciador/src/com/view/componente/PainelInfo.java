package com.view.componente;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import com.principal.gerenciador.GerenciadorApplication;
import com.vaadin.data.Item;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings({"serial", "deprecation", "unused"})
public class PainelInfo extends VerticalLayout {

	public final Form painelForm;
	public Panel painelFoto;
	public Resource res;
	public Label nome;
	public PopupDateField dataCriacao;
	public PopupDateField dataModificacao;
	private Button buttonLogo;
	private GridLayout ourLayout;
	private GridLayout ourLayoutData;
	private VerticalLayout ourLayoutLogo;
	private ImagemLoad image;
    private Long  id;
    private String caminhoImagem;
	
	public PainelInfo(String captionNome,Long id, String conteudoNome, Date conteudoCriacao, Date conteudoModificacao, String caminhoImagem)  {
       
		 this.id = id;
		 this.caminhoImagem = caminhoImagem;
		 
    	 Info infoItem = new Info(conteudoNome, conteudoCriacao, conteudoModificacao); // item from
         // POJO

        painelForm = new FormWithComplexLayout("Info", infoItem  );

        // Add form to layout
        addComponent(painelForm);

        painelForm.getFooter().setMargin(true);
        //painelForm.setWidth("-1px");
       
        image = new ImagemLoad(captionNome,id,"","");
       
        image.alterar.addListener(new ClickListener() {
			
     			@Override
     			public void buttonClick(ClickEvent event) {
     			    try{
     		        	     setCaminhoImagem(image.getFile());
     			             addImagem(null);
     			        } catch (Exception e) {
     			        	    addComponent(new Label("Arquivo n‹o encontrado"));
     					}	
     			}
     		});  
     }
    
     public void addImagem(Resource res){
    	
    	    ourLayoutLogo.removeAllComponents();
    	
    		File file2 = null;
    		
    		 File rootDir = File.listRoots()[0];
    		        try {
						file2 = new File(caminhoImagem+"96x96.jpg");
						if(file2.isFile()){
						System.out.print(file2.getAbsolutePath());
						res = new FileResource(file2,GerenciadorApplication.app);
						}
    		        } 
    		 	catch (NullPointerException e2) {
				// TODO Auto-generated catch block
	   			System.out.print(e2.getMessage());
			}
    	   
    	   Embedded em = new Embedded("",res);
    	   ourLayoutLogo.addComponent(em);
    	   ourLayoutLogo.setComponentAlignment(em, Alignment.TOP_CENTER);
           ourLayoutLogo.addComponent(buttonLogo);
           ourLayoutLogo.setComponentAlignment(buttonLogo, Alignment.BOTTOM_CENTER);	         
    }
   
	/**
	 * @return the caminhoImagem
	 */
	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	/**
	 * @param caminhoImagem the caminhoImagem to set
	 */
	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}


	public class Info {
    	 
    	 private String nome;
    	 private Date dataCadastro;
    	 private Date dataAlteracao;
    	 
    	 
    	 public Info(String nome, Date dataCadastro, Date dataAlteracao) {
			super();
			this.nome = nome;
			this.dataCadastro = dataCadastro;
			this.dataAlteracao = dataAlteracao;
		}

		/**
		 * @return the nome
		 */
		public String getNome() {
			return nome;
		}

		/**
		 * @param nome the nome to set
		 */
		public void setNome(String nome) {
			this.nome = nome;
		}

		/**
		 * @return the dataCadastro
		 */
		public Date getDataCadastro() {
			return dataCadastro;
		}

		/**
		 * @param dataCadastro the dataCadastro to set
		 */
		public void setDataCadastro(Date dataCadastro) {
			this.dataCadastro = dataCadastro;
		}

		/**
		 * @return the dataAlteracao
		 */
		public Date getDataAlteracao() {
			return dataAlteracao;
		}

		/**
		 * @param dataAlteracao the dataAlteracao to set
		 */
		public void setDataAlteracao(Date dataAlteracao) {
			this.dataAlteracao = dataAlteracao;
		}

				
	}
    
	public class FormWithComplexLayout extends Form {
        /**
		 * CRIA GRID E OS CAMPOS
		 */

        public FormWithComplexLayout(String titulo, Info info) {
            setCaption(titulo);

            ourLayout = new GridLayout(3, 2);
            ourLayoutData = new GridLayout(3, 2);
            ourLayoutLogo = new VerticalLayout();

            // Use top-left margin and spacing
            ourLayout.setMargin(true, false, false, true);
            ourLayout.setSpacing(true);
            ourLayoutData.setSpacing(true);
            ourLayoutLogo.setSpacing(true);
       
            setLayout(ourLayout);
     
            // Set up buffering
            setWriteThrough(false); // we want explicit 'apply'
            setInvalidCommitted(false); // no invalid values in datamodel

            // FieldFactory for customizing the fields and adding validators
            setFormFieldFactory(new PersonFieldFactory());
           
        	nome.setReadOnly(false);
            nome.setValue("<b>"+info.getNome()+"</b>");
            nome.setReadOnly(true);
            
            dataCriacao.setReadOnly(false);
            dataCriacao.setValue(info.getDataCadastro());
            dataCriacao.setReadOnly(true);
            
            dataModificacao.setReadOnly(false);
            dataModificacao.setValue(info.getDataAlteracao());
            dataModificacao.setReadOnly(true);
            
            ourLayoutData.addComponent(dataCriacao, 0, 1);
            ourLayoutData.addComponent(dataModificacao, 1, 1);
            
            ourLayout.addComponent(painelFoto, 0, 0, 0, 1);
            ourLayoutData.addComponent(nome, 0, 0, 1, 0);
            ourLayout.addComponent(ourLayoutData, 1, 1, 2, 1);
        
            // Determines which properties are shown, and in which order:
            setVisibleItemProperties(Arrays.asList(new String[] {"nome", "dataCadastro", "dataAlteracao"}));
        
        }
        
        @Override
        protected void attachField(Object propertyId, Field field) {
           
        }
    }
	
	
	
    private class PersonFieldFactory extends DefaultFieldFactory {
    	/**
		 * CONFIGURA‚ÌO DOS CAMPOS
		 */
    	
	    public PersonFieldFactory() {
	        
	    	nome = new Label();
            nome.setWidth("30em");
            nome.setContentMode(Label.CONTENT_XHTML); 
            
            dataCriacao = new PopupDateField("Data Criação");
            dataCriacao.setWidth("15em");   
            
            dataModificacao = new PopupDateField("Data Modificação");
            dataModificacao.setWidth("15em");
         
            
    		buttonLogo = new Button("Alterar", new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                	image.limpa();
                	getWindow().addWindow(image);
            }});
    		buttonLogo.setStyleName(BaseTheme.BUTTON_LINK);
            
    	    addImagem(new ThemeResource("../reindeer/Icons/user.png"));
    	    
            painelFoto = new Panel();
            painelFoto.setStyleName(Panel.STYLE_LIGHT);
            painelFoto.setImmediate(false);
    		painelFoto.setWidth("-1px");
    		painelFoto.setHeight("-1px");
    		painelFoto.setLayout(ourLayoutLogo);
    	
        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f;
               f = super.createField(item, propertyId, uiContext);
            return f;
        }
     
        
    }
	
}