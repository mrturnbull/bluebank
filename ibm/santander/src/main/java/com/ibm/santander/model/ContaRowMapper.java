package com.ibm.santander.model;

import com.ibm.santander.model.Conta;

import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;		

public class ContaRowMapper implements RowMapper
{
	Conta conta = null;

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		conta = new Conta();
		conta.setId(rs.getInt("ID"));
		conta.setAgencia(rs.getString("Agencia"));
		conta.setNumero(rs.getString("Numero"));
		conta.setSaldo(rs.getBigDecimal("Saldo"));
		
		return conta;
		
	}

}	
