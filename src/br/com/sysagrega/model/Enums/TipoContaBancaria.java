package br.com.sysagrega.model.Enums;

public enum TipoContaBancaria {
	
	CORRENTE("Corrente"),
	POUPANCA("Poupança"),
	SALARIO("Salário");
	
	private String descricao;

	private TipoContaBancaria(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
