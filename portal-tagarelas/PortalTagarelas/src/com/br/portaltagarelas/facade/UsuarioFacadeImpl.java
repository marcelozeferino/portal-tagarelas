package com.br.portaltagarelas.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.portaltagarelas.dao.UsuarioDAOIntf;
import com.br.portaltagarelas.model.Usuario;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UsuarioFacadeImpl implements UsuarioFacadeIntf{

	@Autowired private UsuarioDAOIntf usuarioDAO;
	
	public Usuario retornaUsuarioPorEmailFacade(String email) throws Exception{
		try {
			return usuarioDAO.retornaUsuarioPorEmail(email);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean cadastrarUsuarioFacade(Usuario usuario) throws Exception{
		try {
			return usuarioDAO.cadastrarUsuario(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean alterarUsuarioFacade(Usuario usuario) throws Exception{
		try {
			return usuarioDAO.alterarUsuario(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
}