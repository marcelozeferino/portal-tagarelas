package br.com.portal.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mensagensporminuto_usuario")
public class MensagensPorMinutoUsuario implements Serializable{
	
	private static final long serialVersionUID = 8991510933800418177L;

	@Id
	private String horario;
	private String nomeUsuario;
	
	private int idAgenda;
	private int total;
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}	
	

}
