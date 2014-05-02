package com.br.portaltagarelas.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.br.portaltagarelas.model.Usuario;

@Repository
public class UsuarioDAOImpl extends BaseDAOImpl implements UsuarioDAOIntf{

	public Usuario retornaUsuarioPorEmail(String email) throws Exception{
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			String sql = "SELECT * FROM usuario where email = :email;";
			
			param.addValue("email", email);
			
			return (Usuario) getNamedParameterJdbcTemplate().queryForObject(sql, param, new UsuarioRowMapper());
		} catch (Exception e) {
			if(e instanceof EmptyResultDataAccessException){
				return null;
			}else{
				throw e;
			}
		}
	}
	
	public boolean cadastrarUsuario(Usuario usuario) throws Exception{
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			StringBuilder sql = new StringBuilder("INSERT INTO usuario(nomeUsuario, senha, email, sobreNome, foto) ");
			sql.append("VALUES(:nomeUsuario, :senha, :email, :sobreNome, :foto)");
			
			param.addValue("nomeUsuario", usuario.getNome());
			param.addValue("senha", usuario.getSenha());
			param.addValue("email", usuario.getEmail());
			param.addValue("sobreNome", usuario.getSobrenome());
			param.addValue("foto", usuario.getFoto());
			
			boolean retorno = getNamedParameterJdbcTemplate().update(sql.toString(), param) > 0;
			
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
	
	public boolean alterarUsuario(Usuario usuario) throws Exception{
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			StringBuilder sql = new StringBuilder("UPDATE usuario SET nomeUsuario = :nomeUsuario, senha = :senha, email = :email, sobreNome = :sobreNome, foto = :foto ");
			sql.append("WHERE idUsuario = :idUsuario");
			
			param.addValue("nomeUsuario", usuario.getNome());
			param.addValue("senha", usuario.getSenha());
			param.addValue("email", usuario.getEmail());
			param.addValue("sobreNome", usuario.getSobrenome());
			param.addValue("foto", usuario.getFoto());
			param.addValue("idUsuario", usuario.getIdUsuario());
			
			boolean retorno = getNamedParameterJdbcTemplate().update(sql.toString(), param) > 0;
			
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