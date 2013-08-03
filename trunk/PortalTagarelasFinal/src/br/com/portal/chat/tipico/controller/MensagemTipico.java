package br.com.portal.chat.tipico.controller;

import java.io.Serializable;

public class MensagemTipico implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5845644100438377340L;

	private String comentario;
	
	private String cor;
	
	

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
