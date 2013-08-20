package br.com.portal.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class Sessao implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4963089141453017366L;

	@Id
	@SequenceGenerator(name = "seqGen", sequenceName = "seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "seqGen") 
	private int idSessao;
	private int idUsuario;
	private int idAgenda;
	public int getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(int idSessao) {
		this.idSessao = idSessao;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	
	
}
