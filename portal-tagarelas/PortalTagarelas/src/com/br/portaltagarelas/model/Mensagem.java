package com.br.portaltagarelas.model;

import java.util.Date;

public class Mensagem {
	
	private Long idMensagem;
	private Agenda agenda;
	private Usuario usuario;
	private Date horario;
	private String conteudo;
	private String tags;
	private String digitacao;
	private String tempoTotalMensagem;
	private Mensagem mensagemPai;
	
	public Mensagem(){}
	
	public Mensagem(Long idMensagem, Agenda agenda, Usuario usuario, Date horario, String conteudo, String tags, String digitacao, String tempoTotalMensagem, Mensagem mensagemPai){
		this.idMensagem = idMensagem;
		this.agenda = agenda;
		this.usuario = usuario;
		this.horario = horario;
		this.conteudo = conteudo;
		this.tags = tags;
		this.digitacao = digitacao;
		this.tempoTotalMensagem = tempoTotalMensagem;
		this.mensagemPai = mensagemPai;
	}
	
	public Long getIdMensagem() {
		return idMensagem;
	}
	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDigitacao() {
		return digitacao;
	}
	public void setDigitacao(String digitacao) {
		this.digitacao = digitacao;
	}
	public String getTempoTotalMensagem() {
		return tempoTotalMensagem;
	}
	public void setTempoTotalMensagem(String tempoTotalMensagem) {
		this.tempoTotalMensagem = tempoTotalMensagem;
	}
	public Mensagem getMensagemPai() {
		return mensagemPai;
	}
	public void setMensagemPai(Mensagem mensagemPai) {
		this.mensagemPai = mensagemPai;
	}
}