package br.com.portal.chat.model.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mensagem")
public class Mensagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4173912054542922787L;

	@Id
	@SequenceGenerator(name = "seqGen", sequenceName = "seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "seqGen") 
	private int idMensagem;
	
	private int  idAgenda;
	
	private int  idUsuario;
	
	@Temporal(TemporalType.TIME)
	private Date horario;
	
	private String  conteudo;
	
	private String tags;
	
	private String digitacao;
	
	private String  tempoTotalMsg;
	
	private int idMensagemPai;
	
	

	public String getTempoTotalMsg() {
		return tempoTotalMsg;
	}

	public void setTempoTotalMsg(String tempoTotalMsg) {
		this.tempoTotalMsg = tempoTotalMsg;
	}

	public int getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(int idMensagem) {
		this.idMensagem = idMensagem;
	}

	

	public int getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	public int getIdMensagemPai() {
		return idMensagemPai;
	}

	public void setIdMensagemPai(int idMensagemPai) {
		this.idMensagemPai = idMensagemPai;
	}
	
	
	
	
}
