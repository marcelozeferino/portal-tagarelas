package com.br.portaltagarelas.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

public class BaseDAOImpl implements BaseDAOIntf{

	@Autowired
	private DataSource dataSource;
	@Autowired
	protected PlatformTransactionManager transactionManager;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}
    
	@Autowired
    public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
}