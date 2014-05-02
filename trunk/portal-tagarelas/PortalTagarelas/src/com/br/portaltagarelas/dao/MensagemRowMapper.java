package com.br.portaltagarelas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.br.portaltagarelas.model.Agenda;
import com.br.portaltagarelas.model.Mensagem;
import com.br.portaltagarelas.model.Usuario;

public class MensagemRowMapper implements RowMapper<Mensagem>{

	public Mensagem mapRow(ResultSet rs, int row) throws SQLException{
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(rs.getLong("idUsuario"));
		
		Agenda agenda = new Agenda();
		agenda.setIdAgenda(rs.getLong("idAgenda"));
		
		Mensagem mensagemPai = new Mensagem();
		mensagemPai.setIdMensagem(rs.getLong("idMensagemPai"));
		
		Mensagem mensagem = new Mensagem(rs.getLong("idMensagem"), agenda, usuario, rs.getTimestamp("horario"), rs.getString("conteudo"), rs.getString("tags"), rs.getString("digitacao"), 
				rs.getString("tempoTotalMsg"), mensagemPai);
		
		return mensagem;
	}
}