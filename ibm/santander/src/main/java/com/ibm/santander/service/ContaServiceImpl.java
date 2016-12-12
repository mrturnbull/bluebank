package com.ibm.santander.service;

import com.ibm.santander.model.Conta;
import com.ibm.santander.dao.ContaDao;
import com.ibm.santander.service.ContaService;

import java.math.BigDecimal;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service("contaService")
public class ContaServiceImpl implements ContaService{

	@Autowired
    ContaDao dao;
    
	public Conta isCadastrada(String agencia, String numero){
		return dao.isCadastrada(agencia, numero);
	}
	
	@Transactional
	public BigDecimal depositar(int idConta, BigDecimal acrescimo){
		return dao.depositar(idConta, acrescimo);
	}
	
	@Transactional	
	public BigDecimal sacar(int idConta, BigDecimal decrescimo){
		return dao.sacar(idConta, decrescimo);
	}  
	
	public boolean isSaldoPositivo(Conta conta, BigDecimal saque){
		return dao.isSaldoPositivo(conta, saque);
	}
    
}