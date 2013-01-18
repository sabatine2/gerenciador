package com.ticket.view.form;

import com.auditor.model.Departamento;
import com.funcionario.model.Funcionario;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class FuncionarioOrdemView extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private VerticalLayout verticalLayoutFuncionario;
	private ComboBox comboBoxFuncionario;
	private ComboBox comboBoxDepartamento;
	private Funcionario funcionario;
    private Departamento departamento;
    
	public FuncionarioOrdemView(Departamento departamento) {
		
		this.funcionario = funcionario;
		this.departamento = departamento;
	    
		setStyleName(Reindeer.PANEL_LIGHT);
		setWidth("100.0%");
		setHeight("-1px");
		setCaption("Dados do Departamento");
		setImmediate(false);
		verticalLayoutFuncionario = buildVerticalLayoutFuncionario();
		setContent(verticalLayoutFuncionario);
	}

	private VerticalLayout buildVerticalLayoutFuncionario() {

		verticalLayoutFuncionario = new VerticalLayout();
		verticalLayoutFuncionario.setImmediate(false);
		verticalLayoutFuncionario.setWidth("100.0%");
		verticalLayoutFuncionario.setHeight("100.0%");
		verticalLayoutFuncionario.setMargin(false);
		verticalLayoutFuncionario.setSpacing(true);
		
		// comboBoxContato
		comboBoxDepartamento = new ComboBox();
		comboBoxDepartamento.setImmediate(true);
		comboBoxDepartamento.setWidth("100.0%");
		comboBoxDepartamento.setHeight("-1px");
		comboBoxDepartamento.setCaption("Departamento");
		comboBoxDepartamento.setRequired(true);
		comboBoxDepartamento.addListener(new Property.ValueChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				 if(event.getProperty().getValue() instanceof Departamento){
				 Departamento departamento = (Departamento)event.getProperty().getValue();
				 System.out.println(departamento.toString());
				 atualizaDepartamento(departamento);	
				 }else{
					 atualizaDepartamento(new Departamento());	
				 }
					
			}
		});
		verticalLayoutFuncionario.addComponent(comboBoxDepartamento);
	
		
		// comboBoxFuncionario
		comboBoxFuncionario = new ComboBox();
		comboBoxFuncionario.setImmediate(true);
		comboBoxFuncionario.setWidth("100.0%");
		comboBoxFuncionario.setHeight("-1px");
		comboBoxFuncionario.setCaption("Funcionario");
		comboBoxFuncionario.setValue(this.funcionario);
		verticalLayoutFuncionario.addComponent(comboBoxFuncionario);
		
			
		return verticalLayoutFuncionario;
	}

	public void  atualizaDepartamento(Departamento departamento){
		    BeanItemContainer<Funcionario> beans = new BeanItemContainer<Funcionario>(Funcionario.class);
			beans.addAll(departamento.getFuncionario());
		    comboBoxFuncionario.setContainerDataSource(beans);
		    comboBoxFuncionario.setItemCaptionPropertyId("nome");
			
		 }
	
	
	
	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the departamento
	 */
	public Departamento getDepartamento() {
		return departamento;
	}
	public void adicionar(){
		comboBoxDepartamento.setContainerDataSource(Departamento.listaBens());
		comboBoxDepartamento.setItemCaptionPropertyId("nome");
		comboBoxDepartamento.setValue(this.departamento);
	}
	
	public void visualizar(){
		try{
		comboBoxDepartamento.setNewItemsAllowed(true);
		comboBoxDepartamento.addItem(this.departamento.getNome());
		comboBoxDepartamento.setValue(this.departamento.getNome());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * @param departamento the departamento to set
	 */
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void commit(){
		comboBoxDepartamento.validate();
		
		this.departamento = (Departamento) comboBoxDepartamento.getValue();
		this.funcionario = (Funcionario) comboBoxFuncionario.getValue();
	}
}
