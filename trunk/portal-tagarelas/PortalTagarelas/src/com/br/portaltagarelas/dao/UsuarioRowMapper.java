package com.br.portaltagarelas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.br.portaltagarelas.model.Usuario;

public class UsuarioRowMapper implements RowMapper<Usuario>{

	public Usuario mapRow(ResultSet rs, int row) throws SQLException{
		Usuario usuario = new Usuario(rs.getLong("idUsuario"), rs.getString("nomeUsuario"), rs.getString("senha"), rs.getString("email"), rs.getString("sobreNome"), rs.getString("foto"));
		
		return usuario;
	}
}