package com.ibm.santander.model;

import java.math.BigDecimal;

public class Conta {

	private int id;
	private String agencia = "";
	private String numero = "";
	private BigDecimal saldo;
	private BigDecimal valorTransferir;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getAgencia(){
		return agencia;
	}
	
	public void setAgencia(String agencia){
		this.agencia = agencia;
	}
	
	public String getNumero(){
		return numero;
	}
	
	public void setNumero(String numero){
		this.numero = numero;
	}
	
	public BigDecimal getSaldo(){
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo){
		this.saldo = saldo;
	}	
	
	public BigDecimal getValorTransferir(){
		return valorTransferir;
	}
	
	public void setValorTransferir(BigDecimal valorTransferir){
		this.valorTransferir = valorTransferir;
	}	
		
}