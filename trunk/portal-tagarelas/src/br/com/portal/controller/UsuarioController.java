package br.com.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import br.com.portal.dao.UsuarioDAO;
import br.com.portal.dao.UsuarioDAOImpl;
import br.com.portal.model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioController {
	
	
	
	public Usuario usuario;
	private DataModel listaUsuarios;
	 
//	public DataModel getListarUsuarios() {
//		List<Usuario> usuarios = new ArrayList<Usuario>();
//		usuario = new Usuario();
//		usuario.setIdUsuario(0);
//		usuario.setNomeUsuario("nomeUsuario0");
//		usuario.setLogin("login0");
//		usuario.setSenha("senha0");
//		
//		usuarios.add(usuario);
//		Usuario usuario2 = new Usuario();
//		usuario2.setIdUsuario(1);
//		usuario2.setNomeUsuario("nomeUsuario1");
//		usuario2.setLogin("login1");
//		usuario2.setSenha("senha1");
//		usuarios.add(usuario2);
		
//		listaUsuarios = new ListDataModel<Usuario>(usuarios);
//		return listaUsuarios;
//	List<Usuario> lista = new UsuarioDAOImpl().list();
//	listaUsuarios = new ListDataModel(lista);
//	return listaUsuarios;
//	}
	
	public Usuario getUsuario() {
	return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
	}
	
	public String prepararAdicionarUsuario(){
		usuario = new Usuario();
	return "gerenciarUsuario";
	}
	
//	public String adicionarUsuario(){
//		UsuarioDAO dao = new UsuarioDAOImpl();
//		dao.save(usuario);
//		return "index";
//		}
	
	public String prepararAlterarUsuario(){
		usuario = (Usuario)(listaUsuarios.getRowData());
		return "gerenciarUsuario";
	}
	
//	public String excluirUsuario(){
//		Usuario usuarioTemp = (Usuario)(listaUsuarios.getRowData());
//		UsuarioDAO dao = new UsuarioDAOImpl();
//		dao.remove(usuarioTemp);
//		return "index";
//	}
	
//	public String alterarLivro(){
//		UsuarioDAO dao = new UsuarioDAOImpl();
//		dao.update(usuario);
//		return "index";
//		}
	
}
