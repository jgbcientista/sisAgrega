package br.com.sysagrega.model.Enums;

public enum TipoColaborador {
	
	SOCIO("S�cio"),
	EMPREGADO("Empregado"),
	PARCEIRO("Parceiro"),
	ESTAGIO("Estagi�rio");
	
	private String descricao;

	private TipoColaborador(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
