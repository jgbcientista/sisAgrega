package br.com.sysagrega.model.Enums;

public enum TipoEscolaridade {
	
	PRIMEIRO_GRAU("Primeiro Grau"),
	NEVEL_MEDIO("N�vel M�dio"),
	SUPERIOR_INCOMPLETO("Superior Incompleto"),
	SUPERIOR_CURSANDO("Superior Cursando"),
	SUPERIOR("Superior Completo"),
	POSGRADUADO("P�s-Graduado"),
	MESTRADO("Mestrado");
	
		
	private String descricao;

	private TipoEscolaridade(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
