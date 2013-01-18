package com.contato.controller;

import java.util.Collection;
import java.util.Iterator;

import com.cliente.controller.ClienteController;
import com.contato.model.Contato;
import com.endereco.model.Endereco;
import com.contato.view.ContatoView;
import com.funcionario.controller.FuncionarioController;
import com.midiasocial.controller.MidiaSocialContatoController;

import com.midiasocial.model.MidiaSocial;
import com.telefone.controller.TelefoneController;
import com.telefone.model.TelefoneContato;


public class ContatoController {

	private ContatoView                 contatoView = null;
	private ClienteController           clienteController = null;
	private FuncionarioController       funcionarioController = null;
	private Collection<Contato>		    contatos;
	private Contato                     contato = null;
	private MidiaSocialContatoController mediaSocialController = null;
	private TelefoneController          telefoneController = null;
	
	public ContatoController(ClienteController clienteController){
		this.clienteController = clienteController;
		contatos =  this.clienteController.getCliente().getContato();
	}
	
	public ContatoController(FuncionarioController funController){
		this.funcionarioController = funController;
		contatos =  this.funcionarioController.getFuncionario().getContato();
	}
	
	public boolean salvar(){
		
		Contato contato = getContatoView().getContato();
		
		if(contato != null){

			setContato(contato);

			getContato().getEndereco().salvar();

			if(clienteController != null){
				getContato().setCliente(clienteController.getCliente());
			}
			else
				getContato().setFuncionario(funcionarioController.getFuncionario());

			if(getContato().salvar()){

				for(Iterator<MidiaSocial> i = mediaSocialController.getListaMedia().iterator(); i.hasNext();){
					MidiaSocial media = i.next();
					if(media.getId_media() == null){
						media.salvar();
					}
				}

				for(Iterator<TelefoneContato> i = telefoneController.getListaTelefone().iterator(); i.hasNext();){
					TelefoneContato telefone = i.next();
					if(telefone.getId() == null){
						telefone.salvar();
					}else{ telefone.alterar();}
				}
				return true;
			}
			else {return false;}
		}
		return false;
	}
	
	public void alterar(){
		
		
		contato.getEndereco().alterar();
		
		setContato(getContatoView().getContato());
		contato.alterar();
	
		for(Iterator<MidiaSocial> i = mediaSocialController.getListaMedia().iterator(); i.hasNext();){
	        MidiaSocial media = i.next();
	        if(media.getId_media() == null){
	        media.salvar();
	        }else{ media.alterar();}
	     }
	    
		for(Iterator<TelefoneContato> i = telefoneController.getListaTelefone().iterator(); i.hasNext();){
	        TelefoneContato telefone = i.next();
	        if(telefone.getId() == null){
	        telefone.salvar();
	        }else{ telefone.alterar();}
	     }
		
	}
	
	public boolean remover(Contato contato){
			if(contato.remover())
			return true;
			else return false;
	}
	
    public boolean removerMedia(MidiaSocial mediaCliente){
		
		if(mediaCliente.remover()){
			contato.getMedia().remove(mediaCliente);
			return true;
		}
		else
			return false;
	}
    
    public boolean removerTelefone(TelefoneContato telefonCliente){
		
		if(telefonCliente.remover()){
			contato.getTelefoneContato().remove(telefonCliente);
			return true;
		}
		else
			return false;
	}
	
	public Contato getContato() {
		if(contato == null){
			contato = new Contato();
			contato.setEndereco(new Endereco());
		}
	
		return contato;
	}
	
	
	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public ContatoView getContatoView() {
		if(contatoView == null){
			contatoView = new ContatoView(this);
		}
		return contatoView;
	}
	
	
	public void modoVisualizar(Contato contato){
		setContato(contato);
	    getContato().setEndereco(contato.getEndereco());
	    if(clienteController != null){
		clienteController.setEstado("livro");
	    }
	    else
	    	funcionarioController.setEstado("livro");
	}
	
	public void modoAdicionar(Contato contato){
		setContato(null);
		if(clienteController != null){
				clienteController.setEstado("addlivro");
			    }
			    else
			    	funcionarioController.setEstado("addlivro");
	}
	
	public ContatoView modoContatoView(String opcao) {
		if(contatoView == null){
			contatoView = new ContatoView(this);
			System.out.println(this);
		}
		
		if(opcao.equals("add")){
			contatoView.modoAdicionarContato();
		}
		
		if(opcao.equals("livro")){
			contatoView.modoLivroContato();
		}
		return contatoView;
	}
	
	public void atualizaDetalhes(){
		contatoView.atualizaDetalhes(getContato());
	}

	public void setContatoView(ContatoView contatoView) {
		this.contatoView = contatoView;
	}

	
	
	/**
	 * @return the contatos
	 */
	public Collection<Contato> getContatos() {
		return contatos;
	}

	/**
	 * @param contatos the contatos to set
	 */
	public void setContatos(Collection<Contato> contatos) {
		this.contatos = contatos;
	}

	public MidiaSocialContatoController getMediaController(){
		  mediaSocialController = new MidiaSocialContatoController(getContato().getMedia(),getContato());
		  return mediaSocialController;
	}
	
	public TelefoneController getTelefoneController(){
		  telefoneController = new TelefoneController(getContato().getTelefoneContato(),getContato());
		  return telefoneController;
	}
	
	
	
}
