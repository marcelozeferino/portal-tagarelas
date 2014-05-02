package com.br.portaltagarelas.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.br.portaltagarelas.model.Mensagem;

@Repository
public class MensagemDAOImpl extends BaseDAOImpl implements MensagemDAOIntf{

	public boolean salvarMensagem(Mensagem mensagem) throws Exception{
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			StringBuilder sql = new StringBuilder("INSERT INTO mensagem(idAgenda, idUsuario, horario, conteudo, tags, digitacao, tempoTotalMsg, idMensagemPai) ");
			sql.append("VALUES(:idAgenda, :idUsuario, :horario, :conteudo, :tags, :digitacao, :tempoTotalMsg, :idMensagemPai)");
			
			param.addValue("idAgenda", mensagem.getAgenda().getIdAgenda());
			param.addValue("idUsuario", mensagem.getUsuario().getIdUsuario());
			param.addValue("horario", mensagem.getHorario());
			param.addValue("conteudo", mensagem.getConteudo());
			param.addValue("tags", mensagem.getTags());
			param.addValue("digitacao", mensagem.getDigitacao());
			param.addValue("tempoTotalMsg", mensagem.getTempoTotalMensagem());
			param.addValue("idMensagemPai", mensagem.getMensagemPai().getIdMensagem());
			
			boolean retorno = getNamedParameterJdbcTemplate().update(sql.toString(), param) > 0;
			
			transactionManager.commit(status);
			
			return retorno;
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
	}
	
	public Mensagem salvarMensagemRetornandoId(Mensagem mensagem) throws Exception{
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			StringBuilder sql = new StringBuilder("INSERT INTO mensagem(idAgenda, idUsuario, horario, conteudo, tags, digitacao, tempoTotalMsg, idMensagemPai) ");
			sql.append("VALUES(:idAgenda, :idUsuario, :horario, :conteudo, :tags, :digitacao, :tempoTotalMsg, :idMensagemPai)");
			
			param.addValue("idAgenda", mensagem.getAgenda().getIdAgenda());
			param.addValue("idUsuario", mensagem.getUsuario().getIdUsuario());
			param.addValue("horario", mensagem.getHorario());
			param.addValue("conteudo", mensagem.getConteudo());
			param.addValue("tags", mensagem.getTags());
			param.addValue("digitacao", mensagem.getDigitacao());
			param.addValue("tempoTotalMsg", mensagem.getTempoTotalMensagem());
			param.addValue("idMensagemPai", mensagem.getMensagemPai().getIdMensagem());
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(sql.toString(), param, keyHolder);
			
			mensagem.setIdMensagem(keyHolder.getKey().longValue());
			
			transactionManager.commit(status);
			
			return mensagem;
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
	}
}