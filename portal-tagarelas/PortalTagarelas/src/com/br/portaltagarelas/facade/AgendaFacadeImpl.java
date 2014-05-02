package com.br.portaltagarelas.facade;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.portaltagarelas.dao.AgendaDAOIntf;
import com.br.portaltagarelas.model.Agenda;
import com.br.portaltagarelas.model.Usuario;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AgendaFacadeImpl implements AgendaFacadeIntf{

	@Autowired private AgendaDAOIntf agendaDAO;
	
	public boolean salvarAgendaFacade(Agenda agenda) throws Exception{
		try {
			return agendaDAO.salvarAgenda(agenda);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasDisponiveisFacade() throws Exception{
		try {
			return agendaDAO.listarAgendasDisponiveis();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasDisponiveisPorUsuarioFacade(Usuario usuario) throws Exception{
		try {
			return agendaDAO.listarAgendasDisponiveisPorUsuario(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasCriadasPorUsuarioFacade(Usuario usuario) throws Exception{
		try {
			return agendaDAO.listarAgendasCriadasPorUsuario(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarTodasAgendasCriadasPorUsuarioFacade(Usuario usuario) throws Exception{
		try {
			return agendaDAO.listarTodasAgendasCriadasPorUsuario(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasCriadasPorUsuarioEArquivadasFacade(Usuario usuario) throws Exception{
		try {
			return agendaDAO.listarAgendasCriadasPorUsuarioEArquivadas(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasQueUsuarioIraParticiparFacade(Usuario usuario) throws Exception{
		try {
			return agendaDAO.listarAgendasQueUsuarioIraParticipar(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasQueUsuarioParticipouFacade(Usuario usuario) throws Exception{
		try {
			return agendaDAO.listarAgendasQueUsuarioParticipou(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarTodasAgendasQueUsuarioIraParticiparFacade(Usuario usuario) throws Exception{
		try {
			return agendaDAO.listarTodasAgendasQueUsuarioIraParticipar(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarTodasAgendasQueUsuarioParticipouFacade(Usuario usuario) throws Exception{
		try {
			return agendaDAO.listarTodasAgendasQueUsuarioParticipou(usuario);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean cadastrarParticipacaoSessaoFacade(Usuario usuario, Agenda agenda) throws Exception{
		try {
			return agendaDAO.cadastrarParticipacaoSessao(usuario, agenda);
		} catch (Exception e) {
			throw e;
		}
	}
}