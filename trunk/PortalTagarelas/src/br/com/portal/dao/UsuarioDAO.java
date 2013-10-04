package br.com.portal.dao;

import java.util.List;

import br.com.portal.model.Usuario;


public interface UsuarioDAO {

	    public void save(Usuario usuario);
	    public Usuario getUsuario(long id);
	    public List<Usuario> list();
	    public void remove(Usuario usuario);
	    public void update(Usuario usuario);
}
