package com.br.portaltagarelas.dao;

import com.br.portaltagarelas.model.Usuario;

public interface UsuarioDAOIntf {

	public Usuario retornaUsuarioPorEmail(String email) throws Exception;
	
	public boolean cadastrarUsuario(Usuario usuario) throws Exception;
	
	public boolean alterarUsuario(Usuario usuario) throws Exception;
}