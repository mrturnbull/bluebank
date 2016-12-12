package com.ibm.santander.dao;

import com.ibm.santander.model.Conta;
import com.ibm.santander.model.ContaRowMapper;

import java.io.IOException;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

public class ContaDaoImpl implements ContaDao {

	private DataSource dataSource = null;
	private JdbcTemplate jdbcTemplate = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public boolean isSaldoPositivo(Conta conta, BigDecimal saque){
	
		String sql = "";
		boolean saldoPositivo = true;
		Object args[] = new Object[]{saque};
	
		sql = "SELECT ((Saldo - ?) > 0) FROM Conta WHERE Id = " + conta.getId();
		
		if (jdbcTemplate == null) jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			saldoPositivo = jdbcTemplate.queryForObject(sql, args, Boolean.class);
		}
		catch (EmptyResultDataAccessException erdae){
			//TODO
		}
		
		return saldoPositivo;
	
	}
	
	@Override
	public Conta isCadastrada(String agencia, String numero){
	
		Conta conta = null;
		String sql = "";
		String args[] = new String[]{agencia, numero};
		
		sql = "SELECT Id, Agencia, Numero, Saldo FROM Conta WHERE Agencia = ? AND Numero = ?";

		if (jdbcTemplate == null) jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			conta = (Conta) jdbcTemplate.queryForObject(sql, args, new ContaRowMapper());
		}
		catch (EmptyResultDataAccessException erdae){
			//erdae.printStackTrace();
		}
		
		return conta;
			
	}

	@Override
	public BigDecimal depositar(int idConta, BigDecimal acrescimo){
	
		Object args[] = new Object[]{acrescimo};
		
		String sql = "UPDATE Conta SET Saldo = (Saldo + ?) WHERE Id = " + idConta;
		
		int retId = jdbcTemplate.update(sql, args);
		
		if (retId == 1)
			return acrescimo; 
		else
			return new BigDecimal(0);
						
	}
	
	@Override
	public BigDecimal sacar(int idConta, BigDecimal decrescimo){
			
		Object args[] = new Object[]{decrescimo};
		
		String sql = "UPDATE Conta SET Saldo = (Saldo - ?) WHERE Id = " + idConta;
		
		int retId = jdbcTemplate.update(sql, args);
		
		if (retId == 1)
			return decrescimo; 
		else
			return new BigDecimal(0);
						
	}
	
}