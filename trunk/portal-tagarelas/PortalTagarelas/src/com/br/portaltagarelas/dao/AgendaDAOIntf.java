package com.br.portaltagarelas.dao;

import java.util.ArrayList;

import com.br.portaltagarelas.model.Agenda;
import com.br.portaltagarelas.model.Usuario;

public interface AgendaDAOIntf {

	public ArrayList<Agenda> listarAgendasDisponiveis() throws Exception;
	
	public ArrayList<Agenda> listarAgendasDisponiveisPorUsuario(Usuario usuario) throws Exception;
	
	public ArrayList<Agenda> listarAgendasCriadasPorUsuario(Usuario usuario) throws Exception;
	
	public ArrayList<Agenda> listarAgendasCriadasPorUsuarioEArquivadas(Usuario usuario) throws Exception;

	public ArrayList<Agenda> listarAgendasQueUsuarioIraParticipar(Usuario usuario) throws Exception;

	public ArrayList<Agenda> listarAgendasQueUsuarioParticipou(Usuario usuario) throws Exception;

	public ArrayList<Agenda> listarTodasAgendasCriadasPorUsuario(Usuario usuario) throws Exception;
	
	public ArrayList<Agenda> listarTodasAgendasQueUsuarioIraParticipar(Usuario usuario) throws Exception;
	
	public ArrayList<Agenda> listarTodasAgendasQueUsuarioParticipou(Usuario usuario) throws Exception;
	
	public boolean salvarAgenda(Agenda agenda) throws Exception;
	
	public boolean cadastrarParticipacaoSessao(Usuario usuario, Agenda agenda) throws Exception;
}