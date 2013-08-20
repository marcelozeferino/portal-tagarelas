package br.com.portal.chat.tipico.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.portal.chat.dao.MensagemDAOImpl;
import br.com.portal.chat.model.vo.Mensagem;
import br.com.portal.dao.AgendaDAOImpl;
import br.com.portal.model.Agenda;
import br.com.portal.model.Usuario;

@ManagedBean
@SessionScoped
public class SalaChatBean implements Serializable {
private static final long  serialVersionUID = 1L;
private static final DateFormat df  = new SimpleDateFormat("HH:mm:ss");
private StringBuilder conversa  = new StringBuilder();
private StringBuilder resposta = new StringBuilder();

private StringBuilder alertaShow  = new StringBuilder();
private StringBuilder login  = new StringBuilder();
private List listaUsuarios = new ArrayList();
private List listaMensagem = new ArrayList();
private String palavra;
private String colori;
private int idPai;
private String horaServidor;

private static int cont = 0;
private static int contResposta = 0;
private static boolean flagAlerta = false;

private Usuario usuario;
private Agenda agenda = new Agenda();
private SortedMap<Long, String> mensagens = new TreeMap<Long, String>();
private SortedMap<Long, SortedMap<Long, String>> mensagensPorAgenda = new TreeMap<Long, SortedMap<Long, String>>();

private HashMap<Long, ArrayList<String>> mensagensAux = new HashMap<Long, ArrayList<String>>();
private HashMap<Long, String> mapTextosDigitados = new HashMap<Long, String>();
private SortedMap<Long, String> mapMensagens = new TreeMap<Long, String>();
private SortedMap<Long, ArrayList<Long>> mapRelacionamentos = new TreeMap<Long, ArrayList<Long>>();
private SortedMap<Long, Long> mapRelacionamentosInvertido = new TreeMap<Long, Long>();
private Integer ordemMensagem = 0;
private ChatBean chatBean = new ChatBean();
private Long idAgendaAux;
private String idAgendaStr;
private String keyListaUsuarios;

private FacesContext facesContext = FacesContext.getCurrentInstance(); 
private HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
private HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
private HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
private Map<String, Object> application = facesContext.getExternalContext().getApplicationMap();


public String getIdAgendaStr() {
	return idAgendaStr;
}

public void setIdAgendaStr(String idAgendaStr) {
	this.idAgendaStr = idAgendaStr;
}

public Long getIdAgendaAux() {
	return idAgendaAux;
}

public void setIdAgendaAux(Long idAgendaAux) {
	this.idAgendaAux = idAgendaAux;
}

public SortedMap<Long, SortedMap<Long, String>> getMensagensPorAgenda() {
	return mensagensPorAgenda;
}

public void setMensagensPorAgenda(
		SortedMap<Long, SortedMap<Long, String>> mensagensPorAgenda) {
	this.mensagensPorAgenda = mensagensPorAgenda;
}

public ChatBean getChatBean() {
	return chatBean;
}

public void setChatBean(ChatBean chatBean) {
	this.chatBean = chatBean;
}

public StringBuilder getAlertaShow() {
	return alertaShow;
}

public void setAlertaShow(StringBuilder alertaShow) {
	this.alertaShow = alertaShow;
}

public Usuario getUsuario() {
	return usuario;
}

public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}

public String getColori() {
	return colori;
}

public void setColori(String colori) {
	this.colori = colori;
}

public void digitando(String apelido) {
	   System.out.println("entrei aqui");
		setPalavra(apelido + " está digitando.");
}

public void naoDigitando() {
	   System.out.println("entrei aqui sem digitacao");
		setPalavra("");
}

public String getPalavra() {
	return palavra;
}

public void setPalavra(String palavra) {
	this.palavra = palavra;
}


public List getListaMensagem() {
	return listaMensagem;
}

public void setListaMensagem(List listaMensagem) {
	this.listaMensagem = listaMensagem;
}

public List getListaUsuarios() {
	return listaUsuarios;
}

public String retorno(){
	return "consegui";
}

public void setListaUsuarios(List listaUsuarios) {
	this.listaUsuarios = listaUsuarios;
}

	public SalaChatBean() {
		Date agora = new Date(System.currentTimeMillis());
		// usado logo que o usuario entrar na sala
		//conversa.append("[").append(df.format(agora)).append("] Sala de chat criada!\n");
	}

	public String getConversa() {
		return conversa.toString();
	}
	
	public String getResposta() {
		return resposta.toString();
	}
	
	public String getLogin() {
		return login.toString();
	}
	
	// metodos do chat tipico
	
	public void conversarTipico(String apelido, String cor, String texto,String idAgenda,String idUsuario,String url,String msgTempo,String msgTempoTotal, String flgResposta, String idMensagemPai, String nivelResposta) {
		Date agora = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(agora));
		Mensagem msg = new Mensagem();
		msg.setConteudo(texto);
		msg.setHorario(agora);
		msg.setIdAgenda(Integer.parseInt(idAgenda));
		msg.setIdUsuario(Integer.parseInt(idUsuario));
		msg.setDigitacao(msgTempo);
		msg.setTempoTotalMsg(msgTempoTotal);
		msg.setIdMensagemPai(Integer.parseInt(idMensagemPai));
		MensagemDAOImpl mensagemDAOImpl = new MensagemDAOImpl();
		mensagemDAOImpl.save(msg);
		idPai = msg.getIdMensagemPai();
		idAgendaStr = "agenda_"+idAgenda;
		StringBuilder conversaTemp = new StringBuilder();
		
		conversaTemp.append("<div id=\"dialogo_" + cont + "\"" + " style=\"margin-left: 0; border-top:1px solid #eee\" class=\"arrowchat_chatboxmessagecontent arrowchat_self dial\"" + ">");
		conversaTemp.append("<span id=\"hora_" + cont + "\"" + "  class=\"arrowchat_ts\" style=\"float:right;\"" + ">" + sdf.format(agora) + "</span>");
		conversaTemp.append("<img src=\"" + url + "\"" + " class=\"arrowchat_userlist_foto\"" + "/>");
		conversaTemp.append("<strong>" + apelido + "</strong></br>");
		conversaTemp.append("<div>");
		conversaTemp.append(texto);
		conversaTemp.append("</div>");
		conversaTemp.append("<br clear=\"all\"" + "/>");
		conversaTemp.append("</div>");
		
		montaConversa(new Long(msg.getIdMensagem()), new Long(idMensagemPai), conversaTemp.toString(), String.valueOf(idAgenda));
	}
	
	// fim tipico
	
	
	
	
	@SuppressWarnings("unchecked")
	public void conversar(String apelido, String cor, String texto,String idAgenda,String idUsuario,String url,String msgTempo,String msgTempoTotal, String flgResposta, String idMensagemPai) {
		Date agora = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(agora));
		Mensagem msg = new Mensagem();
		msg.setConteudo(texto);
		msg.setHorario(agora);
		msg.setIdAgenda(Integer.parseInt(idAgenda));
		msg.setIdUsuario(Integer.parseInt(idUsuario));
		msg.setDigitacao(msgTempo);
		msg.setTempoTotalMsg(msgTempoTotal);
		msg.setIdMensagemPai(Integer.parseInt(idMensagemPai));
		MensagemDAOImpl mensagemDAOImpl = new MensagemDAOImpl();
		mensagemDAOImpl.save(msg);
		idPai = msg.getIdMensagemPai();
		idAgendaStr = "agenda_"+idAgenda;
		StringBuilder conversaTemp = new StringBuilder();
		String textoResposta = "<strong>Responder para: " + apelido + " - </strong>" + texto;
		
		HashMap<Long, String> mapTextosDigitadosTemp = new HashMap<Long, String>();
		
		if(application.containsKey("textos_agenda_"+idAgenda)){
			mapTextosDigitadosTemp = (HashMap<Long, String>) application.get("textos_agenda_"+idAgenda);
		}
		
		mapTextosDigitadosTemp.put(new Long(msg.getIdMensagem()), textoResposta);
		application.put("textos_agenda_"+idAgenda, mapTextosDigitadosTemp);
		mapTextosDigitados = new HashMap<Long, String>();
		mapTextosDigitados.putAll(mapTextosDigitadosTemp);
		
		cont = cont + 1;
		conversaTemp.append("<div id=\"dialogo_" + cont + "\"" + " style=\"margin-left: 0; border-top:1px solid #eee\" class=\"arrowchat_chatboxmessagecontent arrowchat_self dial\"" + ">");
		conversaTemp.append("<span id=\"hora_" + cont + "\"" + "  class=\"arrowchat_ts\" style=\"float:right;\"" + ">" + sdf.format(agora) + "</span>");
		conversaTemp.append("<img src=\"" + url + "\"" + " class=\"arrowchat_userlist_foto\"" + "/>");
		conversaTemp.append("<strong>" + apelido + "</strong></br>");
		conversaTemp.append("<div id=\"texto\">");
		conversaTemp.append(texto);
		conversaTemp.append("</div>");
		conversaTemp.append("<br clear=\"all\"" + "/>");
		conversaTemp.append("</div>");
		
		montaConversa(new Long(msg.getIdMensagem()), Long.parseLong(idMensagemPai), conversaTemp.toString(), idAgenda);
	}
	
	@SuppressWarnings("unchecked")
	private void montaConversa(Long idMensagem, Long idMensagemPai, String texto, String idAgenda){
		
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
		mapRelacionamentos = new TreeMap<Long, ArrayList<Long>>();
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
//					conversa.append(mapMensagens.get(entry.getValue()));
					
//					if(mapRelacionamentosInvertido.containsValue(entry.getKey())){
//						continue;
//					}
					conversa.append("<div id=\"divResp_" + entry.getKey() + "\"" + " style=\"display: none; margin-left: 0px; border-top:1px solid #eee\" class=\"arrowchat_chatboxmessagecontent arrowchat_self dial\"" + ">");
					buscaMensagensPais(entry.getValue(), entry.getKey(), 0);
				}
				conversa.append(mapMensagens.get(entry.getKey()));
				
				if(!"0".equals(agenda.getIdSistema())){
					conversa.append("<a id=\"escreverResposta_" + entry.getKey() + "\" href=\"#\" onclick=\"responderMensagem('" + entry.getKey() + "', '" + mapTextosDigitados.get(entry.getKey()) + "');\">Responder</a>");
					conversa.append("<a id=\"cancelarResposta_" + entry.getKey() + "\" href=\"#\" onclick=\"cancelarResposta('" + entry.getKey() + "');\" style=\"display: none;\">Cancelar Resposta</a>");
//					conversa.append(" - <a id=\"exibirRespostas_" + entry.getKey() + "\" href=\"#\" onclick=\"exibirRespostas('" + entry.getKey()+"');\">Exibir Mensagens Anteriores</a>");
//					conversa.append("<a id=\"ocultarRespostas_" + entry.getKey() + "\" href=\"#\" onclick=\"ocultarRespostas('" + entry.getKey()+"');\" style=\"display: none;\">Ocultar Mensagens Anteriores</a>");
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
				String exibir = "<a id=\"exibirRespostas_" + idMensagemDiv + "\" href=\"#\" onclick=\"exibirRespostas('" + idMensagemDiv+"');\"> <img src=\"img/sort_desc.png\" title=\"Exibir mensagens anteriores\" /></a>";
				String ocultar = "<a id=\"ocultarRespostas_" + idMensagemDiv + "\" href=\"#\" onclick=\"ocultarRespostas('" + idMensagemDiv +"');\" style=\"display: none;\"> <img src=\"img/sort_asc.png\" title=\"Ocultar mensagens anteriores\" /></a>";
				
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
				String exibir = "<a id=\"exibirRespostas_" + idMensagemDiv + "\" href=\"#\" onclick=\"exibirRespostas('" + idMensagemDiv+"');\"> <img src=\"img/sort_desc.png\" title=\"Exibir mensagens anteriores\" /></a>";
				String ocultar = "<a id=\"ocultarRespostas_" + idMensagemDiv + "\" href=\"#\" onclick=\"ocultarRespostas('" + idMensagemDiv +"');\" style=\"display: none;\"> <img src=\"img/sort_asc.png\" title=\"Ocultar mensagens anteriores\" /></a>";
				
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
	 
	@SuppressWarnings("unchecked")
	public List<Usuario> entrou(Usuario usuario, int idAgenda) {
		
		if(application.containsKey("textos_agenda_"+idAgenda)){
			mapTextosDigitados = (HashMap<Long, String>) application.get("textos_agenda_"+idAgenda);
		}
		
		StringBuilder conversaTemp = new StringBuilder();
		
		AgendaDAOImpl agendaDao = new AgendaDAOImpl();
		agenda = agendaDao.detalheItem(String.valueOf(idAgenda));
		idAgendaStr = "agenda_"+idAgenda;
		keyListaUsuarios = "usuarios_"+idAgendaStr;
		usuario = usuarioLogado(usuario);
		setUsuario(usuario);
		
		conversaTemp.append("<div id=\"dialogo_" + cont + "\"" + " style=\"margin-left: 0; border-top:1px solid #eee\" class=\"arrowchat_chatboxmessagecontent arrowchat_self dial\"" + ">");
		conversaTemp.append("<div style=\"padding-left: 5px; color: #D3D3D3\"" + "> " + usuario.getNomeUsuario() + " acabou de entrar na sala..." + "</div>");
		conversaTemp.append("</div>");
		
		ArrayList<Usuario> listaUsuariosTemp = new ArrayList<Usuario>();
		
		if(application.containsKey(keyListaUsuarios)){
			listaUsuariosTemp = (ArrayList<Usuario>) application.get(keyListaUsuarios);
		}
		
		listaUsuariosTemp.add(usuario);
		application.put(keyListaUsuarios, listaUsuariosTemp);
		listaUsuarios = new ArrayList<Usuario>();
		listaUsuarios.addAll(listaUsuariosTemp);
		
		Date agora = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		horaServidor = sdf.format(agora);
		System.out.println(sdf.format(agora));
		Mensagem msg = new Mensagem();
		msg.setConteudo( usuario.getNomeUsuario() + " acabou de entrar na sala..." );
		msg.setHorario(agora);
		msg.setIdAgenda(idAgenda);
		msg.setIdUsuario(usuario.getIdUsuario());
		msg.setIdMensagemPai(new Integer(-1));
		//msg.setDigitacao(msgTempo);
		//msg.setTempoTotalMsg(msgTempoTotal);
		MensagemDAOImpl mensagemDAOImpl = new MensagemDAOImpl();
		mensagemDAOImpl.save(msg);
		
//		mensagens.put(new Long(ordemMensagem), msg.getIdMensagem() + "|" + msg.getIdMensagemPai() + "|" + conversaTemp.toString());
		ordemMensagem++;
		idAgendaAux = new Long(idAgenda);
		SortedMap<Long, String> mensagensTemp = new TreeMap<Long, String>();
		mensagensTemp.put(new Long(ordemMensagem), msg.getIdMensagem() + "|" + msg.getIdMensagemPai() + "|" + conversaTemp.toString());
		
		if(!mensagensPorAgenda.containsKey(idAgendaAux)){
			mensagensPorAgenda.put(idAgendaAux, mensagensTemp);
		}
		else{
			SortedMap<Long, String> mapMensagensTemp = mensagensPorAgenda.get(idAgendaAux);
			mapMensagensTemp.put(new Long(ordemMensagem), msg.getIdMensagem() + "|" + msg.getIdMensagemPai() + "|" + conversaTemp.toString());
			mensagensPorAgenda.put(idAgendaAux, mapMensagensTemp);
		}
		
		montaConversa(new Long(msg.getIdMensagem()), new Long(-1), conversaTemp.toString(), String.valueOf(idAgenda));
		
//		mensagens.put(idAgendaAux, conversa.toString());
//		idAgendaStr = "agenda_"+String.valueOf(idAgendaAux);
//		session.setAttribute(idAgendaStr, conversa.toString());
		
		// vou chamar para comecar a contar o tempo da sessao
//		TempoSessao tempo = new TempoSessao(10);
		
		
		return listaUsuarios;
	}
	
	public void alertaTempo(){
		flagAlerta = true;
		//return  "chatTipicoFullFuncionando?faces-redirect=true;"; 
		
	}
	
//	public List<Usuario> entrou(String apelido) {
//		Date agora = new Date(System.currentTimeMillis());
//		MensagemTipico msg = new MensagemTipico();
//		msg.setComentario("[" + df.format(agora) + "] " + apelido + " Entrou na sala ");
//		
//		//listaMensagem.add(msg);
//		return usuarioLogado(apelido);
//	}
	
	public void definindoCor(String cor) {
		setColori(cor);
	}
	
	public void usuarioLogout(String apelido) {
		Usuario usu = new Usuario();
		usu.setNomeUsuario(apelido);
		ListIterator list = listaUsuarios.listIterator();
		int index = 0;
		while(list.hasNext()) {
			Usuario element = (Usuario)list.next(); 
		    if((apelido).equals(element.getNomeUsuario())){
		    	listaUsuarios.remove(index);
		    	
		    	conversa.append("<div style=\"padding-left: 5px; color: #D3D3D3\"" + "> " + apelido + " acabou de sair da sala..." + "</div>");
		    	return;
		    }
		    index = index + 1;
		    //System.out.println(element.getNomeUsuario());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void usuarioLogout(String apelido, String idAgendaSaiu, String idUsuario) {
		StringBuilder conversaTemp = new StringBuilder();
		idAgendaStr = "agenda_"+idAgendaSaiu;
		keyListaUsuarios = "usuarios_"+idAgendaStr;
		listaUsuarios = (ArrayList<Usuario>) application.get(keyListaUsuarios);
		
		Usuario usu = new Usuario();
		usu.setNomeUsuario(apelido);

		ListIterator list = listaUsuarios.listIterator();
		int index = 0;
		while(list.hasNext()) {
			Usuario element = (Usuario)list.next(); 
		    if((apelido).equals(element.getNomeUsuario())){
		    	listaUsuarios.remove(index);
		    	
		    	conversaTemp.append("<div style=\"padding-left: 5px; color: #D3D3D3\"" + "> " + apelido + " acabou de sair da sala..." + "</div>");
		    	break;
		    }
		    index = index + 1;
		    //System.out.println(element.getNomeUsuario());
		}
		
		application.put(keyListaUsuarios, listaUsuarios);
		
		Date agora = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(agora));
		Mensagem msg = new Mensagem();
		msg.setConteudo( usuario.getNomeUsuario() + " acabou de sair da sala..." );
		msg.setHorario(agora);
		msg.setIdAgenda(Integer.parseInt(idAgendaSaiu));
		msg.setIdUsuario(Integer.parseInt(idUsuario));
		msg.setIdMensagemPai(new Integer(-1));
		//msg.setDigitacao(msgTempo);
		//msg.setTempoTotalMsg(msgTempoTotal);
		MensagemDAOImpl mensagemDAOImpl = new MensagemDAOImpl();
		mensagemDAOImpl.save(msg);
		//loginBean.setFlagValida(false);
		//loginBean.setUsuario(null);
		
//		mensagens.put(new Long(ordemMensagem), msg.getIdMensagem() + "|" + msg.getIdMensagemPai() + "|" + conversaTemp.toString());
		ordemMensagem++;
		idAgendaAux = Long.parseLong(idAgendaSaiu);
		idAgendaStr = "agenda_"+idAgendaSaiu;
		SortedMap<Long, String> mensagensTemp = new TreeMap<Long, String>();
		mensagensTemp.put(new Long(ordemMensagem), msg.getIdMensagem() + "|" + msg.getIdMensagemPai() + "|" + conversaTemp.toString());
		
		if(!mensagensPorAgenda.containsKey(idAgendaAux)){
			mensagensPorAgenda.put(idAgendaAux, mensagensTemp);
		}
		else{
			SortedMap<Long, String> mapMensagensTemp = mensagensPorAgenda.get(idAgendaAux);
			mapMensagensTemp.put(new Long(ordemMensagem), msg.getIdMensagem() + "|" + msg.getIdMensagemPai() + "|" + conversaTemp.toString());
			mensagensPorAgenda.put(idAgendaAux, mapMensagensTemp);
		}
		
		montaConversa(new Long(msg.getIdMensagem()), new Long(-1), conversaTemp.toString(), String.valueOf(idAgendaSaiu));
		
//		mensagens.put(idAgendaAux, conversa.toString());
		
//		idAgendaStr = "agenda_"+String.valueOf(idAgendaAux);
//		session.setAttribute(idAgendaStr, conversa.toString());
	}
	
//	public List<Usuario> usuarioLogado(Usuario usuario) {
//		
//		
//		
//		try {
//			  String nomeDaImagem = "img" + System.currentTimeMillis() + ".jpg";
//			  BufferedImage img = null;
//			  //recupera a imagem do banco usando a entidade usuario, poderia ser
//			  // qualquer byte para a classe ImageIO ler.
//			  img = ImageIO.read((new ByteArrayInputStream(usuario.getFoto())));
//			  
//			  URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
//			  
//			  
//			  String urlString = url.toString();
//			  
//			 
//			  String [] urlStringSplitFile = urlString.split("file:/"); 
//			  String a = urlStringSplitFile[1].toString();
//			  String [] urlStringSplit = a.split("WEB-INF"); 
//			  System.out.println(urlStringSplit[0].toString());
//			  System.out.println(url.toString());
//			  ImageIO.write(img, "JPG", new File(urlStringSplit[0].toString() + "\\imagens\\" + nomeDaImagem));
//			  usuario.setUrl("imagens/" + nomeDaImagem);
//			} catch (IOException e) {
//			  e.printStackTrace();
//			}
//			
//			listaUsuarios.add(usuario);
//		return listaUsuarios;
//		//login.append(listaUsuarios.toString()).append("\n");
//	}
	
public Usuario usuarioLogado(Usuario usuario) {
		
		
		
		try {
			  String nomeDaImagem = "img" + System.currentTimeMillis() + ".jpg";
			  BufferedImage img = null;
			  //recupera a imagem do banco usando a entidade usuario, poderia ser
			  // qualquer byte para a classe ImageIO ler.
			  img = ImageIO.read((new ByteArrayInputStream(usuario.getFoto())));
			  
			  URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
			  
			  
			  String urlString = url.toString();
			  
			  System.out.println("aqui0: ");
			  String [] urlStringSplitFile = urlString.split("file:/"); 
			  System.out.println("aqui01: ");
			  String a = urlStringSplitFile[1].toString();
			  System.out.println("aqui02: " + a);
			  String [] urlStringSplit = a.split("WEB-INF"); 
			  System.out.println("aqui03: " + urlStringSplit[0].toString());
			  System.out.println("aqui1: " + url.toString());
			  ImageIO.write(img, "JPG", new File(urlStringSplit[0].toString() + "\\imagens\\" + nomeDaImagem));
			  System.out.println("aqui2: ");
			  usuario.setUrl("imagens/" + nomeDaImagem);
			  System.out.println("aqui3: ");
		} catch (IOException e) {
			 System.out.println("aqui4: ");
			  System.out.println(e.getMessage());
			}
			
			
		return usuario;
		//login.append(listaUsuarios.toString()).append("\n");
	}
	


	
//	public List<Usuario> usuarioLogado(String apelido) {
//		Usuario usu = new Usuario();
//		usu.setNomeUsuario(apelido);
//		listaUsuarios.add(usu);
//		
//		try {
//			  String nomeDaImagem = "img" + System.currentTimeMillis() + ".jpg";
//			  BufferedImage img = null;
//			  //recupera a imagem do banco usando a entidade usuario, poderia ser
//			  // qualquer byte para a classe ImageIO ler.
//			  img = ImageIO.read(new ByteArrayInputStream(usuario.getBlFoto()));
//			  ImageIO.write(img, "JPG", new File("C:\\idoctor\\" + nomeDaImagem));
//			  
//			} catch (IOException e) {
//			  e.printStackTrace();
//			}
//		
//		return listaUsuarios;
//		//login.append(listaUsuarios.toString()).append("\n");
//	}
	
	
//	public void usuarioLogado(String apelido) {
//		Usuario usu = new Usuario();
//		usu.setNomeUsuario(apelido);
//		listaUsuarios.add(usu);
//		//login.append(listaUsuarios.toString()).append("\n");
//	}
	

	public static void main(String[] args) {
		Date agora = new Date(System.currentTimeMillis());
		System.out.println(df.format(agora));
		
	}

	public int getIdPai() {
		return idPai;
	}

	public void setIdPai(int idPai) {
		this.idPai = idPai;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public String getHoraServidor() {
		return horaServidor;
	}

	public void setHoraServidor(String horaServidor) {
		this.horaServidor = horaServidor;
	}

	public SortedMap<Long, String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(SortedMap<Long, String> mensagens) {
		this.mensagens = mensagens;
	}

	public Integer getOrdemMensagem() {
		return ordemMensagem;
	}

	public void setOrdemMensagem(Integer ordemMensagem) {
		this.ordemMensagem = ordemMensagem;
	}

	public HashMap<Long, ArrayList<String>> getMensagensAux() {
		return mensagensAux;
	}

	public void setMensagensAux(HashMap<Long, ArrayList<String>> mensagensAux) {
		this.mensagensAux = mensagensAux;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getKeyListaUsuarios() {
		return keyListaUsuarios;
	}

	public void setKeyListaUsuarios(String keyListaUsuarios) {
		this.keyListaUsuarios = keyListaUsuarios;
	}
	
}
