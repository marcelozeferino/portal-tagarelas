package br.com.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import br.com.portal.dao.AgendaDAOImpl;
import br.com.portal.model.Agenda;
import br.com.portal.model.Usuario;

@ManagedBean
@SessionScoped
public class SessaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8805243817139080218L;
	
	private String assunto;
	private String descricao;
	private int idSistema;
	private Calendar data = new GregorianCalendar();
	private Calendar hora = new GregorianCalendar();
	private int idUsuario;
	private String mensagem;
	
	private List<Agenda> sessoes;
	
	private List<Agenda> donoSessaoArquivada;
	
	private List<Agenda> minhasArquivadas;
	
	private List<Agenda> arquivadasParticipei;
	
	private List<Agenda> listaSessaoVouParticipar;
	
	private List<Agenda> listaMinhaSessaoGeral;
	
	
	public List<Agenda> getListaMinhaSessaoGeral() {
		return listaMinhaSessaoGeral;
	}

	public void setListaMinhaSessaoGeral(List<Agenda> listaMinhaSessaoGeral) {
		this.listaMinhaSessaoGeral = listaMinhaSessaoGeral;
	}

	public List<Agenda> getListaSessaoVouParticipar() {
		return listaSessaoVouParticipar;
	}

	public void setListaSessaoVouParticipar(List<Agenda> listaSessaoVouParticipar) {
		this.listaSessaoVouParticipar = listaSessaoVouParticipar;
	}

	public List<Agenda> getArquivadasParticipei() {
		return arquivadasParticipei;
	}

	public void setArquivadasParticipei(List<Agenda> arquivadasParticipei) {
		this.arquivadasParticipei = arquivadasParticipei;
	}

	public List<Agenda> getMinhasArquivadas() {
		return minhasArquivadas;
	}

	public void setMinhasArquivadas(List<Agenda> minhasArquivadas) {
		this.minhasArquivadas = minhasArquivadas;
	}




	@ManagedProperty(value = "#{loginBean}")
	public LoginBean loginBean;	
	
	public void setLoginBean(LoginBean loginBean){
		this.loginBean = loginBean;
	}
	
	public List<Agenda> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Agenda> sessoes) {
		this.sessoes = sessoes;
	}

	
	
	public void setDonoSessaoArquivada(List<Agenda> donoSessao) {
		this.donoSessaoArquivada = donoSessao;
	}

	public String getMinhaSessao(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Usuario u = loginBean.getUsuario();
		String retorno = "";
		if(u == null){
			setMensagem("Usuário não está logado");
			retorno = "semAcesso?faces-redirect=true;";
		}else{
			List<Agenda> arquivos = dao.getMinhaSessao(Integer.toString(u.getIdUsuario()));
			setSessoes(arquivos);
			retorno =  "listaMinhaSessao?faces-redirect=true;";
		}
		return retorno;
	}
	
	public String getArquivoMinhaSessao(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Usuario u = loginBean.getUsuario();
		String retorno = "";
		if(u == null){
			setMensagem("Usuário não está logado");
			retorno = "semAcesso?faces-redirect=true;";
		}else{
			List<Agenda> lista = dao.getArquivoMinhaSessao(Integer.toString(u.getIdUsuario()));
			if(lista.size() == 0){
				setMensagem("Não existem salas agendadas.");
			}else{
				setMinhasArquivadas(lista);
			}
			retorno =  "listaArquivo?faces-redirect=true;";

		}	
		return retorno;
	
	}
	
	
	public String getSessaoParticiparGeral(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Usuario u = loginBean.getUsuario();
		String retorno = "";
		if(u == null){
			setMensagem("Usuário não está logado");
			retorno = "semAcesso?faces-redirect=true;";
		}else{
			List<Agenda> lista = dao.getSessaoParticiparGeral(Integer.toString(u.getIdUsuario()));
			if(lista.size() == 0){
				setMensagem("Não existem salas agendadas.");
			}else{
				setListaSessaoVouParticipar(lista);
			}
			retorno =  "listaSessaoVouParticipar?faces-redirect=true;";

		}	
		return retorno;
	
	}
	
	public String getMinhaSessaoGeral(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Usuario u = loginBean.getUsuario();
		String retorno = "";
		if(u == null){
			setMensagem("Usuário não está logado");
			retorno = "semAcesso?faces-redirect=true;";
		}else{
			List<Agenda> lista = dao.getMinhaSessaoGeral(Integer.toString(u.getIdUsuario()));
			if(lista.size() == 0){
				setMensagem("Não existem salas agendadas.");
			}else{
				setListaMinhaSessaoGeral(lista);
			}
			retorno =  "listaMinhaSessaoGeral?faces-redirect=true;";

		}	
		return retorno;
	
	}
	
	
	
	
	public String getArquivoSessaoGeralParticipei(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Usuario u = loginBean.getUsuario();
		String retorno = "";
		if(u == null){
			setMensagem("Usuário não está logado");
			retorno = "semAcesso?faces-redirect=true;";
		}else{
			List<Agenda> lista = dao.getSessaoGeralParticipei(Integer.toString(u.getIdUsuario()));
			if(lista.size() == 0){
				setMensagem("Não existem salas agendadas.");
			}else{
				setArquivadasParticipei(lista);
			}
			retorno =  "listaArquivoParticipei?faces-redirect=true;";

		}	
		return retorno;
	
	}
	
	
	public List<Agenda>  getDonoSessaoArquivada(){
		AgendaDAOImpl dao = new AgendaDAOImpl();
		Usuario u = loginBean.getUsuario();
		//String retorno = "";
		List<Agenda> listaDono = new ArrayList<Agenda>();
		if(u == null){
			setMensagem("Usuário não está logado");
			//retorno = "semAcesso?faces-redirect=true;";
		}else{
			listaDono = dao.getDonoSessaoArquivada(Integer.toString(u.getIdUsuario()));
			setSessoes(listaDono);
			//retorno =  "listaMinhaSessao?faces-redirect=true;";
		}
		return listaDono;
	}
	
	 	
	public String listaSessao(){
		return "listaSessoes?faces-redirect=true;";
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
