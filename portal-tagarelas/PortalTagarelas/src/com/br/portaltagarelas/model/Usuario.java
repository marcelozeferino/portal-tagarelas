package com.br.portaltagarelas.model;

public class Usuario {

	private Long idUsuario;
	private String nome;
	private String senha;
	private String email;
	private String sobrenome;
	private String foto;
	
	public Usuario(){}
	
	public Usuario(Long idUsuario, String nome, String senha, String email, String sobrenome, String foto){
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.sobrenome= sobrenome;
		this.foto = foto;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}