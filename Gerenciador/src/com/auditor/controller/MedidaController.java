package com.auditor.controller;

import com.auditor.model.PalavraChave;
import com.auditor.model.Medida;
import com.auditor.view.MedidaView;

public class MedidaController {

	private Medida medida = null;
	private MedidaView medidaView = null;
	private PalavraChave palavra = null;

	
	public MedidaController(){
		
		medidaView = new MedidaView(this);
		medidaView.modoTabela();
		defaultTable();
	}
	
	public boolean salvar(Medida medida){
	
		if(medida.salvar()){
			String palavras = medidaView.getpChaveForm().palavraChave.getValue().toString();
	        String listPalavras[] = palavras.split(";");
			String palavrasRepetidas = "";
			
			for (int i = 0; i < listPalavras.length; i++) {
				palavra = new PalavraChave();
				palavra.setPalavraChave(listPalavras[i].trim());
				palavra.setMedida(medida);
				
				if(!palavra.salvar()){
					palavrasRepetidas += "</br>"+listPalavras[i];
				}
			}
			
			if(palavrasRepetidas.length() > 0){
				medidaView.msgAviso("Palavra-chave repetida: "+palavrasRepetidas);
			}
			
			defaultTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean alterar(Medida medida){
		
		if(medida.alterar()){
			defaultTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean removerButton(Medida medida){
		
		if(remover(medida.getId())){
			defaultTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		Medida medida = Medida.pesquisaTipoID(id);
		
		result = medida.remover();
		defaultTable();
		return result;
	}
	
	public Medida getMedida() {
		if(medida == null){
			medida = new Medida();
		}
		return medida;
	}
	
	public Medida getNovaMedida(){
		medida = new Medida();
		return medida;
	}
	
	public MedidaView getView(){
		defaultTable();
		return this.medidaView;
	}
	
	public void visualizar(Object event){
		
		medida = (Medida) event;
		Object[] dados = new Object[25];
		medidaView.visualizar(dados);
	}
	
	public void defaultTable(){
		medidaView.tabelaFiltro.tableMain.setContainerDataSource(Medida.ListaBens());
		medidaView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id", "descricao", "observacao"});
	}
}
