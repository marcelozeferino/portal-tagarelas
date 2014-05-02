package com.br.portaltagarelas.model;

import java.util.Date;

public class Agenda {

	private Long idAgenda;
	private String assunto;
	private String descricao;
	private Date data;
	private Date hora;
	private Long idSistema;
	private Usuario usuario;
	private String breveDescricao;
	private String arquivo;
	private Date dataHora;
	private String tempoSessao;
	private String observacao;
	
	public Agenda(){}
	
	public Agenda(Long idAgenda, String assunto, String descricao, Date data, Date hora, Long idSistema, Usuario usuario, String breveDescricao, String arquivo,
					Date dataHora, String tempoSessao, String observacao){
		
		this.idAgenda = idAgenda;
		this.assunto = assunto;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
		this.idSistema = idSistema;
		this.usuario = usuario;
		this.breveDescricao = breveDescricao;
		this.arquivo = arquivo;
		this.dataHora = dataHora;
		this.tempoSessao = tempoSessao;
		this.observacao = observacao;
	}
	
	public Long getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public Long getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getBreveDescricao() {
		return breveDescricao;
	}
	public void setBreveDescricao(String breveDescricao) {
		this.breveDescricao = breveDescricao;
	}
	public String getArquivo() {
		return arquivo;
	}
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public String getTempoSessao() {
		return tempoSessao;
	}
	public void setTempoSessao(String tempoSessao) {
		this.tempoSessao = tempoSessao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}