package com.auditor.controller;

import com.auditor.model.PalavraChave;
import com.auditor.model.TipoServico;
import com.auditor.view.TipoServicoView;

public class TipoServicoController {

	private TipoServico tipoServico = null;
	private TipoServicoView tipoView = null;
	private PalavraChave palavra = null;

	
	public TipoServicoController(){
		
		tipoView = new TipoServicoView(this);
		tipoView.modoTabela();
		defaultTable();
	}
	
	public boolean salvar(TipoServico tipoServico){
	
		if(tipoServico.salvar()){
			String palavras = tipoView.getpChaveForm().palavraChave.getValue().toString();
	        String listPalavras[] = palavras.split(";");
			String palavrasRepetidas = "";
			
			for (int i = 0; i < listPalavras.length; i++) {
				palavra = new PalavraChave();
				palavra.setPalavraChave(listPalavras[i].trim());
				palavra.setTipoServico(tipoServico);
				
				if(!palavra.salvar()){
					palavrasRepetidas += listPalavras[i] +"\n";
				}
			}
			
			if(palavrasRepetidas.length() > 0){
				tipoView.msgAviso("Palavra-chave repetida: "+palavrasRepetidas);
			}
			
			defaultTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean alterar(TipoServico tipoServico){
		
		if(tipoServico.alterar()){
			defaultTable();
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean removerButton(TipoServico tipoServico){
		
		if(remover(tipoServico.getId())){
			defaultTable();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remover(Long id){
		boolean result;
		TipoServico tipoServico = TipoServico.pesquisaTipoID(id);
		
		result = tipoServico.remover();
		defaultTable();
		return result;
	}
	
	public TipoServico getTipo() {
		if(tipoServico == null){
			tipoServico = new TipoServico();
		}
		return tipoServico;
	}
	
	public TipoServico getNovoTipo(){
		tipoServico = new TipoServico();
		return tipoServico;
	}
	
	public TipoServicoView getView(){
		defaultTable();
		return this.tipoView;
	}
	
	public void visualizar(Object event){
		
		tipoServico = (TipoServico) event;
		Object[] dados = new Object[25];
		tipoView.visualizar(dados);
	}
	
	public void defaultTable(){
		tipoView.tabelaFiltro.tableMain.setContainerDataSource(TipoServico.ListaBens());
		tipoView.tabelaFiltro.tableMain.setVisibleColumns(new Object[]{"id", "descricao", "observacao"});
	}
}