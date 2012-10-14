package br.com.portal.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




@SuppressWarnings("serial")
@Entity
public class Usuario implements Serializable{
	@Id
	@SequenceGenerator(name = "seqGen", sequenceName = "seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "seqGen") 
	private int idUsuario;
	private String nomeUsuario;
	private String sobreNome;
	private String senha;
	private String email;
	 private byte[] foto;
	
	
	
	 public Usuario(){
		 
	 }
	
	 
	


	public byte[] getFoto() {
		return foto;
	}





	public void setFoto(byte[] foto) {
		this.foto = foto;
	}





	public Usuario(int idUsuario, String nomeUsuario, String login, String senha, String email,String sobreNome) {
	        this.idUsuario = idUsuario;
	        this.nomeUsuario = nomeUsuario;
	        this.senha = senha;
	        this.email = email;
	        this.sobreNome= sobreNome; 
	    }
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenha() {
		return senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getSobreNome() {
		return sobreNome;
	}
}
