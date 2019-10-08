package br.com.sysagrega.model.Enums;

public enum TipoDespesaSeguranca {
	
	ASO("ASO"),
	PPRA("PPRA"),
	PSMSO("PSMSO"),
	EPI_UNIFORME("EPI e Uniforme");
	
	private String descricao;

	private TipoDespesaSeguranca(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
