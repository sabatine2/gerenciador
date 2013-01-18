package com.auditor.view.menu;

import java.util.Iterator;

import com.auditor.controller.CentroCustoController;
import com.auditor.controller.DepartamentoController;
import com.auditor.controller.EnderecoAuditorController;
import com.auditor.controller.OrganizacaoController;
import com.auditor.controller.PrestadoraTelefoniaController;
import com.auditor.controller.ReferenciaServicoController;
import com.auditor.controller.TelefoneController;
import com.auditor.controller.TipoServicoController;
import com.auditor.controller.MedidaController;
import com.auditor.controller.UnidadeController;
import com.dispositivo.controller.DispositivoController;
import com.regiao.controller.RegiaoController;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.NativeButton;

@SuppressWarnings("serial")
public class MenuAuditor extends HorizontalSplitPanel implements ClickListener{
	
	private OrganizacaoController orgController;
	private CssLayout menu;
	private AbsoluteLayout verticalLayoutRegiao;
	private AbsoluteLayout verticalLayoutOrganizacao;
	private AbsoluteLayout verticalLayoutCentroCusto;
	private AbsoluteLayout verticalLayoutUnidade;
	private AbsoluteLayout verticalLayoutDepartamento;
	private AbsoluteLayout verticalLayoutTelefone;
	private AbsoluteLayout verticalLayoutReferenciaServico;
	private AbsoluteLayout verticalLayoutTelefonia;
	private AbsoluteLayout verticalLayoutEnderecoAuditor;
	private AbsoluteLayout verticalLayoutDispositivo;
	private CentroCustoController centroCustoController;
	private UnidadeController unidadeController;
	private RegiaoController regiaoController;
	private DepartamentoController departamentoController;
	private TelefoneController telefoneController;
	private ReferenciaServicoController referenciaController;
	private PrestadoraTelefoniaController prestadoraController;
	private EnderecoAuditorController enderecoAuditorController;
	private DispositivoController dispositivoController;
	private NativeButton buttonOrganizacao;
	private NativeButton buttonRegiao;
	private NativeButton buttonEnderecoAuditor;
	private NativeButton buttonPrestadora;
	private Label labelFuncionalidades;
	private NativeButton buttonReferencia;
	private NativeButton buttonUnidade;
	private NativeButton buttonDepartamento;
	private NativeButton buttonCentroCusto;
	private NativeButton buttonTelefone;
	private NativeButton buttonDispositivo;
	private NativeButton buttonTopico;
	private Label labelUtil;
	private  NativeButton buttonTipoServico;
	private AbsoluteLayout verticalLayoutTipoServico;
	private TipoServicoController tipoController;
	private NativeButton buttonMedida;
	private AbsoluteLayout verticalLayoutMedida;
	private MedidaController medidaController;
	
	public MenuAuditor(OrganizacaoController orgController){
		this.orgController = orgController;
		
		//Estilo e posi��o da barra
	    addStyleName("small blue white");
	    setSplitPosition(0);
	    setWidth("100%");
	    setHeight("100%");
	    setMargin(true);
	        
	    //Define CSS
	    menu = new CssLayout();
	    menu.addStyleName("menu");
	    menu.setWidth("100%");
	    setFirstComponent(menu);
	    
	    buttonOrganizacao= new NativeButton("Dados Organizacao");
	    verticalLayoutOrganizacao = new AbsoluteLayout();
    	verticalLayoutOrganizacao.setHeight("100.0%");
    	verticalLayoutOrganizacao.setWidth("100.0%");
    	verticalLayoutOrganizacao.addComponent(orgController.getView(), "top:0.0px;left:0.0px;");
        buttonOrganizacao.setData(verticalLayoutOrganizacao);
	    
        setSecondComponent(verticalLayoutOrganizacao);
	}
	
	public void moduloVisualizar(){
        setSplitPosition(16);
		
		//Adiciona Categoria Auditor
	    labelFuncionalidades = new Label("Auditor");
	    labelFuncionalidades.addStyleName("section");	    
	    menu.addComponent(labelFuncionalidades);
	    
	    //Bot�o Configura��o Teste Organizacao
        //NativeButton buttonOrganizacao = new NativeButton("Dados Organizacao");
        buttonOrganizacao.setHeight("30px");
        buttonOrganizacao.addStyleName("selected");
        buttonOrganizacao.addListener(this);
        menu.addComponent(buttonOrganizacao);
     
        /*AbsoluteLayout verticalLayoutOrganizacao = new AbsoluteLayout();
    	verticalLayoutOrganizacao.setHeight("100.0%");
    	verticalLayoutOrganizacao.setWidth("100.0%");
    	verticalLayoutOrganizacao.addComponent(orgController.getView(), "top:0.0px;left:0.0px;");
        buttonOrganizacao.setData(verticalLayoutOrganizacao);*/
        
        //Bot�o Configura��o Teste Centro Custo
        buttonCentroCusto = new NativeButton("Centro Custo");
        buttonCentroCusto.setHeight("30px");
        buttonCentroCusto.addStyleName("selected");
        buttonCentroCusto.addListener(this);
        menu.addComponent(buttonCentroCusto);
     
        verticalLayoutCentroCusto = new AbsoluteLayout();
    	verticalLayoutCentroCusto.setHeight("100.0%");
    	verticalLayoutCentroCusto.setWidth("100.0%");
   
    	centroCustoController = new CentroCustoController(orgController);
    	
    	verticalLayoutCentroCusto.addComponent(centroCustoController.getView(), "top:0.0px;left:0.0px;");
        buttonCentroCusto.setData(verticalLayoutCentroCusto);
        
        //Bot�o Configura��o Teste Unidade
        buttonUnidade = new NativeButton("Unidade");
        buttonUnidade.setHeight("30px");
        buttonUnidade.addStyleName("selected");
        buttonUnidade.addListener(this);
        menu.addComponent(buttonUnidade);
     
        verticalLayoutUnidade = new AbsoluteLayout();
    	verticalLayoutUnidade.setHeight("100.0%");
    	verticalLayoutUnidade.setWidth("100.0%");
   
    	unidadeController = new UnidadeController(orgController);
    	
    	verticalLayoutUnidade.addComponent(unidadeController.getView(), "top:0.0px;left:0.0px;");
        buttonUnidade.setData(verticalLayoutUnidade);
        
        //Bot�o Configura��o Teste Departamento
        buttonDepartamento = new NativeButton("Departamento");
        buttonDepartamento.setHeight("30px");
        buttonDepartamento.addStyleName("selected");
        buttonDepartamento.addListener(this);
        menu.addComponent(buttonDepartamento);
     
        verticalLayoutDepartamento = new AbsoluteLayout();
    	verticalLayoutDepartamento.setHeight("100.0%");
    	verticalLayoutDepartamento.setWidth("100.0%");
   
    	departamentoController = new DepartamentoController(orgController);
    	
    	verticalLayoutDepartamento.addComponent(departamentoController.getView(), "top:0.0px;left:0.0px;");
        buttonDepartamento.setData(verticalLayoutDepartamento);	 
        
        //Bot�o Configura��o Telefone
        buttonTelefone = new NativeButton("Telefone");
        buttonTelefone.setHeight("30px");
        buttonTelefone.addStyleName("selected");
        buttonTelefone.addListener(this);
        menu.addComponent(buttonTelefone);
     
        verticalLayoutTelefone = new AbsoluteLayout();
    	verticalLayoutTelefone.setHeight("100.0%");
    	verticalLayoutTelefone.setWidth("100.0%");
   
    	telefoneController = new TelefoneController(orgController);
    	
    	verticalLayoutTelefone.addComponent(telefoneController.getView(), "top:0.0px;left:0.0px;");
        buttonTelefone.setData(verticalLayoutTelefone);	 
        
        //Bot�o Configura��o Dispositivo
        buttonDispositivo = new NativeButton("Dispositivo");
        buttonDispositivo.setHeight("30px");
        buttonDispositivo.addStyleName("selected");
        buttonDispositivo.addListener(this);
        menu.addComponent(buttonDispositivo);
     
        verticalLayoutDispositivo = new AbsoluteLayout();
    	verticalLayoutDispositivo.setHeight("100.0%");
    	verticalLayoutDispositivo.setWidth("100.0%");
   
    	dispositivoController = new DispositivoController(orgController);
    	
    	verticalLayoutDispositivo.addComponent(dispositivoController.getView(), "top:0.0px;left:0.0px;");
        buttonDispositivo.setData(verticalLayoutDispositivo);	 
        
        //Adiciona Categoria Auditor
	    labelUtil = new Label("Util");
	    labelUtil.addStyleName("section");
	    menu.addComponent(labelUtil);
        
        //Bot�o Configura��o Teste ReferenciaServico
        buttonReferencia = new NativeButton("Referencia");
        buttonReferencia.setHeight("30px");
        buttonReferencia.addStyleName("selected");
        buttonReferencia.addListener(this);
        menu.addComponent(buttonReferencia);
     
        verticalLayoutReferenciaServico = new AbsoluteLayout();
    	verticalLayoutReferenciaServico.setHeight("100.0%");
    	verticalLayoutReferenciaServico.setWidth("100.0%");
   
    	referenciaController = new ReferenciaServicoController();
    	
    	verticalLayoutReferenciaServico.addComponent(referenciaController.getView(), "top:0.0px;left:0.0px;");
        buttonReferencia.setData(verticalLayoutReferenciaServico);
        
        //Bot�o Configura��o Prestadora
        buttonPrestadora = new NativeButton("Prestadora");
        buttonPrestadora.setHeight("30px");
        buttonPrestadora.addStyleName("selected");
        buttonPrestadora.addListener(this);
        menu.addComponent(buttonPrestadora);
     
        verticalLayoutTelefonia = new AbsoluteLayout();
    	verticalLayoutTelefonia.setHeight("100.0%");
    	verticalLayoutTelefonia .setWidth("100.0%");
   
    	prestadoraController = new PrestadoraTelefoniaController();
    	
    	verticalLayoutTelefonia.addComponent(prestadoraController.getView(), "top:0.0px;left:0.0px;");
        buttonPrestadora.setData(verticalLayoutTelefonia);
        
        //Botao TipoServico
        buttonTipoServico = new NativeButton("Tipo Servi�o");
        buttonTipoServico.setHeight("30px");
        buttonTipoServico.addStyleName("selected");
        buttonTipoServico.addListener(this);
        menu.addComponent(buttonTipoServico);
     
        verticalLayoutTipoServico = new AbsoluteLayout();
    	verticalLayoutTipoServico.setHeight("100%");
    	verticalLayoutTipoServico.setWidth("100.0%");
    	
    	tipoController = new TipoServicoController();
    	
    	verticalLayoutTipoServico.addComponent(tipoController.getView(), "top:0.0px;left:0.0px;");
        buttonTipoServico.setData(verticalLayoutTipoServico);
        
        //Botao Medida
        buttonMedida = new NativeButton("Medida");
        buttonMedida.setHeight("30px");
        buttonMedida.addStyleName("selected");
        buttonMedida.addListener(this);
        menu.addComponent(buttonMedida);
     
        verticalLayoutMedida = new AbsoluteLayout();
    	verticalLayoutMedida.setHeight("100%");
    	verticalLayoutMedida.setWidth("100.0%");
    	
    	medidaController = new MedidaController();
    	
    	verticalLayoutMedida.addComponent(medidaController.getView(), "top:0.0px;left:0.0px;");
        buttonMedida.setData(verticalLayoutMedida);
        
      
        //Botao Regiao
        buttonRegiao = new NativeButton("Regi�o");
        buttonRegiao.setHeight("30px");
        buttonRegiao.addStyleName("selected");
        buttonRegiao.addListener(this);
        menu.addComponent(buttonRegiao);
     
        verticalLayoutRegiao = new AbsoluteLayout();
    	verticalLayoutRegiao.setHeight("100%");
    	verticalLayoutRegiao.setWidth("100.0%");
    	
    	regiaoController = new RegiaoController();
    	
    	verticalLayoutRegiao.addComponent(regiaoController.getView(), "top:0.0px;left:0.0px;");
        buttonRegiao.setData(verticalLayoutRegiao);
        
        //Bot�o Configura��o Endereco Auditor
        buttonEnderecoAuditor = new NativeButton("Endereco");
        buttonEnderecoAuditor.setHeight("30px");
        buttonEnderecoAuditor.addStyleName("selected");
        buttonEnderecoAuditor.addListener(this);
        menu.addComponent(buttonEnderecoAuditor);
     
        verticalLayoutEnderecoAuditor = new AbsoluteLayout();
    	verticalLayoutEnderecoAuditor.setHeight("100.0%");
    	verticalLayoutEnderecoAuditor.setWidth("100.0%");
   
    	enderecoAuditorController = new EnderecoAuditorController();
    	
    	verticalLayoutEnderecoAuditor.addComponent(enderecoAuditorController.getView(), "top:0.0px;left:0.0px;");
        buttonEnderecoAuditor.setData(verticalLayoutEnderecoAuditor);
   	}
	
	public void moduloVoltar(){
		
		try {
			menu.removeComponent(labelFuncionalidades);
			menu.removeComponent(buttonEnderecoAuditor);
			menu.removeComponent(buttonPrestadora);
			menu.removeComponent(buttonReferencia);
			menu.removeComponent(labelUtil);
			menu.removeComponent(buttonDepartamento);
			menu.removeComponent(buttonUnidade);
			menu.removeComponent(buttonCentroCusto);
			menu.removeComponent(buttonOrganizacao);
			menu.removeComponent(buttonTelefone);
			menu.removeComponent(buttonTipoServico);
			menu.removeComponent(buttonMedida);
			menu.removeComponent(buttonDispositivo);
			menu.removeComponent(buttonTopico);
			menu.removeComponent(buttonRegiao);
			setSplitPosition(0);
			
		} catch (Exception e) {
			setSplitPosition(0);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void buttonClick(ClickEvent event) {
		ComponentContainer p = (ComponentContainer) event.getButton()
                .getParent();
        for (Iterator iterator = p.getComponentIterator(); iterator
                .hasNext();) {
            ((AbstractComponent) iterator.next())
                    .removeStyleName("selected");
        }
        event.getButton().addStyleName("selected");
        
        atualizar();
        setSecondComponent((Component) event.getButton()
                .getData());
	}
	
	public void atualizar(){
		//orgController.setOrganizacao(orgController.getOrganizacao().pesquisaOrganizacaoID());
		orgController.refreshTable();
	 	centroCustoController.getCentroView().carregaTabela();
    	unidadeController.refreshTable();
        departamentoController.defaultTable(); 
	}
}
