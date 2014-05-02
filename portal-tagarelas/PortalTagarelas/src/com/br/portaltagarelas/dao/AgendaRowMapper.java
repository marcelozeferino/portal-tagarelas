package com.br.portaltagarelas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.br.portaltagarelas.model.Agenda;
import com.br.portaltagarelas.model.Usuario;

public class AgendaRowMapper implements RowMapper<Agenda>{

	public Agenda mapRow(ResultSet rs, int row) throws SQLException{
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(rs.getLong("idUsuario"));
		
		Agenda agenda = new Agenda(rs.getLong("idAgenda"), rs.getString("assunto"), rs.getString("descricao"), rs.getTimestamp("data"), rs.getTimestamp("hora"),
				rs.getLong("idSistema"), usuario, rs.getString("breveDescricao"), rs.getString("arquivo"), rs.getTimestamp("datahora"), rs.getString("tempoSessao"),
				rs.getString("observacao"));
		
		return agenda;
	}
}