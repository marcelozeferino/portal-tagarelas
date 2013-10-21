package br.com.portal.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vw_mensagens_por_usuario")
public class MensagesPorUsuario implements Serializable{
	
	private static final long serialVersionUID = 8991510933800418177L;

	@Id
	private String nomeUsuario;
	
	private int idAgenda;
	private int totalMensagens;
	private int totalCaracteres;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public int getTotalMensagens() {
		return totalMensagens;
	}
	public void setTotalMensagens(int totalMensagens) {
		this.totalMensagens = totalMensagens;
	}
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public int getTotalCaracteres() {
		return totalCaracteres;
	}
	public void setTotalCaracteres(int totalCaracteres) {
		this.totalCaracteres = totalCaracteres;
	}	
	

}
