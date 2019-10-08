package br.com.sysagrega.model.Enums;

public enum TipoCustoBdiComissao {
	
	BDI("BDI"),
	COMISSAO("Comiss�o");
	
	private String descricao;

	private TipoCustoBdiComissao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
