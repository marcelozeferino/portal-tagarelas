package com.br.portaltagarelas.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.br.portaltagarelas.model.Agenda;
import com.br.portaltagarelas.model.Usuario;

@Repository
public class AgendaDAOImpl extends BaseDAOImpl implements AgendaDAOIntf{

	public boolean salvarAgenda(Agenda agenda) throws Exception{
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			StringBuilder sql = new StringBuilder("INSERT INTO agenda(assunto, descricao, data, hora, idSistema, idUsuario, breveDescricao, arquivo, datahora, tempoSessao, observacao) ");
			sql.append("VALUES(:assunto, :descricao, :data, :hora, :idSistema, :idUsuario, :breveDescricao, :arquivo, :datahora, :tempoSessao, :observacao)");
			
			param.addValue("assunto", agenda.getAssunto());
			param.addValue("descricao", agenda.getDescricao());
			param.addValue("data", agenda.getData());
			param.addValue("hora", agenda.getHora());
			param.addValue("idSistema", agenda.getIdSistema());
			param.addValue("idUsuario", agenda.getUsuario().getIdUsuario());
			param.addValue("breveDescricao", agenda.getBreveDescricao());
			param.addValue("arquivo", agenda.getArquivo());
			param.addValue("datahora", agenda.getDataHora());
			param.addValue("tempoSessao", agenda.getTempoSessao());
			param.addValue("observacao", agenda.getObservacao());
			
			boolean retorno = getNamedParameterJdbcTemplate().update(sql.toString(), param) > 0;
			
			transactionManager.commit(status);
			
			return retorno;
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasDisponiveis() throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtual = new Date();
			String dataStr = sdf.format(dataAtual);
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "SELECT * FROM agenda where data >= :data;";
			
			param.addValue("data", dataStr);
			
			return (ArrayList<Agenda>) getNamedParameterJdbcTemplate().query(sql, param, new AgendaRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasDisponiveisPorUsuario(Usuario usuario) throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtual = new Date();
			String dataStr = sdf.format(dataAtual);
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			StringBuilder sql = new StringBuilder("select a.* from agenda a ");
			sql.append("where a.idAgenda not in (select s.idAgenda from sessao s where s.idUsuario = :idUsuario) and a.idUsuario <> :idUsuario ");
			sql.append("and a.data >= :data;");
			
			param.addValue("idUsuario", usuario.getIdUsuario());
			param.addValue("idUsuario", usuario.getIdUsuario());
			param.addValue("data", dataStr);
			
			return (ArrayList<Agenda>) getNamedParameterJdbcTemplate().query(sql.toString(), param, new AgendaRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarTodasAgendasCriadasPorUsuario(Usuario usuario) throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtual = new Date();
			String dataStr = sdf.format(dataAtual);
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "SELECT * FROM agenda a where a.data >= :data and a.idUsuario = :idUsuario order by a.datahora desc;";
			
			param.addValue("data", dataStr);
			param.addValue("idUsuario", usuario.getIdUsuario());
			
			return (ArrayList<Agenda>) getNamedParameterJdbcTemplate().query(sql.toString(), param, new AgendaRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasCriadasPorUsuario(Usuario usuario) throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtual = new Date();
			String dataStr = sdf.format(dataAtual);
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "SELECT * FROM agenda a where a.data >= :data and a.idUsuario = :idUsuario order by a.datahora desc Limit 0,3;";
			
			param.addValue("data", dataStr);
			param.addValue("idUsuario", usuario.getIdUsuario());
			
			return (ArrayList<Agenda>) getNamedParameterJdbcTemplate().query(sql.toString(), param, new AgendaRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasCriadasPorUsuarioEArquivadas(Usuario usuario) throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtual = new Date();
			String dataStr = sdf.format(dataAtual);
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "SELECT * FROM agenda a where a.data < :data and a.idUsuario = :idUsuario order by a.datahora desc Limit 0,3;";
			
			param.addValue("data", dataStr);
			param.addValue("idUsuario", usuario.getIdUsuario());
			
			return (ArrayList<Agenda>) getNamedParameterJdbcTemplate().query(sql.toString(), param, new AgendaRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasQueUsuarioIraParticipar(Usuario usuario) throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtual = new Date();
			String dataStr = sdf.format(dataAtual);
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "SELECT * FROM agenda a, sessao s where a.data >= :data and s.idUsuario = :idUsuario and s.idAgenda = a.idAgenda order by a.datahora desc Limit 0,3;";
			
			param.addValue("data", dataStr);
			param.addValue("idUsuario", usuario.getIdUsuario());
			
			return (ArrayList<Agenda>) getNamedParameterJdbcTemplate().query(sql.toString(), param, new AgendaRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarTodasAgendasQueUsuarioIraParticipar(Usuario usuario) throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtual = new Date();
			String dataStr = sdf.format(dataAtual);
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "SELECT * FROM agenda a, sessao s where a.data >= :data and s.idUsuario = :idUsuario and s.idAgenda = a.idAgenda order by a.datahora desc;";
			
			param.addValue("data", dataStr);
			param.addValue("idUsuario", usuario.getIdUsuario());
			
			return (ArrayList<Agenda>) getNamedParameterJdbcTemplate().query(sql.toString(), param, new AgendaRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarAgendasQueUsuarioParticipou(Usuario usuario) throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtual = new Date();
			String dataStr = sdf.format(dataAtual);
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "SELECT * FROM agenda e, sessao s where e.data < :data and s.idUsuario = :idUsuario and s.idAgenda=e.idAgenda order by e.datahora desc Limit 0,3;";
			
			param.addValue("data", dataStr);
			param.addValue("idUsuario", usuario.getIdUsuario());
			
			return (ArrayList<Agenda>) getNamedParameterJdbcTemplate().query(sql.toString(), param, new AgendaRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Agenda> listarTodasAgendasQueUsuarioParticipou(Usuario usuario) throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataAtual = new Date();
			String dataStr = sdf.format(dataAtual);
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "SELECT * FROM agenda e, sessao s where e.data < :data and s.idUsuario = :idUsuario and s.idAgenda=e.idAgenda order by e.datahora desc;";
			
			param.addValue("data", dataStr);
			param.addValue("idUsuario", usuario.getIdUsuario());
			
			return (ArrayList<Agenda>) getNamedParameterJdbcTemplate().query(sql.toString(), param, new AgendaRowMapper());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean cadastrarParticipacaoSessao(Usuario usuario, Agenda agenda) throws Exception{
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "INSERT INTO sessao(idUsuario, idAgenda) VALUES(:idUsuario, :idAgenda) ";
			
			param.addValue("idUsuario", usuario.getIdUsuario());
			param.addValue("idAgenda", agenda.getIdAgenda());
			
			boolean retorno = getNamedParameterJdbcTemplate().update(sql, param) > 0;
			
			if(retorno){
				transactionManager.commit(status);
			}else{
				transactionManager.rollback(status);
			}
			return retorno;
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
	}
}