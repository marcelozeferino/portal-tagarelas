package com.br.portaltagarelas.facade;

import java.util.ArrayList;

import com.br.portaltagarelas.model.Agenda;
import com.br.portaltagarelas.model.Usuario;

public interface AgendaFacadeIntf {

	public ArrayList<Agenda> listarAgendasDisponiveisFacade() throws Exception;
	
	public ArrayList<Agenda> listarAgendasDisponiveisPorUsuarioFacade(Usuario usuario) throws Exception;
	
	public ArrayList<Agenda> listarAgendasCriadasPorUsuarioFacade(Usuario usuario) throws Exception;

	public ArrayList<Agenda> listarAgendasCriadasPorUsuarioEArquivadasFacade(Usuario usuario) throws Exception;

	public ArrayList<Agenda> listarAgendasQueUsuarioIraParticiparFacade(Usuario usuario) throws Exception;

	public ArrayList<Agenda> listarAgendasQueUsuarioParticipouFacade(Usuario usuario) throws Exception;
	
	public ArrayList<Agenda> listarTodasAgendasCriadasPorUsuarioFacade(Usuario usuario) throws Exception;
	
	public ArrayList<Agenda> listarTodasAgendasQueUsuarioIraParticiparFacade(Usuario usuario) throws Exception;
	
	public ArrayList<Agenda> listarTodasAgendasQueUsuarioParticipouFacade(Usuario usuario) throws Exception;
	
	public boolean salvarAgendaFacade(Agenda agenda) throws Exception;
	
	public boolean cadastrarParticipacaoSessaoFacade(Usuario usuario, Agenda agenda) throws Exception;
}