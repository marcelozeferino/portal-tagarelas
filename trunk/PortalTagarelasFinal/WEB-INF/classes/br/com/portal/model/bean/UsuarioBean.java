package br.com.portal.model.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.portal.model.vo.UsuarioVO;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {

	private List<UsuarioVO> usuarios;
	
	private UsuarioVO usuario = new UsuarioVO();
	
	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioVO> getUsuarios(){
		
		List listaUsuarios = new ArrayList<UsuarioVO>();
		
		UsuarioVO vo1 = new UsuarioVO();
		vo1.setEmail("marcelo@uol.com");
		vo1.setLogin("mar");
		vo1.setNome("nome");
		vo1.setSenha("senha");
		
		
		UsuarioVO vo2 = new UsuarioVO();
		vo2.setEmail("marcelo 2@uol.com");
		vo2.setLogin("mar 2");
		vo2.setNome("nome 2");
		vo2.setSenha("senha 2");
		
		listaUsuarios.add(vo1);
		listaUsuarios.add(vo2);
		
		return listaUsuarios;
		
	}
	
	public void gravar(){
		System.out.println("usuario: " + usuario.getLogin());
	}
}
