package br.com.portal.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SistemaBean {

	private String assunto;
	private String descricao;
	private int idSistema;
	private Calendar data = new GregorianCalendar();
	private Calendar hora = new GregorianCalendar();
	private int idUsuario;
	private String mensagem;
	
	 	
	public String listaSistema(){
		return "sistemas";
	}
	
	public String getAssunto() {
		return assunto;
	}




	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	public int getIdSistema() {
		return idSistema;
	}




	public void setIdSistema(int idSistema) {
		this.idSistema = idSistema;
	}




	public Calendar getData() {
		return data;
	}




	public void setData(Calendar data) {
		this.data = data;
	}




	public Calendar getHora() {
		return hora;
	}




	public void setHora(Calendar hora) {
		this.hora = hora;
	}




	public int getIdUsuario() {
		return idUsuario;
	}




	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}




	public String getMensagem() {
		return mensagem;
	}




	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}




	
	
	
	
	
	
}
