package br.com.sysagrega.controller.vo;

import br.com.sysagrega.model.imp.Contrato;

public class ContratoVO {
	
	private Contrato contrato;
	private String nomeCliente;
	private String nomeProjeto;
	
	public ContratoVO(){
		
	}
	
	
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	


	
}
