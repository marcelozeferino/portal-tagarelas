package com.br.portaltagarelas.facade;

import com.br.portaltagarelas.model.Usuario;

public interface UsuarioFacadeIntf {

	public Usuario retornaUsuarioPorEmailFacade(String email) throws Exception;
	
	public boolean cadastrarUsuarioFacade(Usuario usuario) throws Exception;
	
	public boolean alterarUsuarioFacade(Usuario usuario) throws Exception;
}