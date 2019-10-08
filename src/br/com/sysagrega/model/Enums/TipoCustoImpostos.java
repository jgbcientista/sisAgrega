package br.com.sysagrega.model.Enums;

public enum TipoCustoImpostos {
	
	ISS("ISS"),
	COFINS("COFINS"),
	PIS("PIS"),
	CSLL("CSLL"),
	IR("IR");
	
	private String descricao;

	private TipoCustoImpostos(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
