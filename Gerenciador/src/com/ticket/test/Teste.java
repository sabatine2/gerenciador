package com.ticket.test;

import java.util.Date;

import com.cliente.model.Cliente;
import com.contato.model.Contato;
import com.funcionario.model.Funcionario;
import com.ticket.model.Mensagem;
import com.ticket.model.Ordem;
import com.ticket.model.Prioridade;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    /*
		Prioridade prioridade = new Prioridade();
		prioridade.setPrioridade("Normal");
		prioridade.setPrioridadeUrgencia(1);
		prioridade.setPublico("nao");
		prioridade.setCor("amarela");
		prioridade.salvar();
	
		Funcionario f = Funcionario.pesquisaFuncionarioID(7l);
		Cliente c = Cliente.pesquisaClienteID(1l);
		Contato con = Contato.pesquisaContatoID(1l);
		
		Ordem o = new Ordem();
		o.setFonte("WEB");
		o.setContato(con);
		o.setAssunto("Teste");
		o.setFuncionario(f);
		o.setDepartamento(f.getDepartamento());
		o.setPrioridade(prioridade);
		o.setStatus("Aberto");
		o.salvar();
		*/
		
		Ordem o = Ordem.pesquisaOrdemID(1l);
	    o.remover();
	
		
	}

}
