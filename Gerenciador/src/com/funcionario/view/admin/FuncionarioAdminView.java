package com.funcionario.view.admin;

import com.abstracts.view.ViewComponente;
import com.funcionario.controller.FuncionarioController;
import com.funcionario.model.Funcionario;
import com.funcionario.view.form.FuncionarioViewForm;
import com.usuario.model.Usuario;
import com.usuario.view.UsuarioPainel;
import com.usuario.view.UsuarioView;
import com.usuario.view.form.UsuarioForm;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.view.componente.JanelaConfirmacao;
import com.view.componente.Mensagem;
import com.view.componente.PainelInfo;
import com.view.componente.TabelaFiltro;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

public class FuncionarioAdminView extends ViewComponente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FuncionarioViewForm   funcionarioForm;
	private FuncionarioController funcionarioController;
	private VerticalLayout        modoLayoutAgenda =null;
	private VerticalLayout        modoLayoutLivroContato =null;
	private TabelaFiltro          tabelaFiltro;
	private PainelInfo            painelInfo; 
    private UsuarioForm	          usuarioForm;
    
	private BeanItemContainer<Funcionario> beanFuncionario;
	
	public FuncionarioAdminView(FuncionarioController  funcionarioController){
		super();
	    this.funcionarioController = funcionarioController;
	}
	
	@Override
    public void modoTabela(){
		tabelaFiltro = new TabelaFiltro("Funcionario");
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
					if(event.getItemId() instanceof Funcionario){
					funcionarioController.visualizar(event.getItemId());
					}
					else{
					   // funcionarioController.visualizar(((Contato)event.getItemId()).getFuncionario());
					}
				}
			}
		});
		
		tabelaFiltro.tableMain.addActionHandler(new Action.Handler() {

 			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void handleAction(Action action, Object sender, final Object target) {
 				if (action == REMOVE_ITEM_ACTION) {
 					
 					final JanelaConfirmacao janelaConfirmacao = new JanelaConfirmacao("Remover", "Deseja remover?");
 					getWindow().addWindow(janelaConfirmacao);
 				
 					janelaConfirmacao.getButtonSim().addListener(new Button.ClickListener() {
 	                /**
						 * 
						 */
						private static final long serialVersionUID = 1L;

					// inline click-listener
 	                public void buttonClick(ClickEvent event) {
 	                  	 tabelaFiltro.tableMain.removeItem(target);
 	                  	 remover(target);
 	                  	 janelaConfirmacao.getParent().removeWindow(janelaConfirmacao);
 	                	
 	                }});
 				 
 					janelaConfirmacao.getButtonNao().addListener(new Button.ClickListener() {
 					/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

					public void buttonClick(ClickEvent event) {
 		                // close the window by removing it from the parent window
 		                janelaConfirmacao.getParent().removeWindow(janelaConfirmacao);
 		            }});
 				 
 				} 
            }

            public Action[] getActions(Object target, Object sender) {
                         // Context menu for an item
                    return new Action[] { REMOVE_ITEM_ACTION };
             }
        });
					
		addComponent(tabelaFiltro());
		setComponent(modoLayoutTable);
		defaultTable();
    	
    }
	
	
	 public VerticalLayout tabelaFiltro(){
		    
	    	modoLayoutTable = new VerticalLayout();
			modoLayoutTable.setHeight("500px");
			modoLayoutTable.setMargin(true);
		
			modoLayoutTable.addComponent(tabelaFiltro);
			return modoLayoutTable;
	 }
	public void filtro(TextChangeEvent event){
		beanFuncionario.removeAllContainerFilters();
		beanFuncionario.addContainerFilter("infoFuncionario", event.getText(), true, false);
	}	

	@Override
	public VerticalLayout modotabelaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerticalLayout modovisualizarView() {
		if(modoLayoutView == null){ modoLayoutView = new VerticalLayout();}
			modoLayoutView.setMargin(true);
			modoLayoutView.setSpacing(true);
			modoLayoutView.removeAllComponents();
			funcionarioForm = new FuncionarioViewForm(funcionarioController.getFuncionario());
			painelInfo  = new PainelInfo("Funcionario",funcionarioController.getFuncionario().getId(),funcionarioController.getFuncionario().getNome(),funcionarioController.getFuncionario().getDataCadastro(), funcionarioController.getFuncionario().getDataUltimaAlteracao(), funcionarioController.getFuncionario().getImagem());
			usuarioForm = new UsuarioForm(funcionarioController.getFuncionario().getUsuario());
			usuarioForm.moduloVisualizar();
			modoLayoutView.addComponent(painelInfo);
			modoLayoutView.addComponent(funcionarioForm);
			modoLayoutView.addComponent(usuarioForm);
			
		return modoLayoutView;
	}

	@Override
	public VerticalLayout modoadicionarView() {
			if(modoLayoutAdd == null){ modoLayoutAdd = new VerticalLayout();}
			modoLayoutAdd.setMargin(true);
			modoLayoutAdd.setSpacing(true);
			modoLayoutAdd.removeAllComponents();
			funcionarioForm = new FuncionarioViewForm(funcionarioController.getFuncionario());
			usuarioForm = new UsuarioForm(new Usuario());
			usuarioForm.moduloAdicionar();
			modoLayoutAdd.addComponent(funcionarioForm);
			modoLayoutAdd.addComponent(usuarioForm);
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
		modoLayoutLivroContato.addComponent(funcionarioController.getContatoController().modoContatoView("livro"));
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
        funcionarioController.modoAdicionar();
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
		if(funcionarioController.remover()){
			Mensagem.msgConf(this, "Cliente","Cliente excluido com sucesso");
			voltar();
		}else
			Mensagem.msgErro(this, "Cliente","Cliente não excluido");
		}
	 catch (Exception e) {
		 Mensagem.msgErro(this, "Cliente",e.getMessage());
	   }	
	}
	

	@Override
	public void voltar() {
		removeComponent(getComponent());
    	addComponent(modoLayoutTable);
    	setComponent(modoLayoutTable);
    	//BOTOES//
    	buttonVoltar.setVisible(false);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(false);
    	buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(true);
    	buttonDeletar.setVisible(false);
    	
    	funcionarioController.voltar();
  	}

	@Override
	public void salvar() {
		
		
		Funcionario funcionario = null;
		Boolean     ok = true;
		try{
		  funcionario = funcionarioForm.getFuncionario();
		 
		  if(funcionarioController.getEstado().equals("visualizar")){
		       funcionario.setImagem(painelInfo.getCaminhoImagem());
		  }
		  
		  if(funcionarioController.getEstado().equals("novo")){
			  Usuario u = usuarioForm.getUsuario();
			  if(u != null){
				   u.setNivel("funcionario");
			       funcionario.setUsuario(u);
			       ok = true;
			  }
			  else {
				  ok = false;
				  Mensagem.msgErro(this,"Erro na confirmação das senhas" ,"Senhas diferentes");
			  }
		  }
		
		  if(ok)
		  if(funcionarioController.salvar(funcionario)){
			     MessageBox mb = new MessageBox(getWindow(), 
						"Funcionario", 
	                    MessageBox.Icon.INFO, 
	                    "Funcionario salvo com sucesso",  
	                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
				 mb.show(); 
				 mb= null;
				funcionarioController.setFuncionario(funcionario);
				funcionario = null;
			}
		   else {  MessageBox mb = new MessageBox(getWindow(), 
					"Exclusão", 
                    MessageBox.Icon.INFO, 
                    "Não foi possível salvar o funcionario",  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
	   		       mb.show(); 
                update();
	       }
		  else{MessageBox mb = new MessageBox(getWindow(), 
					"Exclusão", 
                   MessageBox.Icon.INFO, 
                    "Não foi possível salvar o funcionario ",  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
	   		      mb.show(); 
	   		      }
		}
		catch (Exception e) {
			MessageBox mb = new MessageBox(getWindow(), 
					"Exclusão", 
                    MessageBox.Icon.INFO, 
                    "Não foi possível salvar o funcionario " + e.getMessage(),  
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
	   		      mb.show(); 
		}
	}

	public void visualizar(Object funcionario) {
		if(getComponent() != null){
	    	removeComponent(getComponent());
	    	}
	    
		funcionarioController.setFuncionario((Funcionario)funcionario);
    	
		addComponent(modovisualizarView());
	    	
	    setComponent(modoLayoutView);
	    
	    buttonVoltar.setVisible(true);
    	buttonEditar.setVisible(false);
    	buttonSalvar.setVisible(true);
    	buttonClonar.setVisible(false);
    	buttonAdicionar.setVisible(false);
    	buttonDeletar.setVisible(true);
    }
	
	@Override
	public void visualizar(Object[] cliente) {}
	

	public void update(){
		   beanFuncionario.removeAllItems();
		   beanFuncionario = null;
		   tabelaFiltro.tableMain.setContainerDataSource(beanFuncionario);
		   defaultTable();
	}	
		
	public void removeFuncionarioLista(Funcionario funcionario){
		   beanFuncionario.removeItem(funcionario);
	}
	   
	public void insereFuncionarioLista(Funcionario funcionario){
		   beanFuncionario.addBean(funcionario);
	}
	   
	public void defaultTable(){
		
		
			beanFuncionario = Funcionario.listaBeans(funcionarioController.session);
			tabelaFiltro.tableMain.setContainerDataSource(beanFuncionario);
			tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id","infoFuncionario","rg","status","gerenciamento","dataCadastro"});
			
	}
		
}
