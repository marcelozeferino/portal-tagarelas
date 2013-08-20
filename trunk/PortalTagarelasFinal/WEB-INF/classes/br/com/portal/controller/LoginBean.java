package br.com.portal.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.portal.chat.tipico.controller.SalaChatBean;
import br.com.portal.dao.UsuarioDAOImpl;
import br.com.portal.model.Usuario;
import br.com.portal.util.JPAUtil;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4975476298542147697L;
	private String login;
	private String senha;
	private String mensagem;
	private boolean flagValida;
	
	private Usuario usuario;
	
	//@ManagedProperty(value = "#{entityManager}")
	//private EntityManager entityManager;
	
	public void validarLogin(){
		JPAUtil jpa = new JPAUtil();
		UsuarioDAOImpl dao = new UsuarioDAOImpl(jpa.getEm());
		Usuario u  = dao.validaLogin(login,senha);
		if (u != null ) {
			SalaChatBean sala = new SalaChatBean();
			setUsuario(sala.usuarioLogado(u));
			setFlagValida(true);
		} else {
			setUsuario(null);
			setFlagValida(false);
		}
		
	}
	
	public String cadastrarUsuario(){
		return "cadastro";
	}

//	public void adiciona() {
//		//UsuarioDAOImpl dao = new UsuarioDAOImpl(this.entityManager);
//		//.adiciona(usuario);
//		this.setUsuario(new Usuario());
//	}
	
	
	public String chamaEsqueceuSenha(){
		return "esqueceusenha";
	}
	
	public void setLogin(String login) {
		this.login = login;
	}


	public String getLogin() {
		return login;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getSenha() {
		return senha;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setFlagValida(boolean flagValida) {
		this.flagValida = flagValida;
	}


	public boolean isFlagValida() {
		return flagValida;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
