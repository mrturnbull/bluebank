package com.ibm.santander.service;

import com.ibm.santander.model.Conta;
import java.math.BigDecimal;

public interface ContaService {

	public Conta isCadastrada(String agencia, String numero);
	public BigDecimal depositar(int idConta, BigDecimal acrescimo);
	public BigDecimal     sacar(int idConta, BigDecimal decrescimo);
	public boolean isSaldoPositivo(Conta conta, BigDecimal saque);

}