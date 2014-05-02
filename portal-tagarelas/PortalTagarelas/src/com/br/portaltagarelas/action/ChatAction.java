package com.br.portaltagarelas.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.br.portaltagarelas.facade.MensagemFacadeIntf;
import com.br.portaltagarelas.model.Agenda;
import com.br.portaltagarelas.model.Mensagem;
import com.br.portaltagarelas.model.Usuario;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("request")
public class ChatAction extends BaseAction{

	private static final long serialVersionUID = -1212915580447487943L;
	private ArrayList<Usuario> listaUsuariosPorAgenda;
	private Agenda agenda;
	private Map<String, Object> application = ActionContext.getContext().getApplication();
	private HashMap<Long, String> mapTextosDigitados = new HashMap<Long, String>();
	private SortedMap<Long, SortedMap<Long, String>> mensagensPorAgenda = new TreeMap<Long, SortedMap<Long, String>>();
	private String idAgendaStr;
	private String horaServidor;
	private Integer ordemMensagem = 0;
	private Long idAgendaAux;
	private SortedMap<Long, String> mapMensagens = new TreeMap<Long, String>();
	private SortedMap<Long, Long> mapRelacionamentosInvertido = new TreeMap<Long, Long>();
	private StringBuilder conversa  = new StringBuilder();
	private String idMensagemPai;
	private String msgTempo;
	private String msgTempoTotal;
	private String texto;
	
	private static int cont = 0;
	
	private @Autowired MensagemFacadeIntf mensagemFacade;
	
	public String chatTipico(){
		String status = getUsuarioLogado().getSobrenome() + " acabou de entrar na sala...";
		insereUsuarioNaSessao(agenda);
		montaMensagemStatusUsuarioPorAgenda(agenda, status);
		
		return SUCCESS;
	}
	
	public String chatTipicoFullFuncionando(){
		return chatTipico();
	}
	
	public String sairDaSessao(){
		String status = getUsuarioLogado().getSobrenome() + " acabou de sair da sala...";
		removeUsuarioDaSessao(agenda);
		montaMensagemStatusUsuarioPorAgenda(agenda, status);
		
		return SUCCESS;
	}
	
	public String sairDaSessaoAjax(){
		String status = getUsuarioLogado().getSobrenome() + " acabou de sair da sala...";
		String idAgenda = getRequest().getParameter("idAgenda");
		agenda = new Agenda();
		agenda.setIdAgenda(Long.parseLong(idAgenda));
		removeUsuarioDaSessao(agenda);
		montaMensagemStatusUsuarioPorAgenda(agenda, status);
		
		return null;
	}
	
	public String retornaMensagensPorAgendaAjax(){
		String mensagens = (String) application.get("agenda_"+agenda.getIdAgenda());
		
		return retornaJSON(mensagens);
	}
	
	@SuppressWarnings("unchecked")
	public String retornaListaUsuariosPorAgendaAjax(){
		listaUsuariosPorAgenda = (ArrayList<Usuario>) ActionContext.getContext().getApplication().get(agenda.getIdAgenda());
		
		return retornaJSON(listaUsuariosPorAgenda);
	}
	
	@SuppressWarnings("unchecked")
	public void insereUsuarioNaSessao(Agenda agenda){
		listaUsuariosPorAgenda = (ArrayList<Usuario>) application.get(agenda.getIdAgenda());
		
		if(listaUsuariosPorAgenda == null || listaUsuariosPorAgenda.size() == 0){
			listaUsuariosPorAgenda = new ArrayList<Usuario>();
		}
		
		if(!listaUsuariosPorAgenda.contains(getUsuarioLogado())){
			listaUsuariosPorAgenda.add(getUsuarioLogado());
		}

		application.put(String.valueOf(agenda.getIdAgenda()), listaUsuariosPorAgenda);
	}
	
	@SuppressWarnings("unchecked")
	public void removeUsuarioDaSessao(Agenda agenda){
		listaUsuariosPorAgenda = (ArrayList<Usuario>) application.get(agenda.getIdAgenda());
		
		if(listaUsuariosPorAgenda.contains(getUsuarioLogado())){
			listaUsuariosPorAgenda.remove(getUsuarioLogado());
		}
		
		application.put(String.valueOf(agenda.getIdAgenda()), listaUsuariosPorAgenda);
	}
	
	public String enviarMensagemUsuarioTipicoAjax(){
		enviarMensagemUsuarioTipico(texto, agenda, msgTempo, msgTempoTotal, idMensagemPai);
		
		return null;
	}
	
	public String enviarMensagemUsuarioAjax(){
		enviarMensagemUsuario(texto, agenda, msgTempo, msgTempoTotal, idMensagemPai);
		
		return null;
	}
	
	public void enviarMensagemUsuarioTipico(String texto, Agenda agenda, String msgTempo, String msgTempoTotal, String idMensagemPai) {
		Usuario usuario = getUsuarioLogado();
		String idAgenda = String.valueOf(agenda.getIdAgenda());
		
		Date agora = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		Mensagem msg = new Mensagem();
		msg.setConteudo(texto);
		msg.setHorario(agora);
		msg.setAgenda(agenda);
		msg.setUsuario(usuario);
		msg.setDigitacao(msgTempo);
		msg.setTempoTotalMensagem(msgTempoTotal);
		
		Mensagem mensagemPai = new Mensagem();
		mensagemPai.setIdMensagem(Long.parseLong(idMensagemPai));
		
		msg.setMensagemPai(mensagemPai);
		try {
			msg = mensagemFacade.salvarMensagemRetornandoIdFacade(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		idAgendaStr = "agenda_"+idAgenda;
		StringBuilder conversaTemp = new StringBuilder();
		
		conversaTemp.append("<div id=\"dialogo_" + cont + "\"" + " style=\"margin-left: 0; border-top:1px solid #eee\" class=\"arrowchat_chatboxmessagecontent arrowchat_self dial\"" + ">");
		conversaTemp.append("<span id=\"hora_" + cont + "\"" + "  class=\"arrowchat_ts\" style=\"float:right;\"" + ">" + sdf.format(agora) + "</span>");
		conversaTemp.append("<img src=\"" + usuario.getFoto() + "\"" + " class=\"arrowchat_userlist_foto\"" + "/>");
		conversaTemp.append("<strong>" + usuario.getSobrenome() + "</strong></br>");
		conversaTemp.append("<div>");
		conversaTemp.append(texto);
		conversaTemp.append("</div>");
		conversaTemp.append("<br clear=\"all\"" + "/>");
		conversaTemp.append("</div>");
		
		montaConversa(msg, conversaTemp.toString(), String.valueOf(idAgenda), agenda);
	}
	
	@SuppressWarnings("unchecked")
	public void enviarMensagemUsuario(String texto, Agenda agenda, String msgTempo,String msgTempoTotal, String idMensagemPai) {
		Usuario usuario = getUsuarioLogado();
		String idAgenda = String.valueOf(agenda.getIdAgenda());
		
		Date agora = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		Mensagem msg = new Mensagem();
		msg.setConteudo(texto);
		msg.setHorario(agora);
		msg.setAgenda(agenda);
		msg.setUsuario(usuario);
		msg.setDigitacao(msgTempo);
		msg.setTempoTotalMensagem(msgTempoTotal);
		
		Mensagem mensagemPai = new Mensagem();
		mensagemPai.setIdMensagem(Long.parseLong(idMensagemPai));
		
		msg.setMensagemPai(mensagemPai);
		try {
			msg = mensagemFacade.salvarMensagemRetornandoIdFacade(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		idAgendaStr = "agenda_"+idAgenda;
		StringBuilder conversaTemp = new StringBuilder();
		
		String textoResposta = "<strong>Responder para: " + usuario.getSobrenome() + " - </strong>" + texto;
		
		HashMap<Long, String> mapTextosDigitadosTemp = new HashMap<Long, String>();
		
		if(application.containsKey("textos_agenda_"+idAgenda)){
			mapTextosDigitadosTemp = (HashMap<Long, String>) application.get("textos_agenda_"+idAgenda);
		}
		
		mapTextosDigitadosTemp.put(new Long(msg.getIdMensagem()), textoResposta);
		application.put("textos_agenda_"+idAgenda, mapTextosDigitadosTemp);
		mapTextosDigitados = new HashMap<Long, String>();
		mapTextosDigitados.putAll(mapTextosDigitadosTemp);
		
		if(texto.indexOf("[aspas_simples]") > -1){
			texto = texto.replace("[aspas_simples]", "'");
		}
		
		if(texto.indexOf("[aspas_duplas]") > -1){
			texto = texto.replace("[aspas_duplas]", "\"");
		}
		
		cont = cont + 1;
		conversaTemp.append("<div id=\"dialogo_" + cont + "\"" + " style=\"margin-left: 0; border-top:1px solid #eee\" class=\"arrowchat_chatboxmessagecontent arrowchat_self dial\"" + ">");
		conversaTemp.append("<span id=\"hora_" + cont + "\"" + "  class=\"arrowchat_ts\" style=\"float:right;\"" + ">" + sdf.format(agora) + "</span>");
		conversaTemp.append("<img src=\"" + usuario.getFoto() + "\"" + " class=\"arrowchat_userlist_foto\"" + "/>");
		conversaTemp.append("<strong>" + usuario.getSobrenome() + "</strong></br>");
		conversaTemp.append("<div id=\"texto\">");
		conversaTemp.append(texto);
		conversaTemp.append("</div>");
		conversaTemp.append("<br clear=\"all\"" + "/>");
		conversaTemp.append("</div>");
		
		montaConversa(msg, conversaTemp.toString(), String.valueOf(idAgenda), agenda);
	}
	
	@SuppressWarnings("unchecked")
	public void montaMensagemStatusUsuarioPorAgenda(Agenda agenda, String status){
		String idAgenda = String.valueOf(agenda.getIdAgenda());
		Usuario usuario = getUsuarioLogado();
		
		if(application.containsKey("textos_agenda_"+idAgenda)){
			mapTextosDigitados = (HashMap<Long, String>) application.get("textos_agenda_"+idAgenda);
		}
		
		StringBuilder conversaTemp = new StringBuilder();
		
		idAgendaStr = "agenda_"+idAgenda;
		
		conversaTemp.append("<div id=\"dialogo_" + cont + "\"" + " style=\"margin-left: 0; border-top:1px solid #eee\" class=\"arrowchat_chatboxmessagecontent arrowchat_self dial\"" + ">");
		conversaTemp.append("<div style=\"padding-left: 5px; color: #D3D3D3\"" + "> " + status + "</div>");
		conversaTemp.append("</div>");
		
		Date agora = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		horaServidor = sdf.format(agora);
		
		Mensagem msg = new Mensagem();
		msg.setConteudo( status );
		msg.setHorario(agora);
		msg.setAgenda(agenda);
		msg.setUsuario(usuario);
		
		Mensagem mensagemPai = new Mensagem();
		mensagemPai.setIdMensagem(new Long(-1));
		
		msg.setMensagemPai(mensagemPai);
		try {
			msg = mensagemFacade.salvarMensagemRetornandoIdFacade(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		mensagens.put(new Long(ordemMensagem), msg.getIdMensagem() + "|" + msg.getIdMensagemPai() + "|" + conversaTemp.toString());
		ordemMensagem++;
		idAgendaAux = new Long(idAgenda);
		SortedMap<Long, String> mensagensTemp = new TreeMap<Long, String>();
		mensagensTemp.put(new Long(ordemMensagem), msg.getIdMensagem() + "|" + msg.getMensagemPai().getIdMensagem() + "|" + conversaTemp.toString());
		
		if(!mensagensPorAgenda.containsKey(idAgendaAux)){
			mensagensPorAgenda.put(idAgendaAux, mensagensTemp);
		}
		else{
			SortedMap<Long, String> mapMensagensTemp = mensagensPorAgenda.get(idAgendaAux);
			mapMensagensTemp.put(new Long(ordemMensagem), msg.getIdMensagem() + "|" + msg.getMensagemPai().getIdMensagem() + "|" + conversaTemp.toString());
			mensagensPorAgenda.put(idAgendaAux, mapMensagensTemp);
		}
		
		montaConversa(msg, conversaTemp.toString(), String.valueOf(idAgenda), agenda);
	}
	
	@SuppressWarnings("unchecked")
	private void montaConversa(Mensagem mensagem, String texto, String idAgenda, Agenda agenda){
		Long idMensagem = mensagem.getIdMensagem();
		Long idMensagemPai = mensagem.getMensagemPai().getIdMensagem();
		
		SortedMap<Long, String> mapMensagensTemp = new TreeMap<Long, String>();
		SortedMap<Long, Long> mapRelacionamentosInvertidosTemp = new TreeMap<Long, Long>();
		
		if(application.containsKey("msgs_agenda_"+idAgenda)){
			mapMensagensTemp = (SortedMap<Long, String>) application.get("msgs_agenda_"+idAgenda);
		}
		
		if(application.containsKey("relacionamentos_agenda_"+idAgenda)){
			mapRelacionamentosInvertidosTemp = (SortedMap<Long, Long>) application.get("relacionamentos_agenda_"+idAgenda);
		}
		
		mapMensagensTemp.put(idMensagem, texto);
		application.put("msgs_agenda_"+idAgenda, mapMensagensTemp);
		mapMensagens = new TreeMap<Long, String>();
		mapMensagens.putAll(mapMensagensTemp);
		
		// ID MENSAGEM - ID MENSAGEM PAI
		mapRelacionamentosInvertidosTemp.put(idMensagem, idMensagemPai);
		application.put("relacionamentos_agenda_"+idAgenda, mapRelacionamentosInvertidosTemp);
		mapRelacionamentosInvertido.putAll(mapRelacionamentosInvertidosTemp);
		
		conversa = new StringBuilder();
		
		for(Map.Entry<Long, Long> entry : mapRelacionamentosInvertido.entrySet()){
			if(mapMensagens.get(entry.getKey()) == null){
				continue;
			}
			if(entry.getValue() == -1){
				conversa.append(mapMensagens.get(entry.getKey()));
			}
			else{
				if(entry.getValue() != 0){
					conversa.append("<div id=\"divResp_" + entry.getKey() + "\"" + " style=\"display: none; margin-left: 0px; border-top:1px solid #eee\" class=\"arrowchat_chatboxmessagecontent arrowchat_self dial\"" + ">");
					buscaMensagensPais(entry.getValue(), entry.getKey(), 0);
				}
				conversa.append(mapMensagens.get(entry.getKey()));
				
				if(!"0".equals(String.valueOf(agenda.getIdSistema()))){
					conversa.append("<a id=\"escreverResposta_" + entry.getKey() + "\" href=\"#\" onclick=\"responderMensagem('" + entry.getKey() + "', '" + mapTextosDigitados.get(entry.getKey()) + "');\">Responder</a>");
					conversa.append("<a id=\"cancelarResposta_" + entry.getKey() + "\" href=\"#\" onclick=\"cancelarResposta('" + entry.getKey() + "');\" style=\"display: none;\">Cancelar Resposta</a>");
					conversa.append("<br clear=\"all\"" + "/>");
				}
			}
		}
		
		application.put("agenda_"+idAgenda, conversa.toString());
	}
	
	private void buscaMensagensPais(Long idMensagemPaiTemp, Long idMensagemDiv, Integer nivel){
		String var = "";
		boolean penultimaMsg = false;
		if(mapRelacionamentosInvertido.get(idMensagemPaiTemp) != 0){
			buscaMensagensPais(mapRelacionamentosInvertido.get(idMensagemPaiTemp), idMensagemDiv, nivel + 1);
			if(nivel == 0){
				conversa.append("</div>");
				penultimaMsg = true;
			}
			
			var = mapMensagens.get(idMensagemPaiTemp);
			
			if(!penultimaMsg){
				var = var.replace("margin-left: 0;", "margin-left: 0; background-color:#EAEEFB;");
			}
			else{
				String exibir = "<div id=\"exibirRespostas_" + idMensagemDiv + "\" style=\"display: block;\"><a href=\"#\" onclick=\"exibirRespostas('" + idMensagemDiv+"');\"> <img src=\"includes/images/sort_desc.png\" title=\"Exibir mensagens anteriores\" /></a></div>";
				String ocultar = "<div id=\"ocultarRespostas_" + idMensagemDiv + "\" style=\"display: none;\"><a href=\"#\" onclick=\"ocultarRespostas('" + idMensagemDiv +"');\"> <img src=\"includes/images/sort_asc.png\" title=\"Ocultar mensagens anteriores\" /></a></div>";
				
				Integer inicio = var.indexOf("<strong>");
				Integer fim = var.indexOf("</strong>");
				
				String nome = var.substring(inicio, fim+9);
				
				inicio = var.indexOf("<div id=\"texto\">");
				fim = var.indexOf("</div>");
				
				String texto = var.substring(inicio+16, fim);
				
				inicio = var.indexOf("<div id=\"dialogo_");
				fim = var.indexOf("</span>");
				
				String inicioDiv = var.substring(inicio, fim+7);
				
				var = inicioDiv + exibir + ocultar + nome + " - " + texto + "<br clear=\"all\"" + "/></div>";
				
				var = var.replace("margin-left: 0;", "margin-left: 0; background-color:#EAEEFB;");
			}
			conversa.append(var);
		}
		else{
			if(nivel == 0){
				conversa.append("</div>");
				penultimaMsg = true;
			}			
			
			var = mapMensagens.get(idMensagemPaiTemp);
			
			if(!penultimaMsg){
				var = var.replace("margin-left: 0;", "margin-left: 0; background-color:#EAEEFB;");
			}
			else{
				String exibir = "<div id=\"exibirRespostas_" + idMensagemDiv + "\" style=\"display: block;\"><a href=\"#\" onclick=\"exibirRespostas('" + idMensagemDiv+"');\"> <img src=\"includes/images/sort_desc.png\" title=\"Exibir mensagens anteriores\" /></a></div>";
				String ocultar = "<div id=\"ocultarRespostas_" + idMensagemDiv + "\" style=\"display: none;\"><a href=\"#\" onclick=\"ocultarRespostas('" + idMensagemDiv +"');\"> <img src=\"includes/images/sort_asc.png\" title=\"Ocultar mensagens anteriores\" /></a></div>";
				
				Integer inicio = var.indexOf("<strong>");
				Integer fim = var.indexOf("</strong>");
				
				String nome = var.substring(inicio, fim+9);
				
				inicio = var.indexOf("<div id=\"texto\">");
				fim = var.indexOf("</div>");
				
				String texto = var.substring(inicio+16, fim);
				
				inicio = var.indexOf("<div id=\"dialogo_");
				fim = var.indexOf("</span>");
				
				String inicioDiv = var.substring(inicio, fim+7);
				
				var = inicioDiv + exibir + ocultar + nome + " - " + texto + "<br clear=\"all\"" + "/></div>";
				
				var = var.replace("margin-left: 0;", "margin-left: 0; background-color:#EAEEFB;");
			}
			conversa.append(var);
		}
	}
	
	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public ArrayList<Usuario> getListaUsuariosPorAgenda() {
		return listaUsuariosPorAgenda;
	}

	public void setListaUsuariosPorAgenda(ArrayList<Usuario> listaUsuariosPorAgenda) {
		this.listaUsuariosPorAgenda = listaUsuariosPorAgenda;
	}

	public String getIdAgendaStr() {
		return idAgendaStr;
	}

	public void setIdAgendaStr(String idAgendaStr) {
		this.idAgendaStr = idAgendaStr;
	}

	public String getHoraServidor() {
		return horaServidor;
	}

	public void setHoraServidor(String horaServidor) {
		this.horaServidor = horaServidor;
	}

	public Integer getOrdemMensagem() {
		return ordemMensagem;
	}

	public void setOrdemMensagem(Integer ordemMensagem) {
		this.ordemMensagem = ordemMensagem;
	}

	public Long getIdAgendaAux() {
		return idAgendaAux;
	}

	public void setIdAgendaAux(Long idAgendaAux) {
		this.idAgendaAux = idAgendaAux;
	}

	public String getIdMensagemPai() {
		return idMensagemPai;
	}

	public void setIdMensagemPai(String idMensagemPai) {
		this.idMensagemPai = idMensagemPai;
	}

	public String getMsgTempo() {
		return msgTempo;
	}

	public void setMsgTempo(String msgTempo) {
		this.msgTempo = msgTempo;
	}

	public String getMsgTempoTotal() {
		return msgTempoTotal;
	}

	public void setMsgTempoTotal(String msgTempoTotal) {
		this.msgTempoTotal = msgTempoTotal;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}