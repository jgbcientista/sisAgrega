package br.com.sysagrega.model.Enums;

public enum TipoDespesaOperacional {
	
	HOSPEDAGEM("Hospedagem"),
	ALIMENTACAO("Alimenta��o"),
	MATEIRO("Mateiro"),
	LAVAGEM_CARRO("Lavagem Carro");
	
	private String descricao;

	private TipoDespesaOperacional(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
