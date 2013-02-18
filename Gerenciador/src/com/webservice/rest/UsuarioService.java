package com.webservice.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;

import com.abstracts.dao.DAO;
import com.principal.helper.HibernateHelper;
import com.usuario.dao.UsuarioDAO;
import com.usuario.model.Usuario;

@Path("/usuario")
public class UsuarioService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO(Usuario.class);
		Usuario usuario = usuarioDAO.buscaUsuario(1l);
	    usuario.setSenha(null);
		return usuario;
	}
	
	@Path("/autentica/{user}/{senha}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario autentica(@PathParam("user") String user, @PathParam("senha") String senha) {
		Usuario usuario = Usuario.autenticaUsu(user, senha);
		return usuario;
	}
	
    /*
     * 
     * 
	@Path("{adicionar}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String adicionaProposta(Proposta proposta) {
		proposta.setId(propostaMap.size() + 1);
		propostaMap.put(proposta.getId(), proposta);
		return proposta.getProposta() + " adicionado.";
	}

	@Path("{id}")
	@PUT
	@Consumes("text/xml")
	@Produces("text/plain")
	public String atualizaBanda(Proposta banda, @PathParam("id") int id) {
		Proposta atual = propostaMap.get(id);
		atual.setProposta(banda.getProposta());
			return banda.getProposta() + " atualizada.";
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String removeBanda(@PathParam("id") int id) {
		propostaMap.remove(id);
		return "Banda removida.";
	}
	*/
}
