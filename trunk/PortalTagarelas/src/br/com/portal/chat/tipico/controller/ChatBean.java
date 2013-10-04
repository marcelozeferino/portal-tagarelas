package br.com.portal.chat.tipico.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.icefaces.application.PushRenderer;

import br.com.portal.chat.dao.MensagemDAOImpl;
import br.com.portal.chat.model.vo.Mensagem;
import br.com.portal.controller.LoginBean;
import br.com.portal.model.Usuario;

@ManagedBean
@CustomScoped(value = "#{window}")
public class ChatBean implements Serializable {



	
/**
	 * 
	 */
	private static final long serialVersionUID = 3163196461035332461L;
private static final String GRUPO_PUSH = "GRUPO_PUSH_CHAT";
private String apelido;
private String mensagem;
private String cor;
private List<Usuario> listaUsuarios = new ArrayList();
private String msgTempo;
private String msgTempoTotal;

private String msgH;




@ManagedProperty(value = "#{salaChatBean}")
private SalaChatBean salaChat;

@ManagedProperty(value = "#{loginBean}")
private LoginBean loginBean;

public ChatBean() {
PushRenderer.addCurrentSession(GRUPO_PUSH);
}

public LoginBean getLoginBean() {
	return loginBean;
}

public void setLoginBean(LoginBean loginBean) {
	this.loginBean = loginBean;
}

public void setSalaChat(SalaChatBean salaChat) {
this.salaChat = salaChat;
}

private String idAgenda;

private Usuario usuario;

private String nomeUsuario;



public String getMsgH() {
	return msgH;
}

public void setMsgH(String msgH) {
	this.msgH = msgH;
}

public String getNomeUsuario() {
	return nomeUsuario;
}

public void setNomeUsuario(String nomeUsuario) {
	this.nomeUsuario = nomeUsuario;
}

public String getMsgTempoTotal() {
	return msgTempoTotal;
}

public void setMsgTempoTotal(String msgTempoTotal) {
	this.msgTempoTotal = msgTempoTotal;
}

public String getMsgTempo() {
	return msgTempo;
}

public void setMsgTempo(String msgTempo) {
	this.msgTempo = msgTempo;
}

public Usuario getUsuario() {
	return usuario;
}

public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}

public String getIdAgenda() {
	return idAgenda;
}

public void setIdAgenda(String idAgenda) {
	this.idAgenda = idAgenda;
}


public List<Usuario> getListaUsuarios() {
	return listaUsuarios;
}

public void setListaUsuarios(List<Usuario> listaUsuarios) {
	this.listaUsuarios = listaUsuarios;
}

public String getCor() {
	return cor;
}

public void setCor(String cor) {
	this.cor = cor;
}

public String getApelido() {
	return apelido;
}

public void setApelido(String apelido) {
	this.apelido = apelido;
}

public String getMensagem() {
	return mensagem;
}

public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
}

public static String getGrupoPush() {
	return GRUPO_PUSH;
}

public SalaChatBean getSalaChat() {
	return salaChat;
}

public void temAlguemDigitando(){
	salaChat.digitando(apelido);
	PushRenderer.render(GRUPO_PUSH);
}

public void naoTemAlguemDigitando(){
	salaChat.naoDigitando();
	PushRenderer.render(GRUPO_PUSH);
}

public void entrar(Usuario usuario, int id) { 
	setApelido(usuario.getNomeUsuario());
	setListaUsuarios(salaChat.entrou(usuario,id));
	setUsuario(usuario);
	//salaChat.definindoCor(cor);
	PushRenderer.render(GRUPO_PUSH);
}

//public void entrar(String login, int idUsuario) {
//	setApelido(login);
//	setListaUsuarios(salaChat.entrou(login));
//	
//	//salaChat.definindoCor(cor);
//	PushRenderer.render(GRUPO_PUSH);
//}

//public void entrar() {
//	salaChat.entrou(apelido);
//	salaChat.definindoCor(cor);
//	PushRenderer.render(GRUPO_PUSH);
//}

public String saiu() {
	String idAgendaSaiu = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idAgenda");
	String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario");
	System.out.println(idAgendaSaiu);
	salaChat.usuarioLogout(apelido, idAgendaSaiu, idUsuario);
	apelido = "";
	
	salaChat.setUsuario(null);
	PushRenderer.render(GRUPO_PUSH);
	return "index?faces-redirect=true;";
}

public String saiu(String apelido, String idAgendaSaiu, String idUsuario) {
	System.out.println(idAgendaSaiu);
	salaChat.usuarioLogout(apelido, idAgendaSaiu, idUsuario);
	apelido = "";
	
	salaChat.setUsuario(null);
	PushRenderer.render(GRUPO_PUSH);
	return "index?faces-redirect=true;";
}


public void enviarMensagemTipico() {
	System.out.println(msgTempo);
	System.out.println(msgH);
	System.out.println(msgTempoTotal);
	if ((mensagem != null) && (! mensagem.isEmpty())) {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idAgenda");
		String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario");
		String url = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("url");
		String flgResposta = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("flgResposta");
		String idMensagemPai = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMensagemPai");
		String nivelResposta = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nivelResposta");
		System.out.println(idUsuario);
		salaChat.conversarTipico(apelido, cor, mensagem, id,idUsuario,url,msgTempo,msgTempoTotal, flgResposta, idMensagemPai, nivelResposta);
		
		mensagem = "";
		PushRenderer.render(GRUPO_PUSH);
	}
	msgTempo = "";
	msgTempoTotal = "";
}

	public void enviarMensagem() {
		System.out.println(msgTempo);
		System.out.println(msgH);
		System.out.println(msgTempoTotal);
		if ((mensagem != null) && (! mensagem.isEmpty())) {
			String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idAgenda");
			String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario");
			String url = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("url");
			String flgResposta = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("flgResposta");
			String idMensagemPai = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMensagemPai");
//			String nivelResposta = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nivelResposta");
			System.out.println(idUsuario);
//			salaChat.conversar(apelido, cor, mensagem, id,idUsuario,url,msgTempo,msgTempoTotal, flgResposta, idMensagemPai, nivelResposta);
			salaChat.conversar(apelido, cor, mensagem, id,idUsuario,url,msgTempo,msgTempoTotal, flgResposta, idMensagemPai);
			
			mensagem = "";
			PushRenderer.render(GRUPO_PUSH);
		}
		msgTempo = "";
		msgTempoTotal = "";
	}

}
