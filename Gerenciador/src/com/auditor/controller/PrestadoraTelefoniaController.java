package com.auditor.controller;

import com.auditor.dao.PrestadoraTelefoniaDAO;
import com.auditor.model.contato.PrestadoraTelefonia;
import com.auditor.view.PrestadoraTelefoniaView;

public class PrestadoraTelefoniaController  {

	private PrestadoraTelefonia prestadora = null;
	private PrestadoraTelefoniaView prestadoraView = null;
	
	public PrestadoraTelefoniaController(){
	     prestadoraView = new PrestadoraTelefoniaView(this);
	     prestadoraView.modoTabela();
	     defaultTable();
	}
	
	public boolean salvar(PrestadoraTelefonia prestadora){
		
		if(prestadora.salvar()){
			defaultTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean alterar(PrestadoraTelefonia prestadora){
		
		if(prestadora.alterar()){
			defaultTable();
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean removerButton(PrestadoraTelefonia prestadora){
		
		if(remover(prestadora.getCodigo())){
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		
		org.hibernate.Session s = com.principal.helper.HibernateUtil.openSession();
		PrestadoraTelefoniaDAO prestadoraDAO = new PrestadoraTelefoniaDAO(s, PrestadoraTelefonia.class);
		PrestadoraTelefonia prestadora = prestadoraDAO.buscaPrestadoraTelefonia(id);
		s.close();
		
		result = prestadora.remover();
		defaultTable();
		return result;
	}
	
	public PrestadoraTelefonia getPrestadora() {
		if(prestadora == null){
			prestadora = new PrestadoraTelefonia();
		}
		
		return prestadora;
	}	
	
	public PrestadoraTelefonia getNovaPrestadora(){
		prestadora = new PrestadoraTelefonia();
		return prestadora;
	}
	
	public PrestadoraTelefoniaView getPrestadoraView() {
		return prestadoraView;
	}

	public void setPrestadoraView(PrestadoraTelefoniaView prestadoraView) {
		this.prestadoraView = prestadoraView;
	}

	public void setPrestadora(PrestadoraTelefonia prestadora) {
		this.prestadora = prestadora;
	}

	public PrestadoraTelefoniaView getView(){
		  return this.prestadoraView;
	}
	
	public void visualizar(Object event){
		
		prestadora = (PrestadoraTelefonia) event;
		
		Object[] dados = new Object[4];
		dados[0] = prestadora.getCodigo();
		dados[1] = prestadora.getNome();
		dados[2] = prestadora.getCnpj();
		dados[3] = prestadora.getEstado();
		
		prestadoraView.visualizar(dados);
	}
	
	public void defaultTable(){
		prestadoraView.tabelaFiltro.tableMain.setContainerDataSource(PrestadoraTelefonia.ListaBens());
		prestadoraView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"codigo", "nome" ,"estado", "cnpj"});
	}
	
	public void filtro(String nome){
		PrestadoraTelefonia.ListaBens().removeAllContainerFilters();
		PrestadoraTelefonia.ListaBens().addContainerFilter("nome", nome, true, false);
	}
	
	public boolean validarCNPJ(String cnpj) {
		
		if (cnpj.length() == 11) {
			int acumulador1 = 0;
			int resto1 = 0;
			
			for (int i = 0; i < 9; i++) {
			
				acumulador1 += Character.getNumericValue(cnpj.charAt(i)) * (10-i);
				System.out.println("Acumulador1: " + acumulador1);
			}
			resto1 = 11 - acumulador1 %11;
			
			if (resto1 == 10) {
				resto1 = 0;
			}
			
			if (resto1 == Character.getNumericValue(cnpj.charAt(9))) {
				int acumulador2 = 0;
				int resto2 = 0;
				
				for (int i = 0; i < 10; i++) {
					
					acumulador2 += Character.getNumericValue(cnpj.charAt(i)) * (11-i);
					System.out.println("Acumulador2: " + acumulador2);
				}
				resto2 = 11 - acumulador2 %11;
				
				if (resto2 == 10) {
					resto2 = 0;
				}
				
				if (resto2 == Character.getNumericValue(cnpj.charAt(10))) {
					
					System.out.println("D�gito1: " + resto1);
					System.out.println("D�gito2: " + resto2);
					return true;
				}
				else {
					System.out.println("ERRO CPF INVALIDO 2");
					//Mensagem.msgErro(usuarioView.getComponent(), "Erro", "CPF inv�lido");
					return false;
				}
			}
			else {
				System.out.println("ERRO CPF INVALIDO 1");
				//Mensagem.msgErro(usuarioView.getComponent(), "Erro", "CPF inv�lido");
				return false;
			}
		}
		else if(cnpj.length() == 14) {
			int acumulador1 = 0;
			int resto1 = 0;		
			
			acumulador1 = Character.getNumericValue(cnpj.charAt(0)) * 5;
			acumulador1 += Character.getNumericValue(cnpj.charAt(1)) * 4;
			acumulador1 += Character.getNumericValue(cnpj.charAt(2)) * 3;
			acumulador1 += Character.getNumericValue(cnpj.charAt(3)) * 2;
			acumulador1 += Character.getNumericValue(cnpj.charAt(4)) * 9;
			acumulador1 += Character.getNumericValue(cnpj.charAt(5)) * 8;
			acumulador1 += Character.getNumericValue(cnpj.charAt(6)) * 7;
			acumulador1 += Character.getNumericValue(cnpj.charAt(7)) * 6;
			acumulador1 += Character.getNumericValue(cnpj.charAt(8)) * 5;
			acumulador1 += Character.getNumericValue(cnpj.charAt(9)) * 4;
			acumulador1 += Character.getNumericValue(cnpj.charAt(10)) * 3;
			acumulador1 += Character.getNumericValue(cnpj.charAt(11)) * 2;
			
			resto1 = 11 - acumulador1 %11;
			
			if (resto1 == 10) {
				resto1 = 0;
			}
			
			if (resto1 == Character.getNumericValue(cnpj.charAt(12))) {
				int acumulador2 = 0;
				int resto2 = 0;		
				
				acumulador2 = Character.getNumericValue(cnpj.charAt(0)) * 6;
				acumulador2 += Character.getNumericValue(cnpj.charAt(1)) * 5;
				acumulador2 += Character.getNumericValue(cnpj.charAt(2)) * 4;
				acumulador2 += Character.getNumericValue(cnpj.charAt(3)) * 3;
				acumulador2 += Character.getNumericValue(cnpj.charAt(4)) * 2;
				acumulador2 += Character.getNumericValue(cnpj.charAt(5)) * 9;
				acumulador2 += Character.getNumericValue(cnpj.charAt(6)) * 8;
				acumulador2 += Character.getNumericValue(cnpj.charAt(7)) * 7;
				acumulador2 += Character.getNumericValue(cnpj.charAt(8)) * 6;
				acumulador2 += Character.getNumericValue(cnpj.charAt(9)) * 5;
				acumulador2 += Character.getNumericValue(cnpj.charAt(10)) * 4;
				acumulador2 += Character.getNumericValue(cnpj.charAt(11)) * 3;
				acumulador2 += Character.getNumericValue(cnpj.charAt(12)) * 2;
				
				resto2 = 11 - acumulador2 %11;
				
				if (resto2 == 10) {
					resto2 = 0;
				}
				
				if (resto2 == Character.getNumericValue(cnpj.charAt(13))) {
					
					System.out.println("D�gito1: " + resto1);
					System.out.println("D�gito2: " + resto2);
					return true;
				}
				else {
					System.out.println("ERRO CNPJ INVALIDO 2");
					//Mensagem.msgErro(usuarioView.getComponent(), "Erro", "CNPJ inv�lido");
					return false;
				}
			}
			else {
				System.out.println("ERRO CNPJ INVALIDO 1");
				//Mensagem.msgErro(usuarioView.getComponent(), "Erro", "CNPJ inv�lido");
				return false;
			}
		}
		else {
			return false;
		}
	}
}
