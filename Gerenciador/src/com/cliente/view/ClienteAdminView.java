package com.cliente.view;

import com.abstracts.view.ViewComponente;
import com.cliente.controller.ClienteController;
import com.cliente.model.Cliente;
import com.cliente.view.form.ClienteForm;
import com.contato.model.Contato;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.VerticalLayout;
import com.view.componente.PainelInfo;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

public class ClienteAdminView extends ViewComponente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClienteForm       clienteForm;
	private ClienteController clienteController;
	
	private VerticalLayout    modoLayoutAgenda =null;
	private VerticalLayout    modoLayoutLivroContato =null;
	
	public TabelaFiltro       tabelaFiltro;
	
	public PainelInfo         painelInfo; 
	
	private BeanItemContainer<Cliente> beanCliente;
	
	public ClienteAdminView(ClienteController clienteController){
		super();
	    this.clienteController = clienteController;
	}
	
	@Override
    public void modoTabela(){
		tabelaFiltro = new TabelaFiltro("Cliente");
    	tabelaFiltro.setImmediate(true);
		tabelaFiltro.filterField.addListener(new TextChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub
				filtro(event);  
			}
		});
		
		tabelaFiltro.tableMain.addListener(new ItemClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void itemClick(ItemClickEvent event) {
				
				if (event.isDoubleClick()) {	
					if(event.getItemId() instanceof Cliente){
					clienteController.visualizar(event.getItemId());
					}
					else{
					    clienteController.visualizar(((Contato)event.getItemId()).getCliente());
					}
				}
			}
		});
		
		addComponent(modotabelaFiltro());
		setComponent(modoLayoutTable);
		defaultTable();
    }
	
	 public void filtro(TextChangeEvent event){
		beanCliente.removeAllContainerFilters();
		beanCliente.addContainerFilter("infoCliente", event.getText(), true, false);
	}	

	@Override
	public VerticalLayout modotabelaFiltro() {
		modoLayoutTable = new VerticalLayout();
		modoLayoutTable.setHeight("500px");
		modoLayoutTable.setMargin(true);
	
		modoLayoutTable.addComponent(tabelaFiltro);
		return modoLayoutTable;
	}

	@Override
	public VerticalLayout modovisualizarView() {
		if(modoLayoutView == null){ modoLayoutView = new VerticalLayout();}
		modoLayoutView.setMargin(true);
		modoLayoutView.setSpacing(true);
		modoLayoutView.removeAllComponents();
		clienteForm = new ClienteForm(clienteController.getCliente());
		painelInfo = new PainelInfo("Cliente",clienteController.getCliente().getId(),clienteController.getCliente().getNomeFantasia(),clienteController.getCliente().getDataCadastro(), clienteController.getCliente().getDataUltimaAlteracao(), clienteController.getCliente().getImagem());
		modoLayoutView.addComponent(painelInfo);
		modoLayoutView.addComponent(clienteForm);
		return modoLayoutView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
		if(modoLayoutAdd == null){ modoLayoutAdd = new VerticalLayout();}
			modoLayoutAdd.setMargin(true);
			modoLayoutAdd.setSpacing(true);
			modoLayoutAdd.removeAllComponents();
			clienteForm = new ClienteForm(clienteController.getCliente());
			modoLayoutAdd.addComponent(clienteForm);
			return modoLayoutAdd;
	}
	
	public VerticalLayout modoLivroContato() {
		
		if(getComponent() != null){
	       removeComponent(getComponent());
	    }
	    
		if(modoLayoutLivroContato == null){ 
			modoLayoutLivroContato = new VerticalLayout();
			modoLayoutLivroContato.setMargin(true);
			modoLayoutLivroContato.setSpacing(true);
		}
		else{
			modoLayoutLivroContato.removeAllComponents();
		}
		modoLayoutLivroContato.addComponent(clienteController.getContatoController().modoContatoView("livro"));
		addComponent(modoLayoutLivroContato);
		setComponent(modoLayoutLivroContato);
	
		buttonVoltar.setVisible(true);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(true);
    	buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(false);
    	buttonDeletar.setVisible(false);
		
    	return modoLayoutLivroContato;
		
	}
	

	public VerticalLayout modoAgendaView() {
		
		if(getComponent() != null){
	       removeComponent(getComponent());
	    }
	    
		if(modoLayoutAgenda == null){ 
			modoLayoutAgenda = new VerticalLayout();
			modoLayoutAgenda.setMargin(true);
			modoLayoutAgenda.setSpacing(true);
		}
		else{
			modoLayoutAgenda.removeAllComponents();
		}
		
	//	modoLayoutAgenda.addComponent(new AgendaController().getAgendaView());
		addComponent(modoLayoutAgenda);
		setComponent(modoLayoutAgenda);
	
		buttonVoltar.setVisible(true);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(false);
    	buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(false);
    	buttonDeletar.setVisible(false);
		
    	return modoLayoutAgenda;
		
	}
	
	@Override
	public void editar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void adicionar() {
		if(getComponent() != null){
	    removeComponent(getComponent());
	    }
        clienteController.modoAdicionar();
		addComponent(modoadicionarView());
	    setComponent(modoLayoutAdd);
	    
	    buttonVoltar.setVisible(true);
	    buttonEditar.setVisible(false);
	    buttonSalvar.setVisible(true);
	    buttonClonar.setVisible(false);
	    buttonAdicionar.setVisible(false);
	
	}

	@Override
	public void remover(Object target) {
	 try{	
		if(clienteController.remover()){
			MessageBox mb = new MessageBox(getWindow(), 
					"Exclusão", 
                    MessageBox.Icon.INFO, 
                    "Cliente removido com Sucesso",  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
			mb.show(); 
			voltar();
		}else{
			MessageBox mb = new MessageBox(getWindow(), 
					"Exclusão", 
                    MessageBox.Icon.INFO, 
                    "Não foi possível excluir o Cliente",  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
			mb.show(); 
		   }
	 }catch (Exception e) {
		 MessageBox mb = new MessageBox(getWindow(), 
					"Exclusão", 
                 MessageBox.Icon.INFO, 
                 "Erro ao Excluir Cliente: " + e.getMessage(),  
                 new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
			mb.show(); 
        }	
	}

	@Override
	public void voltar() {
		removeComponent(getComponent());
		
    	addComponent(modoLayoutTable);
    	setComponent(modoLayoutTable);
    	
    	buttonVoltar.setVisible(false);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(false);
    	buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(true);
    	buttonDeletar.setVisible(false);
    	
    	clienteController.voltar();
 	}

	@Override
	public void salvar() {
		Cliente cliente = null;
		try{
		   cliente = clienteForm.getCliente();
		  
		  if(clienteController.getEstado().equals("visualizar")){
		       cliente.setImagem(painelInfo.getCaminhoImagem());
		  }
		  
		  if(clienteController.salvar(cliente)){
			    clienteController.setCliente(cliente);
			    cliente = null;
			    MessageBox mb = new MessageBox(getWindow(), 
						"Cadastro", 
	                    MessageBox.Icon.INFO, 
	                    "Cliente salvo com sucesso",  
	                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
				mb.show(); 
		  }
		  else { MessageBox mb = new MessageBox(getWindow(), 
					"Exclusão", 
                    MessageBox.Icon.INFO, 
                    "Não foi possível salvar o Cliente",  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
	   		      mb.show(); 
		          update();
			   }
		}
		catch (Exception e) {
			MessageBox mb = new MessageBox(getWindow(), 
					"Exclusão", 
                    MessageBox.Icon.INFO, 
                    "Não foi possível salvar o Cliente " + e.getMessage(),  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
	   		      mb.show(); 
		}
	}

	public void visualizar(Object cliente) {
		if(getComponent() != null){
	    	removeComponent(getComponent());
	    }
	    
		clienteController.setCliente((Cliente)cliente);
    	
		addComponent(modovisualizarView());
	    setComponent(modoLayoutView);
	    
	    buttonVoltar.setVisible(true);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(true);
    	buttonDeletar.setVisible(true);
        buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(false);	
	}
	
	@Override
	public void visualizar(Object[] cliente) {}
	
   public void update(){
	   beanCliente.removeAllItems();
	   beanCliente = null;
	   tabelaFiltro.tableMain.setContainerDataSource(beanCliente);
	   defaultTable();
   }	
  
   public void removeClienteLista(Cliente cliente){
	   beanCliente.removeItem(cliente);
	}
   
   public void insereClienteLista(Cliente cliente){
	   beanCliente.addBean(cliente);
	}
   
   public void defaultTable(){
		beanCliente = Cliente.listaBens(clienteController.session);
		tabelaFiltro.tableMain.setContainerDataSource(beanCliente);
		tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id","dataCadastro","razaoSocial","status","site","ie"});
	}
	
}
