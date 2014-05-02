package com.br.portaltagarelas.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import com.br.portaltagarelas.facade.AgendaFacadeIntf;
import com.br.portaltagarelas.facade.UsuarioFacadeIntf;
import com.br.portaltagarelas.model.Agenda;
import com.br.portaltagarelas.model.Usuario;

// incluindo um comentario para fazer o commit

@Controller
@Scope("request")
public class AgendaAction extends BaseAction{

	private static final long serialVersionUID = -6199134090791205240L;
	private ArrayList<Agenda> listaAgendasDisponiveis;
	private ArrayList<Agenda> listaAgendasArquivadas;
	private ArrayList<Agenda> listaAgendasUsuario;
	private ArrayList<Agenda> listaAgendasArquivadasUsuario;
	private ArrayList<Agenda> listaAgendasUsuarioParticipa;
	private ArrayList<Agenda> listaAgendasUsuarioParticipou;
	private String dia;
	private String hora;
	private Agenda agenda;
	private Usuario usuario;
	
	@Autowired private AgendaFacadeIntf agendaFacade;
	@Autowired private UsuarioFacadeIntf usuarioFacade;
	
	public String listaAgenda(){
		try {
			if(getUsuarioLogado() == null){
				listaAgendasDisponiveis = agendaFacade.listarAgendasDisponiveisFacade();
			}else{
				Usuario usuario = getUsuarioLogado();
				
				listaAgendasDisponiveis = agendaFacade.listarAgendasDisponiveisPorUsuarioFacade(usuario);
				listaAgendasArquivadasUsuario = agendaFacade.listarAgendasQueUsuarioParticipouFacade(usuario);
			}
			
			return SUCCESS;
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String index(){
		try {
			Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();   
	        
	        if(authentication != null){  
	            Object obj = authentication.getPrincipal();  
	              
	            if (obj instanceof String){  
	            	setUsuarioLogado(null);
	            	return listaAgenda();
	            } else{
	            	Usuario usuarioLogadoTemp = (Usuario) getRequest().getSession().getAttribute("usuarioLogado");
	            	if(usuarioLogadoTemp == null){
	            		User user = (User)obj;
	            		setUsuarioLogado(usuarioFacade.retornaUsuarioPorEmailFacade(user.getUsername()));
	            		
	            		listaAgendasDisponiveis = agendaFacade.listarAgendasDisponiveisPorUsuarioFacade(getUsuarioLogado());
	    				listaAgendasArquivadasUsuario = agendaFacade.listarAgendasQueUsuarioParticipouFacade(getUsuarioLogado());
	            		
	            		return "usuarioLogado";
	            	}else{
	            		listaAgendasDisponiveis = agendaFacade.listarAgendasDisponiveisPorUsuarioFacade(getUsuarioLogado());
	    				listaAgendasArquivadasUsuario = agendaFacade.listarAgendasQueUsuarioParticipouFacade(getUsuarioLogado());
	            		
	            		return SUCCESS;
	            	}
	            }
	        }else{
	        	return listaAgenda();
	        }
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String listaArquivo(){
		try {
			listaAgendasArquivadas = agendaFacade.listarAgendasCriadasPorUsuarioEArquivadasFacade(getUsuarioLogado());
			
			return SUCCESS;
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String listaMinhaSessao(){
		try {
			Usuario usuarioTemp = getUsuarioLogado();
			
			listaAgendasUsuario = agendaFacade.listarAgendasCriadasPorUsuarioFacade(usuarioTemp);
			listaAgendasArquivadasUsuario = agendaFacade.listarAgendasCriadasPorUsuarioEArquivadasFacade(usuarioTemp);
			listaAgendasUsuarioParticipa = agendaFacade.listarAgendasQueUsuarioIraParticiparFacade(usuarioTemp);
			listaAgendasUsuarioParticipou = agendaFacade.listarAgendasQueUsuarioParticipouFacade(usuarioTemp);
			return SUCCESS;
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String listaMinhaSessaoGeral(){
		try {
			listaAgendasDisponiveis = agendaFacade.listarTodasAgendasCriadasPorUsuarioFacade(getUsuarioLogado());
			
			return SUCCESS;
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String listaSessaoVouParticipar(){
		try {
			listaAgendasUsuarioParticipa = agendaFacade.listarTodasAgendasQueUsuarioIraParticiparFacade(getUsuarioLogado());
			
			return SUCCESS;
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String listaArquivoParticipei(){
		try {
			listaAgendasUsuarioParticipou = agendaFacade.listarTodasAgendasQueUsuarioParticipouFacade(getUsuarioLogado());
			
			return SUCCESS;
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String cadastrarAgenda(){
		try {
			DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dataTemp = (Date) dataFormat.parse(dia);
			
			DateFormat horaFormat = new SimpleDateFormat("hh:mm");
			Date horaTemp = (Date) horaFormat.parse(hora);
			
			agenda.setData(dataTemp);
			agenda.setHora(horaTemp);
			agenda.setDataHora(getDataHora());
			agenda.setTempoSessao("1000000");
			agenda.setUsuario(getUsuarioLogado());
			
			if(validarFormAgenda(agenda)){
				if(agendaFacade.salvarAgendaFacade(agenda)){
					addActionMessage("Agenda cadastrada com sucesso!");
					return SUCCESS;
				}else{
					addActionError("Erro ao cadastrar a agenda. Tente novamente mais tarde!");
					return ERROR;
				}
			}else{
				addActionError(CAMPOS_OBRIGATORIOS);
				return 	ERROR;
			}
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String cadastrarParticipacaoAgenda(){
		try {
			if(agendaFacade.cadastrarParticipacaoSessaoFacade(usuario, agenda)){
				addActionMessage("Participa��o confirmada com sucesso!");
				return SUCCESS;
			}else{
				addActionError("Erro ao confirmar a participa��o. Tente novamente mais tarde!");
				return ERROR;
			}
		} catch (Exception e) {
			addActionError(ERRO_INESPERADO);
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private boolean validarFormAgenda(Agenda agenda){
		if((!"".equals(agenda.getAssunto()) && agenda.getAssunto() != null) && (!"".equals(agenda.getDescricao()) && agenda.getDescricao() != null)
				&& (!"".equals(agenda.getIdSistema()) && agenda.getIdSistema() != null)){
			return true;
		} else{
			return false;
		}
	}
	
	public Date getDataHora(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date data = new Date();
		try {
			data = sdf.parse(dia + " " +hora);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<Agenda> getListaAgendasDisponiveis() {
		return listaAgendasDisponiveis;
	}

	public void setListaAgendasDisponiveis(ArrayList<Agenda> listaAgendasDisponiveis) {
		this.listaAgendasDisponiveis = listaAgendasDisponiveis;
	}

	public ArrayList<Agenda> getListaAgendasArquivadas() {
		return listaAgendasArquivadas;
	}

	public void setListaAgendasArquivadas(
			ArrayList<Agenda> listaAgendasArquivadas) {
		this.listaAgendasArquivadas = listaAgendasArquivadas;
	}

	public ArrayList<Agenda> getListaAgendasUsuario() {
		return listaAgendasUsuario;
	}

	public void setListaAgendasUsuario(ArrayList<Agenda> listaAgendasUsuario) {
		this.listaAgendasUsuario = listaAgendasUsuario;
	}

	public ArrayList<Agenda> getListaAgendasArquivadasUsuario() {
		return listaAgendasArquivadasUsuario;
	}

	public void setListaAgendasArquivadasUsuario(
			ArrayList<Agenda> listaAgendasArquivadasUsuario) {
		this.listaAgendasArquivadasUsuario = listaAgendasArquivadasUsuario;
	}

	public ArrayList<Agenda> getListaAgendasUsuarioParticipa() {
		return listaAgendasUsuarioParticipa;
	}

	public void setListaAgendasUsuarioParticipa(
			ArrayList<Agenda> listaAgendasUsuarioParticipa) {
		this.listaAgendasUsuarioParticipa = listaAgendasUsuarioParticipa;
	}

	public ArrayList<Agenda> getListaAgendasUsuarioParticipou() {
		return listaAgendasUsuarioParticipou;
	}

	public void setListaAgendasUsuarioParticipou(
			ArrayList<Agenda> listaAgendasUsuarioParticipou) {
		this.listaAgendasUsuarioParticipou = listaAgendasUsuarioParticipou;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}