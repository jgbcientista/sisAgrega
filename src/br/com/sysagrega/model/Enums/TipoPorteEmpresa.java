package br.com.sysagrega.model.Enums;

public enum TipoPorteEmpresa {
	
	PEQUENO_PORTE("Pequeno Porte"),
	MEDIO_PORTE("Médio Porte"),
	GRANDE_GRANDE("Grande Porte");
	
	private String descricao;

	private TipoPorteEmpresa(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
