package br.com.portal.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;

import br.com.portal.dao.AgendaDAOImpl;
import br.com.portal.model.Agenda;
import br.com.portal.model.Usuario;

@ManagedBean
@SessionScoped 
public class ArquivoBean {

	private String assunto;
	private String descricao;
	private int idSistema;
	private Calendar data = new GregorianCalendar();
	private Calendar hora = new GregorianCalendar();
	private int idUsuario;
	private String mensagem;
	
	 	
	@ManagedProperty(value = "#{loginBean}")
	public LoginBean loginBean;	
	
	public void setLoginBean(LoginBean loginBean){
		this.loginBean = loginBean;
	}
	
	
	private List<Agenda> arquivos;
	
	
	
	
	
	public List<Agenda> getArquivos() {
		return arquivos;
	}




	public void setArquivos(List<Agenda> arquivos) {
		this.arquivos = arquivos;
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


	public String getArquivo(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		List<Agenda> arquivos = dao.getArquivo();
		setArquivos(arquivos);
		return "listaArquivo";
	}

	public String listaArquivo(){
		Usuario u = loginBean.getUsuario();
		System.out.println(u.getNomeUsuario());
		return "listaArquivo";
	}
	
	
	public String pegarDetalheItem(){
		return "listaMinhaSessao";
	}
	
	
	
	
}
